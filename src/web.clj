(ns web
  (use [compojure core route]
       mount.core
       resources
       resource-routes
       model))


(defresource Clients)
(defresource Jobs
  :fetch-with find-jobs
  :display-key :name
  :labels {:client_name "Client"})

(defsection "Work"
  Clients
  Jobs)

(defroutes all-routes
  (resources "/")
  (make-routes Jobs Clients))
