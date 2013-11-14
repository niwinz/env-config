(ns env-config.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.tools.reader.edn :as edn]
            [env-config.environ :as environ])
  (:gen-class))

(defn- read-env-config
  "Read config env-config file"
  []
  (let [ds (-> "env-config.clj"
               io/resource
               slurp
               edn/read-string
               eval)]
    (if (map? ds) ds {})))

(defn get-config
  ([env]
   {:pre [(keyword? env)]}
   (env (read-env-config)))
  ([]
   (get-config (environ/get-environ-name))))
