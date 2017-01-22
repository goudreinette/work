(ns templates.resource-menu
  (use [templates helpers]))


(defn resource-item [label active link]
  [:a.item {:class (active-class label active)
            :href link}
           label])

(defn resource-submenu [label active & children]
  [:div.item {:class (active-class label active)}
   [:div.header label]
   [:div.menu
      (for [[label link] (partition 2 children)]
        (resource-item label active link))]])

(defn resource-menu []
  [:div.ui.vertical.attached.menu.resource-menu
   (resource-submenu "Work" "Work"
     "Jobs" ""
     "Clients" "")
   (resource-item "School" "" "")
   (resource-item "Fitness" "" "")])
