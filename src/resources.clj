(ns resources
  (require [clojure.string :refer [lower-case]]))


; Helpers
(defn resource-prefix [name]
  (str "/" (lower-case name)))


; Constructor
(defn make-resource [name options]
  (-> options
    (select-keys [:heading :fetch-with :save-with])
    (assoc :name (str name) :link (resource-prefix name))))

(defn prep-section [heading resources]
  (->> resources
    ; (map eval)
    (map #(assoc % :heading heading))
    (map #(update % :name symbol))))

(defmacro resource [name & {:as options}]
  (make-resource name options))

(defmacro defresource [name & {:as options}]
 `(def ~name ~(make-resource name options)))

(defn defsection [heading & resources]
  (doseq [{name :name :as resource} (prep-section heading resources)]
    (intern *ns* name resource)))

(doseq [sym ['a 'b 'c]]
  `(def ~sym 5))

; Idea
(comment
  (defsection "Work"
    (resource "Jobs")
    (resource "Clients")))
