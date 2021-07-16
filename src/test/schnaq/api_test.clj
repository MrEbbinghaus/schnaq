(ns schnaq.api-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest testing is are use-fixtures]]
            [ring.mock.request :as mock]
            [schnaq.api :as api]
            [schnaq.database.discussion :as discussion-db]
            [schnaq.database.main :as db]
            [schnaq.test.toolbelt :as schnaq-toolbelt]))

(use-fixtures :each schnaq-toolbelt/init-test-delete-db-fixture)
(use-fixtures :once schnaq-toolbelt/clean-database-fixture)

(deftest check-credentials-test
  (testing "Check if credentials are verified correctly."
    (let [credential-request (fn [share-hash edit-hash]
                               {:request-method :post :uri "/credentials/validate"
                                :body-params {:share-hash share-hash :edit-hash edit-hash}})
          share-hash "simple-hash"
          edit-hash "simple-hash-secret"]
      (is (= 200 (-> (credential-request share-hash edit-hash) api/app :status)))
      (is (= 403 (-> (credential-request "invalid" edit-hash) api/app :status)))
      (is (= 403 (-> (credential-request share-hash "invalid") api/app :status)))
      (is (= 403 (-> (credential-request "invalid" "invalid") api/app :status))))))

(deftest graph-data-for-agenda-test
  (testing "Check if graph data is correct"
    (let [graph-data-for-agenda @#'api/graph-data-for-agenda
          graph-request (fn [share-hash] (graph-data-for-agenda {:parameters {:query {:share-hash share-hash}}}))
          share-hash "cat-dog-hash"
          valid-response (graph-request "cat-dog-hash")
          bad-response (graph-request "qweoiuqwe")]
      (testing "valid request"
        (is (= 200 (:status valid-response)))
        (is (contains? (-> valid-response :body) :graph))
        (is (contains? (-> valid-response :body :graph) :nodes))
        (is (contains? (-> valid-response :body :graph) :edges))
        (is (not (nil? (-> valid-response :body :graph :nodes))))
        (is (not (nil? (-> valid-response :body :graph :edges)))))
      (testing "bad request"
        (is (= 400 (:status bad-response))))
      (testing "Check with complete app"
        (is (= 200 (:status (api/app {:request-method :get :uri "/discussion/graph"
                                      :query-params {:share-hash share-hash}}))))
        (is (= 400 (:status (api/app {:request-method :get :uri "/discussion/graph"
                                      :query-params {:share-hash "bad-hash"}}))))))))

(deftest cors-origin-tests
  (let [test-regex (partial re-matches api/allowed-origin)]
    (testing "Valid origins for production mode."
      (are [origin] (not (nil? (test-regex origin)))
                    "api.schnaq.com"
                    "schnaq.com"
                    "schnaq.de"
                    "www.schnaq.de"
                    "www.schnaq.com"
                    "https://api.schnaq.com"
                    "https://schnaq.com"
                    "https://schnaq.com/?kangaroo=rocks"
                    "api.staging.schnaq.com"
                    "staging.schnaq.com"
                    "https://api.staging.schnaq.com"
                    "https://staging.schnaq.com"
                    "https://staging.schnaq.com/meetings/create"))
    (testing "Invalid origins."
      (are [origin] (nil? (test-regex origin))
                    "localhost"
                    "penguin.books"
                    "christian.rocks"
                    "schnaqqi.com"
                    "schnaq.dev"
                    "fakeschnaq.com"))))

(deftest edit-statement!-test
  (let [edit-statement! #'api/edit-statement!
        share-hash "simple-hash"
        keycloak-id "59456d4a-6950-47e8-88d8-a1a6a8de9276"
        statement (first (discussion-db/starting-statements share-hash))
        request #(-> (mock/request :put "/discussion/statement/edit")
                     (assoc-in [:identity :sub] %)
                     (assoc-in [:parameters :body :share-hash] share-hash)
                     (assoc-in [:parameters :body :statement-type] :statement.type/neutral)
                     (assoc-in [:parameters :body :statement-id] (:db/id statement))
                     (assoc-in [:parameters :body :new-content] "any-text"))]
    (testing "Only requests from valid author should be allowed."
      ;; The author is not the registered user, rest is fine
      (is (= 403 (:status (edit-statement! (request keycloak-id)))))
      ;; Make the author the user
      (db/transact [[:db/add (:db/id statement) :statement/author [:user.registered/keycloak-id keycloak-id]]])
      ;; Everything should be fine
      (is (= 200 (:status (edit-statement! (request keycloak-id)))))
      ;; Statement is deleted
      (db/transact [[:db/add (:db/id statement) :statement/deleted? true]])
      (is (= 400 (:status (edit-statement! (request keycloak-id)))))
      ;; Statement is fine but discussion is read-only
      (db/transact [[:db/add (:db/id statement) :statement/deleted? false]])
      (discussion-db/set-discussion-read-only share-hash)
      (is (= 400 (:status (edit-statement! (request keycloak-id))))))))
