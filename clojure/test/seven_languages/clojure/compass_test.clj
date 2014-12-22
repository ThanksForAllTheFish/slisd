(ns seven-languages.clojure.compass-test
  (:require [clojure.test :refer :all]
            [seven_languages.clojure.compass :refer :all])
  (:import [seven_languages.clojure.compass SimpleCompass]))

(deftest compass-module
  (testing "turn function"
           (is (= 2 (turn 1 1)))
           (is (= 1 (turn 1 4)))
           (is (= 0 (turn 3 1)))
           (is (= 1 (turn 2 3)))
           (is (= 3 (turn 0 -1)))
           (is (= 3 (turn 0 -5)))
           )
  (testing "Compass direction"
           (is (= :north (direction (SimpleCompass. 0))))
           (is (= :east (direction (SimpleCompass. 1))))
           (is (= :south (direction (SimpleCompass. 2))))
           (is (= :west (direction (SimpleCompass. 3))))
           )
  (testing "Compass left"
           (is (= :west (direction (left (SimpleCompass. 0)))))
           (is (= :north (direction (left (SimpleCompass. 1)))))
           (is (= :east (direction (left (SimpleCompass. 2)))))
           (is (= :south (direction (left (SimpleCompass. 3)))))
           )
  (testing "Compass right"
           (is (= :east (direction (right (SimpleCompass. 0)))))
           (is (= :south (direction (right (SimpleCompass. 1)))))
           (is (= :west (direction (right (SimpleCompass. 2)))))
           (is (= :north (direction (right (SimpleCompass. 3)))))
           )
)