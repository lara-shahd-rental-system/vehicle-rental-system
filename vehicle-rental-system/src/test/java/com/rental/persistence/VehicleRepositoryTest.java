package com.rental.persistence;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rental.domain.model.Vehicle;
import com.rental.domain.model.VehicleStatus;
import java.util.List;



class VehicleRepositoryTest {

	private VehicleRepository repository;
	private Vehicle vehicle1;
	private Vehicle vehicle2;
	private Vehicle vehicle3;


	
	
	@BeforeEach
	void setUp() {
	    repository = new VehicleRepository();
	    vehicle1 = new Vehicle("V001", "BMW", "X5", 2020);
	    vehicle2 = new Vehicle("V002", "Toyota", "Camry", 2022);
	    vehicle3 = new Vehicle("V003", "Honda", "Civic", 2021);
	}
	
	
	@Test
	public void testAddOneVehicle() {
	
	repository.addVehicle(vehicle1);
		 assertEquals(1, repository.findAll().size());
	}
	
	@Test
	public void testAddMultipleVehicles() {
		repository.addVehicle(vehicle1);
		repository.addVehicle(vehicle2);
		repository.addVehicle(vehicle3);
	 assertEquals(3, repository.findAll().size());


		
		
	}
	
	@Test
	public void testAddDuplicateVehicle() {


 repository.addVehicle(vehicle1);
repository.addVehicle(vehicle1);
 assertEquals(2, repository.findAll().size());
	}
	
	@Test
	public void testFindAllWithVehicles() {
		repository.addVehicle(vehicle1);
		repository.addVehicle(vehicle2);
		assertEquals(2, repository.findAll().size());

	}


	
	@Test
	public void testFindAllEmpty() {
	 assertNotNull(repository.findAll());
	 assertEquals(0, repository.findAll().size());
	}
	@Test
	public void testFindAvailableAllAvailable() {
		
		repository.addVehicle(vehicle1);
		repository.addVehicle(vehicle2);
		repository.addVehicle(vehicle3);
		assertEquals(3, repository.findAvailable().size());

		
	}


	
	@Test
	public void testFindAvailableMixed() {
		repository.addVehicle(vehicle1);
		repository.addVehicle(vehicle2);
		repository.addVehicle(vehicle3);
        vehicle1.setStatus(VehicleStatus.RENTED);
		assertEquals(2, repository.findAvailable().size());
		
	}
	
	
	@Test
	
	public void testFindAvailableAllRented() {
		repository.addVehicle(vehicle1);
		repository.addVehicle(vehicle2);
		repository.addVehicle(vehicle3);
		vehicle1.setStatus(VehicleStatus.RENTED);
		vehicle2.setStatus(VehicleStatus.RENTED);
		vehicle3.setStatus(VehicleStatus.RENTED);
		
		assertEquals(0, repository.findAvailable().size());

	}
	@Test
	public void testFindAvailableEmpty() {
		assertEquals(0, repository.findAvailable().size());


	}
	@Test
	public void testFindAvailableOneAvailableOneRented() {
		repository.addVehicle(vehicle1);
		repository.addVehicle(vehicle2);
		vehicle1.setStatus(VehicleStatus.RENTED);
		List<Vehicle> available = repository.findAvailable();
	 assertEquals(1, available.size());
		 assertEquals("V002", available.get(0).getVehicleId());
	}
	@Test
	public void testFindByIdExists() {
		repository.addVehicle(vehicle1);

 Vehicle found = repository.findById("V001");
 assertNotNull(found);
assertEquals("V001", found.getVehicleId());

	}@Test
	public void testFindByIdCorrectVehicle() {
		repository.addVehicle(vehicle1);
		repository.addVehicle(vehicle2);
		repository.addVehicle(vehicle3);
		
		Vehicle found = repository.findById("V002");
	 assertEquals("Toyota", found.getBrand());
	}@Test
	public void testFindByIdNotFound() {
		repository.addVehicle(vehicle1);
		assertNull(repository.findById("XXX"));

	}
		@Test
		public void testFindAvailableDoesNotModifyOriginalList() {
			
			
			
			repository.addVehicle(vehicle1);
			repository.addVehicle(vehicle2);
			repository.addVehicle(vehicle3);

 repository.findAvailable();
 assertEquals(3, repository.findAll().size());
		}
			
		
		@Test
		public void testFindByIdEmptyRepository () {
			
			assertNull(repository.findById("V001"));

		}
		@Test
		public void testAddVehicleThenFindById() {
			repository.addVehicle(vehicle1);

			assertEquals(vehicle1, repository.findById("V001"));




		}
		
		@Test
		public void testFindAvailableAfterStatusChange() {
			
			repository.addVehicle(vehicle1);

			assertEquals(1, repository.findAvailable().size());
			vehicle1.setStatus(VehicleStatus.RENTED);
			assertEquals(0, repository.findAvailable().size());

		}
		
}