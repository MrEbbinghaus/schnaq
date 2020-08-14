(ns meetly.meeting.database-test
  (:require [clojure.test :refer [deftest testing use-fixtures is]]
            [meetly.test.toolbelt :as meetly-toolbelt]
            [dialog.discussion.database :as ddb]
            [meetly.meeting.database :as database]))

(use-fixtures :each meetly-toolbelt/init-test-delete-db-fixture)
(use-fixtures :once meetly-toolbelt/clean-database-fixture)

(defn- any-meeting-id
  []
  (database/add-meeting {:meeting/title "Bla"
                         :meeting/start-date (database/now)
                         :meeting/end-date (database/now)
                         :meeting/share-hash "aklsuzd98-234da-123d"
                         :meeting/author (database/add-user-if-not-exists "Wegi")}))

(deftest up-and-downvotes-test
  (testing "Tests whether setting up and downvotes works properly."
    (let [cat-or-dog (:db/id (first (ddb/all-discussions-by-title "Cat or Dog?")))
          some-statements (map #(-> % :argument/premises first :db/id)
                               (ddb/all-arguments-for-discussion cat-or-dog))
          author-1 "Test-1"
          author-2 "Test-2"]
      (database/add-user-if-not-exists author-1)
      (database/add-user-if-not-exists author-2)
      (database/upvote-statement! (first some-statements) author-1)
      (database/downvote-statement! (second some-statements) author-1)
      (database/upvote-statement! (first some-statements) author-2)
      (is (database/did-user-upvote-statement (first some-statements) author-1))
      (is (database/did-user-downvote-statement (second some-statements) author-1))
      (is (= 2 (database/upvotes-for-statement (first some-statements))))
      (is (= 1 (database/downvotes-for-statement (second some-statements))))
      (is (= 0 (database/downvotes-for-statement (first some-statements))))
      ;; No up- and downvote for the same statement by the same user!
      (database/downvote-statement! (first some-statements) author-1)
      (is (= 1 (database/upvotes-for-statement (first some-statements))))
      (is (= 1 (database/downvotes-for-statement (first some-statements))))
      ;; Remove the up and downvotes now
      (database/remove-downvote! (first some-statements) author-1)
      (database/remove-upvote! (first some-statements) author-2)
      (is (= 0 (database/upvotes-for-statement (first some-statements))))
      (is (= 0 (database/downvotes-for-statement (first some-statements)))))))

(deftest valid-statement-id-and-meeting?-test
  (testing "Test the function that checks whether a statement belongs to a certain meeting."
    (let [meeting (database/add-meeting {:meeting/title "test-meet"
                                         :meeting/description "whatever"
                                         :meeting/start-date (database/now)
                                         :meeting/end-date (database/now)
                                         :meeting/share-hash "Wegi-ist-der-schönste"
                                         :meeting/author (database/add-user-if-not-exists "Wegi")})
          agenda (database/add-agenda-point "Hi" "Beschreibung" meeting)
          discussion (:db/id (:agenda/discussion (database/agenda agenda)))
          _ (ddb/add-new-starting-argument! discussion "Christian" "this is sparta" ["foo" "bar" "baz"])
          argument (first (ddb/starting-arguments-by-discussion discussion))
          conclusion-id (:db/id (:argument/conclusion argument))
          premise-id (:db/id (first (:argument/premises argument)))]
      (is (database/check-valid-statement-id-and-meeting conclusion-id "Wegi-ist-der-schönste"))
      (is (database/check-valid-statement-id-and-meeting premise-id "Wegi-ist-der-schönste")))))

