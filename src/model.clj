(ns model
  (use init yesql.core))

(defqueries "queries.sql"
  {:connection dbspec})



(defn job-length-in-minutes [job-id]
  (->> job-id
    (session-lengths-in-minutes-for-job)
    (map :minutes)
    (reduce +)))
