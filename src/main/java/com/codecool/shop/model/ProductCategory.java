package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel {
    private String department;
    private final String description;
    private final List<Integer> supplierIds;
    private List<Product> products;

    public ProductCategory(String name, String department, String description, Integer... supplierIds) {
        super(name);
        this.department = department;
        this.description = description;
        this.supplierIds = List.of(supplierIds);
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String getDescription() {
        return description;
    }

    public List<Integer> getSupplierIds() {
        return List.copyOf(supplierIds);
    }

    public boolean hasSupplier(int supplierId) {
        return supplierIds.contains(supplierId);
    }

    @Override
    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}