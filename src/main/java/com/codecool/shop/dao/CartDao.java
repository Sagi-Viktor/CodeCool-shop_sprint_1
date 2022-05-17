package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.Map;

public interface CartDao {
    void add(Product product, Integer quantity);
    Map<Product, Integer> getAll();
}
