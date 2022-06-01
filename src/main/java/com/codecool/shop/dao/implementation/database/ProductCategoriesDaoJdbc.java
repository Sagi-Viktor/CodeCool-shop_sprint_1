package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try (Connection connection = dataSource.getConnection()){
            String sqlQuery = "INSERT INTO categories(id, category_name, department, description)" +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setString(3, category.getDepartment());
            preparedStatement.setString(4, category.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException msg){
            throw new RuntimeException("Error under add product category to database: " + category, msg);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "SELECT * FROM categories WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            int categoryId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String department = resultSet.getString(3);
            String description = resultSet.getString(4);
            return new ProductCategory(name, department, description, categoryId);
        } catch (SQLException msg) {
            throw new RuntimeException("Error under get category from database. Searched Id: " + id, msg);
        }
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
