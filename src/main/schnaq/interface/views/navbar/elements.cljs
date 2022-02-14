(ns schnaq.interface.views.navbar.elements
  (:require [com.fulcrologic.guardrails.core :refer [>defn- ?]]
            [goog.string :as gstring]
            [re-frame.core :as rf]
            [schnaq.interface.components.colors :refer [colors]]
            [schnaq.interface.components.icons :refer [icon]]
            [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.components.motion :as motion]
            [schnaq.interface.components.navbar :as navbar-components]
            [schnaq.interface.navigation :as navigation]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.language :as language]
            [schnaq.interface.utils.toolbelt :as toolbelt]
            [schnaq.interface.utils.tooltip :as tooltip]
            [schnaq.interface.views.graph.settings :as graph-settings]
            [schnaq.interface.views.navbar.user-management :as um]
            [schnaq.interface.views.schnaq.admin :as admin]))

(defn- clickable-title
  ([]
   [clickable-title "text-primary" "text-dark"])
  ([label-class title-class]
   (let [{:discussion/keys [title share-hash]} @(rf/subscribe [:schnaq/selected])]
     [:<>
      [:small {:class label-class} (labels :discussion.navbar/title)]
      [:div.clickable-no-hover
       [:a.link-unstyled
        {:href (navigation/href :routes.schnaq/start {:share-hash share-hash})}
        [:h1.h5.d-none.d-md-block {:class title-class} (toolbelt/truncate-to-n-chars title 25)]
        [:div.d-md-none {:class title-class} (toolbelt/truncate-to-n-chars title 22)]]]])))

(defn- schnaq-logo []
  [:<>
   [:img.schnaq-brand-logo.align-middle.me-2.d-md-none.d-none.d-xxl-block
    {:src (img-path :logo-white) :alt "schnaq logo"
     :style {:max-height "100%" :max-width "100%" :object-fit "contain"}}]
   [:img.schnaq-brand-logo.align-middle.me-2.d-xxl-none
    {:src (img-path :schnaqqifant/white) :alt "schnaq logo"
     :style {:max-height "100%" :max-width "100%" :object-fit "contain"}}]])

(defn navbar-title
  "Brand logo and title with dynamic resizing."
  [title additional-content]
  [:div.d-flex.align-items-center.flex-row.schnaq-navbar-title.me-2.bg-white
   [:a.schnaq-logo-container.d-flex.h-100 {:href (navigation/href :routes/startpage)}
    [schnaq-logo]]
   [:div.mx-0.mx-md-4
    title]
   additional-content])

(defn navbar-qanda-title []
  [:div.d-flex.align-items-center.flex-row.schnaq-navbar-title.me-2
   [:a.p-3.d-flex.h-100 {:href (toolbelt/current-overview-link)}
    [schnaq-logo]]
   [:div.mx-1.mx-md-5.px-md-5.pt-2.flex-column
    [clickable-title "text-white" "text-white"]]])

(defn additional-label-counter [label count]
  [:div.mx-4.ms-auto.d-none.d-xxl-block
   [:small.text-primary label]
   [:h5.text-center count]])

;; -----------------------------------------------------------------------------

(>defn- discussion-button-builder
  "Build buttons in the discussion navigation."
  [label icon href]
  [keyword? keyword? (? string?) :ret vector?]
  [:a.dropdown-item {:href href}
   [:div.text-center
    [:img.navbar-view-toggle
     {:src (img-path icon)
      :alt "graph icon"}]
    [:p.small.m-0.text-nowrap (labels label)]]])

(defn graph-button
  "Rounded square button to navigate to the graph view"
  []
  (let [share-hash @(rf/subscribe [:schnaq/share-hash])]
    [discussion-button-builder
     :graph.button/text :icon-graph-dark
     (navigation/href :routes/graph-view {:share-hash share-hash})]))

(defn summary-button
  "Button to navigate to the summary view."
  []
  (let [share-hash @(rf/subscribe [:schnaq/share-hash])]
    [discussion-button-builder
     :summary.link.button/text :icon-summary-dark
     (navigation/href :routes.schnaq/dashboard {:share-hash share-hash})]))

(defn- standard-view-button
  "Button to navigate to the standard overview."
  []
  (let [share-hash @(rf/subscribe [:schnaq/share-hash])]
    [discussion-button-builder
     :discussion.button/text :icon-cards-dark
     (navigation/href :routes.schnaq/start {:share-hash share-hash})]))

(defn- qanda-view-button
  "Button to navigate to the Q&A view."
  []
  (let [share-hash @(rf/subscribe [:schnaq/share-hash])]
    [discussion-button-builder
     :qanda.button/text :icon-qanda-dark
     (navigation/href :routes.schnaq/qanda {:share-hash share-hash})]))

