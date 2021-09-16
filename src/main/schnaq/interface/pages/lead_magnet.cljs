(ns schnaq.interface.pages.lead-magnet
  (:require [oops.core :refer [oget]]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as reitfe]
            [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.js-wrapper :as jq]
            [schnaq.interface.views.pages :as pages]))

(rf/reg-event-fx
  :lead-magnet/subscribe
  (fn [{:keys [db]} [_ email]]
    {:fx [(http/xhrio-request db :post "/lead-magnet/subscribe" [:lead-magnet.subscribe/success] {:email email})]}))

(rf/reg-event-db
  :lead-magnet.subscribe/success
  (fn [db _]
    (assoc-in db [:lead-magnet :requested?] true)))

(rf/reg-sub
  :lead-magnet/requested?
  (fn [db _]
    (get-in db [:lead-magnet :requested?] false)))

(defn- subscription-form
  []
  [:form.text-left
   {:on-submit (fn [e]
                 (jq/prevent-default e)
                 (rf/dispatch [:lead-magnet/subscribe (oget e [:target :elements "EMAIL" :value])]))}

   [:div.form-group
    [:label {:for "EMAIL"} (labels :lead-magnet.form/label)]
    [:input#dsgvo-checklist
     {:required true
      :placeholder (labels :startpage.newsletter/address-placeholder)
      :name "EMAIL" :defaultValue "" :type "email"
      :class "form-control"}]]

   [:div.form-group
    [:div.form-check
     [:input#nochmal-nachfragen.form-check-input {:type "checkbox" :required true}]
     [:label.form-check-label {:for "nochmal-nachfragen"}
      (labels :lead-magnet.privacy/consent)]
     [:p
      [:a {:href "#" :type "button" :data-toggle "collapse" :data-target "#collapse-more-newsletter"
           :aria-expanded "false" :aria-controls "#collapse-more-newsletter" :data-reitit-handle-click false}
       (labels :startpage.newsletter/more-info-clicker)]]
     [:div.collapse {:id "collapse-more-newsletter"}
      [:p.small (labels :startpage.newsletter/policy-disclaimer)
       [:br] (labels :startpage.newsletter/privacy-policy-lead) " "
       [:a {:href (reitfe/href :routes/privacy-extended)}
        (labels :privacy/note)] "."]]]]

   [:div.form-group
    [:input
     {:name "subscribe" :value (labels :lead-magnet.form/button) :type "submit" :readOnly true
      :class "btn btn-primary d-block mx-auto"}]]])

(defn- thank-you-view
  []
  [:section.panel-white
   [:img.img-fluid
    {:src (img-path :schnaqqifant.300w/talk)}]
   [:p (labels :lead-magnet.requested/part-1)]
   [:p (labels :lead-magnet.requested/part-2)]])

(defn- lead-magnet
  []
  [pages/with-nav-and-header
   {:page/heading (labels :lead-magnet/heading)
    :page/subheading (labels :lead-magnet/subheading)}
   [:section.container.text-center.pb-5
    [:a.btn.button-secondary.mb-3 {:href "#dsgvo-checklist"} (labels :lead-magnet.cta/button)]
    [:h4 (labels :lead-magnet.explain.what/heading)]
    [:p.text-left (labels :lead-magnet.explain.what/text)]
    [:h4 (labels :lead-magnet.explain.how/heading)]
    [:p.text-left.pb-3 (labels :lead-magnet.explain.how/text)]
    [:a {:href "#dsgvo-checklist"}
     [:img.img-fluid.mb-5.mx-auto.text-center.shadow
      {:src (img-path :lead-magnet/cover)
       :alt (labels :lead-magnet.cover/alt-text)}]]
    (if @(rf/subscribe [:lead-magnet/requested?])
      [thank-you-view]
      [subscription-form])]])

(defn view []
  [lead-magnet])
