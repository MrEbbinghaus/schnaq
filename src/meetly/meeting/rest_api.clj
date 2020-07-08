(ns meetly.meeting.rest-api
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [org.httpkit.server :as server]
            [ring.middleware.defaults :refer
             [wrap-defaults site-defaults api-defaults]]
            [meetly.config :as config]
            [clojure.pprint :as pp]))


(defn simple-body-page [_req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

; request-example
(defn request-example [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (->>
           (pp/pprint req)
           (str "Request Object: " req))})

(defn hello-name [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (->
           (pp/pprint req)
           (str "Hello " (:name (:params req))))})

(defroutes app-routes
           (GET "/" [] hello-name)
           (GET "/request" [] request-example)
           (route/not-found "Error, page not found!"))


(defn api-main
  "This is our main entry point for the REST API"
  []
  (let [port (:port config/rest-api)]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

(comment
  (api-main)
  :end)