package com.adapter.登陆方式适配实战.适配三方登录;

/**
 * NO2 微信登陆
 */
public class LoginForWechatAdapter implements LoginAdapter {
    @Override
    public Boolean support(Object adapter) {
        return adapter instanceof LoginForWechatAdapter;
    }

    @Override
    public String login(String id, Object adapter) {
        return null;
    }
}
