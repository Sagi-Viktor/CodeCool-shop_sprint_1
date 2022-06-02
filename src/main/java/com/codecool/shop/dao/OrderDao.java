package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {
    void add(Order order);
    void update(Order order);
    Order get(String id);
    List<Order> getAll();
}
