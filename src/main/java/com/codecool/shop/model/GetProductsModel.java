package com.codecool.shop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetProductsModel {
    private final Set<Product> productsByFilter;
    private final Map<Integer, Integer> numberOfProductsInCategories;

    public GetProductsModel(Set<Product> productsByFilter, HashMap<Integer, Integer> numberOfProductsInCategories) {
        this.productsByFilter = productsByFilter;
        this.numberOfProductsInCategories = numberOfProductsInCategories;
    }
}
