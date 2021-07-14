package com.boot.基于注解驱动实现Enable;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@EnableHelloWorld
@Configuration
public class BootStrap {
    public static void main(String[] args) {
//        ApplicationContext context = new SpringApplicationBuilder(BootStrap.class).bannerMode(Banner.Mode.OFF).run(args);
//        System.out.println(context.getBean("helloWorld", String.class));
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BootStrap.class);
        context.refresh();
        System.out.println(context.getBean("helloWorld", String.class));
        context.close();
    }
}
