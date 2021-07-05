package aye.spring.study._2xml配置自动织入;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlAspectProxyTest {
    //自动织入
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:aspect.xml");
        XmlNativeWaiter waiter = (XmlNativeWaiter) context.getBean("xmlWaiter");
        waiter.greetTo("aye");
    }

}
