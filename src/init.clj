(ns init
  (require [mount.core :refer [defstate start]]
           [monger.core :refer [connect disconnect get-db]]
           [monger.collection :refer [find-map-by-id find-maps] :as mc]))

(defstate db :start (get-db (connect) "work"))

; Database methods
(defn find!
  ([coll db condition] (find-maps db coll condition))
  ([coll db]           (find-maps db coll {})))

(start)
