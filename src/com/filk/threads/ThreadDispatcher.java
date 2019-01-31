package com.filk.threads;

public class ThreadDispatcher {
    private int threadCount;
    private int MAX_THREAD_COUNT;

    public ThreadDispatcher(int MAX_THREAD_COUNT) {
        this.MAX_THREAD_COUNT = MAX_THREAD_COUNT;
    }

    public synchronized void checkNewThread() throws InterruptedException {
        if(threadCount >= MAX_THREAD_COUNT) {
            this.wait();
        }
        threadCount++;
    }

    public synchronized void releaseThread() {
        threadCount--;
        this.notify();
    }

}
