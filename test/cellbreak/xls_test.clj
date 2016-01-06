(ns cellbreak.xls-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [cellbreak.xls :refer :all]))

(def xlsx-file (io/resource "simple.xlsx"))

(deftest simple-xlsx
  (testing "Opens a simple xlsx file."
    (let [results (io->map xlsx-file)]
      (println results)
      (is (= "Part number" (-> results first :vals first first))
      ))))
