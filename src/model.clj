(ns model
  (use init yesql.core))

(defqueries "sql/queries.sql"
  {:connection {:dbtype "mysql" :dbname "work"
                :user "root" :password ""}})
