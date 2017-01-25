(ns db
  (use yesql.core utils))

(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work" :user "root"    :password ""}})


(def get-jobs
  (partial get-all-with-aggregates find-jobs job-duration job-cost))
