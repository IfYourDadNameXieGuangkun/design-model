package aye.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("aop")
public class WebController {

    @RequestMapping("/test")
    public String testAop(){

        return "aop test";
    }
}
