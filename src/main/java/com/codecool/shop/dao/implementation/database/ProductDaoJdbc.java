package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private final DataSource dataSource;
    private static ProductDaoJdbc instance = null;


    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductDaoJdbc getInstance(DataSource dataSource){
        if (instance == null){
            instance = new ProductDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getProductsBySupplier(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(ProductCategory productCategory) {
        return null;
    }
}
