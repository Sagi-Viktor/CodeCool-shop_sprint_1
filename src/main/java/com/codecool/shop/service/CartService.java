package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class CartService {
    private final CartDao cartDao;
    private Cart cart = null;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }


    public void createCart() {
        //TODO add user id
        cart = new Cart();
    }

    public void save() {
        //TODO add user id
        cartDao.add(cart);
    }

    public void load() {
        //TODO add user id
        cart = cartDao.get();
    }

    public Cart getCart() {
        if (cart == null) cart = new Cart();
        return cart;
    }

    public BigDecimal getTotalPrice() {
        return cart.getTotalPrice();
    }

    public void addToCart(int productId) {
        if (cart == null) cart = new Cart();
        cart.add(productId);
    }

    public Set<CartItem> getAllCartItems() {
        if (cart == null) cart = new Cart();
        return cart.getCartItems();
    }

    public UUID getId() {
        return cart.getId();
    }

    public void setProductQuantity(int productId, int quantity) {
        cart.setProductQuantity(productId, quantity);
    }

    public void removeAll() {
        cartDao.removeAll();
        cart.removeAll();
    }

    public void remove(int productId) {
        cartDao.find(productId).ifPresent(cartDao::remove);
        cart.remove(productId);
    }
}
