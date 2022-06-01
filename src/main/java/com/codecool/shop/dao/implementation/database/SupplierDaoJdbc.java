package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
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
            String sqlQuery = "INSERT INTO suppliers(id, supplier_name, description) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, supplier.getId());
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.setString(3, supplier.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException("Error under add supplier to database: " + supplier, throwables);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "SELECT * FROM suppliers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
//            int id, String name, String description, List<Product> products, List<Integer> productCategoryIds
        } catch (SQLException throwables) {
            throw new RuntimeException("Error under get supplier from database. searched id: " + id, throwables);
        }
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
