package com.adapter.controller;

import com.adapter.实战.IPay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//研究DispatcherServlet的demo Controller
@RestController
@RequestMapping("/adapter")
public class ControllerDemo {

    @Resource
    private IPay pay;
    @RequestMapping("get")
    public String getName(@RequestParam("name") String name) {
        pay.pay();
        return "hello" + name;
    }
}
