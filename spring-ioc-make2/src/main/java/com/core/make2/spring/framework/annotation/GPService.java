package com.core.make2.spring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})//标注在类/接口上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPService {
    String value() default "";
}
