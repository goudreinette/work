(ns web
  (use [compojure core route]))

(defroutes all-routes
  (GET "/" [] "Hello World!"))
