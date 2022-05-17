package com.codecool.shop.controller.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "getSelectedProducts", urlPatterns = {"/api/products"}, loadOnStartup = 2)
public class GetProducts extends HttpServlet {
}
