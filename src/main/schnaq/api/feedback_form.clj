(ns schnaq.api.feedback-form
  (:require
   [com.fulcrologic.guardrails.core :refer [=> >defn-]]
   [ring.util.http-response :refer [ok bad-request]]
   [schnaq.api.toolbelt :as at]
   [schnaq.database.feedback-form :as feedback-db]))

(>defn- create-form
  "Create a new feedback-form based on the items the user sends. Does not check amount of items."
  [{:keys [parameters]}]
  [:ring/request => :ring/response]
  (let [{:keys [share-hash items]} (:body parameters)
        new-feedback-id (feedback-db/new-feedback-form! share-hash items)]
    (if new-feedback-id
      (ok {:feedback-form-id new-feedback-id})
      (bad-request (at/build-error-body :missing-items "Please provide items for the form")))))

(>defn- update-items
  "Set a new collection of items on a feedback form."
  [{:keys [parameters]}]
  [:ring/request => :ring/response]
  (let [{:keys [share-hash items visible]} (:body parameters)
        visible (true? visible) ;; Coerce into boolean
        new-feedback-id (feedback-db/update-feedback-form-items! share-hash items visible)]
    (if new-feedback-id
      (ok {:updated-form? true})
      (bad-request (at/build-error-body :malformed-update "No feedback created or empty items.")))))

(>defn- delete-feedback
  "Deletes the feedback-form including items and answers."
  [{:keys [parameters]}]
  [:ring/request => :ring/response]
  (let [{:keys [share-hash]} (:body parameters)
        deleted? (true? (feedback-db/delete-feedback! share-hash))]
    (ok {:deleted? deleted?})))

(def feedback-form-routes
  ["/feedback"
   ["/form"
    ["" {:post {:handler create-form
                :description (at/get-doc #'create-form)
                :responses {200 {:body {:feedback-form-id :db/id}}
                            400 at/response-error-body}
                :parameters {:body {:share-hash :discussion/share-hash
                                    :items :feedback/items}}}
         :put {:handler update-items
               :description (at/get-doc #'update-items)
               :responses {200 {:body {:updated-form? boolean?}}
                           400 at/response-error-body}
               :parameters {:body {:share-hash :discussion/share-hash
                                   :items :feedback/items
                                   :visible boolean?}}}
         :delete {:handler delete-feedback
                  :description (at/get-doc #'delete-feedback)
                  :parameters {:body {:share-hash :discussion/share-hash}}
                  :responses {200 {:body {:deleted? boolean?}}}}
         :name :api.discussion.feedback/form
         :middleware [:discussion/valid-share-hash? :discussion/user-moderator?]}]]])
