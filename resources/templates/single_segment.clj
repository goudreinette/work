(ns templates.single-segment)

(defn single-segment [header & contents]
  [:main
    [:div.ui.padded.segment.single [:h3.ui.dividing.header header]
      contents]])
