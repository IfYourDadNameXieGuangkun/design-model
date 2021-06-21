package com.adapter.登陆方式适配实战.适配三方登录;

/**
 * NO2 新浪登陆
 */
public class LoginForSinaAdapter implements LoginAdapter {
    @Override
    public Boolean support(Object adapter) {
        return adapter instanceof LoginForSinaAdapter;
    }

    @Override
    public String login(String id, Object adapter) {
        return null;
    }
}
