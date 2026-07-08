package com.rental.service;

import java.util.List;
import com.rental.domain.model.Vehicle;
import com.rental.exception.UnauthorizedAccessException;
import com.rental.persistence.VehicleRepository;

public class VehicleService {

	private VehicleRepository vehicleRepository;
	private AuthenticationService authService;

	public VehicleService(VehicleRepository vehicleRepository, AuthenticationService authService) {
		this.vehicleRepository = vehicleRepository;
		this.authService = authService;

	}

	public List<Vehicle> getAvailableVehicles() {
		if (!authService.isLoggedIn()) {
		    throw new UnauthorizedAccessException("User must be logged in to view vehicles");

		}
		return vehicleRepository.findAvailable();

	}

}
