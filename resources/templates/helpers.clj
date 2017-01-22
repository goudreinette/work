(ns templates.helpers)

(defn active-class [current active]
  (if (= current active) "active" ""))
