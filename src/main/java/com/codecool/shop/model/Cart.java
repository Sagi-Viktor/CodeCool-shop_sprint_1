package com.codecool.shop.model;

import com.codecool.shop.service.Services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Cart {

    private Set<CartItem> cartItems;
    private BigDecimal totalPrice;

    public Cart(Set<CartItem> cartItems, BigDecimal totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public Cart() {
        this(new HashSet<>(), new BigDecimal(0));
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Optional<CartItem> getCartItem(int productId) {
        return cartItems.stream().filter(cartItem -> cartItem.getProductId() == productId).findFirst();
    }

    public void add(int productId) {
        getCartItem(productId).ifPresentOrElse(CartItem::increaseQuantity, () -> cartItems.add(new CartItem(Services.ProductService().getProduct(productId), 1)));

    }
}
