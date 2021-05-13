package com.boot;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

@RestController
@RequestMapping("/")
public class TestController {

    @PostMapping("/txd")
    public void helloController(InputStream in) throws IOException {
        String json = IOUtils.toString(in, "UTF-8");
        json = URLDecoder.decode(json, "UTF-8");
        System.out.println(json);
    }

}
