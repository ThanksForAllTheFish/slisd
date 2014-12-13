(ns seven-languages.clojure.week1)

(defn big [aString aLength] (> (count aString) aLength))

(defn coll-type [aCollection] (
                                cond 
                                (list? aCollection) :list
                                (map? aCollection) :map
                                (vector? aCollection) :vector
                                (set? aCollection) :set))