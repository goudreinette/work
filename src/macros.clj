(ns macros
  (use compojure.core clojure.core.strint joy.macros templates.layout)
  (require [clojure.string :refer [lower-case]])
  (:refer-clojure :exclude [update]))

; Goal
(comment
  (defresource Jobs
    :fetch find-jobs
    :heading "Work"))

(defn resource-prefix [name]
  (<< "/~(lower-case name)"))


; Handlers
(defn all [params]
  "all")

(defn form [params]
  "show form")

(defn post [params]
  "create new, show message")

(defn single [params]
  "single one")

(defn edit [params]
  "show update form")

(defn put [params]
  "update, show message")

(defn delete [params]
  "delete, show message")

(defn resource-routes [name {:keys [fetch-with save-with delete-with]}]
  (context (resource-prefix name) []
    (GET    "/"         []   (all fetch-with))
    (GET    "/:id"      [id] (single fetch-with id))
    (GET    "/new"      []   (form fetch-with))
    (POST   "/new"      []   (post save-with))
    (GET    "/:id/edit" [id] (edit fetch-with id))
    (PUT    "/:id"      [id] (put save-with id))
    (DELETE "/:id"      [id] (delete delete-with id))))

; Routes
(defn make-routes [resources & options])


; Setup
(defn resource [name options]
  (-> options
    (assoc :name name)
    (select-keys [:name :heading :fetch-with :save-with])))

(defmacro defresource [name & {:as options}]
  `(def ~name ~(resource name options)))
