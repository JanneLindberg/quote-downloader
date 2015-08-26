(ns quote-downloader.core-test
  (:require [clojure.test :refer :all]
            [quote-downloader.core :refer :all]
            [clj-time.core :as t]))


(deftest test-1
  (testing "default dates"
    (let [
          date_1 (t/local-date 2013 1 1)
          date_0 (t/local-date 2000 1 1)
          today (t/today)
          ]

      (is (= "2000-01-01" (str (start-date nil))))
      (is (= "2013-01-01" (str (start-date date_1))))

      (is (= today (end-date nil)))
      (is (= "2013-01-01" (str (end-date date_1))))

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
