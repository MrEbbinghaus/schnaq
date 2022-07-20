(ns schnaq.interface.views.admin.control-center
  (:require [cljs.pprint :refer [pprint]]
            [cljs.spec.alpha :as s]
            [clojure.set :as set]
            [clojure.test.check.properties]
            [oops.core :refer [oget oget+]]
            [re-frame.core :as rf]
            [schnaq.database.specs :as specs]
            [schnaq.interface.translations :refer [labels]]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.utils.toolbelt :as toolbelt]
            [schnaq.interface.views.pages :as pages]
            [schnaq.shared-toolbelt :as shared-tools]))

(rf/reg-event-fx
 :admin.delete/schnaq
 (fn [{:keys [db]} [_ share-hash]]
   {:fx [(http/xhrio-request db :delete "/admin/schnaq/delete" [:no-op]
                             {:share-hash share-hash} [:ajax.error/as-notification])]}))

(rf/reg-event-fx
 :admin.delete.user/statements
 (fn [{:keys [db]} [_ keycloak-id]]
   {:fx [(http/xhrio-request db :delete "/admin/user/statements" [:no-op]
                             {:keycloak-id keycloak-id} [:ajax.error/as-notification])]}))

(rf/reg-event-fx
 :admin.delete.user/schnaqs
 (fn [{:keys [db]} [_ keycloak-id]]
   {:fx [(http/xhrio-request db :delete "/admin/user/schnaqs" [:no-op]
                             {:keycloak-id keycloak-id} [:ajax.error/as-notification])]}))

(rf/reg-event-fx
 :admin.delete.user/identity
 (fn [{:keys [db]} [_ keycloak-id]]
   {:fx [(http/xhrio-request db :delete "/admin/user/identity" [:no-op]
                             {:keycloak-id keycloak-id} [:ajax.error/as-notification])]}))

;; -----------------------------------------------------------------------------

(defn- input-form-builder
  "Build an input form for database manipulation."
  [input-id dispatch-event placeholder-text button-text confirmation-text]
  [:form.pb-3
   {:on-submit (fn [e]
                 (.preventDefault e)
                 (if confirmation-text
                   (when (js/confirm (labels confirmation-text))
                     (rf/dispatch [dispatch-event
                                   (oget+ e [:target :elements input-id :value])]))
                   (rf/dispatch [dispatch-event
                                 (oget+ e [:target :elements input-id :value])])))}
   [:div.input-group
    [:div.form-floating
     [:input.form-control
      {:id input-id
       :name input-id
       :placeholder (labels placeholder-text)
       :required true}]
     [:label {:for input-id} (labels placeholder-text)]]
    [:button.input-group-text (labels button-text)]]])

(defn- schnaq-deletion-form
  "Delete any schnaq."
  []
  [input-form-builder "share-hash-deletion"
   :admin.delete/schnaq
   :admin.center.delete.schnaq/label :admin.center.delete.schnaq/button
   :admin.center.delete/confirmation])

(defn- statements-deletion-form
  "Delete any schnaq."
  []
  [input-form-builder "statements-deletion"
   :admin.delete.user/statements
   :common/keycloak-id :admin.center.delete.user.statements/button
   :admin.center.delete/confirmation])

(defn- all-schnaqs-deletion
  "Delete all schnaqs for a given user."
  []
  [input-form-builder "schnaqs-deletion"
   :admin.delete.user/schnaqs
   :common/keycloak-id :admin.center.delete.user.schnaqs/button
   :admin.center.delete/confirmation])

(defn- delete-user-identity
  "Delete a user's identity."
  []
  [input-form-builder "user-identity-deletion"
   :admin.delete.user/identity
   :common/keycloak-id :admin.center.delete.user.identity/button
   :admin.center.delete/confirmation])

;; -----------------------------------------------------------------------------

(defn- deletion-section
  "Admin section to delete entities."
  []
  [:<>
   [:h2 (labels :admin.center.delete/heading)]
   [:section.row
    [:div.col-md-6.col-12
     [:h4 (labels :admin.center.delete.schnaq/heading)]
     [schnaq-deletion-form]]
    [:div.col-md-6.col-12
     [:h4 (labels :admin.center.delete.user/heading)]
     [statements-deletion-form]
     [all-schnaqs-deletion]
     [delete-user-identity]]]])

;; -----------------------------------------------------------------------------

(defn- load-user
  "Form to load and display user."
  []
  (let [user @(rf/subscribe [:admin/user])]
    [:div.row
     [:div.col-12.col-md-6
      [input-form-builder "user-load"
       :admin.user/load
       :common/keycloak-id :admin.center.user.load/button]]
     (when user
       [:div.col-12.col-md-6
        [:pre {:style {:max-height "200px"}}
         [:code
          (with-out-str (pprint user))]]])]))

