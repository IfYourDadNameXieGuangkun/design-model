package aye.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class MyListener {

    @Async
    @EventListener(MyEvent.class)
    public void listener1(MyEvent myEvent) throws InterruptedException {
        Thread.sleep(10000);
        System.out.println(myEvent.getSource()+"===");
    }
}
