package com.design.proxy.动态代理.JDK实现;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 媒婆类(婚介所)
 */
public class JDKMeipo implements InvocationHandler {
    //被代理的对象,把引用保存下来
    private Object target;

    public Object getInstance(Object target) throws Exception {
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target, args);
        after();
        return obj;
    }

    private void before() {
        System.out.println("我是媒婆,我要给你找对象,现在已经确认你的需求");
        System.out.println("开始物色");
    }
    private void after() {
        System.out.println("如果合适,就洞房吧!");
    }
}
