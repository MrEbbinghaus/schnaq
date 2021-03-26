(ns schnaq.interface.views.hub.overview
  (:require [ghostwheel.core :refer [>defn-]]
            [goog.string :as gstring]
            [re-frame.core :as rf]
            [schnaq.interface.text.display-data :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.toolbelt :as toolbelt]
            [schnaq.interface.views.feed.overview :as feed]
            [schnaq.interface.views.pages :as pages]))

(>defn- hub-index
  "Shows the page for an overview of schnaqs for a hub. Takes a keycloak-name which
  uniquely refers to a hub."
  [keycloak-name]
  [string? :ret vector?]
  [pages/with-nav
   {:page/heading (gstring/format (labels :hub/heading) keycloak-name)}
   [:div.container-fluid.px-0
    [toolbelt/desktop-mobile-switch
     [feed/feed-page-desktop [:hubs/schnaqs keycloak-name]]
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

(rf/reg-sub
  :hubs/all
  (fn [db] (:hubs db)))