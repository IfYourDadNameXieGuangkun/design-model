//import aye.spring.AnnotationBean;
//import aye.spring.aop.AnnotationAspect;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.apache.bcel.util.ClassPath;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@ContextConfiguration("classpath*:user.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
//public class Tets implements ApplicationContextAware {
//    private ApplicationContext context;
//    @Autowired
//    private AnnotationBean annotationBean;
//
//    @Test
//    public void test() {
//        AnnotationAspect bean = context.getBean(AnnotationAspect.class);
//        System.out.println(bean);
//        annotationBean.getName();
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.context = applicationContext;
//    }
//}
