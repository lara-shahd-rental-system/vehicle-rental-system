
package com.rental.domain.model;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class VehicleTest {
	private Vehicle vehicle;
	
	
	@BeforeEach 
	 void setUp () throws Exception {
		vehicle = new Vehicle("1532", "BMW", "EE", 2025);


	}
	
	
	
	
	
	
	
	@Test
	public void testCreateVehicle() {
	 assertEquals("1532", vehicle.getVehicleId());
	 assertEquals("BMW", vehicle.getBrand());
	 assertEquals( "EE", vehicle.getModel());
	 assertEquals(2025, vehicle.getYear());
	}
	
	@Test
	public void testDefaultStatusIsAvailable() {
		
	 assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
	  
	}
	
	@Test
	public void testChangeStatusToRented() {
		
        vehicle.setStatus(VehicleStatus.RENTED);
	 assertEquals(VehicleStatus.RENTED, vehicle.getStatus());
	  
	}
	
	@Test
	public void testChangeStatusBackToAvailable() {
		
        vehicle.setStatus(VehicleStatus.RENTED);
        vehicle.setStatus(VehicleStatus.AVAILABLE) ;
	 assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
	}
	
	@Test
	public void testVehicleId() {
	    assertEquals("1532", vehicle.getVehicleId());
	}
	
	

	@Test
	public void testVehicleBrand() {
		 assertEquals("BMW", vehicle.getBrand());
}
	

		@Test
		public void testVehicleModel() {
			 assertEquals( "EE", vehicle.getModel());}
	

			@Test
			public void testVehicleYear() {
			    assertEquals(2025, vehicle.getYear());
	}
			
			@Test
			public void testVehicleObjectIsNotNull()  {
				assertNotNull(vehicle);}
			
			@Test
			public void testStatusIsNotNullAfterCreation ()  {
				assertNotNull(vehicle.getStatus());			
			}
			
			
				

			@Test
			public void testStatusIsAvailableUsingBoolean  ()  {
				assertTrue(vehicle.getStatus() ==VehicleStatus.AVAILABLE);		}
			

			@Test
			public void testStatusIsNotRentedWhenNew  ()  {
				assertFalse(vehicle.getStatus() ==	VehicleStatus.RENTED);		}
			

			@Test
			public void testYearZero  ()  {
				assertThrows(IllegalArgumentException.class, () -> { new Vehicle("1532", "BMW", "EE", 0); });

					}
			

			@Test
			public void testNegativeYear  ()  {
				assertThrows(IllegalArgumentException.class, () -> { new Vehicle("1532", "BMW", "EE", -5); });

						}

			@Test
			public void testEmptyBrand  ()  {
				assertThrows(IllegalArgumentException.class, () -> { new Vehicle("1532", "", "EE", 2025); });
					}
			
			
			@Test
			public void testSetStatusNull   ()  {
				assertThrows(IllegalArgumentException.class, () -> { vehicle.setStatus(null); });
				}
			
			@Test
			public void testRentAvailableVehicle()  {
				vehicle.rent();
			 assertEquals(VehicleStatus.RENTED, vehicle.getStatus());

				}
			@Test
			public void testRentAlreadyRentedVehicle ()  {
			vehicle.rent();
			assertThrows(IllegalStateException.class, () -> { vehicle.rent(); });
			
				}
			@Test
			public void testReturnRentedVehicle()  {
			 vehicle.rent();
			 vehicle.returnVehicle();
			 assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
				}
			@Test
			public void testReturnAvailableVehicle   ()  {
			 assertThrows(IllegalStateException.class, () -> { vehicle.returnVehicle(); });

				}
			@Test
			public void testIsAvailableWhenNew ()  {
				assertTrue(vehicle.isAvailable());
		 
}
			
			@Test
			public void testIsAvailableAfterReturn ()  {
				vehicle.rent();				
			vehicle.returnVehicle();
	     assertTrue(vehicle.isAvailable());}
			


			
			
			@Test
			public void testToStringContainsId ()  {
			 assertTrue(vehicle.toString().contains("1532"));

				}
			@Test
			public void testToStringContainsBrand   ()  {
			 assertTrue(vehicle.toString().contains("BMW"));

				}
			@Test
			public void testToStringContainsModel   ()  {
			 assertTrue(vehicle.toString().contains("EE"));

				}
			@Test
			public void testToStringContainsYear   ()  {
			 assertTrue(vehicle.toString().contains("2025"));

				}
			
			
			
			
			@Test
			public void testEqualsSameObject ()  {
				 assertEquals(vehicle, vehicle);

				}
			@Test
			public void testEqualsSameId   ()  {

 Vehicle vehicle2 = new Vehicle("1532", "Toyota", "Corolla", 2020);
 assertEquals(vehicle, vehicle2);
				}
			
			
			@Test
			public void testEqualsDifferentId ()  {
				Vehicle vehicle2 = new Vehicle("9999", "BMW", "EE", 2025);				
			     assertNotEquals(vehicle, vehicle2);}



			@Test
			public void testEqualsNull   ()  {
			 assertFalse(vehicle.equals(null));

				}
			@Test
			public void testEqualsDifferentType   ()  {
				assertFalse(vehicle.equals("string"));

				}
		
			
			
			
			
			
			
			
			
			
			
			
			
}

