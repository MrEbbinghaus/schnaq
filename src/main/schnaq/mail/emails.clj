(ns schnaq.mail.emails
  (:require [clojure.spec.alpha :as s]
            [com.fulcrologic.guardrails.core :refer [>defn >defn- ?]]
            [hiccup.util :as hiccup-util]
            [postal.core :refer [send-message]]
            [schnaq.config :as config]
            [schnaq.database.specs :as specs]
            [schnaq.links :as schnaq-links]
            [schnaq.mail.template :as template]
            [schnaq.translations :refer [email-templates]]
            [taoensso.timbre :as log]))

(def ^:private conn {:host (:sender-host config/email)
                     :ssl true
                     :user (:sender-address config/email)
                     :pass (:sender-password config/email)})

(def ^:private mail-configured?
  (every? not-empty (vals config/email)))

(>defn- valid-mail
  "Check valid mail"
  [mail]
  [string? :ret (? string?)]
  (if (re-matches #"[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,10}" mail)
    mail
    (log/info (format "Mail validation failed for address %s" mail))))

(def ^:private failed-sendings (atom '()))

(>defn- send-mail-with-custom-body
  "Sends a single mail to a recipient with a passed body."
  [title recipient body]
  [string? string? coll? :ret (? coll?)]
  (if mail-configured?
    (if (valid-mail recipient)
      (try
        (send-message conn {:from (:sender-address config/email)
                            :to recipient
                            :subject title
                            :body body})
        (log/info "Sent mail to" recipient)
        (Thread/sleep 100)
        (catch Exception exception
          (log/error "Failed to send mail to" recipient)
          (log/error exception)
          (swap! failed-sendings conj recipient)))
      (swap! failed-sendings conj recipient))
    (log/info (format "Should send an email to %s now, but email is not configured." recipient))))

(>defn send-mail
  "Sends a single mail to the recipient. Title and content are used as passed."
  ([title content recipient]
   [string? string? string? :ret (? coll?)]
   (send-mail-with-custom-body title recipient (template/mail "" title "" content "" "")))
  ([mail-title header title content recipient]
   [string? string? string? string? string? :ret (? coll?)]
   (send-mail-with-custom-body mail-title recipient (template/mail header title "" content "" "")))
  ([mail-title header title sub-title content additional-html-content additional-plain-content recipient]
   [string? string? string? string? string? string? string? string? :ret (? coll?)]
   (send-mail-with-custom-body mail-title recipient
                               (template/mail header
                                              title
                                              sub-title
                                              content
                                              additional-html-content
                                              additional-plain-content))))

(>defn send-mails
  "Sends an email with a `title` and `content` to all valid recipients.
  Returns a list of invalid addresses and failed sends."
  [title content recipients]
  [string? string? (s/coll-of string?) :ret any?]
  (reset! failed-sendings '())
  (run! (partial send-mail title content) recipients)
  {:failed-sendings @failed-sendings})

(>defn send-welcome-mail
  "Sends a welcome e-mail to a recipient. The mail template is stored in s3."
  [recipient]
  [string? :ret any?]
  (send-mail-with-custom-body
   (email-templates :welcome/title)
   recipient
   (template/welcome)))

(>defn send-flagged-post
  "Send a mail containing the content and link to the flagged statement "
  [discussion statement recipients]
  [::specs/discussion ::specs/statement (s/coll-of string?) :ret any?]
  (send-mails
   "Beitrag mit bedenklichem Inhalt!"
   (format "Ein:e Nutzer:in hat folgenden Beitrag als Bedenklich gemeldet:\n\n'%s'\n\n %s"
           (hiccup-util/escape-html (:statement/content statement))
           (schnaq-links/get-link-to-statement
            (:discussion/share-hash discussion)
            (:db/id statement)))
   recipients))
