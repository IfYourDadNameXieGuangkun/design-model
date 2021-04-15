package spring.core.make1.controller;

import spring.core.make1.annotation.GPAutowired;
import spring.core.make1.annotation.GPController;
import spring.core.make1.annotation.GPRequestMapping;
import spring.core.make1.annotation.GPRequestParam;
import spring.core.make1.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@GPController
@GPRequestMapping("/demo1")
public class DemoAction1 {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public void queryName(HttpServletRequest req, HttpServletResponse resp, @GPRequestParam("name") String name) {
        try {
            resp.getWriter().write(demoService.getName(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
