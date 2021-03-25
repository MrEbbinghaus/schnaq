(ns schnaq.interface.views.user.edit-account
  (:require [re-frame.core :as rf]
            [schnaq.interface.text.display-data :refer [labels]]
            [schnaq.interface.views.user.elements :as elements]
            [schnaq.interface.views.pages :as pages]))

(defn- change-user-info []
  [:div "Edit account"])

(defn- content []
  (let [user @(rf/subscribe [:user/data])]
    [pages/with-nav
     {:page/heading (labels :user/edit-account)}
     [elements/user-view-desktop user
      [change-user-info]]]))

(defn view []
  [content])

;; subs ;;

(rf/reg-sub
  :user/data
  (fn [db]
    (get-in db [:user])))