(ns web
  (require [ring.util.response :refer [redirect]])
  (use [compojure core route]
       mount.core
       resources
       resource-routes
       model))


(defresource Clients)
(defresource Jobs
  :fetch-with get-jobs
  :display-key :name
  :labels {:client_name "Client"
           :minutes "Length in minutes"
           :cost "Cost in euros"})

(defsection "Work"
  Clients
  Jobs)

(defroutes all-routes
  (resources "/")
  (GET "/" [] (redirect "/jobs"))
  (make-routes Jobs Clients))
