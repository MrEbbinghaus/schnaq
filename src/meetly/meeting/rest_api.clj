(ns meetly.meeting.rest-api
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [org.httpkit.server :as server]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response not-found]]
            [meetly.config :as config]
            [meetly.meeting.database :as db]
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
    (db/add-author-if-not-exists author-name)
    (response {:text "POST successful"})))

(defn- add-agendas
  "Adds a list of agendas to the database."
  [req]
  (let [agendas (-> req :body-params :agendas)
        meeting-id (-> req :body-params :meeting-id)]
    (doseq [agenda-point agendas]
      (db/add-agenda-point (:title agenda-point) (:description agenda-point)
                           meeting-id)))
  (response {:text "Agendas, sent over successfully"}))

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

(defn- agenda-by-discussion-id
  "Returns the agenda tied to a certain discussion-id."
  [req]
  (let [discussion-id (Long/valueOf ^String (-> req :route-params :discussion-id))
        agenda-point (db/agenda-by-discussion-id discussion-id)]
    (if agenda-point
      (response agenda-point)
      (not-found (str "No Agenda with discussion-id " discussion-id " in the DB.")))))

(defn- start-discussion
  "Start a new discussion for an agenda point."
  [req]
  (let [discussion-id (Long/valueOf ^String (-> req :route-params :discussion-id))
        username (get-in req [:query-params "username"])]
    (response {:discussion-reactions
               (dialog/start-discussion {:discussion/id discussion-id
                                         :user/nickname username})})))

(defn- continue-discussion
  "Dispatches the wire-received events to the dialog.core backend."
  [req]
  (let [[reaction args] (:body-params req)]
    (response {:discussion-reactions (dialog/continue-discussion reaction args)})))

(defroutes app-routes
           (GET "/meetings" [] all-meetings)                ;;fertig!
           (GET "/meeting/by-hash/:hash" [] meeting-by-hash) ;;fertig
           (POST "/meeting/add" [] add-meeting)             ;; fertig!
           (POST "/agendas/add" [] add-agendas)             ;; fertig!
           (POST "/author/add" [] add-author)               ;; fertig!
           (GET "/agendas/by-meeting-hash/:hash" [] agendas-by-meeting-hash) ;fertig!
           (GET "/agenda/:discussion-id" [] agenda-by-discussion-id) ;fertig!
           (GET "/start-discussion/:discussion-id" [] start-discussion)
           (POST "/continue-discussion" [] continue-discussion)
           (route/not-found "Error, page not found!"))


(defn -main
  "This is our main entry point for the REST API Server"
  []
  (let [port (:port config/rest-api)]
    ; Run the server with Ring.defaults middleware
    (meetly-core/-main)
    (server/run-server
      (-> #'app-routes
          (wrap-cors :access-control-allow-origin [#".*"]
                     :access-control-allow-methods [:get :put :post :delete])
          (wrap-restful-format :formats [:transit-json :transit-msgpack :json-kw :edn :msgpack-kw :yaml-kw :yaml-in-html])
          (wrap-defaults api-defaults))
      {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running web-server at http:/127.0.0.1:" port "/"))))