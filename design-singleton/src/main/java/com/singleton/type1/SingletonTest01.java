package com.singleton.type1;

public class SingletonTest01 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.equals(instance1));

    }

}

//饿汉式(静态变量)
class Singleton {
    //1.私有构造方法(防止New)
    private Singleton() {
    }

    //2.内部创建对象实例
    private static final Singleton instance = new Singleton();

    //3.暴露私有方法提供类实例
    public static Singleton getInstance() {
        return instance;
    }
}