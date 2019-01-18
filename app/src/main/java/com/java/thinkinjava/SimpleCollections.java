package com.java.thinkinjava;

import java.util.Vector;

class Cat {
	private int catNumber;

	Cat(int i) {
		catNumber = i;
	}

	void print() {
		System.out.println("Cat #" + catNumber);
	}
}

class Dog {
	private int dogNumber;

	Dog(int i) {
		dogNumber = i;
	}

	void print() {
		System.out.println("Dog #" + dogNumber);
	}
}

public class SimpleCollections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector cats = new Vector();
		for (int i = 0; i < 7; i++) {
			cats.addElement(new Cat(i));
		}
		// Not a problem to add a dog to cats:
		cats.addElement(new Dog(7));
		for (int i = 0; i < cats.size(); i++) {
			((Cat) cats.elementAt(i)).print();
			// Dog is detected only at run-time
		}
	}

}
