(ns schnaq.interface.views.discussion.search
  (:require [re-frame.core :as rf]
            [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.components.motion :as motion]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.views.discussion.card-elements :as elements]
            [schnaq.interface.views.discussion.conclusion-card :as card]
            [schnaq.interface.views.discussion.filters :as filters]
            [schnaq.interface.views.discussion.logic :as logic]
            [schnaq.interface.views.pages :as pages]))

(rf/reg-sub
  :schnaq.search.current/search-string
  (fn [db _]
    (get-in db [:search :schnaq :current :search-string] "")))

(rf/reg-sub
  :schnaq.search.current/result
  (fn [db _]
    (get-in db [:search :schnaq :current :result] [])))

(defn- search-info [results search-string empty-search?]
  [motion/move-in :left
   [:div.panel-white.mb-4
    [:div.d-inline-block
     [:h2 (labels :schnaq.search/heading)]
     [:div.row.mx-0.mt-4.mb-3
      [:img.dashboard-info-icon-sm {:src (img-path :icon-search)}]
      [:div.text.display-6.my-auto.mx-3
       (if empty-search?
         (labels :schnaq.search/no-input)
         search-string)]]]
    [:div.row.m-0
     [:img.dashboard-info-icon-sm {:src (img-path :icon-posts)}]
     (if (or empty-search? (empty? results))
       [:p.mx-3 (labels :schnaq.search/new-search-title)]
       [:p.mx-3 (str (count results) " " (labels :schnaq.search/results))])]]])

(defn search-results [results]
  (let [sort-method @(rf/subscribe [:discussion.statements/sort-method])
        local-votes (rf/subscribe [:local-votes])
        key-fn (case sort-method
                 :newest :statement/created-at
                 :popular #(logic/calculate-votes % @local-votes))
        sorted-results (sort-by key-fn > results)
        active-filters @(rf/subscribe [:filters/active])
        filtered-conclusions (filters/filter-statements sorted-results active-filters local-votes)]
    [motion/move-in :right
     (for [statement filtered-conclusions]
       [:div.p-2.w-lg-50.d-inline-block
        {:key (:db/id statement)}
        [card/statement-card statement]])]))

(defn- search-view []
  (let [search-string @(rf/subscribe [:schnaq.search.current/search-string])
        results @(rf/subscribe [:schnaq.search.current/result])
        empty-search? (= "" search-string)]
    [pages/with-discussion-header
     {:page/heading (labels :schnaq.search/title)}
     [:div.container-fluid
      [:div.row
       [:div.col-md-6.col-lg-4.py-4.px-0.px-md-3
        [search-info results search-string empty-search?]]
       [:div.col-md-6.col-lg-8.py-4.px-0.px-md-3
        [elements/action-view true]
        (when-not empty-search?
          [search-results results])]]]]))

(defn view
  "Search view"
  []
  [search-view])
