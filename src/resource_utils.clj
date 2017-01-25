(ns resource-utils
  (require [clojure.string :refer [lower-case]]
           [schema.core :refer [validate]]))



(defn resource-prefix [name]
  (str "/" (lower-case name)))

(defn prep-section [heading resources]
 (->> resources
   (map #(assoc % :heading heading))
   (map #(update % :name symbol))))

(defn empty-schema [schema]
  (apply hash-map (interleave (keys schema) (repeat nil))))

(defn validate-with-defaults [schema map & {:as defaults}]
  (validate schema
    (merge (empty-schema schema)
      map
      defaults)))
