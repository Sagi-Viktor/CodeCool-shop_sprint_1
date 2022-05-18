package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface CartDao {
    void add(CartItem cartItem);
    Set<CartItem> getAll();
    Optional<CartItem> find(int productId);
}
