(ns meetly.meeting.interface.views.agenda
  (:require [oops.core :refer [oget]]
            [re-frame.core :as rf]
            [ajax.core :as ajax]
            [meetly.meeting.interface.text.display-data :as data]
            [meetly.meeting.interface.views.base :as base]
            [meetly.meeting.interface.config :refer [config]]))

(defn- new-agenda-local
  "This function formats the agenda-form input and saves it locally to the db until
  the discussion is created fully. `field` can be `title` or `description`."
  [field content suffix]
  (case field
    :title (rf/dispatch [:agenda/update-title content suffix])
    :description (rf/dispatch [:agenda/update-description content suffix])))

(defn- new-agenda-form
  "A form for creating a new agenda. The new agenda is automatically saved in the
  app-state according to the suffix."
  [numbered-suffix]
  [:div
   [:div.agenda-line]
   [:div.add-agenda-div.agenda-point
    [:form {:id (str "agenda-" numbered-suffix)}
     ;; title
     [:input.form-control.agenda-form-title.form-title
      {:type "text"
       :name "title"
       :autocomplete "off"
       :placeholder (str (data/labels :agenda-point) numbered-suffix)
       :id (str "title-" numbered-suffix)
       :on-key-up
       #(new-agenda-local :title (oget % [:target :value]) numbered-suffix)}]
     ;; description
     [:textarea.form-control.agenda-form-round
      {:name "description"
       :placeholder (str (data/labels :agenda-desc-for) numbered-suffix)
       :id (str "description-" numbered-suffix)
       :on-key-up #(new-agenda-local
                     :description (oget % [:target :value]) numbered-suffix)}]]]])

(defn- add-agenda-button []
  [:input.btn.agenda-add-button {:type "button"
                                 :value "+"
                                 :on-click #(rf/dispatch [:increase-agenda-forms])}])

(defn- submit-agenda-button []
  [:input.btn.button-primary {:type "button"
                              :value (data/labels :meeting-create-header)
                              :on-click #(rf/dispatch [:send-agendas])}])

;; #### header ####

(defn- header []
  (base/header
    (data/labels :agenda-header)
    (data/labels :agenda-subheader)))


(defn agenda-view
  "Shows the view for adding one or more agendas."
  []
  [:div#create-agenda
   [header]
   [:div.container.px-5.py-3.text-center
    [:div.agenda-meeting-title
     [:h2 (:title @(rf/subscribe [:meeting/last-added]))]
     [:br]
     [:h4 (:description @(rf/subscribe [:meeting/last-added]))]]
    [:div.container.agenda-container
     (for [agenda-num (range @(rf/subscribe [:agenda/number-of-forms]))]
       [:div {:key agenda-num}
        [new-agenda-form agenda-num]])]
    [:div.agenda-line]
    [add-agenda-button]
    [:br]
    [:br]
    [:br]
    [submit-agenda-button]]])

(defn agenda-in-meeting-view
  "The view of an agenda which gets embedded inside a meeting view."
  []
  [:div
   (let [agendas @(rf/subscribe [:current-agendas])]
     (for [agenda agendas]
       [:div.card
        {:key (random-uuid)
         :on-click (fn []
                     (rf/dispatch [:navigate :routes/meetings.discussion.start
                                   {:id (:discussion-id agenda)}])
                     (rf/dispatch [:choose-agenda agenda]))}
        [:p "Agenda: " (:title agenda)]
        [:p "Mehr Infos: " (:description agenda)]
        [:p "Discussion-ID: " (:discussion-id agenda)]
        [:br]]))])

;; #### Events ####

(rf/reg-event-fx
  :send-agendas
  (fn [{:keys [db]} _]
    (let [meeting-id (-> db :meeting/added :id)
          meeting-hash (-> db :meeting/added :share-hash)]
      {:http-xhrio {:method :post
                    :uri (str (:rest-backend config) "/agendas/add")
                    :params {:agendas (get-in db [:agenda :all] [])
                             :meeting-id meeting-id}
                    :format (ajax/json-request-format)
                    :response-format (ajax/json-response-format {:keywords? true})
                    :on-success [:on-successful-agenda-add meeting-hash]
                    :on-failure [:ajax-failure]}})))

(rf/reg-event-fx
  :on-successful-agenda-add
  (fn [_ [_ meeting-hash]]
    {:dispatch-n [[:clear-current-agendas]
                  [:navigate :routes/meetings.show {:share-hash meeting-hash}]]}))

(rf/reg-event-fx
  :load-agendas
  (fn [_ [_ hash]]
    {:http-xhrio {:method :get
                  :uri (str (:rest-backend config) "/agendas/by-meeting-hash/" hash)
                  :format (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:set-current-agendas]
                  :on-failure [:ajax-failure]}}))

(rf/reg-event-fx
  :load-agenda-information
  (fn [{:keys [db]} [_ discussion-id]]
    (when-not (-> db :agenda :chosen)
      {:http-xhrio {:method :get
                    :uri (str (:rest-backend config) "/agenda/" discussion-id)
                    :format (ajax/json-request-format)
                    :response-format (ajax/json-response-format {:keywords? true})
                    :on-success [:set-response-as-agenda]
                    :on-failure [:agenda-not-available]}})))

(rf/reg-event-fx
  :agenda-not-available
  (fn [{:keys [db]} _]
    {:db (assoc-in db [:error :ajax] "Agenda could not be loaded, please refresh the App.")
     :dispatch [:navigate :routes/meetings]}))

(rf/reg-event-db
  :set-current-agendas
  (fn [db [_ response]]
    (assoc-in db [:agendas :current] (:agendas response))))

(rf/reg-event-db
  :clear-current-agendas
  (fn [db _]
    (assoc-in db [:agendas :current] [])))

(rf/reg-event-db
  :increase-agenda-forms
  (fn [db _]
    (update-in db [:agenda :number-of-forms] inc)))

(rf/reg-event-db
  :agenda/update-title
  (fn [db [_ content suffix]]
    (assoc-in db [:agenda :all suffix :title] content)))

(rf/reg-event-db
  :agenda/update-description
  (fn [db [_ content suffix]]
    (assoc-in db [:agenda :all suffix :description] content)))

(rf/reg-event-db
  :reset-temporary-agenda
  (fn [db _]
    (assoc db :agenda {:number-of-forms 1 :all {}})))

(rf/reg-event-db
  :choose-agenda
  (fn [db [_ agenda]]
    (assoc-in db [:agenda :chosen] agenda)))

(rf/reg-event-db
  :set-response-as-agenda
  (fn [db [_ response]]
    (assoc-in db [:agenda :chosen] (:agenda response))))

;; #### Subs ####

(rf/reg-sub
  :agenda/number-of-forms
  (fn [db _]
    (-> db :agenda :number-of-forms)))

(rf/reg-sub
  :current-agendas
  (fn [db _]
    (get-in db [:agendas :current])))

(rf/reg-sub
  :chosen-agenda
  (fn [db _]
    (get-in db [:agenda :chosen])))