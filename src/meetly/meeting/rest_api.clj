(ns meetly.meeting.rest-api
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [org.httpkit.server :as server]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response not-found bad-request]]
            [meetly.config :as config]
            [meetly.meeting.database :as db]
            [meetly.meeting.processors :as processors]
            [dialog.engine.core :as dialog]
            [meetly.core :as meetly-core]))

(defn- fetch-meetings
  "Fetches meetings from the db and preparse them for transit via JSON."
  []
  (->> (db/all-meetings)
       (map first)))

(defn- all-meetings
  "Returns all meetings from the db."
  [_req]
  (response (fetch-meetings)))

(defn- add-meeting
  "Adds a meeting to the database.
  Converts the epoch dates it receives into java Dates.
  Returns the id of the newly-created meeting as `:id-created`."
  [req]
  (let [meeting (-> req :body-params)
        new-id (db/add-meeting meeting)]
    (response {:id-created new-id})))

(defn- add-author
  "Adds an author to the database."
  [req]
  (let [author-name (:nickname (:body-params req))]
    (db/add-user-if-not-exists author-name)
    (response {:text "POST successful"})))

(defn- add-agendas
  "Adds a list of agendas to the database."
  [{:keys [body-params]}]
  (let [agendas (:agendas body-params)
        meeting-id (:meeting-id body-params)
        meeting-hash (:meeting-hash body-params)
        suspected-meeting (db/meeting-by-hash meeting-hash)]
    (if (= meeting-id (:db/id suspected-meeting))
      (do (doseq [agenda-point agendas]
            (db/add-agenda-point (:title agenda-point) (:description agenda-point)
                                 meeting-id))
          (response {:text "Agendas, sent over successfully"}))
      (bad-request "Your request was invalid."))))

(defn- meeting-by-hash
  "Returns a meeting, identified by its share-hash."
  [req]
  (let [hash (get-in req [:route-params :hash])]
    (response (db/meeting-by-hash hash))))

(defn- agendas-by-meeting-hash
  "Returns all agendas of a meeting, that matches the share-hash."
  [req]
  (let [meeting-hash (get-in req [:route-params :hash])]
    (response (db/agendas-by-meeting-hash meeting-hash))))

(defn- agenda-by-meeting-hash-and-discussion-id
  "Returns the agenda tied to a certain discussion-id."
  [req]
  (let [discussion-id (Long/valueOf ^String (-> req :route-params :discussion-id))
        meeting-hash (-> req :route-params :meeting-hash)
        agenda-point (db/agenda-by-meeting-hash-and-discussion-id meeting-hash discussion-id)]
    (if agenda-point
      (response agenda-point)
      (not-found (format "No Agenda with discussion-id %s in the DB or the queried discussion does not belong to the meeting %s." discussion-id meeting-hash)))))

(defn- start-discussion
  "Start a new discussion for an agenda point."
  [req]
  (let [discussion-id (Long/valueOf ^String (get-in req [:route-params :discussion-id]))
        username (get-in req [:query-params "username"])
        meeting-hash (get-in req [:query-params "meeting-hash"])
        valid-link? (db/agenda-by-meeting-hash-and-discussion-id meeting-hash discussion-id)]
    (if valid-link?
      (response (processors/with-votes
                  (dialog/start-discussion {:discussion/id discussion-id
                                            :user/nickname username})))
      (bad-request "Your request was malformed"))))

(defn- continue-discussion
  "Dispatches the wire-received events to the dialog.core backend."
  [{:keys [body-params]}]
  (let [[reaction args] (:payload body-params)
        meeting-hash (:meeting-hash body-params)
        discussion-id (:discussion-id body-params)
        valid-link? (db/agenda-by-meeting-hash-and-discussion-id meeting-hash discussion-id)]
    (if valid-link?
      (response (processors/with-votes
                  (dialog/continue-discussion reaction args)))
      (bad-request "Your request was malformed"))))

(defn- toggle-upvote-statement
  "Upvote if no upvote has been made, otherwise remove upvote for statement."
  [{:keys [body-params]}]
  (let [meeting-hash (:meeting-hash body-params)
        statement-id (:statement-id body-params)
        user-nickname (:nickname body-params)]
    (if (db/check-valid-statement-id-and-meeting statement-id meeting-hash)
      (if (db/did-user-upvote-statement statement-id user-nickname)
        (do (db/remove-upvote! statement-id user-nickname)
            (response {:operation :removed}))
        (do (db/upvote-statement! statement-id user-nickname)
            (response {:operation :added})))
      (bad-request "The request was malformed"))))

(defn- toggle-downvote-statement
  "Upvote if no upvote has been made, otherwise remove upvote for statement."
  [{:keys [body-params]}]
  (let [meeting-hash (:meeting-hash body-params)
        statement-id (:statement-id body-params)
        user-nickname (:nickname body-params)]
    (if (db/check-valid-statement-id-and-meeting statement-id meeting-hash)
      (if (db/did-user-downvote-statement statement-id user-nickname)
        (do (db/remove-downvote! statement-id user-nickname)
            (response {:operation :removed}))
        (do (db/downvote-statement! statement-id user-nickname)
            (response {:operation :added})))
      (bad-request "The request was malformed"))))

(defroutes app-routes
  (GET "/meetings" [] all-meetings)
  (GET "/meeting/by-hash/:hash" [] meeting-by-hash)
  (POST "/meeting/add" [] add-meeting)
  (POST "/agendas/add" [] add-agendas)
  (POST "/author/add" [] add-author)
  (GET "/agendas/by-meeting-hash/:hash" [] agendas-by-meeting-hash)
  (GET "/agenda/:meeting-hash/:discussion-id" [] agenda-by-meeting-hash-and-discussion-id)
  (GET "/start-discussion/:discussion-id" [] start-discussion)
  (POST "/continue-discussion" [] continue-discussion)
  (POST "/votes/up/toggle" [] toggle-upvote-statement)
  (POST "/votes/down/toggle" [] toggle-downvote-statement)
  (route/not-found "Error, page not found!"))


(defonce current-server (atom nil))

(defn stop-server []
  (when-not (nil? @current-server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@current-server :timeout 100)
    (reset! current-server nil)))

(defn -main
  "This is our main entry point for the REST API Server."
  [& _args]
  (let [port (:port config/rest-api)]
    ; Run the server with Ring.defaults middleware
    (meetly-core/-main)
    (reset! current-server
            (server/run-server
              (-> #'app-routes
                  (wrap-cors :access-control-allow-origin [#".*"]
                             :access-control-allow-methods [:get :put :post :delete])
                  (wrap-restful-format :formats [:transit-json :transit-msgpack :json-kw :edn :msgpack-kw :yaml-kw :yaml-in-html])
                  (wrap-defaults api-defaults))
              {:port port}))
    (println (format "Running web-server at http://127.0.0.1:%s/" port))))

(comment
  "Start the server from here"
  (-main)
  :end)