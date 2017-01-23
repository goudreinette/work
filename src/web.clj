(ns web
  (use [compojure core route]
       resources
       resource-routes))

(defresource Jobs
  :heading "Work")

(defresource Clients
  :heading "Work")

(defroutes all-routes
  (resources "/")
  (make-routes [Jobs Clients]))
