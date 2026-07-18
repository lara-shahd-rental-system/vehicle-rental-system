package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rental.exception.RentalException;

class MotorcycleTest {

    @Test
    void testUserOver18CanRent() throws RentalException {
        Motorcycle moto = new Motorcycle("V001", "Honda", "CBR", 2022, 40.0);
        User user = new User("john", "pass123", "CUSTOMER", null, 25, null);

        moto.validateEligibility(user);
    }

    @Test
    void testUserExactly18CanRent() throws RentalException {
        Motorcycle moto = new Motorcycle("V001", "Honda", "CBR", 2022, 40.0);
        User user = new User("john", "pass123", "CUSTOMER", null, 18, null);

        moto.validateEligibility(user);
    }

    @Test
    void testUserUnder18CannotRent() {
        Motorcycle moto = new Motorcycle("V001", "Honda", "CBR", 2022, 40.0);
        User user = new User("john", "pass123", "CUSTOMER", null, 17, null);

        assertThrows(RentalException.class, () -> {
            moto.validateEligibility(user);
        });
    }
}