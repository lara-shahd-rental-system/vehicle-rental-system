package com.rental.persistence;

import java.util.List;
import java.util.ArrayList;
import com.rental.domain.model.Vehicle;
import com.rental.domain.model.VehicleStatus;

public class VehicleRepository {
	private List<Vehicle> vehicles;

	public VehicleRepository() {
		this.vehicles = new ArrayList<>();
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public List<Vehicle> findAll() {
		return vehicles;
	}

	public List<Vehicle> findAvailable() {
		List<Vehicle> available = new ArrayList<>();
		for (Vehicle v : vehicles) {
			if (v.getStatus() == VehicleStatus.AVAILABLE) {
				available.add(v);
			}

		}
		return available;
	}

	public Vehicle findById(String vehicleId) {
		for (Vehicle v : vehicles) {
			if (v.getVehicleId().equals(vehicleId)) {
				return v;
			}

		}
		return null;

	}
}
