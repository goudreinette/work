(ns model
  (use init yesql.core))

(defqueries "queries.sql"
  {:connection dbspec})
