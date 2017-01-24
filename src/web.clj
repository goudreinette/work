(ns web
  (use [compojure core route]
       mount.core
       resources
       resource-routes
       model))

(defsection "Work"
  (resource Clients)
  (resource Jobs
    :fetch-with find-jobs
    :list-key :name))

(defsection "Fitness"
  (resource Weight)
  (resource Strength))

(defresource Stuff)

(defroutes all-routes
  (resources "/")
  (make-routes [Jobs Clients Weight Strength Stuff]))
