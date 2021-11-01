(ns schnaq.interface.components.motion
  (:require ["framer-motion" :refer [motion]]
            [re-frame.core :as rf]
            [reagent.core :as reagent]))

(defn zoom-image
  "Create an image, which zooms in and out on click.

  Usage: `[zoom-image {:src \"path-to-file\" :class \"additional-classes\"}]`"
  [properties]
  (let [open? (reagent/atom false)
        transition {:type "spring"
                    :damping 25
                    :stiffness 120}]
    (fn []
      [:div.image-container {:class (when @open? "open")}
       [:> (.-div motion)
        {:animate {:opacity (if @open? 1 0)}
         :transition transition
         :class "shade"
         :on-click #(reset! open? false)}]
       [:> (.-img motion)
        (merge
          {:on-click #(swap! open? not)
           :layout true
           :transition transition}
          properties)]])))

(defn fade-in-and-out
  "Add animation to component, which fades the component in and out. Takes
  optional parameter `delay`, which adds a delay to the point when the component
  should fade in."
  ([component]
   [fade-in-and-out component 0.5])
  ([component delay]
   [:> (.-div motion)
    {:initial {:opacity 0}
     :animate {:opacity 1}
     :transition {:delay delay}
     :exit {:opacity 0}}
    component]))

(defn- basic-move-in
  "A basic move-in animation. Pass any transition you like."
  [from-direction transition component]
  (let [direction (case from-direction
                    :top {:y "-200%"}
                    :bottom {:y "200%"}
                    :left {:x "-200%"}
                    {:x "200%"})]
    [:> (.-div motion)
     {:initial direction
      :animate {:x 0 :y 0}
      :exit direction
      :transition transition}
     component]))

(defn move-in
  "Add animation to component, which fades the component in and out."
  [from-direction component]
  [basic-move-in
   from-direction
   {:ease "easeOut" :duration 0.5}
   component])

(defn move-in-spring
  "The spring animation for any component"
  [from-direction component]
  [basic-move-in
   from-direction
   {:type "spring"
    :bounce 0.4
    :duration 1}
   component])

(defn pulse-once
  "Lets your component pulse a number of times.
  Pulses once if the pulse-sub subscription returns true.
  Then dispatches the pulse-stop-event. Optional pulse color can be given."
  ([component pulse-sub pulse-stop-event]
   [pulse-once component pulse-sub pulse-stop-event #"000" #"000"])
  ([component pulse-sub pulse-stop-event base-color pulse-color]
   [:> (.-div motion)
    {:variants {:pulse {:scale [1 2.5 1 1]
                        :color [base-color pulse-color base-color]
                        :transition {:delay 0.1
                                     :duration 2}}}
     :animate (if @(rf/subscribe pulse-sub) :pulse :nothing)
     :on-animation-complete #(rf/dispatch pulse-stop-event)}
    component]))
