(ns schnaq.interface.analytics.core
  (:require [clojure.string :as string]
            [ghostwheel.core :refer [>defn-]]
            [goog.string :as gstring]
            [oops.core :refer [oget]]
            [re-frame.core :as rf]
            [schnaq.interface.analytics.charts :as chart]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.js-wrapper :as js-wrap]
            [schnaq.interface.views.pages :as pages]))

(defn- analytics-card
  "A single card containing a metric and a title."
  [title metric]
  [:div.card
   [:div.card-body
    [:h5.card-title title]
    [:p.card-text.display-1 metric]
    [:p.card-text [:small.text-muted "Last updated ..."]]]])

(defn- percentage-change
  "Calculate the percentage change between two values. Color positive changes green and negative red."
  [initial-value changed-value]
  (let [change (* 100 (/ (- changed-value initial-value) initial-value))]
    [:span {:class (if (< change 0) "text-warning" "text-success")}
     (gstring/format "%s %%" change)]))

(defn- statements-stats
  "A single card containing statement-growth metrics."
  [statements-total statements-series]
  (let [values (map second statements-series)
        penultimate (last (butlast values))
        ultimate (last values)]
    [:div.card
     [:div.card-body
      [:h5.card-title (labels :analytics/statements-num-title)]
      [:p.card-text.display-5 "Overall: " statements-total " — change: " [percentage-change penultimate ultimate]]
      [chart/regular "Statements" (map first statements-series) values]
      [:p.card-text [:small.text-muted "Last updated ..."]]]]))

(>defn- multi-arguments-card
  "A card containing multiple sub-metrics that are related. Uses the keys of a map
  to make sub-headings."
  [title content]
  [string? map? :ret vector?]
  [:div.card
   [:div.card-body
    [:h5.card-title title]
    (for [[metric-name metric-value] content]
      [:div {:key metric-name}
       [:p.card-text [:strong (string/capitalize (name metric-name))]]
       [:p.card-text.display-1 metric-value]
       [:hr]])
    [:p.card-text [:small.text-muted "Last updated ..."]]]])

(defn- analytics-controls
  "The controls for the analytics view."
  []
  [:form.form-inline
   {:on-submit (fn [e]
                 (js-wrap/prevent-default e)
                 (rf/dispatch [:analytics/load-all-with-time (oget e [:target :elements :days-input :value])]))}
   [:input#days-input.form-control.form-round-05.py-1.mr-sm-2
    {:type "number"
     :name "days-input"
     :placeholder "Stats for last X days"
     :autoFocus true
     :required true
     :defaultValue 30}]
   [:input.btn.btn-outline-primary.mt-1.mt-sm-0
    {:type "submit"
     :value (labels :analytics/fetch-data-button)}]])

(defn- analytics-dashboard-view
  "The dashboard displaying all analytics."
  []
  [pages/with-nav-and-header
   {:condition/needs-administrator? true
    :page/heading (labels :analytics/heading)}
   [:<>
    (let [discussions-num @(rf/subscribe [:analytics/number-of-discussions-overall])
          usernames-num @(rf/subscribe [:analytics/number-of-usernames-anonymous])
          registered-users @(rf/subscribe [:analytics/number-of-users-registered])
          average-statements @(rf/subscribe [:analytics/number-of-average-statements])
          statements-num @(rf/subscribe [:analytics/number-of-statements-overall])
          statements-series @(rf/subscribe [:analytics/number-of-statements-series])
          active-users-num @(rf/subscribe [:analytics/number-of-active-users-overall])
          statement-lengths @(rf/subscribe [:analytics/statement-lengths-stats])
          statement-types @(rf/subscribe [:analytics/statement-type-stats])
          labels-stats @(rf/subscribe [:analytics/labels-stats])]
      [:<>
       [:div.container.px-5.py-3
        [analytics-controls]]
       [:div.container-fluid
        [:div.row
         [:div.col-12.col-lg-6
          [statements-stats statements-num statements-series]]]
        [:div.card-columns
         [analytics-card (labels :analytics/overall-discussions) discussions-num]
         [analytics-card (labels :analytics/user-numbers) usernames-num]
         [analytics-card (labels :analytics/registered-users-numbers) registered-users]
         [analytics-card (labels :analytics/average-statements-title) average-statements]
         [analytics-card (labels :analytics/active-users-num-title) active-users-num]
         [multi-arguments-card (labels :analytics/statement-lengths-title) statement-lengths]
         [multi-arguments-card (labels :analytics/statement-types-title) statement-types]
         [multi-arguments-card (labels :analytics/labels-stats) labels-stats]]]])]])

(defn analytics-dashboard-entrypoint []
  [analytics-dashboard-view])

;; #### Events ####

(rf/reg-event-fx
 :analytics/load-dashboard
 (fn [_ _]
   {:fx [[:dispatch [:analytics/load-all-with-time 30]]]}))

(>defn- fetch-statistics
  "Fetches something from an endpoint with an authentication header."
  ([db path on-success-event]
   [map? string? keyword? :ret map?]
   (fetch-statistics db path on-success-event 9999))
  ([db path on-success-event days-since]
   [map? string? keyword? int? :ret map?]
   (when (get-in db [:user :authenticated?])
     {:fx [(http/xhrio-request db :get path [on-success-event] {:days-since days-since})]})))

