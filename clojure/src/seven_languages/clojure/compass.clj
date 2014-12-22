(ns seven_languages.clojure.compass)

(defprotocol Compass
  (direction [this])
  (left [this])
  (right [this])
)

(def directions [:north :east :south :west])

(defn safe [degrees]
  (loop [deg degrees]
    (if
    (> 0 deg) (recur (+ deg (count directions)))
    deg
  )))

(defn turn [start degrees]
  (rem (+ start (safe degrees)) (count directions)))

(defrecord SimpleCompass [currentDirection] Compass
  (direction [_] (directions currentDirection))
  (left [_] (SimpleCompass. (turn currentDirection -1)))
  (right [_] (SimpleCompass. (turn currentDirection 1)))
  Object (toString [_] (str "pippo"))
)

(defn printcompass [compass] (.toString compass))