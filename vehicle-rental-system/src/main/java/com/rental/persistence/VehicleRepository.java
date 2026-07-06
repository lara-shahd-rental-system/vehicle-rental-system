package com.rental.persistence;

import java.util.List;
import java.util.ArrayList;
import com.rental.domain.model.Vehicle;
import com.rental.domain.model.VehicleStatus;

/**
 * Stores and manages all vehicles in the system.
 */

public class VehicleRepository {

	/** The list of all vehicles in the system. */
	private List<Vehicle> vehicles;

	/**
	 * Creates a new empty vehicle repository.
	 */
	public VehicleRepository() {
		this.vehicles = new ArrayList<>();
	}

	/**
	 * Adds a vehicle to the repository.
	 *
	 * @param vehicle the vehicle to add
	 */

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	/**
	 * Returns all vehicles in the repository.
	 *
	 * @return list of all vehicles
	 */

	public List<Vehicle> findAll() {
		return vehicles;
	}

	/**
	 * Returns only vehicles with AVAILABLE status.
	 *
	 * @return list of available vehicles
	 */

	public List<Vehicle> findAvailable() {
		List<Vehicle> available = new ArrayList<>();
		for (Vehicle v : vehicles) {
			if (v.getStatus() == VehicleStatus.AVAILABLE) {
				available.add(v);
			}

		}
		return available;
	}

	/**
	 * Finds a vehicle by its ID.
	 *
	 * @param vehicleId the ID to search for
	 * @return the vehicle if found, or null if not found
	 */
	public Vehicle findById(String vehicleId) {
		for (Vehicle v : vehicles) {
			if (v.getVehicleId().equals(vehicleId)) {
				return v;
			}

		}
		return null;

	}
}
