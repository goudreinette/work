(ns init
  (require [mount.core :refer [defstate start]]
           [org.httpkit.server :refer [run-server]]
           [routes :refer [all-routes]]
           [prone.middleware :as prone]))

(def app (prone/wrap-exceptions all-routes))


(defstate server :start (run-server app)
                 :stop  (server))


(start)
