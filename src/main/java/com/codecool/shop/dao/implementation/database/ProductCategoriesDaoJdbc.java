package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.util.List;

public class ProductCategoriesDaoJdbc implements ProductCategoryDao {
    private final DataSource dataSource;
    private static ProductCategoriesDaoJdbc instance = null;


    public ProductCategoriesDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductCategoriesDaoJdbc getInstance(DataSource dataSource){
        if (instance == null){
            instance = new ProductCategoriesDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public ProductCategory find(String name) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    @Override
    public List<Integer> getAllId() {
        return null;
    }
}
