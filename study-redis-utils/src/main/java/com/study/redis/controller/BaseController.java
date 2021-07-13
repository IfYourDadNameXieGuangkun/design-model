package com.study.redis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.study.redis.conf.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/")
public class BaseController {

    @Autowired
    private RedisUtils redisDao;

    /**
     * 根据key获取value
     */
    @RequestMapping("/get/{key}")
    public Object get(@PathVariable String key) {
        Object o = redisDao.get(key);
        return o;
    }

    /**
     * 获取所有的前缀匹配的keys值
     */
    @RequestMapping("/keys")
    public void keys() {
//        Set<String> keys = redisDao.keys("PROMOTION:zy:MZHE*");
        Set<String> keys = redisDao.keys("PROMOTION:OR*");
        if (!CollectionUtils.isEmpty(keys)){
            System.out.println(keys);
        }
    }

    /**
     * 获取所有的前缀匹配的keys值
     */
    @RequestMapping("/hget/{key}/{filed}")
    public void hget(@PathVariable String key,@PathVariable String filed) {
        Object obj = redisDao.hget(key, filed);
        System.out.println(obj);
    }

    /**
     * 获取所有的前缀匹配的keys值
     */
    @RequestMapping("/set")
    public void set() {
        Set<String> objects = new HashSet<>();
        objects.add("1249601977045916729");
        objects.add("12341234");
//        redisDao.set("PROMOTION:zy:MZHE:3011", objects.toString());
        String s = (String) redisDao.get("PROMOTION:zy:MZHE:3011");
        Set<String> promIds = JSON.parseObject(s, new TypeReference<Set<String>>() {
        });
        System.out.println(promIds.toString());
    }

    /**
     * 获取所有的前缀匹配的keys值
     */
    @RequestMapping("/del")
    public void del() {
        Set<String> keys = redisDao.keys("PROMOTION:zy:MZHE*");
        int cnt = 0;
        for (String key : keys) {
            redisDao.del(key);
            System.out.println(++cnt+"==>"+key);
        }
    }

    public static void main(String[] args) {
        Set<String> validPromotionIds = new HashSet<>();
        Set<String> promIds = new HashSet<>();
        promIds.add("123");
        promIds.add("444");
        promIds.add("555");
        validPromotionIds.add("123");
        validPromotionIds.add("234");
        Set<String> result = new HashSet<>(validPromotionIds);
        result.retainAll(promIds);
        System.out.println(result.toString());
    }
}
