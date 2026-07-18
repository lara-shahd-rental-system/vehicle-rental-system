package com.rental.domain.model;
import com.rental.exception.RentalException;




public class Motorcycle extends Vehicle {

    private static final int MIN_AGE = 18;

	
    public Motorcycle(String vehicleId, String brand, String model, int year, double dailyRate) {
        super(vehicleId, brand, model, year, dailyRate);
    }
	
	
    @Override
    public void validateEligibility(User user) throws RentalException {
        if (user.getAge() < MIN_AGE) {
            throw new RentalException("User must be at least 18 years old to rent a motorcycle");
        }
    }
	
	
	
}
