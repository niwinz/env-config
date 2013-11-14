(ns env-config.environ
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(defn keywordize
  [^String s]
  (if (nil? s)
    s
    (-> (str/lower-case s)
        (str/replace "_" "-")
        (str/replace "." "-")
        (keyword))))

(defn sanitize [k]
  (let [s (keywordize (name k))]
    (if-not (= k s) (println "Warning: environ key " k " was has been corrected to " s))
    s))

(defn read-system-env []
  (->> (System/getenv)
       (map (fn [[k v]] [(keywordize k) v]))
       (into {})))

(defn read-system-props []
  (->> (System/getProperties)
       (map (fn [[k v]] [(keywordize k) v]))
       (into {})))

(defn read-env-file []
  (let [env-file (io/file ".lein-env")]
    (if (.exists env-file)
      (into {} (for [[k v] (read-string (slurp env-file))]
                 [(sanitize k) v])))))

(def ^{:doc "A map of environment variables."}
  env
  (merge
   (read-env-file)
   (read-system-props)
   (read-system-env)))

(defn get-environ-name
  [& {:keys [default] :or {default :dev}}]
  (let [detected (keywordize (:environ env))]
    (if (nil? detected) default detected)))

(def environ-name
  (delay (get-environ-name)))
