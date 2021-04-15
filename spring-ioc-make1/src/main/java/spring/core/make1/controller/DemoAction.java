package spring.core.make1.controller;

import spring.core.make1.annotation.GPAutowired;
import spring.core.make1.annotation.GPController;
import spring.core.make1.annotation.GPRequestMapping;
import spring.core.make1.annotation.GPRequestParam;
import spring.core.make1.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 版本V3可传入多个参数,并支持多种
 */
@GPController
@GPRequestMapping("/demo")
public class DemoAction {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public void queryName(HttpServletRequest req, HttpServletResponse resp, @GPRequestParam("name") String name, @GPRequestParam("age") Integer age) {
        try {
            resp.getWriter().write(demoService.getName(name) + ",age:" + age);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
