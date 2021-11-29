(ns schnaq.interface.integrations.wetog.events
  (:require [re-frame.core :as rf]
            [schnaq.interface.utils.js-wrapper :as jsw]))

(rf/reg-event-fx
 :wetog/initialize-from-data
 (fn [{:keys [db]} _]
   (let [integration-div "schnaq-integration"
         share-hash (jsw/data-attribute integration-div "shareHash")
         display-name (jsw/data-attribute integration-div "displayName")
         jwt (jsw/data-attribute integration-div "jwt")
         authenticated? (string? (and jwt (not-empty jwt)))]
     {:db (-> db
              (assoc-in [:user :names :display] display-name)
              (assoc-in [:user :jwt] jwt)
              (assoc-in [:schnaq :selected :discussion/share-hash] share-hash))
      :fx [[:dispatch [:user/authenticated! authenticated?]]
           [:dispatch [:user/register authenticated?]]
           [:dispatch [:schnaq/load-by-share-hash share-hash]]]})))

(rf/reg-event-fx
 :initialize/wetog-integration
 (fn [_ _]
   {:fx [[:dispatch [:how-to-visibility/from-localstorage-to-app-db]]
         [:dispatch [:visited.save-statement-nums/store-hashes-from-localstorage]]
         [:dispatch [:visited.save-statement-ids/store-hashes-from-localstorage]]
         [:dispatch [:schnaq.discussion-secrets/load-from-localstorage]]]}))
