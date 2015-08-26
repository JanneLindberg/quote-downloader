(ns quote-downloader.core
  (:require
   [org.httpkit.client :as http]
   [clj-time.core :as t]))


(def yahoo-url "http://itable.finance.yahoo.com/table.csv")
(def default-start-date (t/local-date 2000 01 01))

(defn yahoo-query-map
  "Yahoo query parameters"
  [ticker startDate endDate]
  (let [start startDate end endDate]
    {
     :s ticker
     :g "d"
     :a (- (.getMonthOfYear start) 1)
     :b (.getDayOfMonth start)
     :c (.getYear start)
     :d (- (.getMonthOfYear end) 1)
     :e (.getDayOfMonth end)
     :f (.getYear end)
     }))


(defn start-date
  "Return the start date or an ancient date if nil "
  [startDate]
  (or startDate default-start-date))


(defn end-date
  "Return todays date if end date is nil"
  [endDate]
  (or endDate (t/today)))


(defn get-yahoo-stock-data [entry]
  (let [symb (:symbol entry)
        startDate (start-date (:first entry))
        endDate (end-date (:last entry))
        url yahoo-url
        options {
                 :timeout 1000
                 :query-params (yahoo-query-map symb startDate endDate)
                 }

        {:keys [status headers body error] :as resp} @(http/get url options)
        ]

    (if-not error
      {:status status :headers headers :body body}
      {:status status :error error}
      )))
