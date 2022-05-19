package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductCategory extends BaseModel {
    private String department;
    private final Set<Integer> supplierIds;
    transient private List<Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.supplierIds = new HashSet<>();
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

    public int getNumberOfProducts() {
        return products.size();
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

    public void addSupplier(Supplier supplier) {
        supplierIds.add(supplier.getId());
    }
}