(ns model
  (use yesql.core model-utils))

(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work" :user "root"    :password ""}})


(def get-jobs
  (partial get-all-with-aggregates find-jobs job-cost job-duration-in-minutes))
