package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private final List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(data.size() + 1);
        data.add(category);
    }

    @Override
    public ProductCategory find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public ProductCategory find(String name) {
        return data.stream().filter(product -> product.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return data;
    }

    @Override
    public List<ProductCategory> getBySupplierId(int supplierId) {
        return data.stream().filter(productCategory -> productCategory.hasSupplier(supplierId)).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getAllId() {
        return data.stream()
                .map(ProductCategory::getId)
                .collect(Collectors.toList());
    }
}
