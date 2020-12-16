(ns schnaq.interface.views.text-editor.view
  (:require [re-frame.core :as rf]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [schnaq.interface.text.display-data :as data]
            ["easymde" :as mde]))

(defn view
  "Mark Up Text Editor View"
  ([initial-text on-change-function text-update]
   [view initial-text on-change-function text-update "300px"])
  ([initial-text on-change-function _text-update min-height]
   (let [mde-ref (reagent/atom nil)
         ;; Needed to not write an empty value to the db on unmount. Otherwise writing
         ;; an empty string to the db will cause an empty agenda form to appear. The empty
         ;; string is needed to not implicitly carry the text over to another view.
         unmounting? (reagent/atom false)]
     (reagent/create-class
       {:display-name "markdown-editor"
        :reagent-render (fn [] [:textarea])
        :component-did-update
        (fn [comp _argv]
          (let [[_ initial-text _ text-update _] (reagent/argv comp)]
            ;; Update value of MDE only if the current value is different to the current one.
            (when text-update
              (.value @mde-ref text-update)
              (rf/dispatch [:agenda.edit/reset-editor-update-flag]))
            ;; Update with the initial-text, when value is not set (for lazy loaded content)
            (when (and initial-text (empty? (.value @mde-ref)))
              (.value @mde-ref initial-text))))
        :component-will-unmount (fn []
                                  (reset! unmounting? true)
                                  (.value @mde-ref ""))
        :component-did-mount
        (fn [comp]
          (let [newMDE (mde.
                         (clj->js {:element (rdom/dom-node comp)
                                   :minHeight min-height
                                   :spellChecker false
                                   :sideBySideFullscreen false
                                   :placeholder (data/labels :meeting-form-description-placeholder)}))]
            (reset! mde-ref newMDE)
            (when initial-text (.value @mde-ref initial-text))
            (.on (.-codemirror newMDE) "change"
                 (fn [_e]
                   (when-not @unmounting?
                     (on-change-function (.value @mde-ref)))
                   (reset! unmounting? false)))))}))))
