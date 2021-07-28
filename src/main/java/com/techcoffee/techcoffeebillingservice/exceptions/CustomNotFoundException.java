package com.techcoffee.techcoffeebillingservice.exceptions;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String customer_not_found) {
        super(customer_not_found);
    }
}
