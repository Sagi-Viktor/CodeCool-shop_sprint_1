package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.google.common.io.CharStreams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/api/add-to-cart"})
public class AddToCartAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Replace CharStreams
        String requestData = CharStreams.toString(req.getReader());
        requestData = requestData.replaceAll("\"", "");
        int productId = Integer.parseInt(requestData);

        CartDao cart = CartDaoMem.getInstance();
        ProductDao productStore = ProductDaoMem.getInstance();
        Product product = productStore.find(productId);


        Optional<CartItem> cartItem = cart.find(productId);
        cartItem.ifPresentOrElse(CartItem::increaseQuantity, () -> cart.add(new CartItem(product, 1)));
    }
}
