package com.test;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class measures throughput of both approaches.
 * Although result is obvious, someone had to do it in this world
 */
public class SynchronizedListVsCopyOnWriteArrayListReadBenchmark {
    private static final int CAPACITY = 1_000_000;
    private static final int NUM_OF_THREADS = 10;

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private static final List<Integer> syncedList;
        private static final List<Integer> copyOnWriteArrayList;

        static {
            syncedList = fillList(Collections.synchronizedList(new ArrayList<>(CAPACITY)), CAPACITY);
            copyOnWriteArrayList = new CopyOnWriteArrayList<>(syncedList);
        }

        private static List<Integer> fillList(List list, int capacity) {
            for (int i = 0; i < capacity; i++) {
                list.add(i);
            }
            return list;
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void readSyncedList() {
        readList(BenchmarkState.syncedList, NUM_OF_THREADS);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void readCopyOnWriteArrayList() {
        readList(BenchmarkState.copyOnWriteArrayList, NUM_OF_THREADS);
    }

    /* TODO: method body is too long, should be split into short parts*/
    private void readList(List<Integer> list, int numOfThreads) {
        AtomicInteger threadCount = new AtomicInteger(numOfThreads);

        CountDownLatch latchForStart = new CountDownLatch(numOfThreads);
        CountDownLatch latchForFinish = new CountDownLatch(numOfThreads);

        List<Thread> workers = Stream
                .generate(() -> new Thread(() -> {
                    int rangeLength = CAPACITY /numOfThreads;
                    int count = threadCount.decrementAndGet()*rangeLength;

                    latchForStart.countDown();
                    try {
                        latchForStart.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    int start = count != 0 ? (count + 1) : count;
                    int end = count + rangeLength;

                    Integer obj;
                    for (int i = start; i < end; i++) {
                        try {
                            obj = list.get(i);
                            obj.hashCode(); //to avoid compiler optimization like removing previous line of code as unused
                        } catch (Exception e) {
                            throw new RuntimeException("i=" + i + ", size=" + list.size());
                        }
                    }
                    latchForFinish.countDown();
                }))
                .limit(numOfThreads)
                .collect(Collectors.toList());

        workers.stream().forEach((t) -> t.start());
        try {
            latchForFinish.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(SynchronizedListVsCopyOnWriteArrayListReadBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            throw new RuntimeException(e);
        }
    }
}