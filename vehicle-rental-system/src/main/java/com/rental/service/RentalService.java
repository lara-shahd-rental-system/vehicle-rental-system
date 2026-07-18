package com.rental.service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.rental.domain.model.Vehicle;
import com.rental.domain.model.Rental;
import com.rental.exception.RentalException;
import com.rental.persistence.RentalRepository;
import com.rental.persistence.VehicleRepository;


public class RentalService {

	private RentalRepository rentalRepository;
	private VehicleRepository vehicleRepository;
	private VehicleService vehicleService;
	private AuthenticationService authenticationService;
	private DoubleBookingCheckStrategy doubleBookingCheckStrategy;
	private DateProvider dateProvider;	
	
	private static final String MSG_NOT_LOGGED_IN = "User must be logged in to rent a vehicle";
	private static final String MSG_VEHICLE_NOT_FOUND  =  "Vehicle not found";
	private static final String MSG_VEHICLE_NOT_AVAILABLE  = "Vehicle is not available";
	private static final String MSG_END_DATE_INVALID  = "End date must be after start date" ;
	private static final String MSG_START_DATE_PAST  = "Start date cannot be in the past" ;
	private static final String MSG_DURATION_INVALID  ="Rental duration must be between 1 and 30 days" ;

	
	public RentalService(RentalRepository rentalRepository, VehicleRepository vehicleRepository, 
            VehicleService vehicleService, AuthenticationService authenticationService,
            DoubleBookingCheckStrategy doubleBookingCheckStrategy, DateProvider dateProvider) {
		this.rentalRepository = rentalRepository;
		this.vehicleRepository = vehicleRepository;
		this.vehicleService = vehicleService;
		this.authenticationService = authenticationService;
		this.doubleBookingCheckStrategy = doubleBookingCheckStrategy;
		this.dateProvider = dateProvider;
	}
	public Rental rentVehicle(String username, String vehicleId, LocalDate startDate, LocalDate endDate) throws RentalException{
		
		if (!authenticationService.isLoggedIn()) {
		    throw new RentalException(MSG_NOT_LOGGED_IN);
		}
		
		Vehicle vehicle = vehicleRepository.findById(vehicleId);
		if (vehicle == null) {
		    throw new RentalException(MSG_VEHICLE_NOT_FOUND);
		}
		if (!doubleBookingCheckStrategy.isBookingAllowed(vehicle)) {
		    throw new RentalException(MSG_VEHICLE_NOT_AVAILABLE);
		}
		
		if (!endDate.isAfter(startDate)) {
		    throw new IllegalArgumentException(MSG_END_DATE_INVALID);
		}
		if (startDate.isBefore(dateProvider.getToday())) {
		    throw new IllegalArgumentException(MSG_START_DATE_PAST);
		}
		
		long durationDays = ChronoUnit.DAYS.between(startDate, endDate);
		if (durationDays < 1 || durationDays > 30) {
		    throw new RentalException(MSG_DURATION_INVALID);
		}
		Rental rental = new Rental(vehicleId, username, startDate, endDate);
		rentalRepository.addRental(rental);
		vehicleService.markAsRented(vehicleId);
		return rental;
	}


}
