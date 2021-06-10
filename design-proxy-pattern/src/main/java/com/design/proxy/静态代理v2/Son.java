package com.design.proxy.静态代理v2;

/**
 * NO2 儿子对象
 * 儿子要找对象
 */
public class Son implements  Person {

    @Override
    public void findLove() {
        //我没时间,工作忙
        System.out.println("儿子要求:肤白貌美大长腿");
    }
}
