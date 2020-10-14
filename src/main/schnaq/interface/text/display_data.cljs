(ns schnaq.interface.text.display-data)


(defn labels
  "Returns a label as String for a given identifier"
  [identifier]
  (identifier
    {
     ;; navbar labels
     :nav-startpage "Home"
     :nav-example "Examples"
     :nav-meeting "Alle schnaqs"
     :nav-meeting-create "Schnaq erstellen"
     :nav-meeting-agenda "Agenda erzeugen"

     ;; Startpage
     :startpage/heading "Nie wieder langwierige Meetings ohne Ziel!"
     :startpage/subheading "Schnaq strukturiert Meetings und bringt sie auf den Punkt"

     :startpage.heading-list/community "Meetingziele kollaborativ festlegen"
     :startpage.heading-list/exchange "Informationen gezielt austauschen"
     :startpage.heading-list/reports "Strukturiert Wissen festhalten"
     :startpage.under-construction/heading "Betreten der Baustelle erwünscht!"
     :startpage.under-construction/body "schnaq befindet sich in einer Beta-Phase und Feedback ist uns wichtig!"

     :startpage.button/create-schnaq "Jetzt einen schnaq starten!"

     :startpage.value-cards.discussion/title "Diskussionen führen"
     :startpage.value-cards.discussion/description "Es ist nicht einfach über das Internet miteinander zu Diskutieren ohne sich schnell verloren zu fühlen. Mit schnaq können Sie strukturierte Diskussionen führen, und dabei leicht den Überblick über die Argumente und konktroverse Themen behalten."
     :startpage.value-cards.discussion/alt-text "Ein Symbolbild einer Sprechblase"
     :startpage.value-cards.meetings/title "Meetings optimieren"
     :startpage.value-cards.meetings/description "Vor allem seitdem die Arbeitswelt verteilter geworden ist, hetzt man von einem (digitalen) Meeting zum anderen. Damit die Vor- und Nachbereitung nicht mehr zur Nebensache wird, gibt es schnaq. Sparen Sie Arbeitszeit und optimieren sie die Ergebnisse Ihrer Meetings."
     :startpage.value-cards.meetings/alt-text "Menschen in einem Meeting, eine Frau redet gerade"
     :startpage.value-cards.knowledge/title "Wissen generieren"
     :startpage.value-cards.knowledge/description "Oft fragt man sich ob ehemals getroffene Entscheidungen oder generiertes Wissen noch aktuell sind. Mit schnaq wird nicht nur das Ergebnis, sondern auch die Wissensgenerierung festgehalten. Damit lässt sich in der Zukunft leicht nachvollziehen weshalb welche Entscheidungen getroffen wurden, und ob Wissen noch zeitgemäß ist."
     :startpage.value-cards.knowledge/alt-text "Etliche Klebezettel auf einer Wand"
     :startpage.value-cards.button/text "Mehr erfahren"

     :startpage.usage/lead "Wofür kann ich schnaq verwenden?"

     :startpage.features/more-information "Mehr Informationen"

     :startpage.demo.request/title "Demo anfordern"
     :startpage.demo.request/body "Wenn Sie gerne sehen würden, wie man maximal gut schnaqqen kann, führen wir Ihnen Schnaq persönlich vor. Nutzen Sie dazu einfach den Button und wir werden uns schnellstmöglich bei Ihnen melden."
     :startpage.demo.request/button "Jetzt Demo anfordern!"
     :startpage.demo.request.modal.name/label "Ihr Name"
     :startpage.demo.request.modal.name/placeholder "Mein Name"
     :startpage.demo.request.modal.email/label "E-Mail Adresse"
     :startpage.demo.request.modal.email/placeholder "meine@email.de"
     :startpage.demo.request.modal.company/label "Name des Unternehmens"
     :startpage.demo.request.modal.company/placeholder "Firmenname, falls zutreffend"
     :startpage.demo.request.modal.phone/label "Telefon"
     :startpage.demo.request.modal.phone/placeholder "0 1234 56789"
     :startpage.demo.request.send.notification/title "Anfrage versendet!"
     :startpage.demo.request.send.notification/body "Es wird sich schnellstmöglich jemand bei Ihnen melden."
     :startpage.demo.request.send.notification/failed-title "Anfrage fehlgeschlagen!"
     :startpage.demo.request.send.notification/failed-body "Etwas ist schief gelaufen. Überprüfen Sie Ihre Eingaben und versuchen Sie es bitte erneut."

     :startpage.features.meeting-organisation/lead "Meetingplanung"
     :startpage.features.meeting-organisation/title "Gemeinsame Vorbereitung eines Meetings"
     :startpage.features.meeting-organisation/body "Binden Sie Ihre Mitarbeitenden mit in die Planung des Meetings ein! Aktivieren Sie so ungenutzte Ressourcen und erreichen Sie so eine höhere Zufriedenheit bei der Besprechung."
     :startpage.features.discussion/lead "Strukturierte Diskussionen"
     :startpage.features.discussion/title "Produktiver Austausch"
     :startpage.features.discussion/body "Durch strukturierten Meinungsaustausch können Entscheidungen und Ideen fundiert ausgetauscht und gebildet werden. Um genau das zu lösen, bieten wir eine dialogbasierte Diskussionslösung für Ihr Team. Bessere Vorbereitung durch vorherigen Austausch!"
     :startpage.features.graph/lead "Übersicht"
     :startpage.features.graph/title "Automatische Diskussionsaufbereitung"
     :startpage.features.graph/body "Sehen Sie die Argumente Ihrer Mitarbeitenden. Erkennen Sie Zusammenhänge, kontroverse Diskussionspunkte oder Probleme, auf denen Sie dann im nachfolgenden Meeting genau drauf eingehen können. Fokussieren Sie sich auf die wirklich zu besprechenden Punkte und reduzieren Sie so die Länge von Meetings."

     ;; Meeting Feature Page
     :feature.meetings/lead "Organisierte Meetings mit schnaq"
     :feature.meetings/title "Mitarbeiter:innen aktivieren, Zeit sparen"
     :feature.meetings/body "Schnaq lässt sich nutzen, um Meetings effizient vorzubereiten, alle Teilnehmer:innen während dem Meeting zu unterstützen und erlaubt eine Nachbereitung des Meetings. Damit werden alle Teilnehmer:innen aktiviert, Arbeitszeit eingespart und Ergebnisse effizienter erzielt."
     :feature.meetings.schedule/lead "Gezielte Vorbereitung"
     :feature.meetings.schedule/title "Agenda kollaborativ vorbereiten"
     :feature.meetings.schedule/body "Bereiten Sie eine erste Version der Agenda vor. Die Agenda und angehängte vorbereitende Materialien werden per E-Mail und Kalendereinladung an alle Teilnehmer:innen verteilt. Durch schnaq können alle Kolleg:innen Änderungsvorschläge und Ergänzungen für einzelne Agendapunkte vorschlagen, die von der Meetingersteller:in angenommen werden können."
     :feature.meetings.discuss/lead "Strittige Punkte klären"
     :feature.meetings.discuss/title "Diskutieren Sie unklare Punkte im Vorhinein"
     :feature.meetings.discuss/body "Bei Punkten, die nicht klar umrissen sind, bietet sich eine Online-Diskussion an. Diese kann man direkt über die vorgeschlagenen Agendapunkte erreichen und ad-hoc erledigen. Die Diskussion kann der Ausrichtung einer Agenda dienen, aber auch der Klärung von offenen Punkten, bei denen nicht klar ist, wie die Agenda gestaltet werden sollte. Dies geschieht strukturiert und asynchron, so dass alle Mitarbeiter:innen teilnehmen können. Das Ergebnis sind fokussierte Meetings."
     :feature.meetings.admin-center/lead "Accounts nicht notwendig"
     :feature.meetings.admin-center/title "Verwalten Sie schnaq ohne zusätzliche Accounts"
     :feature.meetings.admin-center/body "Schnaq lässt sich bequem durch gesicherte Links verwalten. Sie benötigen keine weiteren Accounts, sondern lediglich einen Browser. So können Sie von überall auf schnaq und Ihre Meetings zugreifen. Im Admin-Center können Sie Ihr Meeting administrieren und alle wichtigen Aktionen von überall ausführen."
     :feature.meetings/heading "Sparen Sie Arbeitszeit durch strukturierte Meetings"
     :feature.meetings/features-subheading "Schnaq für Meetings einsetzen"
     :feature.meetings/tbd-subheading "More to come..."
     :feature.meetings/tbd-lead "Werden Sie Early Adopter und genießen Sie folgende Features als erste, sobald diese verfügbar sind:"
     :feature.meetings.tbd/teams "Integration mit MS Teams und Slack"
     :feature.meetings.tbd/outlook "Outlook-Export"
     :feature.meetings.tbd/protocols "Kollaborative Protokollführung im Meeting"
     :feature.meetings.tbd/prereads "Dateianhänge / Prereads während der Agendaerstellung"
     :feature.meetings.tbd/assignments "Personenzuteilung für einzelne Agendapunkte"
     :feature.meetings.tbd/timeboxing "Timeboxing während des Meetings"
     :feature.meetings.tbd/task-tracking "Übertragung der Ergebnisse in Task-Tracker"
     :feature.meetings/feedback "Haben Sie weitere Wünsche? Kontaktieren Sie uns gerne über das Feedback-Formular auf der rechten Seite."

     :feature.discussions/lead "Strukturierte Diskussionen mit Kolleg:innen und Kunden"
     :feature.discussions/title "Strukturierte Diskussionen für strukturierte Ergebnisse"
     :feature.discussions/body "Mit schnaq können strukturierte Diskussionen online und ohne weitere Gerätschaften geführt werden. Wir haben Ergebnisse aus unserer langjährigen Forschung genutzt, um eine optimale Diskussionsform zu entwickeln, die strukturierte Diskussionen ermöglicht. Die Ergebnisse der Diskussionen lassen sich strukturiert und automatisch in graphischer Form aufbereiten und erlauben so die Nachvollziehbarkeit einer Diskussion auf einen Blick. Wertvolle Informationen für alle Entscheider:innen und deren Teams!"
     :feature.discussions/features-subheading "Schnaq als Diskussionsplattform"
     :feature.discussions.spaces/lead "Egal ob Brainstorming oder Diskussion"
     :feature.discussions.spaces/title "Schaffen Sie Platz für Diskussionen"
     :feature.discussions.spaces/body "Erstellen Sie Diskussionsthemen, die Sie ihren Teams einfach per Link zugänglich machen können. Die eingeladenen Kolleg:innen können den geschaffenen Raum nutzen, um zeitversetzt und online miteinander zu diskutieren und ihr Wissen einfließen zu lassen."
     :feature.discussions.discuss/lead "Einfach online diskutieren"
     :feature.discussions.discuss/title "Strukturierter Austausch per schnaq"
     :feature.discussions.discuss/body "Das Interface von schnaq-Diskussionen basiert auf wissenschaftlichen Erkenntnissen aus der Diskussionsforschung. Die Teilnehmer:innen werden dabei immer angeleitet sachliche und begründete Beiträge abzugeben. Dadurch bleiben Diskussionen und auf ihnen basierende Entscheidungen leicht nachvollziehbar."
     :feature.discussions.graph/lead "Alles auf einen Blick"
     :feature.discussions.graph/title "Graphische Aufbereitung der Diskussion"
     :feature.discussions.graph/body "Alle Diskussionen werden automatisiert graphisch aufbereitet. So bekommt man sofort einen Überblick bezüglich stark diskutierter Bereiche, dem Zusammenhang der Beiträge und kontroversen Standpunkten innerhalb der Diskussion. Durch die graphische Aufbereitung lassen sich Entscheidungen bequem und einfach visualisieren."
     :feature.discussions.tbd/reports "Automatisierte Zusammenfassungen der Diskussionen"
     :feature.discussions.tbd/wikis "Anbindung an Wiki-Systeme, wie Confluence"
     :feature.discussions.tbd/ideas "Automatisierte Benachrichtigungen, wenn Themen diskutiert werden, die abonniert wurden"
     :feature.discussions.tbd/navigation "Innovative Navigation durch große Diskussionen"
     :feature.discussions.tbd/connect "Anbindung an MS Teams, Slack und Co."
     :feature.discussions.tbd/bot "AI-basierte Hinweise, welche Bereiche weiteren Input benötigen"

     :feature.knowledge/lead ""

     :how-to.startpage/title "Wie benutze ich schnaq?"
     :how-to.startpage/body "Sie möchten losschnaqqen, sind aber unsicher wie Sie schnaq bedienen können? Wir haben eine ausführliche Anleitung mit kurzen Videos erstellt, um Ihnen den Einstieg zu erleichtern."
     :how-to.startpage/button "Wie schnaqqe ich?"
     :how-to/title "Wie benutze ich schnaq?"
     :how-to.why/title "Wozu dient schnaq?"
     :how-to.why/body "Schnaq dient dazu Meetings und andere Treffen im Voraus mit den Teilnehmenden zu planen und zu diskutieren."
     :how-to.create/title "schnaq erstellen"
     :how-to.create/body "Legen Sie zuerst einen schnaq an. Geben Sie Ihrem schnaq danach einen Titel und eine Beschreibung. Sie können auch Bilder und Dokumente verlinken."
     :how-to.agenda/title "Agenda erstellen"
     :how-to.agenda/body "Sie können mehrere Agendapunkte anlegen, um Ihren schnaq granularer zu planen und um Themen einzeln zu diskutieren."
     :how-to.admin/title "Teilnehmende einladen"
     :how-to.admin/body "Teilnehmende können entweder per Link oder Mail eingeladen werden. Weitere Admins laden Sie über den Admin Zugang ein. Administrierende können ebenfalls Teilnehmende einladen oder den schnaq editieren."
     :how-to.call-to-action/title "Genug gequatscht, jetzt wird geschnaqqt!"
     :how-to.call-to-action/body "Starten Sie jetzt Ihren schnaq bequem mit einem Klick! Laden Sie Teilnehmende ein und diskutieren Sie Vorschläge untereinander. Kollaborative Vorbereitung ohne Hürden, ganz einfach gemacht."

     :startpage.early-adopter/title "Neugierig geworden?"
     :startpage.early-adopter/body "Nutzen Sie exklusiv jetzt schon schnaq.com und zählen Sie damit zu unseren Early Adoptern."
     :startpage.early-adopter.buttons/join-schnaq "Beispielschnaq ansehen"
     :startpage.early-adopter/or "oder"

     :startpage.mailing-list/title "Fordern Sie mehr Informationen zu schnaq an"
     :startpage.mailing-list/body "Holen Sie sich regelmäßig Updates zu schnaq, DisqTec und den aktuellsten Produkten."
     :startpage.mailing-list/button "Zum Newsletter anmelden"

     :footer.buttons/about-us "Über uns"
     :footer.buttons/legal-note "Impressum"
     :footer.buttons/privacy "Datenschutz"

     ;; create schnaq
     :meeting-create-header "schnaq erstellen"
     :meeting-create-subheader "Geben Sie Ihrem schnaq einen Namen und eine Beschreibung"
     :meeting-form-title "Titel"
     :meeting-form-title-placeholder "Wie soll Ihr schnaq heißen?"
     :meeting-form-description "Beschreibung"
     :meeting-form-description-placeholder "Dauer: X Minuten\n\nThema"
     :meeting-form-end-date "Datum"
     :meeting-form-end-time "Uhrzeit"
     :meeting/copy-share-link "Link kopieren:"
     :meeting/copy-link-tooltip "Hier klicken um Link zu kopieren"
     :meeting/link-copied-heading "Link kopiert"
     :meeting/link-copied-success "Der Link wurde in Ihre Zwischenablage kopiert!"
     :meeting/created-success-heading "Ihr schnaq wurde erstellt!"
     :meeting/created-success-subheading "Nun können Sie den Zugangslink verteilen oder andere Personen per Mail einladen 🎉"
     :meetings/continue-with-schnaq-after-creation "Alle eingeladen? Legen Sie los!"
     :meetings/continue-to-schnaq-button "Zum schnaq"
     :meetings/edit-schnaq-button "schnaq editieren"
     :meetings.suggestions/header "Vorschläge einreichen"
     :meetings.suggestions/subheader "Die erstellende Person kann die Vorschläge einsehen und berücksichtigen"

     :meeting.admin/addresses-label "E-Mail Adressen der Teilnehmenden"
     :meeting.admin/addresses-placeholder "E-Mail Adressen getrennt mit Leerzeichen oder Zeilenumbruch eingeben."
     :meeting.admin/addresses-privacy "Diese Adressen werden ausschließlich zum Mailversand genutzt und danach sofort von unseren Servern gelöscht."
     :meeting.admin/send-invites-button-text "Einladungen versenden"
     :meeting.admin/send-invites-heading "Laden Sie die Teilnehmenden per E-Mail ein"
     :meeting.admin.notifications/emails-successfully-sent-title "Mail(s) verschickt!"
     :meeting.admin.notifications/emails-successfully-sent-body-text "Ihre Mail(s) wurden erfolgreich versendet."
     :meeting.admin.notifications/sending-failed-title "Fehler bei Zustellung!"
     :meeting.admin.notifications/sending-failed-lead "Die Einladung konnte an folgende Adressen nicht zugestellt werden: "

     ;; schnaqs not found
     :schnaqs.not-found/alert-lead "Leider wurden keine schnaqs gefunden, zu denen Sie Zugriff haben."
     :schnaqs.not-found/alert-body "Laden Sie zu Ihrem ersten schnaq ein, indem Sie einen erstellen."

     ;; Admin Center
     :meeting/educate-on-link-text "Teilen Sie den untenstehenden Link mit Ihren KollegInnen."
     :meetings/educate-on-link-text-subtitle "Teilnahme ist für alle, die den Link kennen, möglich!"
     :meeting/educate-on-edit "Titel ändern oder Agendapunkte editieren?"
     :meeting/educate-on-admin "Später jederzeit zum Admin-Center zurückkehren!"
     :meeting.admin-center/heading "Admin-Center"
     :meeting.admin-center/subheading "schnaq: \"%s\""
     :meeting.admin-center.edit.link/header "Zugang zum Admin-Center"
     :meeting.admin-center.edit.link/primer "Administration ist Arbeit, lassen Sie sich helfen!"
     :meeting.admin-center.edit.link/admin "Zugang zum Admin-Center per Mail"
     :meeting.admin-center.edit.link/admin-privilges "Editieren und Vorschläge verwalten"
     :meeting.admin-center.edit.link.form/label "E-Mail Adresse der Administrierenden"
     :meeting.admin-center.edit.link.form/placeholder "Eine E-Mailadresse eingeben"
     :meeting.admin-center.edit.link.form/submit-button "Link verschicken"
     :meeting.admin-center.invite/via-link "Link verteilen"
     :meeting.admin-center.invite/via-mail "Per E-Mail einladen"
     :meeting/admin-center-tooltip "Schnaq administrieren"

     ;; Suggestions
     :suggestions.modal/header "Eingereichte Vorschläge"
     :suggestions.modal/primer "Einige TeilnehmerInnen haben Ihnen Vorschläge zu Ihrem schnaq gegeben."
     :suggestions.modal/primer-delete "Folgende TeilnehmerInnen schlagen die Löschung des Agendapunktes vor."
     :suggestions.modal.delete/button "Entgültig löschen"
     :suggestions.modal.table/nickname "Nickname"
     :suggestions.modal.table/suggestion-title "Titel"
     :suggestions.modal.table/suggestion-description "Beschreibung"
     :suggestions.modal.table/suggestion-accept "Übernehmen"
     :suggestions.modal.delete/title "Löschanfragen zu diesem Agendapunkt"
     :suggestions.modal.update/title "Änderungsvorschläge"
     :suggestions.modal.new/title "Vorschläge folgende neuen Agendapunkte hinzuzufügen"
     :suggestions.notification/title "Vorschläge eingereicht"
     :suggestions.notification/body "Ihre Vorschläge wurden erfolgreich verschickt!"
     :suggestions.update.agenda/success-title "Vorschlag übernommen"
     :suggestions.update.agenda/success-body "Der Vorschlag wurde übernommen und ist für alle TeilnehmerInnen sichtbar."
     :suggestions.agenda/delete-title "Agendapunkt gelöscht"
     :suggestions.agenda/delete-body "Der Agendapunkt wurde erfolgreich gelöscht"
     :suggestion.feedback/label "Zusätzliches Feedback"
     :suggestions.feedback/title "Feedback zum Meeting"
     :suggestions.feedback/primer "Folgendes Feedback wurde zu diesem Meeting im Vorfeld abgegeben."
     :suggestions.feedback.table/nickname "Nickname"
     :suggestions.feedback.table/content "Feedback"
     :suggestions.feedback/header "Freitext-Feedback"

     ;; Create Agenda
     :agenda/desc-for "Beschreibung für Agendapunkt "
     :agenda/point "Agendapunkt "
     :agenda.create/optional-agenda "Agenda hinzufügen"

     ;; Edit Agenda
     :agenda/edit-title "Schnaq editieren"
     :agenda/edit-subtitle "Beschreibung und Agendapunkte editieren"
     :agenda/edit-button "Änderungen speichern"

     :agendas.button/navigate-to-suggestions "Änderungsvorschläge erstellen"

     ;; Discussion Language
     :discussion/agree "Zustimmung"
     :discussion/disagree "Ablehnung"
     :discussion/create-argument-action "Meinung hinzufügen"
     :discussion/create-argument-heading "Eigene Meinung abgeben / Informationen hinzufügen"
     :discussion/add-argument-conclusion-placeholder "Das denke ich darüber."
     :discussion/add-argument-premise-placeholder "Und das ist meine Begründung dafür."
     :discussion/add-starting-premise-placeholder "weil..."
     :discussion/add-premise-supporting "Ich möchte die Aussage unterstützen"
     :discussion/add-premise-against "Ich habe einen Grund dagegen"
     :discussion/add-undercut "Die letzten beiden Aussagen passen nicht zusammen"
     :discussion/reason-nudge "Was denken Sie darüber?"
     :discussion/premise-placeholder "Ich denke..."
     :discussion/create-starting-premise-action "Beitrag hinzufügen"
     :discussion/others-think "Andere denken folgendes:"
     :discussion/undercut-bubble-intro "Der letzte Beitrag hat nichts mit dem vorherigen zu tun. Begründung:"
     :discussion.badges/user-overview "Alle Teilnehmenden"
     :discussion.notification/new-content-title "Neuer Beitrag!"
     :discussion.notification/new-content-body "Ihr Beitrag wurde erfolgreich gespeichert."
     :discussion.carousel/heading "Beiträge Anderer"
     :discussion/discuss "Diskutieren"
     :discussion/discuss-tooltip "Diskutieren Sie mit anderen über diesen Agendapunkt."

     ;; meetings overview
     :meetings/header "Übersicht Ihrer schnaqs"
     :meetings/subheader "Auf diese schnaqs haben Sie Zugriff"

     ;; Feedbacks
     :feedbacks.overview/header "Rückmeldungen"
     :feedbacks.overview/subheader "Alle abgegebenen Rückmeldungen"
     :feedbacks.overview/description "Beschreibung"
     :feedbacks.overview/contact-name "Von"
     :feedbacks.overview/contact-mail "E-Mail"
     :feedbacks/button "Feedback"
     :feedbacks/screenshot "Screenshot"
     :feedbacks.modal/primer "Feedback ist wichtig! Wir freuen uns sehr über
     jede Art von Feedback, je ehrlicher desto besser 🥳 Hinterlassen Sie uns
     gerne einen kleinen Kommentar und helfen Sie uns damit diese Software
     weiter zu verbessern. Dankeschön!"
     :feedbacks.modal/contact-name "Ihr Name"
     :feedbacks.modal/contact-mail "E-Mail Adresse"
     :feedbacks.modal/description "Ihre Rückmeldung"
     :feedbacks.modal/optional "Optional"
     :feedbacks.modal/screenshot "Foto der Anwendung mit abschicken?"
     :feedbacks.modal/disclaimer "Ihre Daten werden nur auf unseren Servern
     abgespeichert und keinen Dritten zugänglich gemacht."
     :feedbacks.notification/title "Vielen Dank für Ihre Rückmeldung!"
     :feedbacks.notification/body "Ihr Feedback wurde erfolgreich an uns
     gesendet 🎉"

     :feedbacks.survey/primer
     [:<> "Wir würden uns freuen, wenn Sie bei einer
     kleinen Umfrage teilnehmen würden. Diese wird bei Google Forms gehostet
     und unterliegt den "
      [:a {:href "https://policies.google.com/privacy"} "Datenschutzbestimmungen von Google"]
      ". Mit der Teilnahme an der Umfrage akzeptieren Sie diese Datenschutzbestimmungen."]
     :feedbacks.survey/checkbox "Ja, ich möchte an der Umfrage teilnehmen"
     :feedbacks.survey/loading "Formular wird geladen..."
     :feedbacks.survey/tab "Umfrage"

     ;; login
     :login/as "Hallo, "
     :login/set-name "Geben Sie Ihren Namen ein"

     ;; analytics
     :analytics/heading "Analytics"
     :analytics/overall-meetings "Schnaqs erstellt"
     :analytics/user-numbers "Usernamen angelegt"
     :analytics/average-agendas-title "Durchschnittliche Zahl an Agendas pro Schnaq"
     :analytics/statements-num-title "Anzahl Statements"
     :analytics/active-users-num-title "Aktive User (min. 1 Beitrag)"
     :analytics/statement-lengths-title "Beitragslängen"
     :analytics/argument-types-title "Argumenttypen"
     :analytics/fetch-data-button "Hole Daten"

     ;; User related
     :user.button/set-name "Name speichern"
     :user.button/set-name-placeholder "Ihr Name"
     :user.button/success-body "Name erfolgreich gespeichert"
     :user.set-name/dialog-header "Hallo 👋"
     :user.set-name/dialog-lead "Schön, dass Sie hier sind!"
     :user.set-name/dialog-body "Um an Diskussionen teilzunehmen ist es notwendig, dass Sie einen Namen eingeben."
     :user.set-name/dialog-button "Wie möchten Sie genannt werden?"
     :user.set-name.modal/header "Geben Sie einen Namen ein"
     :user.set-name.modal/primer "Der Name wird den anderen Teilnehmenden im schnaq angezeigt."

     ;; Errors
     :errors/navigate-to-startpage "Zurück zur Startseite"
     :errors/generic "Es ist ein Fehler aufgetreten"

     :error.generic/contact-us [:span "Sollten Sie hier landen nachdem Sie etwas auf schnaq.com angeklickt haben, geben Sie uns gerne Bescheid unter " [:a {:href "mailto:info@dialogo.io"} "info@dialogo.io"]]

     :error.404/heading "Diese Seite existiert nicht 🙉"
     :error.404/body "Die URL der Sie gefolgt sind existiert leider nicht. Möglicherweise hat sich ein Tippfehler
     oder ein Zeichen zu viel eingeschlichen."

     :error.403/heading "Sie haben nicht die Berechtigung diese Seite aufzurufen 🧙‍♂️"
     :error.403/body "Ihnen fehlt die Berechtigung diese Seite aufzurufen oder es handelt sich um einen Tippfehler in Ihrer URL."

     ;; Graph Texts
     :graph/heading "Diskussionsübersicht"
     :graph.button/text "Zeige Diskussionsgraphen an"

     ;; Route Link Texts
     :router/all-meetings "Alle schnaqs"
     :router/all-feedbacks "Alle Feedbacks"
     :router/create-meeting "Schnaq anlegen"
     :router/meeting-created "Zuletzt angelegter schnaq"
     :router/my-schnaqs "Meine schnaqs"
     :router/show-single-meeting "Schnaq anzeigen"
     :router/start-discussion "Starte Besprechung"
     :router/continue-discussion "Führe Besprechung fort"
     :router/startpage "Startseite"
     :router/how-to "Wie benutze ich schnaq?"
     :router/analytics "Analyse-Dashboard"
     :router/invalid-link "Fehlerseite"
     :router/true-404-view "404 Fehlerseite"
     :router/not-found-label "Not Found route redirect"
     :router/graph-view "Graph View"
     :router.features/meetings "Meeting Features"
     :router.features/discussion "Diskussionsfeatures"}))


