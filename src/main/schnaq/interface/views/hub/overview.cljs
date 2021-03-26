(ns schnaq.interface.views.hub.overview
  (:require [ghostwheel.core :refer [>defn-]]
            [goog.string :as gstring]
            [re-frame.core :as rf]
            [schnaq.interface.text.display-data :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.js-wrapper :as js-wrap]
            [schnaq.interface.utils.toolbelt :as toolbelt]
            [schnaq.interface.views.feed.overview :as feed]
            [schnaq.interface.views.pages :as pages]))

(defn hub-settings
  "Additional hub settings that are displayed in the feed."
  []
  [:div.pb-3
   [:form
    [:div.form-row
     [:div.col
      [:input.form-control {:name "hub-schnaq-input"
                            :required true
                            :placeholder "Schnaq-URL z.B. https://schnaq.com/schnaq/…"}]]
     [:div.col
      [:button.btn.btn-secondary
       {:on-click (fn [e]
                    (js-wrap/prevent-default e))}
       "Add schnaq to hub"]]]]])

(defn hub-page-desktop [subscription-vector]
  [:div.row.px-0.mx-0.py-3
   [:div.col-3.py-3
    [feed/feed-navigation]]
   [:div.col-6.py-3.px-5
    [hub-settings]
    [feed/schnaq-list-view subscription-vector]]
   [:div.col-3.py-3
    [feed/feed-extra-information]]])


;; TODO mobile umstellen
(>defn- hub-index
  "Shows the page for an overview of schnaqs for a hub. Takes a keycloak-name which
  uniquely refers to a hub."
  [keycloak-name]
  [string? :ret vector?]
  [pages/with-nav
   {:page/heading (gstring/format (labels :hub/heading) keycloak-name)}
   [:div.container-fluid.px-0
    [toolbelt/desktop-mobile-switch
     [hub-page-desktop [:hubs/schnaqs keycloak-name]]
     [feed/feed-page-mobile [:hubs/schnaqs keycloak-name]]]]])

(defn hub-overview
  "Renders all schnaqs belonging to the hub."
  []
  (let [keycloak-name (get-in @(rf/subscribe [:navigation/current-route])
                              [:path-params :keycloak-name])]
    [hub-index keycloak-name]))

(rf/reg-event-fx
  :hub/load
  (fn [{:keys [db]} [_ keycloak-name]]
    {:fx [(http/xhrio-request db :get (str "/hub/" keycloak-name) [:hub.load/success keycloak-name])]}))

(rf/reg-event-fx
  :hubs.personal/load
  (fn [{:keys [db]}]
    {:fx [(http/xhrio-request db :get "/hubs/personal" [:hubs.load/success])]}))

(rf/reg-event-db
  :hubs.load/success
  (fn [db [_ {:keys [hubs]}]]
    (when-not (empty? hubs)
      (let [formatted-hubs
            (into {} (map #(vector (:hub/keycloak-name %) %) hubs))]
        (assoc db :hubs formatted-hubs)))))

(rf/reg-event-db
  :hub.load/success
  (fn [db [_ keycloak-name response]]
    (assoc-in db [:hubs keycloak-name] (:hub response))))

(rf/reg-sub
  :hubs/schnaqs
  (fn [db [_ keycloak-name]]
    (get-in db [:hubs keycloak-name :hub/schnaqs] [])))