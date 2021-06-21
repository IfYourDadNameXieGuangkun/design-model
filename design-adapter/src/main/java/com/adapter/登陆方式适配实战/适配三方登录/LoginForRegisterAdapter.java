package com.adapter.登陆方式适配实战.适配三方登录;

/**
 * NO2 注册后自动登录
 */
public class LoginForRegisterAdapter implements LoginAdapter {
    @Override
    public Boolean support(Object adapter) {
        return adapter instanceof LoginForRegisterAdapter;
    }

    @Override
    public String login(String id, Object adapter) {
        return null;
    }
}
