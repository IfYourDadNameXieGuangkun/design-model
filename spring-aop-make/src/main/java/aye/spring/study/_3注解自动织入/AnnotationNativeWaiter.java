package aye.spring.study._3注解自动织入;

import org.springframework.stereotype.Component;

@Component
public class AnnotationNativeWaiter {
    public String greetTo(String clientName) {
        System.out.println("NativeWaiter:greetTo " + clientName + "...");
        return "this is return";
    }

    public void serverTo(String clientName) {
        System.out.println("NativeWaiter:serverTo " + clientName + "...");
    }
}
