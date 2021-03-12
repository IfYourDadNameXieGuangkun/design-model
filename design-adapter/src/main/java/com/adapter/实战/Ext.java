package com.adapter.实战;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

public abstract class Ext<T> implements ApplicationContextAware {
    private ApplicationContext context;

    public IPay getPayAdapter(T t) {
        Map<String, IPay> payMap = new HashMap<>();
        Map<String, IPay> map = context.getBeansOfType(IPay.class);//1.拿到所有实现IPay接口的实现类
        map.forEach((key, value) -> {
            String channel = value.getClass().getAnnotation(PayCode.class).payChannel().getChannel();
            payMap.put(channel, value);
        });
        return payMap.get(Channel.WX.getChannel());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
