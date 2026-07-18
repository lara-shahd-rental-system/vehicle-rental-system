package com.rental.service;
import java.time.temporal.ChronoUnit;
import com.rental.domain.model.Rental;
import com.rental.domain.model.Vehicle;






public class StandardPricingStrategy implements RentalPricingStrategy {

    @Override
    public double calculateCost(Rental rental, Vehicle vehicle) {
        long durationDays = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        return durationDays * vehicle.getDailyRate();
    }
}