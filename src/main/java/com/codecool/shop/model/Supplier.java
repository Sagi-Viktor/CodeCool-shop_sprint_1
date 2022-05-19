package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {
    transient private List<Product> products;
    private List<Integer> productCategoryIds;

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
        this.productCategoryIds = new ArrayList<>();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public int getNumberOfProducts() {
        return products.size();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addProductCategory(int productCategoryId) {
        productCategoryIds.add(productCategoryId);
    }

    public int getNumberOfProductCategories() {
        return productCategoryIds.size();
    }

    public List<Integer> getProductCategoryIds() {
        return productCategoryIds;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}