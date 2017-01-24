(ns templates.list)


(defn item-list [{lk :list-key} items]
  [:div.list
    (for [label (map lk items)]
      [:div.ui.vertical.padded.segment [:p label]])])
