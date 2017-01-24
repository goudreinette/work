(ns templates.layout
  (use [hiccup core page]
       [templates helpers top-menu resource-menu list single]))

(defn includes []
  [:head
    (include-css "/semantic.css" "/style.css")
    (include-js "https://code.jquery.com/jquery-3.1.1.min.js" "/selection.js")])


(defn layout [all-resources current-resource items]
  (html
    [:div#app
     (includes)
     (top-menu ["Discard" "Save"])
     (resource-menu all-resources (current-resource :link))
     (item-list current-resource items)
     (all current-resource items)]))
