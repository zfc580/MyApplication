package com.java.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 12.4 终结任务————装饰者花园
 * 	1.Count.increment()和Count.value()的是synchronized的，保持各个任务间的互斥性；
 * 	2.当调用Entrance.cancel()突然中断时，仍然能保证数据没有出错；
 */
class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (rand.nextBoolean()) {
			Thread.yield();
		}
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

class Entrance implements Runnable {

	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private static volatile boolean canceled = false;
	private int number = 0;
	private final int id;

	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}

	@Override
	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + " Total=" + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		System.out.println("Stopping " + this);
	}

	public static void cancel() {
		canceled = true;
	}

	public synchronized int getValue() {
		return number;
	}

	@Override
	public String toString() {
		return "Entrance#" + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}

}

public class OrnamentalGarden {

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Entrance(i));
		}
		TimeUnit.SECONDS.sleep(3);
		//Entrance.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("Some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}

}
