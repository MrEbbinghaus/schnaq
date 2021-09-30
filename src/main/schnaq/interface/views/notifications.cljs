(ns schnaq.interface.views.notifications
  "Prints notifications on the screen. See specs for more options.

  Minimal example:
  `(rf/dispatch
     [:notification/add
       #:notification{:title \"Hello, World!\"
                      :body \"I am a toast\"
                      :context :primary}])`

  Extended example:
  `{:dispatch [:notification/add
               #:notification{:title \"Fehler\"
                              :body [:pre [:code (str failure)]]
                              :context :danger
                              :stay-visible? true
                              :on-close-fn #(rf/dispatch [:clear-error])}]}`"
  (:require ["framer-motion" :refer [AnimatePresence]]
            [cljs.spec.alpha :as s]
            [clojure.string :as string]
            [ghostwheel.core :refer [>defn- >defn]]
            [goog.string :as gstring]
            [re-frame.core :as rf]
            [reagent.dom]
            [schnaq.interface.components.icons :refer [fa]]
            [schnaq.interface.components.motion :as motion]))

(def ^:private display-time
  "Milliseconds, that a notification stays visible."
  5000)

(defn- toast-classes
  "Specify toast classes, depending on the context it is being used."
  [context]
  (let [common-classes "show toast"]
    (if context
      (gstring/format "%s toast-%s" common-classes (str (name context)))
      common-classes)))

(>defn- toast
  "Adds a toast to the screen. Has a title and a body, id is randomly generated.
   The context uses the same classes as it is known from bootstrap (e.g. primary,
   secondary, ...)."
  [{:notification/keys [title body id context on-close-fn]}]
  [::notification :ret associative?]
  [motion/fade-in-and-out
   [:article
    {:class-name (toast-classes context)
     :aria-atomic "true", :aria-live "assertive", :role "alert"}
    [:div.toast-header
     [:strong.mr-auto title]
     [:button.close {:type "button"
                     :on-click (fn []
                                 (when on-close-fn (on-close-fn))
                                 (rf/dispatch [:notification/remove id]))}
      [:span {:aria-hidden "true"}
       [:i {:class (str " m-auto fas fa-xs " (fa :delete-icon))}]]]]
    [:div.toast-body.scrollable-toast body]]])

(>defn notify!
  "Directly dispatch a notification event, which shall immediately show up to
  the user."
  [title body context stay-visible?]
  [:notification/title :notification/body :notification/context :notification/stay-visible?
   :ret nil?]
  (rf/dispatch
    [:notification/add
     #:notification{:title title
                    :body body
                    :stay-visible? stay-visible?
                    :context context}]))

(defn view
  "Presenting all notifications to the user."
  []
  (let [notifications @(rf/subscribe [:notifications/all])]
    [:div#notifications-wrapper.notifications-wrapper
     {:aria-live "polite"
      :aria-atomic true}
     [:> AnimatePresence
      (for [notification notifications]
        [:div {:key (:notification/id notification)}
         [toast notification]])]]))


;; -----------------------------------------------------------------------------

(s/def ::non-blank-string (s/and string? (complement string/blank?)))

(s/def :notification/title ::non-blank-string)
(s/def :notification/body ::non-blank-string)
(s/def :notification/id string?)
(s/def :notification/context #{:primary :secondary :success :danger :warning :info})
(s/def :notification/stay-visible? boolean?)
(s/def :notification/on-close-fn fn?)
(s/def ::notification
  (s/keys :req [:notification/title :notification/body :notification/context]
          :opt [:notification/id :notification/stay-visible?
                :notification/on-close-fn]))


;; -----------------------------------------------------------------------------

(rf/reg-event-fx
  :notification/add
  (fn [{:keys [db]} [_ {:notification/keys [stay-visible? id] :as notification}]]
    (let [notification-id (or id (str (random-uuid)))
          notification' (assoc notification :notification/id notification-id)]
      (cond-> {:db (update db :notifications conj notification')}
              ;; Auto-hide notification if not specified otherwise
              (not stay-visible?) (assoc :fx [[:notification/timed-remove notification-id]])))))

(rf/reg-fx
  :notification/timed-remove
  (fn [notification-id]
    (js/setTimeout #(rf/dispatch [:notification/remove notification-id])
                   display-time)))

(rf/reg-event-db
  :notification/remove
  (fn [db [_ notification-id]]
    (when-let [notifications (:notifications db)]
      (assoc db :notifications
                (remove (fn [{:notification/keys [id]}]
                          (= id notification-id))
                        notifications)))))

(rf/reg-event-db
  :notifications/reset
  (fn [db [_]]
    (assoc db :notifications [])))

(rf/reg-sub
  :notifications/all
  (fn [db]
    (get db :notifications)))
