(ns model
  (use yesql.core joy.macros))


; Helpers
(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work"
                :user   "root"  :password ""}})
