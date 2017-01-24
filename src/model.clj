(ns model
  (use yesql.core joy.macros korma.db korma.core)
  (:refer-clojure :exclude [update]))

; Setup
(defdb db (mysql {:host "localhost" :port 3306
                  :user "root" :db "work"}))

(declare session job client)
(defn now []
  (java.util.Date.))


; Entities
(defentity session)

(defentity job
  (has-many session)
  (belongs-to client))

(defentity client
  (has-many job))


; Queries
(defn find-jobs []
  (select job
    (with session)))

(defn start-session! [job-id]
  (insert session
    (values {:job_id job-id})))

(defn end-session! []
  (update session
    (where {:end_date nil})
    (set-fields {:end_date (now)})))
