(ns templates.layout
  (use [hiccup core page]
       [templates helpers top-menu resource-menu segment-list single-segment]))

(defn includes []
  [:head
    (include-css "/semantic.css" "/style.css")])


(defn layout [all-resources current-resource items]
  (html
    [:div#app
     (includes)
     (top-menu ["Discard" "Save"])
     (resource-menu all-resources (current-resource :link))
     (segment-list (map (current-resource :list-key) items))
     (single-segment "Guide!")]))
