(ns schnaq.api.schnaq-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is testing use-fixtures]]
            [muuntaja.core :as m]
            [schnaq.api :as api]
            [schnaq.database.specs :as specs]
            [schnaq.test.toolbelt :as schnaq-toolbelt]))

(use-fixtures :each schnaq-toolbelt/init-test-delete-db-fixture)
(use-fixtures :once schnaq-toolbelt/clean-database-fixture)

(defn- schnaq-by-hash-as-admin-request [share-hash edit-hash]
  (-> {:request-method :post :uri (:path (api/route-by-name :api.schnaq/by-hash-as-admin))
       :headers {"accept" "application/edn"}
       :body-params {:share-hash share-hash
                     :edit-hash edit-hash}}
      api/app))

(deftest schnaq-by-hash-as-admin-test
  (let [share-hash "graph-hash"
        edit-hash "graph-edit-hash"]
    (testing "Valid hashes are ok."
      (is (= 200 (:status (schnaq-by-hash-as-admin-request share-hash edit-hash)))))
    (testing "Wrong hashes are forbidden."
      (is (= 403 (:status (schnaq-by-hash-as-admin-request share-hash "👾"))))
      (is (= 403 (:status (schnaq-by-hash-as-admin-request "razupaltuff" edit-hash)))))))

(defn- schnaq-by-hashes-request [share-hashes]
  (-> {:request-method :get :uri (:path (api/route-by-name :api.schnaqs/by-hashes))
       :headers {"accept" "application/edn"}
       :query-params {:share-hashes share-hashes}}
      api/app))

(deftest schnaqs-by-hashes-test
  (let [share-hash1 "cat-dog-hash"
        share-hash2 "graph-hash"]
    (testing "No hash provided is a bad request."
      (is (= 400 (:status (schnaq-by-hashes-request nil)))))
    (testing "Invalid hash returns no discussion."
      (is (= 200 (:status (schnaq-by-hashes-request "something-non-existent"))))
      (is (empty? (:schnaqs (m/decode-response-body (schnaq-by-hashes-request "something-non-existent"))))))
    (testing "Querying by a single valid hash returns a discussion."
      (let [api-call (schnaq-by-hashes-request share-hash1)]
        (is (= 200 (:status api-call)))
        (is (= 1 (count (:schnaqs (m/decode-response-body api-call)))))
        (is (s/valid? ::specs/discussion (first (:schnaqs (m/decode-response-body api-call)))))))
    (testing "A valid hash packed into a collection should also work."
      (let [api-call (schnaq-by-hashes-request [share-hash1])]
        (is (= 200 (:status api-call)))
        (is (= 1 (count (:schnaqs (m/decode-response-body api-call)))))
        (is (s/valid? ::specs/discussion (first (:schnaqs (m/decode-response-body api-call)))))))
    (testing "Asking for multiple valid hashes, returns a list of valid discussions."
      (let [api-call (schnaq-by-hashes-request [share-hash1 share-hash2])]
        (is (= 200 (:status api-call)))
        (is (= 2 (count (:schnaqs (m/decode-response-body api-call)))))
        (is (every? true?
                    (map (partial s/valid? ::specs/discussion)
                         (:schnaqs (m/decode-response-body api-call)))))))))