package com.rental.persistence;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rental.domain.model.Rental;
import com.rental.domain.model.RentalStatus;
import java.time.LocalDate;
import java.util.List;

public class RentalRepositoryTest
{
    private RentalRepository rentalRepository;
    private LocalDate today;

    @BeforeEach
    public void setUp()
    {
        rentalRepository = new InMemoryRentalRepository();
        today = LocalDate.now();
    }

    @Test
    public void testAddRental_ThenFindAll_ContainsRental()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(3));
        rentalRepository.addRental(rental);

        List<Rental> all = rentalRepository.findAll();

        assertEquals(1, all.size());
        assertEquals(rental, all.get(0));
    }

    @Test
    public void testAddRental_WithNull_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalRepository.addRental(null);
        });
        assertEquals("Rental cannot be null", exception.getMessage());
    }

    @Test
    public void testFindByUsername_ReturnsOnlyMatchingRentals()
    {
        Rental rental1 = new Rental("V001", "lara123", today, today.plusDays(3));
        Rental rental2 = new Rental("V002", "sara456", today, today.plusDays(3));
        rentalRepository.addRental(rental1);
        rentalRepository.addRental(rental2);

        List<Rental> laraRentals = rentalRepository.findByUsername("lara123");

        assertEquals(1, laraRentals.size());
        assertEquals(rental1, laraRentals.get(0));
    }

    @Test
    public void testFindByUsername_NoMatches_ReturnsEmptyList()
    {
        List<Rental> result = rentalRepository.findByUsername("notExist");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByUsername_WithNull_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalRepository.findByUsername(null);
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testFindByVehicleId_ReturnsOnlyMatchingRentals()
    {
        Rental rental1 = new Rental("V001", "lara123", today, today.plusDays(3));
        Rental rental2 = new Rental("V002", "lara123", today, today.plusDays(3));
        rentalRepository.addRental(rental1);
        rentalRepository.addRental(rental2);

        List<Rental> result = rentalRepository.findByVehicleId("V001");

        assertEquals(1, result.size());
        assertEquals(rental1, result.get(0));
    }

    @Test
    public void testFindByVehicleId_WithNull_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalRepository.findByVehicleId(null);
        });
        assertEquals("Vehicle ID cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testHasActiveRentalForVehicle_WithActiveRental_ReturnsTrue()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(3));
        rentalRepository.addRental(rental);

        assertTrue(rentalRepository.hasActiveRentalForVehicle("V001"));
    }

    @Test
    public void testHasActiveRentalForVehicle_WithCompletedRental_ReturnsFalse()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(3));
        rental.setStatus(RentalStatus.COMPLETED);
        rentalRepository.addRental(rental);

        assertFalse(rentalRepository.hasActiveRentalForVehicle("V001"));
    }

    @Test
    public void testHasActiveRentalForVehicle_NoRentalAtAll_ReturnsFalse()
    {
        assertFalse(rentalRepository.hasActiveRentalForVehicle("V999"));
    }

    @Test
    public void testHasActiveRentalForVehicle_WithNull_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalRepository.hasActiveRentalForVehicle(null);
        });
        assertEquals("Vehicle ID cannot be null or empty", exception.getMessage());
    }
}