(ns meetly.meeting.interface.views.discussion.view-elements
  (:require [re-frame.core :as rf]
            [meetly.meeting.interface.text.display-data :refer [labels fa]]
            [ghostwheel.core :refer [>defn]]
            ["jdenticon" :as jdenticon]
            [meetly.meeting.interface.config :refer [config]]
            [meetly.meeting.interface.views.discussion.logic :as logic]
            [oops.core :refer [oget]]
            [meetly.meeting.interface.views.base :as base]
            [ajax.core :as ajax]))

(defn up-down-vote
  "Add panel for up and down votes."
  [statement]
  (let [votes @(rf/subscribe [:local-votes])]
    [:div.up-down-vote
     [:p {:on-click (fn [e]
                      (.stopPropagation e)                  ;; Prevent activating the time travel or deep dive
                      (rf/dispatch [:toggle-upvote statement]))}
      [:i.pr-1 {:class (str "m-auto fas " (fa :arrow-up))}]
      (logic/calculate-votes statement :upvotes votes)]
     [:p {:on-click (fn [e]
                      (.stopPropagation e)
                      (rf/dispatch [:toggle-downvote statement]))}
      [:i.pr-1 {:class (str "m-auto fas " (fa :arrow-down))}]
      (logic/calculate-votes statement :downvotes votes)]]))

(>defn avatar
  "Create an image based on the nickname."
  [name size]
  [string? number? :ret vector?]
  [:div.avatar.text-center
   [:div.avatar-image.img-thumbnail.rounded-circle
    {:dangerouslySetInnerHTML {:__html (jdenticon/toSvg name size)}}]
   [:div.avatar-name name]])

;; discussion header

(defn discussion-header [current-meeting]
  ;; meeting header
  (base/discussion-header
    (:meeting/title current-meeting)
    (:meeting/description current-meeting)
    (fn []
      (rf/dispatch [:navigate :routes/meetings.show
                    {:share-hash (:meeting/share-hash current-meeting)}])
      (rf/dispatch [:select-current-meeting current-meeting]))))

;; discussion loop box

(defn agenda-header-back-arrow [on-click-back-function]
  (let [agenda @(rf/subscribe [:chosen-agenda])]
    [:div.discussion-view-top-rounded
     [:div.row
      ;; back arrow
      [:div.col-lg-1.back-arrow
       (when on-click-back-function
         [:i.arrow-icon {:class (str "m-auto fas " (fa :arrow-left))
                         :on-click on-click-back-function}])]
      ;; title
      [:div.col-lg-11
       [:div
        [:h2 (:agenda/title agenda)]
        [:p (:agenda/description agenda)]]]]]))

(defn input-footer [content]
  [:div.discussion-view-bottom-rounded
   content])

;; text input

(defn- input-starting-argument-form
  "A form, which allows the input of a complete argument.
  (Premise and Conclusion as statements)"
  []
  [:form
   {:on-submit (fn [e] (.preventDefault e)
                 (rf/dispatch [:continue-discussion :starting-argument/new
                               (oget e [:target :elements])]))}
   [:input.form-control.discussion-text-input.mb-1
    {:type "text" :name "conclusion-text"
     :auto-complete "off"
     :placeholder (labels :discussion/add-argument-conclusion-placeholder)}]
   [:br]
   [:input.form-control.discussion-text-input.mb-1
    {:type "text" :name "premise-text"
     :auto-complete "off"
     :placeholder (labels :discussion/add-argument-premise-placeholder)}]
   [:div.text-center.button-spacing-top
    [:button.btn.button-secondary {:type "submit"} (labels :discussion/create-argument-action)]]])

(defn input-field []
  (let [allow-new-argument? @(rf/subscribe [:allow-new-argument?])]
    [:div.discussion-view-bottom-rounded
     (when allow-new-argument?
       [:div
        [:h5 (labels :discussion/create-argument-heading)]
        [:br]
        [input-starting-argument-form]])]))


(defn input-form
  "Text input for adding a statement"
  []
  [:div
   [:input.form-control.discussion-text-input.mb-1
    {:type "text" :name "premise-text"
     :auto-complete "off"
     :placeholder (labels :discussion/premise-placeholder)}]
   ;; add button
   [:div.text-center.button-spacing-top
    [:button.btn.button-secondary {:type "submit"} (labels :discussion/create-starting-premise-action)]]])

;; selection

(defn radio-button
  "Radio Button helper function. This function creates a radio button."
  [id name value label checked?]
  [:div.custom-control.custom-radio
   [:input.custom-control-input.custom-radio-button
    {:type "radio"
     :id id
     :name name
     :value value
     :default-checked checked?}]
   [:label.custom-control-label.custom-radio-button-label.clickable
    {:for id} (labels label)]])


;; bubble

