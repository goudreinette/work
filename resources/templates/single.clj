(ns templates.single)

(defn to-single [{k :display-key} item]
  {:header   (item k)
   :others   (dissoc item k)})


(defn single [labels header others]
  [:div.ui.padded.stacked.segment.single.hidden
    [:h3.ui.dividing.header header]
    (for [[k v] (select-keys others (keys labels))]
      [:p [:strong.key (labels k)]
          [:span.value v]])])

(defn all [resource all]
  [:main
    (for [{h :header o :others} (map #(to-single resource %) all)]
      (single (resource :labels) h o))])
