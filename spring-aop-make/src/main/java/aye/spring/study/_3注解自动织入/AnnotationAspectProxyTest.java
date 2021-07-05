package aye.spring.study._3注解自动织入;

import aye.spring.study._2xml配置自动织入.XmlNativeWaiter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationAspectProxyTest {
    //自动织入
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationAspectConfig.class);
        AnnotationNativeWaiter waiter = (AnnotationNativeWaiter) context.getBean("annotationNativeWaiter");
        waiter.greetTo("aye");
    }

}
