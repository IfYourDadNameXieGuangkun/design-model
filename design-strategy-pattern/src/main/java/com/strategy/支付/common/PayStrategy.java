package com.strategy.支付.common;

import com.strategy.支付.service.IPay;
import exception.aye.ex.enums.ServletErrorEnum;
import exception.aye.ex.exception.BizException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class PayStrategy<T> implements ApplicationContextAware {
    private ApplicationContext context;

//    public IPay getPayAdapter(String channel) {
//        Map<String, IPay> payMap = new HashMap<>();
//        Map<String, IPay> map = context.getBeansOfType(IPay.class);//1.拿到所有实现IPay接口的实现类
//        map.forEach((key, value) -> {
//            String channel_ = value.getClass().getAnnotation(PayCode.class).payChannel().getChannel();
//            payMap.put(channel_, value);
//        });
//        return payMap.get(PayChannel.WX.getChannel());
//    }

    protected IPay getPayAdapter(String channel) {
        Optional<IPay> payService = context.getBeansOfType(IPay.class).values().stream().filter(pay->pay.getClass().getAnnotation(PayCode.class).payChannel().getChannel().equals(channel)).findFirst();
        return payService.orElseThrow(NullPointerException::new);
//        return payService.orElseThrow(new Supplier() {
//            @Override
//            public Object get() {
//                return new BizException(ServletErrorEnum.AsyncRequestTimeoutException);
//            }
//        });
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
