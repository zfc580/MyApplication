package com.java.designmode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] args) {

        final Shape shape = new Circle();
        Shape myInstance = (Shape) Proxy.newProxyInstance(shape.getClass().getClassLoader(),
                shape.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("draw the circle in the handler ... ");
                return method.invoke(shape, args);
            }
        });
        myInstance.draw();
    }
}

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("draw the circle... ");
    }
}