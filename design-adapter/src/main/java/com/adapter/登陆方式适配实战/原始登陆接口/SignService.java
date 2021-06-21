package com.adapter.登陆方式适配实战.原始登陆接口;

/**
 * NO0原始用户密码登录
 */
public class SignService {
    public Boolean register(String username, String password) {
        System.out.println("注册成功");
        return true;
    }

    public Boolean login(String username, String password) {
        System.out.println("登录成功");
        return true;
    }
}
