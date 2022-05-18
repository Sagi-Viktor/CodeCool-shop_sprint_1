package com.codecool.shop.model;

import java.util.List;
import java.util.Set;

public class GetProductsModel {
    private final Set<Integer> availableProductCategories;
    private final List<Product> productsByFilter;

    public GetProductsModel(Set<Integer> availableProductCategories, List<Product> productsByFilter) {
        this.availableProductCategories = availableProductCategories;
        this.productsByFilter = productsByFilter;
    }
}
