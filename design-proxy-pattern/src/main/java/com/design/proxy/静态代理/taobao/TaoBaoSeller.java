package com.design.proxy.静态代理.taobao;

import com.design.proxy.静态代理.factory.KingStonFactory;
import com.design.proxy.静态代理.service.UsbSell;


//第三 定义代理类对象
// 这是淘宝商家 是一个代理对象
public class TaoBaoSeller implements UsbSell {


    @Override
    public float sell() {
        KingStonFactory kingStonFactory = new KingStonFactory();
        float sell = kingStonFactory.sell();

        //这就是增强
        return sell+100;
    }
}
