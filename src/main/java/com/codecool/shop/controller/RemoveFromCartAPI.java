package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static com.codecool.shop.controller.JsonUtil.getRequestJsonObject;

@WebServlet(urlPatterns = {"/api/remove-from-cart"})
public class RemoveFromCartAPI extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject cartItem = getRequestJsonObject(req);
        int productId = cartItem.get("productId").getAsInt();

        CartDao cart = CartDaoMem.getInstance();
        cart.find(productId).ifPresent(cart::remove);
    }


}
