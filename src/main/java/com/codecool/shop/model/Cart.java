package com.codecool.shop.model;

import com.codecool.shop.service.Services;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Cart {

    private Set<CartItem> cartItems;
    private final UUID id;

    public Cart(Set<CartItem> cartItems, UUID id) {
        this.cartItems = cartItems;
        this.id = id;
    }

    public Cart() {
        this(new HashSet<>(), UUID.randomUUID());
    }

    public Set<CartItem> getCartItems() {
        return Set.copyOf(cartItems);
    }

    public Map<Integer, Integer> getCartItemIdsWithQuantity() {
        return cartItems.stream().collect(Collectors.toMap(CartItem::getProductId, CartItem::getQuantity, (o1, o2) -> o1, LinkedHashMap::new));
    }

    public void add(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(cartItems.stream().map(CartItem::getPrice).reduce(new BigDecimal(0), BigDecimal::add).doubleValue());
    }

    public Optional<CartItem> getCartItem(int productId) {
        return cartItems.stream().filter(cartItem -> cartItem.getProductId() == productId).findFirst();
    }

    public void add(int productId) {
        getCartItem(productId).ifPresentOrElse(CartItem::increaseQuantity, () -> cartItems.add(new CartItem(Services.ProductService().getProduct(productId), 1)));

    }

    public UUID getId() {
        return id;
    }

    public void setProductQuantity(int productId, int quantity) {
        getCartItem(productId).ifPresent(item -> item.setQuantity(quantity));
    }

    public void removeAll() {

    }

    public void remove(int productId) {
        cartItems.remove(getCartItem(productId).orElse(null));
    }

    public Currency getCurrency() {
        return cartItems.iterator().next().getCurrency();
    }
}
