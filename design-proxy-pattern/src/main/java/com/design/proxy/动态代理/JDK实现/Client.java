package com.design.proxy.动态代理.JDK实现;

import com.design.proxy.静态代理v2.Person;

public class Client {
    public static void main(String[] args) {
        try {
            Person person = (Person) new JDKMeipo().getInstance(new Customer());
            person.findLove();
        } catch (Exception e) {

        }
    }
}
