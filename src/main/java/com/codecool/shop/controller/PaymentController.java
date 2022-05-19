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

@WebServlet(name = "payment", urlPatterns = {"/checkout/payment/creditcard", "/checkout/payment/paypal", "/checkout/payment/summary"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        WebContext context = new WebContext(req, resp, req.getServletContext());

        String path = req.getServletPath();
        if (path.contains("paypal")) {
            engine.process("payment-with-paypal.html", context, resp.getWriter());
        } if (path.contains("creditcard")) {
            engine.process("payment-with-creditcard.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String path = req.getServletPath();
        if (path.contains("paypal")) {
            System.out.println("PayPal username: " +req.getParameter("username"));

        } else if (path.contains("creditcard")) {
            StringBuilder cardNumber = new StringBuilder();
            for (int i = 1; i <= 4; i++) {cardNumber.append(req.getParameter("card-num-" + i));}
            System.out.println("Buyer credit card number: " + cardNumber);

        }
        System.out.println("Payment Done");
        engine.process("payment-summary.html", context, resp.getWriter());
    }
}
