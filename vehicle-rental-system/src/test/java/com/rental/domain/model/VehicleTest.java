
package com.rental.domain.model;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class VehicleTest {
	@Test
	public void testCreateVehicle() {
	Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
	 assertEquals("1532", vehicle.getVehicleId());
	 assertEquals("BMW", vehicle.getBrand());
	 assertEquals( "EE", vehicle.getModel());
	 assertEquals(2025, vehicle.getYear());
	}
	
	@Test
	public void testDefaultStatusIsAvailable() {
		
		Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
	 assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
	  
	}
	
	@Test
	public void testChangeStatusToRented() {
		
		Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
        vehicle.setStatus(VehicleStatus.RENTED);
	 assertEquals(VehicleStatus.RENTED, vehicle.getStatus());
	  
	}
	
	@Test
	public void testChangeStatusBackToAvailable() {
		
		Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
        vehicle.setStatus(VehicleStatus.RENTED);
        vehicle.setStatus(VehicleStatus.AVAILABLE) ;
	 assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
	}
	
	@Test
	public void testVehicleId() {
		Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
	    assertEquals("1532", vehicle.getVehicleId());
	}
	
	

	@Test
	public void testVehicleBrand() {
		Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
		 assertEquals("BMW", vehicle.getBrand());
}
	

		@Test
		public void testVehicleModel() {
			Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
			 assertEquals( "EE", vehicle.getModel());}
	

			@Test
			public void testVehicleYear() {
				Vehicle vehicle = new Vehicle("1532", "BMW", "EE", 2025);
			    assertEquals(2025, vehicle.getYear());
	}
}

