package com.stufy.threadpool;

public class ThreadDemo extends Thread {


    private String name;

    public ThreadDemo(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name);
    }


    public static void main(String[] args) {
        new ThreadDemo("aye").run();
        new ThreadDemo("aye").start();
    }
}
