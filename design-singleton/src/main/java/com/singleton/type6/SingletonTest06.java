package com.singleton.type6;


public class SingletonTest06 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.equals(instance1));
    }

}

//双重校验( 线程安全 同步代码块 推荐使用 volatile关键字)
class Singleton {

    //1.私有化构造函数
    private Singleton() {

    }

    //2.定义静态变量
    private static volatile Singleton instance = null;


    //3.暴露私有方法提供类实例,用到才创建,所以叫懒汉式
    public static Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
