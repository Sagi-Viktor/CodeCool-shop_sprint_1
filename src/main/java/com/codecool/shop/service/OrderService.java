package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;

public class OrderService {
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
