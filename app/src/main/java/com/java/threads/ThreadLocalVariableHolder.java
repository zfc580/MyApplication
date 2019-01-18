package com.java.threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * ThreadLocal能让每个线程都独立使用一块内存，只要该线程还活着，如果线程不在了，则该线程对ThreadLocal
 * 的拷贝内存将会被回收
 * 
 * @author fucai.zhou
 *
 */
class Accessor implements Runnable {
	private final int id;

	public Accessor(int idn) {
		id = idn;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}

	@Override
	public String toString() {
		return "#" + id + ":" + ThreadLocalVariableHolder.get();
	}
}

public class ThreadLocalVariableHolder {

	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);

		@Override
		protected synchronized Integer initialValue() {
			int randomInt = rand.nextInt(10000);
			System.out.println("randomInt = " + randomInt);
			return randomInt;
		}
	};

	public static void increment() {
		value.set(value.get() + 1);
	}

	public static int get() {
		return value.get();
	}

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.execute(new Accessor(i));
		}
		TimeUnit.MILLISECONDS.sleep(20);
        executor.execute(new Accessor(8));
		executor.shutdownNow(); //关闭所有正在执行的任务，包括未执行的任务
		//executor.shutdown(); //关闭线程池，不再接受新任务，就任务等待执行完

	}

}
