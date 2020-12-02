(ns schnaq.interface.views.discussion.new-view
  (:require [oops.core :refer [oget]]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as reitfe]
            [schnaq.interface.views.common :as common]
            [schnaq.interface.text.display-data :refer [labels img-path #_fa]]
            [schnaq.interface.utils.js-wrapper :as js-wrap]
            [schnaq.interface.utils.markdown-parser :as markdown-parser]
            [schnaq.interface.views.brainstorm.tools :as btools]
            [schnaq.interface.views.discussion.view-elements :as view]
            [schnaq.interface.views.meeting.admin-buttons :as admin-buttons]
            [schnaq.interface.views.navbar :as navbar]
            ))


(defn- meeting-header
  "Overview header for a meeting with its title as headline"
  [{:meeting/keys [title share-hash] :as meeting}]
  (let [admin-access-map @(rf/subscribe [:meetings/load-admin-access])
        edit-hash (get admin-access-map share-hash)]
    [:nav.navbar.navbar-expand-lg.py-3.navbar-dark.context-header
     [:div.container
      ;; schnaq logo
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
      [:div {:id "schnaq-navbar" :class "collapse navbar-collapse"}
       ;; clickable title
       [:div.mr-auto.clickable-no-hover
        {:on-click
         (fn []
           (rf/dispatch [:navigation/navigate :routes.meeting/show
                         {:share-hash share-hash}])
           (rf/dispatch [:meeting/select-current meeting]))}
        [:h3.mx-5 title]]
       [admin-buttons/txt-export share-hash title]
       (when (and edit-hash (btools/is-brainstorm? meeting))
         [admin-buttons/admin-center share-hash edit-hash])
       ;; name input
       [navbar/username-bar-view-light]]]]))

(defn- graph-buton
  "Rounded square button to navigate to the graph view"
  [agenda share-hash]
  [:img.graph-icon-img.clickable-no-hover
   {:src (img-path :icon-graph) :alt (labels :graph.button/text)
    :title (labels :graph.button/text)
    :on-click #(rf/dispatch
                 [:navigation/navigate :routes/graph-view
                  {:id (-> agenda :agenda/discussion :db/id)
                   :share-hash share-hash}])}])
;;; text input

(defn- input-starting-statement-form
  "A form, which allows the input of a starting-statement."
  []
  [:form
   {:on-submit (fn [e] (js-wrap/prevent-default e)
                 (rf/dispatch [:discussion.add.statement/starting
                               (oget e [:target :elements])]))}
   [:input.form-control.discussion-text-input-bubble
    {:name "statement-text" :wrap "soft" :rows 1
     :auto-complete "off"
     :required true
     :placeholder (labels :discussion/add-argument-conclusion-placeholder)}]])


(defn- topic-bubble [meeting]
  (let [agenda @(rf/subscribe [:chosen-agenda])
        {:meeting/keys [share-hash]} meeting
        {:meeting/keys [title]} meeting
        admin-access-map @(rf/subscribe [:meetings/load-admin-access])
        edit-hash (get admin-access-map share-hash)
        ]
    (common/set-website-title! (:agenda/title agenda))
    [:div.topic-view-rounded
     [:div.discussion-light-background
      [:div.row
       ;; graph
       [:div.col-3.col-lg-1.graph-icon
        [graph-buton agenda share-hash]]
       ;; title
       [:div.col-6.col-lg-10
        [:h2.clickable-no-hover.align-self-center.my-4
         {:on-click #(rf/dispatch [:navigation/navigate :routes.discussion/start
                                   {:share-hash share-hash
                                    :id (:db/id (:agenda/discussion agenda))}])}
         (:agenda/title agenda)]
        [markdown-parser/markdown-to-html (:agenda/description agenda)]
        [:div.line-divider.my-4]
        [input-starting-statement-form]
        ]
       ;; information
       [:div.col-3.col-lg-1.p-0
        (when (and edit-hash (btools/is-brainstorm? meeting))
          [admin-buttons/admin-center share-hash edit-hash])
        [admin-buttons/txt-export share-hash title]
        ]]]]))


(defn- topic-view [current-meeting]
  [:<>
   [topic-bubble
    current-meeting
    (fn []
      (rf/dispatch [:navigation/navigate :routes.meeting/show
                    {:share-hash (:meeting/share-hash current-meeting)}])
      (rf/dispatch [:meeting/select-current current-meeting]))]
   [view/conclusions-list @(rf/subscribe [:discussion.conclusions/starting])
    (:meeting/share-hash current-meeting)]])

(defn- discussion-start-view
  "The first step after starting a discussion."
  []
  (let [current-meeting @(rf/subscribe [:meeting/selected])]
    [:<>
     [meeting-header current-meeting]
     [:container-fluid
      [:div.row.px-0.mx-0
       [:div.col-1]
       [:div.col-10.py-4
        [topic-view current-meeting]]
       [:div.col-1]]]]))

(defn discussion-start-view-entrypoint []
  [discussion-start-view])