package com.design.proxy.动态代理.数据源动态路由业务.dao;

import com.design.proxy.动态代理.数据源动态路由业务.pojo.Order;

public class OrderDao {
    public int insert(Order order) {
        System.out.println("OrderDao 创建Order 成功!");
        return 1;
    }
}
