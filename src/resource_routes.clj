(ns resource-routes
  (use compojure.core
       templates.layout
       resources))


(defn resource-routes [layout {:keys [name heading fetch-with save-with delete-with]}]
  (context (resource-prefix name) []
    (GET    "/"         []   (layout))
    (GET    "/:id"      [id] (layout))
    (GET    "/new"      []   (layout))
    (POST   "/new"      []   (layout))
    (GET    "/:id/edit" [id] (layout))
    (PUT    "/:id"      [id] (layout))
    (DELETE "/:id"      [id] (layout))))


(defn make-routes [resources & options]
  (let [layout          (partial layout resources)
        resource-routes (partial resource-routes layout)]
    (->> resources
      (map resource-routes)
      (apply routes))))
