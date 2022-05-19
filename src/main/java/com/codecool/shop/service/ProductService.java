package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryDao.getAll();
    }

    public ProductCategory getProductCategory(String categoryName) {
        return productCategoryDao.find(categoryName);
    }

    public int countAvailableProducts(int categoryId, List<Supplier> selectedSuppliers) {
        if (selectedSuppliers.isEmpty()) {
            return productCategoryDao.find(categoryId).getNumberOfProducts();
        }
        int numberOfProducts = 0;
        for (Supplier supplier : selectedSuppliers) {
            numberOfProducts += supplier.getProducts().stream()
                    .filter(product -> product.hasCategory(categoryId))
                    .count();
        }
        return numberOfProducts;
    }

    public List<ProductCategory> getProductCategoryBySupplier(int supplierId) {
        return productCategoryDao.getBySupplierId(supplierId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getProductsForCategory(String categoryName) {
        var category = productCategoryDao.find(categoryName);
        return productDao.getBy(category);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }


    public List<Supplier> getSupplierCategories() {
        return supplierDao.getAll();
    }

    public Supplier getSupplierCategory(int id) {
        return supplierDao.find(id);
    }

    public Set<Product> getProductsByFilter(List<Integer> categoryIds, List<Supplier> selectedSuppliers) {
        List<ProductCategory> selectedCategories = getProductCategories(categoryIds);
        return (!selectedCategories.isEmpty()) ?
                getProductsByCategoriesAndSuppliers(selectedCategories, selectedSuppliers) :
                this.getProductsBySuppliers(selectedSuppliers);
    }

    public Set<Integer> getAvailableCategories(List<Supplier> suppliers) {
        return suppliers.isEmpty() ? Set.copyOf(productCategoryDao.getAllId()) :
                suppliers.stream()
                        .map(Supplier::getProductCategoryIds)
                        .flatMap(List::stream)
                        .collect(Collectors.toSet());
    }

    public List<ProductCategory> getProductCategories(List<Integer> categoryIds) {
        return categoryIds.stream()
                .map(this::getProductCategory)
                .collect(Collectors.toList());
    }

    public List<Supplier> getSuppliers(List<Integer> supplierIds) {
        return supplierIds.stream()
                .map(this::getSupplierCategory)
                .collect(Collectors.toList());
    }

    public Set<Product> getProductsBySuppliers(List<Supplier> suppliers) {
        return  Set.copyOf(suppliers.stream()
                .map(Supplier::getProducts)
                .reduce(new ArrayList<>(), (arrayList, productList) -> {
                    arrayList.addAll(productList);
                    return arrayList;
                }));
    }

    private Set<Product> getProductsByCategoriesAndSuppliers(List<ProductCategory> productCategories, List<Supplier> selectedSuppliers) {
        return productCategories.stream()
                .map(productCategory ->  productDao.getBy(productCategory))
                .flatMap(List::stream)
                .filter(product -> selectedSuppliers.isEmpty() || product.hasSupplier(selectedSuppliers))
                .collect(Collectors.toSet());
    }

}
