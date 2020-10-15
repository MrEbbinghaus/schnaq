(ns schnaq.discussion-test
  (:require [clojure.test :refer [use-fixtures is deftest testing are]]
            [dialog.discussion.database :as ddb]
            [schnaq.discussion :as discussion]
            [schnaq.meeting.database :as db]
            [schnaq.test.toolbelt :as schnaq-toolbelt]))

(use-fixtures :each schnaq-toolbelt/init-test-delete-db-fixture)
(use-fixtures :once schnaq-toolbelt/clean-database-fixture)

(deftest undercuts-for-root-test
  (testing "Test whether all undercut descendants are found in a sub-discussion."
    (let [undercuts-for-root @#'discussion/undercuts-for-root
          discussion-id (:db/id (first (ddb/all-discussions-by-title "Tapir oder Ameisenbär?")))
          starting-argument (first (ddb/starting-arguments-by-discussion discussion-id))
          root-statement (first (:argument/premises starting-argument))
          all-arguments (ddb/all-arguments-for-discussion discussion-id)
          matching-undercut (first (undercuts-for-root (:db/id root-statement) all-arguments))]
      (is (= "going for a walk with the dog every day is good for social interaction and physical exercise"
             (-> matching-undercut :argument/premises first :statement/content)))
      (is (= "Der miese Peter" (-> matching-undercut :argument/author :author/nickname)))
      (is (empty? (undercuts-for-root [] all-arguments))))))

(deftest direct-children-test
  (testing "Test whether all direct children are found."
    (let [direct-children @#'discussion/direct-children
          discussion-id (:db/id (first (ddb/all-discussions-by-title "Tapir oder Ameisenbär?")))
          root-id (:db/id (:argument/conclusion (first (ddb/starting-arguments-by-discussion discussion-id))))
          all-arguments (ddb/all-arguments-for-discussion discussion-id)
          children (direct-children root-id all-arguments)]
      (is (= 2 (count children)))
      (is (empty? (direct-children -1 all-arguments))))))

(deftest sub-discussion-information-test
  (testing "Test information regarding sub-discussions."
    (let [discussion-id (:db/id (first (ddb/all-discussions-by-title "Tapir oder Ameisenbär?")))
          arguments (ddb/all-arguments-for-discussion discussion-id)
          root-id (:db/id (:argument/conclusion (first (ddb/starting-arguments-by-discussion discussion-id))))
          infos (discussion/sub-discussion-information root-id arguments)
          author-names (into #{} (map :author/nickname (:authors infos)))]
      (is (= 3 (:sub-statements infos)))
      (is (contains? author-names "Der miese Peter"))
      (is (contains? author-names "Wegi"))
      (is (contains? author-names "Der Schredder")))))


(deftest nodes-for-agenda-test
  (testing "Validate data for graph nodes."
    (let [discussion-id (:db/id (first (ddb/all-discussions-by-title "Tapir oder Ameisenbär?")))
          share-hash "89eh32hoas-2983ud"
          statements (db/all-statements-for-discussion discussion-id)
          contents (set (map :content statements))
          starting-arguments (ddb/starting-arguments-by-discussion discussion-id)
          nodes (discussion/nodes-for-agenda statements starting-arguments discussion-id share-hash)
          statement-nodes (filter #(= "statement" (:type %)) nodes)]
      (testing "Nodes contains agenda as data thus containing one more element than the statements."
        (is (= (count statements) (dec (count nodes)))))
      (testing "Only one agenda point."
        (is (= 1 (count (filter #(= :agenda (:type %)) nodes)))))
      (testing (str "Check if all content from statements is present in nodes.")
        (is (= (count statement-nodes) (count (filter #(contents (:content %)) statement-nodes))))))))

(deftest links-for-agenda-test
  (testing "Validate data for graph links"
    (let [discussion-id (:db/id (first (ddb/all-discussions-by-title "Wetter Graph")))
          statements (db/all-statements-for-discussion discussion-id)
          starting-arguments (ddb/starting-arguments-by-discussion discussion-id)
          links (discussion/links-for-agenda statements starting-arguments discussion-id)]
      (testing "Links contains agenda as data thus containing one more element than the statements."
        (is (= (count statements) (count links)))))))

(deftest update-controversy-map-test
  (testing "Update of a controversy-map"
    (let [edge-1 {:to 123 :type :argument.type/support}
          edge-2 {:to 1234 :type :argument.type/attack}
          edge-3 {:to 1235 :type :argument.type/starting}]
      (are [x y]
        (= x (@#'discussion/update-controversy-map {} y))
        {123 {:positive 1}} edge-1
        {1234 {:negative 1}} edge-2
        {} edge-3)
      (is (= {123 {:positive 2}} (@#'discussion/update-controversy-map {123 {:positive 1}}
                                   {:to 123 :type :argument.type/support}))))))

(deftest single-controversy-val-test
  (testing "Calculate a single controversy-value"
    (let [con-vals-1 {:positive 1}
          con-vals-2 {:negative 1}
          con-vals-3 {:positive 1
                      :negative 1}]
      (are [x y]
        (= x (@#'discussion/single-controversy-val y))
        0 con-vals-1
        100.0 con-vals-2
        50.0 con-vals-3))))

(deftest calculate-controversy-test
  (testing "Test the creation of a controversy-map"
    (let [edges [{:to 123 :type :argument.type/support} {:to 1234 :type :argument.type/support}
                 {:to 1234 :type :argument.type/attack} {:to 1235 :type :argument.type/starting}]]
      (is (= {123 0
              1234 50.0}
             (discussion/calculate-controversy edges))))))
