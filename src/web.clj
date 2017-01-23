(ns web
  (use [compojure core route]
       resources
       resource-routes))

(defresource Jobs
  :heading "Work")

(defresource Clients
  :heading "Work")

(defresource Weight
  :heading "Fitness")

(defresource Strength
  :heading "Fitness")

(defroutes all-routes
  (resources "/")
  (make-routes [Jobs Clients]))
