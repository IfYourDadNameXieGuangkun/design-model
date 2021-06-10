package com.design.proxy.动态代理.数据源动态路由业务.service.impl;

import com.design.proxy.动态代理.数据源动态路由业务.dao.OrderDao;
import com.design.proxy.动态代理.数据源动态路由业务.pojo.Order;
import com.design.proxy.动态代理.数据源动态路由业务.service.IOrderService;

public class OrderService implements IOrderService {
    private OrderDao orderDao;

    public OrderService() {
        orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService 调用 OrderDao 创建订单");
        return orderDao.insert(order);
    }
}