(defn- statement-bubble
  "A single bubble of a statement to be used ubiquitously."
  ([statement]
   (statement-bubble statement (logic/arg-type->attitude (:meta/argument.type statement))))
  ([{:keys [statement/content] :as statement} attitude]
   [:div.card.statement {:class (str "statement-" (name attitude))}
    [:div.row
     [:div.col-1
      (up-down-vote statement)]
     [:div.col-11
      (when (= :argument.type/undercut (:meta/argument.type statement))
        [:p.small.text-muted (labels :discussion/undercut-bubble-intro)])
      [:small.text-right.float-right (avatar (-> statement :statement/author :author/nickname) 50)]
      [:p content]]]]))

;; carousel

(defn premises-carousel [premises]

  [:div#carouselExampleIndicators.carousel.slide {:data-ride "carousel"}
   ;; indicator
   [:ol.carousel-indicators.carousel-indicator-custom
    ;; range of number of premises and set the first element as selected
    (map
      (fn [i]
        (let [params {:key (str "indicator-" i) :data-target "#carouselExampleIndicators" :data-slide-to (str i)}]
          (if (= i 0)
            [:li.active params]
            [:li params])))
      (range (count premises)))]
   ;; content
   [:div.carousel-inner
    ;; set first indexed element as selected
    (map-indexed
      (fn [index premise]
        (let [params {:key (:db/id premise)}
              content [:div.premise-carousel-item
                       {:on-click #(rf/dispatch [:continue-discussion :premises/select premise])}
                       [statement-bubble premise]]]
          (if (= index 0)
            [:div.carousel-item.active params content]
            [:div.carousel-item params content])))
      premises)]
   ;; interface elements
   [:a.carousel-control-prev {:href "#carouselExampleIndicators" :role "button" :data-slide "prev"}
    [:span.carousel-control-prev-icon {:aria-hidden "true"}]
    [:span.sr-only "Previous"]]
   [:a.carousel-control-next {:href "#carouselExampleIndicators" :role "button" :data-slide "next"}
    [:span.carousel-control-next-icon {:aria-hidden "true"}]
    [:span.sr-only "Next"]]])


(defn conclusions-list []
  (let [agenda @(rf/subscribe [:chosen-agenda])
        conclusions @(rf/subscribe [:starting-conclusions])
        meeting @(rf/subscribe [:selected-meeting])]
    [:div#conclusions-list.container
     (for [conclusion conclusions]
       [:div {:key (:statement/content conclusion)
              :on-click (fn [_e]
                          (rf/dispatch [:continue-discussion :starting-conclusions/select conclusion])
                          (rf/dispatch [:navigate :routes/meetings.discussion.continue
                                        {:id (-> agenda :agenda/discussion :db/id)
                                         :share-hash (:meeting/share-hash meeting)}]))}
        [statement-bubble conclusion :neutral]])]))


(defn history-view
  "Displays the statements it took to get to where the user is."
  []
  (let [history @(rf/subscribe [:discussion-history])
        indexed-history (map-indexed #(vector (- (count history) %1 1) %2) history)]
    [:div#discussion-history
     (for [[count [statement attitude]] indexed-history]
       [:div {:key (:db/id statement)
              :on-click #(rf/dispatch [:discussion.history/time-travel count])}
        [statement-bubble statement attitude]])]))


(rf/reg-event-fx
  :toggle-upvote
  (fn [{:keys [db]} [_ {:keys [db/id] :as statement}]]
    {:http-xhrio {:method :post
                  :uri (str (:rest-backend config) "/votes/up/toggle")
                  :format (ajax/transit-request-format)
                  :params {:statement-id id
                           :nickname (get-in db [:user :name] "Anonymous")
                           :meeting-hash (-> db :meeting :selected :meeting/share-hash)}
                  :response-format (ajax/transit-response-format)
                  :on-success [:upvote-success statement]
                  :on-failure [:ajax-failure]}}))

(rf/reg-event-fx
  :toggle-downvote
  (fn [{:keys [db]} [_ {:keys [db/id] :as statement}]]
    {:http-xhrio {:method :post
                  :uri (str (:rest-backend config) "/votes/down/toggle")
                  :format (ajax/transit-request-format)
                  :params {:statement-id id
                           :nickname (get-in db [:user :name] "Anonymous")
                           :meeting-hash (-> db :meeting :selected :meeting/share-hash)}
                  :response-format (ajax/transit-response-format)
                  :on-success [:downvote-success statement]
                  :on-failure [:ajax-failure]}}))

(rf/reg-event-db
  :upvote-success
  (fn [db [_ {:keys [db/id]} {:keys [operation]}]]
    (if (= operation :added)                                ; Other option is :removed
      (update-in
        (update-in db [:votes :up] conj id)
        [:votes :down] disj id)
      (update-in db [:votes :up] disj id))))

(rf/reg-event-db
  :downvote-success
  (fn [db [_ {:keys [db/id]} {:keys [operation]}]]
    (if (= operation :added)
      (update-in
        (update-in db [:votes :down] conj id)
        [:votes :up] disj id)
      (update-in db [:votes :down] disj id))))
