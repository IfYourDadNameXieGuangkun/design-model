package com.java.jvm.双亲委派;

public class ClassLoaderTest {
    public static void main(String[] args) {
        String s = new String();//java.java.lang 包下
        System.out.println(s.getClass().getClassLoader());//输出为null,说明是BootStrapClassLoader(启动类加载器,C/C++写的,并不继承与ClassLoader)
        //自定义一个 java.java.lang.String
        String s2 = new String();
        System.out.println(s2.getClass().getClassLoader());
    }
}
