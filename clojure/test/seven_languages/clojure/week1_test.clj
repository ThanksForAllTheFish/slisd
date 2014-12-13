(ns seven-languages.clojure.week1-test
  (:require [clojure.test :refer :all]
            [seven-languages.clojure.week1 :refer :all]))

(deftest test-coll-type
   (testing "coll-type"
      (is (= :list (coll-type '()))))
      (is (= :vector (coll-type [])))
      (is (= :map (coll-type {})))
      (is (= :set (coll-type #{})))
      (is (nil? (coll-type "")))
)

(deftest test-big
   (testing "big"
            (is (big "bigger" 3))
            (is (not (big "" 0))))
)