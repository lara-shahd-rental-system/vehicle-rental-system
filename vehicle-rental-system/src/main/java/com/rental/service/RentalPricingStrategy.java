package com.rental.service;
import com.rental.domain.model.Rental;
import com.rental.domain.model.Vehicle;


public interface  RentalPricingStrategy {

	
    double calculateCost(Rental rental, Vehicle vehicle);

}
