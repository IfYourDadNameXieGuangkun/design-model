package com.singleton.懒汉式.type4;


public class SingletonTest04 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.equals(instance1));
    }

}

//懒汉式(线程安全,同步方法synchronize关键字)
class Singleton {

    //1.私有化构造函数
    private Singleton() {

    }

    //2.定义静态变量
    private static Singleton instance = null;


    //3.暴露私有方法提供类实例,用到才创建,所以叫懒汉式
    public static synchronized Singleton getInstance() {
        if (null == instance) {
            instance = new Singleton();
        }
        return instance;
    }

}
