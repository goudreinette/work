(ns model
  (require [schema.core :refer [defschema validate]]
           [hara.time :refer [now default-type]]))


(def Date java.util.Date)
(default-type Date)


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
