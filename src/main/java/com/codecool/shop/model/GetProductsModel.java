package com.codecool.shop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProductsModel {
    private final List<Product> productsByFilter;
    private final Map<Integer, Integer> numberOfProductsInCategories;

    public GetProductsModel(List<Product> productsByFilter, HashMap<Integer, Integer> numberOfProductsInCategories) {
        this.productsByFilter = productsByFilter;
        this.numberOfProductsInCategories = numberOfProductsInCategories;
    }
}
