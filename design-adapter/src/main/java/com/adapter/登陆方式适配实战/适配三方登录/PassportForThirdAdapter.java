package com.adapter.登陆方式适配实战.适配三方登录;

import com.adapter.登陆方式适配实战.原始登陆接口.SignService;

public class PassportForThirdAdapter extends SignService implements IPassportForThird {
    @Override
    public String loginForQQ(String id) {
        return processLogin(id,LoginForQQAdapter.class);
    }



    @Override
    public String loginForSina(String id) {
        return null;
    }

    @Override
    public String loginForTel(String id, String code) {
        return null;
    }

    @Override
    public String loginForWechat(String id) {
        return null;
    }

    @Override
    public String loginForRegister(String username, String password) {
        return null;
    }

    private String processLogin(String id, Class<? extends LoginAdapter> clazz) {
        try {
            LoginAdapter adapter = clazz.newInstance();
            if (adapter.support(adapter)){
                return adapter.login(id,adapter);
            }
        }catch (Exception e){

        }
        return null;
    }
}
