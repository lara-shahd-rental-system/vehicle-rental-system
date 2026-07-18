package com.rental.service;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.rental.domain.model.Car;
import com.rental.domain.model.Rental;
import com.rental.domain.model.Vehicle;

class StandardPricingStrategyTest {

    @Test
    void testCalculateCostFiveDays() {
        StandardPricingStrategy strategy = new StandardPricingStrategy();
        Vehicle vehicle = new Car("V001", "BMW", "X5", 2020, 50.0);
        Rental rental = new Rental("V001", "admin", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 6));

        double cost = strategy.calculateCost(rental, vehicle);

        assertEquals(250.0, cost);
    }

    @Test
    void testCalculateCostOneDay() {
        StandardPricingStrategy strategy = new StandardPricingStrategy();
        Vehicle vehicle = new Car("V001", "BMW", "X5", 2020, 100.0);
        Rental rental = new Rental("V001", "admin", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 2));

        double cost = strategy.calculateCost(rental, vehicle);

        assertEquals(100.0, cost);
    }
}