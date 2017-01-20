(ns model
  (require [schema.core :refer [defschema validate enum optional-key]]
           [date :refer [Date]]
           [hara.time :refer [now]]
           [monger.collection :as mc]
           [joy.macros :refer [le]]
           [init :refer [db]]))

; Schema's
(defschema Session
  {:start-date Date
   :end-date   Date})


(defschema Job
  {:name         String
   :pricing-type (enum :hourly :fixed)
   (optional-key :start-date) Date
   :sessions     [Session]
   :amount-paid  Float})

(defschema Invoice
  {:date Date
   :jobs [Job]})

(defschema Client
  {:name     String
   :address  String
   :city     String
   :postcode String
   :invoices [Invoice]})

; Constructors
(defn job [name pricing-type]
  (validate Job
    {:name          name
     :pricing-type  pricing-type
     :start-date    nil
     :sessions      []
     :amount-paid   0}))

(defn session []
  {:start-date (now)
   :end-date nil})


; Operations
(defn start-session [job-id]
  (mc/update-by-id db "jobs" job-id {:$push {:sessions (session)}}))



; Queries
