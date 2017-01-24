(ns model
  (use yesql.core joy.macros))


; Helpers
(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work"
                :user   "root"  :password ""}})



(defn get-jobs-with-length []
  (->>
    (find-jobs)
    (map #(assoc % :id (% :job_id)))
    (map #(merge % (first (job-length-in-minutes %))))))
