(ns web
  (use [compojure core route]
       resources
       resource-routes))

(defsection "Work"
  (resource Clients)
  (resource Jobs))
  ;
  ; (defsection "Fitness"
  ;   (resource 'Weight)
  ;   (resource 'Strength))

(defresource Stuff)

(defroutes all-routes
  (resources "/"))
  ;(make-routes [Jobs Clients Weight Strength]))
