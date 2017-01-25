(ns model
  (use init yesql.core model-utils))

(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work"
                :user "root"    :password ""}})

(def jobs-with-aggregates
  (partial get-all-with-aggregates find-jobs
    job-duration-in-minutes
    job-cost))
