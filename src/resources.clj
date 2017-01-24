(ns resources
  (require [clojure.string :refer [lower-case]]))


; Helpers
(defn resource-prefix [name]
  (str "/" (lower-case name)))

(defn make-resource [name options]
  (-> options
    (select-keys [:heading :fetch-with :save-with :update-with :delete-with :list-key])
    (assoc :name (str name) :link (resource-prefix name))))

(defn prep-section [heading resources]
  (->> resources
    (map #(assoc % :heading heading))
    (map #(update % :name symbol))))


; Constructors
(defmacro resource [name & {:as options}]
  (make-resource name options))

(defmacro defresource [name & {:as options}]
 `(def ~name ~(make-resource name options)))

(defn defsection [heading & resources]
  (doseq [{name :name :as resource} (prep-section heading resources)]
    (intern *ns* name resource)))
