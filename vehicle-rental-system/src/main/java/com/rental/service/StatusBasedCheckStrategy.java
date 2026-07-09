package com.rental.service;
 import com.rental.domain.model.Vehicle;
import com.rental.domain.model.VehicleStatus;



public class StatusBasedCheckStrategy implements DoubleBookingCheckStrategy {
	
	 public boolean isBookingAllowed(Vehicle vehicle) {

	return vehicle.getStatus() == VehicleStatus.AVAILABLE;

		
		
		
	}
	
	
	
	
	 }
	

 
	
	