(defn- add-role
  "Add role to user."
  []
  (let [selection-id "select-role-id"
        {:user.registered/keys [keycloak-id roles]} @(rf/subscribe [:admin/user])]
    [:form {:on-submit (fn [e]
                         (.preventDefault e)
                         (when-let [selection (oget+ e [:target :elements selection-id :value])]
                           (when (seq selection)
                             (let [role (keyword "role" selection)]
                               (rf/dispatch [:admin.user/update {:user.registered/keycloak-id keycloak-id
                                                                 :user.registered/roles role}])))))}
     [:label (labels :admin.center.user.role.add/label)
      [:div.input-group
       [:select.form-control {:id selection-id}
        [:option {:value ""} "---"]
        (for [role (set/difference specs/user-roles roles)]
          [:option {:key (str "role-selector-" role)
                    :value role} role])]
       [:button.input-group-text {:type :submit} (labels :admin.center.user.role.add/button)]]]]))

(defn- remove-role
  "Remove a user's role."
  []
  (let [selection-id "remove-role-id"
        {:user.registered/keys [keycloak-id roles]} @(rf/subscribe [:admin/user])]
    (when (seq roles)
      [:form {:on-submit (fn [e]
                           (.preventDefault e)
                           (when-let [selection (oget+ e [:target :elements selection-id :value])]
                             (when (not-empty selection)
                               (let [role (keyword "role" selection)]
                                 (rf/dispatch [:admin.user.delete/role keycloak-id role])))))}
       [:label (labels :admin.center.user.role.delete/label)
        [:div.input-group
         [:select.form-control {:id selection-id}
          [:option {:value ""} "---"]
          (for [role roles]
            [:option {:key (str "role-remove-selector-" role)
                      :value role} role])]
         [:button.input-group-text {:type :submit} (labels :admin.center.user.role.delete/button)]]]])))

(defn- input-field
  "Generate an input field based on a model's field."
  [field sample]
  (let [user-value @(rf/subscribe [:admin/user field])
        fqname (shared-tools/namespaced-keyword->string field)
        id (str "user-input-" fqname)]
    [:div.col-12.col-md-4.pt-3
     [:label.small {:for id} fqname]
     [:input.form-control {:id id
                           :name fqname
                           :placeholder user-value}]
     [:small.text-muted sample]]))

(defn- user-form
  "Generate a form to update a user."
  []
  (when-let [keycloak-id @(rf/subscribe [:admin/user :user.registered/keycloak-id])]
    [:<>
     [:div.d-flex.flex-row
      [:div.me-3 [add-role]]
      [remove-role]]
     [:form {:on-submit (fn [e]
                          (.preventDefault e)
                          (let [form (oget e [:target :elements])
                                user (toolbelt/form->map form)]
                            (rf/dispatch [:admin.user/update (assoc user :user.registered/keycloak-id keycloak-id)])
                            (rf/dispatch [:form/should-clear form])))}
      [:div.row
       [input-field :user.registered/display-name]
       [input-field :user.registered/first-name]
       [input-field :user.registered/last-name]
       [input-field :user.registered/email]
       [input-field :user.registered/profile-picture]
       [input-field :user.registered/groups]
       [input-field :user.registered.subscription/stripe-customer-id (str (s/form :user.registered.subscription/stripe-customer-id))]
       [input-field :user.registered.subscription/stripe-id (str (s/form :stripe.subscription/id))]]
      [:button.btn.btn-primary.mt-3 {:type :submit}
       (labels :admin.center.user.save/button)]]]))

(defn- user-management
  "Show user management section."
  []
  [:section.pb-5
   [:h2 (labels :admin.center.user/headline)]
   [:p.lead (labels :admin.center.user/subheadline)]
   [load-user]
   [user-form]])

(rf/reg-event-fx
 :admin.user/update
 (fn [{:keys [db]} [_ user]]
   {:fx [(http/xhrio-request db :put "/admin/user"
                             [:admin.user.load/success]
                             {:user user}
                             [:ajax.error/as-notification])]}))

(rf/reg-event-fx
 :admin.user.delete/role
 (fn [{:keys [db]} [_ keycloak-id role]]
   {:db (update-in db [:admin :user :user.registered/roles] #(disj % role))
    :fx [(http/xhrio-request db :delete "/admin/user/role"
                             [:admin.user/load keycloak-id]
                             {:keycloak-id keycloak-id
                              :role role}
                             [:ajax.error/as-notification])]}))

(rf/reg-event-fx
 :admin.user/load
 (fn [{:keys [db]} [_ keycloak-id]]
   {:fx [(http/xhrio-request db :get "/admin/user"
                             [:admin.user.load/success]
                             {:keycloak-id keycloak-id}
                             [:ajax.error/as-notification])]}))

(rf/reg-event-db
 :admin.user.load/success
 (fn [db [_ {:keys [user]}]]
   (assoc-in db [:admin :user] user)))

(rf/reg-sub
 :admin/user
 (fn [db [_ field]]
   (if field
     (get-in db [:admin :user field])
     (get-in db [:admin :user]))))

;; -----------------------------------------------------------------------------

(defn- center-overview
  "The startpage of the admin center."
  []
  [pages/with-nav-and-header
   {:condition/needs-administrator? true
    :page/heading (labels :admin.center.start/heading)
    :page/subheading (labels :admin.center.start/subheading)
    :page/vertical-header? true}
   [:div.container
    [user-management]
    [deletion-section]]])

;; -----------------------------------------------------------------------------

(defn center-overview-route
  []
  [center-overview])
