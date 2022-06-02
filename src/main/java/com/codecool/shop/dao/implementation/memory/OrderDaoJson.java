package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class OrderDaoJson implements OrderDao {

    private String filePathBeginning = "src/main/resources/order-";
    private String extension = ".json";
    private static OrderDaoJson instance;

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoJson();
        }
        return instance;
    }

    @Override
    public void add(Order order, UUID uuid) {
        Gson gson = new Gson();
        String fileName = filePathBeginning + uuid + extension;
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileWriter fileWriter =  new FileWriter(file.getAbsolutePath());
            gson.toJson(order, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
