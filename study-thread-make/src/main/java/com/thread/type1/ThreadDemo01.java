package com.thread.type1;

public class ThreadDemo01 extends Thread {
    public ThreadDemo01(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println(getName());
    }
}
