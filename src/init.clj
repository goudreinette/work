(ns init
  (require [mount.core :refer [defstate]]
           [monger.core :refer [connect disconnect get-db]]))


(defstate conn :start (connect)
               :stop  (disconnect conn))

(def db (get-db conn "work"))
