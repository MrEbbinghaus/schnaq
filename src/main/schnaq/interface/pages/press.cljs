(ns schnaq.interface.pages.press
  (:require [reitit.frontend.easy :as reitfe]
            [schnaq.interface.components.icons :refer [icon]]
            [schnaq.interface.components.images :refer [img-path]]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.views.pages :as pages]))

(defn- card [title photo-keyword link-url]
  (let [anchor {:href link-url :target :_blank}
        title-string (labels title)]
    [:article.card.shadow
     [:div.card-body.text-center.d-flex.flex-column
      [:h5.card-title title-string]
      [:a.my-auto anchor
       [:img.img-fluid.p-lg-4 {:src (img-path photo-keyword) :alt title-string}]]
      [:a.btn.btn-primary.mt-2 anchor (labels :press-kit.materials/download)]]]))

(defn- materials []
  [:section.py-5
   [:h3.text-center.pb-4 (labels :press-kit.materials/heading)]
   [:div.card-deck
    [card :press-kit.materials/fact-sheet :press-kit/fact-sheet "https://s3.disqtec.com/schnaq-presskit/fact-sheet-schnaq.pdf"]
    [card :press-kit.materials/logos :press-kit/logo "https://s3.disqtec.com/schnaq-presskit/logos-schnaq.zip"]
    [card :press-kit.materials/team :press-kit/team "https://s3.disqtec.com/schnaq-presskit/images-schnaq.zip"]
    [card :press-kit.materials/product :press-kit/product "https://s3.disqtec.com/schnaq-presskit/screenshots-schnaq.zip"]]])

(defn- not-to-do-list []
  (let [circle-icon [icon :times-circle "text-danger mt-3" {:listItem true}]]
    [:<>
     [:h5.pt-4 (labels :press-kit.not-to-do/heading)]
     [:ul.fa-ul
      [:li circle-icon (labels :press-kit.not-to-do/bullet-1)]
      [:li circle-icon (labels :press-kit.not-to-do/bullet-2)]
      [:li circle-icon (labels :press-kit.not-to-do/bullet-3)]
      [:li circle-icon (labels :press-kit.not-to-do/bullet-4)]]]))

(defn- more-information []
  [:<>
   [:h4.pt-3 (labels :press-kit.about-us/heading)]
   [:p (labels :press-kit.about-us/body)]
   [:a.btn.btn-primary {:href (reitfe/href :routes/about-us)}
    (labels :footer.buttons/about-us)]
   [:a.btn.btn-primary.ml-3 {:href (reitfe/href :routes/publications)}
    (labels :footer.buttons/publications)]])


;; -----------------------------------------------------------------------------

(defn- content []
  [pages/with-nav-and-header
   {:page/heading (labels :press-kit/heading)
    :page/subheading (labels :press-kit/subheading)
    :page/vertical-header? true}
   [:div.container.chat-background
    [:section.w-75.mx-auto
     [:h2 (labels :press-kit.intro/heading)]
     [:p.lead (labels :press-kit.intro/lead)]

     [:h4.pt-3 (labels :press-kit.spelling/heading)]
     [:p (labels :press-kit.spelling/content-1) [:strong " schnaq "] (labels :press-kit.spelling/content-2)]

     [more-information]
     [not-to-do-list]]
    [materials]]])

(defn view []
  [content])
