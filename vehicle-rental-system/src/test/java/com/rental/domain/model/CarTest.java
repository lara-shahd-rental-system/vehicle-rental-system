package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rental.exception.RentalException;

class CarTest {

    @Test
    void testAnyUserCanRentCar() throws RentalException {
        Car car = new Car("V001", "BMW", "X5", 2020, 50.0);
        User user = new User("john", "pass123", "CUSTOMER");

        car.validateEligibility(user);
    }
}