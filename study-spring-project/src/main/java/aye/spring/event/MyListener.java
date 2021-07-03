package aye.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@Slf4j
public class MyListener {

    @Async
    @EventListener(MyEvent.class)
    public void listener1(MyEvent myEvent) throws InterruptedException {
//        Thread.sleep(5000);
        log.info("listener1-->" + myEvent.getSource());
        System.out.println(myEvent.getSource() + "===");
    }
}
