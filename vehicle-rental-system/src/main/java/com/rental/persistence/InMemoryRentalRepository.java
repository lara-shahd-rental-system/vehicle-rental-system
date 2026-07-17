package com.rental.persistence;

import java.util.ArrayList;
import java.util.List;
import com.rental.domain.model.Rental;
import com.rental.domain.model.RentalStatus;

/**
 * In-memory implementation of RentalRepository using a List.
 */
public class InMemoryRentalRepository implements RentalRepository
{
    private List<Rental> rentals;

    public InMemoryRentalRepository()
    {
        this.rentals = new ArrayList<>();
    }

    @Override
    public void addRental(Rental rental)
    {
        if (rental == null)
        {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        rentals.add(rental);
    }

    @Override
    public List<Rental> findAll()
    {
        return rentals;
    }

    @Override
    public List<Rental> findByUsername(String username)
    {
        if (username == null || username.trim().isEmpty())
        {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        List<Rental> result = new ArrayList<>();
        for (Rental rental : rentals)
        {
            if (rental.getUsername().equals(username))
            {
                result.add(rental);
            }
        }
        return result;
    }

    @Override
    public List<Rental> findByVehicleId(String vehicleId)
    {
        if (vehicleId == null || vehicleId.trim().isEmpty())
        {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }

        List<Rental> result = new ArrayList<>();
        for (Rental rental : rentals)
        {
            if (rental.getVehicleId().equals(vehicleId))
            {
                result.add(rental);
            }
        }
        return result;
    }

    @Override
    public boolean hasActiveRentalForVehicle(String vehicleId)
    {
        if (vehicleId == null || vehicleId.trim().isEmpty())
        {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }

        for (Rental rental : rentals)
        {
            if (rental.getVehicleId().equals(vehicleId) && rental.getStatus() == RentalStatus.ACTIVE)
            {
                return true;
            }
        }
        return false;
    }
}