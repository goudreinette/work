(ns init
  (require [mount.core :refer [defstate start]]
           [org.httpkit.server :refer [run-server]]
           [web :refer [all-routes]]))


(defstate dbspec :start {:dbtype "mysql"
                         :dbname "work"
                         :user "root"
                         :password ""})

(defstate server :start (run-server all-routes)
                 :stop  (server))


(start)
