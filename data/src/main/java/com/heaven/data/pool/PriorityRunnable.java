package com.heaven.data.pool;

/**
 * 作者:Heaven
 * 时间: on 2017/4/6 11:16
 * 邮箱:heavenisme@aliyun.com
 */

public class PriorityRunnable implements Runnable {

    /**
     * 线程优先级
     */
    public enum Priority {
        HIGH, NORMAL, LOW
    }

    public final Priority priority;//任务优先级
    private final Runnable runnable;//任务真正执行者
    /*package*/ long SEQ;//任务唯一标示

    public PriorityRunnable(Priority priority, Runnable runnable) {
        this.priority = priority == null ? Priority.NORMAL : priority;
        this.runnable = runnable;
    }

    @Override
    public final void run() {
        this.runnable.run();
    }
}
