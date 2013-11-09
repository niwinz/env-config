(ns datasource.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.tools.reader.edn :as edn]
            [datasource.environ :as environ])
  (:gen-class))

(defn- read-datasource
  "Read config datasource file"
  []
  (let [ds (-> "datasource.clj"
               io/resource
               slurp
               edn/read-string
               eval)]
    (if (map? ds) ds {})))

(defn get-config
  ([env]
   {:pre [(keyword? env)]}
   (env (read-datasource)))
  ([]
   (get-config (environ/get-environ-name))))
