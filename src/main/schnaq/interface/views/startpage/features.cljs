(ns schnaq.interface.views.startpage.features
  (:require [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.components.motion :as motion]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.rows :as rows]
            [schnaq.interface.views.startpage.preview-statements :as examples]))

(defn- example-question
  "Box describing what schnaq does and why"
  []
  [:div.mt-5.pt-5
   [rows/row-builder-text-left
    [rows/build-text-box :startpage.information.know-how]
    [examples/display-example-statements]]])

(defn- schnaq-promise
  "Box describing schnaq's promise to the user"
  []
  [:div.my-5.pt-lg-5
   [rows/row-builder-text-left
    [rows/build-text-box :startpage.information.positioning]
    [:div.example-dashboard-image
     [motion/zoom-image
      {:class "img-fluid shadow-lg rounded-2"
       :src (img-path :startpage.example/dashboard)}]]]])

(defn- use-it-anywhere []
  [:div.mb-5.py-lg-5
   [rows/row-builder-text-right
    [:img.shadow-lg.rounded-2
     {:src (img-path :startpage.information/anywhere)}]
    [rows/build-text-box :startpage.information.anywhere]]])

(defn- feature-box
  "A Single feature box that can be put in a row. All inputs are keys."
  [title body img-url img-alt]
  [:div.card.panel-transparent.text-center
   [:div.card-body
    [:img.startpage-feature-box-image.w-75 {:src (img-path img-url) :alt img-alt}]
    [:div.display-6.text-typography.card-title (labels title)]
    [:p.text-justify.card-text (labels body)]]])

(defn how-does-schnaq-work
  "Arguments for getting schnaq in three columns."
  []
  [:div
   [:div.card-deck
    [feature-box
     :startpage.feature-box.know-how/title
     :startpage.feature-box.know-how/body
     :startpage.schnaqqifant/easy
     :startpage.feature-box.know-how/img-alt]
    ;; This block is used to break the card deck into one card per row for devices smaller than md
    ;; Without this only sm devices break.
    [:div.w-100.d-none.d-sm-block.d-lg-none.py-2]
    [feature-box
     :startpage.feature-box.discussion/title
     :startpage.feature-box.discussion/body
     :startpage.schnaqqifant/time
     :startpage.feature-box.discussion/img-alt]
    [:div.w-100.d-none.d-sm-block.d-lg-none.py-2]
    [feature-box
     :startpage.feature-box.learnings/title
     :startpage.feature-box.learnings/body
     :startpage.schnaqqifant/know-how
     :startpage.feature-box.learning/img-alt]]])

;; -----------------------------------------------------------------------------

(defn feature-rows
  "Collection of feature rows."
  []
  [:<>
   [example-question]
   [use-it-anywhere]
   [schnaq-promise]])
