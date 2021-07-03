package aye.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Aspect//切面
@Slf4j
public class ControllerAspect {

    //配置切入点,方法无实体,主要是为了方便同类中其他方法使用此配置切入点
    @Pointcut("execution(* aye.spring.web.WebController.*(..))")//切入点
    public void AnnotationBeanAspect() {
    }

    /**
     * 配置前置通知,使用aspect()方法上注册切入点
     * 同时接受JoinPoint(连接点)对象,可以没有该参数
     */
    @Before("AnnotationBeanAspect()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before 通知:" + joinPoint);
    }

    @After("AnnotationBeanAspect()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after 通知:" + joinPoint);
    }

    @Around("AnnotationBeanAspect()")
    public void around(JoinPoint joinPoint) {
        log.info("around 通知:" + joinPoint);
    }
}
