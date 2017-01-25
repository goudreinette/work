(ns resources
  (import clojure.lang.IFn)
  (require [clojure.core :exclude [defrecord]]
           [clojure.string :refer [lower-case]]
           [schema.core :refer [defschema optional-key maybe Keyword Symbol Any]]))


; The Resource Schema
(defschema Resource
  "Represents a single type of admin resource"
  {; Structure
   :name        Symbol
   :link        String
   :heading     (maybe String)

   ; Display
   :display-key Keyword
   :labels      {Keyword String}

   ; Create, Read, Update, Delete
   :fetch-with  IFn
   :save-with   (maybe IFn)
   :update-with (maybe IFn)
   :delete-with (maybe IFn)})



; Helpers
(defn resource-prefix [name]
  (str "/" (lower-case name)))

(defn prep-section [heading resources]
 (->> resources
   (map #(assoc % :heading heading))
   (map #(update % :name symbol))))


(defn make-resource
  [name {:keys [heading display-key labels fetch-with save-with update-with delete-with]}]
  {:name        (str name)
   :link        (resource-prefix name)
   :heading      heading
   :display-key  display-key
   :labels       labels
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
