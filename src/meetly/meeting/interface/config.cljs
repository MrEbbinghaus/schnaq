(ns meetly.meeting.interface.config)

;; Third parameter is a default value
(goog-define rest-api-url "http://localhost:3000")
(goog-define environment "development")

(def config
  {:rest-backend rest-api-url
   :environment environment})