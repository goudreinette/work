(ns templates.top-menu)

(defn top-menu-item [label & {:keys [action]}]
  [:button.ui.basic.compact.tiny.button.hidden
    {:id (clojure.string/lower-case label)} label])

(defn top-menu [items]
  [:div.ui.attached.menu.top-menu
    (for [label items]
      (top-menu-item label))])
