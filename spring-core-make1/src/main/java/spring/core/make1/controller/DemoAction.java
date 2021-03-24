package spring.core.make1.controller;

import spring.core.make1.annotation.GPAutowired;
import spring.core.make1.annotation.GPController;
import spring.core.make1.annotation.GPRequestMapping;
import spring.core.make1.annotation.GPRequestParam;
import spring.core.make1.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@GPController
@GPRequestMapping("/name")
public class DemoAction {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public String queryName(HttpServletRequest req, HttpServletResponse resp) {
        return demoService.getName("hello");
    }
}
