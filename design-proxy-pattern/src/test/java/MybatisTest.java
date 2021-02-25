import com.design.proxy.example2.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisTest {
    @Test
    public void test01(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("mybatis-spring.xml");
        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);
        String aye = userDao.selectOne("aye");
        System.out.println(aye);

    }
}
