package com.stream.type1;

public class ThreadDemo01 extends Thread {
    private String name;
    private ThreadDemo01(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println(getName());
    }
}
