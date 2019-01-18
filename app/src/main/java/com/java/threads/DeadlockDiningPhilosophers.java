package com.java.threads;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Chopstick {
    private boolean taken = false;

    public synchronized void take() {
        try {
            System.out.println("take start taken = " + taken);
            while (taken) {
                wait();
            }
            taken = true;
        } catch (InterruptedException e) {

        }
        System.out.println("take start end. ");
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}

class Philosopher implements Runnable {

    private Chopstick left;
    private Chopstick right;
    private int id;
    private int ponderFactor;

    Philosopher(Chopstick l, Chopstick r, int id, int factor) {
        this.left = l;
        this.right = r;
        this.id = id;
        this.ponderFactor = factor;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        Random random = new Random();
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + "thinking. ");
                pause();
                System.out.println(this + "take right. ");
                right.take();
                System.out.println(this + "take left. ");
                left.take();
                System.out.println(this + "thinking. ");
                pause();
                System.out.println(this + "drop right. ");
                right.drop();
                System.out.println(this + "drop left. ");
                left.drop();

            }
        } catch (InterruptedException e) {

        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id + ": ";
    }
}


public class DeadlockDiningPhilosophers {


    public static void main(String[] args) throws IOException {
        int size = 2;
        int poder = 5;
        Chopstick[] sticks = new Chopstick[size];
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, poder));
        }
        System.out.println("press Enter to end.");
        System.in.read();
        exec.shutdownNow();
    }
}
