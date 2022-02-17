(ns schnaq.mail.template
  (:require [clojure.string :as cstring]
            [schnaq.config :as config]
            [schnaq.translations :refer [email-templates]]))

(defn mail
  "Basic html mail template with a schnaq logo and passed heading.
  Title, subtitle and content are strings displayed in a light blue section.
  Additional html content is not displayed in the text/plain version"
  [header title subtitle content additional-html-content additional-plain-content]
  (let [replace-fn #(cstring/replace %1 (first %2) (second %2))
        format-map {"$$$Header$$$" header
                    "$$$CONTENT-TITLE$$$" title
                    "$$$CONTENT-SUB-TITLE$$$" subtitle
                    "$$$CONTENT-TEXT$$$" (cstring/replace content "\n" "<br>")
                    "$$$ADDITIONAL-CONTENT$$$" additional-html-content}]
    [:alternative
     {:type "text/plain; charset=utf-8" :content
      (str header
           "\n" title
           (when (seq subtitle)
             (str "\n" subtitle))
           (when (seq content)
             (str "\n" content))
           (when (seq additional-plain-content)
             (str "\n\n" additional-plain-content))
           "\n\n\nViele Grüße\n\nDein schnaq Team")}
     {:type "text/html; charset=utf-8" :content
      (reduce replace-fn (slurp config/mail-template) format-map)}]))

(defn mail-content-left-button-right
  "Additional html content to display content on the left side and a button on the right side"
  [content-title content-subtitle button-text button-link]
  (let [replace-fn #(cstring/replace %1 (first %2) (second %2))
        format-map {"$$$LEFT-CONTENT$$$" content-title
                    "$$$LEFT-CONTENT-BOTTOM$$$" content-subtitle
                    "$$$BUTTON-TEXT$$$" button-text
                    "$$$BUTTON-LINK$$$" button-link}]
    (reduce replace-fn (slurp config/mail-content-button-right-template) format-map)))

(defn welcome
  "Welcome Mail Template."
  []
  [:alternative
   {:type "text/plain; charset=utf-8" :content
    (email-templates :welcome/body)}
   {:type "text/html; charset=utf-8" :content
    (slurp "https://s3.schnaq.com/welcome-mail/welcome_template.html")}])
