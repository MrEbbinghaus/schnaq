(ns schnaq.interface.views.meeting.admin-buttons
  (:require [ajax.core :as ajax]
            [goog.string :as gstring]
            [oops.core :refer [oset! oget]]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as reitfe]
            [schnaq.interface.config :refer [config]]
            [schnaq.interface.text.display-data :refer [labels fa]]
            [schnaq.interface.utils.clipboard :as clipboard]
            [schnaq.interface.utils.tooltip :as tooltip]
            [schnaq.interface.utils.file-download :as file-download]))

(defn admin-center
  "Button to access admin menu."
  [share-hash edit-hash]
  [tooltip/tooltip-button "bottom"
   (labels :meeting/admin-center-tooltip)
   [:i {:class (str "m-auto fas " (fa :cog))}]
   #(rf/dispatch [:navigation/navigate
                  :routes.schnaq/admin-center
                  {:share-hash share-hash :edit-hash edit-hash}])])

(defn share-link
  "Button to access admin menu."
  [share-hash]
  [tooltip/tooltip-button "bottom"
   (labels :meeting/share-link-tooltip)
   [:i {:class (str "m-auto fas " (fa :share))}]
   #(let [path (reitfe/href :routes.schnaq/start {:share-hash share-hash})
          location (oget js/window :location)]
      (clipboard/copy-to-clipboard!
        (gstring/format "%s//%s%s" (oget location :protocol) (oget location :host) path))
      (rf/dispatch [:notification/add
                    #:notification{:id (str "content-added" (random-uuid))
                                   :title (labels :user.action/link-copied)
                                   :body [:<>
                                          [:p (labels :user.action/link-copied-body)]]
                                   :context :info
                                   :stay-visible? false}]))])

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
   [:i {:class (str "fas " (fa :graph))}]
   (fn []
     (let [canvas (.querySelector js/document (gstring/format "%s div canvas" surrounding-div))
           anchor (.createElement js/document "a")]
       (oset! anchor [:href] (.toDataURL canvas "image/png"))
       (oset! anchor [:download] "graph.png")
       (.click anchor)))])

(defn txt-export
  "Request a txt-export of the discussion."
  [share-hash title]
  (let [request-fn #(ajax/ajax-request {:method :get
                                        :uri (str (:rest-backend config) "/export/txt")
                                        :format (ajax/transit-request-format)
                                        :params {:share-hash share-hash}
                                        :response-format (ajax/transit-response-format)
                                        :handler (partial create-txt-download-handler title)
                                        :error-handler show-error})]
    (when share-hash
      [tooltip/tooltip-button "bottom" (labels :meeting/admin-center-export)
       [:i {:class (str "fas " (fa :file-download))}]
       #(request-fn)])))
