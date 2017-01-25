(ns templates.layout
  (use [hiccup core page]
       [templates helpers timer top-menu resource-menu list single]))

(defn includes []
  [:head
    (include-css "https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.7/semantic.css" "/style.css")
    (include-js "https://code.jquery.com/jquery-3.1.1.min.js" "/selection.js")])


(defn layout [all-resources current-resource items]
  (html
    (timer items) ; separate me
    [:div#app
     (includes)
     (top-menu ["Save" "Discard" "Edit"])
     (resource-menu all-resources (current-resource :link))
     (item-list current-resource items)
     (all current-resource items)]))
