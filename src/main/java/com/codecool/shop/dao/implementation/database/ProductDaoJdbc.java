package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try (Connection connection = dataSource.getConnection()){
            String sqlQuery = "INSERT INTO products(id, product_name, description, price, image_name, currency, supplier_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setBigDecimal(4, product.getPriceToDB());
            preparedStatement.setString(5, product.getImageName());
            preparedStatement.setString(6, String.valueOf(product.getDefaultCurrency()));
            preparedStatement.setInt(7, product.getSupplier().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException msg){
            throw new RuntimeException("Error under add product to database: " + product, msg);
        }
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
