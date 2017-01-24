(ns model
  (use yesql.core joy.macros))


; Helpers
(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work"
                :user   "root"  :password ""}})

(defn get-job-with-aggregates [{id :job_id :as job}]
  (as-> job j
    (assoc j :id id)
    (merge j (first (job-length-in-minutes j)))
    (merge j (first (job-cost (assoc j :hourly_rate 25))))))

(defn get-jobs-with-aggregates []
  (->>
    (find-jobs)
    (map get-job-with-aggregates)))
