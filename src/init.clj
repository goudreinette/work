(ns init
  (require [mount.core :refer [defstate start]]
           [monger.core :refer [connect disconnect get-db]]))

(defstate db :start (get-db (connect) "work"))

(start)
