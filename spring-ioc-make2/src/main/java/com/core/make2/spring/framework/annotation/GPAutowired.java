package com.core.make2.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解配置
 *
 */
@Target({ElementType.FIELD})//标注在字段上
@Retention(RetentionPolicy.RUNTIME)//运行时
@Documented
public @interface GPAutowired {
    String value() default "";
}
