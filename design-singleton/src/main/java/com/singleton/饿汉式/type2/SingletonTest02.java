package com.singleton.饿汉式.type2;


public class SingletonTest02 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.equals(instance1));
    }

}

//饿汉式(静态代码块)
class Singleton {

    //1.私有化构造函数
    private Singleton() {

    }

    //2.定义静态变量
    private static Singleton instance;

    //3.静态代码块
    static {
        instance = new Singleton();
    }

    //3.暴露私有方法提供类实例
    public static Singleton getInstance() {
        return instance;
    }

}
