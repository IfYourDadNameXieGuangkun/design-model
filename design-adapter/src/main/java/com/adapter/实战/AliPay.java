package com.adapter.实战;

import org.springframework.stereotype.Service;

@PayCode(payChannel = Channel.ALI,payName = "支付宝支付")
@Service
public class AliPay implements IPay {
    @Override
    public void pay() {
        System.out.println("这是支付宝支付");
    }
}
