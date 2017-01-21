(ns init
  (require [mount.core :refer [defstate start]]
           [org.httpkit.server :refer [run-server]]
           [web :refer [all-routes]]))


(defstate db     :start (atom {:id 0 :entities #{}}))
(defstate server :start (run-server all-routes)
                 :stop  (server))


(start)
