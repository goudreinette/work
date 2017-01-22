(ns model
  (use init yesql.core joy.macros))

; Helpers
(defqueries "sql/queries.sql"
  {:connection dbspec})
