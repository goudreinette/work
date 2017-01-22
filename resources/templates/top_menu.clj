(ns templates.top-menu)

(defn top-menu-item [label & {:keys [action class]}]
  [:button.ui.basic.compact.tiny.button
    {:data-action action :class class} label])

(defn top-menu [items]
  [:div.ui.attached.menu.top-menu
    (for [label items]
      (top-menu-item label))])
