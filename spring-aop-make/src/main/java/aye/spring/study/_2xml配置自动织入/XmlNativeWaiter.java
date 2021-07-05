package aye.spring.study._2xml配置自动织入;


public class XmlNativeWaiter {
    public void greetTo(String clientName) {
        System.out.println("NativeWaiter:greetTo " + clientName + "...");
    }

    public void serverTo(String clientName) {
        System.out.println("NativeWaiter:serverTo " + clientName + "...");
    }
}
