(defproject work "0.0.1"
  :description ""
  :dependencies
    [[org.clojure/clojure "1.8.0"]
     [org.clojure/core.match "0.3.0-alpha4"]
     [org.clojure/core.incubator "0.1.4"]
     [mount "0.1.11"]
     [yesql "0.5.3"]
     [mysql/mysql-connector-java "5.1.32"]
     [http-kit "2.2.0"]
     [compojure "1.5.1"]
     [hiccup "1.0.5"]
     [reinvdwoerd/sandbox "0.0.1"]
     [clj-time "0.13.0"]
     [clojurewerkz/money "1.9.0"]]
  :main work
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["test"]
                   :dependencies [[midje "1.8.3"]
                                  [proto-repl "0.3.1"]]}})
