package aye.spring;

import org.springframework.stereotype.Component;

@Component
public class AnnotationBean {

    public String getName() {
        return "AnnotationName";
    }
}
