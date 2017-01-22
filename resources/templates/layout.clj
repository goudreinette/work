(ns templates.layout
  (use [hiccup core page]
       [templates helpers top-menu resource-menu segment-list]))

(defn includes []
  [:head
    (include-css "/semantic.css" "/style.css")])


(defn layout []
  [:div#app
   (includes)
   (top-menu ["Discard" "Save"])
   (resource-menu)
   (segment-list ["WooEvents" "Buku" "Guide!"])
   (single-segment "Guide!")])
