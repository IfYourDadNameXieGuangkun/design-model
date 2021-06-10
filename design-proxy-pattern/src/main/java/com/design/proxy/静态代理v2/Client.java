package com.design.proxy.静态代理v2;

public class Client {
    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }
}
