(ns resources
  (require [clojure.string :refer [lower-case]]))

; Helpers
(defn resource-prefix [name]
  (str "/" (lower-case name)))

(defn prep-section [heading resources]
 (->> resources
   (map #(assoc % :heading heading))
   (map #(update % :name symbol))))


(defn make-resource
  [name {:keys [heading list-key fetch-with save-with update-with delete-with]}]
  {:name        (str name)
   :link        (resource-prefix name)
   :heading      heading
   :list-key     list-key
   :fetch-with   (or fetch-with (constantly []))
   :save-with    save-with
   :update-with  update-with
   :delete-with  delete-with})


; Constructors
(defmacro resource [name & {:as options}]
  (make-resource name options))

(defmacro defresource [name & {:as options}]
 `(def ~name ~(make-resource name options)))

(defn defsection [heading & resources]
  (doseq [{name :name :as resource} (prep-section heading resources)]
    (intern *ns* name resource)))
