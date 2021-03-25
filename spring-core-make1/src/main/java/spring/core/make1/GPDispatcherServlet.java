package spring.core.make1;

import spring.core.make1.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {
    /**
     * 声明全局成员变量
     */
    //保存application.properties配置文件中的内容
    private Properties contextConfig = new Properties();

    //保存扫描的所有的类名
    private List<String> classNames = new ArrayList<String>();

    /**
     * 传说中的IOC 容器
     * 为了简化程序,暂时不考虑ConcurrentHashMap
     * 关注点还是放在 设计思路和原理
     */
    private Map<String, Object> ioc = new HashMap<String, Object>();

    //保存url 和Method的对应关系
    private Map<String, Object> HandlerMapping = new HashMap<String, Object>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception" + Arrays.toString(e.getStackTrace()));
        }

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!this.HandlerMapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!!");
            return;
        }
        Method method = (Method) this.HandlerMapping.get(url);
        //第一个参数:方法所在的实例
        //第二个参数:调用时所需要的实参
        Map<String, String[]> params = req.getParameterMap();
        //获取方法的形参列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        //保存请求的url参数列表
        Map<String, String[]> parameterMap = req.getParameterMap();
        //保存复制参数的位置
        Object[] paramsValues = new Object[parameterTypes.length];
        //根据参数位置动态赋值
        for (int i = 0; i < parameterTypes.length; i++) {
            Class parameterType = parameterTypes[i];
            if (parameterType == HttpServletRequest.class) {
                paramsValues[i] = req;
                continue;
            }
            if (parameterType == HttpServletResponse.class) {
                paramsValues[i] = resp;
                continue;
            }
            if (parameterType == String.class) {
                //提取方法中加了注解的参数
                Annotation[][] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length; j++) {
                    for (Annotation annotation : pa[j]) {
                        if (annotation instanceof GPRequestParam) {
                            String paramName = ((GPRequestParam) annotation).value();
                            if (!"".equals(paramName.trim())) {
                                String value = Arrays.toString(parameterMap.get(paramName))
                                        .replaceAll("\\[|\\]", "")
                                        .replaceAll("\\s", "");
                                paramsValues[i] = value;
                            }
                        }
                    }
                }
            }
        }
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName), paramsValues);

    }


    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.初始化扫描到的类,并且将它们放入 IOC 容器中
        doInstance();

        //4.完成依赖注入
        doAutowired();

        //5.初始化HandlerMapping
        initHandlerMapping();

        System.out.println("GP MVC Framework is init v2");

    }


    //加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        /**
         * 通过类路径招待Spring主配置文件所在的路径
         * 并且将其读取出来放到Properties 对象中
         * 相当于scanPackage = spring.core.make1
         */
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        //scanPackage = spring.core.make1
        //转换为文件路径,实际上就是把.替换成/
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) continue;
                String clazzName = (scanPackage + "." + file.getName().replace(".class", ""));
                classNames.add(clazzName);
            }
        }
    }

    private void doInstance() {
        if (classNames.isEmpty()) return;
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                /**
                 * 什么样的类需要初始化?
                 * 加了注解的类需要初始化,怎么判断?
                 * 只用@Controller @Service 举例 @Component 就不一一举例了
                 */
                if (clazz.isAnnotationPresent(GPController.class)) {
                    Object instance = clazz.newInstance();
                    //Spring 默认类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(GPService.class)) {
                    //1.自定义的beanName
                    GPService service = clazz.getAnnotation(GPService.class);
                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    //根据类型主动赋值,这是投机取巧的方式
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (ioc.containsKey(i.getName())) {
                            throw new Exception("the" + i.getName() + "is exists!!");
                        }
                        //把接口的类型直接当作key
                        ioc.put(i.getName(), instance);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void doAutowired() {
        if (ioc.isEmpty()) return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //获取所有字段,包括private ,protected,default
            //正常来说,普通的OOP编程只能获得public类型的字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();  //1.获取ioc容器中的bean对象
            for (Field field : fields) {//2.遍历bean对象中的容器字段
                if (!field.isAnnotationPresent(GPAutowired.class)) continue;    //3.字段是否上标记@GPAutowired注解
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);//4.获取注解信息

                //如果用户没有自定义 beanName,默认就根据类型注入
                String beanName = autowired.value();//5.判断注解信息中 是否存在有别名
                if ("".equals(beanName.trim())) {
//                    beanName = toLowerFirstCase(field.getType().getName());
                    beanName = field.getType().getName();
                }

                //如果是public 以外的类型,只要加了@GPAutowired 注解都要强制赋值
                //反射中的暴力访问
                field.setAccessible(true);
                try {
                    //用反射机制动态给字段赋值
                    field.set(entry.getValue(), ioc.get(beanName));
                    System.out.println(field);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    //初始化url和Method的一对一关系
    private void initHandlerMapping() {
        if (ioc.isEmpty()) return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(GPController.class)) continue;
            //保存写在类上的@GPRequestMapping("/name")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            //获取所有的public类型的方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(GPRequestMapping.class)) continue;
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                HandlerMapping.put(url, method);
                System.out.println("Mapped:" + url + "-" + method);
            }
        }
    }


    //将类的首字母改为小写
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //大小写字母的ASCII码相差32
        //大写字母的ASCII码要小于ASCII码
        //在JAVA中,对char做算数运算实际上就是对ASCII码作算数运算
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
