package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.CartItem;

import java.math.BigDecimal;

public class CartService {
    private static final CartDao CART_DAO = CartDaoMem.getInstance();

    public static BigDecimal getTotalPrice() {
        return CART_DAO.getAll().stream()
                .map(CartItem::getPrice).reduce(new BigDecimal(0), BigDecimal::add);
    }

}
