(ns schnaq.config.shared
  (:require [clojure.set :as cset]))

#?(:clj (def api-port
          (Integer/parseInt (or (System/getenv "API_PORT") "3000")))
   :cljs (goog-define api-port "3000"))

#?(:clj (def api-url
          (or (System/getenv "API_URL") (str "http://localhost:" api-port)))
   :cljs (goog-define api-url "http://localhost:3000"))

#?(:clj (def keycloak-host
          (or (System/getenv "KEYCLOAK_SERVER") "https://auth.schnaq.com"))
   :cljs (goog-define keycloak-host "https://auth.schnaq.com"))

#?(:clj (def s3-host
          (or (System/getenv "S3_HOST") "https://s3.schnaq.com"))
   :cljs (goog-define s3-host "https://s3.schnaq.com"))

(def default-anonymous-display-name "Anonymous")

(defn s3-buckets
  "Returns bucket names"
  [bucket-name]
  (get
   {:hub/logo "schnaq-hub-logo"
    :schnaq/header-images "schnaq-header-images"
    :user/media "user-media"
    :schnaq/media "schnaq-media"
    :feedbacks/screenshots "schnaq-feedback-screenshots"}
   bucket-name))

#?(:clj (def environment (or (System/getenv "ENVIRONMENT") "development"))
   :cljs (goog-define environment "development"))

(def production?
  "Checks the configuration for the current environment."
  (= "production" environment))

(def allowed-mime-types-images
  "Define a list of allowed mime-types."
  #{"image/jpeg" "image/png" "image/webp"})

(def admin-roles
  #{"admin" :role/admin})

(def analytics-roles
  (cset/union admin-roles #{"analytics-admin" :role/analytics}))

(def beta-tester-roles
  "Admins and testers have beta-access."
  (cset/union admin-roles analytics-roles #{"beta-tester" :role/tester}))

(def pro-roles
  "All beta testers, admins, pro and enterprise users have pro access."
  (cset/union beta-tester-roles #{:role/pro :role/enterprise}))

(def allowed-labels
  "A set of allowed labels for statements. They correspond to fa symbols"
  #{":check" ":question" ":times" ":ghost" ":calendar-alt" ":arrow-right" ":comment"})

(def allowed-share-hash-in-development
  "Allow a share-hash in development without verification."
  "CAFECAFE-CAFE-CAFE-CAFE-CAFECAFECAFE")

;; -----------------------------------------------------------------------------
;; Access Codes

(def access-code-length
  "Defines how long the access code should be, e.g. eight numbers."
  8)

(def access-code-default-expiration
  "Default duration in days for the access code."
  7)

;; -----------------------------------------------------------------------------
;; Time settings

(def time-settings
  {:pattern "HH:mm dd.MM.yyy"
   :timezone "Europe/Berlin"})

;; -----------------------------------------------------------------------------
;; Stripe

(def currencies
  "Define the accepted currencies."
  #{:eur :usd})

;; -----------------------------------------------------------------------------

(def feature-limits
  "Describe default tier limits. If no value is provided, the user has unlimited
  access to this feature."
  {:free {:wordcloud? false
          :rankings? false
          :embeddings? false
          :theming? false
          :polls 0
          :concurrent-users 100
          :total-schnaqs 10
          :posts-per-schnaq 30}
   :pro {:concurrent-users 250}})

;; -----------------------------------------------------------------------------
;; Feature toggles

(def check-limits? (not production?))
