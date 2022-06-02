package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.OrderModel;

import javax.sql.DataSource;
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
    public void add(OrderModel orderModel, UUID uuid) {

    }

    @Override
    public void update(OrderModel orderModel) {

    }

    @Override
    public OrderModel get(String id) {
        return null;
    }

    @Override
    public List<OrderModel> getAll() {
        return null;
    }
}