(defn img-path
  "Returns an image path as String for a given identifier"
  [identifier]
  (identifier
    {:animation-discussion "/animations/animation_discussion.webm"
     :animation-discussion-mp4 "/animations/animation_discussion.mp4"
     :elephant-admin "/imgs/elephants/admin.png"
     :elephant-erase "/imgs/elephants/erase.png"
     :elephant-share "/imgs/elephants/share.png"
     :elephant-stop "/imgs/elephants/stop.png"
     :elephant-talk "/imgs/elephants/talk.png"
     :how-to/taskbar "/imgs/howto/taskbar.svg"
     :icon-add "/imgs/buttons/add-button.svg"
     :icon-community "/imgs/community.svg"
     :icon-crane "/imgs/crane.svg"
     :icon-graph "/imgs/graph/graph-icon.svg"
     :icon-reports "/imgs/reports.svg"
     :icon-robot "/imgs/robot.svg"
     :logo "/imgs/Schnaq-Logo.svg"
     :logo-white "/imgs/Schnaq-Logo-White.svg"
     :schnaqqifant/original "/imgs/schnaqqifant.svg"
     :schnaqqifant/white "/imgs/schnaqqifant_white.svg"
     :startpage.features/meeting-organisation "/imgs/startpage/meeting_organisation_500px.png"
     :startpage.features/sample-discussion "/imgs/startpage/discussion_elearning.png"
     :startpage.features/discussion-graph "/imgs/startpage/discussion_graph_500px.png"
     :startpage.value-cards.discussion/image "/imgs/stock/discussion.jpeg"
     :startpage.value-cards.meetings/image "/imgs/stock/meeting.jpeg"
     :startpage.value-cards.knowledge/image "/imgs/stock/knowledge.jpeg"
     :feature.meetings/hero-image "/imgs/stock/meeting_landing_hero_500w.jpeg"
     :feature.meetings/schedule-meetings "/imgs/startpage/features/meeting-erstellen.png"
     :feature.discussions/hero-image "/imgs/stock/discussion_landing_hero.jpeg"
     :feature.discussions/create-discussion-spaces "/imgs/startpage/features/discussion-agendas.png"
     :startpage.features/admin-center "/imgs/startpage/features/admin-center.png"}))

