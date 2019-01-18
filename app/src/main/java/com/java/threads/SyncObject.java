package com.java.threads;

/**
 * 通过yield()使线程强制调度，是为了突出证明synchronized的作用
 *
 * @author fucai.zhou
 *
 */
class DualSynch {

	private Object syncObject = new Object();

	public void f() {
		synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("f()");
                Thread.yield();
            }
        }
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			@Override
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}

}
