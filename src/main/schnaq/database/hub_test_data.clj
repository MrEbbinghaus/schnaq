(ns schnaq.database.hub-test-data)

(def ^:private hub-schnaqs
  [{:discussion/title "Hub Discussion"
    :discussion/share-hash "public-share-hash"
    :discussion/edit-hash "secret-public-hash"
    :discussion/author "user/hub-tester"
    :discussion/created-at #inst "2020-04-20"
    :discussion/states [:discussion.state/public]}
   {:discussion/title "Another Hub Discussion"
    :discussion/share-hash "public-share-hash-hubby"
    :discussion/edit-hash "secret-public-hash-hubby"
    :discussion/author "user/hub-tester"
    :discussion/created-at #inst "2020-03-16"
    :discussion/states [:discussion.state/public]}])

(def hub-test-data
  [{:hub/keycloak-name "test-keycloak"
    :hub/name "YouHub"
    :hub/schnaqs hub-schnaqs
    :hub/created-at #inst "2021-03-04"}
   {:hub/keycloak-name "some-empty-hub"
    :hub/name "Phub"
    :hub/created-at #inst "2020-04-03"}

   {:db/id "user/hub-tester"
    :user/nickname "Hub Tester"}])
