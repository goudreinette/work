(ns model
  (require [schema.core :refer [defschema validate enum optional-key]]
           [clj-time.core :refer [minus plus] :as t]
           [clj-time.coerce :as c]
           [monger.collection :as mc]
           [init :refer :all]
           [clojurewerkz.money.amounts :as amounts]
           [clojurewerkz.money.currencies :as currencies]))

(def euros (partial amounts/amount-of currencies/EUR))

(def now #(c/to-date (t/now)))
(def hourly-rate (euros 25))
(def minute-rate (amounts/divide hourly-rate 60 :half-even))

; Constructors
(defn job [name & {:keys [pricing-type] :or {pricing-type :hourly}}]
  {:name          name
   :pricing-type  pricing-type
   :start-date    nil
   :amount-paid   0})

(defn session []
  {:start-date (now)
   :end-date nil
   :description ""})

; Side Effecting Queries
(defn find! [coll]
  (fn ([db]           (mc/find-maps db coll {}))
      ([db condition] (mc/find-maps db coll condition))))


; Convenience shorthands. Clean up with a macro?
(def find-sessions! (find! "sessions"))
(def find-jobs!     (find! "jobs"))
(def find-clients!  (find! "clients"))
(def find-invoices! (find! "invoices"))





; Operations
(defn create-job! [name & attrs]
  (mc/insert-and-return db "jobs" (apply job name attrs)))

(defn start-session! [job-id]
  (mc/update-by-id db "jobs" job-id
    {:$push {:sessions (session)}}))

(defn end-session! []
  (mc/update db "jobs" {:sessions.end-date nil}
    {:$set {:sessions.$.end-date (now)}}))

; Helpers
(defn sum-interval [intervals]
  (->> intervals
    (map t/in-minutes)
    (reduce +)
    (t/minutes)))

; Queries
(defn session-length [{:keys [start-date end-date] :or {end-date (now)}}]
  (t/interval (c/from-date start-date)
              (c/from-date end-date)))

(defn job-length [job]
  (->> job :sessions
    (map session-length)
    (sum-interval)))

(defn job-price [job]
  (->> job
    (job-length)
    (t/in-hours)
    (amounts/multiply hourly-rate)))
