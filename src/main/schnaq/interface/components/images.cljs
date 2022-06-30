(ns schnaq.interface.components.images)

(defn img-path
  "Returns an image path as String for a given identifier"
  [identifier]
  (identifier
   {:how-to/taskbar "https://s3.schnaq.com/schnaq-common/howto/taskbar.svg"
    :icon-add "https://s3.schnaq.com/schnaq-common/buttons/add-button.svg"
    :icon-cards-dark "https://s3.schnaq.com/schnaq-common/icons/squares_dark.svg"
    :icon-community "https://s3.schnaq.com/schnaq-common/community.svg"
    :icon-crane "https://s3.schnaq.com/schnaq-common/icons/crane.svg"
    :icon-graph "https://s3.schnaq.com/schnaq-common/icons/mind_map_circle.svg"
    :icon-graph-dark "https://s3.schnaq.com/schnaq-common/icons/mind_map_circle_dark.svg"
    :icon-posts "https://s3.schnaq.com/schnaq-common/icons/dashboard/posts.svg"
    :icon-qanda-dark "https://s3.schnaq.com/schnaq-common/icons/qanda_dark.svg"
    :icon-reports "https://s3.schnaq.com/schnaq-common/icons/reports.svg"
    :icon-robot "https://s3.schnaq.com/schnaq-common/icons/robot.svg"
    :icon-search "https://s3.schnaq.com/schnaq-common/icons/dashboard/search.svg"
    :icon-summary "https://s3.schnaq.com/schnaq-common/icons/layers.svg"
    :icon-summary-dark "https://s3.schnaq.com/schnaq-common/icons/layers_dark.svg"
    :icon-users "https://s3.schnaq.com/schnaq-common/icons/dashboard/users.svg"
    :icon-views-dark "https://s3.schnaq.com/schnaq-common/icons/views_dark.svg"
    :icon-views-light "https://s3.schnaq.com/schnaq-common/icons/views_light.svg"
    :logo "https://s3.schnaq.com/schnaq-common/logos/schnaq.svg"
    :logo-white "https://s3.schnaq.com/schnaq-common/logos/schnaq_white.webp"
    :logo.square.schnaqqi/blue "https://s3.schnaq.com/schnaq-common/logos/schnaqqi-qr.png"
    :logos/hetzner "https://s3.schnaq.com/schnaq-common/logos/logo-hetzner.svg"
    :preview/summary "https://s3.schnaq.com/schnaq-common/blurred/blurred_summary.png"
    :preview/wordcloud "https://s3.schnaq.com/schnaq-common/blurred/blurred_wordcloud.png"
    :schnaqqifant/admin "https://s3.schnaq.com/schnaq-schnaqqifanten/admin.png"
    :schnaqqifant/erase "https://s3.schnaq.com/schnaq-schnaqqifanten/erase.png"
    :schnaqqifant/share "https://s3.schnaq.com/schnaq-schnaqqifanten/share.png"
    :schnaqqifant/stop "https://s3.schnaq.com/schnaq-schnaqqifanten/stop.png"
    :schnaqqifant/talk "https://s3.schnaq.com/schnaq-schnaqqifanten/talk.png"
    :schnaqqifant/rocket "https://s3.schnaq.com/schnaq-schnaqqifanten/rocket.webp"
    :schnaqqifant/three-d-bubble "https://s3.schnaq.com/schnaq-schnaqqifanten/schnaqqi_bubble.png"
    :schnaqqifant/three-d-head "https://s3.schnaq.com/schnaq-schnaqqifanten/schnaqqi-3d-head.png"
    :schnaqqifant/three-d-left "https://s3.schnaq.com/schnaq-schnaqqifanten/schnaqqi-3d-head-left.png"
    :schnaqqifant/white "https://s3.schnaq.com/schnaq-common/logos/schnaqqifant_white.webp"}))
