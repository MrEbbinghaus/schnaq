(ns meetly.meeting.interface.views
  (:require [reagent.dom]
            [re-frame.core :as rf]
            [clojure.string :as str]
            [oops.core :refer [oget]]))

;; -- Domino 5 - View Functions ----------------------------------------------

(defn clock
  []
  [:div.example-clock
   {:style {:color @(rf/subscribe [:time-color])}}
   (-> @(rf/subscribe [:time])
       .toTimeString
       (str/split " ")
       first)])

(defn color-input
  []
  [:div.color-input
   "Time color: "
   [:input {:type "text"
            :value @(rf/subscribe [:time-color])
            :on-change #(rf/dispatch [:time-color-change (-> % .-target .-value)])}]]) ;; <---

(defn- new-meeting-helper
  [form-elements]
  (rf/dispatch
    [:new-meeting
     {:title (oget form-elements [:title :value])
      :description (oget form-elements [:description :value])
      :end-date (.getTime (js/Date. (oget form-elements [:end-date :value])))
      :share-hash (str (random-uuid))
      :start-date (.now js/Date)}]))

(defn create-meeting-form []
  [:div.create-meeting-form
   [:form {:on-submit (fn [e] (.preventDefault e)
                        (new-meeting-helper (oget e [:target :elements])))}
    [:label {:for "title"} "Title: "]
    [:input#title {:type "text" :name "title"}] [:br]
    [:label {:for "description"} "Description: "]
    [:textarea#description {:name "description"}] [:br]
    [:label {:for "end-date"} "End Date: "]
    [:input#end-date {:type "datetime-local" :name "end-date"}] [:br]
    [:input {:type "submit" :value "Create Meetly"}]]])

(defn meetings-list []
  [:div.meetings-list
   [:h3 "Meetings"]
   (let [meetings @(rf/subscribe [:meetings])]
     (for [meeting meetings]
       [:div {:key (random-uuid)}
        [:p (:title meeting) " - " (:description meeting)]
        [:p "Start: "
         ;; TODO use joda.time in final application
         (str (js/Date. (js/Number. (:start-date meeting)))) " - End Date: "
         (str (js/Date. (js/Number. (:end-date meeting))))]
        [:p "Share-Hash: " (:share-hash meeting)]
        [:hr]]))])

(defn ui
  []
  [:div
   [:h1 "Meetly Central"]
   [:hr]
   [:h2 "Meeting controls"]
   [create-meeting-form]
   [:hr]
   [meetings-list]
   [:hr]
   [clock]
   [color-input]])


(defn main-page
  []
  (let [current-route @(rf/subscribe [:current-route])]
    [:div
     (when current-route
       [(-> current-route :data :view)])]))

(defn root []
  [:div#root
   {:style {:width "100vw"}}
   [main-page]])
