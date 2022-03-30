package com.zmji.year.three.month.one;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/19 1:42 下午
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
        // 打印线程池的信息，稍后我会解释这段代码
        printStats(threadPool);
        for (int i = 0; i < 7000L; i++) {
            threadPool.execute(() -> {
                String payload = IntStream.rangeClosed(1, 1000).mapToObj(__ -> "a").collect(Collectors.joining(""))
                    + UUID.randomUUID().toString();
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println(payload);
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    private static void printStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("=========================");
            System.out.println("Pool Size: {}" + threadPool.getPoolSize());
            System.out.println("Active Threads: {}" + threadPool.getActiveCount());
            System.out.println("Number of Tasks Completed: {}" + threadPool.getCompletedTaskCount());
            System.out.println("Number of Tasks in Queue: {}" + threadPool.getQueue().size());

            System.out.println("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
