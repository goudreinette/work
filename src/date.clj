(ns date
  (require [hara.time :refer [now default-type]]))

(def Date java.util.Date)
(default-type Date)
