package com.java.javadoc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ParseAnnotation {

	public static void parseTypeAnnotation() throws ClassNotFoundException {

		Class clazz = Class.forName("com.java.javadoc.UserAnnotation");
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			testA test = (testA) annotation;
			System.out.println("id=" + test.id() + ",name=" + test.name() + ",gid=" + test.gid());
		}

	}

	public static void parseMethodAnnotation() {
		Method[] methods = UserAnnotation.class.getDeclaredMethods();
		for (Method method : methods) {
			boolean hasAnnotation = method.isAnnotationPresent(testA.class);
			if (hasAnnotation) {
				testA test = method.getAnnotation(testA.class);
				System.out.println("method=" + method.getName() + ",id=" + test.id() + ",gid=" + test.gid());
			}
		}
	}

	public static void parseConstructAnnotation() {
		Constructor[] constructors = UserAnnotation.class.getConstructors();
		for (Constructor constructor : constructors) {

			boolean hasAnnotation = constructor.isAnnotationPresent(testA.class);
			if (hasAnnotation) {

				testA annotation = (testA) constructor.getAnnotation(testA.class);
				System.out.println("constructor = " + constructor.getName() + " ; id = " + annotation.id()
						+ " ; description = " + annotation.name() + "; gid= " + annotation.gid());
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			parseTypeAnnotation();
		} catch (Exception e) {
			// TODO: handle exception
		}
		parseMethodAnnotation();
		parseConstructAnnotation();
	}

}
