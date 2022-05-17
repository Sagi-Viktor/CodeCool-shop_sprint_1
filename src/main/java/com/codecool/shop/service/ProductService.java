package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<ProductCategory> getProductCategories(){
        return productCategoryDao.getAll();
    }

    public ProductCategory getProductCategory(String categoryName){
        return productCategoryDao.find(categoryName);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getProductsForCategory(String categoryName){
        var category = productCategoryDao.find(categoryName);
        return productDao.getBy(category);
    }


    public List<Supplier> getSupplierCategories() {
        return supplierDao.getAll();
    }

    public Supplier getSupplierCategory(int id) {
        return supplierDao.find(id);
    }
}
