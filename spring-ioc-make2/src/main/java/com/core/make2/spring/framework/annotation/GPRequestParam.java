package com.core.make2.spring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})//标注在参数上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestParam {
    String value() default "";
}
