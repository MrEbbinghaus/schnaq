(ns schnaq.interface.views.schnaq.summary
  "All views and events important to extractive summaries can be found here."
  (:require [goog.string :as gstring]
            [oops.core :refer [oget oget+]]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [schnaq.config.shared :as shared-config]
            [schnaq.interface.components.common :as common-components]
            [schnaq.interface.components.motion :as motion]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.js-wrapper :as jq]
            [schnaq.interface.utils.time :as util-time]
            [schnaq.interface.views.loading :as loading]
            [schnaq.interface.views.pages :as pages]))

(def ^:private calculation-states
  #{:request-succeeded :requested})

(defn- abort-summary
  "Something went wrong. Add link to abort the summary request
  _in the frontend_. Does actually not abort processes in summy or in the
  backend."
  [share-hash]
  (let [request-status @(rf/subscribe [:schnaq.summary/status share-hash])
        time-to-show-info 5000]
    (when (some #{request-status} calculation-states)
      [motion/fade-in-and-out
       [:small
        (labels :summary.user.abort/label)
        " "
        [:a.btn.btn-sm.btn-link
         {:on-click #(when (js/confirm (labels :summary.user.abort/confirm))
                       (rf/dispatch [:schnaq.summary/abort share-hash]))}
         (labels :summary.user.abort/button)]]
       time-to-show-info])))

(defn- summary-request-button
  "Requests a summary or a refresh."
  [share-hash]
  (let [request-status @(rf/subscribe [:schnaq.summary/status share-hash])
        button-text (case request-status
                      :request-succeeded (labels :summary.user.request-succeeded/label)
                      :requested (str (labels :summary.user.requested/label) "…")
                      (labels :summary.user.not-requested/label))]
    [:section.d-block.text-center
     [:button.btn.btn-dark.mb-2
      (if (some #{request-status} calculation-states)
        {:disabled true}
        {:on-click #(rf/dispatch [:schnaq.summary/request share-hash])})
      button-text]
     [abort-summary share-hash]
     [common-components/hint-text
      (str
       (labels :summary.user/computation-time)
       (when-not shared-config/embedded?
         (str " " (labels :summary.user/privacy-warning))))]]))

(defn summary-body
  "Contains the summary and possibly some meta information."
  [schnaq]
  (let [{:summary/keys [created-at text]} @(rf/subscribe [:schnaq/summary (:discussion/share-hash schnaq)])
        locale @(rf/subscribe [:current-locale])
        [updated-at text] (if text
                            [(util-time/timestamp-with-tooltip created-at locale) text]
                            ["-" "-"])]
    [:<>
     [:small.text-muted (labels :summary.user/last-updated) " " updated-at]
     [:p.p-3 (or text "-")]
     [:hr.py-2]
     [summary-request-button (:discussion/share-hash schnaq)]]))

(defn- list-open-summaries
  "Shows a list of all still open summaries."
  []
  [:div.py-4
   (let [summaries @(rf/subscribe [:summaries/open])
         locale @(rf/subscribe [:current-locale])]
     (if summaries
       [:<>
        [:h4 (gstring/format (labels :summary.admin/open-summaries) (count summaries))]
        [:table.table.table-striped
         [:thead
          [:tr
           [:th {:width "15%"} (labels :summary.admin/discussion)]
           [:th {:width "15%"} (labels :summary.admin/requester)]
           [:th {:width "15%"} (labels :summary.admin/requested-at)]
           [:th {:width "55%"} (labels :summary.admin/summary)]]]
         [:tbody
          (for [summary summaries]
            (let [requester (-> summary :summary/requester :user.registered/display-name)
                  share-hash (-> summary :summary/discussion :discussion/share-hash)
                  summary-id (:db/id summary)]
              [:tr {:key (str "row-" summary-id)}
               [:td [:a {:href (rfe/href :routes.schnaq/start
                                         {:share-hash share-hash})}
                     (-> summary :summary/discussion :discussion/title)]]
               [:td requester]
               [:td (util-time/timestamp-with-tooltip (:summary/requested-at summary) locale)]
               [:td [:form
                     {:on-submit (fn [e]
                                   (jq/prevent-default e)
                                   (rf/dispatch
                                    [:summary.admin/send share-hash (str summary-id) (oget e [:currentTarget :elements])]))}
                     [:textarea.form-control {:name (str summary-id) :rows 3 :defaultValue (:summary/text summary)}]
                     [:button.btn.btn-outline-primary.ml-1 {:type "submit"} (labels :summary.admin/submit)]]]]))]]]
       [loading/loading-placeholder]))])

(defn- list-closed-summaries
  "Shows a list of all closed summaries."
  []
  [:div.py-4
   (let [summaries @(rf/subscribe [:summaries/closed])
         locale @(rf/subscribe [:current-locale])]
     (if summaries
       [:<>
        [:h4 (gstring/format (labels :summary.admin/closed-summaries) (count summaries))]
        [:table.table.table-striped
         [:thead
          [:tr
           [:th {:width "20%"} (labels :summary.admin/discussion)]
           [:th {:width "15%"} (labels :summary.admin/requested-at)]
           [:th {:width "50%"} (labels :summary.admin/summary)]
           [:th {:width "15%"} (labels :summary.admin/closed-at)]]]
         [:tbody
          (for [summary summaries]
            [:tr {:key (str "row-" (:db/id summary))}
             [:td [:a {:href (rfe/href :routes.schnaq/start
                                       {:share-hash (-> summary :summary/discussion :discussion/share-hash)})}
                   (-> summary :summary/discussion :discussion/title)]]
             [:td (util-time/timestamp-with-tooltip (:summary/requested-at summary) locale)]
             [:td (:summary/text summary)]
             [:td (util-time/timestamp-with-tooltip (:summary/created-at summary) locale)]])]]]
       [loading/loading-placeholder]))])

(defn- admin-summaries
  "Shows all summaries to the admins."
  []
  [pages/with-nav-and-header
   {:page/heading "Zusammenfassungen"
    :page/subheading "Beim Drücken von Senden, werden diese sofort erstellt."
    :condition/needs-administrator? true}
   [:section.container
    [list-open-summaries]
    [:hr]
    [list-closed-summaries]]])

;; -----------------------------------------------------------------------------

(defn admin-summaries-view []
  [admin-summaries])

;; -----------------------------------------------------------------------------

(rf/reg-event-fx
 :summary.admin/send
 (fn [{:keys [db]} [_ share-hash html-selector form]]
   {:fx [(http/xhrio-request db :put "/admin/summary/send"
                             [:summary.admin.send/success form]
                             {:new-summary-text (oget+ form [html-selector :value])
                              :share-hash share-hash})]}))

(rf/reg-event-fx
 :summary.admin.send/success
 (fn [{:keys [db]} [_ form response]]
   (let [new-summary (:new-summary response)
         updated-summaries (map #(if (= (:db/id new-summary) (:db/id %)) new-summary %)
                                (get-in db [:summaries :all]))]
     {:db (assoc-in db [:summaries :all] updated-summaries)
      :fx [[:form/clear form]]})))

(rf/reg-event-fx
 :schnaq.summary/request
 (fn [{:keys [db]} [_ share-hash]]
   {:db (assoc-in db [:schnaq :summary :status share-hash] :requested)
    :fx [(http/xhrio-request db :post "/schnaq/summary/request" [:schnaq.summary.request/success share-hash]
                             {:share-hash share-hash})]}))

(rf/reg-event-db
 :schnaq.summary.request/success
 (fn [db [_ share-hash result]]
   (-> db
       (assoc-in [:schnaq :summary :status share-hash] :request-succeeded)
       (assoc-in [:schnaq :summary :result share-hash] (:summary result)))))

(rf/reg-event-db
 :schnaq.summary/abort
 (fn [db [_ share-hash]]
   (assoc-in db [:schnaq :summary :status share-hash] :abort)))

(rf/reg-sub
 :schnaq.summary/status
 (fn [db [_ share-hash]]
   (get-in db [:schnaq :summary :status share-hash])))

(rf/reg-sub
 :schnaq/summary
 (fn [db [_ share-hash]]
   (get-in db [:schnaq :summary :result share-hash])))

(rf/reg-event-fx
 :schnaq.summary/load
 (fn [{:keys [db]} _]
   (let [share-hash (get-in db [:schnaq :selected :discussion/share-hash])]
     {:fx [(http/xhrio-request db :get "/schnaq/summary" [:schnaq.summary.load/success share-hash]
                               {:share-hash share-hash})]})))

(rf/reg-event-db
 :schnaq.summary.load/success
 (fn [db [_ share-hash result]]
   (let [{:summary/keys [created-at requested-at text] :as summary} (:summary result)]
     (cond-> (assoc-in db [:schnaq :summary :result share-hash] summary)
       (or (and requested-at (not text))                    ;; Requested, but not finished
           (and created-at requested-at (> requested-at created-at))) ; Requested update
       (assoc-in [:schnaq :summary :status share-hash] :request-succeeded)))))

(rf/reg-event-fx
 :summaries/load-all
 (fn [{:keys [db]} _]
   {:fx [(http/xhrio-request db :get "/admin/summaries" [:summaries.load-all/success])]}))

(rf/reg-event-db
 :summaries.load-all/success
 (fn [db [_ result]]
   (assoc-in db [:summaries :all] (:summaries result))))

(rf/reg-sub
 :summaries/all
 (fn [db _]
   (get-in db [:summaries :all] [])))

(rf/reg-sub
 :summaries/open
 :<- [:summaries/all]
 (fn [summaries _ _]
   (sort-by
    :summary/requested-at
    (remove #(and (:summary/text %)                         ;; No summary provided yet
                  (< (:summary/requested-at %) (:summary/created-at %))) ;; Update requested after last summary
            summaries))))

(rf/reg-sub
 :summaries/closed
 :<- [:summaries/all]
 (fn [summaries _ _]
   (sort-by
    :summary/created-at
    (filter #(and (:summary/text %)
                  (< (:summary/requested-at %) (:summary/created-at %)))
            summaries))))
