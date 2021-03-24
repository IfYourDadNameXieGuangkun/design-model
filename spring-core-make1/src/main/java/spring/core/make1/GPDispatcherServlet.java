package spring.core.make1;

import spring.core.make1.annotation.GPAutowired;
import spring.core.make1.annotation.GPController;
import spring.core.make1.annotation.GPRequestMapping;
import spring.core.make1.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private List<String> clazzName = new ArrayList<String>();

    /**
     * 传说中的IOC 容器
     * 为了简化程序,暂时不考虑ConcurrentHashMap
     * 关注点还是放在 设计思路和原理
     */
    private Map<String, Object> ioc = new HashMap<String, Object>();

    //保存url 和Method的对应关系
    private Map<String, Object> mapping = new HashMap<String, Object>();


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
        if (!this.mapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!!");
            return;
        }
        Method method = (Method) this.mapping.get(url);
        Map<String, String[]> params = req.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()), req, resp, params.get("name")[0]);

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


    private void doLoadConfig(String contextConfigLocation) {

    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) continue;
                String clazzName = (scanPackage + "." + file.getName().replace(".class", ""));
                mapping.put(clazzName, null);
            }
        }
    }

    private void doInstance() {
    }

    private void doAutowired() {
    }

    private void initHandlerMapping() {
    }


//    public void init(ServletConfig config) throws ServletException {
//        InputStream is = null;
//        try {
//            Properties configContext = new Properties();
//            is = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
//            configContext.load(is);
//            String scanPackage = configContext.getProperty("scanPackage");
//            doScanner(scanPackage);
//            for (String clazzName : mapping.keySet()) {
//                if (!clazzName.contains(".")) continue;
//                Class<?> clazz = Class.forName(clazzName);
//                if (clazz.isAnnotationPresent(GPController.class)) {
//                    mapping.put(clazzName, clazz.newInstance());
//                    String baseUrl = "";
//                    if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
//                        GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
//                        baseUrl = requestMapping.value();
//                    }
//                    Method[] methods = clazz.getMethods();
//                    for (Method method : methods) {
//                        if (!method.isAnnotationPresent(GPRequestMapping.class)) continue;
//                        GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
//                        String url = (baseUrl + "/" + requestMapping.value().replaceAll("/+", "/"));
//                        mapping.put(url, method);
//                        System.out.println("Mapped" + url + "," + method);
//                    }
//                } else if (clazz.isAnnotationPresent(GPService.class)) {
//                    GPService service = clazz.getAnnotation(GPService.class);
//                    String beanName = service.value();
//                    if ("".equals(beanName)) {
//                        beanName = clazz.getName();
//                    }
//                    Object instance = clazz.newInstance();
//                    mapping.put(beanName, instance);
//                    for (Class<?> i : clazz.getInterfaces()) {
//                        mapping.put(i.getName(), instance);
//                    }
//                } else continue;
//            }
//            for (Object object : mapping.values()) {
//                if (null == object) continue;
//                Class<?> clazz = object.getClass();
//                if (clazz.isAnnotationPresent(GPController.class)) {
//                    Field[] fields = clazz.getDeclaredFields();
//                    for (Field field : fields) {
//                        if (!field.isAnnotationPresent(GPAutowired.class)) continue;
//                        GPAutowired autowired = field.getAnnotation(GPAutowired.class);
//                        String beanName = autowired.value();
//                        if ("".equals(beanName)) {
//                            beanName = field.getType().getName();
//                        }
//                        field.setAccessible(true);
//                        try {
//                            field.set(mapping.get(clazz.getName()), mapping.get(beanName));
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("GP MVC Framework is init");
//    }


}
