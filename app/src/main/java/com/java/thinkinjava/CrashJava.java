package com.java.thinkinjava;

import java.util.Vector;

public class CrashJava {
	/**
	 * 编译器就在一个字串后面发现了一个“+”以及好象并非字串的其他东西，所以它会试图将this转换成一个
	 * 字串。转换时调用的是toString()，后者会产生一个递归调用，陷入死循环，进而出现StackOverflowError。
	 */
	@Override
	public String toString() {
		return "CrashJava address: " + this + "\n";
	}

	public static void main(String[] args) {
		Vector v = new Vector();
		for (int i = 0; i < 10; i++) {
			v.addElement(new CrashJava());
		}
		System.out.println(v);
	}
}