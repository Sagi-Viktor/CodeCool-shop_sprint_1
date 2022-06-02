package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderDao {
    void add(Order order, UUID uuid);
    void update(Order order);
    Order get(String id);
    List<Order> getAll();
}
