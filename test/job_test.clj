(ns job-test
  (use midje.sweet)
  (require [clj-time.core :refer [ epoch minus hours in-hours]]
           [clj-time.coerce :refer [to-date]]
           [model :refer [session-length job-length job-price job]]
           [clojurewerkz.money.amounts :refer [amount-of multiply]]
           [clojurewerkz.money.currencies :refer [EUR]]))

(def -threehours     (-> (epoch) (minus (hours 3)) to-date))

(def session'        {:start-date -threehours
                      :end-date   (to-date (epoch))
                      :description ""})

(def job'            (-> (job "Guide" :pricing-type :fixed)
                         (assoc :sessions [session'])))

(def job-double      (update job' :sessions #(concat % %)))


(facts "session-length"
  (-> session' session-length in-hours) => 3)

(facts "job-length"
  (-> job'       job-length in-hours) => 3
  (-> job-double job-length in-hours) => 6)

(facts "job-price"
  (-> job' job-price) => (amount-of EUR 75))
