(ns schnaq.interface.views.discussion.filters
  "Filters are saved and read as maps internaly. e.g.

  ```
  {:type :labels
   :label :check
   :criteria :includes}
  ```

   This filter filters for statements that include the label :check."
  (:require [oops.core :refer [oget oget+]]
            [reagent.core :as r]
            [schnaq.config.shared :as shared-config]
            [schnaq.interface.text.display-data :refer [labels fa]]
            [schnaq.interface.utils.tooltip :as tooltip]))

(defn- set-selected-option
  "Helper function to set the correct temp atom value for a selection."
  [event store]
  (let [options (oget event :target :options)
        selection-index (str (oget event :target :selectedIndex))]
    (reset! store (oget+ options selection-index :value))))

(defn- add-filter-selection
  "A small compontent for adding new filters."
  []
  (let [current-selection (r/atom "labels")]
    (fn []
      [:section.border-bottom.pb-2
       [:form.text-left
        [:div.form-group
         [:label {:for :add-filter-menu}
          (labels :filters.label/filter-for)]
         [:select#add-filter-menu.mr-1.form-control
          {:on-change #(set-selected-option % current-selection)}
          [:option {:value :labels} (labels :filters.option.labels/text)]
          [:option {:value :type} (labels :filters.option.type/text)]
          [:option {:value :votes} (labels :filters.option.votes/text)]]]
        (case @current-selection
          "labels"
          [:<>
           [:div.form-row.pb-3
            [:div.col-auto
             [:select#filter-labels-selection.mr-1.form-control
              [:option {:value :includes} (labels :filters.option.labels/includes)]
              [:option {:value :excludes} (labels :filters.option.labels/excludes)]]]
            [:div.col-auto
             [:select#filter-labels-label.mr-1.form-control
              (for [label shared-config/allowed-labels]
                [:option
                 {:value label
                  :key (str "filter-label-" label)}
                 label])]]]]
          ;; TODO pretty labels in selection
          "")
        [:button.btn.btn-outline-dark.mr-2
         [:i {:class (fa :plus)}] " " (labels :filters.add/button)]]])))

(defn- default-menu
  "The default filter menu that is shown to the user."
  []
  [:div
   [add-filter-selection]])

(defn filter-button
  "A button opening the default filters on click."
  []
  (let [active-filters? false]
    ;; TODO add flag for when filters are active.
    [tooltip/html
     [default-menu]
     [:button.btn.btn-outline-primary.mr-2.h-100
      {:class (when active-filters? "active")}
      (labels :badges.filters/button)]
     {:offset 50}]))
