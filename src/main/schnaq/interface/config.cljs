(ns schnaq.interface.config
  (:require [goog.string :as gstring]
            [schnaq.config.shared :as shared-config]))

;; Second parameter is a default value
(goog-define build-hash "dev")

(def deleted-statement-text "[deleted]")

(def user-language (atom :en))

(def graph-controversy-upper-bound 65)

(def periodic-update-time
  "Define how many times should the client query the server for live updates.
  Time must be in milliseconds."
  3000)

(def place-holder-header-img "https://s3.schnaq.com/schnaq-header-images/header-placeholder.jpg")

(def in-iframe?
  "Check if schnaq is loaded in an iframe."
  (not= (. js/window -location) (.. js/window -parent -location)))

(def roadmap-url
  "URL to the roadmap."
  "https://trello.com/b/tVatIivm/schnaq-roadmap")

;; -----------------------------------------------------------------------------
;; Keycloak

(goog-define keycloak-realm "development")
(goog-define keycloak-client "development")
(def keycloak-profile-page
  (gstring/format "%s/realms/%s/account/#/personal-info" shared-config/keycloak-host keycloak-realm))

(def keycloak
  "Keycloak configuration, which is sent to the server via keycloak-js."
  {:url (gstring/format "%s/" shared-config/keycloak-host)
   :realm keycloak-realm
   :clientId keycloak-client})

;; -----------------------------------------------------------------------------

(def max-allowed-file-size
  "Maximal allowed image size of a file in megabytes."
  5)

(def marketing-num-schnaqs 1000)
(def marketing-num-statements 4000)

;; -----------------------------------------------------------------------------
;; Pricing

(def price-event-tier-euro 49)
(def max-concurrent-users-free-tier 100)
(def max-concurrent-users-pro-tier 250)

(def breakpoints
  "Mirrors the breakpoints used in the bootstrap css."
  {:xs 0
   :sm 576
   :md 768
   :lg 992
   :xl 1200
   :xxl 1439
   :xxxl 1600
   :hd 1900
   :qhd 2560})
