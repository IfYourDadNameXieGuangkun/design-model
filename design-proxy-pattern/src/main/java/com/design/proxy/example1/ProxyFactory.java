package com.design.proxy.example1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂类 创建动态代理对象 ，动态代理不需要实现接口,但是需要指定接口类型
 */
public class ProxyFactory implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
