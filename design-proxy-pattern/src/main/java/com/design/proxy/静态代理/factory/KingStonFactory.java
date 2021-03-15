package com.design.proxy.静态代理.factory;

import com.design.proxy.静态代理.service.UsbSell;

//第二 定义目标类,并实现功能
// 目标类 金士顿商家
public class KingStonFactory implements UsbSell {
    @Override
    public float sell() {
        return 85f;
    }
}