(rf/reg-event-fx
 :analytics/load-all-with-time
 (fn [{:keys [db]} [_ days]]
   (fetch-statistics db "/admin/analytics" :analytics/all-stats-loaded days)))

(rf/reg-event-fx
 :analytics/load-discussions-num
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/discussions" :analytics/discussions-num-loaded)))

(rf/reg-event-fx
 :analytics/load-usernames-num
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/usernames" :analytics/usernames-num-loaded)))

(rf/reg-event-fx
 :analytics/load-registered-users-num
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/registered-users" :analytics/registered-users-num-loaded)))

(rf/reg-event-fx
 :analytics/load-average-number-of-statements
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/statements-per-discussion" :analytics/statements-per-discussion-loaded)))

(rf/reg-event-fx
 :analytics/load-statements-num
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/statements" :analytics/statements-num-loaded)))

(rf/reg-event-fx
 :analytics/load-active-users
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/active-users" :analytics/active-users-num-loaded)))

(rf/reg-event-fx
 :analytics/load-statement-length-stats
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/statement-lengths" :analytics/statement-length-stats-loaded)))

(rf/reg-event-fx
 :analytics/load-statements-type-stats
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/statement-types" :analytics/statement-type-stats-loaded)))

(rf/reg-event-fx
 :analytics/load-labels-stats
 (fn [{:keys [db]} _]
   (fetch-statistics db "/admin/analytics/labels" :analytics/labels-loaded)))

(rf/reg-event-db
 :analytics/discussions-num-loaded
 (fn [db [_ {:keys [discussions-sum]}]]
   (assoc-in db [:analytics :discussions-sum :overall] discussions-sum)))

(rf/reg-event-db
 :analytics/usernames-num-loaded
 (fn [db [_ {:keys [usernames-sum]}]]
   (assoc-in db [:analytics :users-num :anonymous] usernames-sum)))

(rf/reg-event-db
 :analytics/registered-users-num-loaded
 (fn [db [_ {:keys [registered-users-num]}]]
   (assoc-in db [:analytics :users-num :registered] registered-users-num)))

(rf/reg-event-db
 :analytics/statements-num-loaded
 (fn [db [_ {:keys [statements-num]}]]
   (assoc-in db [:analytics :statements :number] statements-num)))

(rf/reg-event-db
 :analytics/active-users-num-loaded
 (fn [db [_ {:keys [active-users-num]}]]
   (assoc-in db [:analytics :active-users-num :overall] active-users-num)))

(rf/reg-event-db
 :analytics/statements-per-discussion-loaded
 (fn [db [_ {:keys [average-statements]}]]
   (assoc-in db [:analytics :statements :average-per-discussion] (gstring/format "%.2f" average-statements))))

(rf/reg-event-db
 :analytics/statement-length-stats-loaded
 (fn [db [_ {:keys [statement-length-stats]}]]
   (assoc-in db [:analytics :statements :lengths] statement-length-stats)))

(rf/reg-event-db
 :analytics/statement-type-stats-loaded
 (fn [db [_ {:keys [statement-type-stats]}]]
   (assoc-in db [:analytics :statements :types] statement-type-stats)))

(rf/reg-event-db
 :analytics/labels-loaded
 (fn [db [_ {:keys [labels-stats]}]]
   (assoc-in db [:analytics :labels] labels-stats)))

(rf/reg-event-db
 :analytics/all-stats-loaded
 (fn [db [_ {:keys [statistics]}]]
   (assoc db :analytics {:discussions-sum {:overall (:discussions-sum statistics)}
                         :users-num {:anonymous (:usernames-sum statistics)
                                     :registered (:registered-users-num statistics)}
                         :statements {:number (:statements-num statistics)
                                      :lengths (:statement-length-stats statistics)
                                      :average-per-discussion (:average-statements-num statistics)
                                      :types (:statement-type-stats statistics)}
                         :active-users-num {:overall (:active-users-num statistics)}
                         :labels (:labels-stats statistics)})))

;; #### Subs ####

(rf/reg-sub
 :analytics/number-of-discussions-overall
 (fn [db _]
   (get-in db [:analytics :discussions-sum :overall])))

(rf/reg-sub
 :analytics/number-of-usernames-anonymous
 (fn [db _]
   (get-in db [:analytics :users-num :anonymous])))

(rf/reg-sub
 :analytics/number-of-users-registered
 (fn [db _]
   (get-in db [:analytics :users-num :registered])))

(rf/reg-sub
 :analytics/number-of-average-statements
 (fn [db _]
   (get-in db [:analytics :statements :average-per-discussion])))

(rf/reg-sub
 :analytics/number-of-statements-overall
 (fn [db _]
   (get-in db [:analytics :statements :number :overall])))

(rf/reg-sub
 :analytics/number-of-statements-series
 (fn [db _]
   (sort-by first (get-in db [:analytics :statements :number :series]))))

(rf/reg-sub
 :analytics/number-of-active-users-overall
 (fn [db _]
   (get-in db [:analytics :active-users-num :overall])))

(rf/reg-sub
 :analytics/statement-lengths-stats
 (fn [db _]
   (get-in db [:analytics :statements :lengths])))

(rf/reg-sub
 :analytics/statement-type-stats
 (fn [db _]
   (get-in db [:analytics :statements :types])))

(rf/reg-sub
 :analytics/labels-stats
 (fn [db _]
   (get-in db [:analytics :labels])))
