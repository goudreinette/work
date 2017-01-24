(ns templates.layout
  (use [hiccup core page]
       [templates helpers top-menu resource-menu segment-list single-segment]))

(defn includes []
  [:head
    (include-css "/semantic.css" "/style.css")])


(defn layout [all-resources items current-uri]
  (println current-uri)
  (html
    [:div#app
     (includes)
     (top-menu ["Discard" "Save"])
     (resource-menu all-resources current-uri)
     (segment-list items)
     (single-segment "Guide!")]))
