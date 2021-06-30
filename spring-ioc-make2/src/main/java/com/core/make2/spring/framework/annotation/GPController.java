package com.core.make2.spring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})//标注在类或接口上
@Retention(RetentionPolicy.RUNTIME)//运行时
@Documented
public @interface GPController {
    String value() default "";
}
