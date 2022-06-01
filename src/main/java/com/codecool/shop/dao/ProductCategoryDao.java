package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;
import java.util.Set;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    ProductCategory find(String name);
    void remove(int id);

    List<ProductCategory> getAll();

    List<Integer> getAllId();
}
