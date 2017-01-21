(ns model
  (use init yesql.core))

(defqueries "queries.sql"
  {:connection dbspec})


(defn job-length-in-minutes [job-id]
  (session-lengths-in-minutes-for-job job-id))
