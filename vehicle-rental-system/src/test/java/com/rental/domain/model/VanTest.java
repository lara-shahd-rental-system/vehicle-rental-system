package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rental.exception.RentalException;

class VanTest {

    @Test
    void testAnyUserCanRentVan() throws RentalException {
        Van van = new Van("V001", "Ford", "Transit", 2021, 60.0);
        User user = new User("john", "pass123", "CUSTOMER");

        van.validateEligibility(user);
    }
}