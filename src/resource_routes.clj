(ns resource-routes
  (use compojure.core
       templates.layout
       resources))


(defn resource-routes [layout {:keys [name heading fetch-with save-with delete-with]}]
  (context (resource-prefix name) [:as {u :uri}]
    (GET    "/"         []   (layout (fetch-with) u))))
    ; (GET    "/:id"      [id] (layout u))
    ; (GET    "/new"      []   (layout u))
    ; (POST   "/new"      []   (layout u))
    ; (GET    "/:id/edit" [id] (layout u))
    ; (PUT    "/:id"      [id] (layout u))
    ; (DELETE "/:id"      [id] (layout u))))


(defn make-routes [resources & options]
  (let [layout          (partial layout resources)
        resource-routes (partial resource-routes layout)]
    (->> resources
      (map resource-routes)
      (apply routes))))
