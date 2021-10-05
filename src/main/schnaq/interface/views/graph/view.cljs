(ns schnaq.interface.views.graph.view
  (:require ["vis-network/standalone/esm/vis-network" :refer [DataSet Network]]
            [clojure.set :as set]
            [ghostwheel.core :refer [>defn-]]
            [goog.string :as gstring]
            [re-frame.core :as rf]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [reitit.frontend.easy :as rfe]
            [schnaq.interface.components.colors :refer [colors]]
            [schnaq.interface.components.icons :refer [fa]]
            [schnaq.interface.config :as conf]
            [schnaq.interface.utils.http :as http]
            [schnaq.interface.views.common :as common]
            [schnaq.interface.views.graph.settings :as graph-settings]
            [schnaq.interface.views.loading :as loading]
            [schnaq.interface.views.pages :as pages]
            [schnaq.interface.views.schnaq.admin :as admin]))

(def ^:private graph-id "graph")

(>defn- node-types->colors
  "Add colors depending on node type."
  [nodes]
  [:graph/nodes :ret :graph/nodes]
  (map
    #(let [color (case (:type %)
                   :statement.type/starting (colors :neutral/dark)
                   :statement.type/support (colors :positive/default)
                   :statement.type/attack (colors :negative/default)
                   :statement.type/neutral (colors :neutral/medium)
                   :agenda (colors :white)
                   (colors :positive/default))
           border-color (case (:type %)
                          :agenda (colors :neutral/medium)
                          color)]
       (assoc % :color {:background color
                        :highlight {:background color}
                        :hover {:background color}
                        :border border-color}))
    nodes))

(>defn- mark-controversy
  "Marks controversy in nodes."
  [controversy-map nodes]
  [:graph/controversy-values :graph/nodes :ret sequential?]
  (map
    #(let [controversy-score (get controversy-map (:id %))]
       (if (< (- 100 conf/graph-controversy-upper-bound)
              controversy-score
              conf/graph-controversy-upper-bound)
         (-> %
             (assoc-in [:color :border] "#fab907")
             (assoc-in [:color :highlight :border] "#fab907")
             (assoc-in [:color :hover :border] "#fab907"))
         %))
    nodes))

(>defn- node-content-color
  [nodes]
  [:graph/nodes :ret :graph/nodes]
  (map
    #(let [text-color (case (:type %)
                        :agenda (colors :neutral/dark)
                        (colors :white))]
       (merge % {:shape "box"
                 :shapeProperties {:borderRadius 12}
                 :widthConstraint {:minimum 50
                                   :maximum 200}
                 :font {:align "left" :color text-color}
                 :margin 10}))
    nodes))

(>defn- convert-nodes-for-vis
  "Converts the nodes received from backend specifically for viz."
  [nodes controversy-values]
  [:graph/nodes :graph/controversy-values :ret :graph/nodes]
  (->> nodes
       node-types->colors
       (mark-controversy controversy-values)
       node-content-color))

