(ns web
  (use [compojure core route]
       [clojure.core strint]
       [hiccup core]
       [templates layout]))

(defn greeting [name]
  [:h1.header (<< "Hello, ~{name}!")])

(defroutes all-routes
  (resources "/")
  (GET "/" []
    (html (layout)))
  (GET "/:name" [name]
    (html (layout))))
