(ns templates.segment-list)


(defn segment-list [items]
  [:div.list
    (for [label items]
      [:div.ui.vertical.padded.segment [:p label]])])
