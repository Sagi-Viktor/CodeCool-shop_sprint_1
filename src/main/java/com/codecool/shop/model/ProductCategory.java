package com.codecool.shop.model;

public class ProductCategory extends BaseModel {
    private String department;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
    }

    public ProductCategory(String name, String department, String description, int id) {
        super(name, description, id);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}