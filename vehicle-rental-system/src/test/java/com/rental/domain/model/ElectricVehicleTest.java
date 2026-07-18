package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rental.exception.RentalException;

class ElectricVehicleTest {

    @Test
    void testHighBatteryCanRent() throws RentalException {
        ElectricVehicle ev = new ElectricVehicle("V001", "Tesla", "Model3", 2023, 80.0, 50);
        User user = new User("john", "pass123", "CUSTOMER");

        ev.validateEligibility(user);
    }

    @Test
    void testBatteryExactly20PercentCanRent() throws RentalException {
        ElectricVehicle ev = new ElectricVehicle("V001", "Tesla", "Model3", 2023, 80.0, 20);
        User user = new User("john", "pass123", "CUSTOMER");

        ev.validateEligibility(user);
    }

    @Test
    void testLowBatteryCannotRent() {
        ElectricVehicle ev = new ElectricVehicle("V001", "Tesla", "Model3", 2023, 80.0, 10);
        User user = new User("john", "pass123", "CUSTOMER");

        assertThrows(RentalException.class, () -> {
            ev.validateEligibility(user);
        });
    }

    @Test
    void testZeroBatteryCannotRent() {
        ElectricVehicle ev = new ElectricVehicle("V001", "Tesla", "Model3", 2023, 80.0, 0);
        User user = new User("john", "pass123", "CUSTOMER");

        assertThrows(RentalException.class, () -> {
            ev.validateEligibility(user);
        });
    }

    @Test
    void testInvalidBatteryLevelThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ElectricVehicle("V001", "Tesla", "Model3", 2023, 80.0, 150);
        });
    }

    @Test
    void testNegativeBatteryLevelThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ElectricVehicle("V001", "Tesla", "Model3", 2023, 80.0, -5);
        });
    }
}