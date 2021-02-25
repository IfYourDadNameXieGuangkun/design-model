package com.design.proxy.example1;

/**
 * 目标对象,被代理的对象
 */
public class Login implements ILogin {
    @Override
    public void login(String name) {
        System.out.println("login:"+name);
    }
}
