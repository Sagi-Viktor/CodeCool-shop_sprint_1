package com.codecool.shop.model;

import java.util.List;

public class GetProductsModel {
    private final List<Integer> availableProductCategories;
    private final List<Product> productsByFilter;

    public GetProductsModel(List<Integer> availableProductCategories, List<Product> productsByFilter) {
        this.availableProductCategories = availableProductCategories;
        this.productsByFilter = productsByFilter;
    }
}
