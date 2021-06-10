package com.design.proxy.动态代理.JDK实现;

import com.design.proxy.静态代理v2.Person;

/**
 * NO2单身客户
 */
public class Customer implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("180cm150kg180mm");
    }
}
