(ns schnaq.interface.celebrations
  (:require [re-frame.core :as rf]
            [schnaq.interface.text.display-data :refer [labels]]
            [schnaq.interface.views.meeting.admin-buttons :as admin-buttons]))

(defn- schnaq-filled-body []
  [:<>
   [:p (labels :celebrations.schnaq-filled/lead)]
   [:p (labels :celebrations.schnaq-filled/share-now)]
   [:button.btn.btn-sm.btn-secondary
    {:on-click admin-buttons/open-share-modal}
    (labels :celebrations.schnaq-filled/button)]])

(rf/reg-event-fx
  :celebrate/schnaq-filled
  (fn []
    {:fx [[:dispatch [:notification/add
                      #:notification{:title (labels :celebrations.schnaq-filled/title)
                                     :body [schnaq-filled-body]
                                     :context :primary
                                     :stay-visible? true}]]]}))
