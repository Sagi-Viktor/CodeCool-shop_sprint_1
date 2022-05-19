package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Cart {

    private final Set<CartItem> cartItems;
    private final BigDecimal totalPrice;

    public Cart(Set<CartItem> cartItems, BigDecimal totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
