(ns model-utils)

; TODO: Move me to sandbox
(defn merge-with-aggregate [result aggregate-fn]
  (merge result (aggregate-fn result {:result-set-fn first})))

(defn merge-with-aggregates [result aggregate-fns]
  (reduce merge-with-aggregate result aggregate-fns))

(defn get-with-aggregates [initial-result-fn & aggregate-fns]
  (merge-with-aggregates (first (initial-result-fn)) aggregate-fns))

(defn get-all-with-aggregates [initial-results-fn & aggregate-fns]
  (->>
    (initial-results-fn)
    (map #(merge-with-aggregates % aggregate-fns))))
