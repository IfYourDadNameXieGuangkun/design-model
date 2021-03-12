package com.adapter.interfaceadapter;

public class Client {
    public static void main(String[] args) {
        AbstractAdapter abstractAdapter = new AbstractAdapter() {
            @Override
            public String inter1() {
                System.out.println("我只想实现inter1");
                return "hello Adapter";
            }
        };
        abstractAdapter.inter1();
    }
}
