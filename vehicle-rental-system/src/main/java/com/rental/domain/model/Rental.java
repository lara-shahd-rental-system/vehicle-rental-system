package com.rental.domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Represents a vehicle rental transaction.
 */
public class Rental
{
    private static final int MIN_DURATION_DAYS = 1;
    private static final int MAX_DURATION_DAYS = 30;

    private String rentalId;
    private String vehicleId;
    private String username;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalStatus status;

    /**
     * Constructs a new Rental with ACTIVE status and an auto-generated ID.
     *
     * @param vehicleId the ID of the rented vehicle
     * @param username the username of the renter
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @throws IllegalArgumentException if any field is invalid
     */
    public Rental(String vehicleId, String username, LocalDate startDate, LocalDate endDate)
    {
        if (vehicleId == null || vehicleId.trim().isEmpty())
        {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
        if (username == null || username.trim().isEmpty())
        {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (startDate == null || endDate == null)
        {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        if (!endDate.isAfter(startDate))
        {
            throw new IllegalArgumentException("End date must be after start date");
        }

        long durationDays = ChronoUnit.DAYS.between(startDate, endDate);
        if (durationDays < MIN_DURATION_DAYS || durationDays > MAX_DURATION_DAYS)
        {
            throw new IllegalArgumentException("Rental duration must be between 1 and 30 days");
        }

        this.rentalId = UUID.randomUUID().toString();
        this.vehicleId = vehicleId;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = RentalStatus.ACTIVE;
    }

    public String getRentalId()
    {
        return rentalId;
    }

    public String getVehicleId()
    {
        return vehicleId;
    }

    public String getUsername()
    {
        return username;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public RentalStatus getStatus()
    {
        return status;
    }

    /**
     * Updates the rental status (e.g., to COMPLETED after a return).
     *
     * @param status the new status
     */
    public void setStatus(RentalStatus status)
    {
        this.status = status;
    }
}