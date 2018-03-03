# SynchronizedListVsCopyOnWriteArrayListReadBenchmark
## Test results for reading one million elements list with 10 threads
Benchmark                                                                      Mode  Cnt    Score    Error  Units
SynchronizedListVsCopyOnWriteArrayListReadBenchmark.readCopyOnWriteArrayList  thrpt   20  507,972 ± 31,349  ops/s
SynchronizedListVsCopyOnWriteArrayListReadBenchmark.readSyncedList            thrpt   20   25,768 ±  0,479  ops/s

Result "com.test.SynchronizedListVsCopyOnWriteArrayListReadBenchmark.readCopyOnWriteArrayList":
  507,972 ±(99.9%) 31,349 ops/s [Average]
  (min, avg, max) = (377,720, 507,972, 528,198), stdev = 36,102
  CI (99.9%): [476,623, 539,322] (assumes normal distribution)

Result "com.test.SynchronizedListVsCopyOnWriteArrayListReadBenchmark.readSyncedList":
  25,768 ±(99.9%) 0,479 ops/s [Average]
  (min, avg, max) = (24,647, 25,768, 27,192), stdev = 0,552
  CI (99.9%): [25,289, 26,247] (assumes normal distribution)

