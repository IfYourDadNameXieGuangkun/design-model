package com.adapter.登陆方式适配实战.适配三方登录;

/**
 * NO3三方登录兼容接口
 */
public interface IPassportForThird {
    String loginForQQ(String id);
    String loginForSina(String id);
    String loginForTel(String id,String code);
    String loginForWechat(String id);
    String loginForRegister(String username,String password);
}
