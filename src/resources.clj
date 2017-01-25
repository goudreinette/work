(ns resources
  (use resource-utils)
  (import clojure.lang.IFn)
  (require [schema.core :refer [defschema maybe Keyword Symbol] :rename {maybe ?}]))


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
