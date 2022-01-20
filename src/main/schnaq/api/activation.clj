(ns schnaq.api.activation
  (:require [ring.util.http-response :refer [ok]]
            [schnaq.api.dto-specs :as dto]
            [schnaq.api.toolbelt :as at]
            [schnaq.database.activation :as activation-db]
            [taoensso.timbre :as log]))

(defn- start-activation
  "Endpoint to start an activation for a discussion.
   Only creates a new one if none exists."
  [{:keys [parameters]}]
  (let [{:keys [share-hash]} (:body parameters)]
    (log/info "Starting activation for " share-hash)
    (ok {:activation (activation-db/start-activation! share-hash)})))

(defn- increase-activation-counter
  "Endpoint to increase activation counter by one."
  [{:keys [parameters]}]
  (let [{:keys [share-hash]} (:body parameters)]
    (log/info "Increase activation counter for " share-hash)
    (ok {:activation (activation-db/increase-activation! share-hash)})))

(defn- reset-activation
  "Endpoint to reset activation counter."
  [{:keys [parameters]}]
  (let [{:keys [share-hash]} (:body parameters)]
    (log/info "Reset activation counter for " share-hash)
    (ok {:activation (activation-db/reset-activation-by-share-hash! share-hash)})))

(def activation-routes
  [["" {:swagger {:tags ["activation"]}}
    ["/activation"
     ["" {:put start-activation
          :description (at/get-doc #'start-activation)
          :middleware [:user/authenticated?
                       :user/beta-tester?
                       :discussion/valid-credentials?]
          :name :activation/start
          :parameters {:body {:share-hash :discussion/share-hash
                              :edit-hash :discussion/edit-hash}}
          :responses {200 {:body {:activation ::dto/activation}}
                      400 at/response-error-body}}]
     ["/increase" {:put increase-activation-counter
                   :description (at/get-doc #'increase-activation-counter)
                   :name :activation/increase
                   :parameters {:body {:share-hash :discussion/share-hash}}
                   :responses {200 {:body {:activation ::dto/activation}}
                               400 at/response-error-body}}]
     ["/reset" {:put reset-activation
                :description (at/get-doc #'reset-activation)
                :middleware [:user/authenticated?
                             :user/beta-tester?
                             :discussion/valid-credentials?]
                :name :activation/reset
                :parameters {:body {:share-hash :discussion/share-hash
                                    :edit-hash :discussion/edit-hash}}
                :responses {200 {:body {:activation ::dto/activation}}
                            400 at/response-error-body}}]]]])
