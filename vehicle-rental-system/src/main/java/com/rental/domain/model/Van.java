package com.rental.domain.model;

public class Van extends Vehicle {

	
	
	
	 public Van(String vehicleId, String brand, String model, int year, double dailyRate) {
	        super(vehicleId, brand, model, year, dailyRate);
	    }
	 
	 
	 
	 
	 
	   @Override
	    public void validateEligibility(User user) {
	    }
	
	
	
}
