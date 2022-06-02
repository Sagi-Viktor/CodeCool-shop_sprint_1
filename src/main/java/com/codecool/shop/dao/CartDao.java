package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CartDao {
    void add(CartItem cartItem);
    void add(Cart cart);
    void remove(CartItem cartItem);
    void removeAll();
    Set<CartItem> getAll();
    Optional<CartItem> find(int productId);
    UUID getId();

    Cart get();
}
