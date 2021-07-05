package aye.spring.study._3注解自动织入;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(value = "aye.spring.study._3注解自动织入")
@Configuration
@EnableAspectJAutoProxy
public class AnnotationAspectConfig {
}
