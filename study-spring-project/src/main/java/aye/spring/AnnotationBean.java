package aye.spring;

import aye.spring.event.MyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBean {
    private ApplicationEventPublisher applicationEventPublisher;

    public AnnotationBean(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public String getName() {
        applicationEventPublisher.publishEvent(new MyEvent("asdasda"));
        return "AnnotationName";
    }
}
