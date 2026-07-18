package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rental.exception.RentalException;

class TruckTest {

    @Test
    void testUserWithCommercialLicenseCanRent() throws RentalException {
        Truck truck = new Truck("V001", "Volvo", "FH16", 2021, 100.0);
        User user = new User("john", "pass123", "CUSTOMER", null, 30, "COMMERCIAL");

        truck.validateEligibility(user);
    }

    @Test
    void testUserWithoutCommercialLicenseCannotRent() {
        Truck truck = new Truck("V001", "Volvo", "FH16", 2021, 100.0);
        User user = new User("john", "pass123", "CUSTOMER", null, 30, "STANDARD");

        assertThrows(RentalException.class, () -> {
            truck.validateEligibility(user);
        });
    }

    @Test
    void testUserWithNullLicenseCannotRent() {
        Truck truck = new Truck("V001", "Volvo", "FH16", 2021, 100.0);
        User user = new User("john", "pass123", "CUSTOMER", null, 30, null);

        assertThrows(RentalException.class, () -> {
            truck.validateEligibility(user);
        });
    }
}