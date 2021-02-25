package spring.core.make1.controller;

import spring.core.make1.annotation.GPAutowired;
import spring.core.make1.annotation.GPController;
import spring.core.make1.annotation.GPRequestMapping;
import spring.core.make1.annotation.GPRequestParam;
import spring.core.make1.service.IDemoService;

@GPController
@GPRequestMapping("/name")
public class DemoAction {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public String queryName(@GPRequestParam("name") String name) {
        return demoService.getName(name);
    }
}