(defn video
  "Returns an video path"
  [identifier]
  (identifier
    {:how-to.why/webm "/animations/howto/Why.webm"
     :how-to.why/mp4 "/animations/howto/Why.mp4"
     :how-to.create/webm "/animations/howto/Create.webm"
     :how-to.create/mp4 "/animations/howto/Create.mp4"
     :how-to.agenda/webm "/animations/howto/Agenda.webm"
     :how-to.agenda/mp4 "/animations/howto/Agenda.mp4"
     :how-to.admin/webm "/animations/howto/Admin.webm"
     :how-to.admin/mp4 "/animations/howto/Admin.mp4"}))

(defn fa
  "Returns an fontawesome icon id as String for a given identifier"
  [identifier]
  (identifier
    {:add "fa-plus-circle"
     :arrow-down "fa-arrow-down"
     :arrow-left "fa-arrow-left"
     :arrow-right "fa-arrow-right"
     :arrow-up "fa-arrow-up"
     :carry "fa-people-carry"
     :cog "fa-cogs"
     :comment "fa-comments"
     :comment-alt "fa-comment-alt"
     :copy "fa-copy"
     :check "fa-check-square"
     :delete-icon "fa-times-circle"
     :edit "fa-edit"
     :eraser "fa-eraser"
     :laptop "fa-laptop-code"
     :trash "fa-trash-alt"
     :users "fa-users"}))
