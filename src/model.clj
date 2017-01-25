(ns model
  (use init yesql.core model-utils))

(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work"
                :user "root"    :password ""}})
