(ns schnaq.interface.db)


(def default-db
  {:meetings {:all [{:db/id "Fake"
                     :meeting/title "Jour Fixé every day"
                     :meeting/description "Whatever"
                     :meeting/share-hash "test-123-456"
                     :meeting/author "Wegi"}]}
   :current-page :home
   :controls {:username-input {:show? false}}
   :history {:full-context []}
   :votes {:up {}
           :down {}}
   :display-triggers {:meeting-link-success false}
   :edit-meeting {:meeting {}
                  :agendas []
                  :delete-agendas #{}}})