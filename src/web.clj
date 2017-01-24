(ns web
  (use [compojure core route]
       mount.core
       resources
       resource-routes
       model))


(defresource Clients)
(defresource Jobs
  :fetch-with get-jobs-with-aggregates
  :display-key :name
  :labels {:client_name "Client"
           :minutes "Length in minutes"})

(defsection "Work"
  Clients
  Jobs)

(defroutes all-routes
  (resources "/")
  (make-routes Jobs Clients))
