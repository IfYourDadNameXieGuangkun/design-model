package com.design.proxy.动态代理.数据源动态路由业务;

import com.design.proxy.动态代理.数据源动态路由业务.pojo.Order;
import com.design.proxy.动态代理.数据源动态路由业务.service.impl.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Client {
    public static void main(String[] args) throws ParseException {
        Order order = new Order();
        order.setCreateTime(new SimpleDateFormat("yyyy/MM/dd").parse("2021/06/08").getTime());
        OrderService orderService = (OrderService) new OrderServiceDynamicProxy().getInstance(new OrderService());
        orderService.createOrder(order);
//        new OrderServiceDynamicProxy().getInstance(new DynamicDataSourceEntry());
    }
}
