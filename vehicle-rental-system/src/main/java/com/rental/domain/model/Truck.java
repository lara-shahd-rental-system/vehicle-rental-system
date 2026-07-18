package com.rental.domain.model;
import com.rental.exception.RentalException;




public class Truck extends Vehicle {

	
	 public Truck(String vehicleId, String brand, String model, int year, double dailyRate) {
	        super(vehicleId, brand, model, year, dailyRate);
	    }
	 
	 
	 
	 @Override
	    public void validateEligibility(User user) throws RentalException {
	        if (!"COMMERCIAL".equals(user.getLicenseType())) {
	            throw new RentalException("User must have a commercial license to rent a truck");
	        }
	    } 
	
	
	
	
	
	
}
