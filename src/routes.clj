(ns routes
  (use db
    [compojure core route])
  (require
    [ring.util.response]))

(defroutes all-routes
  (context "/sessions" []
    (GET "/stop" []
      (stop-session!))
    (GET "/start/:id" [id]
      (start-session! {:job_id id})))
  (resources "/"))
