package com.codecool.shop.model;

public class OrderModel {

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
                      String zipCode, String street, String houseNumber, String paymentType) {
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
    }

    public void addBillingAddress(String billingCountry, String billingState, String billingZipCode, String billingStreet, String billingHouseNumber) {
        this.billingCountry = country;
        this.billingState = state;
        this.billingZipCode = zipCode;
        this.billingStreet = street;
        this.billingHouseNumber = houseNumber;
    }

    public String getLastName() {
        return lastName;
    }
}
