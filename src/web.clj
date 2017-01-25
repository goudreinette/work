(ns web
  (require [ring.util.response :refer [redirect]])
  (use [compojure core route]
       mount.core
       resources
       resource-routes
       model))


(defresource Clients
  :heading "Work"
  :fetch-with find-clients
  :display-key :name
  :labels {:address "Address"
           :postcode "Postcode"
           :city "City"})

(defresource Jobs
  :heading "Work"
  :fetch-with get-jobs
  :display-key :name
  :labels {:client_name "Client"
           :duration "Duration"
           :cost "Cost"})

; (defsection "Work"
;   Clients
;   Jobs)

(defroutes all-routes
  (resources "/")
  (GET "/" [] (redirect "/jobs"))
  (make-routes Jobs Clients))
