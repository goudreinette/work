(ns init
  (require [mount.core :refer [defstate start]]
           [monger.core :refer [connect disconnect get-db]]
           [org.httpkit.server :refer [run-server]]
           [web :refer [all-routes]]))


(defstate db     :start (get-db (connect) "work"))
(defstate server :start (run-server all-routes)
                 :stop  (server))


(start)
