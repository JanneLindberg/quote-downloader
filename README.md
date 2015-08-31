# quote-downloader

[![Build Status](https://travis-ci.org/JanneLindberg/quote-downloader.svg?branch=master)](https://travis-ci.org/JanneLindberg/quote-downloader)

EOD stock quote downloader


## Example usage

```clojure

(ns quote-downloader.use
  (:require [quote-downloader.core :refer [get-yahoo-stock-data]]))


(def res (get-yahoo-stock-data 
          {:symbol "YHOO" :first "2015-08-20" :last "2015-08-31"}))

;; Simply print it out
(doseq [line (drop 1 (clojure.string/split (:body res) #"\n"))]
  (println line))


```

## Contributors

[Contributors](https://github.com/JanneLindberg/quote-downloader/graphs/contributors)


## License

Copyright (c) 2015 JanneLindberg

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
