(ns model
  (use init yesql.core))

(defqueries "sql/queries.sql"
  {:connection dbspec})


(defn job-length-in-minutes [job-id]
  (->> job-id
    (session-lengths-in-minutes-for-job)
    (map :minutes)
    (reduce +)))

(defn job-cost [job-id hourly-rate]
  (->> job-id
    (job-length-in-minutes)
    (* (/ hourly-rate 60)) ; TODO: money library
    (float)
    (format "%.2f")
    (read-string)))