(deftest clean-db-vals-test
  (testing "Test whether nil values are properly cleaned from a map."
    (let [no-change-map {:foo :bar
                         :baz :bam}
          time-map {:bar (database/now)}]
      (is (= no-change-map (@#'database/clean-db-vals no-change-map)))
      (is (= 2 (count (@#'database/clean-db-vals (merge no-change-map {:unwished-for nil})))))
      (is (= {} (@#'database/clean-db-vals {})))
      (is (= {} (@#'database/clean-db-vals {:foo ""})))
      (is (= time-map (@#'database/clean-db-vals time-map))))))

(deftest add-meeting-test
  (testing "Test whether meetings are properly added"
    (let [minimal-meeting {:meeting/title "Bla"
                           :meeting/start-date (database/now)
                           :meeting/end-date (database/now)
                           :meeting/share-hash "aklsuzd98-234da-123d"
                           :meeting/author (database/add-user-if-not-exists "Wegi")}]
      (is (number? (database/add-meeting minimal-meeting)))
      (is (number? (database/add-meeting (assoc minimal-meeting :meeting/description "some description"))))
      (is (nil? (database/add-meeting (assoc minimal-meeting :meeting/description 123)))))))

(deftest add-agenda-point-test
  (testing "Check whether agendas are added correctly"
    (let [some-meeting (any-meeting-id)]
      (is (number? (database/add-agenda-point "Alles gut" "hier" some-meeting)))
      (is (nil? (database/add-agenda-point 123 nil some-meeting)))
      (is (nil? (database/add-agenda-point "Meeting-kaputt" nil "was ist das?")))
      (is (number? (database/add-agenda-point "Kaputte description wird ignoriert" 123 some-meeting))))))

(deftest add-user-test
  (testing "Check for correct user-addition"
    (is (number? (database/add-user "Gib ihm!")))
    (is (nil? (database/add-user :nono-string)))))

(deftest add-feedback-test
  (testing "Valid feedbacks should be stored."
    (let [feedback {:feedback/description "Very good stuff 👍 Would use again"
                    :feedback/contact-mail "christian@dialogo.io"
                    :feedback/has-image? false}]
      (is (zero? (count (database/all-feedbacks))))
      (is (number? (database/add-feedback! feedback)))
      (is (= 1 (count (database/all-feedbacks)))))))

(deftest add-user-if-not-exists-test
  (testing "Test the function to add a new user if they do not exist."
    (let [new-user (database/add-user-if-not-exists "For Sure a new User that does Not exist")]
      (is (int? new-user))
      (is (= new-user (database/add-user-if-not-exists "FOR SURE a new User that does Not exist"))))))

(deftest user-by-nickname-test
  (testing "Tests whether the user is correctly found, disregarding case."
    (let [wegi (database/user-by-nickname "Wegi")]
      (is (int? wegi))
      (is (= wegi (database/user-by-nickname "WeGi")
             (database/user-by-nickname "wegi")
             (database/user-by-nickname "wegI"))))))

(deftest canonical-username-test
  (testing "Test whether the canonical username is returned."
    (is (= "Wegi" (database/canonical-username "WEGI")
           (database/canonical-username "WeGi")))
    (is (= "Der Schredder" (database/canonical-username "DER schredder")))))


;; Tests for the analytics part

(deftest number-of-meetings-test
  (testing "Return the correct number of meetings"
    (is (= 1 (database/number-of-meetings)))
    (any-meeting-id)                                        ;; Ads any new meeting
    (is (= 2 (database/number-of-meetings)))
    (is (zero? (database/number-of-meetings (database/now))))))

(deftest number-of-usernames-test
  (testing "Return the correct number of usernames"
    ;; There are at least the 4 users from the test-set
    (is (= 4 (database/number-of-usernames)))
    (database/add-user-if-not-exists "Some-Testdude")
    (is (= 5 (database/number-of-usernames)))
    (is (zero? (database/number-of-meetings (database/now))))))

(deftest number-of-statements-test
  (testing "Return the correct number of statements."
    (is (= 27 (database/number-of-statements)))
    (is (zero? (database/number-of-statements (database/now))))))

(deftest average-number-of-agendas-test
  (testing "Test whether the average number of agendas fits."
    (is (= 2 (database/average-number-of-agendas)))
    (any-meeting-id)
    (is (= 1 (database/average-number-of-agendas)))))

(deftest number-of-active-users-test
  (testing "Test whether the active users are returned correctly."
    (let [cat-or-dog-id (:db/id (first (ddb/all-discussions-by-title "Cat or Dog?")))]
      (is (= 4 (database/number-of-active-users)))
      (database/add-user-if-not-exists "wooooggler")
      (is (= 4 (database/number-of-active-users)))
      (@#'database/transact
        [(@#'ddb/prepare-new-argument cat-or-dog-id "wooooggler" "Alles doof" ["weil alles doof war"])])
      (is (= 5 (database/number-of-active-users))))))

(deftest statement-length-stats-test
  (testing "Testing the function that returns lengths of statements statistics"
    (let [stats (database/statement-length-stats)]
      (is (< (:min stats) (:max stats)))
      (is (< (:min stats) (:median stats)))
      (is (> (:max stats) (:median stats)))
      (is (> (:max stats) (:average stats)))
      (is float? (:average stats)))))

(deftest argument-type-stats-test
  (testing "Statistics about argument types should be working."
    (let [stats (database/argument-type-stats)]
      (is (= 6 (:attacks stats)))
      (is (= 9 (:supports stats)))
      (is (= 8 (:undercuts stats))))))

(deftest update-agenda-test
  (testing "Whether the new agenda is added correctly"
    (let [meeting-id (any-meeting-id)
          meeting (database/meeting-private-data meeting-id)
          agenda-id (database/add-agenda-point "Hallo i bims nicht" "Lolkasse Lolberg" meeting-id)
          agenda {:db/id agenda-id
                  :agenda/title "Hallo i bims"
                  :agenda/description "Sparkasse Marketing"
                  :agenda/meeting meeting-id
                  :agenda/discussion (:db/id (first (ddb/all-discussions-by-title "Cat or Dog?")))}
          old-agenda (first (database/agendas-by-meeting-hash (:meeting/share-hash meeting)))
          _ (database/update-agenda agenda)
          new-agenda (first (database/agendas-by-meeting-hash (:meeting/share-hash meeting)))
          ]
      (is (= "Hallo i bims nicht" (:agenda/title old-agenda)))
      (is (= "Lolkasse Lolberg" (:agenda/description old-agenda)))
      (is (= "Hallo i bims" (:agenda/title new-agenda)))
      (is (= "Sparkasse Marketing" (:agenda/description new-agenda))))))

(comment
  (any-meeting-id)
  (database/meeting-private-data 96757023244455)
  (database/add-agenda-point "Hallo i bims nicht" "Lolkasse Lolberg" 96757023244455)
  (first (database/agendas-by-meeting-hash "aklsuzd98-234da-123d"))
  (database/update-agenda {:db/id 87960930222249
                           :agenda/title "Hallo i bims"
                           :agenda/description "Sparkasse Marketing"
                           :agenda/meeting 96757023244455
                           :agenda/discussion (:db/id (first (ddb/all-discussions-by-title "Cat or Dog?")))})
  )