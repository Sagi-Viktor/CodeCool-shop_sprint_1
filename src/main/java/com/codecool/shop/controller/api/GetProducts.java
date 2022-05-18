package com.codecool.shop.controller.api;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.GetProductsModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "getSelectedProducts", urlPatterns = {"/api/products"}, loadOnStartup = 2)
public class GetProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
        PrintWriter out = response.getWriter();

        List<Integer> supplierIds = getQueryParamValue(request, "supplier_id");
        List<Integer> categoryIds = getQueryParamValue(request, "category_id");
        List<ProductCategory> productCategories = productService.getProductCategories(categoryIds);
        List<Supplier> suppliers = productService.getSuppliers(supplierIds);
        Set<Integer> availableCategories = productService.getAvailableCategories(suppliers);
        List<Product> products = (!productCategories.isEmpty()) ? productService.getProductsByCategories(productCategories) : productService.getProductsBySuppliers(suppliers);
        GetProductsModel getProductsModel = new GetProductsModel(availableCategories, products);
        out.println(new Gson().toJson(getProductsModel));
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
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
