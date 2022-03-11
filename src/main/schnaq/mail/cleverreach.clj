(ns schnaq.mail.cleverreach
  (:require [clj-http.client :as client]
            [clojure.pprint :refer [pprint]]
            [clojure.spec.alpha :as s]
            [com.fulcrologic.guardrails.core :refer [>defn >defn- => ?]]
            [muuntaja.core :as m]
            [schnaq.config.cleverreach :as cconfig]
            [schnaq.database.specs :as specs]
            [schnaq.mail.emails :as emails]
            [taoensso.timbre :as log]))

(def ^:private token-url "https://rest.cleverreach.com/oauth/token.php")

(>defn get-access-token
  "Query an access token. Necessary to browse CleverReach's API."
  [client-id client-secret]
  [string? string? => (? map?)]
  (when cconfig/enabled?
    (try
      (let [response
            (m/decode-response-body
             (client/post
              token-url
              {:basic-auth [client-id client-secret]
               :body (m/encode "application/json" {"grant_type" "client_credentials"})
               :content-type :json
               :accept :json}))]
        (log/info "Successfully retrieved access token for CleverReach.")
        response)
      (catch Exception e
        (let [error (ex-data e)]
          (log/error "Could not retrieve access token:" error)
          (emails/send-mail
           "[💥 CleverReach] Konnte keinen access token abrufen"
           (with-out-str (pprint error))
           "christian@schnaq.com")
          error)))))

(def ^:private access-token
  (:access_token (get-access-token cconfig/client-id cconfig/client-secret)))

;; -----------------------------------------------------------------------------

(>defn- wrap-catch-exception
  "Do API call, catch exception and print result or error."
  [email success-log error-log fn]
  [::specs/email string? string? fn? => (? map?)]
  (if cconfig/enabled?
    (try
      (let [response (fn)]
        (log/debug (format success-log email cconfig/receiver-group))
        response)
      (catch Exception e
        (let [error (ex-data e)]
          (log/error (format "%s mail: %s, body: %s"
                             error-log email (m/decode-response-body error)))
          error)))
    (log/debug "Cleverreach is not enabled.")))

;; -----------------------------------------------------------------------------

(>defn add-user-to-customer-group!
  "Add an email address to a group in Cleverreach, i.e. a list of receivers."
  [{:keys [email sub given_name family_name locale]}]
  [::specs/identity => (? map?)]
  (wrap-catch-exception
   email "Added mail %s to group" "User could not be added to cleverreach."
   #(client/post
     (format "https://rest.cleverreach.com/v3/groups.json/%s/receivers?token=%s" cconfig/receiver-group access-token)
     {:body
      (m/encode "application/json"
                {:email email
                 :tags ["customer-free"]
                 :registered (quot (System/currentTimeMillis) 1000)
                 :activated 0
                 :source "schnaq Backend"
                 :global_attributes {:firstname given_name
                                     :lastname family_name
                                     :locale locale
                                     :keycloak_id sub}})
      :content-type :json
      :accept :json})))

(>defn add-tag!
  "Adds a tag to the user's entry in cleverreach."
  [email tags]
  [::specs/email (s/and vector? (s/coll-of string?)) => (? map?)]
  (wrap-catch-exception
   email "Added tag to mail %s." "Could not add pro tag to mail."
   #(client/post
     (format "https://rest.cleverreach.com/v3/receivers.json/%s/tags?token=%s" email access-token)
     {:body (m/encode "application/json" {:tags tags
                                          :group_id cconfig/receiver-group})
      :content-type :json
      :accept :json})))

(>defn add-pro-tag!
  "Send pro-information of user to cleverreach. Adds a tag to the user's entry."
  [email]
  [::specs/email => (? map?)]
  (add-tag! email ["customer-pro"]))

(>defn add-free-tag!
  "Send pro-information of user to cleverreach. Adds a tag to the user's entry."
  [email]
  [::specs/email => (? map?)]
  (add-tag! email ["customer-free"]))

(>defn remove-tag!
  "Remove one tag information from user."
  [email tag]
  [::specs/email string? => (? map?)]
  (wrap-catch-exception
   email "Removed tag from mail %s." "Could not remove pro tag from mail."
   #(client/delete
     ;; The tags, which should be removed, must be a single tag or a comma separated list of tags.
     (format "https://rest.cleverreach.com/v3/receivers.json/%s/tags/%s?token=%s" email tag access-token)
     {:content-type :json
      :accept :json})))

(>defn remove-pro-tag!
  "Remove pro tag information from user."
  [email]
  [::specs/email => (? map?)]
  (remove-tag! email "customer-pro"))

(>defn remove-free-tag!
  "Remove free tag information from user."
  [email]
  [::specs/email => (? map?)]
  (remove-tag! email "customer-free"))
