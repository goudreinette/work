(ns web
  (use [compojure core route]
       [clojure.core strint]
       [hiccup core]
       [templates layout]
       resources))

(defresource Jobs
  :heading "Work")

(defroutes all-routes
  (resources "/")
  ; Jobs
  (GET "/" []
    (html (layout nil nil)))
  (GET "/:name" [name]
    (html (layout nil nil))))
