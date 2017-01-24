(ns resource-routes
  (use compojure.core
       templates.layout
       resources))


(defn resource-routes
  [all-resources {:keys [name heading fetch-with save-with delete-with] :as resource}]
  (let [layout (partial layout all-resources resource)]
    (context (resource-prefix name) []
      (GET    "/"         []   (layout (fetch-with))))))
      ; (GET    "/:id"      [id] (layout u))
      ; (GET    "/new"      []   (layout u))
      ; (POST   "/new"      []   (layout u))
      ; (GET    "/:id/edit" [id] (layout u))
      ; (PUT    "/:id"      [id] (layout u))
      ; (DELETE "/:id"      [id] (layout u))))

(defn make-routes [& resources]
  (->> resources
    (map #(resource-routes resources %))
    (apply routes)))
