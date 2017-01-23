(ns resource-routes
  (use compojure.core clojure.core.strint joy.macros templates.layout)
  (:refer-clojure :exclude [update]))


; Show
(defn all [resources fetch-with]
  "all")

(defn single [resources fetch-with id]
  "single one")

(defn form [resources fetch-with]
  "show form")

(defn edit [resources fetch-with id]
  "show update form")

; Create / Update / Delete
(defn post [save-with]
  "create new, show message")

(defn put [save-with id]
  "update, show message")

(defn delete [delete-with id]
  "delete, show message")

; Routes
(defn resource-routes [resources {:keys [name heading fetch-with save-with delete-with]}]
  (context (resource-prefix name) []
    (GET    "/"         []   (all fetch-with))
    (GET    "/:id"      [id] (single fetch-with id))
    (GET    "/new"      []   (form fetch-with))
    (POST   "/new"      []   (post save-with))
    (GET    "/:id/edit" [id] (edit fetch-with id))
    (PUT    "/:id"      [id] (put save-with id))
    (DELETE "/:id"      [id] (delete delete-with id))))


(defn make-routes [resources & options]
  (apply routes
    (map #(resource-routes resources %) resources)))
