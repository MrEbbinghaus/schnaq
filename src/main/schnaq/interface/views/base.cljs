(ns schnaq.interface.views.base
  (:require [clojure.string :as str]
            [goog.string :as gstring]
            [reitit.frontend.easy :as reitfe]
            [schnaq.interface.components.icons :refer [fa]]
            [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.js-wrapper :as jw]
            [schnaq.interface.views.feedback.collect :as feedback]))

(defn wavy-curve
  "Define a wavy curve."
  ([]
   [wavy-curve "" false])
  ([transformation]
   [wavy-curve transformation false])
  ([transformation gradient?]
   (let [svg-class (if gradient? "wavy-curve-gradient" "wavy-curve")]
     [:svg
      {:class svg-class
       :xmlSpace "preserve"
       :overflow :auto
       :viewBox "0 0 19 4"
       :preserveAspectRatio :none
       :style {:transform transformation
               :-webkit-transform transformation}
       :y "0px"
       :x "0px"}
      [:path {:d "M0 0 L 0 3 Q 3 3, 6 2 T 12 2 T 19 2 L 19 0"}]])))

(defn header
  "Build a header with a curly bottom for a page. Heading, subheading and more will be included in the header."
  [{:page/keys [heading subheading classes more-for-heading vertical-header? wrapper-classes]}]
  [:<>
   [:div
    {:class (if (str/blank? wrapper-classes) "container" wrapper-classes)}
    (if vertical-header?
      [:<> [:h1 heading] [:h4.display-6 subheading]]
      [:div.row.mt-5.mb-2
       ;; If split header is configured, but the screen is too small, display
       ;; the headings one below the other
       [:div.col-12.col-md-6 [:h1 heading]]
       [:div.col-12.col-md-6 [:h2.h4 subheading]]])
    more-for-heading]
   (cond
     (gstring/contains (str classes) "bg-white") [:div.wave-bottom-white]
     (gstring/contains (str classes) "bg-dark-blue") [:div.wave-bottom-dark-blue]
     :else [:div.wave-bottom-light])])

(defn img-bullet-subtext
  "Create one icon in a grid"
  [path-to-img heading subheading]
  [:div.d-flex.flex-row.p-1
   [:div [:img {:src path-to-img}]]
   [:p.h4 heading]
   [:p subheading]])


;; -----------------------------------------------------------------------------
;; Footer

(defn- logo-and-slogan []
  [:<>
   [:img.footer-schnaqqifant
    {:src (img-path :logo-white)}]
   [:div.lead.font-italic.pb-1
    (labels :startpage/heading)]])

(defn- footer-button
  [route-name content-label]
  [:li.list-inline-item
   [:a.btn.btn-sm.btn-outline-white {:href (reitfe/href route-name)}
    (labels content-label)]])

(defn- footer-nav []
  [:<>
   [:ul.list-inline
    [footer-button :routes/how-to :router/how-to]
    [footer-button :routes/code-of-conduct :coc/heading]
    [footer-button :routes/about-us :footer.buttons/about-us]
    [footer-button :routes/press :footer.buttons/press-kit]
    [footer-button :routes/publications :footer.buttons/publications]]
   [:ul.list-inline
    [:li.list-inline-item
     [:button.btn.btn-sm.btn-outline-white {:on-click feedback/show-feedback-modal}
      (labels :feedbacks/button)]]
    [footer-button :routes/privacy :router/privacy]
    [footer-button :routes/legal-note :footer.buttons/legal-note]
    [footer-button :routes/alphazulu :router/alphazulu]]])

(defn- developed-in-nrw []
  [:section.pt-3
   [:i {:class (str "fas " (fa :terminal))}]
   " " (labels :footer.tagline/developed-with) " "
   [:i {:class (str "m-auto fas " (fa :flask))}]
   (gstring/format " in NRW, Germany © schnaq %d" jw/get-date-year)])

(defn- social-media []
  [:section
   [:a.social-media-icon {:href "https://facebook.com/schnaq" :target :_blank}
    [:i.fa-2x.fab.fa-facebook]]
   [:a.social-media-icon {:href "https://instagram.com/schnaqqi" :target :_blank}
    [:i.fa-2x.fab.fa-instagram]]
   [:a.social-media-icon {:href "https://www.linkedin.com/company/schnaq" :target :_blank}
    [:i.fa-2x.fab.fa-linkedin]]
   [:a.social-media-icon {:href "https://twitter.com/getschnaq" :target :_blank}
    [:i.fa-2x.fab.fa-twitter]]
   [:a.social-media-icon {:href "https://github.com/schnaq" :target :_blank}
    [:i.fa-2x.fab.fa-github]]])

(defn- sponsors []
  [:section.sponsors
   [:small (labels :footer.sponsors/heading)]
   [:article
    [:a {:href "https://www.hetzner.com/cloud" :target :_blank}
     [:img {:src (img-path :logos/hetzner)}]]]])

(defn- registered-trademark []
  [:section
   [:small
    (labels :footer.registered/rights-reserved)
    ". schnaq" [:sup "®"] " "
    (labels :footer.registered/is-registered)
    "."]])


;; -----------------------------------------------------------------------------

(defn footer
  "Footer to display at the bottom the page."
  []
  [:footer
   [:div.container
    [:div.row
     [:div.col-md-4.col-12
      [logo-and-slogan]]
     [:div.col-md-8.col-12.text-md-right.pt-3.pt-md-0
      [footer-nav]]]
    [:div.row
     [:div.col-md-6.col-12
      [developed-in-nrw]
      [registered-trademark]]
     [:div.col-md-6.col-12.text-md-right.pt-3.pt-md-0
      [social-media]
      [sponsors]]]]])
