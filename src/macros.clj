(ns macros
  (use compojure.core clojure.core.strint joy.macros)
  (require [clojure.string :refer [lower-case]]))

; Goal
(comment
  (defresource Jobs
    :fetch find-jobs
    :heading "Work"))

(defn resource-prefix [name]
  (<< "/~(lower-case name)"))

(defn resource-routes [name {:keys [fetch heading]}]
  (le prefix (resource-prefix name)
    (routes
      (GET    prefix               [] "all")
      (GET    (<< "~{prefix}/new") [] "show form")
      (POST   (<< "~{prefix}/new") [] "create new, show message")
      (GET    (<< "~{prefix}/:id") [] "single one")
      (PUT    (<< "~{prefix}/:id") [] "update, show message")
      (DELETE (<< "~{prefix}/:id") [] "delete, show message"))))

(defmacro defresource [name & {:as opts}]
  `(def ~name ~(resource-routes name opts)))
