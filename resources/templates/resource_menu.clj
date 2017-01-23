(ns templates.resource-menu
  (use [templates helpers]
       [joy.macros]
       [strings]))

(defn submenu-active-class [resources current-uri]
  (some #(= (:link %) current-uri) resources))

(defn resource-item [label link current-uri]
  [:a.item {:class (active-class link current-uri)
            :href link}
           label])

(defn resource-submenu [heading resources current-uri]
  [:div.item {:class (submenu-active-class resources current-uri)}
   [:div.header heading]
   [:div.menu
      (for [{:keys [label link]} resources]
        (resource-item label link current-uri))]])

(defn resource-menu [all-resources current-uri]
  [:div.ui.vertical.attached.menu.resource-menu
    (for [[heading resources] (group-by :heading all-resources)]
      (resource-submenu heading resources current-uri))])
