package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;

import java.util.*;

public class CartDaoMem implements CartDao {

    private Set<CartItem> data = new HashSet<>();
    private static CartDaoMem instance = null;
    private UUID id;

    private CartDaoMem() {
        id = UUID.randomUUID();
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
    public void add(Cart cart) {
        data.addAll(cart.getCartItems());
        id = cart.getId();
    }

    @Override
    public void remove(CartItem cartItem) {
        data.remove(cartItem);
    }

    @Override
    public void removeAll() {
        this.data = new HashSet<>();
    }

    @Override
    public Set<CartItem> getAll() {
        return data;
    }

    @Override
    public Optional<CartItem> find(int productId) {
        return data.stream().filter(item -> item.getProductId() == productId).findFirst();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Cart get() {
        return new Cart(data, id);
    }
}
