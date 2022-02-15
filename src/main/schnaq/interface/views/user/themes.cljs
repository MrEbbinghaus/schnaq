(ns schnaq.interface.views.user.themes
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [com.fulcrologic.guardrails.core :refer [>defn >defn- =>]]
            [oops.core :refer [oget oget+]]
            [re-frame.core :as rf]
            [schnaq.interface.views.schnaq.activation :as activation]
            [schnaq.interface.views.discussion.conclusion-card :refer [selection-card]]
            [schnaq.interface.utils.js-wrapper :as js-wrap]
            [schnaq.interface.views.pages :as pages]
            [schnaq.interface.views.user.settings :as settings]
            [schnaq.interface.views.navbar.elements :as elements]
            [schnaq.interface.components.buttons :as buttons]
            [schnaq.interface.utils.http :as http]))

(s/def ::hex-color (s/and string? #(.startsWith % "#")))
(s/def ::css-variable (s/and string? #(.startsWith % "--")))
(s/def :theme/field #{:primary :secondary :background :logo :activation})

(>defn- theme-field->css-variable
  "Convert between internally used keywords for addressing the currently
  configured field and the css-variable."
  [theme-field]
  [keyword? => ::css-variable]
  (str "--theming-" (name theme-field)))

(>defn- color-from-root
  "Read css color value."
  [theme-field]
  [:theme/field => ::hex-color]
  (str/trim
   (.getPropertyValue
    (js/getComputedStyle (.-body js/document))
    (theme-field->css-variable theme-field))))
(comment

  (keyword (name :theme.colors/primary))

  nil)
(>defn- set-root-color
  "Set global root css definition to document."
  [css-variable color]
  [::css-variable ::hex-color => nil?]
  (.setProperty
   (.. js/document -documentElement -style)
   css-variable color))

(>defn- color-picker
  "Color picker for the theme colors."
  [theme-field label]
  [keyword? string? => :re-frame/component]
  (let [color (get @(rf/subscribe [:theme/selected]) theme-field)
        color-picker-id (str (name theme-field) "-color-picker")]
    [:div.row
     [:div.col
      [:label.form-label {:for color-picker-id} label]]
     [:div.col
      [:input.form-control.form-control-color
       {:id color-picker-id
        :type :color
        :value (if color color (color-from-root (keyword (name theme-field))))
        :name (str "color-" (name theme-field))
        :on-change (fn [e]
                     (let [color (oget e :target :value)]
                       (rf/dispatch [:theme.selected/color theme-field color])))}]]]))

(defn- preview []
  [:<>
   [:h2 "Vorschau"]
   [:section.theming-enabled
    [:div.base-wrapper.p-3
     [elements/navbar-title
      [:h1.h6.fw-bold.my-auto.text-dark "Welcome to schnaq"]
      nil false]
     [activation/activation-card]
     [:div.d-flex.flex-row
      [buttons/button "primary button"]
      [buttons/button "secondary button" (constantly "#") "btn-secondary ms-2"]]
     [selection-card]]]])

;; -----------------------------------------------------------------------------

(defn- loaded-themes
  "Display all available themes."
  []
  (let [themes @(rf/subscribe [:themes/personal])
        selected @(rf/subscribe [:theme/selected])]
    [:section.pb-5
     [:h4 "Deine Themen"]
     [:div.row.row-cols-2.row-cols-xl-4.g-2.g-lg-3
      (for [theme themes]
        (with-meta
          [:div.col
           [buttons/button (:theme/title theme) #(rf/dispatch [:theme/select theme])
            (if (= (:db/id selected) (:db/id theme))
              "btn-outline-secondary shadow"
              "btn-outline-dark")]]
          {:key (str "theme-" (:db/id theme))}))
      [:div.col
       [buttons/button
        "Neues Thema erstellen"
        #(rf/dispatch [:theme/select nil])
        "btn-outline-primary"]]]]))

(defn- configure-theme
  "TODO"
  []
  (let [selected @(rf/subscribe [:theme/selected])
        user @(rf/subscribe [:user/current])]
    [:form
     {:on-submit (fn [e]
                   (let [add-or-edit (if (:db/id selected) :theme/edit :theme/add)]
                     (js-wrap/prevent-default e)
                     (rf/dispatch [add-or-edit (oget e [:target :elements])])))}
     [:div.form-floating.mb-3
      [:input.form-control
       {:id "theme-title"
        :placeholder "Give your theme a title"
        :required true
        :value (or (:theme/title selected)
                   (str (get-in user [:names :display]) "'s first theme"))
        :name "title"
        :on-change #(rf/dispatch [:theme.selected/update :theme/title
                                  (oget % [:target :value])])}]
      [:label {:for "theme-title"} "Give your theme a title"]]
     [:div.row
      [:div.col-md-5
       [:strong "Logo"]
       [:p.text-muted "Coming Soon"]
       [:strong "Vorschaubild"]
       [:p.text-muted "Coming Soon"]]
      [:div.col-md-7
       [:strong "Farbeinstellungen"]
       [color-picker :theme.colors/primary "Primäre Farbe"]
       [color-picker :theme.colors/secondary "Sekundäre Farbe"]
       [color-picker :theme.colors/background "Hintergrundfarbe"]]]
     [:input {:type :hidden :name "theme-id" :value (or (:db/id selected) "")}]
     [:button.btn.btn-outline-primary {:type :submit} "Speichern"]]))

(defn theming
  "Main Theming view."
  []
  [:section
   [:div.text-center
    [:p.lead.pb-3 "Stelle hier das Erscheinungsbild deiner schnaqs ein!"]]
   [loaded-themes]
   [configure-theme]
   [:hr.my-5]
   #_[preview]])

(defn view []
  [settings/user-view
   :user.settings/themes
   [pages/settings-panel
    "Thema / Branding definieren"
    [theming]]])

;; -----------------------------------------------------------------------------

(rf/reg-fx
 :page.root/set-color
 (fn [[theme-field color]]
   (set-root-color (theme-field->css-variable theme-field) color)))

(rf/reg-event-fx
 :theme.selected/color
 (fn [{:keys [db]} [_ field color]]
   {:db (assoc-in db [:themes :selected field] color)
    :fx [[:page.root/set-color [field color]]]}))

(rf/reg-event-db
 :theme.selected/update
 (fn [db [_ field color]]
   (assoc-in db [:themes :selected field] color)))

(rf/reg-sub
 :theme/selected
 (fn [db]
   (get-in db [:themes :selected])))

(rf/reg-event-db
 :theme/select
 (fn [db [_ theme]]
   (assoc-in db [:themes :selected] theme)))

(rf/reg-event-fx
 :theme/dummy
 ;; Add dummy data to the selected schnaq, e.g. for preview functions
 (fn [{:keys [db]}]
   (let [discussion #:discussion{:author {:user.registered/display-name "schnaqqi"}
                                 :title "Welcome to schnaq"}
         conclusion #:statement{:content "Welcome to schnaq"
                                :author {:user.registered/display-name "schnaqqi"}
                                :created-at nil}]
     {:db (-> db
              (assoc-in [:schnaq :selected] discussion)
              (assoc-in [:discussion :conclusion :selected] conclusion))
      :fx [[:dispatch [:schnaq.activation/temp-counter 0]]]})))

;; -----------------------------------------------------------------------------

(defn- theme-from-form
  "TODO"
  [form]
  (let [get-field #(oget+ form [% :value])]
    {:db/id (get-field :theme-id)
     :theme/title (get-field :theme-title)
     :theme.colors/primary (get-field :color-primary)
     :theme.colors/secondary (get-field :color-secondary)
     :theme.colors/background (get-field :color-background)}))

(rf/reg-event-fx
 :theme/add
 ;; Send new theme to backend
 (fn [{:keys [db]} [_ form]]
   {:fx [(http/xhrio-request db :post "/theme/add"
                             [:theme.add/success]
                             {:theme (theme-from-form form)})]}))

(rf/reg-event-fx
 :theme/edit
 ;; Send new theme to backend
 (fn [{:keys [db]} [_ form]]
   {:fx [(http/xhrio-request db :post "/theme/edit"
                             [:theme.add/success]
                             {:theme (theme-from-form form)})]}))

(rf/reg-event-fx
 :theme.add/success
 (fn [{:keys [db]} [_ {:keys [theme]}]]
   (prn theme)
   {:fx [[:dispatch [:notification/add
                     #:notification{:title "Thema erfolgreich gespeichert"
                                    :body "Dein Thema kann nun von dir in deinen schnaqs verwendet werden 🎉"
                                    :context :success}]]]}))

;; -----------------------------------------------------------------------------

(rf/reg-event-fx
 :themes.load/personal
 (fn [{:keys [db]}]
   {:fx [(http/xhrio-request db :get "/themes/personal" [:themes.load.personal/success])]}))

(rf/reg-event-db
 :themes.load.personal/success
 ;; Load personal themes
 (fn [db [_ {:keys [themes]}]]
   (prn "Loaded themes:" themes)
   (assoc-in db [:themes :all] themes)))

(rf/reg-sub
 :themes/personal
 (fn [db]
   (get-in db [:themes :all])))