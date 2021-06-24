package com.strategy.支付.service;


import com.strategy.支付.common.PayChannel;
import com.strategy.支付.common.PayCode;
import com.strategy.支付.common.PayStrategy;

@PayCode(payChannel = PayChannel.WX, payName = "微信支付")
public class WXPay implements IPay {
    @Override
    public String pay(PayChannel payChannel, String orderCode) {
        System.out.println(payChannel.getChannel() + "===>" + orderCode);
        return payChannel.getChannel() + "===>" + orderCode;
    }
}
