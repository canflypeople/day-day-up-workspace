package com.zmji.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : zhongmou.ji
 * @date : 2021/11/26 5:59 下午
 **/
public class SimpleRateLimitDemo {

    public static void main(String[] args) {
        // 限流器流速：2个请求/秒
        RateLimiter limiter = RateLimiter.create(2.0);
        // 执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        // 记录上一次执行的时间
        AtomicReference<Long> prev = new AtomicReference<Long>(System.nanoTime());
        Long cur = prev.get();
        // 测试执行20次
        for (int i = 0; i < 20; i++) {
            // 限流器限速
            limiter.acquire();
            // 提交任务异步执行
            es.execute(() -> {
                System.out.println((cur - prev.get()) / 1000_000);
                prev.set(cur);
            });
        }

    }

    static class RateLimiter {
        /**
         * 以什么样的频率生成令牌
         *
         * @param rate
         * @return
         */
        public static RateLimiter create(double rate) {
            return null;
        }

        public void acquire() {}
    }

}
