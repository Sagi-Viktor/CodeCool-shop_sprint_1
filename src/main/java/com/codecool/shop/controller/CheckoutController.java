package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String email = req.getParameter("email");

        String country = req.getParameter("country");
        String state = req.getParameter("state");
        String zipCode = req.getParameter("city-code");
        String street = req.getParameter("street");
        String houseNumber = req.getParameter("house-number");

        String paymentType = req.getParameter("payment");

        ArrayList<String> orderDetails = new ArrayList<>();

        orderDetails.add(firstName);
        orderDetails.add(lastName);
        orderDetails.add(email);
        orderDetails.add(country);
        orderDetails.add(state);
        orderDetails.add(zipCode);
        orderDetails.add(street);
        orderDetails.add(houseNumber);
        orderDetails.add(paymentType);

        Optional<String> checkBoxForDifferentAddress = Optional.ofNullable(req.getParameter("same-address"));
        if (checkBoxForDifferentAddress.isEmpty()) {
            String billingCountry = req.getParameter("billing-country");
            String billingState = req.getParameter("billing-state");
            String billingZipCode = req.getParameter("billing-city-code");
            String billingStreet = req.getParameter("billing-street");
            String bullingHouseNumber = req.getParameter("billing-house-number");

            orderDetails.add(billingCountry);
            orderDetails.add(billingState);
            orderDetails.add(billingZipCode);
            orderDetails.add(billingStreet);
            orderDetails.add(bullingHouseNumber);
        }

        //TODO create new OrderModel with orderDetails list

    }
}
