package com.java.threads;

/**
 * 每个线程独自持有自己的变量id和countDown
 *
 * @author fucai.zhou
 *
 */
public class LiftOff implements Runnable {

	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {

	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "). ";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			// Thread.yield();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(new LiftOff());
			thread.start();
		}
	}

}
