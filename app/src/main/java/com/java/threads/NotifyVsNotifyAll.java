package com.java.threads;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * notify()与notifyAll()
 */
class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread() + "");
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

    synchronized void prod() {
        //如果同一个锁有多个线程在等待，调用该方法只会选择其中任意一个线程唤醒
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class Task implements Runnable {
    static Blocker blocker = new Blocker();
    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable {
    static Blocker blocker = new Blocker();
    @Override
    public void run() {
        blocker.waitingCall();
    }
}


public class NotifyVsNotifyAll {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            System.out.println(i+"");
            exec.execute(new Task());
        }
        exec.execute(new Task2());
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;
            @Override
            public void run() {
                if (prod) {
                    System.out.println("\nnotify");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.println("\nnotifyAll");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer canceled.");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nTask2.blocker.prodAll();");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down.");
        exec.shutdownNow();
    }
}