(defn dropdown-views
  "Displays a Dropdown menu button for the available views"
  ([]
   [dropdown-views :icon-views-dark ""])
  ([icon-id toggle-class]
   (let [dropdown-id "schnaq-views-dropdown"
         current-route @(rf/subscribe [:navigation/current-route-name])]
     [tooltip/text
      (labels :discussion.navbar/views)
      [:div.dropdown
       [navbar-components/separated-button
        [:div.dropdown-toggle
         {:class toggle-class}
         [:img.navbar-view-toggle.d-block
          {:src (img-path icon-id) :alt "Icon representing different views"}]
         [:span.small
          (case current-route
            :routes.schnaq/start (labels :discussion.button/text)
            :routes.schnaq.select/statement (labels :discussion.button/text)
            :routes/graph-view (labels :graph.button/text)
            :routes.schnaq/dashboard (labels :summary.link.button/text)
            :routes.schnaq/qanda (labels :qanda.button/text)
            (labels :discussion.navbar/views))]]
        {:id dropdown-id :data-bs-toggle "dropdown"
         :aria-haspopup "true" :aria-expanded "false"}
        [:div.dropdown-menu.dropdown-menu-right {:aria-labelledby dropdown-id}
         [standard-view-button]
         [graph-button]
         [summary-button]
         [qanda-view-button]]]]])))

;; -----------------------------------------------------------------------------

(defn navbar-settings
  "Either display schnaq or graph settings button"
  []
  (let [current-route @(rf/subscribe [:navigation/current-route-name])
        graph? (= current-route :routes/graph-view)
        admin? @(rf/subscribe [:schnaq.current/admin-access])]
    (if graph?
      [graph-settings/open-settings]
      (when admin?
        [admin/admin-center]))))

(defn navbar-download
  "Download button for either text or graph"
  []
  (let [{:discussion/keys [title share-hash]} @(rf/subscribe [:schnaq/selected])
        current-route @(rf/subscribe [:navigation/current-route-name])
        graph? (= current-route :routes/graph-view)]
    (if graph?
      [admin/graph-download-as-png (gstring/format "#%s" graph-settings/graph-id)]
      [admin/txt-export share-hash title])))

(defn statement-counter
  "A counter showing all statements and pulsing live."
  []
  (let [number-of-questions @(rf/subscribe [:schnaq.selected/statement-number])
        share-hash @(rf/subscribe [:schnaq/share-hash])]
    [:div
     [navbar-components/separated-button
      [:div.d-flex.text-white
       [motion/pulse-once [icon :comment/alt]
        [:schnaq.qa.new-question/pulse?]
        [:schnaq.qa.new-question/pulse false]
        (:white colors)
        (:secondary colors)]

       [:div.ms-2 number-of-questions]]
      {:on-click #(rf/dispatch [:navigation/navigate :routes.schnaq/start {:share-hash share-hash}])}]]))

(rf/reg-event-db
 :schnaq.qa.new-question/pulse
 (fn [db [_ pulse]]
   (assoc-in db [:schnaq :qa :new-question :pulse] pulse)))

(rf/reg-sub
 :schnaq.qa.new-question/pulse?
 (fn [db _]
   (get-in db [:schnaq :qa :new-question :pulse] false)))

(defn language-toggle
  "Language Toggle dropdown button"
  []
  [:div.dropdown
   [navbar-components/language-toggle-with-tooltip false {:class "text-dark btn"}]])

(defn title-and-infos
  "Display the schnaq title and info"
  []
  (let [discussion @(rf/subscribe [:schnaq/selected])
        meta-info (:meta-info discussion)
        statement-count (:all-statements meta-info)
        user-count (count (:authors meta-info))]
    [navbar-title
     [clickable-title]
     [:<>
      [additional-label-counter (labels :discussion.navbar/posts) statement-count]
      [additional-label-counter (labels :discussion.navbar/members) user-count]]]))

(defn user-button
  "Display the user settings button"
  ([]
   [user-button "btn-link text-dark"])
  ([classes]
   [:div.d-flex.align-items-center
    [um/user-dropdown-button classes]]))

(defn collapsed-view-language-with-label-dropdown
  "Display a language dropdown menu with a description label"
  []
  (let [dropdown-id "schnaq-collapsed-language-dropdown"]
    [:<>
     [:nav-link.dropdown-toggle
      {:id dropdown-id
       :href "#" :role "button" :data-bs-toggle "dropdown"
       :aria-haspopup "true" :aria-expanded "false"}
      (labels :nav.buttons/language-toggle) " "
      [icon :language]]
     [:div.dropdown-menu {:aria-labelledby dropdown-id}
      [:button.dropdown-item
       {:on-click #(language/set-language :de)} "Deutsch"]
      [:button.dropdown-item
       {:on-click #(language/set-language :en)} "English"]
      [:button.dropdown-item
       {:on-click #(language/set-language :pl)} "Polski"]]]))
