(ns schnaq.interface.views.presentation
  (:require [oops.core :refer [oget]]
            [re-frame.core :as rf]
            [schnaq.interface.components.common :refer [schnaq-logo]]
            [schnaq.interface.components.schnaq :as sc]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.views.loading :as loading]
            [schnaq.interface.views.pages :as pages]
            [schnaq.interface.views.schnaq.poll :as poll]))

(defn- footer
  "Add footer links."
  []
  [:div.text-center
   [:hr.w-50.mx-auto]
   [:a.btn.btn-sm.btn-link.text-dark
    {:href "https://schnaq.com/legal-note"}
    (labels :footer.buttons/legal-note)]
   [:a.btn.btn-sm.btn-link.text-dark
    {:href "https://schnaq.com/privacy"}
    (labels :router/privacy)]])

(defn- share-options
  "Show share-options, e.g. link and QR code."
  []
  [:section.text-center
   [:div.display-6.text-center.pb-3
    (labels :presentation.access/code)
    [:span.h1 [sc/access-code]]]
   [:p.mb-0 (labels :presentation.access/qr-alternative)]
   [sc/qr-code (oget js/window :location :href) 250 {:bgColor "transparent"}]])

(defn- poll-presentation
  "Full screen view of a component."
  []
  (let [{:poll/keys [title] :as poll} @(rf/subscribe [:present/poll])
        admin-access? @(rf/subscribe [:user/moderator?])]
    [pages/fullscreen
     {:page/title title}
     [:div.container.pt-5
      [:div.d-flex.flex-row.pb-5
       [:h1 title]
       [schnaq-logo {:style {:width "200px"}
                     :class "pb-3 ms-auto"}]]
      (if poll
        (if admin-access?
          [:section.row
           [:div.col-12.col-md-3 [share-options]]
           [:div.offset-1.col-md-8 [poll/ranking-results poll]]]
          [:section
           [:div.d-xl-none.w-100 [poll/input-or-results poll]]
           [:div.d-none.d-xl-block.w-50.mx-auto [poll/input-or-results poll]]])
        [:div.text-center [loading/spinner-icon]])
      [footer]]]))

;; -----------------------------------------------------------------------------

(defn view []
  [poll-presentation])

;; -----------------------------------------------------------------------------

(rf/reg-sub
 :present/poll
 (fn [db]
   (when-let [poll-id (get-in db [:present :poll])]
     (get-in db [:schnaq :polls poll-id]))))