(defn- graph-canvas
  "Visualization of Discussion Graph."
  [{:keys [nodes edges controversy-values]}]
  (let [nodes-vis (DataSet.)
        edges-vis (DataSet.)
        nodes-store (reagent/atom nodes)
        edges-store (reagent/atom edges)
        height (* 0.75 (.-innerHeight js/window))
        route-params (get-in @(rf/subscribe [:navigation/current-route]) [:parameters :path])
        share-hash (:discussion/share-hash @(rf/subscribe [:schnaq/selected]))
        gravity @(rf/subscribe [:graph.settings/gravity])
        options {:height (str height)
                 :layout {:randomSeed :constant}
                 :physics {:barnesHut {:avoidOverlap gravity}}}]
    (reagent/create-class
      {:display-name "Visualization of Discussion Graph"
       :reagent-render
       (fn [_graph]
         (let [^js graph-object @(rf/subscribe [:graph/get-object])
               gravity @(rf/subscribe [:graph.settings/gravity])]
           (when graph-object
             (.setOptions graph-object
                          (clj->js (assoc-in options [:physics :barnesHut :avoidOverlap]
                                             gravity))))
           [:div {:id graph-id}]))
       :component-did-mount
       (fn [this]
         (.add nodes-vis (clj->js (convert-nodes-for-vis nodes controversy-values)))
         (.add edges-vis (clj->js edges))
         (let [root-node (rdom/dom-node this)
               data #js {:nodes nodes-vis
                         :edges edges-vis}
               graph (Network. root-node data (clj->js options))]
           (rf/dispatch [:graph/store-object graph])
           (.on graph "doubleClick"
                (fn [properties]
                  (let [node-id (first (get (js->clj properties) "nodes"))]
                    (if (= node-id share-hash)              ;; if true, the user clicked on the discussion title
                      (rf/dispatch [:navigation/navigate :routes.schnaq/start {:share-hash share-hash}])
                      (rf/dispatch [:navigation/navigate :routes.schnaq.select/statement
                                    (assoc route-params :statement-id node-id)])))))))
       :component-did-update
       (fn [this _argv]
         (let [[_ {:keys [nodes edges controversy-values]}] (reagent/argv this)
               new-nodes (set/difference (set nodes) (set @nodes-store))
               new-edges (set/difference (set edges) (set @edges-store))]
           (.add nodes-vis (clj->js (convert-nodes-for-vis new-nodes controversy-values)))
           (.add edges-vis (clj->js new-edges))
           (reset! nodes-store nodes)
           (reset! edges-store edges)))
       :component-will-unmount
       (fn [_this] (rf/dispatch [:graph/set-current nil]))})))

(defn graph-agenda-header
  "Header when displaying the graph."
  [title share-hash]
  (common/set-website-title! title)
  [:section.container-fluid.bg-white.p-4.shadow-sm
   [:div.row
    [:div.col-2.col-md-1
     [:a.link-unstyled {:href (rfe/href :routes.schnaq/start {:share-hash share-hash})}
      [:i.arrow-icon {:class (str "m-auto fas " (fa :arrow-left))}]]]
    [:div.col-10.col-md-7 [:h2 title]]
    [:div.col-12.col-md-4.text-md-right
     [graph-settings/open-settings]
     [admin/graph-download-as-png (gstring/format "#%s" graph-id)]
     [admin/txt-export share-hash title]]]])

(defn- graph-view
  "The core Graph visualization wrapper."
  []
  (let [current-discussion @(rf/subscribe [:schnaq/selected])]
    [pages/with-discussion-header
     {:page/heading (:discussion/title current-discussion)}
     [:<>
      (when-let [graph (:graph @(rf/subscribe [:graph/current]))]
        [graph-canvas graph])
      [loading/spinner]]]))

(defn graph-view-entrypoint []
  [graph-view])


;; -----------------------------------------------------------------------------

(rf/reg-event-fx
  :graph/load-data-for-discussion
  (fn [{:keys [db]} _]
    (let [share-hash (get-in db [:schnaq :selected :discussion/share-hash])]
      {:fx [(http/xhrio-request db :get "/discussion/graph" [:graph/set-current] {:share-hash share-hash})]})))

(rf/reg-event-fx
  :graph/set-current
  (fn [{:keys [db]} [_ graph-data]]
    {:fx [[:dispatch [:spinner/active! false]]]
     :db (assoc-in db [:graph :current] graph-data)}))

(rf/reg-sub
  :graph/current
  (fn [db _]
    (get-in db [:graph :current])))

(rf/reg-event-db
  :graph/store-object
  (fn [db [_ graph-js]]
    (assoc-in db [:graph :object] graph-js)))

(rf/reg-sub
  :graph/get-object
  (fn [db _]
    (get-in db [:graph :object])))

(rf/reg-event-db
  :graph.settings/gravity!
  (fn [db [_ value]]
    (assoc-in db [:graph :settings :gravity] value)))

(rf/reg-sub
  :graph.settings/gravity
  (fn [db _]
    (let [default-gravity 0.1]
      (get-in db [:graph :settings :gravity] default-gravity)))) ;
