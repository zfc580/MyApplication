package com.java.javadoc;

import java.util.HashMap;
import java.util.Map;

@testA(name = "type", gid = Long.class)
public class UserAnnotation {

	@testA(name = "param", id = 1, gid = Long.class)
	// 使用了类成员注解
	private Integer age;

	@testA(name = "construct", id = 2, gid = Long.class)
	// 使用了构造方法注解
	public UserAnnotation() {

	}

	@testA(name = "public method", id = 3, gid = Long.class)
	// 使用了类方法注解
	public void a() {

		// 使用了局部变量注解
		Map m = new HashMap(0);
	}

	@testA(name = "protected method", id = 4, gid = Long.class)
	// 类方法注解
	protected void b() {
		Map m = new HashMap(0);
	}

	@testA(name = "private method", id = 5, gid = Long.class)
	// 类方法注解
	private void c() {
		Map m = new HashMap(0);
	}

	public void b(Integer a) {
	}
}
