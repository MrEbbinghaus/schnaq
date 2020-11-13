(ns schnaq.interface.views.errors
  (:require [cljs.pprint :refer [pprint]]
            [re-frame.core :as rf]
            [schnaq.interface.text.display-data :refer [labels img-path]]
            [schnaq.interface.views.pages :as pages]))

(defn not-found-view-stub []
  [])

(defn- error-page-layout
  "Template to build generic error pages."
  [heading body]
  [pages/with-nav-and-header
   {:page/heading heading}
   [:div.container.py-3.text-center
    [:img.w-25 {:src (img-path :elephant-stop)}]
    [:div.alert.alert-danger.mt-4 {:role "alert"}
     [:h4.alert-heading heading]
     [:hr]
     [:p body]
     [:p (labels :error.generic/contact-us)]]]])

(defn forbidden-page
  "Show the forbidden page."
  []
  (error-page-layout
    (labels :error.403/heading)
    (labels :error.403/body)))

(defn- true-404-page
  "The 404 page the user gets to see."
  []
  (error-page-layout
    (labels :error.404/heading)
    (labels :error.404/body)))

(defn true-404-entrypoint
  "404 view wrapper for routes."
  []
  [true-404-page])


;; -----------------------------------------------------------------------------

(rf/reg-event-fx
  :ajax-failure
  (fn [{:keys [db]} [_ failure]]
    {:db (assoc db :error {:ajax failure})
     :fx [[:dispatch [:notification/add
                      #:notification{:title (labels :errors/generic)
                                     :body [:pre
                                            [:code
                                             (with-out-str (pprint failure))]]
                                     :context :danger
                                     :stay-visible? true
                                     :on-close-fn #(rf/dispatch [:clear-error])}]]]}))

(rf/reg-event-db
  :clear-error
  (fn [db _]
    (dissoc db :error)))