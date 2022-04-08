(ns schnaq.interface.views.discussion.card-view
  (:require [clojure.string :as cstring]
            [re-frame.core :as rf]
            [schnaq.config.shared :as shared-config]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.toolbelt :as tools]
            [schnaq.interface.views.common :as common]
            [schnaq.interface.views.discussion.card-elements :as elements]
            [schnaq.interface.views.discussion.conclusion-card :as cards]
            [schnaq.interface.views.pages :as pages]
            [schnaq.shared-toolbelt :as stools]))

(rf/reg-event-fx
 :discussion.statements/search
 (fn [{:keys [db]} [_ search-string]]
   (if (cstring/blank? search-string)
     {:db (-> (assoc-in db [:search :schnaq :current :result] [])
              (assoc-in [:search :schnaq :current :search-string] search-string))}
     (let [share-hash (get-in db [:schnaq :selected :discussion/share-hash])]
       {:db (assoc-in db [:search :schnaq :current :search-string] search-string)
        :fx [(http/xhrio-request db :get "/discussion/statements/search" [:discussion.statements.search/success]
                                 {:share-hash share-hash
                                  :search-string search-string
                                  :display-name (tools/current-display-name db)})]}))))

(rf/reg-event-db
 :discussion.statements.search/success
 (fn [db [_ {:keys [matching-statements]}]]
   (-> db
       (assoc-in [:search :schnaq :current :result] (map :db/id matching-statements))
       (update-in [:schnaq :statements] merge (stools/normalize :db/id matching-statements)))))

(defn- discussion-view
  "Displays a history  and input field on the left and conclusions in its center"
  []
  [:div.container-fluid.px-0.px-md-3
   [:div.row
    [:div.col-md-12.col-xxl-8.py-0.pt-md-3
     [:div.d-none.d-md-block [elements/action-view]]]]
   [:div.d-md-none [elements/action-view]]
   [cards/conclusion-cards-list]
   [:div.d-md-none [elements/history-view]]
   [:div.mx-auto
    {:class (when-not shared-config/embedded? "col-11 col-md-12 col-lg-12 col-xl-10")}
    [elements/show-how-to]]
   [:div.d-none.d-md-block [elements/history-view]]])

(defn- start-view
  "The first step after starting a schnaq."
  []
  (let [schnaq @(rf/subscribe [:schnaq/selected])
        title (:discussion/title schnaq)]
    (common/set-website-title! title)
    [discussion-view (:discussion/share-hash schnaq)]))

(rf/reg-sub
 :discussion.statements/show
 ;; The statements which should be shown in the discussion view right now.
 :<- [:schnaq/statements]
 :<- [:schnaq.statements/current-level]
 :<- [:schnaq.search.current/result]
 :<- [:schnaq.search.current/search-string]
 (fn [[statements level-statements search-results search-string] _]
   (if (cstring/blank? search-string)
     (stools/select-values statements level-statements)
     (stools/select-values statements search-results))))

(rf/reg-sub
 :schnaq/statements
 (fn [db _]
   (get-in db [:schnaq :statements])))

(rf/reg-sub
 :schnaq/statement
 :<- [:schnaq/statements]
 (fn [statements [_ statement-id]]
   (get statements statement-id {})))

(rf/reg-sub
 :schnaq.statements/current-level
 ;; A set of statements at the current "level" of discussion / questions
 (fn [db _]
   (get-in db [:schnaq :statement-slice :current-level] #{})))

(rf/reg-sub
 :discussion.conclusion/selected
 ;; TODO rename
 (fn [db _]
   ;; TODO ändere pfad. Sollte eher sowas sein wie fokus statement oder so
   (let [focus-id (get-in db [:discussion :conclusion :selected])]
     (get-in db [:schnaq :statements focus-id]))))

;; ----------------------------------------------------------------------------

(defn derive-view []
  (let [current-discussion @(rf/subscribe [:schnaq/selected])]
    [pages/with-discussion-header
     {:page/heading (:discussion/title current-discussion)}
     [start-view]]))

(defn view []
  [derive-view])
