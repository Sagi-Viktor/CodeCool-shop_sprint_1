package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderDaoJdbc implements OrderDao {
    private final DataSource dataSource;
    private static OrderDaoJdbc instance = null;

    private OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static OrderDao getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new OrderDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        } catch (SQLException throwables) {
            throw new RuntimeException("Error under adding order to database: " + order, throwables);
        }

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Order get(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
