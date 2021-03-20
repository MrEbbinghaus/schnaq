(ns schnaq.database.hub
  (:require [clojure.spec.alpha :as s]
            [ghostwheel.core :refer [>defn ? >defn-]]
            [schnaq.meeting.database :refer [transact new-connection query] :as main-db]
            [schnaq.meeting.specs :as specs]
            [taoensso.timbre :as log]))

(def ^:private hub-pattern
  [:db/id
   :hub/name
   {:hub/schnaqs [:discussion/title]}])

(>defn create-hub
  [hub-name]
  [:hub/name :ret ::specs/hub]
  (let [new-hub (get-in
                  (transact [{:db/id "temp"
                              :hub/name hub-name}])
                  [:tempids "temp"])]
    (log/info "Created hub" new-hub)
    (main-db/fast-pull new-hub hub-pattern)))

(>defn add-discussions-to-hub
  [hub-id discussion-ids]
  [:db/id (s/coll-of :db/id) :ret ::specs/hub]
  (transact (mapv #(vector :db/add hub-id :hub/schnaqs %) discussion-ids))
  (log/info "Added schnaqs with ids" discussion-ids "to hub" hub-id)
  (main-db/fast-pull hub-id hub-pattern))
