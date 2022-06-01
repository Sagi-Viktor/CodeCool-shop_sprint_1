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


    public Cart getCart() {
        return cart;
    }

    private BigDecimal getTotalPrice(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItem::getPrice).reduce(new BigDecimal(0), BigDecimal::add);
    }

    public void addToCart(int productId) {
        if (cart == null) cart = new Cart();
        cart.add(productId);
    }

    public Set<CartItem> getAllCartItems() {
        return cartDao.getAll();
    }

    public UUID getId() {
        return cartDao.getId();
    }

    public void setProductQuantity(int productId, int quantity) {
        cartDao.find(productId).ifPresent(item -> item.setQuantity(quantity));
    }

    public void removeAll() {
        cartDao.removeAll();
    }

    public void remove(int productId) {
        cartDao.find(productId).ifPresent(cartDao::remove);
    }
}
