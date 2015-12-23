(ns cellbreak.xls
  (:require [dk.ative.docjure.spreadsheet :as sheet]
            [clojure.data.json :as json]
            [clojure.java.io :as io]))

(extend-type java.util.Date
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(defn parse-spreadsheet
  "Grab rows from an xls file"
  [fname]
  (sheet/load-workbook-from-file fname)
  )

(defn io->wb
  "Grab rows from an xls file"
  [io]
  (sheet/load-workbook (io/input-stream io)))

(defn row-vals
  "Print the values of a row"
  [row]
  (map sheet/read-cell row))

(defn munge-sheet
  "Take a xlsx worksheet and munge it into a map"
  [s & [limit]]
  (let [rvals (map row-vals (sheet/row-seq s))
        sname (sheet/sheet-name s)]
    {:name sname
     :vals (if limit (take limit rvals) rvals)}
    ))

(defn wb->map
  "Convert a sheet to json"
  [wb] (let [sheets (sheet/sheet-seq wb)
             munged (map munge-sheet sheets)]
         munged))

(defn wb->json
  [wb]
  (json/write-str (wb->map wb)))

(def fpath "C:\\Users\\spike\\Downloads\\prices.xlsx")
(def wb (parse-spreadsheet fpath))
(def sh1 (first (sheet/sheet-seq wb)))
(def sheet-data (map row-vals (sheet/row-seq sh1)))


