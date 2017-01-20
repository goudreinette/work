(ns work
  (require [mount.core :refer [defstate]]
           [monger.core :refer [connect disconnect]]))

(defstate conn :start (connect)
               :stop  (disconnect conn))

; server here...
