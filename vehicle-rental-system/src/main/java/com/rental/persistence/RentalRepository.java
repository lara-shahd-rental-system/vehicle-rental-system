package com.rental.persistence;

import java.util.List;
import com.rental.domain.model.Rental;

/**
 * Stores and retrieves Rental records.
 */
public interface RentalRepository
{
    void addRental(Rental rental);

    List<Rental> findAll();

    List<Rental> findByUsername(String username);

    List<Rental> findByVehicleId(String vehicleId);

    boolean hasActiveRentalForVehicle(String vehicleId);
    Rental findById(String rentalId);
}
