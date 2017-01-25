(ns resources
  (import clojure.lang.IFn)
  (require [clojure.string :refer [lower-case]]
           [schema.core :refer [defschema validate maybe Keyword Symbol] :rename {maybe ?}]))


; Helpers: separate namespace?
(defn resource-prefix [name]
  (str "/" (lower-case name)))

(defn prep-section [heading resources]
 (->> resources
   (map #(assoc % :heading heading))
   (map #(update % :name symbol))))

(defn empty-schema [schema]
  (apply hash-map (interleave (keys schema) (repeat nil))))

(defn validate-with-defaults [schema map & {:as defaults}]
  (validate schema (merge map defaults)))


; The Resource Schema
(defschema Resource
  "Represents a single type of admin resource"
  {:name        Symbol
   :link        String
   :heading     (? String)

   ; Display
   :display-key Keyword
   :labels      {Keyword String}

   ; Create, Read, Update, Delete
   :fetch-with  IFn
   :save-with   (? IFn)
   :update-with (? IFn)
   :delete-with (? IFn)})


(defn ->Resource [name options]
  (validate-with-defaults Resource options
      :name       name
      :link       (resource-prefix name)
      :fetch-with (constantly [])))


; Constructors
(defmacro resource [name & {:as options}]
  (->Resource name options))

(defmacro defresource [name & {:as options}]
 `(def ~name ~(->Resource name options)))

(defn defsection [heading & resources]
  (doseq [{name :name :as resource} (prep-section heading resources)]
    (intern *ns* name resource)))
