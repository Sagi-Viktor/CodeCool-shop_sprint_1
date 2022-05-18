package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CartDaoMem implements CartDao {

    private final Map<Product, Integer> data = new HashMap<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {

    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product, Integer quantity) {
        data.put(product, quantity);
    }

    @Override
    public Map<Product, Integer> getAll() {
        // TODO Think about encapsulation
        return data;
    }

    @Override
    public Integer getQuantityOf(Product product) {
        return Optional.ofNullable(data.get(product)).orElse(0);
    }
}
