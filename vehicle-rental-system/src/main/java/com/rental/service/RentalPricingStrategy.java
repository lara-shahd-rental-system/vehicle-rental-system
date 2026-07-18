package com.rental.service;

import com.rental.domain.model.Rental;
import com.rental.domain.model.Vehicle;

/**
 * Calculates the base rental cost before any penalties.
 */
public interface RentalPricingStrategy
{
    double calculateCost(Rental rental, Vehicle vehicle);
}