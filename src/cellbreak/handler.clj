(ns cellbreak.handler
  (:require [compojure.route :as route]
            [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [clojure.java.io :as io]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [cellbreak.xls :as xls]))

(defn parse-spreadsheet
  [req]
  (let [spreadsheet (get (:params req) "spreadsheet")]
    (let [file (:tempfile spreadsheet)]
      (response (xls/wb->map (xls/io->wb file)))
      )))

(defroutes app-routes
           (GET "/" [] (resource-response "index.html" {:root "public"}))
           (POST "/post_spreadsheet" params (parse-spreadsheet params))
           (route/resources "/")
           (route/not-found "Page not found"))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-multipart-params)
      (wrap-defaults api-defaults)))