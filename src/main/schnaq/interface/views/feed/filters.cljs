(ns schnaq.interface.views.feed.filters
  (:require [clojure.set :as cset]
            [goog.dom :as gdom]
            [re-frame.core :as rf]
            [reagent.core :as r]
            [schnaq.interface.text.display-data :refer [fa labels]]
            [schnaq.interface.utils.toolbelt :as tools]
            [schnaq.interface.utils.tooltip :as tooltip]))

(defn- state-selections
  "Selection-options for discussion state filters."
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

(defn- author-selections
  "Selection-options for discussion author filters."
  []
  [:div.form-row.pb-3
   [:div.col-auto.align-self-center
    [:p.my-auto (labels :filters.discussion.option.author/prelude)]]
   [:div.col-auto
    [:select#filter-author-selection.mr-1.form-control
     [:option {:value :included} (labels :filters.discussion.option.author/included)]
     [:option {:value :excluded} (labels :filters.discussion.option.author/excluded)]]]])

(defn- statement-number-selections
  "Selection-options for number of statements filters."
  []
  [:div.form-row.pb-3
   [:div.col-auto
    [:select#filter-numbers-selection.mr-1.form-control
     [:option {:value ">"} (labels :filters.option.vote/bigger)]
     [:option {:value "="} (labels :filters.option.vote/equal)]
     [:option {:value "<"} (labels :filters.option.vote/less)]]]
   [:div.col-auto
    [:input#filter-numbers.mr-1.form-control
     {:type :number
      :placeholder 0
      :defaultValue 0}]]])

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
         [:option {:value :state} (labels :filters.discussion.option.state/label)]
         [:option {:value :numbers} (labels :filters.discussion.option.numbers/label)]
         [:option {:value :author} (labels :filters.discussion.option.author/label)]]]
       (case @current-selection
         "state" [state-selections]
         "numbers" [statement-number-selections]
         "author" [author-selections])
       [:button.btn.btn-outline-dark.mr-2
        {:on-click #(case @current-selection
                      "state"
                      (rf/dispatch [:filters.discussion/activate :state
                                    (tools/get-current-selection (gdom/getElement "filter-state-selection"))
                                    (keyword (tools/get-current-selection (gdom/getElement "filter-state")))])
                      "numbers"
                      (rf/dispatch [:filters.discussion/activate :numbers
                                    (tools/get-current-selection (gdom/getElement "filter-numbers-selection"))
                                    (.-value (gdom/getElement "filter-numbers"))])
                      "author"
                      (rf/dispatch [:filters.discussion/activate :author
                                    (tools/get-current-selection (gdom/getElement "filter-author-selection"))]))}
        [:i {:class (fa :plus)}] " " (labels :filters.add/button)]])))

(defn- prettify-filter
  "A helper returning a single filter prettified."
  [{:keys [type criteria extra] :as filter-data}]
  (let [type-label (labels (keyword :filters.labels.type type))
        criteria-label (labels (keyword :filters.labels.criteria criteria))
        extra-label (case type
                      :state (labels (keyword :filters.discussion.option.state extra))
                      :numbers extra
                      :author "")]
    [:div.d-flex.justify-content-between
     [:p.d-inline-block.pr-2.my-auto {:key (str filter-data)}
      [:strong type-label] " "
      criteria-label " "
      extra-label]
     [:button.btn.btn-outline-primary-small.my-1
      {:on-click #(rf/dispatch [:filters.discussion/deactivate filter-data])} "x"]]))

(defn- active-filters
  "A menu showing the currently active filters."
  []
  (let [active @(rf/subscribe [:filters.discussion/active])]
    [:section.pt-2.text-left
     [:p (labels :filters.heading/active)]
     (when (seq active)
       (for [filter-data active]
         ;; The key needs to be set because it's a list.
         ;; And the key is needed again in prettify-filter or else react does not render.
         (with-meta
           [prettify-filter filter-data]
           {:key (str filter-data)})))]))

(defn- default-menu
  "The default filter menu that is shown to the user."
  []
  [:<>
   [add-filters]
   [active-filters]
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

(rf/reg-event-db
  :filters.discussion/activate
  (fn [db [_ filter-type criteria extra]]
    (let [new-filter {:type filter-type
                      :criteria (keyword criteria)
                      :extra extra}]
      (update-in db [:feed :filters] #(cset/union #{new-filter} %)))))

(rf/reg-sub
  :filters.discussion/active
  (fn [db _]
    (get-in db [:feed :filters] #{})))

(rf/reg-sub
  :filters.discussion/active?
  (fn [_]
    (rf/subscribe [:filters.discussion/active]))
  (fn [active-filters _]
    (seq active-filters)))

(rf/reg-event-db
  :filters.discussion/clear
  (fn [db _]
    (assoc-in db [:feed :filters] #{})))

(rf/reg-event-db
  :filters.discussion/deactivate
  (fn [db [_ filter-data]]
    (update-in db [:feed :filters] disj filter-data)))
