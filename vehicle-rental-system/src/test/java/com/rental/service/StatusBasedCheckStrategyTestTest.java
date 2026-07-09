package com.rental.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rental.domain.model.Vehicle;
import com.rental.domain.model.VehicleStatus;

class StatusBasedCheckStrategyTest {
	
	
	
	
	private StatusBasedCheckStrategy strategy;
 
	@BeforeEach
	public void setUp() {
	strategy = new StatusBasedCheckStrategy();

	}

	@Test
	void testIsBookingAllowedWhenAvailable() {
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		
	assertTrue(strategy.isBookingAllowed(v1));}


	
	

	@Test
	void testIsBookingNotAllowedWhenRented() {
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		v1.setStatus(VehicleStatus.RENTED );
	assertFalse(strategy.isBookingAllowed(v1));
	}


	
	
	
	
	
	
	
	
	

	@Test
	void testIsBookingAllowedAfterReturn() {
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		v1.setStatus(VehicleStatus.RENTED );	
		v1.setStatus(VehicleStatus.AVAILABLE );	
		assertTrue(strategy.isBookingAllowed(v1));



	
	}
	
	
	
	
	
}
