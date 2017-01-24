(ns templates.single)

(defn to-single [{hk :header-key} item]
  {:header   (item hk)
   :others   (dissoc item hk)})


(defn single [header others]
  [:div.ui.padded.segment.single.hidden
    [:h3.ui.dividing.header header]
    (for [[k v] others]
      [:p [:strong k] v])])

(defn all [resource all]
  [:main
    (for [{h :header o :others} (map #(to-single resource %) all)]
      (single h o))])
