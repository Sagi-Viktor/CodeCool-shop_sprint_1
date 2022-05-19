package com.codecool.shop.dao;

import com.codecool.shop.model.OrderModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface OrderDao {
    void add(OrderModel orderModel);
    void update(OrderModel orderModel);
    OrderModel get(int id);
    List<OrderModel> getAll();
}
