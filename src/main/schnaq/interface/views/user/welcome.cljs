(ns schnaq.interface.views.user.welcome
  (:require [schnaq.interface.components.common :refer [next-step]]
            [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.views.pages :as pages]))

(defn- feature-themes
  "Present theme-feature."
  []
  [next-step :palette
   (labels :welcome.pro.features.themes/title)
   (labels :welcome.pro.features.themes/lead)
   (labels :welcome.pro.features.themes/button)
   :routes.user.manage/themes])

(defn- feature-polls
  "Present polls."
  []
  [next-step :chart-pie
   (labels :welcome.pro.features.polls/title)
   (labels :welcome.pro.features.polls/lead)
   (labels :welcome.pro.features.polls/button)
   :routes.schnaqs/personal])

(defn- feature-activation
  "Present activation button."
  []
  [next-step :magic
   (labels :welcome.pro.features.activation/title)
   (labels :welcome.pro.features.activation/lead)
   (labels :welcome.pro.features.activation/button)
   :routes.schnaqs/personal])

(defn- feature-profile
  "Present profile settings."
  []
  [next-step :id-card
   (labels :welcome.free.features.profile/title)
   (labels :welcome.free.features.profile/lead)
   (labels :welcome.free.features.profile/button)
   :routes.user.manage/account])

(defn- feature-notifications
  "Present notification feature."
  []
  [next-step :bell
   (labels :welcome.free.features.notifications/title)
   (labels :welcome.free.features.notifications/lead)
   (labels :welcome.free.features.notifications/button)
   :routes.user.manage/notifications])

;; -----------------------------------------------------------------------------

(defn- welcome-free-user
  "Welcome free user."
  []
  [pages/with-nav-and-header
   {:page/heading (str (labels :welcome.free/heading) " 🎉")
    :page/subheading (labels :welcome.free/subheading)
    :page/vertical-header? true
    :page/classes "base-wrapper bg-typography"
    :page/more-for-heading
    [:section.container {:style {:min-height "50vh"}}
     [:div.row.pt-5.mt-md-5
      [:div.col-md-6.col-lg-4
       [next-step :rocket
        (labels :welcome.free.features.schnaq/title)
        (labels :welcome.free.features.schnaq/lead)
        (labels :welcome.free.features.schnaq/button)
        :routes.schnaq/create]]
      [:div.col-md-6.col-lg-4 [feature-profile]]
      [:div.col-md-6.col-lg-4 [feature-notifications]]]
     [:div.row.pt-5.mt-md-5
      [:h3 "Wenn du ein Pro-User wirst, kannst du auch folgende Features nutzen"]
      [:div.row.pt-3
       [:div.col-md-6.col-lg-4 [feature-themes]]
       [:div.col-md-6.col-lg-4 [feature-polls]]
       [:div.col-md-6.col-lg-4 [feature-activation]]]]
     [:img.pt-5 {:src (img-path :schnaqqifant/rocket)}]]}])

(defn- welcome-pro-user
  "Celebrating and welcoming the new pro user."
  []
  [pages/with-nav-and-header
   {:page/heading (str (labels :welcome.pro/heading) " 🎉")
    :page/subheading (labels :welcome.pro/subheading)
    :page/vertical-header? true
    :page/classes "base-wrapper bg-typography"
    :page/more-for-heading
    [:section.container {:style {:min-height "50vh"}}
     [:div.row.pt-5.mt-md-5
      [:div.col-md-6.col-lg-4
       [next-step :rocket
        (labels :welcome.pro.features.schnaq/title)
        (labels :welcome.pro.features.schnaq/lead)
        (labels :welcome.pro.features.schnaq/button)
        :routes.schnaqs/personal]]
      [:div.col-md-6.col-lg-4 [feature-themes]]
      [:div.col-md-6.col-lg-4 [feature-polls]]
      [:div.col-md-6.col-lg-4 [feature-activation]]
      [:div.col-md-6.col-lg-4
       [next-step :sliders-h
        (labels :welcome.pro.features.subscription/title)
        (labels :welcome.pro.features.subscription/lead)
        (labels :welcome.pro.features.subscription/button)
        :routes.user.manage/account]]]
     [:img.pt-5 {:src (img-path :schnaqqifant/rocket)}]]}])

;; -----------------------------------------------------------------------------

(defn welcome-free-user-view []
  [welcome-free-user])

(defn welcome-pro-user-view []
  [welcome-pro-user])
