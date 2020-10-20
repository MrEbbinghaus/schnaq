(ns schnaq.interface.views.startpage.core
  "Defining the startpage of schnaq."
  (:require [ajax.core :as ajax]
            [oops.core :refer [oget]]
            [re-frame.core :as rf]
            [schnaq.interface.config :as config]
            [schnaq.interface.text.display-data :refer [labels img-path]]
            [schnaq.interface.views.base :as base]
            [schnaq.interface.utils.js-wrapper :as js-wrap]
            [schnaq.interface.views.modals.modal :as modal]
            [schnaq.interface.views.pages :as pages]
            [schnaq.interface.views.startpage.features :as startpage-features]))

(defn- header-animation
  "Display header animation video."
  []
  [:section.col-lg-6
   [:video.w-100.startpage-animation {:auto-play true :loop true :muted true :plays-inline true}
    [:source {:src (img-path :animation-discussion) :type "video/webm"}]
    [:source {:src (img-path :animation-discussion-mp4) :type "video/mp4"}]]])

(defn- start-schnaq-button
  "Tell user to create a schnaq now."
  []
  [:section
   [:button.button-call-to-action
    {:type "button"
     :on-click #(rf/dispatch [:navigation/navigate :routes.meeting/create])}
    (labels :startpage.button/create-schnaq)]])

(defn- under-construction
  []
  [:div.icon-bullets-larger
   (base/img-bullet-subtext (img-path :icon-crane)
                            (labels :startpage.under-construction/heading)
                            (labels :startpage.under-construction/body))])

(defn- usage-of-schnaq-heading
  "Heading introducing the features of schnaq."
  []
  [:div.d-flex.d-row.justify-content-center.py-3
   [:p.display-5 (labels :startpage.usage/lead)]
   [:img.pl-3.d-md-none.d-lg-block
    {:style {:max-height "3rem"}
     :src (img-path :schnaqqifant/original)}]])

(def wavy-top
  [:svg {:xmlns "http://www.w3.org/2000/svg" :viewBox "0 0 1440 320"} [:path {:fill "#1292ee" :fill-opacity "1" :d "M0,96L48,96C96,96,192,96,288,85.3C384,75,480,53,576,69.3C672,85,768,139,864,154.7C960,171,1056,149,1152,154.7C1248,160,1344,192,1392,208L1440,224L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"}]])

(defn- early-adopters
  "Present early-adopters section to catch up interest."
  []
  [:section.overflow-hidden.py-3
   [base/wavy-curve "scale(1.5,-1)"]
   [:div.early-adopter
    [:div.container.text-center.early-adopter-schnaqqifant-wrapper
     [:img.early-adopter-schnaqqifant.pull-right.d-none.d-md-inline
      {:src (img-path :schnaqqifant/white)}]
     [:p.h4 (labels :startpage.early-adopter/title)]
     [:p.lead.pb-3 (labels :startpage.early-adopter/body)]
     [:a.button-secondary {:href config/demo-discussion-link}
      (labels :startpage.early-adopter.buttons/join-schnaq)]
     [:p.pt-4 (labels :startpage.early-adopter/or)]
     [:button.button-secondary
      {:type "button"
       :on-click #(rf/dispatch [:navigation/navigate :routes.meeting/create])}
      (labels :startpage.button/create-schnaq)]]]
   [base/wavy-curve "scale(1.5,1)"]])

(defn- subscribe-to-mailinglist
  "Add possibility to subscribe to our mailing list."
  []
  [:section.container.text-center.subscribe-to-mailinglist
   [:p.h4 (labels :startpage.mailing-list/title)]
   [:p.lead.pb-3 (labels :startpage.mailing-list/body)]
   [:a.button-primary {:href "https://disqtec.com/newsletter"
                       :target "_blank"}
    (labels :startpage.mailing-list/button)]])

(defn- request-demo-modal
  "A modal which the user can use to request a demo"
  []
  [modal/modal-template
   "Persönliche Demo anfordern"
   [:form.form
    {:on-submit
     (fn [e]
       (js-wrap/prevent-default e)
       (rf/dispatch [:startpage.demo.request/send
                     (oget e [:target :elements :requester-name :value])
                     (oget e [:target :elements :requester-contact :value])
                     (oget e [:target :elements :requester-company :value])
                     (oget e [:target :elements :requester-phone :value])]))}
    [:div.form-group
     [:label {:for "demo-requester-name"}
      (labels :startpage.demo.request.modal.name/label)]
     [:input {:id "demo-requester-name"
              :class-name "form-control"
              :placeholder (labels :startpage.demo.request.modal.name/placeholder)
              :required true
              :autoFocus true :name "requester-name"}]]
    [:div.form-group.pb-2
     [:label {:for "demo-requester-contact"}
      (labels :startpage.demo.request.modal.email/label)]
     [:input {:id "demo-requester-contact" :name "requester-contact"
              :class-name "form-control" :type "email"
              :required true
              :placeholder (labels :startpage.demo.request.modal.email/placeholder)}]]
    [:div.form-group
     [:label {:for "demo-requester-company"}
      (labels :startpage.demo.request.modal.company/label)]
     [:input {:id "demo-requester-company" :name "requester-company"
              :class-name "form-control"
              :placeholder (labels :startpage.demo.request.modal.company/placeholder)}]
     [:small.form-text.text-muted
      (labels :feedbacks.modal/optional)]]
    [:div.form-group
     [:label {:for "demo-requester-phone"}
      (labels :startpage.demo.request.modal.phone/label)]
     [:input {:id "demo-requester-phone" :name "requester-phone"
              :class-name "form-control" :type "tel"
              :placeholder (labels :startpage.demo.request.modal.phone/placeholder)}]
     [:small.form-text.text-muted
      (labels :feedbacks.modal/optional)]]
    [:div.modal-footer
     [:input.btn.btn-primary.mr-auto {:type "submit"}]
     [:small.text-muted (labels :feedbacks.modal/disclaimer)]]]])

(rf/reg-event-fx
  :startpage.demo.request/send
  (fn [_ [_ name email company phone]]
    {:fx [[:http-xhrio {:method :post
                        :uri (str (:rest-backend config/config) "/emails/request-demo")
                        :params {:name name
                                 :email email
                                 :company company
                                 :phone phone}
                        :format (ajax/transit-request-format)
                        :response-format (ajax/transit-response-format)
                        :on-success [:startpage.demo.request/send-success]
                        :on-failure [:startpage.demo.request/send-failure]}]]}))

(rf/reg-event-fx
  :startpage.demo.request/send-success
  (fn [_ _]
    {:fx [[:dispatch [:modal {:show? false :child nil}]]
          [:dispatch [:notification/add
                      #:notification{:title (labels :startpage.demo.request.send.notification/title)
                                     :body (labels :startpage.demo.request.send.notification/body)
                                     :context :success}]]]}))

(rf/reg-event-fx
  :startpage.demo.request/send-failure
  (fn [_ _]
    {:fx [[:dispatch [:notification/add
                      #:notification{:title (labels :startpage.demo.request.send.notification/failed-title)
                                     :body (labels :startpage.demo.request.send.notification/failed-body)
                                     :context :alert}]]]}))
