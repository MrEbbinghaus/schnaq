(ns schnaq.interface.views.feed.filters
  (:require [clojure.set :as cset]
            [goog.dom :as gdom]
            [re-frame.core :as rf]
            [reagent.core :as r]
            [schnaq.interface.text.display-data :refer [fa labels]]
            [schnaq.interface.utils.toolbelt :as tools]
            [schnaq.interface.utils.tooltip :as tooltip]))

(defn- state-selections
  "Selection-options for type filters."
  []
  [:div.form-row.pb-3
   [:div.col-auto
    [:select#filter-state-selection.mr-1.form-control
     [:option {:value :is} (labels :filters.option.type/is)]
     [:option {:value :is-not} (labels :filters.option.type/is-not)]]]
   [:div.col-auto
    [:select#filter-state.mr-1.form-control
     ;; Needs to be string, otherwise ns will be stripped
     [:option {:value "discussion.state/closed"} (labels :filters.discussion.option.state/closed)]
     [:option {:value "statement.type/read-only"} (labels :filters.discussion.option.state/read-only)]]]])

(defn- add-filters
  "A small component for adding new filters."
  []
  (let [current-selection (r/atom "state")]
    (fn []
      [:section.border-bottom.pb-2.text-left
       [:div.form-group
        [:label {:for :add-filter-menu}
         (labels :filters.label/filter-for)]
        [:select#add-filter-menu.mr-1.form-control
         {:on-change #(reset! current-selection (tools/get-selection-from-event %))}
         [:option {:value :state} (labels :filters.discussion.option.state/label)]]]
       (case @current-selection
         "state" [state-selections])
       [:button.btn.btn-outline-dark.mr-2
        {:on-click #(case @current-selection
                      "state"
                      (rf/dispatch [:filters.discussion.activate/state
                                    (tools/get-current-selection (gdom/getElement "filter-state-selection"))
                                    (tools/get-current-selection (gdom/getElement "filter-state"))]))}
        [:i {:class (fa :plus)}] " " (labels :filters.add/button)]])))

(rf/reg-event-db
  :filters.discussion.activate/state
  (fn [db [_ criteria extra]]
    (let [new-filter {:type :state
                      :criteria (keyword criteria)
                      :extra extra}]
      (update-in db [:feed :filters] #(cset/union #{new-filter} %)))))

(rf/reg-sub
  ;; TODO use this
  :filters.discussion/active
  (fn [db _]
    (get-in db [:feed :filters] #{})))

(rf/reg-sub
  ;; TODO use this
  :filters.discussion/active?
  (fn [_]
    (rf/subscribe [:filters.discussion/active]))
  (fn [active-filters _]
    (seq active-filters)))

(rf/reg-event-db
  :filters.discussion/clear
  (fn [db _]
    (assoc-in db [:feed :filters] #{})))

(defn- default-menu
  "The default filter menu that is shown to the user."
  []
  [:<>
   [add-filters]
   #_[active-filters]
   (when (< 1 (count @(rf/subscribe [:filters.discussion/active])))
     [:button.btn.btn-outline-secondary.text-center
      {:on-click #(rf/dispatch [:filters.discussion/clear])}
      (labels :filters.buttons/clear)])])

(defn filter-button
  "A button opening the default filters on click."
  []
  (let [active-filters? @(rf/subscribe [:filters.discussion/active?])]
    [tooltip/html
     [default-menu]
     [:span.ml-2.pl-1.border-left
      [:button.btn.btn-outline-primary.btn-sm.mx-1
       {:class (when active-filters? "btn-outline-secondary active")}
       (labels :badges.filters/button)]]
     {:hideOnClick :toggle}]))
