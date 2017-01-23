(ns resources
  (require [clojure.string :refer [lower-case]]))


; Helpers
(defn resource-prefix [name]
  (str "/" (lower-case name)))


; Constructor
(defn resource [name options]
  (-> options
    (select-keys [:heading :fetch-with :save-with])
    (assoc :name (str name) :link (resource-prefix name))))

(defmacro defresource [name & {:as options}]
  `(def ~name ~(resource name options)))
