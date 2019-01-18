package com.java.threads;

public class SimpleDeadLockDemo {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    static class ThreadA extends Thread {

        @Override
        public void run() {

            synchronized (lockA) {
                System.out.println("ThreadA lockA");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println("ThreadA lockB");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    static class ThreadB extends Thread {

        @Override
        public void run() {
            synchronized (lockB) {
                System.out.println("ThreadB lockB");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.println("ThreadB lockA");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        a.start();
        b.start();

    }
}
