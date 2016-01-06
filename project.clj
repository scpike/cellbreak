(defproject cellbreak "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [dk.ative/docjure "1.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 [compojure "1.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.4.0"]
                 ]
  :plugins [[lein-ring "0.7.3"]
            [cider/cider-nrepl "0.10.0"]]
  :ring {:handler cellbreak.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]
         :resource-paths ["test/resources"]}})
