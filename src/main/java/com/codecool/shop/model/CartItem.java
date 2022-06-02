package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class CartItem {

    private final Product product;
    private int quantity;
    private static final int MAX_QUANTITY = 10;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        if (quantity < MAX_QUANTITY) {
            quantity++;
        }
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.min(quantity, MAX_QUANTITY);
    }

    public BigDecimal getPrice() {
        return product.getDefaultPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public int getProductId() {
        return this.product.getId();
    }

    public Currency getCurrency() {
        return product.getDefaultCurrency();
    }
}
