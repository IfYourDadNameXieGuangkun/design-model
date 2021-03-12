package com.adapter.实战;

public enum Channel {
    WX("WX", "普通商品"),
    ALI("ALI", "满减专区商品")
    ;
    private String channel;
    private String name;

    private Channel(String channel, String name) {
        this.channel = channel;
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }

}
