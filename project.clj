(defproject datasource "0.1.0"
  :description "Environment dependent datasource configuration."
  :url "http://github.com/niwibe/datasource"
  :license {:name "Apache 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.txt"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.reader "0.7.10"]]
  :main ^:skip-aot datasource.test
  :profiles {:uberjar {:aot :all}})
