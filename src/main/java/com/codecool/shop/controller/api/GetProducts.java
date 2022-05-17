package com.codecool.shop.controller.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "getSelectedProducts", urlPatterns = {"/api/products"}, loadOnStartup = 2)
public class GetProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> supplerIds = getQueryParamValue(request, "supplier_id");
        List<Integer> categoryIds = getQueryParamValue(request, "category_id");
    }

    private List<Integer> getQueryParamValue(HttpServletRequest request, String queryParameter) {
        Optional<String> queryParameterValue = Optional.ofNullable(request.getParameter(queryParameter));
        return Arrays.stream(queryParameterValue.orElse("").split(","))
                .filter(this::isNumeric)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
