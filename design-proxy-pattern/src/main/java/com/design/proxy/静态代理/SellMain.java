package com.design.proxy.静态代理;

import com.design.proxy.静态代理.taobao.TaoBaoSeller;

public class SellMain {
    public static void main(String[] args) {
        TaoBaoSeller taoBaoSeller = new TaoBaoSeller();
        //目标 厂家卖85块 代理 商家 在85的基础上+100 ,这就是代理,功能增强
        System.out.println(taoBaoSeller.sell());
    }
}
