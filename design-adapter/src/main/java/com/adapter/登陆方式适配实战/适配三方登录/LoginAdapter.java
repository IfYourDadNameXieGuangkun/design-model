package com.adapter.登陆方式适配实战.适配三方登录;

/**
 * NO1 适配接口
 */
public interface LoginAdapter {
    Boolean support(Object adapter);

    String login(String id, Object adapter);
}
