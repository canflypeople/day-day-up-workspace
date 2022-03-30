package com.zmji.util.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/28 11:32 上午
 **/
public class ThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor {

    protected static final StringManager sm = StringManager.getManager("org.apache.tomcat.util.threads.res");

    private final AtomicInteger submittedCount = new AtomicInteger(0);
    private final AtomicLong lastContextStoppedTime = new AtomicLong(0L);

    private final AtomicLong lastTimeThreadKilledItself = new AtomicLong(0L);

    private long threadRenewalDelay = Constants.DEFAULT_THREAD_RENEWAL_DELAY;

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        prestartAllCoreThreads();
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        prestartAllCoreThreads();
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, new RejectHandler());
        prestartAllCoreThreads();
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new RejectHandler());
        prestartAllCoreThreads();
    }

    public long getThreadRenewalDelay() {
        return threadRenewalDelay;
    }

    public void setThreadRenewalDelay(long threadRenewalDelay) {
        this.threadRenewalDelay = threadRenewalDelay;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (!(t instanceof StopPooledThreadException)) {
            submittedCount.decrementAndGet();
        }

        if (t == null) {
            stopCurrentThreadIfNeeded();
        }
    }

    public int getSubmittedCount() {
        return submittedCount.get();
    }

    @Override
    public void execute(Runnable command) {
        execute(command, 0, TimeUnit.MICROSECONDS);
    }

    public void execute(Runnable command, int timeout, TimeUnit unit) {
        submittedCount.incrementAndGet();
        try {
            super.execute(command);
        } catch (RejectedExecutionException rx) {
            if (super.getQueue() instanceof TaskQueue) {
                final TaskQueue queue = (TaskQueue)super.getQueue();
                try {
                    if (!queue.force(command, timeout, unit)) {
                        submittedCount.decrementAndGet();
                        throw new RejectedExecutionException(sm.getString("threadPoolExecutor.queueFull"));
                    }
                } catch (InterruptedException x) {
                    submittedCount.decrementAndGet();
                    throw new RejectedExecutionException(x);
                }
            } else {
                submittedCount.decrementAndGet();
                throw rx;
            }
        }
    }

    protected void stopCurrentThreadIfNeeded() {
        if (currentThreadShouldBeStopped()) {
            long lastTime = lastTimeThreadKilledItself.longValue();
            if (lastTime + threadRenewalDelay < System.currentTimeMillis()) {
                if (lastTimeThreadKilledItself.compareAndSet(lastTime, System.currentTimeMillis() - 1)) {
                    final String msg = sm.getString("threadPoolExecutor.threadStoppedToAvoidPotentialLeak");
                    throw new StopPooledThreadException(msg);
                }
            }
        }
    }

    protected boolean currentThreadShouldBeStopped() {
        if (threadRenewalDelay >= 0 && Thread.currentThread() instanceof TaskThread) {
            TaskThread currentTaskThread = (TaskThread)Thread.currentThread();
            if (currentTaskThread.getCreationTime() < this.lastContextStoppedTime.longValue()) {
                return true;
            }
        }

        return false;
    }

    public void contextStopping() {
        this.lastContextStoppedTime.set(System.currentTimeMillis());

        int savedCorePoolSize = this.getCorePoolSize();
        TaskQueue taskQueue = getQueue() instanceof TaskQueue ? (TaskQueue)getQueue() : null;
        if (taskQueue != null) {
            taskQueue.setForcedRemainingCapacity(0);
        }

        this.setCorePoolSize(0);

        if (taskQueue != null) {
            taskQueue.resetForcedRemainingCapacity();
        }

        this.setCorePoolSize(savedCorePoolSize);
    }

    private static class RejectHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, java.util.concurrent.ThreadPoolExecutor executor) {
            throw new RejectedExecutionException();
        }
    }
}
