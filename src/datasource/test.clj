(ns datasource.test
  (:require [datasource.core :as ds]
            [datasource.environ :as env])
  (:gen-class))

(defn -main
  [& [env-name]]
  (println "Received parameter:" (env/keywordize env-name))
  (println "Current env detected:" (env/get-environ-name)))
