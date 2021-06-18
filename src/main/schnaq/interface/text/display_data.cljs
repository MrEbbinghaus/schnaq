(ns schnaq.interface.text.display-data
  "Texts used as labels in the whole application."
  (:require [schnaq.interface.config :refer [user-language marketing-num-schnaqs marketing-num-statements]]
            [schnaq.interface.utils.js-wrapper :as js-wrap]
            [schnaq.interface.utils.toolbelt :as toolbelt]
            [taoensso.tempura :refer [tr]]))

(def ^:private translations
  {:en {;; Common
        :error/export-failed "Export failed. Please try again later."

        ;; navbar labels
        :nav/startpage "Home"
        :nav/schnaqs "schnaqs"
        :nav.schnaqs/show-all "All schnaqs"
        :nav.schnaqs/show-all-public "All public schnaqs"
        :nav.schnaqs/create-schnaq "Create schnaq"
        :nav.schnaqs/last-added "Last created schnaq"
        :nav/blog "Blog"
        :nav/admin "Admin"

        ;; Call to contribute
        :call-to-contribute/lead "There are currently no contributions"
        :call-to-contribute/body "You might be the first person to write something about this!"

        ;; code of conduct
        :coc/heading "Code of Conduct"
        :coc/subheading "Do onto others as you would have them do unto you"

        :coc.users/lead "Behaviour towards other users"
        :coc.users/title "Respect and Non-Discrimination"
        :coc.users/body "A respectful behaviour is important and is the basis of each factual discussion. This applies not only offline but also online.\nIt is important to us that all users can express themselves without being discriminated against based on their person, origin or views. \nPosts that do not adhere to these guidelines will be deleted."

        :coc.content/lead "Content"
        :coc.content/title "We obey the law, please do that too"
        :coc.content/body "We comply with German law; this applies especially to data protection, equality and non-discrimination.\nContent that violates applicable law will be deleted."

        ;; how-to
        :how-to/button "How to schnaq?"
        :how-to/title "How to schnaq?"
        :how-to.create/title "What would you like to schnaq about with your team??"
        :how-to.create/body
        [:<>
         [:p [:i "\"Which systems do we use and when?\", \"What price do we offer educational institutions?\", \"What is our vision?\""]]
         [:p "Give your schnaq a meaningful title and let the whole team discuss. All contributions are important and offer an individual perspective on the discussion."]]
        :how-to.why/title "Why should I schnaq?"
        :how-to.why/body "Fluffed your lines? Didn't get a chance to speak? Did everyone get everything? No clue why the discussion took so long?\nSave your time and store your knowledge in schnaq. Participate whenever you like. The mindmap of the discussion is on the house."
        :how-to.admin/title "Administer your schnaq or invite someone to help you"
        :how-to.admin/body "Invite participants via link or mail. If you need any help as admin just invite someone to lend you a hand."
        :how-to.schnaq/title "How do I schnaq?"
        :how-to.schnaq/body "Share your opinion! Others can react to your post or add a comment. The mindmap is automatically generated and updated whenever there is a new statement. Fancy a post? Just click on it and jump right at it."
        :how-to.pro-con/title "To Agree or Disagree ..."
        :how-to.pro-con/body "Share your basic attitude towards the current post. With a click on our agree/disagree-button you change your post's attitude. Pro statements are highlighted blue and contra statements are highlighted orange. You can add multiple pro statements as well as contra statements in response to the same statement."
        :how-to.call-to-action/title "Now you know!"
        :how-to.call-to-action/body "That's actually all there is to know about schnaq. Now use it!"
        :how-to/ask-question "Not sure how to schnaq? "     ;; whitespace intended
        :how-to/ask-question-2 "Still not sure how to use schnaq? " ;; whitespace intended
        :how-to/answer-question "Let us show you how!"
        :how-to/question-dont-show-again "Got it? "         ;; whitespace intended
        :how-to/answer-dont-show-again "Don't show this tip anymore!"

        ;; Startpage
        :startpage/heading "Better Decisions Together"
        :startpage/subheading "Software, that helps remote-teams structure their shared knowledge. Democratizing discussions – because everybody has valuable insights."
        :startpage.social-proof/numbers [:span "schnaq helped lead over " [:b marketing-num-schnaqs]
                                         " discussions which amounts to " [:b marketing-num-statements] " instances of shared knowledge."]

        :startpage.usage/lead "What do I use schnaq for?"

        :startpage.features/more-information "More information"

        :startpage.features.meeting-organisation/lead "Well informed at any time"
        :startpage.features.meeting-organisation/title "Make the Optimal Decisions"
        :startpage.features.meeting-organisation/body
        [:<>
         [:p "It's hard to make a decision where anybody can contribute their knowledge by using chat-tools and E-Mail. Clarity suffers in turn."]
         [:p "With schnaq discussions, anybody can participate and be heard equally, no matter the time and place they're in. This way all relevant insights and viewpoints can be incorporated in the decisions you take."]]

        :startpage.features.discussion/lead "Onboarding was never easier"
        :startpage.features.discussion/title "Easy Team Q&A Function"
        :startpage.features.discussion/body
        [:<>
         [:p "Any person on your team has some special knowledge only they posses. And nobody got time to create all those long wiki pages."]
         [:p "schnaq collects the knowledge where it is shared naturally. When somebody asks a question through schnaq, any colleague can pitch in and answer."]
         [:p "Once a question is answered, it is available to be viewed and improved upon by others in the team. Collaboration that feels effortless."]]

        :startpage.features.graph/lead "Easy integration"
        :startpage.features.graph/title "Personal Spaces for Your Teams"
        :startpage.features.graph/body
        [:<>
         [:p "Any team can work in their own private hub. Only the people you add to your hub, can collaborate with you."]
         [:p "Schnaq also lets you use logins, that you may already have in your company. This way you do not need to create any extra accounts. Do you need any logins, we do not support yet? " [:a {:href "mailto:info@schnaq.com"} "Contact us!"]]
         [:p.text-center.mb-0
          [:a.btn.btn-primary {:role "button"
                               :href "mailto:info@schnaq.com"}
           "Contact us if you want to test the Hub feature"]]]

        :startpage.early-adopter/title "Gotten Curious?"
        :startpage.early-adopter/body "Be one of the first to use schnaq hubs"
        :startpage.early-adopter/or "or"
        :startpage.early-adopter/test "Test new schnaq features"

        :startpage.newsletter/heading "Be the first to know when you can profit from new features!"
        :startpage.newsletter/button "Give me exclusive previews!"
        :startpage.newsletter/address-placeholder "E-Mail Address"
        :startpage.newsletter/consent "I want to subscribe to the schnaq newsletter, and regularly receive information about schnaq.com."
        :startpage.newsletter/more-info-clicker "Data Processing"
        :startpage.newsletter/policy-disclaimer "Schnaq saves, processes and saves the personal information you enter above to
        subscribe you to the newsletter. You can unsubscribe at any time, by clicking the appropriate link in the emails you will receive.
        Alternatively you can write us an email, and we will unsubscribe you personally."
        :startpage.newsletter/privacy-policy-lead "More information about the handling of your personal data can be found in our "

        :startpage.faq/title "Frequently Asked Questions"
        :startpage.faq.data/question "What happens with my data?"
        :startpage.faq.data/answer-1 "We only store data on german servers, to provide the best possible data protection. All details are explained
        in an understandable manner in our "
        :startpage.faq.data/link-name "privacy policy"
        :startpage.faq.data/answer-2 "."
        :startpage.faq.integration/question "Can I integrate schnaq with the software I'm already using?"
        :startpage.faq.integration/answer "We currently work on integrations for Slack, MS Teams and other popular communication software.
        If you want to be the first to know, when we launch the feature, subscribe to our "
        :startpage.faq.integration/link-name "newsletter."
        :startpage.faq.costs/question "Are there any hidden costs?"
        :startpage.faq.costs/answer "schnaq is currently in a test-phase and completely free. No hidden payments.
        Although we are always happy about your honest feedback as a form of payment."
        :startpage.faq.start/question "How can I start using schnaq?"
        :startpage.faq.start/answer "You can either use schnaq anonymously, or register to have the possibility to see and administrate your schnaqs
        and statements from anywhere. Give it a try and "
        :startpage.faq.start/link-name "create a schnaq."
        :startpage.faq.why/question "Why should I use schnaq?"
        :startpage.faq.why/answer "schnaq is for you, if you support a modern, open and equal work-culture.
        Our goal is to make communication and knowledge-sharing at the workplace as flexible and easy as possible.
        This way we heighten the potential of every person in the company, and thus of the company itself."

        :startpage.founders-note/title "A Note from the Founders"

        ;; Login page
        :page.login/heading "Please Login"
        :page.login/subheading "You need to login to access this resource"

        :page.beta/heading "Beta-Feature"
        :page.beta/subheading "This feature is currently only enabled for beta-testers. Please log in if you are one."

        :footer.buttons/about-us "About us"
        :footer.buttons/legal-note "Legal Note"
        :footer.buttons/privacy "Privacy Notice"
        :footer.buttons/press-kit "Press Kit"
        :footer.buttons/publications "Publications"
        :footer.tagline/developed-with " Developed with "
        :footer.sponsors/heading "Our servers are hosted by"

        ;; Create schnaqs
        :schnaqs/create "Create schnaq"

        ;; Header image
        :schnaq.header-image.url/placeholder "Image url"
        :schnaq.header-image.url/button "Add as preview image"
        :schnaq.header-image.url/note "Images are restricted to content from pixabay.com"
        :schnaq.header-image.url/label "Add a preview image to your schnaq header"
        :schnaq.header-image.url/successful-set "Preview image successfully set"
        :schnaq.header-image.url/successful-set-body "The image will be featured in this schnaq's header."
        :schnaq.header-image.url/failed-setting-title "Error when adding image"
        :schnaq.header-image.url/failed-setting-body "The image will not be used as preview image."

        ;; Create schnaq
        :schnaq.create.input/placeholder "What would you like to discuss with your team?"
        :schnaq.create.public/help-text "Publicly lists your schnaq."
        :schnaq.create.hub/help-text "Directly assign your schnaq to a hub."
        :schnaq/copy-link-tooltip "Click here to copy your link"
        :schnaq/link-copied-heading "Link copied"
        :schnaq/link-copied-success "The link was copied to your clipboard!"
        :schnaq/created-success-heading "Your schnaq was created!"
        :schnaq/created-success-subheading "Distribute your personal share-link or invite participants via email 🎉"
        :schnaqs/continue-with-schnaq-after-creation "Did you invite everybody? Lets go!"
        :schnaqs/continue-to-schnaq-button "To the schnaq"

        :schnaq.admin/addresses-label "Email addresses of the participants"
        :schnaq.admin/addresses-placeholder "Email addresses separated by a newline or space."
        :schnaq.admin/addresses-privacy "These addresses are only used to send the invitation emails and are deleted from our
        servers immediately afterwards."
        :schnaq.admin/send-invites-button-text "Send invitations"
        :schnaq.admin/send-invites-heading "Invite participants via email"
        :schnaq.admin.notifications/emails-successfully-sent-title "Mails sent!"
        :schnaq.admin.notifications/emails-successfully-sent-body-text "Your invitations were sent successfully."
        :schnaq.admin.notifications/sending-failed-title "Error during mail delivery!"
        :schnaq.admin.notifications/sending-failed-lead "The following invitations could not be delivered: "
        :schnaq.admin.notifications/statements-deleted-title "Statements deleted!"
        :schnaq.admin.notifications/statements-deleted-lead "The statements you entered have been deleted."
        :schnaq.admin.notifications/heading "Configuration"
        :schnaq.admin.configurations.read-only/checkbox "Set to read-only"
        :schnaq.admin.configurations.read-only/explanation "When checked, users can no longer add new posts to the discussion. Existing posts are still readable and can be analysed. You can change this option anytime."
        :schnaq.admin.configurations.disable-pro-con/label "Disable agree/disagree button"
        :schnaq.admin.configurations.disable-pro-con/explanation "When checked, users can no longer use the agree/disagree button. New posts will be handled as agreement. You can change this option anytime."

        :statement/reply "Reply"
        :statement.edit.send.failure/title "Edit could not be made"
        :statement.edit.send.failure/body "The edit could not be published. Please try again in a short while."
        :statement.edit/label "Edit statement"
        :statement.edit.button/submit "Submit"
        :statement.edit.button/cancel "Cancel"

        ;; schnaq creation
        :schnaq.create/heading "Start schnaq"
        :schnaq.create/subheading "With a schnaq, you can let your team discuss and secure sustainable decisions."
        :schnaq.create/schnaqqi-speech "Create your schnaq and invite your whole team in the next step!"
        :schnaq.create.button/save "Start a new schnaq"

        ;; Discussion Creation
        :discussion.create.public-checkbox/label "Make this schnaq public"
        :discussion.create.hub-exclusive-checkbox/label "Add schnaq to a Hub"

        :discussion.privacy/public "Public Discussion"
        :discussion.privacy/private "Private Discussion"
        :discussion.state/read-only-label "read-only"
        :discussion.state/read-only-warning "This discussion is read-only. You can read the statements, but not write anything."

        ;; Conversion-Edit-Funnel
        :discussion.anonymous-edit.modal/title "Please sign in to edit"
        :discussion.anonymous-edit.modal/explain [:<> "To prevent fraudulent behaviour with anonymous statements, you must " [:strong "sign in to edit a statement."]]
        :discussion.anonymous-edit.modal/persuade "Recent statements from you in this browser will automatically be converted to your logged in account."
        :discussion.anonymous-edit.modal/cta "Sign in / Sign up"

        ;; Conversion-Delete-Funnel
        :discussion.anonymous-delete.modal/title "Please sign in to delete"
        :discussion.anonymous-delete.modal/explain [:<> "To prevent fraudulent behaviour with anonymous statements, you must " [:strong "sign in to delete a statement."]]
        :discussion.anonymous-delete.modal/persuade "Recent statements from you in this browser will automatically be converted to your logged in account."
        :discussion.anonymous-delete.modal/cta "Sign in / Sign up"

        ;; Beta Only Funnel
        :beta.modal/title "Beta-Feature"
        :beta.modal/explain [:<> "This feature is in a test-state and you need to " [:strong "be a beta-tester to use it."]]
        :beta.modal/persuade "You can write us an email if you would like to become a beta-tester."
        :beta.modal/cta "Request Access"
        :page.beta.modal/cta "If you are interested in joining our beta-tester programm, please write us an email at "

        ;; Press Kit
        :press-kit/heading "Press & Media"
        :press-kit/subheading "We are happy to be available for interviews and articles!"
        :press-kit.intro/heading "Thank you for your interest in schnaq!"
        :press-kit.intro/lead "Please take a moment to read our brand guidelines. If you have any press enquiries or would like to write about us, please email presse@schnaq.com. We would love to talk to you!"
        :press-kit.spelling/heading "Correct Spelling and Pronunciation"
        :press-kit.spelling/content-1 "Our product is called"
        :press-kit.spelling/content-2 "(spoken: [ˈʃnak]) and is written with a \"q\". It is pronounced with a soft \"sch\", analogous to the North German \"schnacken\". Except at the beginning of sentences, schnaq should be written in lower case."
        :press-kit.not-to-do/heading "Please note the following points"
        :press-kit.not-to-do/bullet-1 "Do not use any other images, illustrations, content or other assets from this domain without permission."
        :press-kit.not-to-do/bullet-2 "Avoid displaying these graphics in a way that implies a relationship, affiliation or endorsement by schnaq. If you are unsure, please feel free to contact us."
        :press-kit.not-to-do/bullet-3 "Do not use these graphics as part of the name of your own product, business or service."
        :press-kit.not-to-do/bullet-4 "Please avoid altering these graphics in any way or combining them with other graphics without our written consent."
        :press-kit.materials/heading "Assets"
        :press-kit.materials/fact-sheet "Fact-Sheet"
        :press-kit.materials/logos "Logos"
        :press-kit.materials/product "Product Images"
        :press-kit.materials/team "Team Pictures"
        :press-kit.materials/download "Download"
        :press-kit.about-us/heading "Further Information"
        :press-kit.about-us/body "Further information on our founders, scientific publications and other appearances in newspapers and media, can be found on the following pages:"

        ;; Publications
        :publications/heading "Publications and Articles"
        :publications/subheading "The Science behind schnaq"
        :publications.primer/heading "From Science into Practice"
        :publications.primer/body "The software we develop is based not only on experience, but also on many years of research in the fields of discussion and communication. Here you will find scientific articles, newspaper articles and other publications that originate from our team or have been produced in cooperation with our team."

        :publications.perspective-daily/summary "An article about our research in Perspective Daily. Focus on structured discussion."
        :publications.salto/summary "An interview with our founders Dr. Christian Meter and Dr. Alexander Schneider about discussions on the internet, trolls and how to fight them."
        :publications.dissertation-alex/summary "Dr. Alexander Schneider's dissertation deals with the question of whether structured discussions can be carried out on the internet via decentralised systems."
        :publications.dissertation-christian/summary "In Dr. Christian Meter's dissertation, several novel procedures and approaches are highlighted to be able to conduct structured discussions on the internet."
        :publications.structure-or-content/summary "This paper analyses whether Pagerank as an algorithm can make reliable statements about argument relevance and how its performance compares to newer algorithms."
        :publications.overview-paper/summary "A presentation of a wide variety of methods that make it possible to improve real discussions on the internet."
        :publications.dbas/summary "The description of a formal prototype for dialogue-based online argumentation including evaluation."
        :publications.dbas-politics/summary "A presentation of the concept of dialogue-based online discussions for lay people."
        :publications.eden/summary "The presentation of a software package that allows the operation of decentralised servers that give users access to online discussion systems."
        :publications.jebediah/summary "The paper demonstrates a social bot based on Google's Dialogflow Engine. The bot is able to communicate with its users in social networks based on dialogue."
        :publications.dbas-experiment/summary "In a field experiment with over 100 test persons, we will investigate how well a dialogue-based argumentation system can be used by laypersons."
        :publications.reusable-statements/summary "The authors explore the idea of making online arguments and their interrelationships usable and reusable as a resource."
        :publications.discuss/summary "If structured discussions are possible via software, is it also possible to let these discussions take place in any web context? The authors explore this question."
        :publications.kind/article "Article"
        :publications.kind/dissertation "Dissertation (english)"
        :publications.kind/interview "Interview"
        :publications.kind/newspaper-article "Newspaper Article"
        :publications.kind/paper "Paper (english)"
        :publications.kind/short-paper "Shortpaper (english)"

        ;; Privacy Page
        :privacy/heading "What happens to your data?"
        :privacy/subheading "We lead you through it step by step!"
        :privacy/open-settings "Open Settings"
        :privacy.made-in-germany/lead "EU-regulation conformity"
        :privacy.made-in-germany/title "Data privacy is important to us!"
        :privacy.made-in-germany/body
        [:<>
         [:p "The development team of schnaq consists of developers that are tired of misuse of private data. This is why we take special care to be GDPR compliant and to save all data securely on german servers provided by Hetzner. We do not exchange any data with other companies without absolute need and making it completely clear."]
         [:p "If you are still unclear about how we handle your data, please feel free to contact us! We really care about transparency and clarity with personal data and we explain to you to the last bit what happens with the data."]]
        :privacy.personal-data/lead "Which data is saved?"
        :privacy.personal-data/title "Personal Data"
        :privacy.personal-data/body
        [:<> [:p "Per default we only save data that is needed to operate the service. There is no analysis of personal data, and anonymous data of your behavior on our website is only collected, when you explicitly allow us to do so. "]
         [:p "If you want to support us and allow the analysis, we collect the data with Matomo and save it on our german servers. Matomo is a free and self-hosted alternative to commercial options for website analytics . We do not exchange this data with third parties."] [:p [:button.btn.btn-outline-primary {:on-click (js-wrap/show-js-klaro)} "Check your settings"]]]
        :privacy.localstorage/lead "What data do I send to the server?"
        :privacy.localstorage/title "Data Exchange"
        :privacy.localstorage/body
        [:<> [:p "schnaq has no need for accounts. This way no personal data about you is saved on the server. Most of the interactions work through links. When you click on a link a part of it (the so called hash) is stored in your browser (in the localStorage). As soon as you go to schnaq.com again, your browser sends this hash back and you gain access to your created schnaqs. Alternatively you can send the links to yourself via email. This way you have all the data in your own hands."]
         [:p "In difference to website-cookies we use the localStorage, which only saves data that is needed for the application to function for you.
         You can see the data in your localStorage by clicking the following button."]]
        :privacy.localstorage/show-data "Show my data"
        :privacy.localstorage.notification/title "This data is saved by your browser"
        :privacy.localstorage.notification/body "Tip: \"Cryptic\" strings of characters are the codes that allow you to view schnaqs."
        :privacy.localstorage.notification/confirmation "Do you really want to delete the data?"
        :privacy.localstorage.notification/delete-button "Delete data"

        :privacy.data-processing.anonymous/lead "What happens to your posts?"
        :privacy.data-processing.anonymous/title "Data Processing for Anonymous Users"
        :privacy.data-processing.anonymous/body [:<> [:p "Your posts and chosen username will be stored on our own servers and are not passed on to other companies. If you do not provide any username, the author of your posts will be displayed as \"Anonymous\". Since even we do not remember who the contributions come from, it is not possible to edit the contribution. We do not store any other personal data, e.g. user-agent or ip address, to your posts."]
                                                 [:p "Posts in public schnaqs can be viewed by anyone. Posts in private schnaqs will only be visible to those with access to the link. Administrators of a schnaq are able to delete a schnaq’s posts."]]
        :privacy.data-processing.registered/lead "And if I am logged in now?"
        :privacy.data-processing.registered/title "Data Processing for Registered Users"
        :privacy.data-processing.registered/body
        [:<> [:p "If you decide to register, your email address and name will be saved. This allows us to personalize your schnaq experience and display your name when you save a post. The email address is among other things necessary for notifications so that you are informed when there are new posts for you."]
         [:p "When you log in via an external provider, such as LinkedIn, LinkedIn receives a request from you to transmit the displayed information to us, which we then store. If you log in again, LinkedIn will also receive another request. If you want to avoid this, simply create an account with us directly."]
         [:p "In addition, we store in your account the hubs and schnaqs to which you have access. So you can also log in on your smartphone or other device and have access to all your schnaqs."]
         [:p "Now it is also possible to use advanced features, like edit posts, since you now have an identity on our platform 👍"]
         [:p "At any time you can contact us and request to view or delete your data."]]

        :privacy.link-to-privacy/lead "More information can be found in the comprehensive "
        :privacy/note "Privacy notice"

        :privacy.extended/heading "Privacy"
        :privacy.extended/subheading "We are compliant to GDPR"
        :privacy.extended.intro/title "General information on data processing"
        :privacy.extended.intro/body
        [:<>
         [:p "As a matter of principle, we only process personal data insofar as this is necessary to provide a functional website and our content. Personal data is regularly processed only with the consent of the user."]
         [:p "Insofar as consent is required for processing operations of personal data, Art. 6 (1) lit. a EU General Data Protection Regulation (GDPR) serves as the legal basis.\nIf the processing is necessary to protect a legitimate interest on our part or on the part of a third party and your interests, fundamental rights and freedoms do not override the former interest, Art. 6 (1) lit. f GDPR serves as the legal basis for the processing."]
         [:p "Personal data will be deleted as soon as the purpose of storage ceases to apply. Storage may also take place if this has been provided for by the European or national legislator in Union regulations, laws or other provisions to which we are subject. Data will also be deleted if a storage period prescribed by the aforementioned standards expires."]]
        :privacy.extended.logfiles/title "Provision of the website and creation of log files"
        :privacy.extended.logfiles/body
        [:<>
         [:p "Each time our website is accessed, our system automatically collects data and information (browser type / version used, operating system, IP address, date and time of access, websites from which our website was accessed, websites accessed via our website) from the computer system of the accessing computer. The data is stored in the log files of our system. This data is not stored together with other personal data of the user. The legal basis for the temporary storage of the data and the log files is Art. 6 para. 1 lit. f GDPR."]
         [:p "The temporary storage of the IP address by the system is necessary to enable delivery of the website to the user's computer. For this purpose, the IP address must remain stored for the duration of the session. The storage in log files is done to ensure the functionality of the website. In addition, we use the data to optimize the website and to ensure the security of our information technology systems. These purposes are also our legitimate interest in data processing according to Art. 6 para. 1 lit. f GDPR."]
         [:p "The data is deleted as soon as it is no longer required to achieve the purpose for which it was collected. In the case of the collection of data for the provision of the website, this is the case when the respective session has ended. In the case of storage of data in log files, this is the case after seven days at the latest. Storage beyond this period is possible. In this case, the IP addresses of the users are deleted or anonymized."]
         [:p "The collection of data for the provision of the website and the storage of the data in log files is mandatory for the operation of the website. Consequently, there is no possibility to object."]]
        :privacy.extended.cookies/title "Cookies"
        :privacy.extended.cookies/body
        [:<>
         [:p "We use so-called cookies on our homepage. Cookies are data packages that your browser stores in your terminal device at our instigation. A distinction is made between two types of cookies: temporary, so-called session cookies, and persistent cookies."]
         [:p "Session cookies are automatically deleted when you close the browser. These store a so-called session ID, with which various requests of your browser can be assigned to the common session. This allows your computer to be recognized when you return to our website. The use of session cookies is necessary so that we can provide you with the website. The legal basis for the processing of your personal data using session cookies is Art. 6 para. 1 lit. f GDPR."]
         [:p "Persistent cookies are automatically deleted after a predefined period of time, which may differ depending on the cookie. These cookies remain on your end device for a predefined time and are usually used to recognize you when you visit our homepage again. The use of persistent cookies on our homepage is based on the legal basis of Art. 6 para. 1 lit. f GDPR."]
         [:p "You can set your internet browser so that our cookies cannot be stored on your end device or so that cookies that have already been stored are deleted. If you do not accept cookies, this may lead to restrictions in the function of the Internet pages."]
         [:p "Specifically, we have these types of cookies:"]
         [:ul
          [:li "CSRF token (session cookie), which secures the contact form against unobserved content submission. This is a random arrangement of characters, which is used only for sending the form. This cookie is deleted after you leave our website. This protection mechanism complies with common security standards and can, for example "
           [:a {:href "https://en.wikipedia.org/wiki/Cross-site_request_forgery"}
            "here"]
           " be researched further."]
          [:li "Login cookie (persistent cookie), which recognizes you as the user you logged in with. After 14 days your cookie expires and is deleted. If you delete this cookie, you will have to log in again the next time you visit the site."]
          [:li "Analysis cookie (persistent cookie), which can optionally be set so that we can understand your behavior and interests anonymously. For more information, see the section on the use of Matomo."]]
         [:p "All cookies we use generate random strings that are used to match corresponding strings on our server."]]

        :privacy.extended.personal-data/title "Personal data"
        :privacy.extended.personal-data/body
        [:<>
         [:h4 "Use of schnaq without user accounts"]
         [:p "If you use schnaq without registering, you are so called \"Anonymous User\". Here, in addition to the data necessary for server operation, only your contribution and an optional self-selected name will be saved. When saving the contribution, this string is then loosely saved with the contribution. An allocation to an identity does not take place thereby. If someone with the same name participates in any schnaq, the contributions appear to the outside as if they came from the same person."]
         [:p "By sending your contribution you agree to the storage. Since we can later no longer trace who made the contribution, you have no right to delete this contribution, because there is no proof of authorship."]
         [:h4 "Use of schnaq as registered user"]
         [:p "When you register, your mail address and your first and last name are stored. These are necessary for the operation of schnaq, the collection is thus made according to Art. 6 para. 1 lit. f GDPR. Registration is optional for the normal operation of schnaq. With the mail address, automatic notifications of new contributions are enabled. With the names, your contributions are displayed together on the interface of schnaq. Further affiliations, for example to the hubs or other schnaqs, are also visually displayed with it."]
         [:p "This data is stored on our own servers and is not passed on to third parties."]
         [:p "There are options to expand your own user profile. This includes, for example, uploading your own optional profile picture. This profile picture is then displayed as your avatar and is presented whenever your user account appears, for example, when people look at your posts."]
         [:h4 "Text contributions"]
         [:p "The text contributions must originate from you and may not violate any copyrights. The text contributions will not be passed on to third parties. Internally, your contributions can be used for further scientific evaluations and the training of our own neural networks. You will never lose your authorship of these contributions. This is used, for example, to automatically calculate machine-generated summaries or statistics. These summaries and statistics are intended for the evaluation of your schnaq and will not be passed on to any third party."]]
        :privacy.extended.matomo/title "Web analysis by Matomo (formerly PIWIK)"
        :privacy.extended.matomo/body
        [:<>
         [:h4 "Description and scope of data processing"]
         [:p "We use the open source software tool Matomo (formerly PIWIK) on our website to analyze the use of our internet presence. For example, we are interested in which pages are accessed how often and whether smartphones, tablets or computers with large screens are used. The software sets a cookie on the user's computer (for cookies, see above). If individual pages of our website are called up, the following data is stored:"]
         [:ol
          [:li "Two bytes of the IP address of the calling system"]
          [:li "The accessed web page"]
          [:li "The website through which our website was accessed (referrer)"]
          [:li "The subpages that are called from the called web page"]
          [:li "The time spent on the website"]
          [:li "The frequency of access to the website"]]
         [:p "Matomo is set so that the IP addresses are not stored in full, but two bytes of the IP address are masked (example: 192.168.xxx.xxx). In this way, an assignment of the shortened IP address to the calling computer is no longer possible."]
         [:p "Matomo is used exclusively on schnaq servers. Personal data of the users is only stored there. The data is not passed on to third parties."]
         [:h4 "Purpose of data processing"]
         [:p "The processing of anonymized user data enables us to analyze the use of our website. By evaluating the data obtained, we are able to compile information about the use of the individual components of our website. This helps us to continuously improve our services and their user-friendliness. By anonymizing the IP address, the interest of the user in the protection of his personal data is sufficiently taken into account."]
         [:p "No profiles are created that would give us a deeper insight into the usage behavior of individual users. The evaluation is exclusively anonymized and aggregated so that no conclusion can be drawn about individual persons."]]
        :privacy.extended.rights-of-the-affected/title "Rights of the data subjects"
        :privacy.extended.rights-of-the-affected/body
        [:<>
         [:p "If personal data is processed by you, you are a data subject in the sense of the GDPR and you are entitled to the rights described below. Please address your request, preferably by e-mail, to the above-mentioned data controller."]
         [:p [:strong "Information:"]
          " You have the right to receive from us at any time free information and confirmation of the personal data stored about you and a copy of this information."]
         [:p [:strong "Correction:"]
          " You have the right to rectification and/or completion if the personal data processed concerning you is inaccurate or incomplete."]
         [:p [:strong "Restriction of processing:"]
          " You have the right to request the restriction of processing if one of the following conditions is met:"]
         [:ul
          [:li "You dispute the accuracy of the personal data for a period of time that allows us to verify the accuracy of the personal data."]
          [:li "The processing is unlawful, you object to the erasure of the personal data and request instead the restriction of the use of the personal data."]
          [:li "We no longer need the personal data for the purposes of processing, but you need it to assert, exercise or defend legal claims."]
          [:li "You have objected to the processing pursuant to Art. 21 (1) GDPR and it is not yet clear whether our legitimate grounds outweigh yours."]]
         [:p [:strong "Deletion:"]
          " You have the right to have the personal data concerning you erased without delay, provided that one of the following reasons applies and insofar as the processing is not necessary:"]
         [:ul
          [:li "The personal data were collected or otherwise processed for such purposes for which they are no longer necessary. "]
          [:li "You withdraw your consent on which the processing was based and there is no other legal basis for the processing. "]
          [:li "You object to the processing pursuant to Article 21(1) of the GDPR and there are no overriding legitimate grounds for the processing, or you object to the processing pursuant to Article 21(2) of the GDPR. "]
          [:li "The personal data have been processed unlawfully. "]
          [:li "The deletion of the personal data is necessary for compliance with a legal obligation under Union or Member State law to which we are subject. "]
          [:li "The personal data was collected in relation to information society services offered pursuant to Art. 8 (1) GDPR. "]]
         [:p [:strong "Data portability:"]
          " You have the right to receive the personal data concerning you that you have provided to the controller in a structured, common and machine-readable format. You also have the right to transfer this data to another controller without hindrance from the controller to whom the personal data was provided. In exercising this right, you also have the right to obtain that the personal data concerning you be transferred directly from us to another controller, insofar as this is technically feasible. The freedoms and rights of other persons must not be affected by this."]
         [:p [:strong "Opposition:"]
          " You have the right to object at any time to the processing of personal data concerning you that is carried out on the basis of Art. 6 (1) lit. f GDPR. We will no longer process the personal data in the event of the objection, unless we can demonstrate compelling legitimate grounds for the processing which override your interests, rights and freedoms, or the processing serves the assertion, exercise or defense of legal claims."]
         [:p [:strong "Revocation of consent:"]
          " You have the right to revoke your declaration of consent under data protection law at any time. The revocation of consent does not affect the lawfulness of the processing carried out on the basis of the consent until the revocation."]]
        :privacy.extended.right-to-complain/title "Right to complain to a supervisory authority"
        :privacy.extended.right-to-complain/body
        [:<>
         [:p "Without prejudice to any other administrative or judicial remedy, you have the right to lodge a complaint with a supervisory authority, in particular in the Member State of your residence, if you consider that the processing of personal data concerning you infringes the GDPR.\nThe data protection supervisory authority responsible for the operator of this site is:"]
         [:p "The State Commissioner for Data Protection and Freedom of Information of North Rhine-Westphalia, Kavalleriestr. 2-4, 40102 Düsseldorf, Tel.: +49211/38424-0, e-mail: poststelle{at}ldi.nrw.de"]]
        :privacy.extended.hosting/title "Hosting der Webseite"
        :privacy.extended.hosting/body
        [:<>
         [:p "The schnaq website is hosted on servers of Hetzner Online GmbH in Germany. For further information, please refer to the websites of Hetzner Online GmbH."]
         [:h4 "Conclusion of a commissioned data processing contract (AV contract)"]
         [:p "We have concluded an AV contract with Hetzner Online GmbH, which protects our customers and obliges Hetzner not to pass on the collected data to third parties."]]
        :privacy.extended.responsible/title "Responsible Person"
        :privacy.extended.responsible/body
        [:<>
         [:p
          "schnaq (not founded)" [:br]
          "represented by Christian Meter" [:br]
          "Am Hagen 6" [:br]
          "42855 Remscheid" [:br]
          (toolbelt/obfuscate-mail "info@schnaq.com")]
         [:p "Legally binding is the German version of this page."]]

        ;; About us
        :about-us.unity/title "The Unit schnaq"
        :about-us.unity/body [:<> [:p "schnaq brings digital discussions into the future. We offer companies the opportunity to conduct transparent decision-making processes in which the entire team can be heard, so that equal-opportunity and comprehensible discourse takes place. Our analytics help you understand which team member has not been heard enough and should be included. By sharing knowledge through discussions on our platform, we prevent knowledge silos and tacit company knowledge by making company knowledge available to all, be it written or later spoken communication."]
                              [:p "Our team is committed to ensuring that every voice can be heard!"]]

        :about-us.value/title "Our Values"
        :about-us.value/subtitle "We follow values that define our actions and our products."
        :about-us.honesty/title "Honesty"
        :about-us.honesty/body "We focus on presenting our products and their capabilities honestly and without exaggeration. We firmly believe that our products can stand for themselves without any exaggeration."
        :about-us.collaborate/title "Will to Collaborate"
        :about-us.collaborate/body "We firmly believe that we can achieve more together than alone. That's why we like to cultivate a culture of collaboration. Whether it's among ourselves as a team or with our customers and cooperation partners. Together we can create great things."
        :about-us.action/title "Drive"
        :about-us.action/body "We don't make decisions out of the blue, but based on all the data we have available. But once a decision has been made after discussions, we stand behind it together and pull together to move forward efficiently."
        :about-us.quality/title "Quality"
        :about-us.quality/body "We are proud of our work and what we create. We like our work, we see it as a part of us and we enjoy connecting people all over the world. That's why we care that our products are of the highest possible quality."
        :about-us.diversity/title "Diversity"
        :about-us.diversity/body "Every person brings their own unique perspective on the world. And precisely because we bring people into contact with each other, we want as many of these perspectives as possible to flow into our work."

        :about-us.numbers/title "schnaq in Numbers"
        :about-us.numbers/research "Years of Research"
        :about-us.numbers/users "Users"
        :about-us.numbers/statements "Statements Structured"
        :about-us.numbers/loc "Lines of Code"

        :about-us.team/title "Focus on the Team"
        :about-us.team/alexander "Co-Founder - Operational Management"
        :about-us.team/christian "Co-Founder - Technical Management"
        :about-us.team/mike "Co-Founder - Product Design Management"
        :about-us.team/philip "Co-Founder - Chief of Customer Success"

        :about-us.page/heading "About Us"
        :about-us.page/subheading "Information about us"

        ;; Legal Note
        :legal-note.page/heading "Legal Note"
        :legal-note.page/disclaimer "Disclaimer"

        :legal-note.contents/title "Liability for Contents"
        :legal-note.contents/body "As a service provider, we are responsible for our own content on these pages in accordance with general legislation pursuant to Section 7 (1) of the German Telemedia Act (TMG). According to §§ 8 to 10 TMG, however, we are not obligated as a service provider to monitor transmitted or stored third-party information or to investigate circumstances that indicate illegal activity. Obligations to remove or block the use of information under the general laws remain unaffected. However, liability in this regard is only possible from the point in time at which a concrete infringement of the law becomes known. If we become aware of such infringements, we will remove this content immediately."
        :legal-note.links/title "Liability for Links"
        :legal-note.links/body "Our offer contains links to external websites of third parties, on whose contents we have no influence. Therefore, we cannot assume any liability for these external contents. The respective provider or operator of the pages is always responsible for the content of the linked pages. The linked pages were checked for possible legal violations at the time of linking. Illegal contents were not recognizable at the time of linking. However, a permanent control of the contents of the linked pages is not reasonable without concrete evidence of a violation of the law. If we become aware of any infringements, we will remove such links immediately."
        :legal-note.copyright/title "Copyright"
        :legal-note.copyright/body "The content and works created by the site operators on these pages are subject to German copyright law. The reproduction, editing, distribution and any kind of exploitation outside the limits of copyright require the written consent of the respective author or creator. Downloads and copies of this site are only permitted for private, non-commercial use. Insofar as the content on this site was not created by the operator, the copyrights of third parties are respected. In particular, third-party content is identified as such. Should you nevertheless become aware of a copyright infringement, please inform us accordingly. If we become aware of any infringements, we will remove such content immediately."
        :legal-note.privacy/title "Privacy Policy"
        :legal-note.privacy/body "You can find our privacy policy here."

        ;; Celebrations
        :celebrations.schnaq-filled/title "🎉 Congratulations 🎉"
        :celebrations.schnaq-filled/lead "You have filled a new schnaq with a first statement. This is the first milestone towards a successful discussion 💪"
        :celebrations.schnaq-filled/share-now "Now share the schnaq with your team!"
        :celebrations.schnaq-filled/button "Sharing Options"
        :celebrations.first-schnaq-created/title "You have created your first schnaq 🎈"
        :celebrations.first-schnaq-created/lead "Do you want to connect your schnaq to an account? Then register with a few clicks 🚀"

        ;; schnaqs not found
        :schnaqs.not-found/alert-lead "Unfortunately, no schnaqs were found."
        :schnaqs.not-found/alert-body "Invite people to your first schnaq after you created it."

        ;; Admin Center
        :schnaq/educate-on-link-text "Share the link below with your coworkers."
        :schnaq/educate-on-link-text-subtitle "Everybody with possession of the link can participate."
        :schnaq/educate-on-edit "Want to change the name or description?"
        :schnaq/educate-on-admin "Go back to the admin center at any time!"
        :schnaq.admin/heading "Admin-Center"
        :schnaq.admin/subheading "schnaq: \"%s\""
        :schnaq.admin.edit.link/header "Entry to the admin-center"
        :schnaq.admin.edit.link/primer "Administration takes work, let others help!"
        :schnaq.admin.edit.link/admin "Entry to Admin-Center via Email"
        :schnaq.admin.edit.link/admin-privileges "Edit and administer suggestions"
        :schnaq.admin.edit.link.form/label "Email address of the administrators"
        :schnaq.admin.edit.link.form/placeholder "Enter an email address"
        :schnaq.admin.edit.link.form/submit-button "Send link"
        :schnaq.admin.invite/via-link "Distribute Link"
        :schnaq.admin.invite/via-mail "Invite via Email"
        :schnaq.admin.edit/administrate "Administrate schnaq"
        :schnaq.export/as-text "Download schnaq as a text-file"
        :schnaq.admin/tooltip "Administrate schnaq"
        :share-link/copy "Copy share-link"

        :sharing/tooltip "Share"
        :sharing.modal/title "Share your schnaq"
        :sharing.modal/lead "Invite your whole team to fill this schnaq with knowledge."
        :sharing.modal/schnaqqi-help "Fill the schnaq with your ideas. Your colleagues will find it easier to get started."

        ;; Discussion Language
        :discussion/create-argument-action "Add Statement"
        :discussion/add-argument-conclusion-placeholder "I think that…"
        :discussion/add-premise-supporting "I want to support the statement"
        :discussion/add-premise-against "I disagree…"
        :discussion/add-premise-neutral "I want to add something"
        :discussion.add.button/support "Support"
        :discussion.add.button/attack "Attack"
        :discussion.add.button/neutral "Neutral"
        :discussion.add.statement/new "New post from you"
        :discussion.badges/user-overview "All participants"
        :discussion.badges/delete-statement "Delete statement"
        :discussion.badges/delete-statement-confirmation "Do you really want to delete the statement?"
        :discussion.notification/new-content-title "New statement!"
        :discussion.notification/new-content-body "Your statement was added successfully!"
        :discussion.badges/edit-statement "edit"
        :discussion.badges/statement-by " by "              ; spaces intended

        ;; meetings overview
        :schnaqs/header "Overview of your schnaqs"
        :schnaqs/subheader "These are the schnaqs that you are part of"
        :schnaqs.all/header "Public schnaqs"

        ;; Feedback
        :feedbacks.overview/header "Feedbacks"
        :feedbacks.overview/subheader "All feedbacks"
        :feedbacks.overview/description "Description"
        :feedbacks.overview/table-header "We have %s feedbacks 🥳!"
        :feedbacks.overview/when? "When?"
        :feedbacks.overview/contact-name "From"
        :feedbacks.overview/contact-mail "E-Mail"
        :feedbacks/button "Feedback"
        :feedbacks/screenshot "Screenshot"
        :feedbacks.modal/primer "Feedback is important! we are happy about any kind of feedback. 🥳
        Leave us a comment and thereby help to improve this software. Thank you and dankeschön!"
        :feedbacks.modal/contact-name "Your Name"
        :feedbacks.modal/contact-mail "Email Address"
        :feedbacks.modal/description "Your Feedback"
        :feedbacks.modal/optional "Optional"
        :feedbacks.modal/screenshot "Add screenshot?"
        :feedbacks.modal/disclaimer "Your data is only saved on our german servers and not exchanged with any third party."
        :feedbacks.notification/title "Thank you for your feedback!"
        :feedbacks.notification/body "Your feedback reached us safely 🎉"

        :feedbacks.survey/primer
        [:<> "We would be extremely happy if you could participate in a small survey.
        The survey is hosted through Google Forms. Find their  "
         [:a {:href "https://policies.google.com/privacy"} "privacy policy here."]
         " By participating, you accept their privacy policy."]
        :feedbacks.survey/checkbox "Yes, I want to participate in the survey."
        :feedbacks.survey/loading "The form is being loaded…"
        :feedbacks.survey/tab "Survey"

        :feedbacks.missing/heading "Feedbacks were not yet loaded from our backend."
        :feedbacks.missing/button-text "Load feedbacks"

        ;; Log-in
        :login/as "Hello, "
        :login/set-name "Enter your name"

        ;; analytics
        :analytics/heading "Analytics"
        :analytics/overall-discussions "schnaqs created"
        :analytics/user-numbers "Usernames created"
        :analytics/registered-users-numbers "Registered Users"
        :analytics/average-statements-title "Average number of statements / schnaq"
        :analytics/statements-num-title "# of statements"
        :analytics/active-users-num-title "Active users"
        :analytics/statement-lengths-title "Length of statements"
        :analytics/statement-types-title "Argument types"
        :analytics/fetch-data-button "Retrieving data…"

        ;; Supporters
        :supporters/heading "Supported by the Ministry of Economics of the State of North Rhine-Westphalia"

        ;; Testimonials
        :testimonials/heading "Testimonials"
        :testimonials.doctronic/quote "We observe the development of schnaq with great interest for our own use and for the use of our customers."
        :testimonials.doctronic/author "Ingo Küper, Managing Director doctronic GmbH & Co. KG"

        ;; User related
        :user.button/set-name "Save name"
        :user.button/set-name-placeholder "Your name"
        :user.button/change-name "Change name"
        :user.button/success-body "Name saved successfully"
        :user.set-name.modal/header "Please, enter a name"
        :user.set-name.modal/primer "The name will be visible to other participants of the schnaq."
        :user/login "Sign In"
        :user/logout "Logout"
        :user.profile/settings "Settings"
        :user.profile/star-tooltip "You're an admin!\n\"With great power comes great responsibility.\""
        :user.action/link-copied "Link copied!"
        :user.action/link-copied-body "Share the link with others, to give them access to the schnaq."
        :user/edit-account "Manage Account Information"
        :user/edit-hubs "Manage Hubs"
        :user.settings "Settings"
        :user.keycloak-settings "Advanced Settings"
        :user.settings/header "Manage User Data"
        :user.settings/info "User infos"
        :user.settings/hubs "Hubs"
        :user.settings/change-name "Change name and profile picture"
        :user.settings.button/change-account-information "Save changes"
        :user.settings.profile-picture-title/success "Profile picture successfully uploaded"
        :user.settings.profile-picture-body/success "Your new profile picture was successfully set. You may have to reload the page to see it."
        :user.settings.profile-picture-title/error "Error while uploading profile picture"
        :user.settings.profile-picture-too-large/error "Your profile picture size is %d bytes, it exceeds the maximum allowed size of %d bytes. Please upload a smaller picture."
        :user.settings.profile-picture.errors/scaling "Your profile picture could not be converted. Maybe the image is corrupt. Please try a different image or contact us."
        :user.settings.profile-picture.errors/invalid-file-type "The image you provided has the wrong file type. Allowed file types: %s"
        :user.settings.profile-picture.errors/default "Something went wrong with the picture you're uploaded. Please try again."

        ;; Errors
        :errors/navigate-to-startpage "Back to the home page"
        :errors/generic "An error occurred"

        :error.generic/contact-us
        [:<> "Did you end up here after clicking something on schnaq.com? Give us a hint at " [:a {:href "mailto:info@schnaq.com"} "info@schnaq.com"]]

        :error.404/heading "This site does not exist 🙉"
        :error.404/body "The URL that you followed does not exist. Maybe there is a typo."

        :error.403/heading "You do not have the rights to view this site 🧙‍♂️"
        :error.403/body "You either have insufficient rights to view this site, or a typo happened."

        :error.beta/heading "You do not have the rights to view this site 🧙‍♂️"
        :error.beta/body "Only schnaq beta-testers can access this page. If you are one, please log in. If you would like to be a beta-tester, write us an email at hello@schnaq.com."

        ;; Graph Texts
        :graph/heading "Discussion Overview"
        :graph.button/text "Mindmap"
        :graph.download/as-png "Download mindmap as image"
        :graph.settings/title "Settings for your Mindmap"
        :graph.settings/description "Here are some settings for your Mindmap! Play around with the sliders and let the magic happen."
        :graph.settings.gravity/label "Adjust the gravity between your nodes."
        :graph.settings/stabilize "Stabilize Mindmap"

        ;; Pricing Page
        :pricing.free-tier/description "For small teams and private parties. The starter plan is the perfect entry
         into structured idea generation."
        :pricing.free-tier/beta-notice "This plan will be still available after the beta-phase for teams of up to 5 members."
        :pricing.free-tier/call-to-action "Start free of charge"
        :pricing.business-tier/description "Whether 10 or 50 members – the price stays the same.
      Perfect for companies, clubs, educational institutions and everyone looking to structure their knowledge."
        :pricing.units/per-month "/ month"
        :pricing.notes/with-vat "incl. VAT"
        :pricing.notes/yearly-rebate "15% discount when paid yearly in advance"
        :pricing.business-tier/call-to-action "Available from 01.01.2021"
        :pricing.trial/call-to-action "Test business 30 days free of charge"
        :pricing.trial/description "No credit card needed! Cancel anytime."
        :pricing.trial.temporary/deactivation "Available from 01.01.2021"
        :pricing.features/heading "Schnaq subscription advantages"
        :pricing.features.user-numbers/heading "Unlimited member accounts"
        :pricing.features.user-numbers/content "Set no bounds for how many people can collaborate. *"
        :pricing.features.team-numbers/heading "Unlimited number of teams"
        :pricing.features.team-numbers/content "Create as many teams as are needed for your projects. *"
        :pricing.features.app-integration/heading "App integration"
        :pricing.features.app-integration/content "Connect schnaq easily to your Slack, MS Teams, Confluence …"
        :pricing.features.analysis/heading "Automatic Analyses"
        :pricing.features.analysis/content "All discussions are automatically analyzed and presented to the participants."
        :pricing.features.knowledge-db/heading "Knowledge Database"
        :pricing.features.knowledge-db/content "Collect all your knowledge in one spot."
        :pricing.features.mindmap/heading "Interactive mindmap"
        :pricing.features.mindmap/content "All statements are automatically structured and shown in an interactive mindmap."
        :pricing.features/disclaimer "* Applies only to business plan"
        :pricing.competitors/per-month-per-user " € per month per user"
        :pricing.comparison/heading "You continue growing – you continue saving!"
        :pricing.comparison/subheading "No matter how much your team grows, the price stays the same.
   Take a look how the price of schnaq compares to Miro + Loomio + Confluence together."
        :pricing.comparison.schnaq/price-point "79 € per month for your company"
        :pricing.comparison.schnaq/brainstorm "Brainstorming"
        :pricing.comparison.schnaq/decision-making "Decision making"
        :pricing.comparison.schnaq/knowledge-db "Knowledge database"
        :pricing.comparison.schnaq/async "Asynchronous communication"
        :pricing.comparison.schnaq/mindmap "Mindmapping"
        :pricing.comparison.schnaq/analysis "Result analysis"
        :pricing.comparison.schnaq/flatrate " Flat per Month"
        :pricing.comparison.schnaq/person-20 "79 € for 20 users"
        :pricing.comparison.schnaq/person-50 "79 € for 50 users"
        :pricing.comparison.schnaq/person-100 "79 € for 100 users …"
        :pricing.comparison/compared-to [:<> "Compared" [:br] "to"]
        :pricing.comparison.miro/description "Brainstorming software"
        :pricing.comparison.loomio/description "Cooperative decision making"
        :pricing.comparison.confluence/description "Knowledge database"
        :pricing.comparison.competitor/person-10 " per month for 10 users"
        :pricing.comparison.competitor/person-20 "247 € for 20 users"
        :pricing.comparison.competitor/person-50 "685 € for 50 users"
        :pricing.comparison.competitor/person-100 "1370 € for 100 users …"
        :pricing.faq/heading "Frequently asked questions regarding schnaq subscriptions"
        :pricing.faq.terminate/heading "Can I cancel anytime?"
        :pricing.faq.terminate/body
        [:<> [:span.text-primary "Yes! "] "You are able to cancel" [:span.text-primary " every month"] ",
     if you are paying monthly. If you are paying yearly, you can cancel at the end of the subscription year."]
        :pricing.faq.extra-price/heading "Do I need to pay extra for more seats?"
        :pricing.faq.extra-price/body
        [:<> [:span.text-primary "No, "] "you can add" [:span.text-primary " unlimited accounts "]
         " to your organization. Every company, club,
         educational institution, etc. only needs " [:span.text-primary "one subscription."]]
        :pricing.faq.trial-time/heading "Is my trial-subscription automatically converted into a paid one?"
        :pricing.faq.trial-time/body
        [:<> [:span.text-primary "No, "] "when your trial ends, you can" [:span.text-primary " actively decide"]
         ", whether you want to add payment data and continue using the business plan.
         The " [:span.text-primary "starter plan stays free of charge"] ", after a possible trial."]
        :pricing.faq.longer-trial/heading "Can I trial the business plan a little bit longer?"
        :pricing.faq.longer-trial/body
        [:<> [:span.text-primary "Sure! "] "Simply write us an " [:span.text-primary " Email"] " at "
         [:a {:href "mailto:info@schnaq.com"} "info@schnaq.com."]]
        :pricing.faq.privacy/heading "Who can access my data?"
        :pricing.faq.privacy/body-1
        [:<> "Any Person who is added to your organization and to corresponding teams can see their data."
         "From a technical standpoint the data is securely saved on"
         [:span.text-primary " german Servers in a GDPR compliant"] " manner. See the "]
        :pricing.faq.privacy/body-2 "Privacy notice page"
        :pricing.faq.privacy/body-3 " for more information."
        :pricing/headline "Schnaq subscription"
        :pricing.newsletter/lead "Subscribe to the newsletter and be informed as soon as the plans go live: "
        :pricing.newsletter/name "schnaq newsletter."

        ;; feature list
        :feature/what "Decisions become more transparent and well-informed"
        :feature/share "Everybody can contribute their knowledge equally"
        :feature/graph "Relevant knowledge is automatically transformed into a mindmap"
        :feature/processing "Information is easy to find"
        :feature/secure "Data is kept safe with german engineering"
        :schnaq.startpage.cta/button "Discuss with your coworkers"

        ;; tooltips
        :tooltip/history-statement "Back to statement made by "
        :tooltip/history-statement-current "Current statement"

        ;; History
        :history/title "History"
        :history.home/text "Start"
        :history.home/tooltip "Back to the discussion's beginning"
        :history.statement/user "Post from "
        :history.all-schnaqs/text "Overview"
        :history.all-schnaqs/tooltip "Back to all schnaqs"
        :history.back/text "Back"
        :history.back/tooltip "Back to previous post"

        ;; Route Link Texts
        :router.features/discussion "Discussion features"
        :router/admin-center "Admin-Center"
        :router/all-feedbacks "All feedbacks"
        :router/analytics "Analytics dashboard"
        :router/continue-discussion "Continue Discussion"
        :router/create-schnaq "Create schnaq"
        :router/graph-view "Graph view"
        :router/how-to "How do I use schnaq?"
        :router/invalid-link "Error page"
        :router/last-added-schnaq "Last created schnaq"
        :router/visited-schnaqs "Visited schnaqs"
        :router/not-found-label "Not found route redirect"
        :router/pricing "Prices"
        :router/privacy "Privacy Policy"
        :router/show-schnaq "Show schnaq"
        :router/start-discussion "Start discussion"
        :router/startpage "Startpage"
        :router/true-404-view "404 error page"
        :router/public-discussions "Public schnaqs"
        :router/code-of-conduct "Code of Conduct"
        :router/summaries "Summaries"

        :admin.center.start/title "Admin Center"
        :admin.center.start/heading "Admin Center"
        :admin.center.start/subheading "Administrate schnaqs as a superuser"
        :admin.center.delete/confirmation "Do you really want to delete this schnaq?"
        :admin.center.delete.public/label "Public schnaqs"
        :admin.center.delete.public/button "Delete schnaq"
        :admin.center.delete/heading "Deletion"
        :admin.center.delete.public/heading "Public schnaqs"
        :admin.center.delete.private/label "Share-hash"
        :admin.center.delete.private/heading "Private schnaqs"

        :badges.sort/sort "Sort"
        :badges.sort/newest "Newest"
        :badges.sort/popular "Popular"
        :badges.sort/alphabetical "Alphabetical"

        :loading.placeholder/lead "Loading..."
        :loading.placeholder/takes-too-long "This takes longer than expected. Maybe something went wrong. Try to reload the page or repeat the process again. If you still have problems, please contact us!"

        :hubs/heading "Your Hubs"
        :hub/heading "Personal %s Hub"
        :hub/settings "Administration"
        :hub.settings/change-name "Change hub's name"
        :hub.settings.name/updated-title "Change name of hub"
        :hub.settings.name/updated-body "The name of the hub was successfully updated!"
        :hub.settings/save "Save Settings"
        :hub.add.schnaq.success/title "Schnaq added!"
        :hub.add.schnaq.success/body "The schnaq has been added to your hub successfully."
        :hub.add.schnaq.error/title "schnaq was not added!"
        :hub.add.schnaq.error/body "The schnaq could not be added or found. Please check your input and try again."
        :hub.add.schnaq.input/button "Add schnaq"
        :hub.add.schnaq.input/placeholder "schnaq-URL e.g. https://schnaq.com/schnaq/… or share-code"
        :hub.remove.schnaq.success/title "schnaq removed!"
        :hub.remove.schnaq.success/body "The schnaq has been removed from your hub."
        :hub.remove.schnaq.error/title "Removal failed!"
        :hub.remove.schnaq.error/body "Something went wrong. We were unable to remove the schnaq. Please try again."
        :hub.remove.schnaq/prompt "Do you really want to remove the schnaq from the hub?"
        :hub.remove.schnaq/tooltip "Remove the schnaq from hub"
        :hub.members/heading "Members"

        :hub.members.add.result.success/title "Success"
        :hub.members.add.result.success/body "User was added to the Hub"
        :hub.members.add.result.error/title "Error"
        :hub.members.add.result.error/unregistered-user "There seems to be no user with the email address you entered"
        :hub.members.add.result.error/generic-error "Something went wrong. Please check the email you entered and try again"
        :hub.members.add.form/title "Add Members"
        :hub.members.add.form/button "Add user now!"

        :schnaq.search/heading "Search results for: \"%s\""
        :schnaq.search/title "Search"
        :schnaq.search/input "Search for…"
        :schnaq.search/new-search-title "There are no results. Start a new search."

        :lead-magnet.privacy/consent "I want to receive a checklist of tools which comply with german privacy laws as .pdf file in German via mail and register for the newsletter as well. The newsletter is currently available in German only."
        :lead-magnet.form/button "Send me a copy!"
        :lead-magnet/heading "German laws take privacy very seriously and so do we"
        :lead-magnet/subheading "Our checklist for working remotely according to german privacy laws (in German only)"
        :lead-magnet.cover/alt-text "cover of the checklist for working remotely according to german privacy laws"
        :lead-magnet.form/label "We will send a download link via mail"
        :lead-magnet.requested/part-1 "First step done to comply with german privacy laws when working remotely!"
        :lead-magnet.requested/part-2 "You should receive the download link within a few minutes. Please check your spam folder in case you did not receive any mail."
        :lead-magnet.cta/button "Take me to the checklist!"
        :lead-magnet.explain.what/heading "What is the checklist for privacy sensitive remote work?"
        :lead-magnet.explain.what/text "We checked current software for conformity with european GDPR regulations.
        The results are summarized in this checklist. We included all categories of software that are helpful while working remotely.
        This way you can take privacy into account while working from home, the office or in a hybrid fashion."
        :lead-magnet.explain.how/heading "How does the checklist work?"
        :lead-magnet.explain.how/text "The checklist is structured in categories. Every category, like e.g. chat-software, contains several alternatives.
        Every alternative is marked with a color. Green shows that there are no privacy concerns. While red warns about
        several major concerns. Orange signals a few minor problems, but an overall okay result.
        Besides the color ranking we've included a few keywords which can be used for further research."

        :summary.link.button/text "Summary"
        :summary.user.request-succeeded/label "Summary requested, please wait."
        :summary.user.requested/label "Requesting summary …"
        :summary.user.not-requested/label "Request summary"
        :summary.user/privacy-warning "This function is currently in a beta phase. For the purpose of improving the quality of the function, schnaq employees will be able to discretely view the summaries contents."
        :summary.user.status/label "A summary is currently being generated. Last requested: "
        :summary.user/cta "Press the button to request a summary. It will take a few hours. The summary will appear here as soon as its done."
        :summary.user/label "Summary: "
        :summary.user/last-updated "Last updated: "
        :summary.user/heading "Summaries"
        :summary.user/subheading "See the discussion in a few sentences"
        :summary.admin/open-summaries "Open Summaries: %s"
        :summary.admin/closed-summaries "Closed Summaries: %s"
        :summary.admin/discussion "Discussion"
        :summary.admin/requested-at "Requested at"
        :summary.admin/summary "Summary"
        :summary.admin/submit "Submit"
        :summary.admin/closed-at "Closed at"}

   :de {;; Common
        :error/export-failed "Export hat nicht geklappt, versuchen Sie es später erneut."

        ;; navbar labels
        :nav/startpage "Home"
        :nav/schnaqs "schnaqs"
        :nav.schnaqs/show-all "Alle schnaqs"
        :nav.schnaqs/show-all-public "Alle öffentlichen schnaqs"
        :nav.schnaqs/create-schnaq "schnaq anlegen"
        :nav.schnaqs/last-added "Zuletzt angelegter schnaq"
        :nav/blog "Zum Blog"
        :nav/admin "Admin"

        ;; Call to contribute
        :call-to-contribute/lead "Bisher gibt es hier noch keine Beiträge"
        :call-to-contribute/body "Du könntest die erste Person sein, die hierzu etwas schreibt!"

        ;; code of conduct
        :coc/heading "Verhaltensregeln"
        :coc/subheading "Unsere Benimmregeln"

        :coc.users/lead "Verhalten gegenüber anderen Nutzer:innen"
        :coc.users/title "Respektvoller Umgang und Nichtdiskriminierung"
        :coc.users/body "Ein respektvoller Umgang ist wichtig, um miteinander leben zu können und bietet die Grundlage für sachliche Diskussionen. Dies gilt nicht nur offline sondern auch online. \nUns ist es wichtig, dass sich jede:r Nutzer:in ausdrücken kann, ohne aufgrund ihrer Person, Herkunft oder Ansichten diskriminiert zu werden. \nBeiträge, die sich nicht an diese Richtlinien halten, werden entfernt."

        :coc.content/lead "Inhalte"
        :coc.content/title "Wir halten uns an das Gesetz, bitte tut das auch"
        :coc.content/body "Wir halten das Deutsche Grundgesetz ein; dies gilt auch und insbesondere für Datenschutz, Gleichberechtigung und Nichtdiskriminierung.\nInhalte, die gegen geltendes Recht verstoßen, werden von uns gelöscht."

        ;; how-to
        :how-to/button "Wie schnaqqe ich?"
        :how-to/title "Wie verwende ich schnaq?"
        :how-to.create/title "Worüber möchtest du mit deinem Team schnaqqen?"
        :how-to.create/body
        [:<>
         [:p [:i "\"Welche Systeme nutzen wir wann?\", \"Welchen Preis bieten wir Bildungseinrichtungen?\", \"Was ist unsere Vision?\""]]
         [:p "Gib deinem schnaq einen aussagekräftigen Titel und lasse das ganze Team diskutieren. Alle Beiträge sind wichtig und bieten eine individuelle Sicht auf die Diskussion."]]
        :how-to.why/title "Wieso soll ich schnaqqen?"
        :how-to.why/body "Faden verloren? Nicht zu Wort gekommen? Haben alle alles verstanden? Mal wieder keine Ahnung warum so viel diskutiert wurde?\nSpar dir Zeit und halte Wissen mit schnaq fest. Nimm teil, wann du Zeit hast. Die Mindmap der Diskussion gibt's geschenkt."
        :how-to.admin/title "Administriere deinen schnaq oder lass dir helfen"
        :how-to.admin/body "Lade Teilnehmer:innen per Link oder Mail ein. Wenn du Hilfe beim Administrieren brauchst, klicke einfach auf \"Admin Zugang\" und verschicke einen Administrationszugang per Mail.\n\nAls Admin kannst du Beiträge löschen und Personen per Mail einladen."
        :how-to.schnaq/title "Wie schnaqqe ich?"
        :how-to.schnaq/body "Teile deine Meinung! Trage sie in das Eingabefeld ein und sie erscheint in der Liste an Beiträgen. Teilnehmer:innen können drauf reagieren und antworten. Die Mindmap wird automatisch generiert und aktualisiert sich mit jedem neuen Beitrag. Wenn du zu einem Beitrag springen möchtest, klicke ihn einfach an."
        :how-to.pro-con/title "Bist du dafür oder dagegen?"
        :how-to.pro-con/body "Teile den Anderen deine Grundhaltung zum aktuellen Beitrag mit. Mit einem Klick auf unseren Dafür/Dagegen Knopf beim Eingabefeld änderst du deine Haltung. Du kannst auch mehrere Argumente dafür oder dagegen nennen. Pro-Argumente werden Blau eingefärbt, Kontra-Argumente Orange."
        :how-to.call-to-action/title "Jetzt weißt du Bescheid!"
        :how-to.call-to-action/body "Hiermit ist alles erklärt was über schnaq zu erklären gibt, also lege direkt los!"
        :how-to/ask-question "Nicht sicher wie du schnaq benutzen sollst? " ;; whitespace intended
        :how-to/ask-question-2 "Noch Fragen? "              ;; whitespace intended
        :how-to/answer-question "Schau hier nach!"
        :how-to/question-dont-show-again "Verstanden? "     ;; whitespace intended
        :how-to/answer-dont-show-again "In Zukunft nicht mehr anzeigen!"


        ;; Startpage
        :startpage/heading "Gemeinsam bessere Entscheidungen treffen"
        :startpage/subheading "Kommunikationssoftware, die Remote-Teams hilft strukturiert Wissen auszutauschen. Demokratisiere Diskussionen – Weil jede:r im Team etwas beitragen kann."
        :startpage.social-proof/numbers [:span "schnaq hat schon in über " [:b marketing-num-schnaqs]
                                         " Diskussionen geholfen mehr als " [:b marketing-num-statements] " mal Wissen auszutauschen."]

        :startpage.usage/lead "Wofür kann ich schnaq verwenden?"
        :startpage.features/more-information "Mehr Informationen"

        :startpage.features.meeting-organisation/lead "Gut informiert an jedem Punkt"
        :startpage.features.meeting-organisation/title "Optimale Entscheidungen treffen"
        :startpage.features.meeting-organisation/body
        [:<>
         [:p "Mit Chat-Tools oder E-Mails ist es schwierig Entscheidungen so zu treffen, dass jede:r Wissen einbringen kann. Die Nachvollziehbarkeit leidet sehr."]
         [:p "Mit schnaq Diskussionen können alle gleichberechtigt und verteilt an verschiedenen Zeitpunkten an einer Diskussion teilnehmen. So können alle im Team dazu beitragen eine gut informierte Entscheidung zu treffen. Dabei ist es unwichtig, wo die einzelnen Teammitglieder sitzen."]]

        :startpage.features.discussion/lead "Onboarding war nie einfacher"
        :startpage.features.discussion/title "Einfache Team Q&A Funktion"
        :startpage.features.discussion/body
        [:<>
         [:p "Allzu oft hängt wichtiges Wissen an einzelnen Personen. Niemand hat die Zeit alles immer in das Firmenwiki zu übertragen."]
         [:p "schnaq zielt darauf ab das Wissen dort zu erfassen wo es entsteht. Wenn jemand eine Frage hat, können die Kolleg:innen diese einfach beantworten."]
         [:p "Anschließend können alle aus dem Team einfach darauf zugreifen und informiert arbeiten. Kollaboration als wäre es Magie."]]

        :startpage.features.graph/lead "Einfache Integration"
        :startpage.features.graph/title "Persönliche Bereiche für jedes Team"
        :startpage.features.graph/body
        [:<>
         [:p "Jedes Team kann einen eigenen Hub bekommen. Auf diesen Bereich haben nur die Teammitglieder, die ihr hinzufügt, Zugriff."]
         [:p "Schnaq bietet auch die Möglichkeit bereits bestehende Logins, die ihr im Unternehmen nutzt, einzubinden. So braucht ihr keinen extra Account. Braucht ihr weitere Anbindungen? " [:a {:href "mailto:info@schnaq.com"} "Kontaktiert uns!"]]
         [:p.text-center.mb-0
          [:a.btn.btn-primary {:role "button"
                               :href "mailto:info@schnaq.com"}
           "Schreib uns wenn du das neue Hub Feature testen möchtest"]]]

        :startpage.early-adopter/title "Neugierig geworden?"
        :startpage.early-adopter/body "Sei einer der ersten die schnaq Hubs nutzen"
        :startpage.early-adopter/or "oder"
        :startpage.early-adopter/test "Teste neue Features"

        :startpage.newsletter/heading "Gehöre zu den Ersten, die von neuen Funktionen profitieren!"
        :startpage.newsletter/button "Exklusive Informationen anfordern!"
        :startpage.newsletter/address-placeholder "E-Mail Adresse"
        :startpage.newsletter/consent "Ich möchte mich hiermit zum schnaq Newsletter anmelden, und in Zukunft regelmäßig Informationen von schnaq.com erhalten."
        :startpage.newsletter/more-info-clicker "Datenverarbeitung"
        :startpage.newsletter/policy-disclaimer "schnaq erhebt, verarbeitet und nutzt Ihre oben angegebenen personenbezogenen Daten zur
        Bearbeitung Ihres Anliegens. Jederzeit können Sie sich von dem Newsletter abmelden, indem Sie auf
        den in der E-Mail zur Verfügung gestellten Link klicken. Alternativ können Sie uns auch eine E-Mail
        schreiben und wir kümmern uns dann um Ihr Anliegen."
        :startpage.newsletter/privacy-policy-lead "Mehr Informationen zur Verarbeitung von personenbezogenen Daten, finden Sie in unserer "

        :startpage.faq/title "Häufig gestellte Fragen"
        :startpage.faq.data/question "Was passiert mit meinen Daten?"
        :startpage.faq.data/answer-1 "Um einen möglichst sicheren Datenschutz zu gewährleisten, speichern
        wir alle Daten nur auf deutschen Servern. Wir haben alle Details einzeln und verständlich in unserer "
        :startpage.faq.data/link-name "Datenschutzerklärung"
        :startpage.faq.data/answer-2 " zusammengefasst."
        :startpage.faq.integration/question "Kann ich schnaq mit meiner bestehenden Software integrieren?"
        :startpage.faq.integration/answer "Wir arbeiten mit Hochdruck an einer Integration für Slack, MS Team und andere gängige Kommunikationssoftware.
        Wenn du sofort informiert werden willst, wenn die Integration live geht, melde dich für den "
        :startpage.faq.integration/link-name "Newsletter an."
        :startpage.faq.costs/question "Gibt es versteckte Kosten?"
        :startpage.faq.costs/answer "schnaq ist derzeit in einer Testphase und kostenlos benutzbar. Es gibt keinerlei Kosten. Wir freuen uns
        aber über ehrliches Feedback als Gegenleistung."
        :startpage.faq.start/question "Wie kann ich mit schnaq starten?"
        :startpage.faq.start/answer "Du kannst schnaq entweder anonym nutzen, oder dich registrieren und anmelden, um deine schnaqs und Beiträge von
        überall aus einsehen und verwalten zu können. Probier es einfach aus und "
        :startpage.faq.start/link-name "starte einen schnaq."
        :startpage.faq.why/question "Warum sollte ich schnaq nutzen?"
        :startpage.faq.why/answer "schnaq ist für dich, wenn du eine moderne, offene und gleichberechtigte Arbeitskultur unterstützt.
        Unser Ziel ist es Kommunikation und Wissensaustausch am Arbeitsplatz flexibel zu gestalten. So heben wir
        nicht nur das Potenzial einzelner Teammitglieder, sondern auch des gesamten Unternehmens."

        :startpage.founders-note/title "Ein Brief von den Gründern"

        ;; Login Page
        :page.login/heading "Bitte logg dich ein"
        :page.login/subheading "Um auf die folgende Ressource zugreifen zu können, musst du eingeloggt sein"

        :page.beta/heading "Beta-Feature"
        :page.beta/subheading "Diese Funktion ist nur für Beta-Tester:innen freigeschaltet. Bitte logge dich ein, wenn du zu der Gruppe gehörst."

        :footer.buttons/about-us "Über uns"
        :footer.buttons/legal-note "Impressum"
        :footer.buttons/privacy "Datenschutz"
        :footer.buttons/press-kit "Presse"
        :footer.buttons/publications "Publikationen"
        :footer.tagline/developed-with " Entwickelt mit "
        :footer.sponsors/heading "Unsere Server werden gehostet bei"

        ;; Create schnaqs
        :schnaqs/create "schnaq anlegen"

        ;; Header image
        :schnaq.header-image.url/placeholder "Bild URL eingeben"
        :schnaq.header-image.url/button "Vorschaubild hinzufügen"
        :schnaq.header-image.url/note "Erlaubt werden nur Inhalte von pixabay.com"
        :schnaq.header-image.url/label "Fügen Sie Ihrem schnaq ein Vorschaubild hinzu"
        :schnaq.header-image.url/successful-set "Vorschaubild erfolgreich gesetzt"
        :schnaq.header-image.url/successful-set-body "Das Bild wird nun in der Übersicht dargestellt."
        :schnaq.header-image.url/failed-setting-title "Fehler beim Hinzufügen des Bildes"
        :schnaq.header-image.url/failed-setting-body "Das Bild wird nicht in der Vorschau genutzt."

        ;; Create schnaq
        :schnaq.create.input/placeholder "Worüber möchtest du mit deinem Team diskutieren?"
        :schnaq.create.public/help-text "Damit wird dein schnaq öffentlich gelistet und zugänglich sein."
        :schnaq.create.hub/help-text "Füge deinen schnaq direkt einem Hub hinzu."
        :schnaq/copy-link-tooltip "Hier klicken, um Link zu kopieren"
        :schnaq/link-copied-heading "Link kopiert"
        :schnaq/link-copied-success "Der Link wurde in deine Zwischenablage kopiert!"
        :schnaq/created-success-heading "Dein schnaq wurde erstellt!"
        :schnaq/created-success-subheading "Nun kannst du den Zugangslink verteilen oder andere Personen per Mail einladen 🎉"
        :schnaqs/continue-with-schnaq-after-creation "Alle eingeladen? Los geht's!"
        :schnaqs/continue-to-schnaq-button "Zum schnaq"

        :schnaq.admin/addresses-label "E-Mail Adressen der Teilnehmer:innen"
        :schnaq.admin/addresses-placeholder "E-Mail Adressen getrennt mit Leerzeichen oder Zeilenumbruch eingeben."
        :schnaq.admin/addresses-privacy "Diese Adressen werden ausschließlich zum Mailversand genutzt und danach sofort von unseren Servern gelöscht."
        :schnaq.admin/send-invites-button-text "Einladungen versenden"
        :schnaq.admin/send-invites-heading "Lade die Teilnehmer:innen per E-Mail ein"
        :schnaq.admin.notifications/emails-successfully-sent-title "Mail(s) verschickt!"
        :schnaq.admin.notifications/emails-successfully-sent-body-text "Deine Mail(s) wurden erfolgreich versendet."
        :schnaq.admin.notifications/sending-failed-title "Fehler bei Zustellung!"
        :schnaq.admin.notifications/sending-failed-lead "Die Einladung konnte an folgende Adressen nicht zugestellt werden: "
        :schnaq.admin.notifications/statements-deleted-title "Nachrichten gelöscht!"
        :schnaq.admin.notifications/statements-deleted-lead "Deine gewählten Nachrichten wurden erfolgreich gelöscht."
        :schnaq.admin.notifications/heading "Einstellungen"
        :schnaq.admin.configurations.read-only/checkbox "Schreibschutz aktivieren"
        :schnaq.admin.configurations.read-only/explanation "Aktivieren, um keine neuen Beiträge zu erlauben. Bestehende Beiträge sind weiterhin sichtbar und können weiterhin analysiert werden. Diese Option kann jederzeit geändert werden."
        :schnaq.admin.configurations.disable-pro-con/label "Dafür/Dagegen Knopf ausblenden"
        :schnaq.admin.configurations.disable-pro-con/explanation "Aktivieren, um den  Dafür/Dagegen Knopf nicht mehr anzuzeigen. Neue Beiträge werden als Zustimmung gewertet. Diese Option kann jederzeit geändert werden."

        :statement/reply "Antworten"
        :statement.edit.send.failure/title "Änderung nicht gespeichert"
        :statement.edit.send.failure/body "Die Änderung konnte nicht durchgeführt werden. Bitte versuche es gleich noch einmal."
        :statement.edit/label "Beitrag bearbeiten"
        :statement.edit.button/submit "Absenden"
        :statement.edit.button/cancel "Abbrechen"

        ;; schnaq creation
        :schnaq.create/heading "Schnaq starten"
        :schnaq.create/subheading "Mit einem schnaq kannst du dein Team diskutieren lassen und Entscheidungen nachhaltig sichern."
        :schnaq.create/schnaqqi-speech "Erstelle deinen schnaq und lade im nächsten Schritt dein ganzes Team ein!"
        :schnaq.create.button/save "Schnaq starten"

        ;; Discussion Creation
        :discussion.create.public-checkbox/label "Diesen schnaq öffentlich machen"
        :discussion.create.hub-exclusive-checkbox/label "Schnaq zu einem Hub hinzufügen"

        :discussion.privacy/public "Öffentliche Diskussion"
        :discussion.privacy/private "Private Diskussion"
        :discussion.state/read-only-label "schreibgeschützt"
        :discussion.state/read-only-warning "Diese Diskussion ist schreibgeschützt, Sie können hier nur lesen aber nicht schreiben."

        ;; Conversion-Edit-Funnel
        :discussion.anonymous-edit.modal/title "Bitte melde dich an zum Editieren"
        :discussion.anonymous-edit.modal/explain [:<> "Um Missbrauch von anonymen Beiträgen zu vermeiden, musst du dich " [:strong "zum Editieren anmelden."]]
        :discussion.anonymous-edit.modal/persuade "Beiträge, die in letzter Zeit von dir in diesem Browser erstellt wurden, werden dabei automatisch umgewandelt."
        :discussion.anonymous-edit.modal/cta "Anmelden / Registrieren"

        ;; Conversion-Delete-Funnel
        :discussion.anonymous-delete.modal/title "Bitte melde dich an, um deinen Beitrag zu löschen"
        :discussion.anonymous-delete.modal/explain [:<> "Um Missbrauch von anonymen Beiträgen zu vermeiden, musst du dich " [:strong "zum Löschen anmelden."]]
        :discussion.anonymous-delete.modal/persuade "Beiträge, die in letzter Zeit von dir in diesem Browser erstellt wurden, werden dabei deinem Konto hinzugefügt."
        :discussion.anonymous-delete.modal/cta "Anmelden / Registrieren"

        ;; Beta Only Funnel
        :beta.modal/title "Beta-Feature"
        :beta.modal/explain [:<> "Das ist eine Testfunktion. Um sie benutzen zu können musst du " [:strong "Beta-Tester:in sein."]]
        :beta.modal/persuade "Schreib uns eine E-Mail, wenn du auch zu den Beta-Tester:innen gehören möchtest."
        :beta.modal/cta "Anfrage Senden"
        :page.beta.modal/cta "Wenn du daran interessiert bist ein:e Beta-Tester:in zu werden, schreibe uns eine E-Mail unter "

        ;; Press Kit
        :press-kit/heading "Presse & Medien"
        :press-kit/subheading "Wir stehen gerne für Interviews und Artikel zur Verfügung!"
        :press-kit.intro/heading "Vielen Dank für Ihr Interesse an schnaq!"
        :press-kit.intro/lead "Bitte nehmen Sie sich einen Moment Zeit, um unsere Markenrichtlinien zu lesen. Wenn Sie Presseanfragen haben oder über uns schreiben möchten, senden Sie eine E-Mail an presse@schnaq.com. Wir würden uns sehr gerne mit Ihnen unterhalten!"
        :press-kit.spelling/heading "Richtige Schreibweise und Aussprache"
        :press-kit.spelling/content-1 "Unser Produkt heißt"
        :press-kit.spelling/content-2 "(gesprochen: [ˈʃnak]) und wird mit einem \"q\" geschrieben. Ausgesprochen wird es mit einem weichen \"sch\", analog zum norddeutschen \"schnacken\". Außer an Satzanfängen sollte schnaq kleingeschrieben werden. Das grammatikalische Geschlecht von schnaq ist männlich, somit heißt es beispielsweise \"der schnaq\" oder \"einen schnaq erstellen\"."
        :press-kit.not-to-do/heading "Bitte beachten Sie folgende Punkte"
        :press-kit.not-to-do/bullet-1 "Verwenden Sie keine anderen Bilder, Illustrationen, Inhalte oder andere Assets aus dieser Domain ohne Genehmigung."
        :press-kit.not-to-do/bullet-2 "Vermeiden Sie es diese Grafiken in einer Weise anzeigen, die eine Beziehung, Zugehörigkeit oder Befürwortung durch schnaq impliziert. Sollten Sie unsicher sein, so kontaktieren Sie uns gerne."
        :press-kit.not-to-do/bullet-3 "Verwenden Sie diese Grafiken nicht als Teil des Namens Ihres eigenen Produkts, Geschäfts oder Ihrer Dienstleistung."
        :press-kit.not-to-do/bullet-4 "Vermeiden Sie es bitte diese Grafiken in irgendeiner Weise zu verändern oder sie mit anderen Grafiken zu kombinieren, ohne unsere schriftliche Zustimmung."
        :press-kit.materials/heading "Materialien"
        :press-kit.materials/fact-sheet "Fact-Sheet"
        :press-kit.materials/logos "Logos"
        :press-kit.materials/product "Produktbilder"
        :press-kit.materials/team "Teamfotos"
        :press-kit.materials/download "Herunterladen"
        :press-kit.about-us/heading "Weitere Informationen"
        :press-kit.about-us/body "Weitere Informationen zu unseren Gründern, wissenschaftliche Publikationen sowie weitere Erscheinungen in Zeitungen und Medien, finden Sie auf den folgenden Seiten:"

        ;; Publications
        :publications/heading "Publikationen und Artikel"
        :publications/subheading "Die Wissenschaft hinter schnaq"
        :publications.primer/heading "Von der Wissenschaft in die Praxis"
        :publications.primer/body "Die Software, die wir entwickeln, basiert nicht nur auf Erfahrung, sondern auch auf langjähriger Forschung auf den Gebieten der Diskussion und Kommunikation. Hier finden Sie wissenschaftliche Artikel, Zeitungsbeiträge und weitere Publikationen, die von unserem Team stammen, oder in Kooperation mit unserem Team entstanden sind"

        :publications.perspective-daily/summary "Ein Artikel über unsere Forschung in der Perspective Daily. Schwerpunkt ist strukturiertes Diskutieren"
        :publications.salto/summary "Ein Interview mit unseren Gründern Dr. Christian Meter und Dr. Alexander Schneider über Diskussionen im Internet, Trolle und wie man dagegen vorgehen könnte."
        :publications.dissertation-alex/summary "In der Dissertation von Dr. Alexander Schneider geht es um die Frage, ob man strukturierte Diskussionen im Internet per dezentralen Systemen durchführen kann."
        :publications.dissertation-christian/summary "In der Dissertation von Dr. Christian Meter werden mehrere neuartige Verfahren und Herangehensweisen beleuchtet, um im Internet strukturierte Diskussionen durchführen zu können."
        :publications.structure-or-content/summary "In diesem Paper wird analysiert, ob Pagerank als Algorithmus zuverlässige Aussagen über Argumentrelevanz treffen kann und wie die Performance im Vergleich zu neueren Algorithmen ist."
        :publications.overview-paper/summary "Eine Präsentation von verschiedensten Methoden, die es ermöglichen, echte Diskussionen im Internet zu verbessern."
        :publications.dbas/summary "Die Beschreibung eines formalen Prototypen für dialogbasierte Online-Argumentation inklusive Evaluierung."
        :publications.dbas-politics/summary "Eine Vorstellung des Konzeptes der dialogbasierten Online-Diskussionen für Laien."
        :publications.eden/summary "Die Vorstellung eines Software-Paketes, welches den Betrieb von dezentral organisierten Servern erlaubt, die Nutzer:innen Zugang zu Online-Diskussionssystemen geben."
        :publications.jebediah/summary "Das Paper demonstriert einen sozialen Bot auf Basis von Googles Dialogflow Engine. Der Bot ist in der Lage in sozialen Netzwerken dialogbasiert mit seinen Nutzer:innen zu kommunizieren."
        :publications.dbas-experiment/summary "In einem Feldexperiment mit über 100 Proband:innen wird untersucht wie gut ein dialogbasiertes Argumentationssystem von Laien bedient werden kann."
        :publications.reusable-statements/summary "Die Autoren untersuchen den Gedanken, wie man online getätigte Argumente und deren Interrelationen als Resource verwertbar und wiederverwendbar machen kann."
        :publications.discuss/summary "Wenn strukturierte Diskussionen per Software möglich sind, ist es dann auch möglich diese Diskussionen in beliebigen Web-Kontexten stattfinden zu lassen? Dieser Frage gehen die Autoren nach."
        :publications.kind/article "Artikel"
        :publications.kind/dissertation "Dissertation (englisch)"
        :publications.kind/interview "Interview"
        :publications.kind/newspaper-article "Zeitungsartikel"
        :publications.kind/paper "Paper (englisch)"
        :publications.kind/short-paper "Shortpaper (englisch)"

        ;; Privacy Page
        :privacy/heading "Was geschieht mit deinen Daten?"
        :privacy/subheading "Wir erklären es dir gerne!"
        :privacy/open-settings "Einstellungen prüfen"
        :privacy.made-in-germany/lead "EU-konformes Vorgehen"
        :privacy.made-in-germany/title "Datenschutz ist uns wichtig!"
        :privacy.made-in-germany/body
        [:<>
         [:p "Das Entwickler:innenteam von schnaq besteht aus Informatiker:innen, die es Leid sind, dass mit Daten nicht sorgfältig umgegangen wird. Deshalb legen wir besonderen Wert darauf, DSGVO konform zu agieren und sämtliche Daten sicher auf Servern in Deutschland bei Hetzner zu speichern. Kein Datenaustausch mit anderen Unternehmen, keine faulen Kompromisse!"]
         [:p "Sollten noch Unklarheiten bei unserem Vorgehen mit deinen Daten bestehen, so kontaktiere uns gerne! Uns liegt Transparenz und Klarheit mit persönlichen Daten wirklich am Herzen und wir erklären dir bis zum letzten Bit was mit den Daten geschieht."]]
        :privacy.personal-data/lead "Welche Daten werden erhoben?"
        :privacy.personal-data/title "Persönliche Daten"
        :privacy.personal-data/body [:<> [:p "Standardmäßig werden nur technisch notwendige Daten erhoben. Es findet keine Auswertung über persönliche Daten statt und dein Verhalten auf unserer Website wird auch nur dann anonymisiert analysiert, wenn du dem zustimmst. "] [:p "Wenn du uns unterstützen möchtest und der anonymisierten Analyse zustimmst, werden diese Daten mit Matomo erfasst und auf unseren Servern in Deutschland gespeichert. Matomo ist eine freie und selbstgehostete Alternative zu kommerziellen Anbietern. Wir geben keine Daten an Dritte damit weiter."]]
        :privacy.localstorage/lead "Welche Daten schicke ich an die Server?"
        :privacy.localstorage/title "Datenaustausch"
        :privacy.localstorage/body [:<> [:p "schnaq kann ganz auf Accounts verzichten. Es werden so keine Daten von dir auf unseren Servern gespeichert. Die meiste Interaktion findet über geteilte Links statt. Klicke auf einen Link zu einem schnaq, wird ein Teil des Links (der Hash) in deinem Browser (im LocalStorage) abgespeichert. Besuchst du dann schnaq erneut, schickt dein Browser diesen Hash zurück an uns und erhält so erneut Zugang zum schnaq. Alternativ kannst du dir die Zugangslinks per E-Mail schicken lassen und hältst so alle für den Betrieb notwendigen Daten selbst in der Hand."]
                                    [:p "Im Unterschied zu herkömmlichen Cookies, verwenden wir den LocalStorage, welcher naturgemäß nur die wirklich notwendigen Daten von dir an uns zurückschickt. Schaue selbst nach, welche Daten das genau sind, indem du auf den Button klickst."]]
        :privacy.localstorage/show-data "Deine Daten anzeigen"
        :privacy.localstorage.notification/title "Diese Daten hat dein Browser gespeichert"
        :privacy.localstorage.notification/body "Hinweis: \"Kryptische\" Zeichenketten sind die Zugangscodes zu den schnaqs."
        :privacy.localstorage.notification/confirmation "Möchtest du deine Daten wirklich löschen?"
        :privacy.localstorage.notification/delete-button "Daten löschen"

        :privacy.data-processing.anonymous/lead "Was passiert mit deinen Beiträgen?"
        :privacy.data-processing.anonymous/title "Datenverarbeitung bei anonymen Zugängen"
        :privacy.data-processing.anonymous/body [:<> [:p "Wir speichern die von dir verfassten Beiträge in Kombination mit dem von dir gewählten Nutzernamen auf unserem Server und geben sie nicht an Dritte weiter. Wenn du keinen Nutzer:innennamen eingibst, wird als Autor:in \"Anonymous\" eingetragen. Die von dir verfassten Beiträge stehen in keiner Beziehung zueinander. Da auch wir uns nicht merken, von wem die Beiträge stammen, ist eine Bearbeitung des Beitrags nicht möglich. Es werden keine persönlichen Daten, wie dein Browser oder deine IP-Adresse, mit deinen Beiträgen zusammengeführt."]
                                                 [:p "Beiträge in öffentlichen schnaqs sind von allen Nutzer:innen einsehbar. Beiträge in privaten schnaqs sind nur von Personen einsehbar, die einen Link zur Diskussion haben. Administrator:innen eines schnaqs haben die Möglichkeit Beiträge zu löschen."]]
        :privacy.data-processing.registered/lead "Und wenn ich nun eingeloggt bin?"
        :privacy.data-processing.registered/title "Datenverarbeitung bei registrierten Nutzer:innen"
        :privacy.data-processing.registered/body
        [:<> [:p "Solltest du dich entscheiden dich zu registrieren, so werden E-Mail Adresse und dein Name gespeichert. Damit personalisieren wir dein schnaq-Erlebnis und zeigen deinen Namen an, wenn du einen Beitrag speicherst. Die Mailadresse ist unter anderem für Benachrichtigungen notwendig damit du informiert wirst wenn es neue Beiträge für dich gibt."]
         [:p "Bei einem Login über einen externen Anbieter, wie LinkedIn, erhält LinkedIn von dir eine Anfrage die angezeigten Informationen an uns zu übermitteln, die wir dann bei uns speichern. Loggst du dich erneut ein, so erhält LinkedIn auch wieder eine Anfrage. Möchtest du das vermeiden, so erstelle einfach direkt einen Account bei uns."]
         [:p "Zusätzlich speichern wir in deinem Account die Hubs und schnaqs, zu denen du Zugang hast. Damit kannst dich auch auf deinem Smartphone oder anderem Endgerät einloggen und hast Zugriff auf alle deine schnaqs."]
         [:p "Nun ist es auch möglich erweiterte Funktionen, wie Beiträge editieren, zu verwenden, da du nun eine Identität auf unserer Plattform hast 👍"]
         [:p "Jederzeit kannst du uns kontaktieren und die Einsicht oder Löschung deiner Daten beantragen."]]

        :privacy.link-to-privacy/lead "Mehr Informationen findest du in unserer ausführlichen "
        :privacy/note "Datenschutzerklärung"

        :privacy.extended/heading "Datenschutzerklärung"
        :privacy.extended/subheading "Wir sind DSGVO konform"
        :privacy.extended.intro/title "Allgemeines zur Datenverarbeitung"
        :privacy.extended.intro/body
        [:<>
         [:p "Wir verarbeiten personenbezogene Daten grundsätzlich nur, soweit dies zur Bereitstellung einer funktionsfähigen Website sowie unserer Inhalte erforderlich ist. Die Verarbeitung personenbezogener Daten erfolgt regelmäßig nur nach Einwilligung der Nutzer:innen."]
         [:p "Soweit für Verarbeitungsvorgänge personenbezogener Daten eine Einwilligung notwendig ist, dient Art. 6 Abs. 1 lit. a EU-Datenschutzgrundverordnung (DSGVO) als Rechtsgrundlage.\nIst die Verarbeitung zur Wahrung eines berechtigten Interesses unsererseits oder eines Dritten erforderlich und überwiegen Ihre Interessen, Grundrechte und Grundfreiheiten das erstgenannte Interesse nicht, so dient Art. 6 Abs. 1 lit. f DSGVO als Rechtsgrundlage für die Verarbeitung. "]
         [:p "Personenbezogene Daten werden gelöscht, sobald der Zweck der Speicherung entfällt. Eine Speicherung kann darüber hinaus erfolgen, wenn dies durch den europäischen oder nationalen Gesetzgeber in unionsrechtlichen Verordnungen, Gesetzen oder sonstigen Vorschriften, denen wir unterliegen, vorgesehen wurde. Eine Löschung der Daten erfolgt auch dann, wenn eine durch die genannten Normen vorgeschriebene Speicherfrist abläuft."]]
        :privacy.extended.logfiles/title "Bereitstellung der Website und Erstellung von Logfiles"
        :privacy.extended.logfiles/body
        [:<>
         [:p "Bei jedem Aufruf unserer Internetseite erfasst unser System automatisiert Daten und Informationen (Browsertyp / verwendete Version, Betriebssystem, IP-Adresse, Datum und Uhrzeit des Zugriffs, Websites, von denen auf unsere Internetseite gelangt wurde, Websites, die über unsere Website aufgerufen werden) vom Computersystem des aufrufenden Rechners. Die Daten werden in den Logfiles unseres Systems gespeichert. Eine Speicherung dieser Daten zusammen mit anderen personenbezogenen Daten des Nutzers findet nicht statt. Rechtsgrundlage für die vorübergehende Speicherung der Daten und der Logfiles ist Art. 6 Abs. 1 lit. f DSGVO."]
         [:p "Die vorübergehende Speicherung der IP-Adresse durch das System ist notwendig, um eine Auslieferung der Website an den Rechner der Nutzer:innen zu ermöglichen. Hierfür muss die IP-Adresse für die Dauer der Sitzung gespeichert bleiben. Die Speicherung in Logfiles erfolgt, um die Funktionsfähigkeit der Website sicherzustellen. Zudem dienen uns die Daten zur Optimierung der Website und zur Sicherstellung der Sicherheit unserer informationstechnischen Systeme. In diesen Zwecken liegt auch unser berechtigtes Interesse an der Datenverarbeitung nach Art. 6 Abs. 1 lit. f DSGVO."]
         [:p "Die Daten werden gelöscht, sobald sie für die Erreichung des Zweckes ihrer Erhebung nicht mehr erforderlich sind. Im Falle der Erfassung der Daten zur Bereitstellung der Website ist dies der Fall, wenn die jeweilige Sitzung beendet ist. Im Falle der Speicherung der Daten in Logfiles ist dies nach spätestens sieben Tagen der Fall. Eine darüberhinausgehende Speicherung ist möglich. In diesem Fall werden die IP-Adressen der Nutzer gelöscht oder anonymisiert."]
         [:p "Die Erfassung der Daten zur Bereitstellung der Website und die Speicherung der Daten in Logfiles ist für den Betrieb der Internetseite zwingend erforderlich. Es besteht folglich keine Widerspruchsmöglichkeit."]]
        :privacy.extended.cookies/title "Cookies"
        :privacy.extended.cookies/body
        [:<>
         [:p "Wir setzen auf unserer Homepage sogenannte Cookies ein. Cookies sind Datenpakete, die Ihr Browser auf unsere Veranlassung in Ihrem Endgerät speichert. Dabei werden zwei Arten von Cookies unterschieden: temporäre, sogenannte Session-Cookies, und persistente Cookies."]
         [:p "Session-Cookies werden automatisiert gelöscht, wenn Sie den Browser schließen. Diese speichern eine sogenannte Session-ID, mit welcher sich verschiedene Anfragen Ihres Browsers der gemeinsamen Sitzung zuordnen lassen. Dadurch kann Ihr Rechner wiedererkannt werden, wenn Sie auf unsere Website zurückkehren. Der Einsatz von Session Cookies ist erforderlich, damit wir Ihnen die Webseite zur Verfügung stellen können. Die Rechtsgrundlage für die Verarbeitung Ihrer personenbezogenen Daten unter Verwendung von Session-Cookies ist Art. 6 Abs. 1 lit. f DSGVO."]
         [:p "Persistente Cookies werden automatisiert nach einer vorgegebenen Dauer gelöscht, die sich je nach Cookie unterscheiden kann. Diese Cookies verbleiben für eine vordefinierte Zeit auf Ihrem Endgerät dienen in der Regel dazu, Sie bei einem erneuten Besuch unserer Homepage wiederzuerkennen. Der Einsatz von persistenten Cookies auf unserer Homepage erfolgt auf Rechtsgrundlage des Art. 6 Abs. 1 lit. f DSGVO."]
         [:p "Sie können Ihren Internetbrowser so einstellen, dass unsere Cookies nicht auf Ihrem Endgerät ablegt werden können oder bereits abgelegte Cookies gelöscht werden. Wenn Sie keine Cookies akzeptieren, kann dies zu Einschränkungen der Funktion der Internetseiten führen."]
         [:p "Konkret haben wir diese Arten von Cookies:"]
         [:ul
          [:li "CSRF-Token (Session-Cookie), womit das Kontaktformular vor unbeobachtetem Abschicken von Inhalten abgesichert wird. Es handelt sich hier um eine zufällige Anordnung von Zeichen, welche nur für den Versand des Formulars verwendet wird. Dieser Cookie wird nach dem Verlassen unserer Website gelöscht. Dieser Schutzmechanismus entspricht gängigen Sicherheitsstandards und kann beispielsweise "
           [:a {:href "https://de.wikipedia.org/wiki/Cross-Site-Request-Forgery"}
            "hier"]
           " weiter recherchiert werden."]
          [:li "Login-Cookie (persistenter Cookie), welcher Sie als den:die Benutzer:in wiedererkennt, mit dem Sie sich eingeloggt haben. Nach 14 Tagen läuft Ihr Cookie ab und wird gelöscht. Wenn Sie diesen Cookie löschen, müssen Sie sich beim nächsten Besuch der Seite erneut einloggen."]
          [:li "Analyse-Cookie (persistenter Cookie), welcher optional gesetzt werden kann, damit wir Ihr Verhalten und Ihre Interessen anonymisiert verstehen können. Weiteres dazu finden Sie in dem Abschnitt zur Verwendung von Matomo."]]
         [:p "Alle von uns eingesetzten Cookies generieren zufällige Zeichenketten, die zum Abgleich mit korrespondierenden Zeichenketten auf unserem Server verwendet werden."]]

        :privacy.extended.personal-data/title "Persönliche Daten"
        :privacy.extended.personal-data/body
        [:<>
         [:h4 "Verwendung von schnaq ohne Nutzer:innen Accounts"]
         [:p "Wenn Sie schnaq verwenden ohne sich zu registrieren, sind so sogenannte \"Anonyme Nutzer:in\". Dabei werden zusätzlich zu den für den Serverbetrieb notwendigen Daten nur Ihr Beitrag und ein optionaler selbstgewählter Name abgespeichert werden. Beim Speichern des Beitrags wird dann diese Zeichenkette lose mit dem Beitrag abgesichert. Eine Zuordnung zu einer Identität erfolgt dabei nicht. Nimmt jemand mit demselben Namen an irgendeinem schnaq teil, so erscheinen die Beiträge nach außen hin so als kämen sie von der selben Person."]
         [:p "Mit dem Abschicken Ihres Beitrages stimmen Sie der Speicherung zu. Da wir später nicht mehr nachvollziehen können, von wem der Beitrag stand, haben Sie kein Recht darauf diesen Beitrag zu löschen, denn es fehlt der Nachweis der Autor:innenschaft."]
         [:h4 "Verwendung von schnaq als registrierte:r Nutzer:in"]
         [:p "Bei der Registrierung werden von Ihnen Ihre Mailadresse und Ihr Vor- und Nachname gespeichert. Diese sind für den Betrieb von schnaq erforderlich, die Erfassung erfolgt somit nach Art. 6 Abs. 1 lit. f DSGVO. Die Registrierung ist für den normalen Betrieb von schnaq optional. Mit der Mailadresse werden automatische Benachrichtigungen auf neue Beiträge ermöglicht. Mit den Namen werden Ihre Beiträge auf der Oberfläche von schnaq zusammen dargestellt. Auch weitere Zugehörigkeiten, beispielsweise zu den Hubs oder weiteren schnaqs, werden damit visuell dargestellt."]
         [:p "Diese Daten werden auf unseren eigenen Servern gespeichert und nicht an Dritte weitergegeben."]
         [:p "Es gibt Möglichkeiten das eigene Nutzer:innenprofil zu erweitern. Dazu gehört beispielsweise ein eigenes optionales Profilbild hochzuladen. Dieses Profilbild wird dann als Ihr Avatar dargestellt und immer dann präsentiert, wenn Ihr Nutzer:innenaccount in Erscheinung tritt, beispielsweise wenn man sich Ihre Beiträge anschaut."]
         [:h4 "Textbeiträge"]
         [:p "Die Textbeiträge müssen von Ihnen selbst stammen und dürfen keine Urheberrechte verletzen. Die Textbeiträge werden nicht an Dritte weitergegeben. Intern können Ihre Beiträge für weitere wissenschaftliche Auswertungen und dem Training von eigenen neuronalen Netzen verwendet werden. Sie verlieren dabei niemals Ihre Autor:innenschaft an diesen Beiträgen. Damit werden beispielsweise automatisiert maschinell erstellte Zusammenfassungen oder Statistiken berechnet. Diese Zusammenfassungen und Statistiken sind für die Auswertung Ihres schnaqs vorgesehen und werden an keine Dritten weitergegeben."]]
        :privacy.extended.matomo/title "Webanalyse durch Matomo (ehemals PIWIK)"
        :privacy.extended.matomo/body
        [:<>
         [:h4 "Beschreibung und Umfang der Datenverarbeitung"]
         [:p "Wir nutzen auf unserer Website das Open-Source-Software-Tool Matomo (ehemals PIWIK) zur Analyse der Nutzung unseres Internet-Auftritts. Uns interessiert zum Beispiel, welche Seiten wie häufig aufgerufen werden und ob dabei Smartphones, Tablets oder Rechner mit großen Bildschirmen eingesetzt werden. Die Software setzt einen Cookie auf dem Rechner der Nutzer:innen (zu Cookies siehe bereits oben). Werden Einzelseiten unserer Website aufgerufen, so werden folgende Daten gespeichert:"]
         [:ol
          [:li "Zwei Bytes der IP-Adresse des aufrufenden Systems"]
          [:li "Die aufgerufene Webseite"]
          [:li "Die Website, über die unsere Webseite gelangt aufgerufen wurde (Referrer)"]
          [:li "Die Unterseiten, die von der aufgerufenen Webseite aus aufgerufen werden"]
          [:li "Die Verweildauer auf der Webseite"]
          [:li "Die Häufigkeit des Aufrufs der Webseite"]]
         [:p "Matomo ist so eingestellt, dass die IP-Adressen nicht vollständig gespeichert werden, sondern zwei Bytes der IP-Adresse maskiert werden (Bsp.:  192.168.xxx.xxx). Auf diese Weise ist eine Zuordnung der gekürzten IP-Adresse zum aufrufenden Rechner nicht mehr möglich."]
         [:p "Matomo kommt ausschließlich auf Servern von schnaq zum Einsatz. Eine Speicherung der personenbezogenen Daten der Nutzer:innen findet nur dort statt. Eine Weitergabe der Daten an Dritte erfolgt nicht."]
         [:h4 "Zweck der Datenverarbeitung"]
         [:p "Die Verarbeitung der anonymisierten Daten der Nutzer:innen ermöglicht uns eine Analyse der Nutzung unserer Webseite. Wir sind in durch die Auswertung der gewonnen Daten in der Lage, Informationen über die Nutzung der einzelnen Komponenten unserer Webseite zusammenzustellen. Dies hilft uns dabei unsere Dienste und deren Nutzer:innenfreundlichkeit stetig zu verbessern. Durch die Anonymisierung der IP-Adresse wird dem Interesse der:die Nutzer:in an deren Schutz personenbezogener Daten hinreichend Rechnung getragen."]
         [:p "Es werden keine Profile erstellt, die uns einen tieferen Einblick in das Nutzungsverhalten der einzelnen Nutzer:innen geben würden. Die Auswertung erfolgt ausschließlich anonymisiert und aggregiert, dass kein Schluss auf einzelne Personen zu ziehen ist."]]
        :privacy.extended.rights-of-the-affected/title "Rechte der Betroffenen"
        :privacy.extended.rights-of-the-affected/body
        [:<>
         [:p "Werden von Ihnen personenbezogene Daten verarbeitet, sind Sie Betroffene:r im Sinne der. DSGVO und es stehen Ihnen die im weiteren beschrieben Rechte uns gegenüber zu. Richten Sie Ihr Verlangen bitte, am besten per E-Mail, an den o.g. Verantwortlichen."]
         [:p [:strong "Auskunft:"]
          " Sie haben das Recht, jederzeit von uns unentgeltliche Auskunft sowie Bestätigung über die zu Ihrer Person gespeicherten personenbezogenen Daten und eine Kopie dieser Auskunft zu erhalten."]
         [:p [:strong "Berichtigung:"]
          " Sie haben das Recht auf Berichtigung und/oder Vervollständigung, sofern die verarbeiteten personenbezogenen Daten, die Sie betreffen, unrichtig oder unvollständig sind."]
         [:p [:strong "Einschränkung der Verarbeitung:"]
          " Sie haben das Recht die Einschränkung der Verarbeitung zu verlangen, wenn eine der folgenden Voraussetzungen gegeben ist:"]
         [:ul
          [:li "Die Richtigkeit der personenbezogenen Daten wird von Ihnen bestritten, und zwar für eine Dauer, die es uns ermöglicht, die Richtigkeit der personenbezogenen Daten zu überprüfen. "]
          [:li "Die Verarbeitung ist unrechtmäßig, Sie lehnen die Löschung der personenbezogenen Daten ab und verlangen stattdessen die Einschränkung der Nutzung der personenbezogenen Daten. "]
          [:li "Wir benötigen die personenbezogenen Daten für die Zwecke der Verarbeitung nicht länger, Sie benötigen sie jedoch zur Geltendmachung, Ausübung oder Verteidigung von Rechtsansprüchen. "]
          [:li "Sie haben Widerspruch gegen die Verarbeitung gem. Art. 21 Abs. 1 DSGVO eingelegt und es steht noch nicht fest, ob unsere berechtigten Gründe gegenüber Ihren überwiegen. "]]
         [:p [:strong "Löschung:"]
          " Sie haben das Recht, dass die sie betreffenden personenbezogenen Daten unverzüglich gelöscht werden, sofern einer der folgenden Gründe zutrifft und soweit die Verarbeitung nicht erforderlich ist:"]
         [:ul
          [:li "Die personenbezogenen Daten wurden für solche Zwecke erhoben oder auf sonstige Weise verarbeitet, für welche sie nicht mehr notwendig sind. "]
          [:li "Sie widerrufen Ihre Einwilligung, auf die sich die Verarbeitung stützte und es fehlt an einer anderweitigen Rechtsgrundlage für die Verarbeitung. "]
          [:li "Sie legen gemäß Art. 21 Abs. 1 DSGVO Widerspruch gegen die Verarbeitung ein, und es liegen keine vorrangigen berechtigten Gründe für die Verarbeitung vor, oder Sie legen gemäß Art. 21 Abs. 2 DSGVO Widerspruch gegen die Verarbeitung ein. "]
          [:li "Die personenbezogenen Daten wurden unrechtmäßig verarbeitet. "]
          [:li "Die Löschung der personenbezogenen Daten ist zur Erfüllung einer rechtlichen Verpflichtung nach dem Unionsrecht oder dem Recht der Mitgliedstaaten erforderlich, dem wir unterliegen. "]
          [:li "Die personenbezogenen Daten wurden in Bezug auf angebotene Dienste der Informationsgesellschaft gemäß Art. 8 Abs. 1 DSGVO erhoben. "]]
         [:p [:strong "Datenübertragbarkeit:"]
          " Sie haben das Recht, die Sie betreffenden personenbezogenen Daten, die Sie dem Verantwortlichen bereitgestellt haben, in einem strukturierten, gängigen und maschinenlesbaren Format zu erhalten. Außerdem haben Sie das Recht diese Daten einem anderen Verantwortlichen ohne Behinderung durch den Verantwortlichen, dem die personenbezogenen Daten bereitgestellt wurden, zu übermitteln. In Ausübung dieses Rechts haben Sie ferner das Recht, zu erwirken, dass die Sie betreffenden personenbezogenen Daten direkt von uns einem anderen Verantwortlichen übermittelt werden, soweit dies technisch machbar ist. Freiheiten und Rechte anderer Personen dürfen hierdurch nicht beeinträchtigt werden."]
         [:p [:strong "Widerspruch:"]
          " Sie haben das Recht, jederzeit gegen die Verarbeitung Sie betreffender personenbezogener Daten, die aufgrund von Art. 6 Abs. 1 lit. f DSGVO erfolgt, Widerspruch einzulegen. Wir verarbeiten die personenbezogenen Daten im Falle des Widerspruchs nicht mehr, es sei denn, wir können zwingende schutzwürdige Gründe für die Verarbeitung nachweisen, die gegenüber Ihren Interessen, Rechten und Freiheiten überwiegen, oder die Verarbeitung dient der Geltendmachung, Ausübung oder Verteidigung von Rechtsansprüchen."]
         [:p [:strong "Widerruf der Einwilligung:"]
          " Sie haben das Recht, Ihre datenschutzrechtliche Einwilligungserklärung jederzeit zu widerrufen. Durch den Widerruf der Einwilligung wird die Rechtmäßigkeit der aufgrund der Einwilligung bis zum Widerruf erfolgten Verarbeitung nicht berührt."]]
        :privacy.extended.right-to-complain/title "Recht auf Beschwerde bei einer Aufsichtsbehörde"
        :privacy.extended.right-to-complain/body
        [:<>
         [:p "Unbeschadet eines anderweitigen verwaltungsrechtlichen oder gerichtlichen Rechtsbehelfs steht Ihnen das Recht auf Beschwerde bei einer Aufsichtsbehörde, insbesondere in dem Mitgliedstaat ihres Aufenthaltsorts, zu, wenn Sie der Ansicht sind, dass die Verarbeitung der Sie betreffenden personenbezogenen Daten gegen die DSGVO verstößt.\nDie für den Betreiber dieser Seite zustände Datenschutzaufsichtsbehörde ist:"]
         [:p "Die Landesbeauftragte für Datenschutz und Informationsfreiheit NRW, Kavalleriestr. 2-4, 40102 Düsseldorf, Tel.: +49211/38424-0, E-Mail: poststelle{at}ldi.nrw.de"]]
        :privacy.extended.hosting/title "Hosting der Webseite"
        :privacy.extended.hosting/body
        [:<>
         [:p "Der Internetauftritt von schnaq wird auf Servern der Hetzner Online GmbH in Deutschland gehostet. Bezüglich weiterer Informationen verweisen wir auf die Webseiten der Hetzner Online GmbH."]
         [:h4 "Abschluss eines Auftragsdatenverarbeitungsvertrags (AV-Vertrag)"]
         [:p "Wir haben mit der Hetzner Online GmbH einen AV-Vertrag abgeschlossen, welcher unsere Kunden schützt und Hetzner verpflichtet die erhobenen Daten nicht an Dritte weiterzugeben."]]
        :privacy.extended.responsible/title "Verantwortliche Person"
        :privacy.extended.responsible/body
        [:<>
         [:p
          "schnaq (nicht gegründet)" [:br]
          "vertreten durch Christian Meter" [:br]
          "Am Hagen 6" [:br]
          "42855 Remscheid" [:br]
          (toolbelt/obfuscate-mail "info@schnaq.com")]
         [:p "Rechtlich bindend ist die deutsche Fassung dieser Seite."]]

        ;; About us
        :about-us.unity/title "Die Einheit schnaq"
        :about-us.unity/body [:<> [:p "schnaq bringt digitale Diskussionen in die Zukunft. Wir bieten Unternehmen die Möglichkeit transparente Entscheidungsprozesse durchzuführen, in denen das gesamte Team gehört werden kann, sodass chancengleiche und nachvollziehbare Diskurse stattfinden. Unsere Analysen helfen Ihnen zu verstehen, welches Teammitglied zu wenig gehört wurde und mit einbezogen werden sollte. Durch das Teilen von Wissen über Diskussionen auf unserer Plattform, verhindern wir Wissenssilos und implizites Firmenwissen, indem wir das Firmenwissen für alle verfügbar machen, sei es geschriebene oder später auch gesprochene Kommunikation."]
                              [:p "Unser Team steht dafür ein, dass jede Stimme gehört werden kann!"]]

        :about-us.value/title "Unsere Werte"
        :about-us.value/subtitle "Wir folgen Werten, die unser Handeln und unsere Produkte definieren."
        :about-us.honesty/title "Ehrlichkeit"
        :about-us.honesty/body "Wir setzen darauf, unsere Produkte und deren Fähigkeiten ehrlich und ohne Übertreibung darzustellen. Wir sind fest davon überzeugt, dass unsere Produkte für sich selbst stehen können, ohne jegliche Überhöhung."
        :about-us.collaborate/title "Kollaborationswille"
        :about-us.collaborate/body "Wir glauben fest daran, dass wir gemeinsam mehr erreichen können als alleine. Deshalb pflegen wir gerne eine Kultur der Kollaboration. Egal ob untereinander im Team oder mit unseren Kund:innen und Kooperationspartner:innen. Zusammen können wir Großartiges schaffen."
        :about-us.action/title "Tatendrang"
        :about-us.action/body "Wir treffen Entscheidungen nicht aus dem Blauen heraus, sondern gestützt auf allen Daten, die wir zur Verfügung haben. Aber sobald nach Diskussionen eine Entscheidung getroffen wurde, stehen wir gemeinsam dahinter und ziehen an einem Strang, um effizient vorwärts zu kommen."
        :about-us.quality/title "Qualität"
        :about-us.quality/body "Wir sind stolz auf unsere Arbeit und das, was wir schaffen. Wir mögen unser Werk, wir sehen es als einen Teil von uns und wir haben Spaß daran, Menschen überall auf der Welt miteinander zu verbinden. Deshalb liegt es uns am Herzen, dass unsere Produkte von größtmöglicher Qualität sind."
        :about-us.diversity/title "Vielfalt"
        :about-us.diversity/body "Jeder Mensch bringt seine einzigartige Perspektive auf die Welt mit. Und gerade weil wir Menschen miteinander in Kontakt bringen, wollen wir, dass möglichst viele dieser Perspektiven in unser Schaffen einfließen."

        :about-us.numbers/title "schnaq in Zahlen"
        :about-us.numbers/research "Jahre Forschung"
        :about-us.numbers/users "Nutzer:innen"
        :about-us.numbers/statements "Aussagen strukturiert"
        :about-us.numbers/loc "Zeilen Code"

        :about-us.team/title "Team im Fokus"
        :about-us.team/alexander "Co-Gründer - Operative Führung"
        :about-us.team/christian "Co-Gründer - Technische Führung"
        :about-us.team/mike "Co-Gründer - Führung Produktdesign"
        :about-us.team/philip "Co-Gründer - Chief of Customer Success"

        :about-us.page/heading "Über uns"
        :about-us.page/subheading "Informationen zu uns"

        ;; Legal Note
        :legal-note.page/heading "Impressum"
        :legal-note.page/disclaimer "Haftungsausschluss (Disclaimer)"

        :legal-note.contents/title "Haftung für Inhalte"
        :legal-note.contents/body "Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich. Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte fremde Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung von Informationen nach den allgemeinen Gesetzen bleiben hiervon unberührt. Eine diesbezügliche Haftung ist jedoch erst ab dem Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung möglich. Bei Bekanntwerden von entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen."
        :legal-note.links/title "Haftung für Links"
        :legal-note.links/body "Unser Angebot enthält Links zu externen Webseiten Dritter, auf deren Inhalte wir keinen Einfluss haben. Deshalb können wir für diese fremden Inhalte auch keine Gewähr übernehmen. Für die Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt der Verlinkung auf mögliche Rechtsverstöße überprüft. Rechtswidrige Inhalte waren zum Zeitpunkt der Verlinkung nicht erkennbar. Eine permanente inhaltliche Kontrolle der verlinkten Seiten ist jedoch ohne konkrete Anhaltspunkte einer Rechtsverletzung nicht zumutbar. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Links umgehend entfernen."
        :legal-note.copyright/title "Urheberrecht"
        :legal-note.copyright/body "Die durch die Seitenbetreiber erstellten Inhalte und Werke auf diesen Seiten unterliegen dem deutschen Urheberrecht. Die Vervielfältigung, Bearbeitung, Verbreitung und jede Art der Verwertung außerhalb der Grenzen des Urheberrechtes bedürfen der schriftlichen Zustimmung des jeweiligen Autors bzw. Erstellers. Downloads und Kopien dieser Seite sind nur für den privaten, nicht kommerziellen Gebrauch gestattet. Soweit die Inhalte auf dieser Seite nicht vom Betreiber erstellt wurden, werden die Urheberrechte Dritter beachtet. Insbesondere werden Inhalte Dritter als solche gekennzeichnet. Sollten Sie trotzdem auf eine Urheberrechtsverletzung aufmerksam werden, bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Inhalte umgehend entfernen."
        :legal-note.privacy/title "Datenschutzerklärung"
        :legal-note.privacy/body "Unsere Datenschutzerklärung finden Sie hier."

        ;; Celebrations
        :celebrations.schnaq-filled/title "🎉 Glückwunsch 🎉"
        :celebrations.schnaq-filled/lead "Du hast einen neuen schnaq mit einer ersten Aussage befüllt. Das ist der erste Meilenstein zu einer erfolgreichen Diskussion 💪"
        :celebrations.schnaq-filled/share-now "Teile nun den schnaq mit deinem Team!"
        :celebrations.schnaq-filled/button "Optionen zum Teilen"
        :celebrations.first-schnaq-created/title "Du hast deinen ersten schnaq erstellt 🎈"
        :celebrations.first-schnaq-created/lead "Möchtest du deinen schnaq mit einem Account verbinden? Dann registriere dich mit wenigen Klicks 🚀"

        ;; schnaqs not found
        :schnaqs.not-found/alert-lead "Leider wurden keine schnaqs gefunden, zu denen du Zugriff hast."
        :schnaqs.not-found/alert-body "Lade zu deinem ersten schnaq ein, indem du einen erstellst."

        ;; Admin Center
        :schnaq/educate-on-link-text "Teile den untenstehenden Link mit deinen Kolleg:innen und Freund:innen."
        :schnaq/educate-on-link-text-subtitle "Teilnahme ist für alle, die den Link kennen, möglich!"
        :schnaq/educate-on-edit "Titel ändern oder Agendapunkte editieren?"
        :schnaq/educate-on-admin "Später jederzeit zum Admin-Center zurückkehren!"
        :schnaq.admin/heading "Admin-Center"
        :schnaq.admin/subheading "schnaq: \"%s\""
        :schnaq.admin.edit.link/header "Zugang zum Admin-Center"
        :schnaq.admin.edit.link/primer "Verwaltung ist Arbeit, lass' dir dabei helfen!"
        :schnaq.admin.edit.link/admin "Zugang zum Admin-Center per Mail"
        :schnaq.admin.edit.link/admin-privileges "Editieren und Vorschläge verwalten"
        :schnaq.admin.edit.link.form/label "E-Mail Adresse der Administrator:innen"
        :schnaq.admin.edit.link.form/placeholder "Eine E-Mailadresse eingeben"
        :schnaq.admin.edit.link.form/submit-button "Link verschicken"
        :schnaq.admin.invite/via-link "Link verteilen"
        :schnaq.admin.invite/via-mail "Per E-Mail einladen"
        :schnaq.admin.edit/administrate "schnaq verwalten"
        :schnaq.export/as-text "schnaq als Textdatei runterladen"
        :schnaq.admin/tooltip "Schnaq verwalten"
        :share-link/copy "Zugangslink kopieren"

        :sharing/tooltip "Teilen"
        :sharing.modal/title "Teile deinen schnaq"
        :sharing.modal/lead "Lade dein ganzes Team mit ein, um diesen schnaq mit Wissen zu befüllen"
        :sharing.modal/schnaqqi-help "Befülle den schnaq schonmal mit deinen Ideen. Deine Kolleg:innen finden so einen leichteren Einstieg."

        ;; Discussion Language
        :discussion/create-argument-action "Beitrag hinzufügen"
        :discussion/add-argument-conclusion-placeholder "Das denke ich darüber."
        :discussion/add-premise-supporting "Ich möchte die Aussage unterstützen"
        :discussion/add-premise-against "Ich habe einen Grund dagegen"
        :discussion/add-premise-neutral "Ich möchte etwas ergänzen"
        :discussion.add.button/support "Dafür"
        :discussion.add.button/attack "Dagegen"
        :discussion.add.button/neutral "Neutral"
        :discussion.add.statement/new "Neuer Beitrag von dir"
        :discussion.badges/user-overview "Alle Teilnehmer:innen"
        :discussion.badges/delete-statement "Beitrag löschen"
        :discussion.badges/delete-statement-confirmation "Möchtest du den Beitrag wirklich löschen?"
        :discussion.notification/new-content-title "Neuer Beitrag!"
        :discussion.notification/new-content-body "Dein Beitrag wurde erfolgreich gespeichert."
        :discussion.badges/edit-statement "editieren"
        :discussion.badges/statement-by " von "             ; spaces intended

        :schnaqs/header "Übersicht deiner schnaqs"
        :schnaqs/subheader "Auf diese schnaqs hast du Zugriff"
        :schnaqs.all/header "Öffentliche schnaqs"

        ;; Feedback
        :feedbacks.overview/header "Rückmeldungen"
        :feedbacks.overview/subheader "Alle abgegebenen Rückmeldungen"
        :feedbacks.overview/description "Beschreibung"
        :feedbacks.overview/table-header "Es gibt %s Rückmeldungen 🥳!"
        :feedbacks.overview/when? "Wann?"
        :feedbacks.overview/contact-name "Von"
        :feedbacks.overview/contact-mail "E-Mail"
        :feedbacks/button "Feedback"
        :feedbacks/screenshot "Screenshot"
        :feedbacks.modal/primer "Feedback ist wichtig! Wir freuen uns sehr über
     jede Art von Feedback, je ehrlicher desto besser 🥳 Hinterlasse uns
     gerne einen kleinen Kommentar und hilf uns damit diese Software
     weiter zu verbessern. Dankeschön!"
        :feedbacks.modal/contact-name "Dein Name"
        :feedbacks.modal/contact-mail "E-Mail Adresse"
        :feedbacks.modal/description "Deine Rückmeldung"
        :feedbacks.modal/optional "Optional"
        :feedbacks.modal/screenshot "Foto der Anwendung mit abschicken?"
        :feedbacks.modal/disclaimer "Deine Daten werden nur auf unseren Servern
     abgespeichert und keinen Dritten zugänglich gemacht."
        :feedbacks.notification/title "Vielen Dank für deine Rückmeldung!"
        :feedbacks.notification/body "Dein Feedback wurde erfolgreich an uns
     gesendet 🎉"

        :feedbacks.survey/primer
        [:<> "Wir würden uns freuen, wenn du bei einer
     kleinen Umfrage teilnehmen würdest. Diese wird bei Google Forms gehostet
     und unterliegt den "
         [:a {:href "https://policies.google.com/privacy"} "Datenschutzbestimmungen von Google"]
         ". Mit der Teilnahme an der Umfrage akzeptierst du diesen Datenschutzbestimmungen."]
        :feedbacks.survey/checkbox "Ja, ich möchte an der Umfrage teilnehmen"
        :feedbacks.survey/loading "Formular wird geladen..."
        :feedbacks.survey/tab "Umfrage"

        :feedbacks.missing/heading "Feedbacks wurden noch nicht geladen 😱"
        :feedbacks.missing/button-text "Nachladen"

        ;; Log-in
        :login/as "Hallo, "
        :login/set-name "Gib deinen Namen ein"

        ;; analytics
        :analytics/heading "Analytics"
        :analytics/overall-discussions "Schnaqs erstellt"
        :analytics/user-numbers "Usernamen angelegt"
        :analytics/registered-users-numbers "Registrierte Nutzer:innen"
        :analytics/average-statements-title "Durchschnittliche Zahl an Beiträgen pro Schnaq"
        :analytics/statements-num-title "Anzahl Statements"
        :analytics/active-users-num-title "Aktive User (min. 1 Beitrag)"
        :analytics/statement-lengths-title "Beitragslängen"
        :analytics/statement-types-title "Argumenttypen"
        :analytics/fetch-data-button "Hole Daten"

        ;; Supporters
        :supporters/heading "Unterstützt vom Wirtschaftsministerium des Landes Nordrhein-Westfalen"

        ;; Testimonials
        :testimonials/heading "Was andere über uns denken:"
        :testimonials.doctronic/quote "Wir beobachten die Entwicklung von schnaq mit großem Interesse für den eigenen Einsatz und für den Einsatz bei unseren Kunden."
        :testimonials.doctronic/author "Ingo Küper, Geschäftsführer doctronic GmbH & Co. KG"

        ;; User related
        :user.button/set-name "Name speichern"
        :user.button/set-name-placeholder "Dein Name"
        :user.button/change-name "Namen ändern"
        :user.button/success-body "Name erfolgreich gespeichert"
        :user.set-name.modal/header "Gib einen Namen ein"
        :user.set-name.modal/primer "Der Name wird den anderen Teilnehmer:innen im schnaq angezeigt."
        :user/login "Anmelden"
        :user/logout "Logout"
        :user.profile/settings "Einstellungen"
        :user.profile/star-tooltip "Du bist ein Admin!\n\"Aus großer Kraft folgt große Verantwortung.\""
        :user.action/link-copied "Link kopiert!"
        :user.action/link-copied-body "Teile den Link mit anderen, um ihnen Zugriff zu geben."
        :user/edit-account "Benutzerkonto verwalten"
        :user/edit-hubs "Hubs verwalten"
        :user.settings "Einstellungen"
        :user.keycloak-settings "Erweiterte Einstellungen"
        :user.settings/header "Nutzer:innendaten verwalten"
        :user.settings/info "Persönliche Informationen"
        :user.settings/hubs "Hubs"
        :user.settings/change-name "Namen ändern"
        :user.settings.button/change-account-information "Änderungen speichern"
        :user.settings.profile-picture-title/success "Profilbild erfolgreich gesetzt"
        :user.settings.profile-picture-body/success "Profilbild wurde hochgeladen und gespeichert. Ggf. Seite neuladen um aktualisiertes Bild zu sehen."
        :user.settings.profile-picture-title/error "Hochladen des Profilbildes fehlgeschlagen"
        :user.settings.profile-picture-too-large/error "Dein Profilbild ist %d Bytes groß, die maximal erlaubte Größe ist %d Bytes. Bitte lade ein kleineres Bild hoch."
        :user.settings.profile-picture.errors/scaling "Dein Profilbild konnte nicht konvertiert werden. Vielleicht ist das Bild kaputt. Bitte probiere ein anderes Bild oder kontaktiere uns."
        :user.settings.profile-picture.errors/invalid-file-type "Dein Profilbild hat den falschen Dateitypen. Erlaubt sind: %s"
        :user.settings.profile-picture.errors/default "Etwas ist beim Hochladen des Bildes schief gelaufen. Bitte versuche es erneut."

        ;; Errors
        :errors/navigate-to-startpage "Zurück zur Startseite"
        :errors/generic "Es ist ein Fehler aufgetreten"

        :error.generic/contact-us [:span "Solltest du hier landen nachdem du etwas auf schnaq.com angeklickt hast, gib uns gerne Bescheid unter " [:a {:href "mailto:info@schnaq.com"} "info@schnaq.com"]]

        :error.404/heading "Diese Seite existiert nicht 🙉"
        :error.404/body "Die URL, der du gefolgt bist, existiert leider nicht. Möglicherweise hat sich ein Tippfehler
     oder ein Zeichen zu viel eingeschlichen."

        :error.403/heading "Du hast nicht die Berechtigung diese Seite aufzurufen 🧙‍♂️"
        :error.403/body "Dir fehlt die Berechtigung diese Seite aufzurufen oder es handelt sich um einen Tippfehler in deiner URL."

        :error.beta/heading "Du hast nicht die Berechtigung diese Seite aufzurufen 🧙‍♂️"
        :error.beta/body "Dieses Feature ist nur für Beta-Tester:innen verfügbar. Wenn du zu den Tester:innen gehörst, melde dich bitte an. Wenn du Beta-Tester:in werden möchtest, dann schreibe uns eine E-Mail an hello@schnaq.com."

        ;; Graph Texts
        :graph/heading "Diskussionsübersicht"
        :graph.button/text "Mindmap"
        :graph.download/as-png "Mindmap als Bild herunterladen"
        :graph.settings/title "Einstellungen für die Mindmap"
        :graph.settings/description "Finde hier Einstellungen für deine Mindmap! Spiele mit den Slidern herum und schau was passiert."
        :graph.settings.gravity/label "Stelle hier die Gravitation zwischen den Knoten ein."
        :graph.settings/stabilize "Stabilisiere Mindmap"

        ;; Pricing Page
        :pricing.free-tier/description "Für kleine Teams und private Zwecke. Der Starter Plan ist der
     perfekte Einstieg in strukturierte Wissensgenerierung."
        :pricing.free-tier/beta-notice "Nach der Beta-Phase ist der Plan weiterhin verfügbar für bis zu 5 Nutzer:innen pro Team"
        :pricing.free-tier/call-to-action "Kostenfrei loslegen"
        :pricing.business-tier/description "Ob 10 oder 50 Nutzer:innen – der Preis ist der gleiche.
      Eignet sich für Unternehmen, Vereine, Bildungsinstitutionen und alle,
      die strukturiert Wissen sammeln möchten."
        :pricing.units/per-month "/ Monat"
        :pricing.notes/with-vat "zzgl. MwSt."
        :pricing.notes/yearly-rebate "Bei jährlicher Zahlweise im Voraus 15% Rabatt"
        :pricing.business-tier/call-to-action "Verfügbar ab 01.01.2021"
        :pricing.trial/call-to-action "30 Tage Business testen"
        :pricing.trial/description "Keine Kreditkarte nötig! Jederzeit kündbar."
        :pricing.trial.temporary/deactivation "Verfügbar ab 01.01.2021"
        :pricing.features/heading "Schnaq-Abonnement Vorteile"
        :pricing.features.user-numbers/heading "Unbegrenzte Teilnehmer:innen"
        :pricing.features.user-numbers/content "Lass so viele Mitarbeiter:innen, wie du möchtest, kooperieren. *"
        :pricing.features.team-numbers/heading "Unbegrenzte Teams"
        :pricing.features.team-numbers/content "Die Anzahl der Teams, die du erstellen kannst, ist unlimitiert. *"
        :pricing.features.app-integration/heading "App-Integration"
        :pricing.features.app-integration/content "Verknüpfe schnaq leicht mit deinem Slack, MS Teams, Confluence …"
        :pricing.features.analysis/heading "Automatische Analysen"
        :pricing.features.analysis/content "Die Beiträge werden automatisch analysiert und für alle Teilnehmer:innen aufbereitet."
        :pricing.features.knowledge-db/heading "Wissensdatenbank"
        :pricing.features.knowledge-db/content "Sammle erarbeitetes Wissen und Ideen an einem Ort."
        :pricing.features.mindmap/heading "Interaktive Mindmap"
        :pricing.features.mindmap/content "Alle Beiträge werden automatisch graphisch und interaktiv dargestellt."
        :pricing.features/disclaimer "* Gilt nur für Business-Abonnement"
        :pricing.competitors/per-month-per-user " € pro Monat pro Nutzer:in"
        :pricing.comparison/heading "Ihr wachst weiter – ihr spart mehr!"
        :pricing.comparison/subheading "Egal wie groß dein Team wird, der Preis bleibt der Gleiche.
   So schlägt sich der Preis von schnaq im Vergleich zu Miro + Loomio + Confluence im Paket."
        :pricing.comparison.schnaq/price-point "79 € pro Monat für dein Unternehmen"
        :pricing.comparison.schnaq/brainstorm "Brainstorming"
        :pricing.comparison.schnaq/decision-making "Entscheidungsfindung"
        :pricing.comparison.schnaq/knowledge-db "Wissensdatenbank"
        :pricing.comparison.schnaq/async "Asynchrone Kommunikation"
        :pricing.comparison.schnaq/mindmap "Mindmapping"
        :pricing.comparison.schnaq/analysis "Ergebnisanalyse"
        :pricing.comparison.schnaq/flatrate " Flatrate im Monat"
        :pricing.comparison.schnaq/person-20 "79 € für 20 Personen"
        :pricing.comparison.schnaq/person-50 "79 € für 50 Personen"
        :pricing.comparison.schnaq/person-100 "79 € für 100 Personen …"
        :pricing.comparison/compared-to [:span "Verglichen" [:br] "mit"]
        :pricing.comparison.miro/description "Brainstorming Software"
        :pricing.comparison.loomio/description "Kooperative Entscheidungsfindung"
        :pricing.comparison.confluence/description "Wissensdatenbank"
        :pricing.comparison.competitor/person-10 " im Monat für 10 Personen"
        :pricing.comparison.competitor/person-20 "247 € für 20 Personen"
        :pricing.comparison.competitor/person-50 "685 € für 50 Personen"
        :pricing.comparison.competitor/person-100 "1370 € für 100 Personen …"
        :pricing.faq/heading "Häufig gestellte Fragen zu schnaq Abos"
        :pricing.faq.terminate/heading "Kann ich jederzeit kündigen?"
        :pricing.faq.terminate/body
        [:<> [:span.text-primary "Ja! "] " Du kannst" [:span.text-primary " jeden Monat"] " kündigen,
     wenn du die monatliche Zahlweise gewählt hast. Wenn du die jährliche Zahlweise
     wählst, kannst du zum Ablauf des Abonnementjahres kündigen."]
        :pricing.faq.extra-price/heading "Muss ich für mehr Leute extra bezahlen?"
        :pricing.faq.extra-price/body
        [:<> [:span.text-primary "Nein, "] "du kannst" [:span.text-primary " beliebig viele Personen "]
         " zu deiner Organisation hinzufügen. Jedes Unternehmen, Verein,
         Bildungseinrichtung, usw. braucht " [:span.text-primary "nur ein Abonnement."]]
        :pricing.faq.trial-time/heading "Verlängert sich der Testzeitraum automatisch?"
        :pricing.faq.trial-time/body
        [:<> [:span.text-primary "Nein, "] "wenn dein Testzeitraum endet, kannst du" [:span.text-primary " aktiv entscheiden"]
         ", ob du Zahlungsdaten hinzufügen und weiter den Business-Tarif nutzen möchtest.
         Der " [:span.text-primary "Starter Plan bleibt unbegrenzt kostenfrei"] ", auch nach dem Testzeitraum."]
        :pricing.faq.longer-trial/heading "Kann ich den Business-Tarif länger testen?"
        :pricing.faq.longer-trial/body
        [:<> [:span.text-primary "Ja! "] "Schreibe uns einfach eine " [:span.text-primary " E-Mail"] " an "
         [:a {:href "mailto:info@schnaq.com"} "info@schnaq.com."]]
        :pricing.faq.privacy/heading "Wer hat Zugriff auf meine Daten?"
        :pricing.faq.privacy/body-1
        [:<> "Jede Person, die du deinem Unternehmen hinzufügst, kann potentiell auf die hinterlegten Daten zugreifen."
         "Technisch werden deine Daten vollständig sicher auf"
         [:span.text-primary " deutschen Servern und DSGVO konform"] " abgespeichert. Auf unserer "]
        :pricing.faq.privacy/body-2 "Seite zur Datensicherheit"
        :pricing.faq.privacy/body-3 " findest du mehr Informationen"
        :pricing/headline "Schnaq Abonnement"
        :pricing.newsletter/lead "Werde sofort informiert, wenn das Abonnement live geht: "
        :pricing.newsletter/name "schnaq Newsletter."

        ;; feature list
        :feature/what "Entscheidungen werden nachvollziehbarer und informierter"
        :feature/share "Alle können gleichberechtigt ihr Wissen einbringen"
        :feature/graph "Relevantes Wissen wird automatisch in eine Mindmap überführt"
        :feature/processing "Informationen sind einfach wiederzufinden"
        :feature/secure "Daten werden nach deutschen Standards geschützt"
        :schnaq.startpage.cta/button "Mit Kolleg:innen diskutieren"

        ;; Tooltips
        :tooltip/history-statement "Zurück zum Beitrag von "
        :tooltip/history-statement-current "Aktueller Beitrag"

        ;; History
        :history/title "Verlauf"
        :history.home/text "Start"
        :history.home/tooltip "Zurück zum Diskussionsanfang"
        :history.statement/user "Beitrag von "
        :history.all-schnaqs/text "Übersicht"
        :history.all-schnaqs/tooltip "Zurück zur Übersicht der schnaqs"
        :history.back/text "Zurück"
        :history.back/tooltip "Zurück zum vorherigen Beitrag"

        ;; Route Link Texts
        :router.features/discussion "Diskussionsfeatures"
        :router/admin-center "Admin-Center"
        :router/all-feedbacks "Alle Feedbacks"
        :router/analytics "Analyse-Dashboard"
        :router/continue-discussion "Führe Besprechung fort"
        :router/create-schnaq "Schnaq anlegen"
        :router/graph-view "Graph View"
        :router/how-to "Wie benutze ich schnaq?"
        :router/invalid-link "Fehlerseite"
        :router/last-added-schnaq "Zuletzt angelegter schnaq"
        :router/visited-schnaqs "Besuchte schnaqs"
        :router/not-found-label "Not Found route redirect"
        :router/pricing "Preise"
        :router/privacy "Datenschutz"
        :router/show-schnaq "Schnaq anzeigen"
        :router/start-discussion "Starte Besprechung"
        :router/startpage "Startseite"
        :router/true-404-view "404 Fehlerseite"
        :router/public-discussions "Öffentliche schnaqs"
        :router/code-of-conduct "Verhaltensregeln"
        :router/summaries "Zusammenfassungen"

        :admin.center.start/title "Admin Center"
        :admin.center.start/heading "Admin Center"
        :admin.center.start/subheading "Administration von schnaqs als Superuser"
        :admin.center.delete/confirmation "Soll dieses schnaq wirklich gelöscht werden?"
        :admin.center.delete.public/label "Öffentliche schnaqs"
        :admin.center.delete.public/button "Schnaq löschen"
        :admin.center.delete/heading "Löschen"
        :admin.center.delete.public/heading "Öffentliche schnaqs"
        :admin.center.delete.private/label "Share-hash"
        :admin.center.delete.private/heading "Private schnaqs"

        :badges.sort/sort "Sortierung"
        :badges.sort/newest "Neueste"
        :badges.sort/popular "Beliebt"
        :badges.sort/alphabetical "Alphabetisch"

        :loading.placeholder/lead "Daten werden geladen..."
        :loading.placeholder/takes-too-long "Das dauert länger als gedacht. Vielleicht ist etwas schiefgelaufen. Versuche die Seite neu zu laden oder den Prozess noch einmal zu wiederholen. Sollte es weiterhin zu Problemen kommen, dann melde dich bei uns!"

        :hubs/heading "Deine Hubs"
        :hub/heading "Persönlicher %s Hub"
        :hub/settings "Verwaltung"
        :hub.settings/change-name "Name des Hubs ändern"
        :hub.settings.name/updated-title "Hub Namensänderung"
        :hub.settings.name/updated-body "Der Name des Hubs wurde erfolgreich verändert!"
        :hub.settings/save "Einstellungen speichern"
        :hub.add.schnaq.success/title "Schnaq hinzugefügt!"
        :hub.add.schnaq.success/body "Der schnaq wurde deinem Hub erfolgreich hinzugefügt."
        :hub.add.schnaq.error/title "Fehler beim Hinzufügen!"
        :hub.add.schnaq.error/body "Der schnaq konnte nicht gefunden oder hinzugefügt werden. Bitte versuche es noch einmal."
        :hub.add.schnaq.input/label "schnaq hinzufügen"
        :hub.add.schnaq.input/placeholder "Schnaq-URL z.B. https://schnaq.com/schnaq/… oder Teil-Code"
        :hub.add.schnaq.input/button "schnaq hinzufügen"
        :hub.remove.schnaq.success/title "schnaq entfernt!"
        :hub.remove.schnaq.success/body "Der schnaq wurde erfolgreich aus deinem Hub entfernt."
        :hub.remove.schnaq.error/title "Entfernen fehlgeschlagen!"
        :hub.remove.schnaq.error/body "Etwas ist beim Entfernen schiefgelaufen. Bitte versuche es erneut."
        :hub.remove.schnaq/prompt "Soll der schnaq wirklich aus dem Hub entfernt werden?"
        :hub.remove.schnaq/tooltip "Schnaq aus Hub entfernen"
        :hub.members/heading "Mitglieder"

        :hub.members.add.result.success/title "Erfolg"
        :hub.members.add.result.success/body "Nutzer:in wurde erfolgreich zum Hub hinzugefügt"
        :hub.members.add.result.error/title "Fehler"
        :hub.members.add.result.error/unregistered-user "Unter der gesuchten E-Mail-Adresse existiert kein schnaq Account"
        :hub.members.add.result.error/generic-error "Etwas ist schief gelaufen. Überprüfe die E-Mail und versuche es noch einmal."
        :hub.members.add.form/title "Mitglieder hinzufügen"
        :hub.members.add.form/button "Nutzer:in hinzufügen!"

        :schnaq.search/heading "Suchergebnisse für: \"%s\""
        :schnaq.search/title "Suche"
        :schnaq.search/input "Suche nach…"
        :schnaq.search/new-search-title "Es gibt keine Ergebnisse. Starte eine neue Suche."

        :lead-magnet.privacy/consent "Ich möchte die Checkliste für datenschutzkonformes Arbeiten als .pdf Datei per E-Mail erhalten und mich hiermit zum schnaq Newsletter anmelden, um in Zukunft regelmäßig Informationen von schnaq.com erhalten."
        :lead-magnet.form/button "Schicke mir die Checkliste zu!"
        :lead-magnet/heading "Datenschutzkonform verteilt arbeiten"
        :lead-magnet/subheading "Eine handliche Checkliste, um in allen Bereichen gerüstet zu sein"
        :lead-magnet.cover/alt-text "Das Cover von der Checkliste zum Datenschutzrechte verteilten Arbeiten"
        :lead-magnet.form/label "Wir versenden den Downloadlink zum PDF per E-Mail"
        :lead-magnet.requested/part-1 "Dein erster Schritt Richtung datenschutzkonformes Arbeiten ist getan!"
        :lead-magnet.requested/part-2 "Du solltest den Downloadlink in einigen Minuten erhalten. Schaue auch im Spam-Ordner nach."
        :lead-magnet.cta/button "Direkt zur Checkliste"
        :lead-magnet.explain.what/heading "Was ist die Checkliste für datenschutzkonforme Remote-Arbeit?"
        :lead-magnet.explain.what/text "Wir haben aktuelle Software für Remote-Arbeit auf die Beachtung des Datenschutzes (im speziellen DSGVO) untersucht.
        Die Ergebnisse sind in einer Checkliste zusammengefasst. Enthalten sind dabei alle wichtigen Kategorien, die man für flexible Arbeit benötigt.
        So kannst du, egal ob beim Homeoffice, bei der mobilen Arbeit oder bei deinem hybriden Arbeitsplatz, auf den Datenschutz achten."
        :lead-magnet.explain.how/heading "Wie funktioniert die Checkliste?"
        :lead-magnet.explain.how/text "Die Checkliste ist nach Kategorien aufgebaut. Zu jeder Kategorie, wie z.B. Chat-Plattformen, finden sich mehrere Alternativen.
        Jede Alternative ist mit einer Ampelfarbe markiert. Grün zeigt dabei die Unbedenklichkeit zum Zeitpunkt unseres Tests. Während Rot vor
        möglichen Problemen warnt. Orange hat schließlich oft kleinere Probleme, ist aber grundsätzlich in Ordnung.
        Neben den Ampelfarben finden sich auch noch erklärende Stichpunkte zur weiteren Recherche."

        :summary.link.button/text "Zusammenfassung"
        :summary.user.request-succeeded/label "Zusammenfassung angefordert. Bitte warte kurz."
        :summary.user.requested/label "Zusammenfassung wird angefordert …"
        :summary.user.not-requested/label "Zusammenfassung anfordern"
        :summary.user/privacy-warning "Diese Funktion befindet sich noch im Beta-Stadium. Zu Zwecken der Verbesserung der Funktion, werden menschliche Mitarbeiter:innen des schnaq-Teams Inhalte der Zusammenfassung vertraulich einsehen und überprüfen können, wenn diese erstellt wird."
        :summary.user.status/label "Eine Zusammenfassung wird gerade erstellt. Letzte Anfrage: "
        :summary.user/cta "Drücke den Knopf um eine Zusammenfassung anzufragen. Der Prozess dauert unter Umständen einige Stunden. Die Zusammenfassung wird hier auftauchen, wenn sie fertig ist."
        :summary.user/label "Zusammenfassung: "
        :summary.user/last-updated "Zuletzt aktualisiert: "
        :summary.user/heading "Zusammenfassungen"
        :summary.user/subheading "Schau dir die Diskussion in wenigen Sätzen an."
        :summary.admin/open-summaries "Offene Zusammenfassungen: %s"
        :summary.admin/closed-summaries "Geschlossene Zusammenfassungen: %s"
        :summary.admin/discussion "Diskussion"
        :summary.admin/requested-at "Angefragt am"
        :summary.admin/summary "Zusammenfassung"
        :summary.admin/submit "Abschicken"
        :summary.admin/closed-at "Geschlossen am"}})

(defn labels
  "Get a localized resource for the requested key. Returns either a string or a hiccup
  element. Optionally tempura parameters can be passed."
  [resource-key & params]
  (let [default-lang :de]
    (tr {:dict translations} [@user-language default-lang] [resource-key] (vec params))))

(defn img-path
  "Returns an image path as String for a given identifier"
  [identifier]
  (identifier
    {:founders-note "https://s3.disqtec.com/startpage/founders_letter.png"
     :how-to/taskbar "https://s3.disqtec.com/schnaq-common/howto/taskbar.svg"
     :icon-add "https://s3.disqtec.com/schnaq-common/buttons/add-button.svg"
     :icon-community "https://s3.disqtec.com/schnaq-common/community.svg"
     :icon-crane "https://s3.disqtec.com/schnaq-common/icons/crane.svg"
     :icon-graph "https://s3.disqtec.com/schnaq-common/icons/graph.svg"
     :icon-reports "https://s3.disqtec.com/schnaq-common/icons/reports.svg"
     :icon-robot "https://s3.disqtec.com/schnaq-common/icons/robot.svg"
     :logo "https://s3.disqtec.com/schnaq-common/logos/schnaq.svg"
     :logo-white "https://s3.disqtec.com/schnaq-common/logos/schnaq_white.png"
     :logos/digihub "https://s3.disqtec.com/schnaq-common/logos/digihub_logo.png"
     :logos/doctronic "https://s3.disqtec.com/schnaq-common/logos/doctronic_logo.png"
     :logos/ignition "https://s3.disqtec.com/schnaq-common/logos/ignition_logo.png"
     :logos/hetzner "https://s3.disqtec.com/schnaq-common/logos/logo-hetzner.svg"
     :press-kit/fact-sheet "https://s3.disqtec.com/schnaq-presskit/factsheet_preview.png"
     :press-kit/logo "https://s3.disqtec.com/schnaq-presskit/logo_card.png"
     :press-kit/product "https://s3.disqtec.com/schnaq-presskit/schnaq_liste_auf_tablet.jpg"
     :press-kit/team "https://s3.disqtec.com/schnaq-presskit/team.jpg"
     :pricing.others/confluence "https://s3.disqtec.com/schnaq-common/startpage/pricing/confluence.jpeg"
     :pricing.others/loomio "https://s3.disqtec.com/schnaq-common/startpage/pricing/loomio.png"
     :pricing.others/miro "https://s3.disqtec.com/schnaq-common/startpage/pricing/miro.png"
     :schnaqqifant/admin "https://s3.disqtec.com/schnaq-schnaqqifanten/admin.png"
     :schnaqqifant/erase "https://s3.disqtec.com/schnaq-schnaqqifanten/erase.png"
     :schnaqqifant/hippie "https://s3.disqtec.com/schnaq-schnaqqifanten/schnaqqifant-hippie.png"
     :schnaqqifant/original "https://s3.disqtec.com/schnaq-common/logos/schnaqqifant.svg"
     :schnaqqifant/police "https://s3.disqtec.com/schnaq-schnaqqifanten/schnaqqifant-polizei.png"
     :schnaqqifant/share "https://s3.disqtec.com/schnaq-schnaqqifanten/share.png"
     :schnaqqifant/stop "https://s3.disqtec.com/schnaq-schnaqqifanten/stop.png"
     :schnaqqifant/talk "https://s3.disqtec.com/schnaq-schnaqqifanten/talk.png"
     :schnaqqifant.300w/talk "https://s3.disqtec.com/schnaq-schnaqqifanten/talk300w.png"
     :schnaqqifant/white "https://s3.disqtec.com/schnaq-common/logos/schnaqqifant_white.svg"
     :schnaqqifant/mail "https://s3.disqtec.com/schnaq-schnaqqifanten/schnaqqi_newsletter.png"
     :stock/team "https://s3.disqtec.com/startpage/team.jpeg"
     :startpage.features/admin-center "https://s3.disqtec.com/startpage/features/admin-center.png"
     :team/alexander "https://s3.disqtec.com/team/alexanderschneider.jpg"
     :team/at-table-with-laptop "https://s3.disqtec.com/team/team_hinter_laptop_am_tisch.jpg"
     :team/vision-mindmap-team "https://s3.disqtec.com/team/vision_mindmap_team.jpg"
     :team/christian "https://s3.disqtec.com/team/christianmeter.jpg"
     :team/mike "https://s3.disqtec.com/team/michaelbirkhoff.jpg"
     :team/philip "https://s3.disqtec.com/team/philipbernardy.jpg"
     :lead-magnet/cover "https://s3.disqtec.com/downloads/checkliste_cover.png"}))

(defn video
  "Returns an video path"
  [identifier]
  (identifier
    {:animation-discussion/webm "https://s3.disqtec.com/schnaq-how-to/animation_discussion.webm"
     :animation-discussion/mp4 "https://s3.disqtec.com/schnaq-how-to/animation_discussion.mp4"
     :celebration.schnaqqi/webm "https://s3.disqtec.com/schnaq-common/celebration/celebration_confetti.webm"
     :celebration.schnaqqi/mp4 "https://s3.disqtec.com/schnaq-common/celebration/celebration_confetti.mp4"
     :how-to.admin/webm "https://s3.disqtec.com/schnaq-how-to/admin.webm"
     :how-to.admin/mp4 "https://s3.disqtec.com/schnaq-how-to/admin.mp4"
     :how-to.create/webm "https://s3.disqtec.com/schnaq-how-to/create.webm"
     :how-to.create/mp4 "https://s3.disqtec.com/schnaq-how-to/create.mp4"
     :how-to.discussion/webm "https://s3.disqtec.com/schnaq-how-to/discussion.webm"
     :how-to.discussion/mp4 "https://s3.disqtec.com/schnaq-how-to/discussion.mp4"
     :how-to.pro-con/webm "https://s3.disqtec.com/schnaq-how-to/discussion-2.webm"
     :how-to.pro-con/mp4 "https://s3.disqtec.com/schnaq-how-to/discussion-2.mp4"
     :how-to.why/webm "https://s3.disqtec.com/schnaq-how-to/why.webm"
     :how-to.why/mp4 "https://s3.disqtec.com/schnaq-how-to/why.mp4"
     :start-page.work-together/webm "https://s3.disqtec.com/schnaq-how-to/WorkTogether.webm"
     :start-page.work-together/mp4 "https://s3.disqtec.com/schnaq-how-to/WorkTogether.mp4"}))

(defn fa
  "Returns an fontawesome icon id as String for a given identifier"
  [identifier]
  (identifier
    {:add "fa-plus-circle"
     :arrow-down "fa-arrow-down"
     :arrow-left "fa-arrow-left"
     :arrow-right "fa-arrow-right"
     :arrow-up "fa-arrow-up"
     :calendar "fa-calendar-plus"
     :camera "fa-camera"
     :carry "fa-people-carry"
     :check/double "fa-check-double"
     :check/normal "fa-check"
     :check/square "fa-check-square"
     :check/circle "fa-check-circle"
     :clipboard "fa-clipboard-list"
     :clock "fa-clock"
     :circle "fa-circle"
     :cog "fa-cog"
     :cogs "fa-cogs"
     :comment "fa-comments"
     :comment-alt "fa-comment-alt"
     :cookie/bite "fa-cookie-bite"
     :cookie/complete "fa-cookie"
     :copy "fa-copy"
     :cross "fa-times"
     :delete-icon "fa-times-circle"
     :edit "fa-edit"
     :eraser "fa-eraser"
     :file-download "fa-file-download"
     :flag "fa-flag"
     :flask "fa-flask"
     :graph "fa-project-diagram"
     :heart "fa-heart"
     :home "fa-home"
     :info "fa-question-circle"
     :language "fa-language"
     :laptop "fa-laptop-code"
     :lock-open "fa-lock-open"
     :minus "fa-minus"
     :newspaper "fa-newspaper"
     :plane "fa-paper-plane"
     :plus "fa-plus"
     :project/diagram "fa-project-diagram"
     :reply "fa-comment-dots"
     :search "fa-search"
     :server "fa-server"
     :share "fa-share-alt"
     :shield "fa-shield-alt"
     :site-map "fa-sitemap"
     :spinner "fa-spinner"
     :star "fa-star"
     :terminal "fa-terminal"
     :text-width "fa-text-width"
     :trash "fa-trash-alt"
     :user/group "fa-users"
     :user/group-edit "fa-users-cog"
     :user/lock "fa-user-lock"
     :user/edit "fa-user-edit"
     :user/ninja "fa-user-ninja"
     :user/plus "fa-user-plus"
     :user/shield "fa-user-shield"}))

(defn colors
  "Color definitions according to our css styles."
  [identifier]
  (identifier
    {:blue/dark "#052740"
     :blue/default "#1292ee"
     :blue/light "#4cacf4"
     :blue/selected "#0181dd"
     :orange/default "#ff772d"
     :orange/selected "#fe661e"
     :gray/dark "#adb5bd"
     :gray/medium "#adb5bd"
     :white "#ffffff"}))