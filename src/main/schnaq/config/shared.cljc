(ns schnaq.config.shared)

#?(:clj  (def api-port
           (Integer/parseInt (or (System/getenv "API_PORT") "3000")))
   :cljs (goog-define api-port "3000"))

#?(:clj  (def api-url
           (or (System/getenv "API_URL") (str "http://localhost:" api-port)))
   :cljs (goog-define api-url "http://localhost:3000"))

#?(:clj  (def keycloak-host
           (or (System/getenv "KEYCLOAK_SERVER") "https://auth.schnaq.com"))
   :cljs (goog-define keycloak-host "https://auth.schnaq.com"))

#?(:clj  (def s3-host
           (or (System/getenv "S3_HOST") "https://s3.disqtec.com"))
   :cljs (goog-define s3-host "https://s3.disqtec.com"))

(defn s3-buckets
  "Returns bucket names"
  [bucket-name]
  (get
    {:schnaq/header-images "schnaq-header-images"
     :user/profile-pictures "schnaq-profile-pictures"
     :feedbacks/screenshots "schnaq-feedback-screenshots"}
    bucket-name))

(def schnaq-environment
  #?(:clj  (or (System/getenv "ENVIRONMENT") "development")
     :cljs (goog-define environment "development")))

(def production?
  "Checks the configuration for the current environment."
  (= "production" schnaq-environment))

;; -----------------------------------------------------------------------------
;; Profile Image Upload

(def allowed-mime-types
  "Define a list of allowed mime-types."
  #{"image/jpeg" "image/png"})

(def beta-tester-groups
  #{"schnaqqifantenparty" "beta-tester"})
