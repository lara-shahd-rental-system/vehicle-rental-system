package com.rental.domain.model;

/**
 * Represents a vehicle in the rental system.
 */
public class Vehicle {

	/** The unique identifier for this vehicle. */
	private String vehicleId;

	/** The brand of the vehicle. */
	private String brand;

	/** The model of the vehicle. */
	private String model;

	/** The manufacturing year of the vehicle. */
	private int year;

	/** The current rental status of the vehicle. */
	private VehicleStatus status;

	private double dailyRate;
	
	/**
	 * Creates a new Vehicle with the given details and sets status to AVAILABLE.
	 *
	 * @param vehicleId unique identifier for the vehicle
	 * @param brand     the vehicle brand
	 * @param model     the vehicle model
	 * @param year      the manufacturing year
	 * @throws IllegalArgumentException if any parameter is invalid
	 */

	public Vehicle(String vehicleId, String brand, String model, int year, double dailyRate)
		if (vehicleId == null || vehicleId.isEmpty()) {
			throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
		}

		if (brand == null || brand.isEmpty()) {
			throw new IllegalArgumentException("brand  cannot be null or empty");
		}
		if (model == null || model.isEmpty()) {
			throw new IllegalArgumentException("Model cannot be null or empty");
		}
		if (year < 1886) {
			throw new IllegalArgumentException("Invalid year");
		}
		if (dailyRate <= 0) {
		    throw new IllegalArgumentException("Daily rate must be positive");
		}
		
		this.vehicleId = vehicleId;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.dailyRate = dailyRate;

		
		status = VehicleStatus.AVAILABLE;
	}

	/**
	 * Returns the vehicle ID.
	 *
	 * @return the vehicle ID
	 */

	public String getVehicleId() {

		return vehicleId;
	}

	/**
	 * Returns the brand.
	 *
	 * @return the brand
	 */

	public String getBrand() {

		return brand;

	}

	/**
	 * Returns the model.
	 *
	 * @return the model
	 */

	public String getModel() {

		return model;

	}

	/**
	 * Returns the manufacturing year.
	 *
	 * @return the manufacturing year
	 */

	public int getYear() {

		return year;

	}

	
	
	/**
	 * Returns the current rental status.
	 *
	 * @return the rental status
	 */

	public VehicleStatus getStatus() {

		return status;

	}

	/**
	 * Sets the rental status of this vehicle.
	 *
	 * @param status the new status to set
	 * @throws IllegalArgumentException if status is null
	 */

	public void setStatus(VehicleStatus status) {

		if (status == null) {
			throw new IllegalArgumentException("Status cannot be null");

		}
		this.status = status;

	}

	/**
	 * Returns a string representation of the vehicle.
	 *
	 * @return string with vehicle details
	 */

	@Override
	public String toString() {

		return "Vehicle{id='" + vehicleId + "', brand='" + brand + "', model='" + model + "', year=" + year
				+ ", status=" + status + "}";

	}

	/**
	 * Checks if the vehicle is available for rent.
	 *
	 * @return true if the vehicle status is AVAILABLE
	 */

	public boolean isAvailable() {

		return status == VehicleStatus.AVAILABLE;
	}

	/**
	 * Rents the vehicle by changing status to RENTED.
	 *
	 * @throws IllegalStateException if vehicle is not available
	 */

	public void rent() {
		if (status != (VehicleStatus.AVAILABLE)) {

			throw new IllegalStateException("Vehicle is not available");

		}
		status = VehicleStatus.RENTED;

	}

	/**
	 * Returns the vehicle by changing status to AVAILABLE.
	 *
	 * @throws IllegalStateException if vehicle is not rented
	 */

	public void returnVehicle() {

		if (status != (VehicleStatus.RENTED)) {

			throw new IllegalStateException("Vehicle is not rented");

		}
		status = VehicleStatus.AVAILABLE;

	} 

	/**
	 * Checks equality based on vehicle ID.
	 *
	 * @param x the object to compare with
	 * @return true if the other object is a Vehicle with the same ID
	 */

	@Override

	public boolean equals(Object x) {

		if (this == x)
			return true;
		if (x == null)
			return false;
		if (!(x instanceof Vehicle))
			return false;
		Vehicle other = (Vehicle) x;
		return this.vehicleId.equals(other.vehicleId);

	}

}
