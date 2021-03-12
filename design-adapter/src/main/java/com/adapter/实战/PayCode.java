package com.adapter.实战;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PayCode {
    /**
     * 支付渠道
     * @return
     */
    Channel payChannel();

    /**
     * 支付名称
     */
    String payName();
}
