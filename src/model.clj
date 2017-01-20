(ns model
  (require [schema.core :refer [defschema validate enum optional-key]]
           [clj-time.core :refer [minus plus] :as t]
           [clj-time.coerce :as c]
           [monger.collection :as mc]
           [joy.macros :refer [le]]
           [init :refer [db]]
           [clojurewerkz.money.amounts :as amounts]
           [clojurewerkz.money.currencies :as currencies]))

(def euros (partial amounts/amount-of currencies/EUR))

(def now #(c/to-date (t/now)))
(def minute-rate (-> 0.41 euros))

; Constructors
(defn job [name & {:keys [pricing-type] :or {pricing-type :hourly}}]
  {:name          name
   :pricing-type  pricing-type
   :start-date    nil
   :sessions      []
   :amount-paid   0})

(defn session []
  {:start-date (now)
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
(defn session-length [{:keys [start-date end-date] :or {end-date (now)}}]
  (t/interval (c/from-date start-date)
              (c/from-date end-date)))

(defn job-length [job-id]
  (le job (mc/find-map-by-id db "jobs" job-id)
    (->> job :sessions
      (map session-length)
      (reduce +))))

(defn job-price [job-id]
  (let [length-in-minutes (-> job-id job-length t/in-minutes)]
    (println length-in-minutes)
    (amounts/multiply minute-rate length-in-minutes)))
