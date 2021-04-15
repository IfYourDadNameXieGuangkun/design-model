package com.core.make2.spring.framework.webmvc.servlet;

import com.core.make2.spring.framework.context.GPApplicationContext;
import com.core.make2.spring.framework.webmvc.GPHandlerAdapter;
import com.core.make2.spring.framework.webmvc.GPHandlerMapping;
import com.core.make2.spring.framework.webmvc.GPViewResolver;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

        } catch (Exception e) {
            resp.getWriter().write("500 Exception" + Arrays.toString(e.getStackTrace()));
        }

    }
}
