package com.java.threads;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 被互斥所阻塞
 * ReentrantLock上阻塞的任务具备可以被中断的能力，这与synchronize方法或临界区上阻塞的任务完全不同
 */
class BlockedMutex {

    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}

class Blocked2 implements Runnable {

    //线程1在构造函数中调用lock.lock()获取了锁
    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        //线程2再次获取锁时失败
        blocked.f();
        System.out.println("Broken out of blocked call");
    }
}

public class Interrupting2 {


    public static void main(String[] args) {
        Thread t = new Thread(new Blocked2());
        t.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}
