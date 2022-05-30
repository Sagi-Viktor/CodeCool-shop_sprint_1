package com.codecool.shop.model;

public class OrderModel {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String state;
    private String zipCode;
    private String street;
    private String houseNumber;
    private String paymentType;
    private String billingCountry;
    private String billingState;
    private String billingZipCode;
    private String billingStreet;
    private String billingHouseNumber;

    public OrderModel(String firstName, String lastName, String email, String country, String state,
                      String zipCode, String street, String houseNumber, String paymentType, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.paymentType = paymentType;
        billingCountry = country;
        billingState = state;
        billingZipCode = zipCode;
        billingStreet = street;
        billingHouseNumber = houseNumber;
        this.id = id;
    }

    public void addBillingAddress(String billingCountry, String billingState, String billingZipCode, String billingStreet, String billingHouseNumber) {
        this.billingCountry = billingCountry;
        this.billingState = billingState;
        this.billingZipCode = billingZipCode;
        this.billingStreet = billingStreet;
        this.billingHouseNumber = billingHouseNumber;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }
}
