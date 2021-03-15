package aye.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootStrap {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("user.xml");
        User user = (User) context.getBean("user");
        System.out.println(user.getId());
    }
}
