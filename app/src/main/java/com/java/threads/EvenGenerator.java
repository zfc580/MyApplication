package com.java.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class IntGenerator {
	private volatile boolean canceled = false;

	public abstract int next();

	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}
}

class EvenChecker implements Runnable {

	private IntGenerator generator;
	private final int id;

	EvenChecker(IntGenerator g, int ident) {
		generator = g;
		id = ident;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				generator.cancel();
			}
			System.out.println("# " + id + ", val = " + val);
		}
	}

	static void test(IntGenerator g, int count) {
		System.out.println("Press ctrl + c to exit.");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(g, i));
		}
		exec.shutdownNow();
	}

	static void test(IntGenerator gp) {
		test(gp, 3);
	}

}

public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
