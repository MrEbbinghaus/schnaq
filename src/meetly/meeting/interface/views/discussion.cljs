(ns meetly.meeting.interface.views.discussion
  (:require [re-frame.core :as rf]
            [meetly.meeting.interface.config :refer [config]]
            [meetly.meeting.interface.text.display-data :refer [labels]]
            [ajax.core :as ajax]
            [oops.core :refer [oget]]
            [vimsical.re-frame.cofx.inject :as inject]))

;; #### Helpers ####

(defn select-premises
  "Selects the premises out of all arguments that have a corresponding conclusion.
  EXPERIMENTAL: Premisegroup-Members are treated individually instead of as a group."
  [arguments conclusion-id]
  (let [selected-arguments
        (filter #(= (get-in % [:argument/conclusion :db/id]) conclusion-id) arguments)]
    (mapcat
      #(partition 2 (interleave (:argument/premises %) (repeat (:argument/type %))))
      selected-arguments)))


(defn- index-of
  "Returns the index of the first occurrence of `elem` in `coll` if its present and
  nil if not."
  [coll elem]
  (let [maybe-index (.indexOf coll elem)]
    (if (= maybe-index -1)
      nil
      maybe-index)))

(defn- argument-from-conclusion
  "Given a sequence of arguments and a conclusion-id, get any argument that has the
  corresponding conclusion."
  [arguments conclusion-id]
  (first
    (filter #(= (get-in % [:argument/conclusion :db/id]) conclusion-id) arguments)))

(defn- args-for-reaction
  "Returns the args for a certain reaction."
  [all-steps all-args reaction]
  (nth all-args (index-of all-steps reaction)))

;; #### Views ####
(defn- single-statement-view
  "Displays a single starting conclusion-statement inside a discussion."
  [statement discussion-id]
  [:div.card {:style {:background-color "#6aadb8"
                      :width "600px"}
              :on-click (fn [_e]
                          (rf/dispatch [:choose-starting-conclusion (:db/id statement)])
                          (rf/dispatch [:navigate :routes/meetings.discussion.start.premises
                                        {:id discussion-id :conclusion-id (:db/id statement)}]))}
   [:p (:statement/content statement)]
   [:small "Written by: " (-> statement :statement/author :author/nickname)]])

(defn- input-starting-argument-form
  "A form, which allows the input of a complete argument.
  (Premise and Conclusion as statements)"
  []
  [:form
   {:on-submit (fn [e] (.preventDefault e)
                 (rf/dispatch [:continue-discussion :starting-argument/new
                               (oget e [:target :elements])]))}
   [:input.form-control.mb-1
    {:type "text" :name "conclusion-text"
     :placeholder (labels :discussion/add-argument-conclusion-placeholder)}]
   [:input.form-control.mb-1
    {:type "text" :name "premise-text"
     :placeholder (labels :discussion/add-argument-premise-placeholder)}]
   [:button.btn.btn-primary {:type "submit"} (labels :discussion/create-argument-action)]])

(defn all-positions-view
  "Shows a nice header and all positions."
  []
  (let [agenda @(rf/subscribe [:chosen-agenda])
        conclusions @(rf/subscribe [:starting-conclusions])]
    [:div.row.discussion-head
     [:div.col-12
      [:h2 (:agenda/title agenda)]
      [:p (:agenda/description agenda)]
      [:hr]
      (for [conclusion conclusions]
        [:div {:key (:statement/content conclusion)}
         [single-statement-view conclusion (-> agenda :agenda/discussion-id :db/id)]])]]))

(defn discussion-start-view
  "The first step after starting a discussion."
  []
  (let [allow-new-argument? @(rf/subscribe [:allow-new-argument?])]
    [:div#discussion-start
     [all-positions-view]
     [:hr]
     (when allow-new-argument?
       [:h3 (labels :discussion/create-argument-heading)]
       [input-starting-argument-form])]))

(defn- add-premise-for-starting-argument-form
  "Allows the adding of a premise for or against a starting argument."
  [conclusion-id]
  [:form
   {:on-submit (fn [e] (.preventDefault e)
                 (rf/dispatch [:continue-discussion-from-premises
                               (oget e [:target :elements]) conclusion-id]))}
   [:input#for-radio {:type "radio" :name "premise-choice" :value "for-radio"
                      :default-checked true}]
   [:label {:for "for-radio"} (labels :discussion/add-premise-supporting)] [:br]
   [:input#against-radio {:type "radio" :name "premise-choice" :value "against-radio"}]
   [:label {:for "against-radio"} (labels :discussion/add-premise-against)] [:br]
   [:input.form-control.mb-1
    {:type "text" :name "premise-text"
     :placeholder (labels :discussion/add-starting-premise-placeholder)}]
   [:button.btn.btn-primary {:type "submit"} (labels :discussion/create-argument-action)]])

(defn- single-premise-div
  "A single premise for or against some conclusion."
  [premise attitude]
  (let [attitude-string
        (if (= attitude :argument.type/support)
          "Zustimmung"                                      ;TODO string -> labels
          "Ablehnung")]
    [:div
     [:p.small.text-muted attitude-string]
     [:p (:statement/content premise)]]))


(defn discussion-starting-premises-view
  "The shows all premises regarding a conclusion which belongs to starting-arguments."
  []
  [:div#discussion-start-premises
   (let [selected-conclusion @(rf/subscribe [:current-starting-conclusion-id])
         starting-arguments @(rf/subscribe [:starting-arguments])
         premises-to-show (select-premises starting-arguments selected-conclusion)
         allow-new-argument? @(rf/subscribe [:allow-new-argument?])]
     [:div
      [:p selected-conclusion]
      [:hr]
      (for [[premise argument-type] premises-to-show]
        [:div.premise {:key (:db/id premise)}
         [single-premise-div premise argument-type]])
      [:hr]
      (when allow-new-argument?
        [:div
         [:h3 (labels :discussion/create-argument-heading)]
         [add-premise-for-starting-argument-form selected-conclusion]])])])

;; #### Events ####

(rf/reg-event-fx
  :start-discussion
  (fn [{:keys [db]} [_ try-counter]]
    (let [discussion-id (-> db :agenda :chosen :agenda/discussion-id :db/id)
          username (get-in db [:user :name] "Anonymous")
          try-counter (or try-counter 0)]
      (when (< try-counter 10)
        (if discussion-id
          {:http-xhrio {:method :get
                        :uri (str (:rest-backend config) "/start-discussion/" discussion-id)
                        :format (ajax/transit-request-format)
                        :url-params {:username username}
                        :response-format (ajax/transit-response-format)
                        :on-success [:set-current-discussion-steps]
                        :on-failure [:ajax-failure]}}
          {:dispatch-later [{:ms 50 :dispatch [:start-discussion (inc try-counter)]}]})))))

(rf/reg-event-db
  :set-current-discussion-steps
  (fn [db [_ response]]
    (-> db
        (assoc-in [:discussion :options :all] response)
        (assoc-in [:discussion :options :steps] (map first response))
        (assoc-in [:discussion :options :args] (map second response)))))

;; This and the following events serve as the multimethod-equivalent in the frontend
;; for stepping through the discussion.
(rf/reg-event-fx
  :continue-discussion
  (fn [_ [_ reaction args]]
    {:dispatch [reaction args]}))

(rf/reg-event-fx
  :starting-argument/new
  (fn [{:keys [db]} [reaction form]]
    (let [discussion-id (-> db :agenda :chosen :agenda/discussion-id :db/id)
          conclusion-text (oget form [:conclusion-text :value])
          premise-text (oget form [:premise-text :value])
          reaction-args
          (args-for-reaction (-> db :discussion :options :steps)
                             (-> db :discussion :options :args) :starting-argument/new)
          updated-args
          (-> reaction-args
              (assoc :new/starting-argument-conclusion conclusion-text)
              (assoc :new/starting-argument-premises [premise-text]))]
      {:dispatch-n [[:continue-discussion-http-call [reaction updated-args]]
                    [:navigate :routes/meetings.discussion.start {:id discussion-id}]]})))

(rf/reg-event-fx
  :continue-discussion-http-call
  (fn [_ [_ payload]]
    {:http-xhrio {:method :post
                  :uri (str (:rest-backend config) "/continue-discussion")
                  :format (ajax/transit-request-format)
                  :params payload
                  :response-format (ajax/transit-response-format)
                  :on-success [:set-current-discussion-steps]
                  :on-failure [:ajax-failure]}}))

(rf/reg-event-db
  :choose-starting-conclusion
  (fn [db [_ conclusion-id]]
    (assoc-in db [:discussion :starting-conclusion :selected :id] conclusion-id)))

(rf/reg-event-fx
  :continue-discussion-from-premises
  [(rf/inject-cofx ::inject/sub [:starting-arguments])]
  (fn [{:keys [db starting-arguments]} [_ form conclusion-id]]
    (let [any-step-args (first (-> db :discussion :options :args))
          argument-chosen (argument-from-conclusion starting-arguments conclusion-id)
          new-text (oget form [:premise-text :value])
          [reaction textkey]
          (case (oget form [:premise-choice :value])
            "for-radio" [:defend/new :new/defend]
            "against-radio" [:rebut/new :new/rebut])
          payload [reaction (-> any-step-args
                                (assoc :argument/chosen argument-chosen)
                                (assoc textkey [new-text])
                                (assoc :starting-argument? true))]]
      {:dispatch [:continue-discussion-http-call payload]})))

;; #### Subs ####

(rf/reg-sub
  :discussion-options
  (fn [db _]
    (get-in db [:discussion :options :all])))

(rf/reg-sub
  :discussion-steps
  (fn [db _]
    (get-in db [:discussion :options :steps])))

(rf/reg-sub
  :discussion-step-args
  (fn [db _]
    (get-in db [:discussion :options :args])))

(rf/reg-sub
  :starting-arguments
  :<- [:discussion-steps]
  :<- [:discussion-step-args]
  (fn [[steps args] _]
    (when (some #(= % :starting-argument/select) steps)
      (->>
        (index-of steps :starting-argument/select)
        (nth args)
        :present/arguments))))

(rf/reg-sub
  :starting-conclusions
  :<- [:starting-arguments]
  (fn [arguments _]
    (->>
      arguments
      (filter #(not= :argument.type/undercut (:argument/type %)))
      (map :argument/conclusion)
      distinct)))

(rf/reg-sub
  :allow-new-argument?
  :<- [:discussion-steps]
  (fn [steps]
    (some #(= % :starting-argument/new) steps)))

(rf/reg-sub
  :current-starting-conclusion-id
  (fn [db _]
    (get-in db [:discussion :starting-conclusion :selected :id])))