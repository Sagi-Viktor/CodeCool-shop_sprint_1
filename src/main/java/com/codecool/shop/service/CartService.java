package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class CartService {
    private CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }


    public Cart getCart() {
        return new Cart(cartDao.getAll(), getTotalPrice());
    }

    public BigDecimal getTotalPrice() {
        return cartDao.getAll().stream()
                .map(CartItem::getPrice).reduce(new BigDecimal(0), BigDecimal::add);
    }

    public void addToCart(int productId) {
        Optional<CartItem> cartItem = cartDao.find(productId);
        cartItem.ifPresentOrElse(CartItem::increaseQuantity, () -> cartDao.add(new CartItem(Services.getProductService().getProduct(productId), 1)));

    }
}
