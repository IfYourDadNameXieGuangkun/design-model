package com.strategy.支付.common;

import lombok.AllArgsConstructor;

/**
 * 支付渠道枚举
 */

@AllArgsConstructor
public enum PayChannel {

    ALI("ALI", "支付宝支付"),
    WX("WX", "微信支付");
    private String channel;

    private String name;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PayChannel getPayChannelByChannel(String channel) {
        for (PayChannel value : PayChannel.values()) {
            if (value.getChannel().equals(channel)) {
                return value;
            }
        }
        return null;
    }

    ;
}
