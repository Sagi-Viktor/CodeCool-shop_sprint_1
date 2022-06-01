package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import com.codecool.shop.service.Services;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/cart-items"})
public class GetCartItemsAPI  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = Services.CartService().getCart();

        var cartItemsJson = new Gson().toJson(cart);
        resp.getWriter().println(cartItemsJson);
    }
}
