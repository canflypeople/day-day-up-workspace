package com.zmji.util.thread;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/28 1:40 下午
 **/
public class TaskThread extends Thread {

    // private static final Log log = LogFactory.getLog(TaskThread.class);

    private final long creationTime;

    public TaskThread(ThreadGroup group, Runnable target, String name) {
        super(group, new WrappingRunnable(target), name);
        this.creationTime = System.nanoTime();
    }

    public long getCreationTime() {
        return creationTime;
    }

    private static class WrappingRunnable implements Runnable {
        private Runnable wrappedRunnable;

        WrappingRunnable(Runnable wrappedRunnable) {
            this.wrappedRunnable = wrappedRunnable;
        }

        @Override
        public void run() {
            try {
                wrappedRunnable.run();
            } catch (StopPooledThreadException exc) {
                // log.debug...
            }
        }
    }
}
