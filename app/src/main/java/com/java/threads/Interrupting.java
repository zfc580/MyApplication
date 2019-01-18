package com.java.threads;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 中断
 * 三个阻塞状态，只有sleep是可中断阻塞，read和死锁不可中断
 */
class SleepBlocked implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("start to sleep.");
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocked.run.");
	}
}

class IOBlocked implements Runnable {
	private InputStream in;

	public IOBlocked(InputStream is) {
		in = is;
	}

	@Override
	public void run() {
		try {
			System.out.println("Waitting for read.");
			in.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted from blocked I/O.");
			} else {
				throw new RuntimeException();
			}
		}
		System.out.println("Exiting IOBlocked.run().");
	}
}

class SynchronizedBlocked implements Runnable {

	public synchronized void f() {
		while (true) {
			Thread.yield();
		}
	}

	public SynchronizedBlocked() {
		new Thread() {
			@Override
			public void run() {
				f();
			}
		}.start();
	}

	@Override
	public void run() {
		System.out.println("Trying to call f().");
		f();
		System.out.println("Exiting SynchronizedBlocked.run().");
	}
}

public class Interrupting {

	private static ExecutorService exec = Executors.newCachedThreadPool();

	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r.getClass().getName());
		f.cancel(true);
		System.out.println("Interrupt sent to " + r.getClass().getName());
	}

	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}
}

/**
 * 强调IO不可中断，想要中断阻塞需要关闭底层资源，即调用close()来关闭；
 */
class CloseResource {

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InputStream socketInput = new Socket("localhost", 8080).getInputStream();
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Shutting down all threads. ");
		exec.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing" + socketInput.getClass().getName());
		socketInput.close();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing" + System.in.getClass().getName());
		System.in.close();
	}

}
