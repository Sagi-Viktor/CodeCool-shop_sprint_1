package com.codecool.shop.dao;

import com.codecool.shop.model.OrderModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.UUID;

public interface OrderDao {
    void add(OrderModel orderModel, UUID uuid);
    void update(OrderModel orderModel);
    OrderModel get(String id);
    List<OrderModel> getAll();
}
