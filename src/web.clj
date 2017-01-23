(ns web
  (use [compojure core route]
       resources
       resource-routes))

(defheading Work
  (resource Clients)
  (resource Jobs))

(defheading Fitness
  (resource Weight)
  (resource Strength))

(defroutes all-routes
  (resources "/")
  (make-routes [Jobs Clients Weight Strength]))
