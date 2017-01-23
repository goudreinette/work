(ns templates.resource-menu
  (use [templates helpers]
       [joy.macros]
       [resources]))

(defn submenu-active-class [resources current-uri]
  (if (some #(= (:link %) current-uri) resources)
    "active"
    ""))
      
(defn resource-item [name link current-uri]
  [:a.item {:class (active-class link current-uri)
            :href link}
           name])

(defn resource-submenu [heading resources current-uri]
  [:div.item {:class (submenu-active-class resources current-uri)}
   [:div.header heading]
   [:div.menu
      (for [{:keys [name link]} resources]
        (resource-item name link current-uri))]])

(defn resource-menu [all-resources current-uri]
  [:div.ui.vertical.attached.menu.resource-menu
    (for [[heading resources] (group-by :heading all-resources)]
      (resource-submenu heading resources current-uri))])
