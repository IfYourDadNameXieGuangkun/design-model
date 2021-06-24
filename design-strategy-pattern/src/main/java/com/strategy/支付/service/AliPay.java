package com.strategy.支付.service;


import com.strategy.支付.common.PayChannel;
import com.strategy.支付.common.PayCode;

@PayCode(payChannel = PayChannel.ALI, payName = "支付宝支付")
public class AliPay implements IPay {
    @Override
    public String pay(PayChannel payChannel, String orderCode) {

        System.out.println(payChannel.getChannel() + "===>" + orderCode);
        return payChannel.getChannel() + "===>" + orderCode;
    }
}
