package com.singleton.静态类部类.type7;


import java.lang.reflect.Constructor;

public class SingletonTest07 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance.equals(instance1));

        /**
         * 静态内部类是最佳,不浪费内存,效率也高,但是所有的懒汉或者饿汉,都会被反射破坏
         */
        Class<?> clazz = Singleton.class;
        try {
            Constructor c = clazz.getDeclaredConstructor(null);
            c.setAccessible(true);
            Object o = c.newInstance();
            Object o1 = c.newInstance();
            System.out.println(o == o1);//false
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//静态类部类(利用JVM类加载机制实现 线程安全,其实ClassLoader.loadClass 就是一个被synchronize修饰的方法,所以在类创建的时候是线程安全的)
class Singleton {

    //1.私有化构造函数
    private Singleton() {

    }

    //2.静态内部类,再类加载的时候 变量不会被实例化
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    //3.暴露私有方法提供类实例,
    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
