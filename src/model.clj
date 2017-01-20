(ns model
  (require [schema.core :refer [defschema validate]]
           [date :refer [Date]]))


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
