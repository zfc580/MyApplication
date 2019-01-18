package com.java.threads;

/**
 * join()方法是等待调用该方法的线程被唤醒，调用时即被阻塞；如果该线程被中断了，该方法阻塞也结束
 *
 * @author fucai.zhou
 *
 */
class Sleeper extends Thread {

	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}

	@Override
	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interrupted. " + " isInterrupted(): " + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened");
	}

}

class Joiner extends Thread {
	private Sleeper mSleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		mSleeper = sleeper;
		start();
	}

	@Override
	public void run() {

		try {
			mSleeper.join();
		} catch (InterruptedException e) {
			System.out.println("interrupted.");
		}
		System.out.println(getName() + " join completed");
	}

}

public class Joining {

	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy", 1500);
		Sleeper grumpy = new Sleeper("Grumpy", 1500);
		Joiner dopey = new Joiner("Dopey", sleepy);
		Joiner doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}

}
