(ns schnaq.interface.views.discussion.edit
  (:require [oops.core :refer [oget oget+]]
            [re-frame.core :as rf]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.js-wrapper :as jq]
            [schnaq.interface.utils.toolbelt :as tools]
            [schnaq.interface.views.discussion.input :as input]))

(defn- edit-card
  "The same as a statement-card, but currently being an editable input."
  [label html-id dispatch-fn edit-id pro-con-enabled? content statement-type change-statement-type]
  [:form.card.statement-card.py-2.px-3
   {:on-submit (fn [e]
                 (jq/prevent-default e)
                 (dispatch-fn e))
    :on-key-down (fn [e]
                   (when (jq/ctrl-press e 13) (dispatch-fn e)))}
   [:div.form-group
    [:label {:for html-id} (labels label)]
    [:textarea.form-control {:name html-id
                             :rows 3
                             :placeholder content
                             :defaultValue content}]]
   [:div.d-flex.justify-content-between.flex-wrap
    [:div.d-flex.mb-3
     (when pro-con-enabled?
       [input/statement-type-choose-button statement-type change-statement-type])]
    [:div.d-flex.mb-3
     [:button.btn.btn-outline-secondary
      {:on-click (fn [e]
                   (jq/prevent-default e)
                   (rf/dispatch [:statement.edit/deactivate-edit edit-id]))}
      (labels :statement.edit.button/cancel)]
     [:button.btn.btn-outline-primary.ml-1 {:type "submit"} (labels :statement.edit.button/submit)]]]])

(defn edit-card-statement
  "Editable statement input."
  [statement]
  (let [statement-id (:db/id statement)
        pro-con-enabled? (and (:statement/parent statement)
                              (not @(rf/subscribe [:schnaq.selected/pro-con?])))
        statement-html-id (str "statement-edit-" statement-id)
        dispatch-fn #(rf/dispatch
                       [:statement.edit/send (:db/id statement) statement-html-id (oget % [:currentTarget :elements])])]
    [edit-card
     :statement.edit/label
     statement-html-id
     dispatch-fn
     (:db/id statement)
     pro-con-enabled?
     (:statement/content statement)
     [:statement.edit/statement-type statement-id]
     [:statement.edit/change-statement-type statement-id]]))

(defn edit-card-discussion
  "Editable discussion title input."
  [discussion]
  (let [discussion-id (:db/id discussion)
        pro-con-enabled? false
        discussion-html-id (str "discussion-edit-" discussion-id)
        dispatch-fn #(rf/dispatch
                       [:discussion.edit-title/send discussion-html-id (oget % [:currentTarget :elements])])]
    [edit-card
     :schnaq.edit/label
     discussion-html-id
     dispatch-fn
     discussion-id
     pro-con-enabled?
     (:statement/content discussion)
     nil
     nil]))

(rf/reg-event-fx
  :statement.edit/send
  (fn [{:keys [db]} [_ statement-id html-selector form]]
    (let [share-hash (get-in db [:schnaq :selected :discussion/share-hash])
          new-type (get-in db [:statements :edit-type statement-id] :statement.type/neutral)]
      {:fx [(http/xhrio-request db :put "/discussion/statement/edit"
                                [:statement.edit.send/success form]
                                {:statement-id statement-id
                                 :statement-type new-type
                                 :share-hash share-hash
                                 :display-name (tools/current-display-name db)
                                 :new-content (oget+ form [html-selector :value])}
                                [:statement.edit.send/failure])]})))

(rf/reg-event-fx
  :statement.edit.send/success
  (fn [{:keys [db]} [_ form response]]
    (let [updated-statement (:updated-statement response)]
      {:db (-> db
               (update-in [:discussion :premises :current] #(tools/update-statement-in-list % updated-statement))
               (update-in [:history :full-context] #(vec (tools/update-statement-in-list % updated-statement)))
               (update-in [:discussion :conclusion :selected] #(if (= (:db/id %) (:db/id updated-statement))
                                                                 updated-statement
                                                                 %)))
       :fx [[:form/clear form]
            [:dispatch [:statement.edit/deactivate-edit (:db/id updated-statement)]]]})))

(rf/reg-event-fx
  :statement.edit.send/failure
  (fn [_ _]
    {:fx [[:dispatch [:notification/add
                      #:notification{:title (labels :statement.edit.send.failure/title)
                                     :body (labels :statement.edit.send.failure/body)
                                     :context :danger}]]]}))

(rf/reg-event-fx
  :discussion.edit-title/send
  (fn [{:keys [db]} [_ html-selector form]]
    (let [share-hash (get-in db [:schnaq :selected :discussion/share-hash])
          admin-access-map @(rf/subscribe [:schnaqs/load-admin-access])
          edit-hash (get admin-access-map share-hash)]
      {:fx [(http/xhrio-request db :put "/schnaq/edit/title"
                                [:discussion.edit.send/success form]
                                {:share-hash share-hash
                                 :edit-hash edit-hash
                                 :new-title (oget+ form [html-selector :value])}
                                [:statement.edit.send/failure])]})))

(rf/reg-event-fx
  :discussion.edit.send/success
  (fn [{:keys [db]} [_ form response]]
    (let [updated-discussion (:schnaq response)
          new-title (:discussion/title updated-discussion)]
      {:db (assoc-in db [:schnaq :selected :discussion/title] new-title)
       :fx [[:form/clear form]
            [:dispatch [:statement.edit/deactivate-edit (:db/id updated-discussion)]]]})))

(rf/reg-event-db
  :statement.edit/activate-edit
  (fn [db [_ statement-id]]
    (update-in db [:statements :currently-edited] #(if (nil? %)
                                                     #{statement-id}
                                                     (conj % statement-id)))))

(rf/reg-event-db
  :statement.edit/deactivate-edit
  (fn [db [_ statement-id]]
    (update-in db [:statements :edit-type] dissoc statement-id)
    (update-in db [:statements :currently-edited] disj statement-id)))

(rf/reg-event-db
  :statement.edit/reset-edits
  (fn [db _]
    (assoc-in db [:statements :currently-edited] #{})))

(rf/reg-sub
  :statement.edit/ongoing?
  (fn [db [_ statement-id]]
    (contains? (get-in db [:statements :currently-edited] #{}) statement-id)))

(rf/reg-event-db
  :statement.edit/change-statement-type
  (fn [db [_ id statement-type]]
    (assoc-in db [:statements :edit-type id] statement-type)))

(rf/reg-sub
  :statement.edit/statement-type
  (fn [db [_ id]]
    (get-in db [:statements :edit-type id] :statement.type/neutral)))
