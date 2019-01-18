package com.java.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Meal {

    private int orderNum = 0;

    Meal(int num) {
        orderNum = num;
    }
    @Override
    public String toString() {
        return "orderNum : " + orderNum;
    }
}


class WaitPerson implements Runnable {

    private Restaurant restaurant;

    WaitPerson(Restaurant rt) {
        restaurant = rt;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                synchronized (restaurant.chef) {
                    System.out.println("take meal : " + restaurant.meal);
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {

        }

    }
}

class Chef implements Runnable {

    private Restaurant restaurant;
    private int count;

    Chef(Restaurant rt) {
        restaurant = rt;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    if (restaurant.meal != null) {
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("Order up.");
                    restaurant.exec.shutdownNow();
                }
                synchronized (restaurant.waitPerson) {

                    restaurant.meal = new Meal(count);
                    System.out.println("make meal : " + restaurant.meal);
                    restaurant.waitPerson.notifyAll();
                }


            }
        } catch (InterruptedException e) {

        }

    }
}
public class Restaurant {

    Meal meal;
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    ExecutorService exec = Executors.newCachedThreadPool();
    Restaurant() {

        exec.execute(waitPerson);
        exec.execute(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
