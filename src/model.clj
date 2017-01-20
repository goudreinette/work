(ns model
  (require [schema.core :refer [defschema validate enum optional-key]]
           [clj-time.core :refer [now minus plus] :as t]
           [clj-time.coerce :as c]
           [monger.collection :as mc]
           [joy.macros :refer [le]]
           [init :refer [db]]))


; Constructors
(defn job [name & {:keys [pricing-type] :or {pricing-type :hourly}}]
  {:name          name
   :pricing-type  pricing-type
   :start-date    nil
   :sessions      []
   :amount-paid   0})

(defn session []
  {:start-date (c/to-date (now))
   :end-date nil
   :description ""})


; Operations
(defn create-job [name & attrs]
  (mc/insert-and-return db "jobs" (apply job name attrs)))

(defn start-session [job-id]
  (mc/update-by-id db "jobs" job-id
    {:$push {:sessions (session)}}))

(defn end-session []
  (mc/update db "jobs" {:sessions.end-date nil}
    {:$set {:sessions.$.end-date (now)}}))


; Queries
(defn session-length [{:keys [start-date end-date]}]
  (t/in-minutes (t/interval (c/from-date start-date) (c/from-date (or end-date (now))))))

(defn job-length [job-id]
  (le job (mc/find-map-by-id db "jobs" job-id)
    (reduce + (map session-length (job :sessions)))))
