(ns web
  (use [compojure core route]
       [clojure.core strint]))

(defroutes all-routes
  (GET "/" []
    "Hello World!")
  (GET "/:name" [name]
    (<< "Hello ~{name}!")))
