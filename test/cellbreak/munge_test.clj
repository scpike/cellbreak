(ns cellbreak.munge-test
  (:require [clojure.test :refer :all]
            [cellbreak.munge :refer :all]))

(def simple-input
  [{:name "Sheet1",
    :vals ['("Part number" "Warehouse" "Inventory on hand" "Inventory in transit")
           '("FOO101" 1.0 -20.0 100.0)
           '("FOO102" 1.0 1000.0 30.0)]}])

(def munge-config
  {:header_map {"Part number" :part_number
                "Warehouse" :warehouse
                "Inventory on hand" :on_hand
                "Inventory in transit" in_transit}

   })

(deftest simple-input-test
  (testing "Remaps simple headers"
    (let [result (munge simple-input munge-config)]
      (is (= {:part_number "F00101"
              :warehouse 1.0
              :on_hand 20.0
              :in_transit 100} (first result))))))
