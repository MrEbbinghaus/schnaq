(ns schnaq.database.question-box
  (:require [clojure.spec.alpha :as s]
            [com.fulcrologic.guardrails.core :refer [>defn => ?]]
            [schnaq.database.main :as db]
            [schnaq.database.patterns :as patterns]
            [schnaq.database.specs :as specs]
            [schnaq.validator :as validators]
            [taoensso.timbre :as log]))

(>defn create-qa-box!
  "Create an empty question box with an optional label if passed."
  ([share-hash]
   [:discussion/share-hash => ::specs/qa-box]
   (create-qa-box! share-hash true nil))
  ([share-hash visible?]
   [:discussion/share-hash :qa-box/visible => ::specs/qa-box]
   (create-qa-box! share-hash visible? nil))
  ([share-hash visible? label]
   [:discussion/share-hash :qa-box/visible :qa-box/label => ::specs/qa-box]
   (let [entity-id "new-qa-box"
         prepared-tx [[:db/add entity-id :qa-box/visible visible?]
                      [:db/add [:discussion/share-hash share-hash] :discussion/qa-boxes entity-id]]]
     (db/transact-and-pull-temp (if label
                                  (conj prepared-tx [:db/add entity-id :qa-box/label label])
                                  prepared-tx)
                                entity-id
                                patterns/qa-box))))

(>defn qa-box-id-matches-hash?
  "Check whether a qa-box-id matches a share-hash."
  [qa-box-id share-hash]
  [:db/id :discussion/share-hash => boolean?]
  (let [real-share-hash (-> qa-box-id
                            (db/fast-pull '[{:discussion/_qa-boxes [:discussion/share-hash]}])
                            (get-in [:discussion/_qa-boxes :discussion/share-hash]))]
    (= share-hash real-share-hash)))

(>defn question-id-plausible?
  "Check whether a question id matches with a qa-box-id and share-hash."
  [question-id qa-box-id share-hash]
  [:db/id :db/id :discussion/share-hash => boolean?]
  (let [real-qa-box-id (-> question-id
                           (db/fast-pull '[:qa-box/_questions])
                           (get-in [:qa-box/_questions :db/id]))
        real-share-hash (-> qa-box-id
                            (db/fast-pull '[{:discussion/_qa-boxes [:discussion/share-hash]}])
                            (get-in [:discussion/_qa-boxes :discussion/share-hash]))]
    (and (= qa-box-id real-qa-box-id)
         (= share-hash real-share-hash))))

(>defn qa-box-moderator?
  "Check whether a user is moderator of a qa-box."
  [user-id qa-box-id]
  [:db/id :db/id => boolean?]
  (let [share-hash (-> qa-box-id
                       (db/fast-pull '[{:discussion/_qa-boxes [:discussion/share-hash]}])
                       (get-in [:discussion/_qa-boxes :discussion/share-hash]))]
    (validators/user-moderator? share-hash user-id)))

(>defn delete-qa-box!
  "Delete a question box by its entity id."
  [entity-id]
  [:db/id => future?]
  (db/transact [[:db/retractEntity entity-id]]))

(>defn update-qa-box
  "Change visibility and label of the qa-box."
  ([entity-id visible?]
   [:db/id :qa-box/visible => (? ::specs/qa-box)]
   (update-qa-box entity-id visible? nil))
  ([entity-id visible? label]
   [:db/id :qa-box/visible (? :qa-box/label) => (? ::specs/qa-box)]
   (let [prepared-tx [[:db/add entity-id :qa-box/visible visible?]]
         tx @(db/transact (if label
                            (conj prepared-tx [:db/add entity-id :qa-box/label label])
                            prepared-tx))]
     (->> tx
          :db-after
          (db/fast-pull entity-id patterns/qa-box)))))


(>defn add-question
  "Adds a single new question to the question box."
  [qa-box-id question]
  [:db/id ::specs/non-blank-string => (? :qa-box/question)]
  (when (not-empty question)
    (let [question-id "new-question"]
      (db/transact-and-pull-temp [[:db/add question-id :qa-box.question/value question]
                                  [:db/add qa-box-id :qa-box/questions question-id]]
                                 question-id
                                 patterns/question))))

(>defn upvote-question
  "Add an upvote to a question. Return a truthy value, when transaction was successful."
  [question-id]
  [:db/id => boolean?]
  (map?
   (db/increment-number question-id :qa-box.question/upvotes)))

(>defn mark-question
  "Mark a question as answered or unanswered. If no param besides question-id is passed, it is marked as answered."
  ([question-id]
   [:db/id => (? :qa-box/question)]
   (mark-question question-id true))
  ([question-id answered?]
   [:db/id :qa-box.question/answered => (? :qa-box/question)]
   (db/transact-and-pull [[:db/add question-id :qa-box.question/answered answered?]]
                          question-id
                          patterns/question)))

(>defn delete-question
  "Delete a question."
  [question-id]
  [:db/id => future?]
  (db/transact [[:db/retractEntity question-id]]))

(>defn qa-boxes-for-share-hash
  "Get all qa-boxes for a certain share-hash."
  [share-hash with-invisible?]
  [:discussion/share-hash boolean? => (s/coll-of ::specs/qa-box)]
  (let [all-qa-boxes (:discussion/qa-boxes
                      (db/fast-pull [:discussion/share-hash share-hash] [{:discussion/qa-boxes patterns/qa-box}]))]
    (log/debug "Retrieving qa-boxes for share-hash" share-hash "— Including invisible?" with-invisible?)
    (if with-invisible?
      all-qa-boxes
      (remove #(not (:qa-box/visible %)) all-qa-boxes))))
