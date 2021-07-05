package aye.spring.study._1使用AspectJproxyFactory手动织入;

public class NativeWaiter implements Waiter {
    @Override
    public void greetTo(String clientName) {
        System.out.println("NativeWaiter:greetTo " + clientName + "...");
    }

    @Override
    public void serverTo(String clientName) {
        System.out.println("NativeWaiter:serverTo " + clientName + "...");
    }
}
