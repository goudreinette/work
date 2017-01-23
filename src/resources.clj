(ns resources
  (require [clojure.string :refer [lower-case]]))

; Goal
(comment
  (defresource Jobs
    :fetch find-jobs
    :heading "Work"))

; Helpers
(defn resource-prefix [name]
  (str "/" (lower-case name)))


; Constructor
(defn resource [heading label]
  {:heading heading
   :label label
   :link (resource-prefix label)})


; Resource Setup
(defn resource [name options]
  (-> options
    (assoc :name name)
    (select-keys [:name :heading :fetch-with :save-with])))

(defmacro defresource [name & {:as options}]
  `(def ~name ~(resource name options)))