(defn- button-with-text-section
  "A button and text to navigate to the demo section"
  [button-label fn-navigation title body]
  [:div.row.align-items-center.feature-row.mt-5
   [:div.col-12.col-lg-5.text-center
    [:button.btn.button-secondary.font-150.mb-5
     {:on-click fn-navigation}
     (labels button-label)]]
   [:div.col-12.col-lg-6.offset-lg-1
    [:article.feature-text-box.pb-3
     [:h5 (labels title)]
     [:p (labels body)]]]])

(defn- request-demo-section
  "A button and some text to request a personal demo"
  []
  [button-with-text-section
   :startpage.demo.request/button
   #(rf/dispatch [:modal {:show? true :large? false
                          :child [request-demo-modal]}])
   :startpage.demo.request/title
   :startpage.demo.request/body])

(defn- how-to-section
  "A button and text to navigate to the demo section"
  []
  [button-with-text-section
   :how-to.startpage/button
   #(rf/dispatch [:navigation/navigate :routes/how-to])
   :how-to.startpage/title
   :how-to.startpage/body])

(defn- value-prop-card
  "A single value proposition-card"
  [title description image-path alt-text show-more-fn]
  [:div.card
   [:img.card-img-top {:src image-path :alt alt-text}]
   [:div.card-body.d-flex.flex-column
    [:h5.card-title title]
    [:p.card-text description]
    [:div.text-center.mt-auto
     [:button.btn.button-primary
      {:on-click show-more-fn}
      [:p.card-text [:small (labels :startpage.value-cards.button/text)]]]]]])

(defn- value-prop-cards
  "Cards displaying the different value propositions."
  []
  [:<>
   [:div.text-center
    [:h4 (labels :startpage.value-cards/heading)]
    [:p.lead (labels :startpage.value-cards/lead)]]
   [:div.card-deck.mb-5
    [value-prop-card
     (labels :startpage.value-cards.discussion/title)
     (labels :startpage.value-cards.discussion/description)
     (img-path :startpage.value-cards.discussion/image)
     (labels :startpage.value-cards.discussion/alt-text)
     #(rf/dispatch [:navigation/navigate :routes.features/discussions])]
    [value-prop-card
     (labels :startpage.value-cards.meetings/title)
     (labels :startpage.value-cards.meetings/description)
     (img-path :startpage.value-cards.meetings/image)
     (labels :startpage.value-cards.meetings/alt-text)
     #(rf/dispatch [:navigation/navigate :routes.features/meetings])]
    [value-prop-card
     (labels :startpage.value-cards.knowledge/title)
     (labels :startpage.value-cards.knowledge/description)
     (img-path :startpage.value-cards.knowledge/image)
     (labels :startpage.value-cards.knowledge/alt-text)
     #(rf/dispatch [:navigation/navigate :routes.features/knowledge])]]])

;; -----------------------------------------------------------------------------

(def ^:private header
  {:page/heading (labels :startpage/heading)
   :page/subheading (labels :startpage/subheading)
   :page/more-for-heading
   [:div.row.pt-5 {:key "HeaderExtras-Bullet-Points-and-Animation"}
    [:div.col-lg-6.icon-bullets
     (base/icon-bullet (img-path :icon-community) (labels :startpage.heading-list/community))
     (base/icon-bullet (img-path :icon-robot) (labels :startpage.heading-list/exchange))
     (base/icon-bullet (img-path :icon-reports) (labels :startpage.heading-list/reports))]
    [header-animation]]})

(defn- startpage-content []
  (pages/with-nav-and-header
    header
    [:<>
     [:section.container
      [:div.row {:style {:padding-top "5rem"}}
       [:div.col-12.col-lg-6.pb-3.pb-lg-0
        [under-construction]]
       [:div.col-12.col-lg-6.text-center
        [start-schnaq-button]]]
      [request-demo-section]
      [how-to-section]
      [usage-of-schnaq-heading]
      [startpage-features/feature-rows]]
     [early-adopters]
     [:section.container.py-5
      [value-prop-cards]]
     [subscribe-to-mailinglist]]))

(defn startpage-view
  "A view that represents the first page of schnaq participation or creation."
  []
  [startpage-content])