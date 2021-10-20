(ns schnaq.interface.config
  (:require [goog.string :as gstring]
            [schnaq.config.shared :as shared-config]))

;; Second parameter is a default value
(goog-define build-hash "dev")

(def deleted-statement-text "[deleted]")

(def user-language (atom :de))

(def graph-controversy-upper-bound 65)

(def default-anonymous-display-name "Anonymous")

(def periodic-update-time
  "Define how many times should the client query the server for live updates.
  Time must be in milliseconds."
  3000)

(def place-holder-header-img "https://s3.disqtec.com/schnaq-header-images/header-placeholder.jpg")


;; -----------------------------------------------------------------------------
;; Pricing

(def pricing-business-tier
  "Price in euros, exclusively VAT."
  8)

;; -----------------------------------------------------------------------------
;; Keycloak

(goog-define keycloak-realm "development")
(goog-define keycloak-client "development")
(goog-define keycloak-public-client true)
(def keycloak-profile-page
  (gstring/format "%s/auth/realms/%s/account/#/personal-info" shared-config/keycloak-host keycloak-realm))

(def keycloak
  "Keycloak configuration, which is sent to the server via keycloak-js."
  {:url (gstring/format "%s/auth/" shared-config/keycloak-host)
   :realm keycloak-realm
   :clientId keycloak-client})


;; -----------------------------------------------------------------------------
;; Time settings

(def time-settings
  {:pattern "HH:mm dd.MM.yyy"
   :timezone "Europe/Berlin"})


;; -----------------------------------------------------------------------------

(def max-allowed-profile-picture-size
  "Maximal allowed image size of profile picture in bytes."
  5000000)

(def marketing-num-schnaqs 550)
(def marketing-num-statements 3600)


;; -----------------------------------------------------------------------------
;; Example schnaqs and statements
;;
;; These samples default to the staging environment so that it works on staging
;; and on production. Change these symbols to your dev environment accordingly.
;; Replaced by the CI when building the application for production.

(goog-define example-share-hash "103f2147-d4dd-491b-8fd0-64aefd950413")
(goog-define example-api-url "https://api.staging.schnaq.com")
(goog-define example-statement-1 17592186048171)
(goog-define example-statement-2 17592186048169)
(goog-define example-statement-3 17592186048173)
