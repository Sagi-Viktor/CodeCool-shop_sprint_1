package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/edit-cart"})
public class EditCartAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Refactor this
        BufferedReader bufferedReader = req.getReader();
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            content.append(inputLine);
        }
        String cartItemString = content.toString();
        JsonObject cartItem = new Gson().fromJson(cartItemString, JsonObject.class);
        int productId = cartItem.get("productId").getAsInt();
        int quantity = cartItem.get("quantity").getAsInt();

        CartDao cart = CartDaoMem.getInstance();
        cart.find(productId).ifPresent(item -> item.setQuantity(quantity));

        var cartItems = cart.getAll();

        var cartItemsJson = new Gson().toJson(cartItems);
        resp.getWriter().println(cartItemsJson);
    }

}
