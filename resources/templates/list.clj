(ns templates.list)


(defn item-list [{k :display-key} items]
  [:div.list
    (for [label (map k items)]
      [:div.ui.vertical.padded.segment [:p label]])])
