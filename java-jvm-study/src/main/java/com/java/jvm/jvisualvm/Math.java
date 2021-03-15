package com.java.jvm.jvisualvm;

public class Math {
    public static final int initData = 666;
    public static User user;

    public int compute() {
        int a = 1;
        int b = 2;
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        int compute = math.compute();
        System.out.println(compute);
    }
}
