(ns routes
  (use db  [compojure core route] selmer.parser)
  (require [ring.util.response]))

(selmer.parser/cache-off!)

(defroutes all-routes
  (context "/jobs" []
    (GET "/" []
      (render-file "jobs.html" {:jobs (get-jobs)}))
    (POST "/" []
      {})
    (PUT "/:id" [id]
      {}))

  (context "/sessions" []
    (GET "/stop" []
      (stop-session!))
    (GET "/start/:id" [id]
      (start-session! {:job_id id})))

  (resources "/"))
