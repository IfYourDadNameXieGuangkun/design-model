package com.study.redis.controller;

import com.study.redis.conf.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/")
public class BaseController {

    @Autowired
    private RedisUtils redisDao;

    /**
     * 根据key获取value
     */
    @RequestMapping("/get")
    public void get() {
        Object o = redisDao.get("PROMOTION:zy:MZHE:3003");
        System.out.println(o);
    }

    /**
     * 获取所有的前缀匹配的keys值
     */
    @RequestMapping("/keys")
    public void keys() {
        Set<String> keys = redisDao.keys("PROMOTION:zy:MZHE*");
        if (!CollectionUtils.isEmpty(keys)){
            System.out.println(keys);
        }
    }
}
