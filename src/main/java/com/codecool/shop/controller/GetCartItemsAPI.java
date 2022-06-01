package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.service.CartService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@WebServlet(urlPatterns = {"/api/cart-items"})
public class GetCartItemsAPI  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDataStore = CartDaoMem.getInstance();
        Set<CartItem> cartItems = cartDataStore.getAll();
        BigDecimal totalPrice = CartService.getTotalPrice();
        Cart cart = new Cart(cartItems, totalPrice);

        var cartItemsJson = new Gson().toJson(cart);
        resp.getWriter().println(cartItemsJson);
    }
}
