package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import com.codecool.shop.dao.implementation.memory.OrderDaoJson;
import com.codecool.shop.model.OrderModel;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Checkout", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        CartDao cartDaoMem = CartDaoMem.getInstance();
        OrderDaoJson orderDaoJson = new OrderDaoJson();

        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String state = req.getParameter("state");
        String zipCode = req.getParameter("city-code");
        String street = req.getParameter("street");
        String houseNumber = req.getParameter("house-number");
        String paymentType = req.getParameter("payment");

        OrderModel orderModel = new OrderModel(firstName, lastName, email, country, state, zipCode, street, houseNumber, paymentType, cartDaoMem.getId().toString());

        if (Optional.ofNullable(req.getParameter("same-address")).isEmpty()) {
            String billingCountry = req.getParameter("billing-country");
            String billingState = req.getParameter("billing-state");
            String billingZipCode = req.getParameter("billing-city-code");
            String billingStreet = req.getParameter("billing-street");
            String billingHouseNumber = req.getParameter("billing-house-number");

            orderModel.addBillingAddress(billingCountry, billingState, billingZipCode, billingStreet, billingHouseNumber);
        }

        orderDaoJson.add(orderModel, cartDaoMem.getId());
        switch (paymentType) {
            case "paypal":
                resp.sendRedirect("checkout/payment/paypal");
                break;
            case "credit-card":
                resp.sendRedirect("checkout/payment/creditcard");
                break;
        }
    }
}
