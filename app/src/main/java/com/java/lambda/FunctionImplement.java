package com.java.lambda;

import java.util.Arrays;

public class FunctionImplement {

    private static void printTest(String value, TestFunction function) {
        function.call(value);
    }

    public static void main(String[] args) {
        Arrays.asList("a", "c", "n").forEach(element -> printTest(element, System.out::println));
    }
}
