(ns schnaq.interface.notification.events
  (:require [clojure.set :refer [union]]
            [re-frame.core :as rf]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.localstorage :refer [from-localstorage]]))

(rf/reg-event-db
 :notification/set-visited-statements
 (fn [db [_ discussion-hash statement premises]]
   (let [visited (set (conj (map :db/id premises) (:db/id statement)))]
     (update-in db [:visited :statement-ids discussion-hash] #(set (concat %1 %2)) visited))))

(rf/reg-event-db
 :visited.save-statement-ids/store-hashes-from-localstorage
 (fn [db _]
   ;; Without the or a nil can be written for fresh users, which breaks the default getter later on
   (assoc-in db [:visited :statement-ids] (or (from-localstorage :discussion/visited-statement-ids)
                                              {}))))

(rf/reg-event-fx
 :visited.statement-ids/to-localstorage-and-merge-with-app-db
 (fn [{:keys [db]} [_]]
   (let [statement-ids (get-in db [:visited :statement-ids])
         visited-statement-ds (merge-with union (from-localstorage :discussion/statement-ids) statement-ids)]
     {:fx [[:localstorage/assoc [:discussion/visited-statement-ids visited-statement-ds]]
           [:dispatch [:visited.save-statement-ids/store-hashes-from-localstorage]]]})))

(rf/reg-event-fx
 :visited.statement-ids/send-seen-statements-to-backend
 (fn [{:keys [db]} _]
   (when (get-in db [:user :authenticated?])
     (let [share-hash (get-in db [:schnaq :selected :discussion/share-hash])
           statement-ids (get-in db [:visited :statement-ids share-hash] #{})]
       {:fx [(http/xhrio-request
              db :put "/discussion/statements/update-seen"
              [:no-op]
              {:share-hash share-hash
               :seen-statement-ids statement-ids})]}))))

(rf/reg-event-db
 :no-op
 (fn [db _]
   db))
