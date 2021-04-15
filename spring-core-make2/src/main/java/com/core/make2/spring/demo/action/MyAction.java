package com.core.make2.spring.demo.action;

import com.core.make2.spring.demo.service.IQueryService;
import com.core.make2.spring.framework.annotation.GPAutowired;
import com.core.make2.spring.framework.annotation.GPController;
import com.core.make2.spring.framework.annotation.GPRequestMapping;
import com.core.make2.spring.framework.annotation.GPRequestParam;
import com.core.make2.spring.framework.webmvc.GPModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@GPController
@GPRequestMapping("/web")
public class MyAction {
    @GPAutowired
    private IQueryService queryService;

    @GPRequestMapping("/query.json")
    public GPModelAndView query(HttpServletRequest req, HttpServletResponse resp, @GPRequestParam("name") String name) {
        String result = queryService.query(name);
        return out(resp, result);
    }

    private GPModelAndView out(HttpServletResponse resp, String result) {
        try {
            resp.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
