package com.adapter.登陆方式适配实战.适配三方登录;

/**
 * NO2 QQ登陆
 */
public class LoginForQQAdapter implements LoginAdapter {
    @Override
    public Boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    @Override
    public String login(String id, Object adapter) {
        System.out.println("QQ登录");
        return "QQ登录";
    }
}
