package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierDaoJdbc implements SupplierDao {
    private final DataSource dataSource;
    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static SupplierDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new SupplierDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "INSERT INTO suppliers(id, supplier_name, description, product_id, product_category_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, supplier.getId());
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.setString(3, supplier.getDescription());
            preparedStatement.setArray(4, (Array) supplier.getProductsIds());
            preparedStatement.setArray(5, (Array) supplier.getProductCategoryIds());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException("Error under add supplier to database: " + supplier, throwables);
        }
    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
