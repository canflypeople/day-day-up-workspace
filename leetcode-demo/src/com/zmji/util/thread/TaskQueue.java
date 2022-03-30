package com.zmji.util.thread;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/28 1:26 下午
 **/
public class TaskQueue extends LinkedBlockingQueue<Runnable> {

    private static final long serialVersionUID = 1L;
    protected static final StringManager sm = StringManager.getManager("org.apache.tomcat.util.threads.res");
    private static final int DEFAULT_FORCE_REMAINING_CAPACITY = -1;

    private transient volatile ThreadPoolExecutor parent = null;

    private int forcedRemainingCapacity = -1;

    public TaskQueue() {
        super();
    }

    public TaskQueue(int capacity) {
        super(capacity);
    }

    public TaskQueue(Collection<? extends Runnable> c) {
        super(c);
    }

    public void setParent(ThreadPoolExecutor tp) {
        parent = tp;
    }

    public boolean force(Runnable o) {
        if (parent == null || parent.isShutdown()) {
            throw new RejectedExecutionException(sm.getString("taskQueue.notRunning"));
        }
        return super.offer(o);
    }

    public boolean force(Runnable o, long timeout, TimeUnit unit) throws InterruptedException {
        if (parent == null || parent.isShutdown()) {
            throw new RejectedExecutionException(sm.getString("taskQueue.notRunning"));
        }
        return super.offer(o, timeout, unit);
    }

    @Override
    public boolean offer(Runnable o) {
        if (parent == null) {
            return super.offer(o);
        }

        if (parent.getPoolSize() == parent.getMaximumPoolSize()) {
            return super.offer(o);
        }

        if (parent.getSubmittedCount() <= (parent.getPoolSize())) {
            return super.offer(o);
        }

        if (parent.getPoolSize() < parent.getMaximumPoolSize()) {
            return false;
        }

        return super.offer(o);
    }

    @Override
    public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
        Runnable runnable = super.poll(timeout, unit);
        if (runnable == null && parent != null) {
            parent.stopCurrentThreadIfNeeded();
        }
        return runnable;
    }

    @Override
    public Runnable take() throws InterruptedException {
        if (parent != null && parent.currentThreadShouldBeStopped()) {
            return poll(parent.getKeepAliveTime(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS);
        }
        return super.take();
    }

    @Override
    public int remainingCapacity() {
        if (forcedRemainingCapacity > DEFAULT_FORCE_REMAINING_CAPACITY) {
            return forcedRemainingCapacity;
        }

        return super.remainingCapacity();
    }

    public void setForcedRemainingCapacity(int forcedRemainingCapacity) {
        this.forcedRemainingCapacity = forcedRemainingCapacity;
    }

    void resetForcedRemainingCapacity() {
        this.forcedRemainingCapacity = DEFAULT_FORCE_REMAINING_CAPACITY;
    }

}
