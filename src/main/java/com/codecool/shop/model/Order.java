package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class Order {
    private final String
            id,
            firstName,
            lastName,
            email,
            country,
            state,
            zipCode,
            street,
            houseNumber,
            paymentType;
    private String
            billingCountry,
            billingState,
            billingZipCode,
            billingStreet,
            billingHouseNumber;
    private final Map<Integer, Integer> cartItemIdsWithQuantity;
    private final BigDecimal totalPrice;
    private final Date date;
    private OrderStatusTypes orderStatus;

    public enum OrderStatusTypes {
        CHECKED,
        PAID,
        CONFIRMED,
        SHIPPED;
    }


    public Order
            (
                    String firstName,
                    String lastName,
                    String email,
                    String country,
                    String state,
                    String zipCode,
                    String street,
                    String houseNumber,
                    String paymentType,
                    Cart cart,
                    OrderStatusTypes orderStatus
            ) {
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
        this.id = cart.getId().toString();
        this.cartItemIdsWithQuantity = cart.getCartItemIdsWithQuantity();
        this.totalPrice = cart.getTotalPrice();
        date = new Date();
        this.orderStatus = orderStatus;
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
