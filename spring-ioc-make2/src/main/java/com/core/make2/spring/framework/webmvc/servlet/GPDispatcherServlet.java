package com.core.make2.spring.framework.webmvc.servlet;

import com.core.make2.spring.framework.annotation.GPController;
import com.core.make2.spring.framework.annotation.GPRequestMapping;
import com.core.make2.spring.framework.context.GPApplicationContext;
import com.core.make2.spring.framework.webmvc.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Servlet 只是作为一个mvc的启动入口
@Slf4j
public class GPDispatcherServlet extends HttpServlet {

    private final String LOCATION = "contextConfigLocation";

    /**
     * 读者可以思考一下这样设计的经典之处
     * GPHandlerMapping 最核心的设计,也是最经典的,它直接干掉 struts WebWork 等MVC框架
     */
    private List<GPHandlerMapping> handlerMappings = new ArrayList<GPHandlerMapping>();

    private Map<GPHandlerMapping, GPHandlerAdapter> handlerAdapters = new HashMap<GPHandlerMapping, GPHandlerAdapter>();

    private List<GPViewResolver> viewResolvers = new ArrayList<GPViewResolver>();

    private GPApplicationContext context;


    @Override
    public void init(ServletConfig config) throws ServletException {
        //相当于吧Ioc容器初始化了
        context = new GPApplicationContext(config.getInitParameter(LOCATION));
        initStrategies(context);
    }

    protected void initStrategies(GPApplicationContext context) {
        //有九种策略
        //针对每个用户请求,都会经过一些处理策略,最终才能有结果输出
        //没中策略都可以自定义干预,但是最终的结果都一致
        //这里就是传说中的九大组件
        initMultipartResolver(context);//文件上传解析,如果请求类型是multipart,将通过MultipartResolver进行文件上传解析
        initLocaleResolver(context);//本地化解析
        initThemeResolver(context);//主题解析
        //GPHandlerMapping用来保存Controller中配置的RequestMapping的Method的对应关系
        initHandlerMappings(context);//通过HandlerMapping将请求映射到处理器
        //HandlerAdapter用来动态匹配Method参数,包括类型转换,动态赋值
        initHandlerAdapter(context);//通过HandlerAdapter进行多类型的参数动态匹配

        initHandlerExceptionResolver(context);//如果执行过程中遇到异常,将交给HandlerExceptionResolver来解析

        initRequestToViewNameTranslator(context);//直接将请求解析到视图名

        //通过ViewResolver实现动态模板的解析
        //自己解析一套模板语言
        initViewResolver(context);//通过viewResolver将逻辑视图解析到具体视图实现

        initFlashMapManager(context);//Flash映射关系

    }

    private void initFlashMapManager(GPApplicationContext context) {
    }

    private void initViewResolver(GPApplicationContext context) {
        //在页面上输入http://localhost/first.html
        //解决页面名字和模板文件关联的问题
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPth = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPth);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new GPViewResolver(templateRoot));
        }

    }

    private void initRequestToViewNameTranslator(GPApplicationContext context) {
    }

    private void initHandlerExceptionResolver(GPApplicationContext context) {
    }

    private void initHandlerAdapter(GPApplicationContext context) {
        //再初始化阶段,我们能做的就是,讲这些参数的名字或者类型按一定的顺序保存下来
        //因为后面用繁色调用的时候,传的形参是一个数组
        //可以通过记录这些参数的位置index,逐个从数组中取值,这样就合参数的顺序无关了
        for (GPHandlerMapping handlerMapping : handlerMappings) {
            handlerAdapters.put(handlerMapping, new GPHandlerAdapter());
        }
    }

    //将Controller中配置的RequestMapping和Method进行一一对应
    private void initHandlerMappings(GPApplicationContext context) {

        //首先从容器中获取所有的实例
        String[] beanNames = context.getBeanDefinitionNames();

        try {
            for (String beanName : beanNames) {
                //到了MVC层,对外提供的方法只有一个getBean()方法
                //返回的对象不是BeanWrapper,怎么办?
                Object controller = context.getBean(beanName);
                Class<?> clazz = controller.getClass();
                if (!clazz.isAnnotationPresent(GPController.class)) {
                    continue;
                }
                String baseUrl = "";
                if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                    GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                //扫描所有的public 类型方法,拼接路径
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(GPRequestMapping.class)) {
                        continue;
                    }
                    GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                    String regex = ("/" + baseUrl + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new GPHandlerMapping(pattern, controller, method));
                    log.info("Mapping: " + regex + " ," + method);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initThemeResolver(GPApplicationContext context) {
    }

    private void initLocaleResolver(GPApplicationContext context) {

    }

    private void initMultipartResolver(GPApplicationContext context) {

    }

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
        //根据用户请求的URL来获取一个Handler
        GPHandlerMapping handler = getHandler(req);
        if (null == handler) {
            processDispatchResult(req, resp, new GPModelAndView("404"));
            return;
        }
        GPHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        GPModelAndView mv = handlerAdapter.handle(req, resp, handler);
        processDispatchResult(req, resp, mv);
    }

    private GPHandlerAdapter getHandlerAdapter(GPHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()){return null;}
        GPHandlerAdapter ha = this.handlerAdapters.get(handler);
        if (ha.support(handler)){
            return ha;
        }
        return null;
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, GPModelAndView mv) throws Exception {
        //调用viewResolver 的resolveViewName()方法
        if (null == mv) {
            return;
        }
        if (this.viewResolvers.isEmpty()) {
            return;
        }
        if (this.viewResolvers != null) {
            for (GPViewResolver viewResolver : this.viewResolvers) {
                GPView view = viewResolver.resolveViewName(mv.getViewName(), null);
                if (view != null) {
                    view.render(mv.getModel(), req, resp);
                    return;
                }
            }
        }

    }

    private GPHandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()){return null;}
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url.replace(contextPath,"").replaceAll("/+","/");
        for (GPHandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()){continue;}
            return handlerMapping;
        }
        return null;
    }
}
