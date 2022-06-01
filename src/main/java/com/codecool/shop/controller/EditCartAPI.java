package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/edit-cart"})
public class EditCartAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject cartItem = JsonUtil.getRequestJsonObject(req);
        int productId = cartItem.get("productId").getAsInt();
        int quantity = cartItem.get("quantity").getAsInt();

        CartDao cart = CartDaoMem.getInstance();
        cart.find(productId).ifPresent(item -> item.setQuantity(quantity));
    }

}
