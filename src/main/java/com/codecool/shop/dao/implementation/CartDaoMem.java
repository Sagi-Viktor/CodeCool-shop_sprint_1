package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartItem;

import java.util.*;
import java.util.stream.Collectors;

public class CartDaoMem implements CartDao {

    private Set<CartItem> data = new HashSet<>();
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
    public void add(CartItem cartItem) {
        data.add(cartItem);
    }

    @Override
    public void remove(CartItem cartItem) {
        data.remove(cartItem);
    }

    @Override
    public Set<CartItem> getAll() {
        return data;
    }

    @Override
    public Optional<CartItem> find(int productId) {
        return data.stream().filter(item -> item.getProductId() == productId).findFirst();
    }
}
