(ns schnaq.interface.views.discussion.share
  (:require [re-frame.core :as rf]
            [schnaq.interface.components.icons :refer [icon]]
            [schnaq.interface.components.schnaq :as sc]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.clipboard :as clipboard]
            [schnaq.interface.utils.tooltip :as tooltip]
            [schnaq.interface.views.common :as common]
            [schnaq.interface.views.modal :as modal]
            [schnaq.interface.views.notifications :refer [notify!]]
            [schnaq.links :as links]))

(defn- share-qanda-via-access-code []
  (when @(rf/subscribe [:schnaq.selected/access-code])
    [:<>
     [:hr.my-4]
     [:div.d-flex.flex-row.my-3
      [:p (labels :share-access-code/via)]
      [:div.flex.flex-fill.text-center.pe-5
       [:p.mb-0 (labels :share-access-code/title-1) " " [:span.text-monospace "https://schnaq.app"]]
       [:p (labels :share-access-code/title-2)]
       [:div.display-4.text-primary [sc/access-code]]]]]))

(defn share-via-qr [link]
  [:<>
   [:hr.my-4]
   [:div.d-flex.flex-row.flex-wrap.my-3
    [:p (labels :share-qr-code/via)]
    [:div.flex.flex-fill.text-center.pe-5
     [:div.d-md-none
      [sc/qr-code link 200]]
     [:div.d-none.d-md-block
      [sc/qr-code link]]]]])

(defn- share-via-link [link]
  [:<>
   [:hr.my-4]
   [:div.d-flex.flex-row.flex-wrap.flex-md-nowrap.my-3.pb-3.my-1
    [:div.d-flex.me-1.me-lg-5.align-self-center (labels :share-link/via)]
    [:div.d-flex.flex-row.flex-grow-1.flex-wrap.flex-md-nowrap
     ;[:div.input-group]
     [:input.form-control.border-0.text-gray.my-1
      {:value link
       :readOnly true}]
     [:button.btn.btn-primary.text-nowrap.ms-md-3.my-1
      {:on-click (fn []
                   (clipboard/copy-to-clipboard! link)
                   (notify! (labels :schnaq/link-copied-heading)
                            (labels :schnaq/link-copied-success)
                            :info
                            false))}
      (labels :share-link/copy)]]]])

(defn- share-qanda-modal
  "Modal showing sharing options."
  []
  (let [share-hash @(rf/subscribe [:schnaq/share-hash])
        share-link (links/get-link-to-ask-interface share-hash)]
    [modal/modal-template
     (labels :sharing.modal/title)
     [:section
      [share-qanda-via-access-code]
      [share-via-link share-link]
      [share-via-qr share-link]
      [:div.mx-auto.py-5.mt-3
       [common/schnaqqi-speech-bubble-blue
        "100px"
        (labels :sharing.modal/qanda-help)]]]]))

(defn share-discussion-modal
  "Modal showing sharing options."
  []
  (let [share-hash @(rf/subscribe [:schnaq/share-hash])
        share-link (links/get-share-link share-hash)]
    [modal/modal-template
     (labels :sharing.modal/title)
     [:section
      [share-via-link share-link]
      [share-via-qr share-link]
      [:div.mx-auto.py-5.mt-3
       [common/schnaqqi-speech-bubble-blue
        "100px"
        (labels :sharing.modal/schnaqqi-help)]]]]))

(defn open-share-qanda
  "Open the share-schnaq dialog."
  []
  (rf/dispatch [:modal {:show? true
                        :large? true
                        :child [share-qanda-modal]}]))

(defn open-share-discussion
  "Open the share-schnaq dialog."
  []
  (rf/dispatch [:modal {:show? true
                        :large? true
                        :child [share-discussion-modal]}]))

(defn share-qanda-button
  "Button to copy access link and acces code."
  []
  [tooltip/tooltip-button "bottom" (labels :sharing/tooltip)
   [:<>
    [icon :share "m-auto d-block" {:size "lg"}]
    [:span.small (labels :discussion.navbar/share)]]
   open-share-qanda])
