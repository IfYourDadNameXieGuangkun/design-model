package aye.spring;

import aye.spring.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBean implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

//    public AnnotationBean(ApplicationEventPublisher applicationEventPublisher) {
//        this.applicationEventPublisher = applicationEventPublisher;
//    }

    public String getName() {
        applicationEventPublisher.publishEvent(new MyEvent("getName"));
        return "getName";
    }

    public String showName() {
        applicationEventPublisher.publishEvent(new MyEvent("showName"));
        return "showName";
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
