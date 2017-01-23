(ns macros
  (use compojure.core clojure.core.strint joy.macros templates.layout)
  (require [clojure.string :refer [lower-case]])
  (:refer-clojure :exclude [update]))

; Goal
(comment
  (defresource Jobs
    :fetch find-jobs
    :heading "Work"))

; Strings
(defn resource-prefix [name]
  (str "/" (lower-case name)))


; Handlers
(defn all [fetch-with]
  "all")

(defn single [fetch-with id]
  "single one")

(defn form [fetch-with]
  "show form")

(defn post [save-with]
  "create new, show message")

(defn edit [fetch-with id]
  "show update form")

(defn put [save-with id]
  "update, show message")

(defn delete [delete-with id]
  "delete, show message")

; Routes
(defn resource-routes [name {:keys [fetch-with save-with delete-with]}]
  (context (resource-prefix name) []
    (GET    "/"         []   (all fetch-with))
    (GET    "/:id"      [id] (single fetch-with id))
    (GET    "/new"      []   (form fetch-with))
    (POST   "/new"      []   (post save-with))
    (GET    "/:id/edit" [id] (edit fetch-with id))
    (PUT    "/:id"      [id] (put save-with id))
    (DELETE "/:id"      [id] (delete delete-with id))))


(defn make-routes [resources & options]
  ())


; Resource Setup
(defn resource [name options]
  (-> options
    (assoc :name name)
    (select-keys [:name :heading :fetch-with :save-with])))

(defmacro defresource [name & {:as options}]
  `(def ~name ~(resource name options)))
