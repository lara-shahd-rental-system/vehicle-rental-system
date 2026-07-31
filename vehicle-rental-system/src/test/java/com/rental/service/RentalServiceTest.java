package com.rental.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.rental.domain.model.Vehicle;
import com.rental.domain.model.Rental;
import com.rental.exception.RentalException;
import com.rental.persistence.RentalRepository;
import com.rental.persistence.VehicleRepository;
import com.rental.domain.model.RentalStatus;

public class RentalServicepublic class RentalServiceTest  {
    private RentalRepository rentalRepository;
    private VehicleRepository vehicleRepository;
    private VehicleService vehicleService;
    private AuthenticationService authenticationService;
    private DoubleBookingCheckStrategy doubleBookingCheckStrategy;
    private DateProvider dateProvider;
    private RentalPricingStrategy pricingStrategy;
    private LatePenaltyPolicy latePenaltyPolicy;

    private static final String MSG_NOT_LOGGED_IN = "User must be logged in to rent a vehicle";
    private static final String MSG_VEHICLE_NOT_FOUND = "Vehicle not found";
    private static final String MSG_VEHICLE_NOT_AVAILABLE = "Vehicle is not available";
    private static final String MSG_END_DATE_INVALID = "End date must be after start date";
    private static final String MSG_START_DATE_PAST = "Start date cannot be in the past";
    private static final String MSG_DURATION_INVALID = "Rental duration must be between 1 and 30 days";
    private static final String MSG_RENTAL_NOT_FOUND = "Rental not found";

    // Constructor is now private - objects must be created via Builder
    private RentalService(Builder builder) {
        this.rentalRepository = builder.rentalRepository;
        this.vehicleRepository = builder.vehicleRepository;
        this.vehicleService = builder.vehicleService;
        this.authenticationService = builder.authenticationService;
        this.doubleBookingCheckStrategy = builder.doubleBookingCheckStrategy;
        this.dateProvider = builder.dateProvider;
        this.pricingStrategy = builder.pricingStrategy;
        this.latePenaltyPolicy = builder.latePenaltyPolicy;
    }

    public Rental rentVehicle(String username, String vehicleId, LocalDate startDate, LocalDate endDate) throws RentalException {

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

    public double returnVehicle(String rentalId, LocalDate actualReturnDate) throws RentalException {
        Rental rental = rentalRepository.findById(rentalId);
        if (rental == null) {
            throw new RentalException(MSG_RENTAL_NOT_FOUND);
        }
        Vehicle vehicle = vehicleRepository.findById(rental.getVehicleId());
        double cost = pricingStrategy.calculateCost(rental, vehicle);
        double penalty = latePenaltyPolicy.calculatePenalty(rental, actualReturnDate);
        rental.setStatus(RentalStatus.COMPLETED);
        vehicleService.markAsAvailable(rental.getVehicleId());
        return cost + penalty;
    }

    // Builder class - handles step-by-step construction of RentalService
    public static class Builder {
        private RentalRepository rentalRepository;
        private VehicleRepository vehicleRepository;
        private VehicleService vehicleService;
        private AuthenticationService authenticationService;
        private DoubleBookingCheckStrategy doubleBookingCheckStrategy;
        private DateProvider dateProvider;
        private RentalPricingStrategy pricingStrategy;
        private LatePenaltyPolicy latePenaltyPolicy;

        public Builder rentalRepository(RentalRepository rentalRepository) {
            this.rentalRepository = rentalRepository;
            return this;
        }

        public Builder vehicleRepository(VehicleRepository vehicleRepository) {
            this.vehicleRepository = vehicleRepository;
            return this;
        }

        public Builder vehicleService(VehicleService vehicleService) {
            this.vehicleService = vehicleService;
            return this;
        }

        public Builder authenticationService(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
            return this;
        }

        public Builder doubleBookingCheckStrategy(DoubleBookingCheckStrategy doubleBookingCheckStrategy) {
            this.doubleBookingCheckStrategy = doubleBookingCheckStrategy;
            return this;
        }

        public Builder dateProvider(DateProvider dateProvider) {
            this.dateProvider = dateProvider;
            return this;
        }

        public Builder pricingStrategy(RentalPricingStrategy pricingStrategy) {
            this.pricingStrategy = pricingStrategy;
            return this;
        }

        public Builder latePenaltyPolicy(LatePenaltyPolicy latePenaltyPolicy) {
            this.latePenaltyPolicy = latePenaltyPolicy;
            return this;
        }

        public RentalService build() {
            return new RentalService(this);
        }
    }
}