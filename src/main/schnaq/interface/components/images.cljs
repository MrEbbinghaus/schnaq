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
    :logos/bialon "https://s3.schnaq.com/schnaq-common/testimonials/bialon_logo.webp"
    :logos/digihub "https://s3.schnaq.com/schnaq-common/logos/digihub_logo.webp"
    :logos/doctronic "https://s3.schnaq.com/schnaq-common/testimonials/doctronic_logo.webp"
    :logos/franky "https://s3.schnaq.com/schnaq-common/testimonials/foxbase_logo.svg"
    :logos/frauke "https://s3.schnaq.com/schnaq-common/testimonials/library_logo.webp"
    :logos/hck "https://s3.schnaq.com/schnaq-common/testimonials/hck.webp"
    :logos/hetzner "https://s3.schnaq.com/schnaq-common/logos/logo-hetzner.svg"
    :logos/hhu "https://s3.schnaq.com/schnaq-common/testimonials/hhu_logo.webp"
    :logos/muetze "https://s3.schnaq.com/schnaq-common/logos/Muetze-nrw.webp"
    :logos/ignition "https://s3.schnaq.com/schnaq-common/logos/ignition_logo.webp"
    :logos/leetdesk "https://s3.schnaq.com/schnaq-common/testimonials/leetdesk_logo.webp"
    :logos/lokay "https://s3.schnaq.com/schnaq-common/testimonials/lokay_logo.webp"
    :logos/metro "https://s3.schnaq.com/schnaq-common/testimonials/metro_logo.svg"
    :logos/sensor "https://s3.schnaq.com/schnaq-common/testimonials/sensor.webp"
    :preview/summary "https://s3.schnaq.com/schnaq-common/blurred/blurred_summary.png"
    :preview/wordcloud "https://s3.schnaq.com/schnaq-common/blurred/blurred_wordcloud.png"
    :pricing.others/confluence "https://s3.schnaq.com/schnaq-common/startpage/pricing/confluence.jpeg"
    :pricing.others/loomio "https://s3.schnaq.com/schnaq-common/startpage/pricing/loomio.png"
    :pricing.others/miro "https://s3.schnaq.com/schnaq-common/startpage/pricing/miro.png"
    :productpage.qa/relevant "https://s3.schnaq.com/schnaq-common/product-page/qa-relevant.jpg"
    :productpage.poll/multiple "https://s3.schnaq.com/schnaq-common/product-page/poll-multiple.jpg"
    :schnaqqifant/admin "https://s3.schnaq.com/schnaq-schnaqqifanten/admin.png"
    :schnaqqifant/erase "https://s3.schnaq.com/schnaq-schnaqqifanten/erase.png"
    :schnaqqifant/flat "https://s3.schnaq.com/schnaq-schnaqqifanten/schnaqqi_flat_front.png"
    :schnaqqifant/lamp "https://s3.schnaq.com/schnaq-schnaqqifanten/lamp.webp"
    :schnaqqifant/mail "https://s3.schnaq.com/schnaq-schnaqqifanten/newsletter.webp"
    :schnaqqifant/original "https://s3.schnaq.com/schnaq-common/logos/schnaqqifant.svg"
    :schnaqqifant/share "https://s3.schnaq.com/schnaq-schnaqqifanten/share.png"
    :schnaqqifant/stop "https://s3.schnaq.com/schnaq-schnaqqifanten/stop.png"
    :schnaqqifant/talk "https://s3.schnaq.com/schnaq-schnaqqifanten/talk.png"
    :schnaqqifant/rocket "https://s3.schnaq.com/schnaq-schnaqqifanten/rocket.webp"
    :schnaqqifant/show "https://s3.schnaq.com/schnaq-schnaqqifanten/show.webp"
    :schnaqqifant/three-d-bubble "https://s3.schnaq.com/schnaq-schnaqqifanten/schnaqqi_bubble.png"
    :schnaqqifant/three-d-head "https://s3.schnaq.com/schnaq-schnaqqifanten/schnaqqi-3d-head.png"
    :schnaqqifant/three-d-left "https://s3.schnaq.com/schnaq-schnaqqifanten/schnaqqi-3d-head-left.png"
    :schnaqqifant/white "https://s3.schnaq.com/schnaq-common/logos/schnaqqifant_white.webp"
    :startpage.alternatives.e-learning/alex "https://s3.schnaq.com/startpage/alex.jpeg"
    :startpage.alternatives.e-learning/christian "https://s3.schnaq.com/startpage/christian.jpg"
    :startpage.alternatives.e-learning/david "https://s3.schnaq.com/startpage/david.jpeg"
    :startpage.alternatives.e-learning/mike "https://s3.schnaq.com/startpage/mike.jpg"
    :startpage.example/dashboard "https://s3.schnaq.com/schnaq-common/startpage/screenshots/example_dashboard.webp"
    :startpage.example/statements "https://s3.schnaq.com/schnaq-common/startpage/screenshots/all_statements.png"
    :startpage.features/admin-center "https://s3.schnaq.com/startpage/features/admin-center.png"
    :startpage.schnaqqifant/knowledge-card "https://s3.schnaq.com/startpage/knowledge-card.webp"
    :startpage.schnaqqifant/create-schnaq "https://s3.schnaq.com/startpage/create-schnaq.webp"
    :startpage.schnaqqifant/share-schnaq "https://s3.schnaq.com/startpage/share-schnaq.webp"
    :startpage.information/anywhere "https://s3.schnaq.com/startpage/startpage_mobile_work.webp"
    :startpage.information/meeting "https://s3.schnaq.com/startpage/startpage-meeting.jpeg"
    :startpage.screenshots/qanda "https://s3.schnaq.com/startpage/screenshots/qanda.png"
    :startpage.trust/germany-100 "https://s3.schnaq.com/startpage/100PercentGermany.png"
    :startpage.trust/germany-100-white "https://s3.schnaq.com/startpage/100PercentGermanyWhite.png"
    :startpage/team-schnaq "https://s3.schnaq.com/startpage/schnaq-team.webp"
    :stock/team "https://s3.schnaq.com/startpage/team.jpeg"
    :team/alexander "https://s3.schnaq.com/team/alexanderschneider.jpg"
    :team/at-table-with-laptop "https://s3.schnaq.com/team/team_hinter_laptop_am_tisch.jpg"
    :team/christian "https://s3.schnaq.com/team/christianmeter.jpg"
    :team/mike "https://s3.schnaq.com/team/michaelbirkhoff.jpg"
    :team/vision-mindmap-team "https://s3.schnaq.com/team/vision_mindmap_team.jpg"
    :testimonial-picture/bjorn "https://s3.schnaq.com/schnaq-common/testimonials/bjorn_picture.webp"
    :testimonial-picture/eugen-bialon "https://s3.schnaq.com/schnaq-common/testimonials/eugen_bialon_picture.webp"
    :testimonial-picture/florian-clever "https://s3.schnaq.com/schnaq-common/testimonials/florian_picture.webp"
    :testimonial-picture/frank-stampa "https://s3.schnaq.com/schnaq-common/testimonials/frank_stampa_picture.webp"
    :testimonial-picture/frauke-kling "https://s3.schnaq.com/schnaq-common/testimonials/frauke_picture.webp"
    :testimonial-picture/hck "https://s3.schnaq.com/schnaq-common/testimonials/hck_picture.webp"
    :testimonial-picture/ingo-kupers "https://s3.schnaq.com/schnaq-common/testimonials/ingo_kupers_picture.webp"
    :testimonial-picture/lokay "https://s3.schnaq.com/schnaq-common/testimonials/lokay_picture.webp"
    :testimonial-picture/meiko-tse "https://s3.schnaq.com/schnaq-common/testimonials/meiko_picture.webp"
    :testimonial-picture/raphael-bialon "https://s3.schnaq.com/schnaq-common/testimonials/bialon_picture.webp"
    :testimonial-picture/tobias-schroeder "https://s3.schnaq.com/schnaq-common/testimonials/tobias_picture.webp"
    :value/book "https://s3.schnaq.com/schnaq-common/icons/value/book.svg"
    :value/bubble "https://s3.schnaq.com/schnaq-common/icons/value/bubble.svg"
    :value/cards "https://s3.schnaq.com/schnaq-common/icons/value/cards.svg"
    :value/share "https://s3.schnaq.com/schnaq-common/icons/value/share.svg"
    :value/shield "https://s3.schnaq.com/schnaq-common/icons/value/shield.svg"
    :value/private "https://s3.schnaq.com/schnaq-common/icons/value/eye.svg"}))
