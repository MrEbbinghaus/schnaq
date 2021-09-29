(ns schnaq.interface.views.schnaq.admin
  (:require [ajax.core :as ajax]
            [goog.string :as gstring]
            [oops.core :refer [oset!]]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [schnaq.config.shared :as shared-config]
            [schnaq.interface.components.icons :refer [fa]]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.clipboard :as clipboard]
            [schnaq.interface.utils.file-download :as file-download]
            [schnaq.interface.utils.tooltip :as tooltip]
            [schnaq.interface.views.common :as common]
            [schnaq.interface.views.modal :as modal]
            [schnaq.interface.views.notifications :refer [notify!]]
            [schnaq.links :as links]))

(defn admin-center
  "Button to access admin menu."
  []
  (let [{:discussion/keys [share-hash edit-hash]} @(rf/subscribe [:schnaq/selected])]
    [tooltip/text
     (labels :schnaq.admin/tooltip)
     [:a.btn.btn-outline-muted.btn-lg
      {:href (rfe/href :routes.schnaq/admin-center {:share-hash share-hash :edit-hash edit-hash})
       :role :button}
      [:i {:class (str "m-auto fas " (fa :cog))}]]]))

(defn- share-modal
  "Modal showing sharing options."
  []
  (let [share-hash @(rf/subscribe [:schnaq/share-hash])]
    [modal/modal-template
     (labels :sharing.modal/title)
     [:section
      [:p.lead (labels :sharing.modal/lead)]
      [:div.input-group
       [:input.form-control
        {:value (links/get-share-link share-hash)
         :readOnly true}]
       [:div.input-group-append
        [:button.btn.btn-outline-secondary
         {:on-click (fn []
                      (clipboard/copy-to-clipboard! (links/get-share-link share-hash))
                      (notify! (labels :schnaq/link-copied-heading)
                               (labels :schnaq/link-copied-success)
                               :info
                               false))}
         (labels :share-link/copy)]]]
      [:div.w-75.mx-auto.py-5
       [common/schnaqqi-speech-bubble-blue
        "100px"
        (labels :sharing.modal/schnaqqi-help)]]]]))

(defn open-share-modal
  "Open the share-schnaq dialog."
  []
  (rf/dispatch [:modal {:show? true
                        :large? true
                        :child [share-modal]}]))

(defn share-link
  "Button to copy access link and notify the user."
  []
  [tooltip/tooltip-button "bottom" (labels :sharing/tooltip)
   [:i {:class (str "m-auto fas " (fa :share))}]
   open-share-modal])

(defn- create-txt-download-handler
  "Receives the export apis answer and creates a download."
  [title [ok response]]
  (when ok
    (file-download/export-data
      (gstring/format "# %s\n%s" title (:string-representation response)))))

(defn- show-error
  [& _not-needed]
  (rf/dispatch [:ajax.error/as-notification (labels :error/export-failed)]))

(defn graph-download-as-png
  "Download the current graph as a png file."
  [surrounding-div]
  [tooltip/tooltip-button "bottom" (labels :graph.download/as-png)
   [:i {:class (str "m-auto fas " (fa :graph))}]
   (fn []
     (let [canvas (.querySelector js/document (gstring/format "%s div canvas" surrounding-div))
           anchor (.createElement js/document "a")]
       (oset! anchor [:href] (.toDataURL canvas "image/png"))
       (oset! anchor [:download] "graph.png")
       (.click anchor)))])

(defn txt-export
  "Request a txt-export of the discussion."
  [share-hash title]
  (let [request-fn #(ajax/ajax-request
                      {:method :get
                       :uri (str shared-config/api-url "/export/argdown")
                       :format (ajax/transit-request-format)
                       :params {:share-hash share-hash}
                       :response-format (ajax/transit-response-format)
                       :handler (partial create-txt-download-handler title)
                       :error-handler show-error})]
    (when share-hash
      [tooltip/tooltip-button "bottom" (labels :schnaq.export/as-text)
       [:i {:class (str "m-auto fas " (fa :file-download))}]
       #(request-fn)])))
