package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.OrderModel;

import java.util.UUID;

public class OrderService {
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void add(OrderModel orderModel, UUID id) {
        orderDao.add(orderModel, id);
    }
}
