(ns quote-downloader.core-test
  (:import [org.joda.time LocalDate])
  (:require [clojure.test :refer :all]
            [quote-downloader.core :refer :all]
            [clj-time.core :as t]))


(deftest test-1
  (testing "default dates"
    (let [date_1 "2013-01-01"
          today (t/today)]

      (is (= (str default-start-date) (str (start-date nil))))
      (is (instance? LocalDate (start-date nil)) "Expected date only")

      (is (= "2013-01-01" (str (start-date date_1))))
      (is (instance? LocalDate (start-date date_1)) "Expected date only")

      (is (= today (end-date nil)))
      (is (instance? LocalDate (end-date nil)) "Expected date only")

      (is (= "2013-01-01" (str (end-date date_1))))
      (is (instance? LocalDate (end-date date_1)) "Expected date only")

      )))


(deftest test-2
  (testing "query map"
    (let [query (yahoo-query-map "TEST"  (t/local-date 2013 01 02) (t/local-date 2015 3 4))]

      (is (= "TEST" (:s query)))

      (is (= "d" (:g query)))

      (is (= 2013 (:c query))) ;; start date
      (is (= 0 (:a query))) ;; start month
      (is (= 2 (:b query))) ;; start day

      (is (= 2015 (:f query))) ;; last date
      (is (= 2 (:d query))) ;; last month
      (is (= 4 (:e query))) ;; last day

    )))

(run-tests 'quote-downloader.core-test)
