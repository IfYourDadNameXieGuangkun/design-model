package aye.spring.study._3注解自动织入;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationPreGreetingAspect {
//    @Before("execution(* greetTo(..))")
//    public void beforeGreeting(){
//        System.out.println("How are you");
//    }

    /**
     *重点注意:若使用环绕@around注解,必须执行proceed方法,不然代理对象方法不执行
     * @param point
     * @throws Throwable
     */
    @Around("execution(* greetTo(..))")
    public void testAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println(point.getArgs().length);
//        Object proceed = point.proceed();
//        System.out.println(proceed);
    }
}
