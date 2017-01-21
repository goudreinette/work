(ns helpers
  (use clojure.set)
  (require [schema.core :refer [Any]]))


; Macro's and Helpers
(defn uuid []
  (str (java.util.UUID/randomUUID)))

(defn make-id-name [sym]
  (keyword (str (name sym) "-id")))

; (defn make-validator [required-keys]
;   (apply hash-map (interpose)))

(defn make-constructor [id-name required-keys]
  (fn [& {:as map}]
    (assert (every? (set required-keys) (keys map))"Required keys missing")
    (assoc map id-name (uuid))))

(defmacro defentity [name & keys]
  (let [id-name (make-id-name name)
        constructor (make-constructor id-name keys)]
    `(def ~name ~constructor)))
