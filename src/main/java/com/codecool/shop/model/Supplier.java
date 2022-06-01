package com.codecool.shop.model;

public class Supplier extends BaseModel {

    public Supplier(String name, String description) {
        super(name, description);
    }

    public Supplier(String name, String description, int id) {
        super(name, description, id);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}