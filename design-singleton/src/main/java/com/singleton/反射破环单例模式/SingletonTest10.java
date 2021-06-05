package com.singleton.反射破环单例模式;


import java.lang.reflect.Constructor;

/**
 * 静态类部类单例模式,用反射破坏单例,//基于反射破坏单例模式进行优化 暴力反射创建对象不允许创建多个实例
 */
public class SingletonTest10 {
    public static void main(String[] args) {
        /**
         * 静态内部类是最佳,不浪费内存,效率也高,但是所有的懒汉或者饿汉,都会被反射破坏
         */
        Singleton_ instance = Singleton_.getInstance();
        Singleton_ instance1 = Singleton_.getInstance();
        System.out.println(instance == instance1);//得到单例对象
        //试图用反射进行破坏,抛出异常
        Class<?> clazz = Singleton_.class;
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
class Singleton_ {

    //1.私有化构造函数
    private Singleton_() {
        //基于反射破坏单例模式进行优化
        if (SingletonInstance.INSTANCE != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    //2.静态内部类,再类加载的时候 变量不会被实例化
    private static class SingletonInstance {
        private static final Singleton_ INSTANCE = new Singleton_();
    }

    //3.暴露私有方法提供类实例,
    public static Singleton_ getInstance() {
        return SingletonInstance.INSTANCE;
    }


}
