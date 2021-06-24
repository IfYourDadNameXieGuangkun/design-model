package com.strategy.支付.service;

import com.strategy.支付.common.PayChannel;

/**
 * 支付通用实现接口,必须要实现这个接口
 */
public interface IPay {
    String pay(PayChannel payChannel, String orderCode);
}
