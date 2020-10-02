(ns schnaq.interface.views.text-editor.view
  (:require [re-frame.core :as rf]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [schnaq.interface.text.display-data :as data]
            ["easymde" :as mde]))

(defn view
  "Mark Up Text Editor View"
  ([initial-text on-change-function text-update]
   (view initial-text on-change-function text-update "300px"))
  ([initial-text on-change-function _text-update min-height]
   (let [mde-ref (reagent/atom nil)]
     (reagent/create-class
       {:display-name "markdown-editor"
        :reagent-render (fn [] [:textarea])
        :component-did-update
        (fn [comp _argv]
          (let [[_ _ _ text-update _] (reagent/argv comp)]
            ;; Update value of MDE only if the current value is different to the current one.
            (when text-update
              (.value @mde-ref text-update))))
        :component-did-mount
        (fn [comp]
          (let [newMDE (mde.
                         (clj->js {:element (rdom/dom-node comp)
                                   :minHeight min-height
                                   :spellChecker false
                                   :sideBySideFullscreen false
                                   :initialValue (data/labels :meeting-form-desc-placeholder)}))]
            (reset! mde-ref newMDE)
            (when initial-text (.value newMDE initial-text))
            (.on (.-codemirror newMDE) "change"
                 #(on-change-function (.value @mde-ref)))))}))))

(defn view-store-on-change
  "Mark Up Editor View which automatically stores its content in the local db.
  The value can be retrieved via subscribing to ':mde/load-content'"
  [storage-key]
  (view nil
        (fn [value]
          (rf/dispatch [:mde/save-content storage-key value]))
        nil))

(rf/reg-event-db
  :mde/save-content
  (fn [db [_ storage-key value]]
    (assoc-in db [:mde storage-key] value)))

(rf/reg-sub
  :mde/load-content
  (fn [db [_ storage-key]]
    (get-in db [:mde storage-key])))