package com.singleton.懒汉式.type5;


public class SingletonTest05 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.equals(instance1));
    }

}

//懒汉式(线程不安全)
class Singleton {

    //1.私有化构造函数
    private Singleton() {

    }

    //2.定义静态变量
    private static Singleton instance = null;


    //3.暴露私有方法提供类实例,用到才创建,所以叫懒汉式
    public static Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }

}
