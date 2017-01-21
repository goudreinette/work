(ns model
  (use init helpers)
  (require [clj-time.core :refer [now]]))


; Entities
(defentity session
  :job-id
  :end-date
  :description)

(defentity job
  :client-id
  :invoice-id
  :name)

(defentity client
  :name
  :address
  :city
  :postcode)

(defentity invoice
  :client-id
  :no
  :date)



; Operations
(defn insert [db & entities]
  (swap! db
    (fn [db]
      (update db :entities #(apply conj % entities)))))

; Queries

; Test Data
(insert db
  (client :name "Rein" :address "KS" :city "Zw" :postcode "8011VC"))
