(ns templates.layout
  (use [hiccup core page]
       [templates helpers top-menu resource-menu segment-list single-segment]))

(defn includes []
  [:head
    (include-css "/semantic.css" "/style.css")])


(defn layout [all-resources]
  [:div#app
   (includes)
   (top-menu ["Discard" "Save"])
   (resource-menu all-resources "jobs/")
   (segment-list ["WooEvents" "Buku" "Guide!"])
   (single-segment "Guide!")])
