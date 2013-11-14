(ns env-config.test
  (:require [env-config.core :as ds]
            [env-config.environ :as env])
  (:gen-class))

(defn -main
  [& [env-name]]
  (println "Received parameter:" (env/keywordize env-name))
  (println "Current env detected:" (env/get-environ-name)))
