package com.singleton.type8;

public class SingletonTest08 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance.equals(instance2));
    }
}

//枚举(线程安全)
enum Singleton {
    INSTANCE;
    public void doSomething(){
        System.out.println("hello Enum");
    }
}
