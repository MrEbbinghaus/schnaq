(ns meetly.meeting.interface.views.agenda.edit
  (:require [meetly.meeting.interface.views.agenda.agenda :as agenda]
            [meetly.meeting.interface.views.base :as base]
            [re-frame.core :as rf]
            [oops.core :refer [oget]]
            [meetly.meeting.interface.text.display-data :refer [labels fa]]))


(defn- agenda-view [agenda]
  (let [db-id (:db/id agenda)]
    [:div
     [:div.agenda-line]
     [:div.add-agenda-div.agenda-point
      [:div.row.agenda-row-title
       [:div.col-10
        ;; title
        [:input.form-control.agenda-form-title.form-title
         {:type "text"
          :name "title"
          :auto-complete "off"
          :required true
          :placeholder (labels :agenda-point)
          :default-value (:agenda/title agenda)
          :id (str "title-" db-id)
          :on-key-up
          #(rf/dispatch [:agenda/update-edit-form :agenda/title db-id (oget % [:target :value])])}]]
       [:div.col-2
        [:div.pt-4.link-pointer
         {:on-click #(rf/dispatch [:agenda/delete db-id])}
         [:i {:class (str "m-auto fas fa-2x " (fa :delete-icon))}]]]]
      ;; description
      [:textarea.form-control.agenda-form-round
       {:name "description"
        :placeholder (labels :agenda-desc-for)
        :default-value (:agenda/description agenda)
        :id (str "description-" db-id)
        :on-key-up
        #(rf/dispatch [:agenda/update-edit-form :agenda/description db-id (oget % [:target :value])])}]]]))

(defn add-editable-agenda-button []
  [:input.btn.agenda-add-button {:type "button"
                                 :value "+"
                                 :on-click #(rf/dispatch [:agenda/add-edit-form])}])

(defn editable-meeting-info [selected-meeting]
  [:div.agenda-meeting-container
   ;; title form
   [:input.form-control.meeting-edit-title
    {:default-value (:meeting/title selected-meeting)
     :type "text"
     :name "meeting-title"
     :auto-complete "off"
     :required true
     :placeholder (labels :meeting-form-title)
     :id (str "meeting-title-" (:db/id selected-meeting))
     :on-key-up
     #(rf/dispatch
        [:meeting/update-meeting-attribute :meeting/title (oget % [:target :value])])}]
   ;; description form
   [:textarea.form-control.meeting-edit-description
    {:default-value (:meeting/description selected-meeting)
     :rows "3"
     :type "text"
     :name "meeting-description"
     :auto-complete "off"
     :placeholder (labels :meeting-form-title)
     :id (str "meeting-description-" (:db/id selected-meeting))
     :on-key-up
     #(rf/dispatch
        [:meeting/update-meeting-attribute :meeting/description (oget % [:target :value])])}]])

(defn edit-view []
  (let [edit-information @(rf/subscribe [:agenda/current-edit-info])
        selected-meeting (:meeting edit-information)
        meeting-agendas (:agendas edit-information)]
    [:div#create-agenda
     [base/nav-header]
     [agenda/header]
     [:div.container.px-5.py-3.text-center
      [:form {:id "agendas-add-form"
              :on-submit (fn [e]
                           (.preventDefault e)
                           (rf/dispatch [:todo]))}
       ;; meeting title and description
       [editable-meeting-info selected-meeting]
       [:div.container
        [:div.agenda-container
         (for [agenda meeting-agendas]
           [:div {:key (:db/id agenda)}
            [agenda-view agenda]])
         [:div.agenda-line]
         [add-editable-agenda-button]
         [:br]
         [:br]
         [:br]
         [agenda/submit-agenda-button]]]]]])

  )

;; load agendas events

(rf/reg-event-fx
  :agenda/load-for-edit
  (fn [_ [_ hash]]
    (agenda/load-agenda-fn hash :agenda/load-for-edit-success)))

(rf/reg-event-db
  :agenda/load-for-edit-success
  (fn [db [_ agendas]]
    (-> db
        (assoc-in [:edit-meeting :agendas] agendas)
        (assoc-in [:edit-meeting :meeting] (get-in db [:meeting :selected])))))

(rf/reg-sub
  :agenda/current-edit-info
  (fn [db _]
    (:edit-meeting db)))

;; delete agenda events

(rf/reg-event-db
  :agenda/delete
  (fn [db [_ agenda-id]]
    (let [delete-fn (fn [agendas] (remove #(= agenda-id (:db/id %)) agendas))]
      (update-in db [:edit-meeting :agendas] delete-fn))))

;; add agenda form event

(rf/reg-event-db
  :agenda/add-edit-form
  (fn [db _]
    (update-in db [:edit-meeting :agendas]
               #(concat % [{:db/id (str (random-uuid))
                            :agenda/title ""
                            :agenda/description ""
                            :agenda/meeting (get-in db [:edit-meeting :meeting :db/id])}]))))

;; update agenda

(rf/reg-event-db
  :agenda/update-edit-form
  (fn [db [_ attribute id new-val]]
    (let [has-id? #(= id (:db/id %))
          update-fn (fn [coll] (map #(if (has-id? %)
                                       ;; update attribute of agenda
                                       (assoc % attribute new-val)
                                       ;; do not update agenda
                                       %) coll))]
      (update-in db [:edit-meeting :agendas] update-fn))))

;; update title

(rf/reg-event-db
  :meeting/update-meeting-attribute
  (fn [db [_ attribute new-val]]
    (update-in db [:edit-meeting :meeting] assoc attribute new-val)))