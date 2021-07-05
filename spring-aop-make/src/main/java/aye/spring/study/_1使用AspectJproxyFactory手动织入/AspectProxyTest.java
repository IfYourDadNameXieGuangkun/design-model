package aye.spring.study._1使用AspectJproxyFactory手动织入;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class AspectProxyTest {
    /**
     * 手动织入target代理对象
     * @param args
     */
    public static void main(String[] args) {
        Waiter target = new NativeWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(target);
        factory.addAspect(PreGreetingAspect.class);
        Waiter proxy = factory.getProxy();
        proxy.greetTo("john");
        proxy.serverTo("aye");

    }

}
