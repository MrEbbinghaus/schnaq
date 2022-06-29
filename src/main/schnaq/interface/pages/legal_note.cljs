(ns schnaq.interface.pages.legal-note
  (:require [schnaq.interface.pages.privacy-extended :as privacy-extended]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.views.pages :as pages]))

(defn- entry [title body]
  [:article.pb-3
   [:h4 (labels title)]
   [:p (labels body)]])

(defn- liability-for-contents []
  [entry :legal-note.contents/title :legal-note.contents/body])

(defn- liability-for-links []
  [entry :legal-note.links/title :legal-note.links/body])

(defn- copyright []
  [entry :legal-note.copyright/title :legal-note.copyright/body])

(defn- privacy []
  [:article.pb-3
   [:h2 (labels :legal-note.privacy/title)]
   [:p [:a.btn.btn-link.ps-0 {:href "https://schnaq.com/privacy"}
        (labels :legal-note.privacy/body)]]])

;; ----------------------------------------------------------------------------

(defn page [_request]
  [pages/with-nav-and-header
   {:page/heading (labels :legal-note.page/heading)
    :page/vertical-header? true}
   [:div.container
    [privacy-extended/responsible]
    [:h2 (labels :legal-note.page/disclaimer)]
    [liability-for-contents]
    [liability-for-links]
    [copyright]
    [privacy]]])
