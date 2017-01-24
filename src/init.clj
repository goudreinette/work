(ns init
  (require [mount.core :refer [defstate start]]
           [org.httpkit.server :refer [run-server]]
           [web :refer [all-routes]]
           [prone.middleware :as prone]))

(def app
  (-> all-routes
    (prone/wrap-exceptions)))

(defstate server :start (run-server app)
                 :stop  (server))


(start)
