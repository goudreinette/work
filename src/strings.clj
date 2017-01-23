(ns strings
  (require [clojure.string :refer [lower-case]]))

(defn resource-prefix [name]
  (str "/" (lower-case name)))
