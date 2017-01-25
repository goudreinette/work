(ns templates.single
  (use joy.macros))

(defn to-single [{k :display-key} item]
  {:header   (item k)
   :others   (dissoc item k)})

(defn stack-class [item-count]
  (condf item-count
    (> 5) "tall stacked"
    (> 1) "stacked"
    (= 1) ""))

(defn single [labels header others item-count]
  [:div.ui.padded.segment.single.hidden {:class (stack-class item-count)}
    [:h3.ui.dividing.header header]
    (for [[k v] (select-keys others (keys labels))]
      [:p [:strong.key (labels k)]
          [:span.value v]])])

(defn all [resource all]
  [:main
    (for [{h :header o :others} (map #(to-single resource %) all)]
      (single (resource :labels) h o 6))])
