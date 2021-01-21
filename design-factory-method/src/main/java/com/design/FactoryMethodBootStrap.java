package com.design;

import com.design.model.Aye;
import com.design.model.card.IQiYiCard;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

@SpringBootApplication
public class FactoryMethodBootStrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FactoryMethodBootStrap.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        while (beanNamesIterator.hasNext()){
            String next = beanNamesIterator.next();
            System.out.println(next);
        }


    }
}
