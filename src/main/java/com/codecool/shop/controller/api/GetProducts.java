package com.codecool.shop.controller.api;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;

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
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        List<Integer> supplierIds = getQueryParamValue(request, "supplier_id");
        List<Integer> categoryIds = getQueryParamValue(request, "category_id");
        List<ProductCategory> productCategories = getProductCategories(productService, categoryIds);
        List<Supplier> suppliers = getSuppliers(productService, supplierIds);
        List<Integer> availableCategories = getAvailableCategories(productService, suppliers);
        List<Product> products = (!productCategories.isEmpty()) ? getProductsByCategories(productDataStore, productCategories) : getProductsBySuppliers(suppliers);


        System.out.printf("suppliers: %s, categories: %s%n products: %s%n Available categories: %s%n", suppliers, productCategories, products, availableCategories);
    }

    private List<Integer> getAvailableCategories(ProductService productService, List<Supplier> suppliers) {
        return suppliers.stream()
                .map(Supplier::getProductCategoryIds)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ProductCategory> getProductCategories(ProductService productService, List<Integer> categoryIds) {
        return categoryIds.stream()
                .map(productService::getProductCategory)
                .collect(Collectors.toList());
    }

    private List<Supplier> getSuppliers(ProductService productService, List<Integer> supplierIds) {
        return supplierIds.stream()
                .map(productService::getSupplierCategory)
                .collect(Collectors.toList());
    }

    private List<Product> getProductsBySuppliers(List<Supplier> suppliers) {
        return suppliers.stream()
                .map(Supplier::getProducts)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Product> getProductsByCategories(ProductDao productDataStore, List<ProductCategory> productCategories) {
        return productCategories.stream()
                .map(productDataStore::getBy)
                .flatMap(List::stream)
                .collect(Collectors.toList());
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
