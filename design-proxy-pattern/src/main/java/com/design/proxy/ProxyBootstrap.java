package com.design.proxy;


import com.design.proxy.example2.MapperFactoryBean;
import com.design.proxy.example2.UserDao;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProxyBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ProxyBootstrap.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        MapperFactoryBean bean = beanFactory.getBean(MapperFactoryBean.class);
        System.out.println(bean);
        //bean.selectOne("aye");

    }
}
