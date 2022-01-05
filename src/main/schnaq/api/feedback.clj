(ns schnaq.api.feedback
  "Create API endpoints for feedback-functions."
  (:require [clojure.java.io :as io]
            [clojure.spec.alpha :as s]
            [clojure.string :as string]
            [com.fulcrologic.guardrails.core :refer [>defn-]]
            [ring.util.http-response :refer [ok created]]
            [schnaq.api.dto-specs :as dto]
            [schnaq.api.toolbelt :as at]
            [schnaq.database.main :as db]
            [schnaq.database.specs :as specs]
            [schnaq.mail.emails :as emails]
            [schnaq.s3 :as s3]
            [taoensso.timbre :as log])
  (:import (java.util Base64)))

(>defn- upload-screenshot!
  "Stores a screenshot from a feedback in s3."
  [screenshot file-name]
  [:feedback/screenshot (s/or :number number? :string string?) :ret string?]
  (let [[_header image] (string/split screenshot #",")
        #^bytes decodedBytes (.decode (Base64/getDecoder) ^String image)]
    (s3/upload-stream
     :feedbacks/screenshots
     (io/input-stream decodedBytes)
     (format "%s.png" file-name)
     {:content-length (count decodedBytes)})))

(defn- add-feedback
  "Add new feedback from schnaq's frontend. If a screenshot is provided, it will
  be uploaded in our s3 bucket. Screenshot must be a base64 encoded string. The
  screenshot-field is optional."
  [{:keys [parameters]}]
  (let [feedback (get-in parameters [:body :feedback])
        feedback-id (db/add-feedback! feedback)
        screenshot (get-in parameters [:body :screenshot])]
    (when screenshot
      (upload-screenshot! screenshot feedback-id))
    (emails/send-mail
     "[FEEDBACK] Es wurde ein neues Feedback hinterlegt 🐳"
     "[FEEDBACK]"
     "Es wurde ein neues Feedback hinterlegt 🐳"
     (format "Feedback kann hier angeschaut werden: https://schnaq.com/admin/feedbacks")
     "info@schnaq.com")
    (log/info "Feedback created")
    (created "" {:feedback feedback})))

(defn- all-feedbacks
  "Returns all feedbacks from the db."
  [_]
  (ok {:feedbacks (db/all-feedbacks)}))

;; -----------------------------------------------------------------------------

(def feedback-routes
  [["" {:swagger {:tags ["feedbacks"]}}
    ["/feedback/add" {:post add-feedback
                      :description (at/get-doc #'add-feedback)
                      :name :api.feedback/add
                      :parameters {:body (s/keys :req-un [::dto/feedback] :opt-un [:feedback/screenshot])}
                      :responses {201 {:body {:feedback ::dto/feedback}}}}]
    ["/admin" {:swagger {:tags ["admin"]}
               :responses {401 at/response-error-body}
               :middleware [:user/authenticated? :user/admin?]}
     ["/feedbacks" {:get all-feedbacks
                    :name :api.feedback/admin
                    :description (at/get-doc #'all-feedbacks)
                    :responses {200 {:body {:feedbacks (s/coll-of ::specs/feedback)}}}}]]]])
