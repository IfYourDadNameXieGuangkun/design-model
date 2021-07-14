package com.boot.基于注解驱动实现Enable;

import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({HelloWorldConfiguration.class})
public @interface EnableHelloWorld {
}
