package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            int supplierId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            return new Supplier(name, description, supplierId);
        } catch (SQLException throwables) {
            throw new RuntimeException("Error under get supplier from database. searched id: " + id, throwables);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection connection = dataSource.getConnection()) {
                String sqlQuery = "SELECT * FROM suppliers";
                ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
                List<Supplier> suppliers = new ArrayList<>();

                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);

                    suppliers.add(new Supplier(name, description, id));
                }
                return suppliers;
        } catch (SQLException msg) {
            throw new RuntimeException("Error under get all supplier from database", msg);
        }
    }
}
