package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.*;
import java.util.function.Function;
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

    public Product getProduct(int id) {
        return productDao.find(id);
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryDao.getAll();
    }

    public ProductCategory getProductCategory(String categoryName) {
        return productCategoryDao.find(categoryName);
    }

    public int countAvailableProducts(int categoryId, List<Supplier> selectedSuppliers) {
        if (selectedSuppliers.isEmpty()) {
            return productDao.getProductsByCategory(productCategoryDao.find(categoryId)).size();
        }
        int numberOfProducts = 0;
        for (Supplier supplier : selectedSuppliers) {
            productDao.getProductsBySupplier(supplier);
            numberOfProducts += productDao.getProductsBySupplier(supplier).stream()
                    .filter(product -> product.hasCategory(categoryId))
                    .count();
        }
        return numberOfProducts;
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getProductsByCategory(category);
    }

    public List<Product> getProductsForCategory(String categoryName) {
        var category = productCategoryDao.find(categoryName);
        return productDao.getProductsByCategory(category);
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
                        .map(productDao::getProductsBySupplier)
                        .flatMap(Collection::stream)
                        .map(Product::getProductCategoryIds)
                        .flatMap(Collection::stream)
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
                .map(productDao::getProductsBySupplier)
                .reduce(new ArrayList<>(), (arrayList, productList) -> {
                    arrayList.addAll(productList);
                    return arrayList;
                }));
    }

    private Set<Product> getProductsByCategoriesAndSuppliers(List<ProductCategory> productCategories, List<Supplier> selectedSuppliers) {
        return productCategories.stream()
                .map(productCategory ->  productDao.getProductsByCategory(productCategory))
                .flatMap(List::stream)
                .filter(product -> selectedSuppliers.isEmpty() || product.hasSupplier(selectedSuppliers))
                .collect(Collectors.toSet());
    }

    public Map<Supplier, Integer> getSuppliersWithProductCount() {
        return supplierDao.getAll().stream()
                .sorted(Comparator.comparing(BaseModel::getName))
                .collect(Collectors.toMap(Function.identity(), (supplier) ->(int) productDao.getAll().stream().filter(product -> product.hasSupplier(supplier)).count(), (o1, o2) -> o1, LinkedHashMap::new));
    }

    public Map<ProductCategory, Integer> getCategoriesWithProductCount() {
        return productCategoryDao.getAll().stream()
                .sorted(Comparator.comparing(BaseModel::getName))
                .collect(Collectors.toMap(Function.identity(), (productCategory) -> productDao.getProductsByCategory(productCategory).size(), (o1, o2) -> o1, LinkedHashMap::new));
    }
}
