
package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Vehicle class.
 */

public class VehicleTest {
	private Vehicle vehicle;

	/**
	 * Creates a new vehicle before each test.
	 */

	@BeforeEach
	void setUp() throws Exception {
		vehicle = new Vehicle("1532", "BMW", "EE", 2025);

	}

	/** Tests that all getters return correct values. */

	@Test
	public void testCreateVehicle() {
		assertEquals("1532", vehicle.getVehicleId());
		assertEquals("BMW", vehicle.getBrand());
		assertEquals("EE", vehicle.getModel());
		assertEquals(2025, vehicle.getYear());
	}

	/** Tests that new vehicle status is AVAILABLE. */

	@Test
	public void testDefaultStatusIsAvailable() {

		assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());

	}

	/** Tests changing status to RENTED. */

	@Test
	public void testChangeStatusToRented() {

		vehicle.setStatus(VehicleStatus.RENTED);
		assertEquals(VehicleStatus.RENTED, vehicle.getStatus());

	}

	/** Tests changing status back to AVAILABLE. */

	@Test
	public void testChangeStatusBackToAvailable() {

		vehicle.setStatus(VehicleStatus.RENTED);
		vehicle.setStatus(VehicleStatus.AVAILABLE);
		assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
	}

	/** Tests getVehicleId returns correct value. */

	@Test
	public void testVehicleId() {
		assertEquals("1532", vehicle.getVehicleId());
	}

	/** Tests getBrand returns correct value. */

	@Test
	public void testVehicleBrand() {
		assertEquals("BMW", vehicle.getBrand());
	}

	/** Tests getModel returns correct value. */

	@Test
	public void testVehicleModel() {
		assertEquals("EE", vehicle.getModel());
	}

	/** Tests getYear returns correct value. */

	@Test
	public void testVehicleYear() {
		assertEquals(2025, vehicle.getYear());
	}

	/** Tests that vehicle object is not null. */

	@Test
	public void testVehicleObjectIsNotNull() {
		assertNotNull(vehicle);
	}

	/** Tests that status is not null after creation. */

	@Test
	public void testStatusIsNotNullAfterCreation() {
		assertNotNull(vehicle.getStatus());
	}

	/** Tests status is AVAILABLE using assertTrue. */

	@Test
	public void testStatusIsAvailableUsingBoolean() {
		assertTrue(vehicle.getStatus() == VehicleStatus.AVAILABLE);
	}

	/** Tests status is not RENTED when new. */

	@Test
	public void testStatusIsNotRentedWhenNew() {
		assertFalse(vehicle.getStatus() == VehicleStatus.RENTED);
	}

	/** Tests that year zero throws exception. */

	@Test
	public void testYearZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Vehicle("1532", "BMW", "EE", 0);
		});

	}

	/** Tests that negative year throws exception. */

	@Test
	public void testNegativeYear() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Vehicle("1532", "BMW", "EE", -5);
		});

	}

	/** Tests that empty brand throws exception. */

	@Test
	public void testEmptyBrand() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Vehicle("1532", "", "EE", 2025);
		});
	}

	/** Tests that null status throws exception. */

	@Test
	public void testSetStatusNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			vehicle.setStatus(null);
		}); 
	}

	/** Tests renting an available vehicle. */

	@Test
	public void testRentAvailableVehicle() {
		vehicle.rent();
		assertEquals(VehicleStatus.RENTED, vehicle.getStatus());

	}

	/** Tests renting an already rented vehicle throws exception. */

	@Test
	public void testRentAlreadyRentedVehicle() {
		vehicle.rent();
		assertThrows(IllegalStateException.class, () -> {
			vehicle.rent();
		});

	}

	/** Tests returning a rented vehicle. */

	@Test
	public void testReturnRentedVehicle() {
		vehicle.rent();
		vehicle.returnVehicle();
		assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
	}

	/** Tests returning an available vehicle throws exception. */

	@Test
	public void testReturnAvailableVehicle() {
		assertThrows(IllegalStateException.class, () -> {
			vehicle.returnVehicle();
		});

	}

	/** Tests isAvailable returns true for new vehicle. */

	@Test
	public void testIsAvailableWhenNew() {
		assertTrue(vehicle.isAvailable());

	}

	/** Tests isAvailable returns true after return. */

	@Test
	public void testIsAvailableAfterReturn() {
		vehicle.rent();
		vehicle.returnVehicle();
		assertTrue(vehicle.isAvailable());
	}

	/** Tests toString contains vehicle ID. */

	@Test
	public void testToStringContainsId() {
		assertTrue(vehicle.toString().contains("1532"));

	}

	/** Tests toString contains brand. */

	@Test
	public void testToStringContainsBrand() {
		assertTrue(vehicle.toString().contains("BMW"));

	}

	/** Tests toString contains model. */

	@Test
	public void testToStringContainsModel() {
		assertTrue(vehicle.toString().contains("EE"));

	}

	/** Tests toString contains year. */

	@Test
	public void testToStringContainsYear() {
		assertTrue(vehicle.toString().contains("2025"));

	}

	/** Tests equals with same object. */

	@Test
	public void testEqualsSameObject() {
		assertEquals(vehicle, vehicle);

	}

	/** Tests equals with same ID different details. */

	@Test
	public void testEqualsSameId() {

		Vehicle vehicle2 = new Vehicle("1532", "Toyota", "Corolla", 2020);
		assertEquals(vehicle, vehicle2);
	}

	/** Tests equals with different ID. */

	@Test
	public void testEqualsDifferentId() {
		Vehicle vehicle2 = new Vehicle("9999", "BMW", "EE", 2025);
		assertNotEquals(vehicle, vehicle2);
	}

	/** Tests equals with null. */

	@Test
	public void testEqualsNull() {
		assertFalse(vehicle.equals(null));

	}

	/** Tests equals with different type. */

	@Test
	public void testEqualsDifferentType() {
		assertFalse(vehicle.equals("string"));

	}

}
