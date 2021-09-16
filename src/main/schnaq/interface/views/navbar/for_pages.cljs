(ns schnaq.interface.views.navbar.for-pages
  (:require [clojure.string :as str]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as reitfe]
            [schnaq.interface.components.icons :refer [fa]]
            [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.language :as language]
            [schnaq.interface.views.navbar.user-management :as um]))

;; -----------------------------------------------------------------------------
;; Navbar Elements

(defn- blog-link []
  [:ul.navbar-nav
   [:li.nav-item
    [:a.nav-link {:href "https://schnaq.com/blog/" :role "button"}
     (labels :nav/blog)]]])


;; -----------------------------------------------------------------------------

(defn navbar-title [title]
  [:div.d-flex.align-items-center.flex-row.schnaq-navbar-space.schnaq-navbar.mb-0.mb-md-4
   ;; schnaq logo
   [:a.schnaq-logo-container.d-flex.h-100 {:href (reitfe/href :routes.schnaqs/personal)}
    [:img.d-inline-block.align-middle.mr-2
     {:src (img-path :logo-white) :alt "schnaq logo"
      :style {:max-height "100%" :max-width "100%" :object-fit "contain"}}]]
   [:div.mx-md-5
    [:div.d-flex.flex-row.d-md-none.align-items-center
     [um/user-handling-menu "btn-link"]]
    [:h1.h3.font-weight-bold.my-auto.d-none.d-md-block.text-dark-highlight title]]])

(defn language-dropdown
  "Dropdown for bootstrap navbar to display the allowed languages."
  []
  (let [current-language @(rf/subscribe [:current-language])]
    [:<>
     [:a#schnaq-language-dropdown.nav-link.dropdown-toggle
      {:href "#" :role "button" :data-toggle "dropdown"
       :aria-haspopup "true" :aria-expanded "false"}
      [:i {:class (str "fas " (fa :language))}] " " current-language]
     [:div.dropdown-menu {:aria-labelledby "schnaq-language-dropdown"}
      [:button.dropdown-item
       {:on-click #(language/set-language :de)} "Deutsch"]
      [:button.dropdown-item
       {:on-click #(language/set-language :en)} "English"]
      [:button.dropdown-item
       {:on-click #(language/set-language :pl)} "Polski"]]]))

(defn navbar-user []
  [:div.d-flex.flex-row.schnaq-navbar-space.mb-0.mb-md-4.ml-auto.schnaq-navbar.align-items-center.flex-wrap.px-md-3
   [:div.mx-1
    [:a.nav-link {:role "button" :href (reitfe/href :routes/privacy)}
     (labels :router/privacy)]]
   [:div.mx-1
    [blog-link]]
   [:div.mx-1
    [:div.dropdown.ml-auto
     [language-dropdown]]]
   [:div.mx-1
    [um/admin-dropdown "btn-outline-secondary"]]
   [:div.mx-1.d-none.d-md-block
    [:div.d-flex.flex-row.align-items-center
     [um/user-handling-menu "btn-link"]]]])

(defn navbar
  "Overview header for a discussion."
  [title]
  [:div.d-flex.flex-row.flex-wrap.p-md-3
   [navbar-title title]
   [navbar-user]])

(defn navbar-transparent
  "Navbar definition for the default pages."
  [wrapper-classes]
  [:nav.navbar.navbar-expand-lg.py-3.navbar-transparent.bg-transparent.mb-4
   ;; logo
   [:section
    {:class (if (str/blank? wrapper-classes) "container" wrapper-classes)}
    [:a.navbar-brand {:href (reitfe/href :routes/startpage)}
     [:img.d-inline-block.align-middle.mr-2
      {:src (img-path :logo-white) :width "150" :alt "schnaq logo"}]]
    ;; hamburger
    [:button.navbar-toggler
     {:type "button" :data-toggle "collapse" :data-target "#schnaq-navbar"
      :aria-controls "schnaq-navbar" :aria-expanded "false" :aria-label "Toggle navigation"
      :data-html2canvas-ignore true}
     [:span.navbar-toggler-icon]]
    ;; menu items
    [:div#schnaq-navbar.collapse.navbar-collapse
     [:ul.navbar-nav.ml-auto
      [:li.nav-item
       [:a.nav-link
        {:role "button"
         :href (reitfe/href :routes.schnaqs/personal)}
        (labels :nav/schnaqs)]]
      [blog-link]
      [:li.nav-item
       [:a.nav-link {:role "button" :href (reitfe/href :routes/privacy)}
        (labels :router/privacy)]]
      [:li.nav-item.dropdown
       [language-dropdown]]]
     [um/admin-dropdown "btn-outline-secondary"]
     [um/user-handling-menu "btn-outline-light btn-transparent"]]]])
