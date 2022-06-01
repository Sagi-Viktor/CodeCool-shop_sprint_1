package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartItem;

import java.math.BigDecimal;

public class CartService {
    private CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }



    public BigDecimal getTotalPrice() {
        return cartDao.getAll().stream()
                .map(CartItem::getPrice).reduce(new BigDecimal(0), BigDecimal::add);
    }

}
