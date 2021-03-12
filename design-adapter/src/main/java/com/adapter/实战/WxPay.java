package com.adapter.实战;

import org.springframework.stereotype.Service;

@PayCode(payChannel = Channel.WX ,payName = "微信支付")
@Service
public class WxPay extends Ext implements IPay  {
    @Override
    public void pay() {
        System.out.println("这是微信支付");
    }
}
