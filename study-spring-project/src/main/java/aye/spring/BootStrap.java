package aye.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class BootStrap {
    public static void main(String[] args) {
        /**
         * 1.基于xml配置Bean
         */
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("user.xml");
//        User user = (User) context.getBean("user");
//        System.out.println(user.getId());

        /**
         * 2.基于注解配置Bean
         */
        AnnotationConfigApplicationContext annContext = new AnnotationConfigApplicationContext("aye.spring");
        AnnotationBean bean = (AnnotationBean) annContext.getBean("annotationBean");
        System.out.println(bean.getName());

    }

}
