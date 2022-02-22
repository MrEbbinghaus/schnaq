(ns schnaq.interface.views.admin.control-center
  (:require [oops.core :refer [oget]]
            [re-frame.core :as rf]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.js-wrapper :as js-wrap]
            [schnaq.interface.views.pages :as pages]))

(rf/reg-event-fx
 :admin.schnaq/delete
 (fn [{:keys [db]} [_ share-hash]]
   {:fx [(http/xhrio-request db :delete "/admin/schnaq/delete" [:no-op]
                             {:share-hash share-hash} [:ajax.error/as-notification])]}))

(defn- schnaq-deletion-form
  "Delete any schnaq."
  []
  [:form#private-schnaq-form.form
   {:on-submit (fn [e]
                 (js-wrap/prevent-default e)
                 (when (js/confirm (labels :admin.center.delete/confirmation))
                   (rf/dispatch [:admin.schnaq/delete
                                 (oget e [:target :elements :share-hash-deletion :value])])))}
   [:div.input-group
    [:div.form-floating
     [:input#share-hash-deletion.form-control
      {:name "share-hash-deletion"
       :placeholder "delete-me"
       :required true}]
     [:label {:for "share-hash-deletion"} (labels :admin.center.delete.private/label)]]
    [:button.input-group-text (labels :admin.center.delete.public/button)]]])

(defn- center-overview
  "The startpage of the admin center."
  []
  [pages/with-nav-and-header
   {:condition/needs-administrator? true
    :page/title (labels :admin.center.start/title)
    :page/heading (labels :admin.center.start/heading)
    :page/subheading (labels :admin.center.start/subheading)}
   [:div.container
    [:h2 (labels :admin.center.delete/heading)]
    [:h4 (labels :admin.center.delete.private/heading)]
    [schnaq-deletion-form]]])

(defn center-overview-route
  []
  [center-overview])
