(ns meetly.meeting.interface.analytics.core
  (:require [meetly.meeting.interface.views.base :as base]
            [meetly.meeting.interface.text.display-data :refer [labels]]
            [meetly.meeting.interface.config :refer [config]]
            [goog.string :as gstring]
            [re-frame.core :as rf]
            [ajax.core :as ajax]))

(defn- analytics-card
  "A single card containing a metric and a title."
  [title metric]
  [:div.card
   [:div.card-body
    [:h5.card-title title]
    [:p.card-text.display-1 metric]
    [:p.card-text [:small.text-muted "Last updated ..."]]]])

(defn analytics-dashboard-view
  "The dashboard displaying all analytics."
  []
  [:div
   [base/nav-header]
   (let [meetings-num @(rf/subscribe [:analytics/number-of-meetings-overall])
         usernames-num @(rf/subscribe [:analytics/number-of-usernames-overall])
         average-agendas @(rf/subscribe [:analytics/number-of-average-agendas])
         statements-num @(rf/subscribe [:analytics/number-of-statements-overall])]
     [:div.container.px-5.py-3
      [:div.card-columns
       [analytics-card (labels :analytics/overall-meetings) meetings-num]
       [analytics-card (labels :analytics/user-numbers) usernames-num]
       [analytics-card (labels :analytics/average-agendas-title) average-agendas]
       [analytics-card (labels :analytics/statements-num-title) statements-num]]])])

;; #### Events ####

(rf/reg-event-fx
  :analytics/load-dashboard
  (fn [_ _]
    {:dispatch-n [[:analytics/load-meeting-num]
                  [:analytics/load-usernames-num]
                  [:analytics/load-average-number-of-agendas]
                  [:analytics/load-statements-num]]}))

(defn fetch-with-password
  "Fetches something from an endpoint with the password."
  [db path on-success-event]
  {:http-xhrio {:method :post
                :uri (str (:rest-backend config) path)
                :format (ajax/transit-request-format)
                :params {:password (-> db :admin :password)}
                :response-format (ajax/transit-response-format)
                :on-success [on-success-event]
                :on-failure [:ajax-failure]}})

(rf/reg-event-fx
  :analytics/load-meeting-num
  (fn [{:keys [db]} _]
    (fetch-with-password db "/analytics/meetings" :analytics/meeting-num-loaded)))

(rf/reg-event-fx
  :analytics/load-usernames-num
  (fn [{:keys [db]} _]
    (fetch-with-password db "/analytics/usernames" :analytics/usernames-num-loaded)))

(rf/reg-event-fx
  :analytics/load-average-number-of-agendas
  (fn [{:keys [db]} _]
    (fetch-with-password db "/analytics/agendas-per-meeting" :analytics/agendas-per-meeting-loaded)))

(rf/reg-event-fx
  :analytics/load-statements-num
  (fn [{:keys [db]} _]
    (fetch-with-password db "/analytics/statements" :analytics/statements-num-loaded)))

(rf/reg-event-db
  :analytics/meeting-num-loaded
  (fn [db [_ {:keys [meetings-num]}]]
    (assoc-in db [:analytics :meetings-num :overall] meetings-num)))

(rf/reg-event-db
  :analytics/usernames-num-loaded
  (fn [db [_ {:keys [usernames-num]}]]
    (assoc-in db [:analytics :usernames-num :overall] usernames-num)))

(rf/reg-event-db
  :analytics/statements-num-loaded
  (fn [db [_ {:keys [statements-num]}]]
    (assoc-in db [:analytics :statements-num :overall] statements-num)))

(rf/reg-event-db
  :analytics/agendas-per-meeting-loaded
  (fn [db [_ {:keys [average-agendas]}]]
    (assoc-in db [:analytics :agendas :average-per-meeting] (gstring/format "%.2f" average-agendas))))

;; #### Subs ####

(rf/reg-sub
  :analytics/number-of-meetings-overall
  (fn [db _]
    (get-in db [:analytics :meetings-num :overall])))

(rf/reg-sub
  :analytics/number-of-usernames-overall
  (fn [db _]
    (get-in db [:analytics :usernames-num :overall])))

(rf/reg-sub
  :analytics/number-of-average-agendas
  (fn [db _]
    (get-in db [:analytics :agendas :average-per-meeting])))

(rf/reg-sub
  :analytics/number-of-statements-overall
  (fn [db _]
    (get-in db [:analytics :statements-num :overall])))