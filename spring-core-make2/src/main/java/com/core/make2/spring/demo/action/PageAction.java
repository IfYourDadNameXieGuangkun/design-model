package com.core.make2.spring.demo.action;

import com.core.make2.spring.demo.service.IQueryService;
import com.core.make2.spring.framework.annotation.GPAutowired;
import com.core.make2.spring.framework.annotation.GPController;
import com.core.make2.spring.framework.annotation.GPRequestMapping;
import com.core.make2.spring.framework.annotation.GPRequestParam;
import com.core.make2.spring.framework.webmvc.GPModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@GPController
@GPRequestMapping("//")
public class PageAction {
    @GPAutowired
    private IQueryService queryService;

    @GPRequestMapping("/first.html")
    public GPModelAndView query(HttpServletRequest req, HttpServletResponse resp, @GPRequestParam("teacher") String teacher) {
        String result = queryService.query(teacher);
        Map<String, Object> model = new HashMap<>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new GPModelAndView("first.html", model);
    }

}
