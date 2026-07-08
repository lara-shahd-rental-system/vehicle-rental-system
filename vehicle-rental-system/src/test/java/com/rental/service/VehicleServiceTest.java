package com.rental.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rental.domain.model.Vehicle;
import com.rental.domain.model.VehicleStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import com.rental.exception.UnauthorizedAccessException;
import com.rental.domain.model.User;
import com.rental.persistence.UserRepository;
import com.rental.persistence.VehicleRepository;

class VehicleServiceTest {

	private VehicleService vehicleService;
	private VehicleRepository vehicleRepository;
	private AuthenticationService authService;
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		userRepository = new UserRepository();
		userRepository.addUser(new User("admin", "admin123", "MANAGER"));
		authService = new AuthenticationService(userRepository);
		vehicleRepository = new VehicleRepository();
		vehicleService = new VehicleService(vehicleRepository, authService);

	}

	@Test
	void testGetAvailableVehiclesWhenLoggedIn()  {
		authService.login("admin", "admin123");
		vehicleRepository.addVehicle(new Vehicle("V001", "BMW", "X5", 2020));
		vehicleRepository.addVehicle(new Vehicle("V002", "Toyota", "Camry", 2022));
		List<Vehicle> result = vehicleService.getAvailableVehicles();
		assertEquals(2, result.size());
	}

	@Test
	void testGetAvailableVehiclesWhenNotLoggedIn() {
		 assertThrows(UnauthorizedAccessException.class, () -> { vehicleService.getAvailableVehicles(); }); 

	}

		
	
	@Test
	void testGetAvailableVehiclesWhenAllRented()  {

		authService.login("admin", "admin123");
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		Vehicle v2 = new Vehicle("V002", "Toyota", "Camry", 2022);
		vehicleRepository.addVehicle(v1);
		vehicleRepository.addVehicle(v2);
		v1.setStatus(VehicleStatus.RENTED);
		v2.setStatus(VehicleStatus.RENTED);
		assertEquals(0, vehicleService.getAvailableVehicles().size());

	}

	@Test
	void testGetAvailableVehiclesWhenNoneExist() {

		authService.login("admin", "admin123");
		assertEquals(0, vehicleService.getAvailableVehicles().size());

	}

	@Test
	void testGetAvailableVehiclesWithMixedStatuses() {
		authService.login("admin", "admin123");
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		Vehicle v2 = new Vehicle("V002", "Toyota", "Camry", 2022);
		Vehicle v3 = new Vehicle("V003", "Toyota", "Camry", 2020);
		vehicleRepository.addVehicle(v1);
		vehicleRepository.addVehicle(v2);
		vehicleRepository.addVehicle(v3);
		v1.setStatus(VehicleStatus.RENTED);

		assertEquals(2, vehicleService.getAvailableVehicles().size());

	}

	@Test
	void testGetAvailableVehiclesAfterLogout() {
		authService.login("admin", "admin123");
		authService.logout();
		assertThrows(UnauthorizedAccessException.class, () -> {
			vehicleService.getAvailableVehicles();
		});

	}
	
	@Test
	void testMarkAsRentedSuccess() {
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		vehicleRepository.addVehicle(v1);
		 vehicleService.markAsRented("V001");
		 assertEquals(VehicleStatus.RENTED, vehicleRepository.findById("V001").getStatus());
	}
	
	@Test
	void testMarkAsRentedVehicleNotFound() {
		assertThrows(IllegalArgumentException.class, () -> { vehicleService.markAsRented("XXX"); });


		
		
	}
	@Test
	void testMarkAsRentedAlreadyRented() {	
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		vehicleRepository.addVehicle(v1);
	 vehicleService.markAsRented("V001");
	 vehicleService.markAsRented("V001");
	 assertEquals(VehicleStatus.RENTED, vehicleRepository.findById("V001").getStatus());

	}
		
	@Test
	void testMarkAsAvailableSuccess() {
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		vehicleRepository.addVehicle(v1);
		 vehicleService.markAsRented("V001");
		 vehicleService.markAsAvailable("V001");
		 assertEquals(VehicleStatus.AVAILABLE, vehicleRepository.findById("V001").getStatus());

		
		
	}
	@Test
	void testMarkAsAvailableVehicleNotFound() {
		assertThrows(IllegalArgumentException.class, () -> { vehicleService.markAsAvailable("XXX"); });


		
	}
	@Test
	void testMarkAsAvailableAlreadyAvailable() {
		Vehicle v1 = new Vehicle("V001", "BMW", "X5", 2020);
		vehicleRepository.addVehicle(v1);
		vehicleService.markAsAvailable("V001");
		assertEquals(VehicleStatus.AVAILABLE, vehicleRepository.findById("V001").getStatus());
	}
	
	}



