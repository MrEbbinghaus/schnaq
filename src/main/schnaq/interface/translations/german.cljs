(ns schnaq.interface.translations.german
  (:require [schnaq.interface.config :refer [marketing-num-schnaqs marketing-num-statements]]
            [schnaq.interface.utils.toolbelt :as toolbelt]))

(def labels
  {:error/export-failed "Export hat nicht geklappt, versuche es später erneut."

   :nav/schnaqs "schnaqs"
   :nav.schnaqs/show-all "Alle schnaqs"
   :nav.schnaqs/show-all-public "Alle öffentlichen schnaqs"
   :nav.schnaqs/create-schnaq "schnaq anlegen"
   :nav.schnaqs/last-added "Zuletzt angelegter schnaq"
   :nav/blog "Blog"
   :nav/admin "Admin"
   :nav/register "Kostenlos anmelden"
   :nav.buttons/language-toggle "Sprache ändern"

   ;; Alphazulu Page
   :alphazulu.page/heading "Alphazulu"
   :alphazulu.page/subheading "Modern Work for Modern Companies"
   :alphazulu.introduction/title "New Work aus Deutschland"
   :alphazulu.introduction/body
   [:<>
    [:p "Sicher, individuell, lokal: Das ist die Power von ALPHAZULU."]
    [:p "Finde mit den Modulen von "
     [:a {:href "/"} "schnaq"] ", " [:a {:href "https://wetog.de/"} "wetog"] ", "
     [:a {:href "https://xignsys.com/"} "XignSys"] ", " [:a {:href "https://www.cobago.de/"} "Cobago"] ", "
     [:a {:href "https://www.trustcerts.de/"} "TrustCerts"] " und " [:a {:href "https://ec3l.com/"} "EC3L"]
     " die maßgeschneiderte Lösung für dein Unternehmen."]
    [:p "Alle Alphazulu-Produkte sind miteinander kombinier- und integrierbar."]]
   :alphazulu.schnaq/title "Strukturierter Wissensaustausch"
   :alphazulu.schnaq/body
   [:<>
    [:p "Manage die digitale Transformation deines Unternehmens. Wir helfen dir bei interner Kommunikation und Wissensaustausch."]
    [:p "Nutze schnaq standalone und logge dich zum Beispiel per Xign.Me ein, oder buche schnaq direkt bei deinem Wetog-Abo dazu."]]
   :alphazulu.wetog/title "Sichere Kollaboration"
   :alphazulu.wetog/body
   [:<>
    [:p "Wetog nutzt die quantencomputer-sichere Verschlüsselung LIQRYPT um sämtliche Daten, Chats und Videokonferenzen sicher zu verschlüsseln."]
    [:p "Du hast schon einen Wetog Zugang? Buche direkt schnaq in Wetog dazu. Oder einen der anderen Alphazulu-Partner."]]
   :alphazulu.xignsys/title "Passwortlose Logins von überall"
   :alphazulu.xignsys/body
   [:<>
    [:p "Mit der einzigartigen Lösung Xign.Me kann man sich überall durch seine Biometrie einfach ohne passwort authentifizieren."]
    [:p "Probiere Xign.Me einfach beim Login bei schnaq aus. Logge dich bei Alphazulu-Partnern ein ohne anfällige Passwörter zu brauchen."]]
   :alphazulu.cobago/title "Digitale Assistenz"
   :alphazulu.cobago/body
   [:<>
    [:p "Cobago hilft deinem Unternehmen dabei einfach Formulare und Prozesse zu automatisieren. Ganz ohne technische Vorkenntnisse."]
    [:p "Du möchtest schnaq-Diskussionen innerhalb der Cobago Plattform nutzen? Gib uns Bescheid für einen Zugang!"]]
   :alphazulu.trustcerts/title "Digitale Signaturen – powered by Blockchains"
   :alphazulu.trustcerts/body
   [:<>
    [:p "Mit den Lösungen von TrustCerts kannst du wichtige Dokumente einfach und unverfälschlich durch die Blockchain signieren und überprüfen."]
    [:p "Egal ob eine Entscheidung aus einem schnaq verbindlich festgehalten werden muss oder ob ein Dokument aus Wetog oder Cobago Sixpad signiert werden muss. Alphazulu liefert die Lösung."]]
   :alphazulu.ec3l/title "Weiterbildung mit Konzept"
   :alphazulu.ec3l/body
   [:<>
    [:p "Moderne, zielgerichtete Weiterbildung die nachweislich funktioniert dein Unternehmenswissen auf eine neue Stufe hebt."]
    [:p "Finde bald auch schnaq Module innerhalb deiner EC3L Weiterbildung. Nutze einfach weiter was du schon kennst."]]
   :alphazulu.activate/title "Persönliches Gespräch"
   :alphazulu.activate/body [:p "Wenn du mehr über schnaq und Alphazulu erfahren möchtest, dann buche gerne einen Termin mit uns "
                             [:a {:href "https://calendly.com/schnaq/30min"} "über Calendly."] " Wir freuen uns auf ein persönliches Gespräch."]

   ;; Call to contribute
   :call-to-contribute/lead "Bisher gibt es hier noch keine Beiträge"
   :call-to-contribute/body "Starte mit deinem ersten Beitrag"

   ;; code of conduct
   :coc/heading "Verhaltensregeln"
   :coc/subheading "Unsere Benimmregeln"

   :coc.users/lead "Verhalten gegenüber anderen Nutzer:innen"
   :coc.users/title "Respektvoller Umgang und Nichtdiskriminierung"
   :coc.users/body "Ein respektvoller Umgang ist wichtig, um miteinander leben zu können und bietet die Grundlage für sachliche Diskussionen. Dies gilt nicht nur offline sondern auch online. \nUns ist es wichtig, dass sich jede:r Nutzer:in ausdrücken kann, ohne aufgrund ihrer Person, Herkunft oder Ansichten diskriminiert zu werden. \nBeiträge, die sich nicht an diese Richtlinien halten, werden entfernt."

   :coc.content/lead "Inhalte"
   :coc.content/title "Wir halten uns an das Gesetz, bitte tut das auch"
   :coc.content/body "Wir halten das Deutsche Grundgesetz ein; dies gilt auch und insbesondere für Datenschutz, Gleichberechtigung und Nichtdiskriminierung.\nInhalte, die gegen geltendes Recht verstoßen, werden von uns gelöscht."

   ;; how-to
   :how-to/button "Wie schnaqqe ich?"
   :how-to/title "Wie verwende ich schnaq?"
   :how-to.create/title "Worüber möchtest du mit deinem Team schnaqqen?"
   :how-to.create/body
   [:<>
    [:p [:i "\"Welche Systeme nutzen wir wann?\", \"Welchen Preis bieten wir Bildungseinrichtungen?\", \"Was ist unsere Vision?\""]]
    [:p "Gib deinem schnaq einen aussagekräftigen Titel und lasse das ganze Team diskutieren. Alle Beiträge sind wichtig und bieten eine individuelle Sicht auf die Diskussion."]]
   :how-to.why/title "Wieso soll ich schnaqqen?"
   :how-to.why/body "Faden verloren? Nicht zu Wort gekommen? Haben alle alles verstanden? Mal wieder keine Ahnung warum so viel diskutiert wurde?\nSpar dir Zeit und halte Wissen mit schnaq fest. Nimm teil, wann du Zeit hast. Die Mindmap der Diskussion gibt's geschenkt."
   :how-to.admin/title "Administriere deinen schnaq oder lass dir helfen"
   :how-to.admin/body "Lade Teilnehmer:innen per Link oder Mail ein. Wenn du Hilfe beim Administrieren brauchst, klicke einfach auf \"Admin Zugang\" und verschicke einen Administrationszugang per Mail.\n\nAls Admin kannst du Beiträge löschen und Personen per Mail einladen."
   :how-to.schnaq/title "Wie schnaqqe ich?"
   :how-to.schnaq/body "Teile deine Meinung! Trage sie in das Eingabefeld ein und sie erscheint in der Liste an Beiträgen. Teilnehmer:innen können drauf reagieren und antworten. Die Mindmap wird automatisch generiert und aktualisiert sich mit jedem neuen Beitrag. Wenn du zu einem Beitrag springen möchtest, klicke ihn einfach an."
   :how-to.pro-con/title "Bist du dafür oder dagegen?"
   :how-to.pro-con/body "Teile den Anderen deine Grundhaltung zum aktuellen Beitrag mit. Mit einem Klick auf unseren Dafür/Dagegen Knopf beim Eingabefeld änderst du deine Haltung. Du kannst auch mehrere Argumente dafür oder dagegen nennen. Pro-Argumente werden Blau eingefärbt, Kontra-Argumente Orange."
   :how-to.call-to-action/title "Jetzt weißt du Bescheid!"
   :how-to.call-to-action/body "Hiermit ist alles erklärt was über schnaq zu erklären gibt, also lege direkt los!"
   :how-to/ask-question "Nicht sicher wie du schnaq benutzen sollst?"
   :how-to/ask-question-2 "Noch Fragen?"
   :how-to/answer-question "Schau hier nach!"
   :how-to/question-dont-show-again "Verstanden?"
   :how-to/answer-dont-show-again "In Zukunft nicht mehr anzeigen!"
   :how-to/back-to-start "Zurück zu schnaq"

   ;; Startpage
   :startpage/heading "Fragen sammeln leicht gemacht"
   :startpage/subheading "Q&A einfach und gut"
   :startpage/hook "Sammle einfach Fragen und liefere Antworten in deinen hybriden Kursen – für mehr Übersicht und nachhaltigen Austausch."
   :startpage.social-proof/numbers [:span "schnaq hat schon in über " [:b marketing-num-schnaqs]
                                    " Diskussionen und Q&As geholfen mehr als " [:b marketing-num-statements] " mal Wissen auszutauschen."]
   :schnaq.startpage.cta/button "Kostenfrei schnaqqen"

   :startpage.usage/lead "Wofür kann ich schnaq verwenden?"
   :startpage.features/more-information "Mehr Informationen"

   :startpage.information.know-how/title "Fragen sammeln leicht gemacht"
   :startpage.information.know-how/body "Schnaq's Q&A Funktionen und strukturierte Diskussionen helfen deinem Kurs, Workshop oder Schulung blitzschnell Wissen auszutauschen und nachhaltig aufzubereiten. Nur wo Wissen fließt, kann Großartiges entstehen."

   :startpage.information.positioning/title "Verstehe was gefragt wird"
   :startpage.information.positioning/body "Erkenne auf einen Blick die Probleme deines Kurses. Mit der automatisch generierten Mindmap und den K.I. Analysen, wird jede Fragerunde übersichtlich und einfach zu verstehen aufbereitet."

   :startpage.information.anywhere/title "Nutze schnaq überall zu jeder Zeit"
   :startpage.information.anywhere/body "Schnaq läuft als Web-App auf allen gängigen Betriebssystemen, Browsern und Geräten. Egal ob Smartphone, Tablet oder Computer."

   :startpage.information.meetings/title "Hybrides Fragenstellen"
   :startpage.information.meetings/body "Erreiche durch schnaq auch die Menschen, die nicht vor Ort sein können. Alle können eingebunden und die Fragen anonym gestellt werden. Mit intelligentem Q&A kannst du sogar online up-to-date bleiben!"

   :startpage.feature-box/heading "Wie funktioniert schnaq?"
   :startpage.feature-box.know-how/title "Bereite dich vor"
   :startpage.feature-box.know-how/body "Erstelle einen schnaq, wo Fragen gesammelt werde können. Du erhältst einen Zugangscode, QR-Code und einen Link, mit dem du alle Teilnehmer:innen einladen kannst."
   :startpage.feature-box.discussion/title "Binde den Kurs ein"
   :startpage.feature-box.discussion/body "Mit Smartphones, Tablets und Laptops können deine Zuhörer:innen dem schnaq beitreten und ihre Fragen stellen. Automatisch werden ihnen passende Fragen mit Antworten gezeigt, die inhaltlich ähnlich sind."
   :startpage.feature-box.learnings/title "Teile die Antworten"
   :startpage.feature-box.learnings/body "Beantwortete Fragen stehen deinem Kurs sofort zur Verfügung und können jederzeit eingesehen werden. Im Nachgang kannst du jederzeit noch Inhalte ergänzen oder die selbe Fragenbasis wieder im nächsten Kurs verwenden."

   :startpage.early-adopter/title "Neugierig geworden?"
   :startpage.early-adopter/body "Sei einer der ersten die schnaq Hubs nutzen"

   :startpage.newsletter/heading "Gehöre zu den Ersten, die von neuen Funktionen profitieren!"
   :startpage.newsletter/button "Exklusive Informationen anfordern!"
   :startpage.newsletter/address-placeholder "E-Mail Adresse"
   :startpage.newsletter/consent "Ich möchte mich hiermit zum schnaq Newsletter anmelden, und in Zukunft regelmäßig Informationen von schnaq.com erhalten."
   :startpage.newsletter/more-info-clicker "Datenverarbeitung"
   :startpage.newsletter/policy-disclaimer "schnaq erhebt, verarbeitet und nutzt Ihre oben angegebenen personenbezogenen Daten zur
        Bearbeitung Ihres Anliegens. Jederzeit kannst du dich von dem Newsletter abmelden, indem du auf
        den in der E-Mail zur Verfügung gestellten Link klicken. Alternativ kannst du uns auch eine E-Mail
        schreiben und wir kümmern uns dann um Ihr Anliegen."
   :startpage.newsletter/privacy-policy-lead "Mehr Informationen zur Verarbeitung von personenbezogenen Daten, findest du in unserer"

   :startpage.faq/title "Häufig gestellte Fragen"
   :startpage.faq/subtitle "(so könnte es bei dir aussehen)"
   :startpage.faq.data/question "Was passiert mit meinen Daten?"
   :startpage.faq.data/answer-1 "Um einen möglichst sicheren Datenschutz zu gewährleisten, speichern
        wir alle Daten nur auf deutschen Servern. Wir haben alle Details einzeln und verständlich in unserer"
   :startpage.faq.data/link-name "Datenschutzerklärung"
   :startpage.faq.data/answer-2 " zusammengefasst."
   :startpage.faq.integration/question "Kann ich schnaq mit meiner bestehenden Software integrieren?"
   :startpage.faq.integration/answer "Aktuell ist schnaq per Mausklick in WETOG integrierbar. Wir arbeiten mit Hochdruck an einer Integration für Slack, MS Team und andere gängige Kommunikationssoftware.
        Wenn du sofort informiert werden willst, wenn die Integration live geht, melde dich für den"
   :startpage.faq.integration/link-name "Newsletter an."
   :startpage.faq.costs/question "Gibt es versteckte Kosten?"
   :startpage.faq.costs/answer "schnaq ist derzeit in einer Testphase und kostenlos benutzbar. Es gibt keinerlei Kosten. Wir freuen uns
        aber über ehrliches Feedback als Gegenleistung."
   :startpage.faq.start/question "Wie kann ich mit schnaq starten?"
   :startpage.faq.start/answer "Du kannst schnaq entweder anonym nutzen, oder dich registrieren und anmelden, um deine schnaqs und Beiträge von
        überall aus einsehen und verwalten zu können. Probier es einfach aus und"
   :startpage.faq.start/link-name "starte einen schnaq."
   :startpage.faq.why/question "Warum sollte ich schnaq nutzen?"
   :startpage.faq.why/answer "schnaq ist für dich, wenn du eine moderne, offene und gleichberechtigte Arbeitskultur unterstützt.
        Unser Ziel ist es Kommunikation und Wissensaustausch am Arbeitsplatz flexibel zu gestalten. So heben wir
        nicht nur das Potenzial einzelner Teammitglieder, sondern auch des gesamten Unternehmens."

   :startpage.founders-note/title "Ein Brief von den Gründern"

   ;; Login Page
   :page.login/heading "Nicht warten, schnaqqen!"
   :page.login/subheading "100% kostenlos für immer"
   :page.login/login "Anmelden / Registrieren"
   :page.login.alert/text-1 "Sieh dir"
   :page.login.alert/button "hier"
   :page.login.alert/text-2 "alle Vorteile als registrierte:r Nutzer:in an."
   :page.login/feature-1 "schnaqs erstellen"
   :page.login/feature-2 "Unbegrenzt viele Teilnehmer:innen"
   :page.login/feature-3 "Teilnahme per Link ohne Registrierung"

   ;; Register Page when creating a schnaq
   :page.register/heading "Jetzt Registrieren und direkt schnaqqen!"
   :page.register/register "Kostenlos Registrieren"

   :auth.modal.request-login/title "Sitzung abgelaufen"
   :auth.modal.request-login/lead "Deine Sitzung ist abgelaufen. Das kann schonmal passieren, wenn du längere Zeit nicht aktiv warst. Bitte lade die Seite neu und logge dich erneut ein"
   :auth.modal.request-login/button "Erneut einloggen"
   :auth.modal.request-login/info "Wenn dein Login wiederhergestellt werden kann, wird beim Klick auf den Button nur die Seite kurz neu geladen."

   :page.beta/heading "Beta-Feature"
   :page.beta/subheading "Diese Funktion ist nur für Beta-Tester:innen freigeschaltet. Bitte logge dich ein, wenn du zu der Gruppe gehörst."

   :footer.buttons/about-us "Über uns"
   :footer.buttons/legal-note "Impressum"
   :footer.buttons/privacy "Datenschutz"
   :footer.buttons/press-kit "Presse"
   :footer.buttons/publications "Publikationen"
   :footer.tagline/developed-with "Entwickelt mit"
   :footer.sponsors/heading "Unsere Server werden gehostet bei"
   :footer.registered/rights-reserved "Alle Rechte vorbehalten"
   :footer.registered/is-registered "ist eine eingetragene Marke"

   ;; Header image
   :schnaq.header-image.url/placeholder "Bild URL eingeben"
   :schnaq.header-image.url/button "Vorschaubild hinzufügen"
   :schnaq.header-image.url/note "Erlaubt werden nur Inhalte von pixabay.com"
   :schnaq.header-image.url/label "Füge deinem schnaq ein Vorschaubild hinzu"
   :schnaq.header-image.url/successful-set "Vorschaubild erfolgreich gesetzt"
   :schnaq.header-image.url/successful-set-body "Das Bild wird nun in der Übersicht dargestellt."
   :schnaq.header-image.url/failed-setting-title "Fehler beim Hinzufügen des Bildes"
   :schnaq.header-image.url/failed-setting-body "Das Bild wird nicht in der Vorschau genutzt."

   ;; Create schnaq
   :schnaq.create.dispatch/heading "Was möchtest du starten?"
   :schnaq.create.dispatch/qanda "Fragen und Antworten"
   :schnaq.create.dispatch.qanda/explain "Sammel Fragen während einer Veranstaltung und beantworte sie wann immer du Zeit hast."
   :schnaq.create.dispatch.qanda/share "Lade Teilnehmer:innen per Link oder Zahlencode ein."
   :schnaq.create.dispatch/discussion "Diskussion"
   :schnaq.create.dispatch.discussion/explain "Führe K.I. gestützte und nachhaltige Diskussionen mit anderen Teilnehmer:innen."
   :schnaq.create.dispatch.discussion/share "Lade Teilnehmer:innen per Link ein."

   :schnaq.create.input/title "Worüber möchtest du diskutieren?"
   :schnaq.create.qanda.input/title "Worum sollen sich die Fragen drehen?"
   :schnaq.create.input/placeholder "Thema festlegen"
   :schnaq.create.hub/help-text "Füge deinen schnaq direkt einem Hub hinzu."
   :schnaq/copy-link-tooltip "Hier klicken, um Link zu kopieren"
   :schnaq/link-copied-heading "Link kopiert"
   :schnaq/link-copied-success "Der Link wurde in deine Zwischenablage kopiert!"
   :schnaq/created-success-heading "Dein schnaq wurde erstellt!"
   :schnaq/created-success-subheading "Nun kannst du den Zugangslink verteilen oder andere Personen per Mail einladen 🎉"
   :schnaqs/continue-with-schnaq-after-creation "Alle eingeladen? Los geht's!"
   :schnaqs/continue-to-schnaq-button "Zum schnaq"

   :schnaq.admin/addresses-label "E-Mail Adressen der Teilnehmer:innen"
   :schnaq.admin/addresses-placeholder "E-Mail Adressen getrennt mit Leerzeichen oder Zeilenumbruch eingeben."
   :schnaq.admin/addresses-privacy "Diese Adressen werden ausschließlich zum Mailversand genutzt und danach sofort von unseren Servern gelöscht."
   :schnaq.admin/send-invites-button-text "Einladungen versenden"
   :schnaq.admin/send-invites-heading "Lade die Teilnehmer:innen per E-Mail ein"
   :schnaq.admin.notifications/emails-successfully-sent-title "Mail(s) verschickt!"
   :schnaq.admin.notifications/emails-successfully-sent-body-text "Deine Mail(s) wurden erfolgreich versendet."
   :schnaq.admin.notifications/sending-failed-title "Fehler bei Zustellung!"
   :schnaq.admin.notifications/sending-failed-lead "Die Einladung konnte an folgende Adressen nicht zugestellt werden:"
   :schnaq.admin.notifications/statements-deleted-title "Nachrichten gelöscht!"
   :schnaq.admin.notifications/statements-deleted-lead "Deine gewählten Nachrichten wurden erfolgreich gelöscht."
   :schnaq.admin.notifications/heading "Einstellungen"
   :schnaq.admin.configurations.read-only/checkbox "Schreibschutz aktivieren"
   :schnaq.admin.configurations.read-only/explanation "Aktivieren, um keine neuen Beiträge zu erlauben. Bestehende Beiträge sind weiterhin sichtbar und können weiterhin analysiert werden. Diese Option kann jederzeit geändert werden."
   :schnaq.admin.configurations.discussion-mode/label "Q&A Modus mit Wissenskarten aktivieren"
   :schnaq.admin.configurations.discussion-mode/explanation "Aktivieren, um auf Frage- und Antwortmodus zu wechseln. Es stehen dann Wissenskarten zur Verfügung, wo du die korrekten Antworten zu den Fragen markieren kannst."
   :schnaq.admin.configurations.disable-pro-con/label "Dafür/Dagegen Knopf ausblenden"
   :schnaq.admin.configurations.disable-pro-con/explanation "Aktivieren, um den  Dafür/Dagegen Knopf nicht mehr anzuzeigen. Neue Beiträge werden als Zustimmung gewertet. Diese Option kann jederzeit geändert werden."
   :schnaq.admin.configurations.mods-mark-only/label "Nur Moderator:innen Antworten markieren lassen"
   :schnaq.admin.configurations.mods-mark-only/explanation "Wenn aktiviert, haben nur Moderatoren die Möglichkeit Antworten als korrekt zu markieren."
   :schnaq.admin.configurations.mods-mark-only/beta "Nur Beta-Nutzer:innen können diese Einstellung verändern. Frage nach Zugang unter hello@schnaq.com."

   :schnaq.access-code.clipboard/header "Zugangscode kopiert"
   :schnaq.access-code.clipboard/body "Der Zugangscode wurde in deine Zwischenablage kopiert."

   :statement/discuss "Diskutieren"
   :statement/reply "Antworten"
   :statement.reply/placeholder "Deine Antwort"
   :statement/ask "Fragen"
   :statement.ask/placeholder "Deine Frage"
   :statement.edit.send.failure/title "Änderung nicht gespeichert"
   :statement.edit.send.failure/body "Die Änderung konnte nicht durchgeführt werden. Bitte versuche es gleich noch einmal."
   :statement.edit/label "Beitrag bearbeiten"
   :statement.edit.button/submit "Absenden"
   :statement.edit.button/cancel "Abbrechen"
   :schnaq.edit/label "Titel bearbeiten"

   ;; schnaq creation
   :schnaq.create/title "Schnaq starten"
   :schnaq.create/heading "Starte mit deinem schnaq."
   :schnaq.create/subheading "Mit einem schnaq kannst du dein Team diskutieren lassen und Entscheidungen nachhaltig sichern."
   :schnaq.create.qanda/subheading "Mit einem schnaq kannst du effizient Fragen sammeln und beantworten."
   :schnaq.create/info "Gib deinem Thema einen möglichst einfachen und verständlichen Titel."
   :schnaq.create.button/save "Schnaq starten"

   ;; schnaq value
   :schnaq.value/title "Du bist startklar"
   :schnaq.value/subtitle "Ein paar Tipps, die dir und deinem Team helfen:"
   :schnaq.value.security/title "Datensicherheit"
   :schnaq.value.security/text "Datenschutz ist uns wichtig! Deine Daten sind sicher auf deutschen Servern."
   :schnaq.value.respect/title "Diskutiere mit Respekt"
   :schnaq.value.respect/text "Ein respektvoller Umgang ist wichtig, um miteinander leben zu können und bietet die Grundlage für sachliche Diskussionen."
   :schnaq.value.share/title "Teile deine Diskussion"
   :schnaq.value.share/text "Lade Teilnehmer:innen einfach per Link oder Mail ein. Keine Anmeldung notwendig!"
   :schnaq.value.private/title "Immer privat"
   :schnaq.value.private/text "Deine schnaqs sind standardmäßig nur von dir und den Leuten mit denen du teilst einsehbar."
   :schnaq.value.cards/title "Mindmap"
   :schnaq.value.cards/text "Für mehr Überblick sorgt unsere Mindmap, die automatisch generiert wird."
   :schnaq.value.results/title "Ergebnisansicht"
   :schnaq.value.results/text "Schau dir Zusammenfassungen und Analysen zu deiner Diskussion an (Beta Feature)."

   ;; Discussion Creation
   :discussion.create.hub-exclusive-checkbox/title "Schnaq zu einem Hub hinzufügen"
   :discussion.create.hub-exclusive-checkbox/label "Dem Hub hinzufügen"

   ;; Discussion Dashboard
   :dashboard/posts "Beiträge"
   :dashboard/members "Mitglieder"
   :dashboard/summary "Kurzzusammenfassung"
   :dashboard/top-posts "Top Beiträge"

   :discussion.navbar/title "Titel"
   :discussion.navbar/posts "Beiträge"
   :discussion.navbar/members "Mitglieder"
   :discussion.navbar/views "Ansichten"
   :discussion.state/read-only-label "schreibgeschützt"
   :discussion.state/read-only-warning "Diese Diskussion ist schreibgeschützt, Du kannst hier nur lesen, aber nicht schreiben."
   :discussion.navbar/settings "Einstellungen"

   ;; schnaq progress bar related stuff
   :discussion.progress/days-left "Noch %s Tage"
   :discussion.progress/unlimited "Unbeschränkt offen"
   :discussion.progress/end "Ende der Diskussion"
   :discussion.progress/ends "Endet %s"
   :discussion.progress/ends-not "Endet nicht"
   :discussion.progress.creation/heading "Begrenze die Laufzeit deiner Diskussion"
   :discussion.progress.creation/label "Ende in Tagen"
   :discussion.progress.creation/button-limit "%s Tage"
   :discussion.progress.creation/button-unlimited "Unbegrenzt"

   ;; Conversion-Edit-Funnel
   :discussion.anonymous-edit.modal/title "Bitte melde dich an zum Editieren"
   :discussion.anonymous-edit.modal/explain [:<> "Um Missbrauch von anonymen Beiträgen zu vermeiden, musst du dich " [:strong "zum Editieren anmelden."]]
   :discussion.anonymous-edit.modal/persuade "Beiträge, die in letzter Zeit von dir in diesem Browser erstellt wurden, werden dabei automatisch umgewandelt."
   :discussion.anonymous-edit.modal/cta "Anmelden / Registrieren"

   :discussion.anonymous-labels.modal/title "Bitte melde dich an, um Labels zu editieren"
   :discussion.anonymous-labels.modal/explain [:<> "Um Missbrauch von anonymen Beiträgen zu vermeiden, musst du dich " [:strong "zum Editieren von Labels anmelden."]]
   :discussion.anonymous-labels.modal/cta "Anmelden / Registrieren"

   ;; Conversion-Delete-Funnel
   :discussion.anonymous-delete.modal/title "Bitte melde dich an, um deinen Beitrag zu löschen"
   :discussion.anonymous-delete.modal/explain [:<> "Um Missbrauch von anonymen Beiträgen zu vermeiden, musst du dich " [:strong "zum Löschen anmelden."]]
   :discussion.anonymous-delete.modal/persuade "Beiträge, die in letzter Zeit von dir in diesem Browser erstellt wurden, werden dabei deinem Konto hinzugefügt."
   :discussion.anonymous-delete.modal/cta "Anmelden / Registrieren"

   ;; Beta Only Funnel
   :beta.modal/title "Beta-Feature"
   :beta.modal/explain [:<> "Das ist eine Testfunktion. Um sie benutzen zu können, musst du " [:strong "Beta-Tester:in sein."]]
   :beta.modal/persuade "Schreib uns eine E-Mail, wenn du auch zu den Beta-Tester:innen gehören möchtest."
   :beta.modal/cta "Anfrage senden"
   :beta.modal.login/intro "Bereits Beta-Tester:in?"
   :beta.modal.login/button "Dann melde dich an"
   :page.beta.modal/cta "Wenn du daran interessiert bist ein:e Beta-Tester:in zu werden, schreibe uns eine E-Mail unter"

   ;; Press Kit
   :press-kit/heading "Presse & Medien"
   :press-kit/subheading "Wir stehen gerne für Interviews und Artikel zur Verfügung!"
   :press-kit.intro/heading "Vielen Dank für Ihr Interesse an schnaq!"
   :press-kit.intro/lead "Bitte nehmen Sie sich einen Moment Zeit, um unsere Markenrichtlinien zu lesen. Wenn Sie Presseanfragen haben oder über uns schreiben möchten, senden Sie eine E-Mail an presse@schnaq.com. Wir würden uns sehr gerne mit Ihnen unterhalten!"
   :press-kit.spelling/heading "Richtige Schreibweise und Aussprache"
   :press-kit.spelling/content-1 "Unser Produkt heißt"
   :press-kit.spelling/content-2 "(gesprochen: [ˈʃnak]) und wird mit einem \"q\" geschrieben. Ausgesprochen wird es mit einem weichen \"sch\", analog zum norddeutschen \"schnacken\". Außer an Satzanfängen sollte schnaq kleingeschrieben werden. Das grammatikalische Geschlecht von schnaq ist männlich, somit heißt es beispielsweise \"der schnaq\" oder \"einen schnaq erstellen\"."
   :press-kit.not-to-do/heading "Bitte beachten Sie folgende Punkte"
   :press-kit.not-to-do/bullet-1 "Verwenden Sie keine anderen Bilder, Illustrationen, Inhalte oder andere Assets aus dieser Domain ohne Genehmigung."
   :press-kit.not-to-do/bullet-2 "Vermeiden Sie es diese Grafiken in einer Weise anzeigen, die eine Beziehung, Zugehörigkeit oder Befürwortung durch schnaq impliziert. Sollten Sie unsicher sein, so kontaktieren Sie uns gerne."
   :press-kit.not-to-do/bullet-3 "Verwenden Sie diese Grafiken nicht als Teil des Namens Ihres eigenen Produkts, Geschäfts oder Ihrer Dienstleistung."
   :press-kit.not-to-do/bullet-4 "Vermeiden Sie es bitte diese Grafiken in irgendeiner Weise zu verändern oder sie mit anderen Grafiken zu kombinieren, ohne unsere schriftliche Zustimmung."
   :press-kit.materials/heading "Materialien"
   :press-kit.materials/fact-sheet "Fact-Sheet"
   :press-kit.materials/logos "Logos"
   :press-kit.materials/product "Produktbilder"
   :press-kit.materials/team "Teamfotos"
   :press-kit.materials/download "Herunterladen"
   :press-kit.about-us/heading "Weitere Informationen"
   :press-kit.about-us/body "Weitere Informationen zu unseren Gründern, wissenschaftliche Publikationen sowie weitere Erscheinungen in Zeitungen und Medien, finden Sie auf den folgenden Seiten:"

   ;; Publications
   :publications/heading "Publikationen und Artikel"
   :publications/subheading "Die Wissenschaft hinter schnaq"
   :publications.primer/heading "Von der Wissenschaft in die Praxis"
   :publications.primer/body "Die Software, die wir entwickeln, basiert nicht nur auf Erfahrung, sondern auch auf langjähriger Forschung auf den Gebieten der Diskussion und Kommunikation. Hier finden Sie wissenschaftliche Artikel, Zeitungsbeiträge und weitere Publikationen, die von unserem Team stammen, oder in Kooperation mit unserem Team entstanden sind"

   :publications.perspective-daily/summary "Ein Artikel über unsere Forschung in der Perspective Daily. Schwerpunkt ist strukturiertes Diskutieren"
   :publications.salto/summary "Ein Interview mit unseren Gründern Dr. Christian Meter und Dr. Alexander Schneider über Diskussionen im Internet, Trolle und wie man dagegen vorgehen könnte."
   :publications.dissertation-alex/summary "In der Dissertation von Dr. Alexander Schneider geht es um die Frage, ob man strukturierte Diskussionen im Internet per dezentralen Systemen durchführen kann."
   :publications.dissertation-christian/summary "In der Dissertation von Dr. Christian Meter werden mehrere neuartige Verfahren und Herangehensweisen beleuchtet, um im Internet strukturierte Diskussionen durchführen zu können."
   :publications.structure-or-content/summary "In diesem Paper wird analysiert, ob Pagerank als Algorithmus zuverlässige Aussagen über Argumentrelevanz treffen kann und wie die Performance im Vergleich zu neueren Algorithmen ist."
   :publications.overview-paper/summary "Eine Präsentation von verschiedensten Methoden, die es ermöglichen, echte Diskussionen im Internet zu verbessern."
   :publications.dbas/summary "Die Beschreibung eines formalen Prototypen für dialogbasierte Online-Argumentation inklusive Evaluierung."
   :publications.dbas-politics/summary "Eine Vorstellung des Konzeptes der dialogbasierten Online-Diskussionen für Laien."
   :publications.eden/summary "Die Vorstellung eines Software-Paketes, welches den Betrieb von dezentral organisierten Servern erlaubt, die Nutzer:innen Zugang zu Online-Diskussionssystemen geben."
   :publications.jebediah/summary "Das Paper demonstriert einen sozialen Bot auf Basis von Googles Dialogflow Engine. Der Bot ist in der Lage in sozialen Netzwerken dialogbasiert mit seinen Nutzer:innen zu kommunizieren."
   :publications.dbas-experiment/summary "In einem Feldexperiment mit über 100 Proband:innen wird untersucht wie gut ein dialogbasiertes Argumentationssystem von Laien bedient werden kann."
   :publications.reusable-statements/summary "Die Autoren untersuchen den Gedanken, wie man online getätigte Argumente und deren Interrelationen als Resource verwertbar und wiederverwendbar machen kann."
   :publications.discuss/summary "Wenn strukturierte Diskussionen per Software möglich sind, ist es dann auch möglich diese Diskussionen in beliebigen Web-Kontexten stattfinden zu lassen? Dieser Frage gehen die Autoren nach."
   :publications.kind/article "Artikel"
   :publications.kind/dissertation "Dissertation (englisch)"
   :publications.kind/interview "Interview"
   :publications.kind/newspaper-article "Zeitungsartikel"
   :publications.kind/paper "Paper (englisch)"
   :publications.kind/short-paper "Shortpaper (englisch)"

   ;; Privacy Page
   :privacy/heading "Was geschieht mit deinen Daten?"
   :privacy/subheading "Wir erklären es dir gerne!"
   :privacy/open-settings "Sicherheitseinstellungen prüfen"
   :privacy.made-in-germany/lead "EU-konformes Vorgehen"
   :privacy.made-in-germany/title "Datenschutz ist uns wichtig!"
   :privacy.made-in-germany/body
   [:<>
    [:p "Das Entwickler:innenteam von schnaq besteht aus Informatiker:innen, die es Leid sind, dass mit Daten nicht sorgfältig umgegangen wird. Deshalb legen wir besonderen Wert darauf, DSGVO konform zu agieren und sämtliche Daten sicher auf Servern in Deutschland bei Hetzner zu speichern. Kein Datenaustausch mit anderen Unternehmen, keine faulen Kompromisse!"]
    [:p "Sollten noch Unklarheiten bei unserem Vorgehen mit deinen Daten bestehen, so kontaktiere uns gerne! Uns liegt Transparenz und Klarheit mit persönlichen Daten wirklich am Herzen und wir erklären dir bis zum letzten Bit was mit den Daten geschieht."]]
   :privacy.personal-data/lead "Welche Daten werden erhoben?"
   :privacy.personal-data/title "Persönliche Daten"
   :privacy.personal-data/body
   [:<>
    [:p "Standardmäßig werden nur technisch notwendige Daten erhoben. Es findet keine Auswertung über persönliche Daten statt und dein Verhalten auf unserer Website wird auch nur anonymisiert analysiert."]
    [:p "Dein Nutzer:innenverhalten wird mit Matomo erfasst und auf unseren Servern in Deutschland gespeichert. Matomo ist eine freie und selbstgehostete Alternative zu kommerziellen Anbietern. Wir geben keine Daten an Dritte damit weiter."]]
   :privacy.localstorage/lead "Welche Daten schicke ich an die Server?"
   :privacy.localstorage/title "Datenaustausch"
   :privacy.localstorage/body
   [:<>
    [:p "schnaq kann ganz auf Accounts verzichten. Es werden so keine Daten von dir auf unseren Servern gespeichert. Die meiste Interaktion findet über geteilte Links statt. Klicke auf einen Link zu einem schnaq, wird ein Teil des Links (der Hash) in deinem Browser (im LocalStorage) abgespeichert. Besuchst du dann schnaq erneut, schickt dein Browser diesen Hash zurück an uns und erhält so erneut Zugang zum schnaq. Alternativ kannst du dir die Zugangslinks per E-Mail schicken lassen und hältst so alle für den Betrieb notwendigen Daten selbst in der Hand."]
    [:p "Im Unterschied zu herkömmlichen Cookies, verwenden wir den LocalStorage, welcher naturgemäß nur die wirklich notwendigen Daten von dir an uns zurückschickt. Schaue selbst nach, welche Daten das genau sind, indem du auf den Button klickst."]]
   :privacy.localstorage/show-data "Deine Daten anzeigen"
   :privacy.localstorage.notification/title "Diese Daten hat dein Browser gespeichert"
   :privacy.localstorage.notification/body "Hinweis: \"Kryptische\" Zeichenketten sind die Zugangscodes zu den schnaqs."
   :privacy.localstorage.notification/confirmation "Möchtest du deine Daten wirklich löschen?"
   :privacy.localstorage.notification/delete-button "Daten löschen"

   :privacy.data-processing.anonymous/lead "Was passiert mit deinen Beiträgen?"
   :privacy.data-processing.anonymous/title "Datenverarbeitung bei anonymen Zugängen"
   :privacy.data-processing.anonymous/body [:<> [:p "Wir speichern die von dir verfassten Beiträge in Kombination mit dem von dir gewählten Nutzernamen auf unserem Server und geben sie nicht an Dritte weiter. Wenn du keinen Nutzer:innennamen eingibst, wird als Autor:in \"Anonymous\" eingetragen. Die von dir verfassten Beiträge stehen in keiner Beziehung zueinander. Da auch wir uns nicht merken, von wem die Beiträge stammen, ist eine Bearbeitung des Beitrags nicht möglich. Es werden keine persönlichen Daten, wie dein Browser oder deine IP-Adresse, mit deinen Beiträgen zusammengeführt."]
                                            [:p "Beiträge in öffentlichen schnaqs sind von allen Nutzer:innen einsehbar. Beiträge in privaten schnaqs sind nur von Personen einsehbar, die einen Link zur Diskussion haben. Administrator:innen eines schnaqs haben die Möglichkeit Beiträge zu löschen."]]
   :privacy.data-processing.registered/lead "Und wenn ich nun eingeloggt bin?"
   :privacy.data-processing.registered/title "Datenverarbeitung bei registrierten Nutzer:innen"
   :privacy.data-processing.registered/body
   [:<> [:p "Solltest du dich entscheiden dich zu registrieren, so werden E-Mail Adresse und dein Name gespeichert. Damit personalisieren wir dein schnaq-Erlebnis und zeigen deinen Namen an, wenn du einen Beitrag speicherst. Die Mailadresse ist unter anderem für Benachrichtigungen notwendig damit du informiert wirst wenn es neue Beiträge für dich gibt."]
    [:p "Bei einem Login über einen externen Anbieter, wie LinkedIn, erhält LinkedIn von dir eine Anfrage die angezeigten Informationen an uns zu übermitteln, die wir dann bei uns speichern. Loggst du dich erneut ein, so erhält LinkedIn auch wieder eine Anfrage. Möchtest du das vermeiden, so erstelle einfach direkt einen Account bei uns."]
    [:p "Zusätzlich speichern wir in deinem Account die Hubs und schnaqs, zu denen du Zugang hast. Damit kannst dich auch auf deinem Smartphone oder anderem Endgerät einloggen und hast Zugriff auf alle deine schnaqs."]
    [:p "Nun ist es auch möglich erweiterte Funktionen, wie Beiträge editieren, zu verwenden, da du nun eine Identität auf unserer Plattform hast 👍"]
    [:p "Jederzeit kannst du uns kontaktieren und die Einsicht oder Löschung deiner Daten beantragen."]]

   :privacy.link-to-privacy/lead "Mehr Informationen findest du in unserer ausführlichen"
   :privacy/note "Datenschutzerklärung"

   :privacy.extended/heading "Datenschutzerklärung"
   :privacy.extended/subheading "Wir handeln DSGVO konform"
   :privacy.extended.intro/title "Allgemeines zur Datenverarbeitung"
   :privacy.extended.intro/body
   [:<>
    [:p "Wir verarbeiten personenbezogene Daten grundsätzlich nur, soweit dies zur Bereitstellung einer funktionsfähigen Website sowie unserer Inhalte erforderlich ist. Die Verarbeitung personenbezogener Daten erfolgt regelmäßig nur nach Einwilligung der Nutzer:innen."]
    [:p "Soweit für Verarbeitungsvorgänge personenbezogener Daten eine Einwilligung notwendig ist, dient Art. 6 Abs. 1 lit. a EU-Datenschutzgrundverordnung (DSGVO) als Rechtsgrundlage.\nIst die Verarbeitung zur Wahrung eines berechtigten Interesses unsererseits oder eines Dritten erforderlich und überwiegen Ihre Interessen, Grundrechte und Grundfreiheiten das erstgenannte Interesse nicht, so dient Art. 6 Abs. 1 lit. f DSGVO als Rechtsgrundlage für die Verarbeitung. "]
    [:p "Personenbezogene Daten werden gelöscht, sobald der Zweck der Speicherung entfällt. Eine Speicherung kann darüber hinaus erfolgen, wenn dies durch den europäischen oder nationalen Gesetzgeber in unionsrechtlichen Verordnungen, Gesetzen oder sonstigen Vorschriften, denen wir unterliegen, vorgesehen wurde. Eine Löschung der Daten erfolgt auch dann, wenn eine durch die genannten Normen vorgeschriebene Speicherfrist abläuft."]]
   :privacy.extended.logfiles/title "Bereitstellung der Website"
   :privacy.extended.logfiles/body
   [:<>
    [:p "Bei jedem Aufruf unserer Internetseite erfasst unser System automatisiert Verbindungsdaten und Informationen (Browsertyp / verwendete Version, Betriebssystem, IP-Adresse, Datum und Uhrzeit des Zugriffs, Websites, von denen auf unsere Internetseite gelangt wurde, Websites, die über unsere Website aufgerufen werden) vom Computersystem des aufrufenden Rechners. Das ist ganz normales Verhalten der meisten Browser. Die Daten werden nur für die Dauer der Nutzung von schnaq im Arbeitsspeicher des Servers gehalten. Eine Speicherung dieser Daten zusammen mit anderen personenbezogenen Daten der Nutzer:innen findet nicht statt. Rechtsgrundlage für die vorübergehende Speicherung der Daten ist Art. 6 Abs. 1 lit. f DSGVO."]
    [:p "Die vorübergehende Speicherung der IP-Adresse durch das System ist notwendig, um eine Auslieferung der Website an den Rechner der Nutzer:innen zu ermöglichen. Hierfür muss die IP-Adresse für die Dauer der Sitzung gespeichert bleiben. Der Browsertyp und die verwendete Version werden benötigt, um die Website auch auf verschiedenen Browsern optimal darzustellen. Die Daten dienen zur Optimierung der Website und zur Sicherstellung der Sicherheit unserer informationstechnischen Systeme. In diesen Zwecken liegt auch unser berechtigtes Interesse an der Datenverarbeitung nach Art. 6 Abs. 1 lit. f DSGVO."]
    [:p "Die Daten werden automatisch gelöscht, sobald sie für die Erreichung des Zweckes ihrer Erhebung nicht mehr erforderlich sind. Im Falle der Erfassung der Daten zur Bereitstellung der Website ist dies der Fall, wenn die jeweilige Sitzung beendet ist. Täglich, teilweise mehrfach, wird der Arbeitsspeicher mit allen Verbindungsdaten gelöscht. Eine darüberhinausgehende Speicherung findet nicht statt."]
    [:p "Die Erfassung der Daten zur Bereitstellung der Website ist für den Betrieb der Internetseite zwingend erforderlich. Es besteht folglich keine Widerspruchsmöglichkeit."]]
   :privacy.extended.cookies/title "Cookies"
   :privacy.extended.cookies/body
   [:<>
    [:p "Wir setzen auf unseren Seiten sogenannte Cookies ein. Cookies sind Datenpakete, die Ihr Browser auf unsere Veranlassung in Ihrem Endgerät speichert. Dabei werden zwei Arten von Cookies unterschieden: temporäre, sogenannte Session-Cookies, und persistente Cookies."]
    [:p "Session-Cookies werden automatisiert gelöscht, wenn Sie den Browser schließen. Diese speichern eine sogenannte Session-ID, mit welcher sich verschiedene Anfragen Ihres Browsers der gemeinsamen Sitzung zuordnen lassen. Dadurch kann Ihr Rechner wiedererkannt werden, wenn Sie auf unsere Website zurückkehren. Der Einsatz von Session Cookies ist erforderlich, damit wir Ihnen die Webseite zur Verfügung stellen können. Die Rechtsgrundlage für die Verarbeitung Ihrer personenbezogenen Daten unter Verwendung von Session-Cookies ist Art. 6 Abs. 1 lit. f DSGVO."]
    [:p "Persistente Cookies werden automatisiert nach einer vorgegebenen Dauer gelöscht, die sich je nach Cookie unterscheiden kann. Diese Cookies verbleiben für eine vordefinierte Zeit auf Ihrem Endgerät dienen in der Regel dazu, Sie bei einem erneuten Besuch unserer Homepage wiederzuerkennen. Der Einsatz von persistenten Cookies auf unserer Homepage erfolgt auf Rechtsgrundlage des Art. 6 Abs. 1 lit. f DSGVO."]
    [:p "Sie können Ihren Internetbrowser so einstellen, dass unsere Cookies nicht auf Ihrem Endgerät ablegt werden können oder bereits abgelegte Cookies gelöscht werden. Wenn Sie keine Cookies akzeptieren, kann dies zu Einschränkungen der Funktion der Internetseiten führen."]
    [:p "Konkret haben wir diese Arten von Cookies:"]
    [:ul
     [:li "CSRF-Token (Session-Cookie), womit beispielsweise das Kontaktformular vor unbeobachtetem Abschicken von Inhalten abgesichert wird. Es handelt sich hier um eine zufällige Anordnung von Zeichen, welche nur für den Versand des Formulars verwendet wird. Dieser Cookie wird nach dem Verlassen unserer Website gelöscht. Dieser Schutzmechanismus entspricht gängigen Sicherheitsstandards und kann beispielsweise "
      [:a {:href "https://de.wikipedia.org/wiki/Cross-Site-Request-Forgery"}
       "hier"]
      " weiter recherchiert werden."]
     [:li "Login-Cookie (persistenter Cookie, auth.schnaq.com), welcher Sie als den:die Benutzer:in wiedererkennt, mit dem Sie sich eingeloggt haben. Nach 15 Minuten läuft Ihr Cookie ab und wird gelöscht. Wenn Sie diesen Cookie vorher löschen, müssen Sie sich beim nächsten Besuch der Seite erneut einloggen. Unseren Authentifizierungsserver finden Sie hier: https://auth.schnaq.com"]
     [:li "schnaq-analytics (persistenter Cookie, schnaq.com) wird gesetzt, wenn Sie der erweiterten Analyse Ihres anonymisierten Nutzer:innenverhaltens zustimmen. Alle Daten werden hierbei DSGVO-konform und ohne Rückschluss auf Sie als Person verarbeitet und dienen uns dazu Probleme auf schnaq schneller zu identifizieren und zu beheben."]]
    [:p "Alle von uns eingesetzten Cookies generieren zufällige Zeichenketten, die zum Abgleich mit korrespondierenden Zeichenketten auf unserem Server verwendet werden."]]

   :privacy.extended.personal-data/title "Persönliche Daten"
   :privacy.extended.personal-data/body
   [:<>
    [:h4 "Verwendung von schnaq ohne Nutzer:innen Accounts"]
    [:p "Wenn Sie schnaq verwenden ohne sich zu registrieren, sind so sogenannte \"Anonyme Nutzer:in\". Dabei werden zusätzlich zu den für den Serverbetrieb notwendigen Daten nur Ihr Beitrag und ein optionaler selbstgewählter Name abgespeichert werden. Beim Speichern des Beitrags wird dann diese Zeichenkette lose mit dem Beitrag abgesichert. Eine Zuordnung zu einer Identität erfolgt dabei nicht. Nimmt jemand mit demselben Namen an irgendeinem schnaq teil, so erscheinen die Beiträge nach außen hin so als kämen sie von der selben Person."]
    [:p "Mit dem Abschicken Ihres Beitrages stimmen Sie der Speicherung zu. Da wir später nicht mehr nachvollziehen können, von wem der Beitrag stand, haben Sie kein Recht darauf diesen Beitrag zu löschen, denn es fehlt der Nachweis der Autor:innenschaft."]
    [:h4 "Verwendung von schnaq als registrierte:r Nutzer:in"]
    [:p "Bei der Registrierung werden von Ihnen Ihre Mailadresse und Ihr Vor- und Nachname gespeichert. Diese sind für den Betrieb von schnaq erforderlich, die Erfassung erfolgt somit nach Art. 6 Abs. 1 lit. f DSGVO. Die Registrierung ist für den normalen Betrieb von schnaq optional. Mit der Mailadresse werden automatische Benachrichtigungen auf neue Beiträge ermöglicht. Mit den Namen werden Ihre Beiträge auf der Oberfläche von schnaq zusammen dargestellt. Auch weitere Zugehörigkeiten, beispielsweise zu den Hubs oder weiteren schnaqs, werden damit visuell dargestellt."]
    [:p "Diese Daten werden auf unseren eigenen Servern gespeichert und nicht an Dritte weitergegeben."]
    [:p "Es gibt Möglichkeiten das eigene Nutzer:innenprofil zu erweitern. Dazu gehört beispielsweise ein eigenes optionales Profilbild hochzuladen. Dieses Profilbild wird dann als Ihr Avatar dargestellt und immer dann präsentiert, wenn Ihr Nutzer:innenaccount in Erscheinung tritt, beispielsweise wenn man sich Ihre Beiträge anschaut."]
    [:h4 "Textbeiträge"]
    [:p "Die Textbeiträge müssen von Ihnen selbst stammen und dürfen keine Urheberrechte verletzen. Die Textbeiträge werden nicht an Dritte weitergegeben. Intern können Ihre Beiträge für weitere wissenschaftliche Auswertungen und dem Training von eigenen neuronalen Netzen verwendet werden. Sie verlieren dabei niemals Ihre Autor:innenschaft an diesen Beiträgen. Damit werden beispielsweise automatisiert maschinell erstellte Zusammenfassungen oder Statistiken berechnet. Diese Zusammenfassungen und Statistiken sind für die Auswertung Ihres schnaqs vorgesehen und werden an keine Dritten weitergegeben."]]
   :privacy.extended.matomo/title "Webanalyse durch Matomo (ehemals PIWIK)"
   :privacy.extended.matomo/body
   [:<>
    [:h4 "Beschreibung und Umfang der Datenverarbeitung"]
    [:p "Wir nutzen auf unserer Website das Open-Source-Software-Tool Matomo (ehemals PIWIK) zur Analyse der Nutzung unseres Internet-Auftritts. Uns interessiert zum Beispiel, welche Seiten wie häufig aufgerufen werden und ob dabei Smartphones, Tablets oder Rechner mit großen Bildschirmen eingesetzt werden. Die Software setzt keinen Cookie und erstellt kein Profil der Besucher:innen. Werden Einzelseiten unserer Website aufgerufen, so werden folgende Daten gespeichert:"]
    [:ol
     [:li "Zwei Bytes der IP-Adresse des aufrufenden Systems"]
     [:li "Die aufgerufene Webseite"]
     [:li "Die Website, über die unsere Webseite gelangt aufgerufen wurde (Referrer)"]
     [:li "Die Unterseiten, die von der aufgerufenen Webseite aus aufgerufen werden"]
     [:li "Die Verweildauer auf der Webseite"]
     [:li "Die Häufigkeit des Aufrufs der Webseite"]]
    [:p "Matomo ist so eingestellt, dass die IP-Adressen nicht vollständig gespeichert werden, sondern zwei Bytes der IP-Adresse maskiert werden (Bsp.: 192.168.xxx.xxx). Auf diese Weise ist eine Zuordnung der gekürzten IP-Adresse zum aufrufenden Rechner nicht mehr möglich."]
    [:p "Matomo kommt ausschließlich auf Servern von schnaq zum Einsatz. Eine Speicherung der personenbezogenen Daten der Nutzer:innen findet nur dort statt. Eine Weitergabe der Daten an Dritte erfolgt nicht."]
    [:h4 "Zweck der Datenverarbeitung"]
    [:p "Die Verarbeitung der anonymisierten Daten der Nutzer:innen ermöglicht uns eine Analyse der Nutzung unserer Webseite. Wir sind in durch die Auswertung der gewonnen Daten in der Lage, Informationen über die Nutzung der einzelnen Komponenten unserer Webseite zusammenzustellen. Dies hilft uns dabei unsere Dienste und deren Nutzer:innenfreundlichkeit stetig zu verbessern. Durch die Anonymisierung der IP-Adresse wird dem Interesse der:die Nutzer:in an deren Schutz personenbezogener Daten hinreichend Rechnung getragen."]
    [:p "Es werden keine Profile erstellt, die uns einen tieferen Einblick in das Nutzungsverhalten der einzelnen Nutzer:innen geben würden. Die Auswertung erfolgt ausschließlich anonymisiert und aggregiert, dass kein Schluss auf einzelne Personen zu ziehen ist."]
    [:p "Der Einsatz von Matomo auf unserer Homepage erfolgt auf Rechtsgrundlage des Art. 6 Abs. 1 lit. f DSGVO."]]
   :privacy.extended.hotjar/title "Nutzer:innenverhalten verstehen mit Hotjar"
   :privacy.extended.hotjar/body
   [:<>
    [:h4 "Beschreibung und Umfang der Datenverarbeitung"]
    [:p "Rein optional und nur mit Ihrer Einstimmung nutzen wir das Tool Hotjar zur tieferen Analyse und zum Verständnis der Nutzung unserer Anwendungen. Damit können wir Probleme im Design und Aufbau der Seite besser verstehen und schneller reagieren. Ohne solche Tools müssten wir nur raten und könnten nicht schnell und einfach das Problem direkt beheben können."]
    [:p "Wir nutzen Hotjar, um die Bedürfnisse unserer Nutzer besser zu verstehen und das Angebot und die Erfahrung auf dieser Webseite zu optimieren. Mithilfe der Technologie von Hotjar bekommen wir ein besseres Verständnis von den Erfahrungen unserer Nutzer (z.B. wie viel Zeit Nutzer auf welchen Seiten verbringen, welche Links sie anklicken, was sie mögen und was nicht etc.) und das hilft uns, unser Angebot am Feedback unserer Nutzer:innen auszurichten. Hotjar arbeitet mit Cookies und anderen Technologien, um Daten über das Verhalten unserer Nutzer:innen und über ihre Endgeräte zu erheben, insbesondere IP Adresse des Geräts (wird während Ihrer Website-Nutzung nur in anonymisierter Form erfasst und gespeichert), Bildschirmgröße, Gerätetyp (Unique Device Identifiers), Informationen über den verwendeten Browser, Standort (nur Land), zum Anzeigen unserer Webseite bevorzugte Sprache. Hotjar speichert diese Informationen in unserem Auftrag in einem pseudonymisierten Nutzerprofil. Hotjar ist es vertraglich verboten, die in unserem Auftrag erhobenen Daten zu verkaufen."]
    [:p "Alle Daten werden auf Servern in Irland gespeichert und verlassen nicht die Europäische Union."]
    [:p "Der Einsatz von Hotjar auf unserer Homepage erfolgt auf Rechtsgrundlage des Art. 6 Abs. 1 lit. a DSGVO und wird nur nach Ihrem explizitem Einverständnis eingebunden. Sie können jederzeit widersprechen."]
    [:p "Weitere Informationen finden Sie in unter dem Abschnitt 'about Hotjar' auf den Hilfeseiten von Hotjar."]]
   :privacy.extended.rights-of-the-affected/title "Rechte der Betroffenen"
   :privacy.extended.rights-of-the-affected/body
   [:<>
    [:p "Werden von Ihnen personenbezogene Daten verarbeitet, sind Sie Betroffene:r im Sinne der DSGVO und es stehen Ihnen die im weiteren beschrieben Rechte uns gegenüber zu. Richten Sie Ihr Verlangen bitte, am besten per E-Mail, an den o.g. Verantwortlichen."]
    [:p [:strong "Auskunft:"]
     " Sie haben das Recht, jederzeit von uns unentgeltliche Auskunft sowie Bestätigung über die zu Ihrer Person gespeicherten personenbezogenen Daten und eine Kopie dieser Auskunft zu erhalten."]
    [:p [:strong "Berichtigung:"]
     " Sie haben das Recht auf Berichtigung und/oder Vervollständigung, sofern die verarbeiteten personenbezogenen Daten, die Sie betreffen, unrichtig oder unvollständig sind."]
    [:p [:strong "Einschränkung der Verarbeitung:"]
     " Sie haben das Recht die Einschränkung der Verarbeitung zu verlangen, wenn eine der folgenden Voraussetzungen gegeben ist:"]
    [:ul
     [:li "Die Richtigkeit der personenbezogenen Daten wird von Ihnen bestritten, und zwar für eine Dauer, die es uns ermöglicht, die Richtigkeit der personenbezogenen Daten zu überprüfen. "]
     [:li "Die Verarbeitung ist unrechtmäßig, Sie lehnen die Löschung der personenbezogenen Daten ab und verlangen stattdessen die Einschränkung der Nutzung der personenbezogenen Daten. "]
     [:li "Wir benötigen die personenbezogenen Daten für die Zwecke der Verarbeitung nicht länger, Sie benötigen sie jedoch zur Geltendmachung, Ausübung oder Verteidigung von Rechtsansprüchen. "]
     [:li "Sie haben Widerspruch gegen die Verarbeitung gem. Art. 21 Abs. 1 DSGVO eingelegt und es steht noch nicht fest, ob unsere berechtigten Gründe gegenüber Ihren überwiegen. "]]
    [:p [:strong "Löschung:"]
     " Sie haben das Recht, dass die sie betreffenden personenbezogenen Daten unverzüglich gelöscht werden, sofern einer der folgenden Gründe zutrifft und soweit die Verarbeitung nicht erforderlich ist:"]
    [:ul
     [:li "Die personenbezogenen Daten wurden für solche Zwecke erhoben oder auf sonstige Weise verarbeitet, für welche sie nicht mehr notwendig sind. "]
     [:li "Sie widerrufen Ihre Einwilligung, auf die sich die Verarbeitung stützte und es fehlt an einer anderweitigen Rechtsgrundlage für die Verarbeitung. "]
     [:li "Sie legen gemäß Art. 21 Abs. 1 DSGVO Widerspruch gegen die Verarbeitung ein, und es liegen keine vorrangigen berechtigten Gründe für die Verarbeitung vor, oder Sie legen gemäß Art. 21 Abs. 2 DSGVO Widerspruch gegen die Verarbeitung ein. "]
     [:li "Die personenbezogenen Daten wurden unrechtmäßig verarbeitet. "]
     [:li "Die Löschung der personenbezogenen Daten ist zur Erfüllung einer rechtlichen Verpflichtung nach dem Unionsrecht oder dem Recht der Mitgliedstaaten erforderlich, dem wir unterliegen. "]
     [:li "Die personenbezogenen Daten wurden in Bezug auf angebotene Dienste der Informationsgesellschaft gemäß Art. 8 Abs. 1 DSGVO erhoben. "]]
    [:p [:strong "Datenübertragbarkeit:"]
     " Sie haben das Recht, die Sie betreffenden personenbezogenen Daten, die Sie dem Verantwortlichen bereitgestellt haben, in einem strukturierten, gängigen und maschinenlesbaren Format zu erhalten. Außerdem haben Sie das Recht diese Daten einem anderen Verantwortlichen ohne Behinderung durch den Verantwortlichen, dem die personenbezogenen Daten bereitgestellt wurden, zu übermitteln. In Ausübung dieses Rechts haben Sie ferner das Recht, zu erwirken, dass die Sie betreffenden personenbezogenen Daten direkt von uns einem anderen Verantwortlichen übermittelt werden, soweit dies technisch machbar ist. Freiheiten und Rechte anderer Personen dürfen hierdurch nicht beeinträchtigt werden."]
    [:p [:strong "Widerspruch:"]
     " Sie haben das Recht, jederzeit gegen die Verarbeitung Sie betreffender personenbezogener Daten, die aufgrund von Art. 6 Abs. 1 lit. f DSGVO erfolgt, Widerspruch einzulegen. Wir verarbeiten die personenbezogenen Daten im Falle des Widerspruchs nicht mehr, es sei denn, wir können zwingende schutzwürdige Gründe für die Verarbeitung nachweisen, die gegenüber Ihren Interessen, Rechten und Freiheiten überwiegen, oder die Verarbeitung dient der Geltendmachung, Ausübung oder Verteidigung von Rechtsansprüchen."]
    [:p [:strong "Widerruf der Einwilligung:"]
     " Sie haben das Recht, Ihre datenschutzrechtliche Einwilligungserklärung jederzeit zu widerrufen. Durch den Widerruf der Einwilligung wird die Rechtmäßigkeit der aufgrund der Einwilligung bis zum Widerruf erfolgten Verarbeitung nicht berührt."]]
   :privacy.extended.right-to-complain/title "Recht auf Beschwerde bei einer Aufsichtsbehörde"
   :privacy.extended.right-to-complain/body
   [:<>
    [:p "Unbeschadet eines anderweitigen verwaltungsrechtlichen oder gerichtlichen Rechtsbehelfs steht Ihnen das Recht auf Beschwerde bei einer Aufsichtsbehörde, insbesondere in dem Mitgliedstaat ihres Aufenthaltsorts, zu, wenn Sie der Ansicht sind, dass die Verarbeitung der Sie betreffenden personenbezogenen Daten gegen die DSGVO verstößt.\nDie für den Betreiber dieser Seite zustände Datenschutzaufsichtsbehörde ist:"]
    [:p "Die Landesbeauftragte für Datenschutz und Informationsfreiheit NRW, Kavalleriestr. 2-4, 40102 Düsseldorf, Tel.: +49211/38424-0, E-Mail: poststelle{at}ldi.nrw.de"]]
   :privacy.extended.hosting/title "Hosting der Webseite"
   :privacy.extended.hosting/body
   [:<>
    [:p "Der Internetauftritt von schnaq wird auf Servern der Hetzner Online GmbH in Deutschland gehostet. Bezüglich weiterer Informationen verweisen wir auf die Webseiten der Hetzner Online GmbH."]
    [:h4 "Abschluss eines Auftragsdatenverarbeitungsvertrags (AV-Vertrag)"]
    [:p "Wir haben mit der Hetzner Online GmbH einen AV-Vertrag abgeschlossen, welcher unsere Kunden schützt und Hetzner verpflichtet die erhobenen Daten nicht an Dritte weiterzugeben."]]
   :privacy.extended.responsible/title "Angaben gemäß § 5 TMG"
   :privacy.extended.responsible/body
   [:<>
    [:p
     "schnaq GmbH i.Gr." [:br]
     "Speditionsstraße 15A" [:br]
     "40221 Düsseldorf" [:br]
     "Deutschland" [:br]
     (toolbelt/obfuscate-mail "info@schnaq.com")]
    [:p "Vertreten durch die Geschäftsführung:" [:br]
     "Dr. Christian Meter, Dr. Alexander Schneider und Michael Birkhoff"]
    [:p "Rechtlich bindend ist die deutsche Fassung dieser Seite."]]

   ;; About us
   :about-us.unity/title "Die Einheit schnaq"
   :about-us.unity/body [:<> [:p "schnaq bringt digitale Diskussionen in die Zukunft. Wir bieten Unternehmen die Möglichkeit transparente Entscheidungsprozesse durchzuführen, in denen das gesamte Team gehört werden kann, sodass chancengleiche und nachvollziehbare Diskurse stattfinden. Unsere Analysen helfen Ihnen zu verstehen, welches Teammitglied zu wenig gehört wurde und mit einbezogen werden sollte. Durch das Teilen von Wissen über Diskussionen auf unserer Plattform, verhindern wir Wissenssilos und implizites Firmenwissen, indem wir das Firmenwissen für alle verfügbar machen, sei es geschriebene oder später auch gesprochene Kommunikation."]
                         [:p "Unser Team steht dafür ein, dass jede Stimme gehört werden kann!"]]

   :about-us.value/title "Unsere Werte"
   :about-us.value/subtitle "Wir folgen Werten, die unser Handeln und unsere Produkte definieren."
   :about-us.honesty/title "Ehrlichkeit"
   :about-us.honesty/body "Wir setzen darauf, unsere Produkte und deren Fähigkeiten ehrlich und ohne Übertreibung darzustellen. Wir sind fest davon überzeugt, dass unsere Produkte für sich selbst stehen können, ohne jegliche Überhöhung."
   :about-us.collaborate/title "Kollaborationswille"
   :about-us.collaborate/body "Wir glauben fest daran, dass wir gemeinsam mehr erreichen können als alleine. Deshalb pflegen wir gerne eine Kultur der Kollaboration. Egal ob untereinander im Team oder mit unseren Kund:innen und Kooperationspartner:innen. Zusammen können wir Großartiges schaffen."
   :about-us.action/title "Tatendrang"
   :about-us.action/body "Wir treffen Entscheidungen nicht aus dem Blauen heraus, sondern gestützt auf allen Daten, die wir zur Verfügung haben. Aber sobald nach Diskussionen eine Entscheidung getroffen wurde, stehen wir gemeinsam dahinter und ziehen an einem Strang, um effizient vorwärts zu kommen."
   :about-us.quality/title "Qualität"
   :about-us.quality/body "Wir sind stolz auf unsere Arbeit und das, was wir schaffen. Wir mögen unser Werk, wir sehen es als einen Teil von uns und wir haben Spaß daran, Menschen überall auf der Welt miteinander zu verbinden. Deshalb liegt es uns am Herzen, dass unsere Produkte von größtmöglicher Qualität sind."
   :about-us.diversity/title "Vielfalt"
   :about-us.diversity/body "Jeder Mensch bringt seine einzigartige Perspektive auf die Welt mit. Und gerade weil wir Menschen miteinander in Kontakt bringen, wollen wir, dass möglichst viele dieser Perspektiven in unser Schaffen einfließen."

   :about-us.numbers/title "schnaq in Zahlen"
   :about-us.numbers/research "Jahre Forschung"
   :about-us.numbers/users "Nutzer:innen"
   :about-us.numbers/statements "Aussagen strukturiert"
   :about-us.numbers/loc "Zeilen Code"

   :about-us.team/title "Team im Fokus"
   :about-us.team/alexander "Co-Gründer - Operative Führung"
   :about-us.team/christian "Co-Gründer - Technische Führung"
   :about-us.team/mike "Co-Gründer - Führung Produktdesign"

   :about-us.page/heading "Über uns"
   :about-us.page/subheading "Informationen zu uns"

   ;; Legal Note
   :legal-note.page/heading "Impressum"
   :legal-note.page/disclaimer "Haftungsausschluss (Disclaimer)"

   :legal-note.contents/title "Haftung für Inhalte"
   :legal-note.contents/body "Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich. Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte fremde Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung von Informationen nach den allgemeinen Gesetzen bleiben hiervon unberührt. Eine diesbezügliche Haftung ist jedoch erst ab dem Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung möglich. Bei Bekanntwerden von entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen."
   :legal-note.links/title "Haftung für Links"
   :legal-note.links/body "Unser Angebot enthält Links zu externen Webseiten Dritter, auf deren Inhalte wir keinen Einfluss haben. Deshalb können wir für diese fremden Inhalte auch keine Gewähr übernehmen. Für die Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt der Verlinkung auf mögliche Rechtsverstöße überprüft. Rechtswidrige Inhalte waren zum Zeitpunkt der Verlinkung nicht erkennbar. Eine permanente inhaltliche Kontrolle der verlinkten Seiten ist jedoch ohne konkrete Anhaltspunkte einer Rechtsverletzung nicht zumutbar. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Links umgehend entfernen."
   :legal-note.copyright/title "Urheberrecht"
   :legal-note.copyright/body "Die durch die Seitenbetreiber erstellten Inhalte und Werke auf diesen Seiten unterliegen dem deutschen Urheberrecht. Die Vervielfältigung, Bearbeitung, Verbreitung und jede Art der Verwertung außerhalb der Grenzen des Urheberrechtes bedürfen der schriftlichen Zustimmung des jeweiligen Autors bzw. Erstellers. Downloads und Kopien dieser Seite sind nur für den privaten, nicht kommerziellen Gebrauch gestattet. Soweit die Inhalte auf dieser Seite nicht vom Betreiber erstellt wurden, werden die Urheberrechte Dritter beachtet. Insbesondere werden Inhalte Dritter als solche gekennzeichnet. Sollten Sie trotzdem auf eine Urheberrechtsverletzung aufmerksam werden, bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Inhalte umgehend entfernen."
   :legal-note.privacy/title "Datenschutzerklärung"
   :legal-note.privacy/body "Unsere Datenschutzerklärung findest du hier."

   ;; Celebrations
   :celebrations.schnaq-filled/title "🎉 Glückwunsch 🎉"
   :celebrations.schnaq-filled/lead "Du hast einen neuen schnaq mit einer ersten Aussage befüllt. Das ist der erste Meilenstein zu einer erfolgreichen Diskussion 💪"
   :celebrations.schnaq-filled/share-now "Teile nun den schnaq mit deinem Team!"
   :celebrations.schnaq-filled/button "Optionen zum Teilen"
   :celebrations.first-schnaq-created/title "Du hast deinen ersten schnaq erstellt 🎈"
   :celebrations.first-schnaq-created/lead "Möchtest du deinen schnaq mit einem Account verbinden? Dann registriere dich mit wenigen Klicks 🚀"

   ;; schnaqs not found
   :schnaqs.not-found/alert-lead "Keine schnaqs gefunden"
   :schnaqs.not-found/alert-body "Erstelle einen schnaq oder lass dich einladen"

   ;; Admin Center
   :schnaq/educate-on-link-text "Teile den untenstehenden Link mit deinen Kolleg:innen und Freund:innen."
   :schnaq/educate-on-link-text-subtitle "Teilnahme ist für alle, die den Link kennen, möglich!"
   :schnaq.admin/heading "Admin-Center"
   :schnaq.admin/subheading "schnaq: \"%s\""
   :schnaq.admin.edit.link/header "Zugang zum Admin-Center"
   :schnaq.admin.edit.link/primer "Verwaltung ist Arbeit, lass' dir dabei helfen!"
   :schnaq.admin.edit.link/admin "Zugang zum Admin-Center per Mail"
   :schnaq.admin.edit.link/admin-privileges "Editieren und Vorschläge verwalten"
   :schnaq.admin.edit.link.form/label "E-Mail Adresse der Administrator:innen"
   :schnaq.admin.edit.link.form/placeholder "Eine E-Mailadresse eingeben"
   :schnaq.admin.edit.link.form/submit-button "Link verschicken"
   :schnaq.admin.invite/via-link "Link verteilen"
   :schnaq.admin.invite/via-mail "Per E-Mail einladen"
   :schnaq.admin.edit/administrate "schnaq verwalten"
   :schnaq.export/as-text "schnaq als Textdatei runterladen"
   :schnaq.admin/tooltip "Schnaq verwalten"
   :share-link/copy "Zugangslink kopieren"
   :share-link/via "Per Link"
   :share-access-code/via "Per Code"
   :share-access-code/title-1 "Gehe auf"
   :share-access-code/title-2 "und gib folgenden Zugangscode ein:"
   :share-qr-code/via "Per QR Code"

   :sharing/tooltip "schnaq teilen"
   :sharing.modal/title "Teile deinen schnaq"
   :sharing.modal/lead "Lade dein ganzes Team mit ein, um diesen schnaq mit Wissen zu befüllen"
   :sharing.modal/schnaqqi-help "Befülle den schnaq schonmal mit deinen Ideen. Deine Kolleg:innen finden so einen leichteren Einstieg."
   :sharing.modal/qanda-help "Über die Q&A Ansicht können Teilnehmer Fragen zur Veranstaltung stellen. Entweder direkt per Link oder per Code auf www.schnaq.app!"

   ;; Discussion Language
   :discussion/create-argument-action "Beitrag hinzufügen"
   :discussion/add-premise-supporting "Ich möchte die Aussage unterstützen"
   :discussion/add-premise-against "Ich habe einen Grund dagegen"
   :discussion/add-premise-neutral "Ich möchte etwas ergänzen"
   :discussion.add.button/support "Dafür"
   :discussion.add.button/attack "Dagegen"
   :discussion.add.button/neutral "Neutral"
   :discussion.add.statement/new "Neuer Beitrag von dir"
   :discussion.badges/user-overview "Alle Teilnehmer:innen"
   :discussion.badges/delete-statement "Beitrag löschen"
   :discussion.badges/posts "Beiträge"
   :discussion.badges/delete-statement-confirmation "Möchtest du den Beitrag wirklich löschen?"
   :discussion.notification/new-content-title "Neuer Beitrag!"
   :discussion.notification/new-content-body "Dein Beitrag wurde erfolgreich gespeichert."
   :discussion.badges/edit-statement "editieren"
   :discussion.badges/statement-by "von"
   :discussion.badges/new "Neu"
   :discussion.button/text "Übersicht"

   ;; Q & A
   :qanda/add-question-label "Stell deine Frage"
   :qanda/add-question "Gib deine Frage hier ein …"
   :qanda.button/text "Q&A"
   :qanda.button/submit "Frage stellen"
   :qanda.state/read-only-warning "Dieser schnaq ist schreibgeschützt, du kannst aktuell keine Fragen stellen."
   :qanda.call-to-action/display-code "Dein Zugangscode:"
   :qanda.call-to-action/intro-1 "Lade weitere Menschen ein, indem sie zu"
   :qanda.call-to-action/intro-2 "navigieren und den Code dort eingeben."
   :qanda.call-to-action/help "Alle Optionen zum Teilen deines schnaqs findest du oben rechts in der Navigationsleiste"
   :qanda.search/similar-results "Ähnliche Fragen"
   :qanda.search/similar-results-explanation-1 "Bereits gestellte ähnliche Fragen erscheinen hier. Du kannst diese mit "
   :qanda.search/similar-results-explanation-2 "für dich als relevant markieren."
   :qanda.button.mark/as-answer "Antwort markieren"
   :qanda.button.mark/as-unanswered "Markierung aufheben"
   :qanda.button.show/replies "Beiträge anzeigen"
   :qanda.button.hide/replies "Beiträge verbergen"

   :schnaqs/header "Deine schnaqs"
   :schnaqs/subheader "Auf diese schnaqs hast du Zugriff"
   :schnaqs/author "Autor"
   :schnaqs/schnaq "schnaq"

   ;; Feedback
   :feedbacks.overview/header "Rückmeldungen"
   :feedbacks.overview/subheader "Alle abgegebenen Rückmeldungen"
   :feedbacks.overview/description "Beschreibung"
   :feedbacks.overview/table-header "Es gibt %s Rückmeldungen 🥳!"
   :feedbacks.overview/when? "Wann?"
   :feedbacks.overview/contact-name "Von"
   :feedbacks.overview/contact-mail "E-Mail"
   :feedbacks/button "Feedback"
   :feedbacks/screenshot "Screenshot"
   :feedbacks.modal/primer "Feedback ist wichtig! Wir freuen uns sehr über
     jede Art von Feedback, je ehrlicher desto besser 🥳 Hinterlasse uns
     gerne einen kleinen Kommentar und hilf uns damit diese Software
     weiter zu verbessern. Dankeschön!"
   :feedbacks.modal/contact-name "Dein Name"
   :feedbacks.modal/contact-mail "E-Mail Adresse"
   :feedbacks.modal/description "Deine Rückmeldung"
   :feedbacks.modal/optional "Optional"
   :feedbacks.modal/screenshot "Foto der Anwendung mit abschicken?"
   :feedbacks.modal/disclaimer "Deine Daten werden nur auf unseren Servern
     abgespeichert und keinen Dritten zugänglich gemacht."
   :feedbacks.notification/title "Vielen Dank für deine Rückmeldung!"
   :feedbacks.notification/body "Dein Feedback wurde erfolgreich an uns
     gesendet 🎉"

   ;; analytics
   :analytics/heading "Analytics"
   :analytics/overall-discussions "Schnaqs erstellt"
   :analytics/user-numbers "Usernamen angelegt"
   :analytics/registered-users-numbers "Registrierte Nutzer:innen"
   :analytics/average-statements-title "Durchschnittliche Zahl an Beiträgen pro schnaq"
   :analytics/statements-num-title "Anzahl Statements"
   :analytics/active-users-num-title "Aktive User (min. 1 Beitrag)"
   :analytics/statement-lengths-title "Beitragslängen"
   :analytics/statement-types-title "Argumenttypen"
   :analytics/labels-stats "Label Nutzung"
   :analytics/fetch-data-button "Hole Daten"

   ;; Supporters
   :supporters/heading "Unterstützt vom Wirtschaftsministerium des Landes Nordrhein-Westfalen"

   ;; Testimonials
   :testimonials/heading "Die haben schon mit uns geschnaqqt"
   :testimonials.doctronic/company "doctronic GmbH & Co. KG"
   :testimonials.doctronic/quote "Wir beobachten die Entwicklung von schnaq mit großem Interesse für den eigenen Einsatz und für den Einsatz bei unseren Kunden."
   :testimonials.doctronic/author "Ingo Küper, Geschäftsführer"

   :testimonials.leetdesk/company "Leetdesk – ODYN GmbH"
   :testimonials.leetdesk/quote "Auch bei unserem recht kleinen Team ist es hilfreich, unsere Gedanken zu sammeln, um eine Diskussion ordentlich antreiben zu können. Dies ist uns durch schnaq sehr gut ermöglicht worden, effizientere Meetings waren die Folge."
   :testimonials.leetdesk/author "Meiko Tse, Geschäftsführer"

   :testimonials.hhu/company "Heinrich-Heine-Universität Düsseldorf"
   :testimonials.bjorn/quote "Zur internen Koordination und Abstimmung haben wir schnaq verwendet, sodass alle Teilnehmer:innen ihre Gedanken aufschreiben und in einen Kontext setzen konnten. Abschließend wurden konkrete Aufgaben abgeleitet und wir konnten strukturiert in die Arbeitsphase gehen."
   :testimonials.bjorn/author "Björn Ebbinghaus, Wissenschaftlicher Mitarbeiter"

   :testimonials.lokay/company "Mediator und Konfliktlösungsberater"
   :testimonials.lokay/quote "Ich hatte die Ehre, den Kolleg:innen in der Anfangsphase einmal ein Feedback geben zu dürfen und bin beeindruckt von diesem Geist der Wertigkeit und Praxisorientierung."
   :testimonials.lokay/author "Oliver Lokay, Mediator und Konfliktlösungsberater"

   :testimonials.hck/company "Chief Digital Officer"
   :testimonials.hck/quote "Als Experte der digitalen Transformation in Unternehmen habe ich das Potenzial von schnaq schnell erkannt und stehe dem Team seither als Mentor zur Verfügung. Eine starke Idee und ein kompetentes Gründerteam, von dem wir noch hören werden!"
   :testimonials.hck/author "Hans-Christoph Kaiser, CDO"

   :testimonials.franky/company "FoxBase GmbH"
   :testimonials.franky/quote "Schnaq ist Raketenwissenschaft im Backend, und Dreirad im Frontend."
   :testimonials.franky/author "Frank Stampa, Head of Sales"

   :testimonials.metro/company "Metro Digital"
   :testimonials.metro/quote "Als Asyncronous Working Evangelist schätze ich schnaq sehr, um Informationssilos aufzubrechen und diese transparent und übersichtlichen allen Mitarbeiter:innen zur Verfügung zu stellen."
   :testimonials.metro/author "Dr. Tobias Schröder, Product Manager"

   :testimonials.eugenbialon/company "EugenBialonArchitekt GmbH"
   :testimonials.eugenbialon/quote "Im Architekturbüro laufen etliche parallele Projekte mit einer Vielzahl von beteiligten Akteuren ab. Schnaq unterstützt uns dabei im projektübergreifenden Informationsmanagement, ob im Büro, im Homeoffice oder auf der Baustelle!"
   :testimonials.eugenbialon/author "Dipl.-Ing. Eugen Bialon, Geschäftsführender Gesellschafter und Architekt, EugenBialonArchitekt GmbH"

   :testimonials.bialon/quote "Mit schnaq gelingt es mir, die Masse an Informationen rund um die Digitalisierung einer Universität strukturiert und übersichtlich aufzubereiten. So kann ich in jedem Projektkontext schnell agieren."
   :testimonials.bialon/author "Raphael Bialon, Persönlicher Referent des Prorektors für Digitalisierung, Heinrich-Heine-Universität Düsseldorf"

   :testimonials.sensor/company "Enterprise Unternehmen im Bereich Sensorik und Messtechnik"
   :testimonials.sensor/quote "Im Rahmen der Einarbeitung in neue Serviceprodukte haben wir schnaq genutzt, um im ganzen Team zentral unsere Ideen und offene Fragen zu sammeln. Somit konnten wir gut vorbereitet in Austauschmeetings gehen und konkrete Punkte ansprechen.\nJetzt schreiben wir Fragen auf und diskutieren sie und können auch in drei Wochen noch nachvollziehen, was wir beschlossen haben."
   :testimonials.sensor/author "Florian Clever, Kundenberater Service Prozessautomation"

   :testimonials.bib/company "Wissenschaftliche Mitarbeiterin"
   :testimonials.bib/quote "Wir konnten durch schnaq auch bei online stattfindenden Veranstaltungen Diskussionen und Austausch zwischen den Studierenden anregen, was den Erfolg der Events maßgeblich beeinflusst hat."
   :testimonials.bib/author "Frauke Kling, Wissenschaftliche Mitarbeiterin"

   ;; User related
   :user.button/set-name "Name speichern"
   :user.button/set-name-placeholder "Dein Name"
   :user.button/change-name "Namen ändern"
   :user.button/success-body "Name erfolgreich gespeichert"
   :user.set-name.modal/header "Gib einen Namen ein"
   :user.set-name.modal/primer "Der Name wird den anderen Teilnehmer:innen im schnaq angezeigt."
   :user/login "Anmelden"
   :user/logout "Logout"
   :user/register "Kostenlos Registrieren"
   :user.profile/settings "Einstellungen"
   :user.action/link-copied "Link kopiert!"
   :user.action/link-copied-body "Teile den Link mit anderen, um ihnen Zugriff zu geben."
   :user/edit-account "Benutzerkonto verwalten"
   :user/edit-notifications "Benachrichtigungen verwalten"
   :user/edit-hubs "Hubs verwalten"
   :user.settings "Einstellungen"
   :user.keycloak-settings "Erweiterte Einstellungen"
   :user.settings/header "Nutzer:innendaten verwalten"
   :user.settings/info "Persönliche Informationen"
   :user.settings/notifications "Benachrichtigungen"
   :user.settings/hubs "Hubs"
   :user.settings/change-name "Namen ändern"
   :user.settings.button/change-account-information "Änderungen speichern"
   :user.settings.profile-picture-title/success "Profilbild erfolgreich gesetzt"
   :user.settings.profile-picture-body/success "Profilbild wurde hochgeladen und gespeichert. Ggf. Seite neuladen um aktualisiertes Bild zu sehen."
   :user.settings.profile-picture-title/error "Hochladen des Profilbildes fehlgeschlagen"
   :user.settings.profile-picture-too-large/error "Dein Profilbild ist %d Bytes groß, die maximal erlaubte Größe ist %d Bytes. Bitte lade ein kleineres Bild hoch."
   :user.settings.profile-picture.errors/scaling "Dein Profilbild konnte nicht konvertiert werden. Vielleicht ist das Bild kaputt. Bitte probiere ein anderes Bild oder kontaktiere uns."
   :user.settings.profile-picture.errors/invalid-file-type "Dein Profilbild hat den falschen Dateitypen. Erlaubt sind: %s"
   :user.settings.profile-picture.errors/default "Etwas ist beim Hochladen des Bildes schief gelaufen. Bitte versuche es erneut."

   ;; notification settings
   :user.notifications/header "Benachrichtigungen verwalten"
   :user.notifications/mails "E-Mail Benachrichtigungen"
   :user.notifications/info "Du erhältst nur Benachrichtigungen, wenn es neue Beiträge in deinen besuchten schnaqs gibt."
   :user.notifications.set-all-to-read/button "Alles als gelesen markieren"
   :user.notifications.set-all-to-read/info "Du erhältst noch Benachrichtigungen von alten Diskussionen? Kein Problem, setze einfach alles auf gelesen und erhalte nur noch Benachrichtigungen für neue Diskussionen."

   ; mail interval
   :notification-mail-interval/daily "Täglich"
   :notification-mail-interval/weekly "Wöchentlich"
   :notification-mail-interval/never "Niemals"

   ;; Errors
   :errors/generic "Es ist ein Fehler aufgetreten"

   :error.generic/contact-us [:span "Solltest du hier landen nachdem du etwas auf schnaq.com angeklickt hast, gib uns gerne Bescheid unter " [:a {:href "mailto:info@schnaq.com"} "info@schnaq.com"]]

   :error.404/heading "Diese Seite existiert nicht 🙉"
   :error.404/body "Die URL, der du gefolgt bist, existiert leider nicht. Möglicherweise hat sich ein Tippfehler
     oder ein Zeichen zu viel eingeschlichen."

   :error.403/heading "Du hast nicht die Berechtigung diese Seite aufzurufen 🧙‍♂️"
   :error.403/body "Dir fehlt die Berechtigung diese Seite aufzurufen oder es handelt sich um einen Tippfehler in deiner URL."

   :error.beta/heading "Du hast nicht die Berechtigung diese Seite aufzurufen 🧙‍♂️"
   :error.beta/body "Dieses Feature ist nur für Beta-Tester:innen verfügbar. Wenn du zu den Tester:innen gehörst, melde dich bitte an. Wenn du Beta-Tester:in werden möchtest, dann schreibe uns eine E-Mail an hello@schnaq.com."

   ;; Graph Texts
   :graph.button/text "Mindmap"
   :graph.download/as-png "Mindmap als Bild herunterladen"
   :graph.settings/title "Einstellungen für die Mindmap"
   :graph.settings/description "Finde hier Einstellungen für deine Mindmap! Spiele mit den Slidern herum und schau was passiert."
   :graph.settings.gravity/label "Stelle hier die Gravitation zwischen den Knoten ein."
   :graph.settings/stabilize "Stabilisiere Mindmap"

   ;; Pricing Page
   :pricing.intro/heading "Bald geht es los!"
   :pricing.intro/lead [:span "Schon bald kannst du hier deinen Tarif buchen. Möchtest du Teil unserer Beta sein und schon jetzt exklusiv und kostenfrei den Business-Tarif testen? Dann kontaktiere uns gerne unter " [:a {:href "mailto:hello@schnaq.com"} "hello@schnaq.com!"]]
   :pricing.free-tier/title "Frei"
   :pricing.free-tier/subtitle "für immer"
   :pricing.free-tier/description "Für effiziente Macher:innen und kleine Teams, die nicht viel brauchen. Erstelle Diskussionen und Q&As mit zwei Klicks."
   :pricing.free-tier/beta-notice "Dieser Plan bleibt dauerhaft kostenfrei. Du brauchst mehr Funktionen? Upgrade jederzeit."
   :pricing.free-tier/call-to-action "Kostenfrei loslegen"
   :pricing.free-tier/for-free "Dauerhaft kostenfrei"
   :pricing.pro-tier/title "Pro"
   :pricing.pro-tier/subtitle "Aktiviere dein Potenzial"
   :pricing.pro-tier/description "Erlange die volle Kontrolle über deine schnaqs und verstehe deine Teilnehmer:innen zu 100 %."
   :pricing.pro-tier/call-to-action "Pro schon jetzt testen"
   :pricing.enterprise-tier/title "Enterprise"
   :pricing.enterprise-tier/subtitle "Großes vor?"
   :pricing.enterprise-tier/description "Optimiere die Kommunikation deines gesamten Unternehmens. Spezielle Wünsche und Anforderungen sind hier kein Problem."
   :pricing.enterprise-tier/call-to-action "Anfrage senden"
   :pricing.enterprise-tier/on-request "Auf Anfrage"
   :pricing.features/implemented "Bereits implementiert"
   :pricing.features/to-be-implemented "Bald verfügbar"
   :pricing.features/starter ["In Deutschland gehostet" "Unendlich viele schnaqs" "Unendlich viele Teilnehmer:innen" "Diskussionen erstellen" "Fragen und Antworten Modus" "Automatische Mindmap" "Teilbar per Link, QR-Code und Zahlencode" "Text- und Bild-Export"]
   :pricing.features/business ["Analyse-Dashboard" "K.I. Zusammenfassungen" "Persönlicher Bereich" "Moderationsoptionen"]
   :pricing.features/enterprise ["Einbettung in bestehende Systeme" "SSO Login (OpenID, LDAP, ...)" "Whitelabeling" "On-Premise"]
   :pricing.features/upcoming ["K.I. Stimmungsanalyse" "Integrationen"]
   :pricing.units/per-month "/ Monat"
   :pricing.notes/with-vat "zzgl. MwSt."
   :pricing.trial/call-to-action "30 Tage den Pro Plan testen"
   :pricing.trial/description "Keine Kreditkarte nötig! Jederzeit kündbar."
   :pricing.trial.temporary/deactivation "Demnächst verfügbar"
   :pricing.features/heading "Schnaq-Abonnement Vorteile"
   :pricing.features.user-numbers/heading "Unbegrenzte Teilnehmer:innen"
   :pricing.features.user-numbers/content "Lasse so viele Personen, wie du möchtest, kooperieren."
   :pricing.features.team-numbers/heading "Unbegrenzte Teams"
   :pricing.features.team-numbers/content "Die Anzahl der Teams, die du erstellen kannst, ist unlimitiert. *"
   :pricing.features.app-integration/heading "App-Integration"
   :pricing.features.app-integration/content "Verknüpfe schnaq leicht mit anderer Software, die du nutzt."
   :pricing.features.analysis/heading "Automatische Analysen"
   :pricing.features.analysis/content "Die Beiträge werden automatisch analysiert und für alle Teilnehmer:innen aufbereitet. *"
   :pricing.features.knowledge-db/heading "Wissensdatenbank"
   :pricing.features.knowledge-db/content "Sammle erarbeitetes Wissen und Ideen an einem Ort."
   :pricing.features.mindmap/heading "Interaktive Mindmap"
   :pricing.features.mindmap/content "Alle Beiträge werden automatisch graphisch und interaktiv dargestellt."
   :pricing.features/disclaimer "* Gilt nur für Pro-Abonnement"
   :pricing.competitors/per-month-per-user " € pro Monat pro Nutzer:in"
   :pricing/headline "Abonnements"
   :pricing.newsletter/lead "Werde sofort informiert, wenn das Abonnement live geht:"
   :pricing.newsletter/name "schnaq Newsletter."

   ;; Tooltips
   :tooltip/history-statement "Zurück zum Beitrag von"
   :tooltip/history-statement-current "Aktueller Beitrag"

   ;; History
   :history/title "Verlauf"
   :history.home/text "Start"
   :history.home/tooltip "Zurück zum Diskussionsanfang"
   :history.statement/user "Beitrag von"
   :history.all-schnaqs/tooltip "Zurück zur Übersicht der schnaqs"
   :history.all-schnaqs/label "zur Übersicht"
   :history.back/tooltip "Zurück zum vorherigen Beitrag"
   :history.back/label "vorheriger Beitrag"

   ;; Route Link Texts
   :router/admin-center "Admin-Center"
   :router/all-feedbacks "Alle Feedbacks"
   :router/analytics "Analyse-Dashboard"
   :router/create-schnaq "Schnaq anlegen"
   :router/graph-view "Graph View"
   :router/how-to "Wie benutze ich schnaq?"
   :router/last-added-schnaq "Zuletzt angelegter schnaq"
   :router/visited-schnaqs "Besuchte schnaqs"
   :router/not-found-label "Not Found route redirect"
   :router/pricing "Preise"
   :router/privacy "Datenschutz"
   :router/qanda "Fragen & Antworten"
   :router/start-discussion "Starte Diskussion"
   :router/startpage "Startseite"
   :router/true-404-view "404 Fehlerseite"
   :router/code-of-conduct "Verhaltensregeln"
   :router/summaries "Zusammenfassungen"
   :router/alphazulu "ALPHAZULU"

   :admin.center.start/title "Admin-Center"
   :admin.center.start/heading "Admin-Center"
   :admin.center.start/subheading "Administration von schnaqs als Superuser"
   :admin.center.delete/confirmation "Soll dieser schnaq wirklich gelöscht werden?"
   :admin.center.delete.public/button "Schnaq löschen"
   :admin.center.delete/heading "Löschen"
   :admin.center.delete.private/label "Share-hash"
   :admin.center.delete.private/heading "Private schnaqs"

   :badges.filters/label "Anzeige"
   :badges/sort "Sortierung"
   :badges.sort/newest "Neueste"
   :badges.sort/popular "Beliebt"
   :badges.sort/alphabetical "Alphabetisch"
   :badges.filters/button "Filter"

   :filters.label/filter-for "Filter nach"
   :filters.add/button "Filter hinzufügen"
   :filters.option.labels/text "Label"
   :filters.option.labels/includes "beinhaltet"
   :filters.option.labels/excludes "beinhaltet kein"
   :filters.option.type/text "Beitragstyp"
   :filters.option.type/is "ist"
   :filters.option.type/is-not "ist nicht"
   :filters.option.votes/text "Votes"
   :filters.option.vote/bigger "mehr als"
   :filters.option.vote/equal "gleich"
   :filters.option.vote/less "weniger als"
   :filters.option.answered/all "Alle Beiträge"
   :filters.option.answered/answered "Beantwortete"
   :filters.option.answered/unanswered "Unbeantwortete"
   :filters.buttons/clear "Alle Filter löschen"
   :filters.heading/active "Aktive Filter"

   ;; Labels for programmatically created text in label overview
   :filters.labels.type/labels "Labels"
   :filters.labels.type/type "Beitragstyp"
   :filters.labels.type/votes "Votes"
   :filters.labels.criteria/includes "beinhalten"
   :filters.labels.criteria/excludes "beinhalten kein"
   :filters.labels.criteria/is "ist"
   :filters.labels.criteria/is-not "ist nicht"
   :filters.labels.criteria/> "sind größer als"
   :filters.labels.criteria/= "gleich"
   :filters.labels.criteria/< "sind kleiner als"
   :filters.stype/neutral "neutral"
   :filters.stype/attack "dagegen"
   :filters.stype/support "dafür"

   :filters.discussion.option.state/label "Schnaq Status"
   :filters.discussion.option.state/closed "geschlossen"
   :filters.discussion.option.state/read-only "nur Lesezugriff"
   :filters.discussion.option.numbers/label "Anzahl Beiträge"
   :filters.discussion.option.author/label "Eigene Teilnahme"
   :filters.discussion.option.author/prelude "Ich"
   :filters.discussion.option.author/included "nehme teil"
   :filters.discussion.option.author/excluded "nehme nicht teil"
   ;; Auto-generation of pretty-labels
   :filters.labels.criteria/included "nimmst teil"
   :filters.labels.criteria/excluded "nimmst nicht teil"
   :filters.labels.type/state "Schnaq Status"
   :filters.labels.type/numbers "Anzahl Beiträge"
   :filters.labels.type/author "Du"

   :loading.placeholder/lead "Daten werden geladen..."
   :loading.placeholder/takes-too-long "Das dauert länger als gedacht. Vielleicht ist etwas schiefgelaufen. Versuche die Seite neu zu laden oder den Prozess noch einmal zu wiederholen. Sollte es weiterhin zu Problemen kommen, dann melde dich bei uns!"

   :hubs/heading "Hubs"
   :hub/heading "Persönlicher %s Hub"
   :hub/settings "Verwaltung"
   :hub.settings/change-name "Name des Hubs ändern"
   :hub.settings.name/updated-title "Hub Namensänderung"
   :hub.settings.name/updated-body "Der Name des Hubs wurde erfolgreich verändert!"
   :hub.settings.update-logo-title/success "Das Hub Logo wurde erfolgreich geändert!"
   :hub.settings.update-logo-body/success "Dein neues Logo wurde erfolgreich hochgeladen. Ggf. Seite neuladen um aktualisiertes Bild anzuzeigen."
   :hub.settings/save "Einstellungen speichern"
   :hub.add.schnaq.success/title "Schnaq hinzugefügt!"
   :hub.add.schnaq.success/body "Der schnaq wurde deinem Hub erfolgreich hinzugefügt."
   :hub.add.schnaq.error/title "Fehler beim Hinzufügen!"
   :hub.add.schnaq.error/body "Der schnaq konnte nicht gefunden oder hinzugefügt werden. Bitte versuche es noch einmal."
   :hub.add.schnaq.input/label "schnaq hinzufügen"
   :hub.add.schnaq.input/placeholder "Schnaq-URL z.B. https://schnaq.com/schnaq/… oder Teil-Code"
   :hub.add.schnaq.input/button "schnaq hinzufügen"
   :hub.remove.schnaq.success/title "schnaq entfernt!"
   :hub.remove.schnaq.success/body "Der schnaq wurde erfolgreich aus deinem Hub entfernt."
   :hub.remove.schnaq.error/title "Entfernen fehlgeschlagen!"
   :hub.remove.schnaq.error/body "Etwas ist beim Entfernen schiefgelaufen. Bitte versuche es erneut."
   :hub.remove.schnaq/prompt "Soll der schnaq wirklich aus dem Hub entfernt werden?"
   :hub.remove.schnaq/tooltip "Schnaq aus Hub entfernen"
   :hub.members/heading "Mitglieder"

   :hub.members.add.result.success/title "Erfolg"
   :hub.members.add.result.success/body "Nutzer:in wurde erfolgreich zum Hub hinzugefügt"
   :hub.members.add.result.error/title "Fehler"
   :hub.members.add.result.error/unregistered-user "Unter der gesuchten E-Mail-Adresse existiert kein schnaq Account"
   :hub.members.add.result.error/generic-error "Etwas ist schief gelaufen. Überprüfe die E-Mail und versuche es noch einmal."
   :hub.members.add.form/title "Mitglieder hinzufügen"
   :hub.members.add.form/button "Nutzer:in hinzufügen!"

   :schnaq.search/heading "Suchergebnisse"
   :schnaq.search/results "Ergebnisse"
   :schnaq.search/input "Suche nach…"
   :schnaq.search/new-search-title "Keine Ergebnisse"

   :lead-magnet.privacy/consent "Ich möchte die Checkliste für datenschutzkonformes Arbeiten als .pdf Datei per E-Mail erhalten und mich hiermit zum schnaq Newsletter anmelden, um in Zukunft regelmäßig Informationen von schnaq.com erhalten."
   :lead-magnet.form/button "Schicke mir die Checkliste zu!"
   :lead-magnet/heading "Datenschutzkonform verteilt arbeiten"
   :lead-magnet/subheading "Eine handliche Checkliste, um in allen Bereichen gerüstet zu sein"
   :lead-magnet.cover/alt-text "Das Cover von der Checkliste zum Datenschutzrechte verteilten Arbeiten"
   :lead-magnet.form/label "Wir versenden den Downloadlink zum PDF per E-Mail"
   :lead-magnet.requested/part-1 "Dein erster Schritt Richtung datenschutzkonformes Arbeiten ist getan!"
   :lead-magnet.requested/part-2 "Du solltest den Downloadlink in einigen Minuten erhalten. Schaue auch im Spam-Ordner nach."
   :lead-magnet.cta/button "Direkt zur Checkliste"
   :lead-magnet.explain.what/heading "Was ist die Checkliste für datenschutzkonforme Remote-Arbeit?"
   :lead-magnet.explain.what/text "Wir haben aktuelle Software für Remote-Arbeit auf die Beachtung des Datenschutzes (im speziellen DSGVO) untersucht.
        Die Ergebnisse sind in einer Checkliste zusammengefasst. Enthalten sind dabei alle wichtigen Kategorien, die man für flexible Arbeit benötigt.
        So kannst du, egal ob beim Homeoffice, bei der mobilen Arbeit oder bei deinem hybriden Arbeitsplatz, auf den Datenschutz achten."
   :lead-magnet.explain.how/heading "Wie funktioniert die Checkliste?"
   :lead-magnet.explain.how/text "Die Checkliste ist nach Kategorien aufgebaut. Zu jeder Kategorie, wie z.B. Chat-Plattformen, finden sich mehrere Alternativen.
        Jede Alternative ist mit einer Ampelfarbe markiert. Grün zeigt dabei die Unbedenklichkeit zum Zeitpunkt unseres Tests. Während Rot vor
        möglichen Problemen warnt. Orange hat schließlich oft kleinere Probleme, ist aber grundsätzlich in Ordnung.
        Neben den Ampelfarben finden sich auch noch erklärende Stichpunkte zur weiteren Recherche."

   :summary.link.button/text "Analyse"
   :summary.user.request-succeeded/label "Zusammenfassung angefordert. Bitte warte kurz."
   :summary.user/computation-time "Die Erstellung der Zusammenfassung kann einige Minuten dauern."
   :summary.user.requested/label "Zusammenfassung wird angefordert"
   :summary.user.not-requested/label "Zusammenfassung anfordern"
   :summary.user.abort/confirm "Die Berechnung kann mehrere Minuten dauern. Möchtest du wirklich abbrechen?"
   :summary.user.abort/label "Probleme bei der Berechnung?"
   :summary.user.abort/button "Abbrechen"
   :summary.user/privacy-warning "Zur Verbesserung werden Mitarbeiter:innen des schnaq-Teams Inhalte der Zusammenfassung vertraulich einsehen und überprüfen können."
   :summary.user/label "Zusammenfassung:"
   :summary.user/last-updated "Zuletzt aktualisiert:"
   :summary.user/heading "Zusammenfassungen"
   :summary.user/subheading "Schau dir die Diskussion in wenigen Sätzen an."
   :summary.admin/open-summaries "Offene Zusammenfassungen: %s"
   :summary.admin/closed-summaries "Geschlossene Zusammenfassungen: %s"
   :summary.admin/discussion "Diskussion"
   :summary.admin/requester "Angefragt von"
   :summary.admin/requested-at "Angefragt am"
   :summary.admin/summary "Zusammenfassung"
   :summary.admin/submit "Abschicken"
   :summary.admin/closed-at "Geschlossen am"})
