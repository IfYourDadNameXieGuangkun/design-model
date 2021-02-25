package com.design.proxy.example2;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MapperFactoryBean<T> implements FactoryBean<T> {
    private Class<T> mapperInterface;

    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = (proxy,method,args)->{
            Select select = method.getAnnotation(Select.class);
            System.out.println("这就是sql吗:"+select.value().replace("#{uid}",args[0].toString()));
            /**
             * 此时可以去查询数据库的动作
             */
            return args[0]+"这就是mabatis的动态代理吗";
        };
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{mapperInterface},invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
