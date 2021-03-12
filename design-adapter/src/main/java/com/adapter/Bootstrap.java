package com.adapter;


import com.adapter.实战.IPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(Bootstrap.class).bannerMode(Banner.Mode.OFF).run(args);


    }
}
