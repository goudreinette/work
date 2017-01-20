(ns model
  (require [schema.core :refer [defschema validate]]
           [date :refer [Date]]
           [hara.time :refer [now]]
           [monger.collection :as mc]
           [joy.macros :refer [le]]))

; Schema's
(defschema Session
  {:start-date Date
   :end-date   Date})


(defschema Job
  {:name       String
   :start-date Date
   :end-date   Date
   :sessions   [Session]})

(defschema Invoice
  {:date Date
   :jobs [Job]})

(defschema Client
  {:name     String
   :address  String
   :city     String
   :postcode String
   :invoices [Invoice]})

; Operations
(defn start-session [job-id]
  le
  (mc/update-by-id conn "jobs" job-id {:$push {:sessions}}))



; Queries
