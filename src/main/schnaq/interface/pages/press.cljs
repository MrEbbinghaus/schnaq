(ns schnaq.interface.pages.press
  (:require [schnaq.interface.text.display-data :refer [img-path labels]]
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
    [card :press-kit.materials/fact-sheet :press-kit/fact-sheet "https://drive.google.com/file/d/1ISIjpex7l3bPSku05LKy-6AxbiAMSRwF/view"]
    [card :press-kit.materials/logos :press-kit/logo "https://drive.google.com/drive/folders/1uO_DCumcqPhfk0rvZ7gYKRUf07uS28yx"]
    [card :press-kit.materials/team :press-kit/team "https://drive.google.com/drive/folders/1zcSi05Q6O5bHRH-Ph2vCikmvOKapHDwQ"]
    [card :press-kit.materials/product :press-kit/product "https://drive.google.com/drive/folders/1m0vrJLstrHhJCOBlU_Ta86fm6X2hLPwr"]]])

(defn- not-to-do-list []
  (let [fa-icon [:span.fa-li.text-danger [:i.fas.fa-times-circle]]]
    [:<>
     [:h5.pt-3 (labels :press-kit.not-to-do/heading)]
     [:ul.fa-ul.press-dont-list
      [:li fa-icon (labels :press-kit.not-to-do/bullet-1)]
      [:li fa-icon (labels :press-kit.not-to-do/bullet-2)]
      [:li fa-icon (labels :press-kit.not-to-do/bullet-3)]
      [:li fa-icon (labels :press-kit.not-to-do/bullet-4)]]]))

(defn- content []
  [pages/with-nav-and-header
   {:page/heading (labels :press-kit/heading)
    :page/subheading (labels :press-kit/subheading)}
   [:div.container.chat-background
    [:section.w-75.mx-auto
     [:h2 (labels :press-kit.intro/heading)]
     [:p.lead (labels :press-kit.intro/lead)]

     [:h4.pt-3 (labels :press-kit.spelling/heading)]
     [:p (labels :press-kit.spelling/content-1) [:strong " schnaq "] (labels :press-kit.spelling/content-2)]

     [not-to-do-list]]

    [materials]]])


(defn view []
  [content])