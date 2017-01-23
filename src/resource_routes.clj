(ns resource-routes
  (use compojure.core
       templates.layout
       resources))


(defn resource-routes [resources {:keys [name heading fetch-with save-with delete-with]}]
  (context (resource-prefix name) []
    (GET    "/"         []   "all")
    (GET    "/:id"      [id] "single")
    (GET    "/new"      []   "form")
    (POST   "/new"      []   "new")
    (GET    "/:id/edit" [id] "update form")
    (PUT    "/:id"      [id] "update")
    (DELETE "/:id"      [id] "delete")))


(defn make-routes [resources & options]
  (apply routes
    (map #(resource-routes resources %) resources)))
