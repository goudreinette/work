(ns model-old)
;   (require [schema.core :refer [defschema validate enum optional-key]]
;            [clj-time.core :refer [minus now plus] :as t]
;            [monger [collection :as mc] joda-time]
;            [init :refer :all]
;            [clojurewerkz.money.amounts :as amounts]
;            [clojurewerkz.money.currencies :as currencies])
;   (import org.bson.types.ObjectId))
;
; (def euros (partial amounts/amount-of currencies/EUR))
;
; (def hourly-rate (euros 25))
; (def minute-rate (amounts/divide hourly-rate 60 :half-even))
;
; ; Constructors
; (defn job [name & {:keys [pricing-type] :or {pricing-type :hourly}}]
;   {:name          name
;    :pricing-type  pricing-type
;    :start-date    nil
;    :amount-paid   0})
;
; (defn session [job-id]
;   {:start-date (now)
;    :end-date nil
;    :job-id job-id
;    :description ""})
;
; ; Side Effecting Queries
; (defn find! [coll]
;   (fn ([db]           (mc/find-maps db coll {}))
;       ([db condition] (mc/find-maps db coll condition))))
;
;
; ; Convenience shorthands. Clean up with a macro?
; (def find-sessions! (find! "sessions"))
; (def find-clients!  (find! "clients"))
; (def find-invoices! (find! "invoices"))
;
;
;
;
;
; ; Operations
; (defn create-job! [name & attrs]
;   (mc/insert-and-return db "jobs" (apply job name attrs)))
;
; (defn start-session! [job-id]
;   (when (mc/find-map-by-id db "jobs" job-id)
;     (mc/insert-and-return db "sessions"
;       (session job-id))))
;
; (defn find-jobs! []
;   (mc/aggregate db "jobs"
;     {:$lookup {:from "sessions",
;                :localField :_id,
;                :foreignField :job-id,
;                :as :sesssion}}))
;
;
; (defn end-session! []
;   (mc/update db "sessions" {:end-date nil} {:$set {:end-date (now)}}))
;
; ; Helpers
; (defn sum-interval [intervals]
;   (->> intervals
;     (map t/in-minutes)
;     (reduce +)
;     (t/minutes)))
;
; ; Queries
; (defn session-length [{:keys [start-date end-date] :or {end-date (now)}}]
;   (t/interval start-date end-date))
;
; (defn job-length [job]
;   (->> job :sessions
;     (map session-length)
;     (sum-interval)))
;
; (defn job-price [job]
;   (->> job
;     (job-length)
;     (t/in-hours)
;     (amounts/multiply hourly-rate)))
