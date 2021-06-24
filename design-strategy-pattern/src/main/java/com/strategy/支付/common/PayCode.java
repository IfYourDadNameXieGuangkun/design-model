package com.strategy.支付.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现IPay的@Service需要添加这个注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface PayCode {
    /**
     * 支付渠道
     *
     * @return
     */
    public PayChannel payChannel();

    /**
     * 支付名称
     */
    public String payName() default "";

}
