package com.boot.基于注解驱动实现Enable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String helloWorld() {
        return "hello world";
    }
}
