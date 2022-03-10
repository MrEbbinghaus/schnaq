(ns schnaq.interface.core
  (:require ["bootstrap"]
            ["popper.js"]
            [day8.re-frame.http-fx]
            [goog.dom :as gdom]
            [goog.string :as gstring]
            [goog.string.format] ;; required for goog.string. We need to require it once in our project.
            [re-frame.core :as rf]
            [reagent.dom]
            [schnaq.config.shared :as shared-config]
            [schnaq.database.specs]
            [schnaq.interface.analytics.core]
            [schnaq.interface.auth]
            [schnaq.interface.components.buttons]
            [schnaq.interface.components.colors]
            [schnaq.interface.components.common]
            [schnaq.interface.components.icons]
            [schnaq.interface.components.images]
            [schnaq.interface.components.navbar]
            [schnaq.interface.components.preview]
            [schnaq.interface.components.schnaq]
            [schnaq.interface.components.videos]
            [schnaq.interface.components.wavy]
            [schnaq.interface.config :as config]
            [schnaq.interface.events]
            [schnaq.interface.integrations.wetog.events]
            [schnaq.interface.integrations.wetog.routes]
            [schnaq.interface.navigation]
            [schnaq.interface.notification.events]
            [schnaq.interface.pages.about-us]
            [schnaq.interface.pages.press]
            [schnaq.interface.pages.privacy]
            [schnaq.interface.routes :as routes]
            [schnaq.interface.scheduler]
            [schnaq.interface.translations]
            [schnaq.interface.translations.english]
            [schnaq.interface.translations.german]
            [schnaq.interface.user]
            [schnaq.interface.utils.file-reader]
            [schnaq.interface.utils.language :as language]
            [schnaq.interface.utils.localstorage]
            [schnaq.interface.utils.time]
            [schnaq.interface.utils.updates :as updates]
            [schnaq.interface.views :as views]
            [schnaq.interface.views.admin.control-center]
            [schnaq.interface.views.common]
            [schnaq.interface.views.discussion.admin-center]
            [schnaq.interface.views.discussion.card-elements]
            [schnaq.interface.views.discussion.card-view]
            [schnaq.interface.views.discussion.conclusion-card]
            [schnaq.interface.views.discussion.filters]
            [schnaq.interface.views.discussion.history]
            [schnaq.interface.views.discussion.labels]
            [schnaq.interface.views.discussion.logic]
            [schnaq.interface.views.discussion.truncated-content]
            [schnaq.interface.views.errors]
            [schnaq.interface.views.feed.filters]
            [schnaq.interface.views.feed.overview]
            [schnaq.interface.views.feedback.admin]
            [schnaq.interface.views.feedback.collect]
            [schnaq.interface.views.graph.settings]
            [schnaq.interface.views.graph.view]
            [schnaq.interface.views.hub.common]
            [schnaq.interface.views.hub.overview]
            [schnaq.interface.views.hub.settings]
            [schnaq.interface.views.loading]
            [schnaq.interface.views.modal]
            [schnaq.interface.views.navbar.for-discussions]
            [schnaq.interface.views.navbar.for-pages]
            [schnaq.interface.views.navbar.user-management]
            [schnaq.interface.views.notifications]
            [schnaq.interface.views.pages]
            [schnaq.interface.views.qa.inputs]
            [schnaq.interface.views.qa.search]
            [schnaq.interface.views.schnaq.activation]
            [schnaq.interface.views.schnaq.admin]
            [schnaq.interface.views.schnaq.create]
            [schnaq.interface.views.schnaq.poll]
            [schnaq.interface.views.schnaq.reactions]
            [schnaq.interface.views.schnaq.summary]
            [schnaq.interface.views.schnaq.visited]
            [schnaq.interface.views.startpage.core]
            [schnaq.interface.views.user]
            [schnaq.interface.views.user.edit-account]
            [schnaq.interface.views.user.settings]
            [schnaq.interface.views.user.subscription]
            [schnaq.interface.views.user.themes]
            [schnaq.interface.views.user.welcome]
            [taoensso.timbre :as log]))
;; NOTE: If you use subs and events in another module, you need to require it
;; Requiring other views is needed to have dynamic updates from shadow and re-frame
;; somewhere where it will be loaded like this core module.

;; -- Entry Point -------------------------------------------------------------

(defn render
  []
  (reagent.dom/render [views/root]
                      (gdom/getElement (if shared-config/embedded? "schnaq-integration" "app"))))

(defn ^:dev/after-load clear-cache-and-render!
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the re-frame subscription cache.
  ;; This function is called implicitly by its annotation.
  (rf/clear-subscription-cache!)
  (render))

(defn- say-hello
  "Add some logging to validate and verify the correct environment."
  []
  (log/info "Welcome to schnaq 🎉")
  (log/info (gstring/format "Build Hash: %s" config/build-hash))
  (log/info (gstring/format "API: %s" shared-config/api-url))
  (log/info (gstring/format "Environment: %s" shared-config/environment))
  (log/info (gstring/format "Example Data: %s from %s, statements [%d]" config/example-share-hash config/example-api-url config/example-statement))
  (log/info (gstring/format "[Keycloak] Realm: %s, Client: %s" config/keycloak-realm config/keycloak-client)))

(defn init
  "Entrypoint into the application."
  []
  (let [init-routine (if shared-config/embedded? :initialize/wetog-integration :initialize/schnaq)]
    (routes/init-routes!)
    (rf/dispatch-sync [init-routine]) ;; put a value into application state
    (language/init-language)
    (render) ;; mount the application's ui into '<div id="app" />'
    (say-hello)
    (updates/init-periodic-updates)))
