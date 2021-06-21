package com.adapter.登陆方式适配实战.适配三方登录;

/**
 * NO2 手机登陆
 */
public class LoginForTelAdapter implements LoginAdapter {
    @Override
    public Boolean support(Object adapter) {
        return adapter instanceof LoginForTelAdapter;
    }

    @Override
    public String login(String id, Object adapter) {
        return null;
    }
}
