package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class RentalTest
{
    private LocalDate today;

    @BeforeEach
    public void setUp()
    {
        today = LocalDate.now();
    }

    @Test
    public void testConstructor_WithValidData_CreatesRentalCorrectly()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(3));

        assertEquals("V001", rental.getVehicleId());
        assertEquals("lara123", rental.getUsername());
        assertEquals(today, rental.getStartDate());
        assertEquals(today.plusDays(3), rental.getEndDate());
        assertEquals(RentalStatus.ACTIVE, rental.getStatus());
    }

    @Test
    public void testConstructor_GeneratesNonNullRentalId()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(3));

        assertNotNull(rental.getRentalId());
        assertFalse(rental.getRentalId().trim().isEmpty());
    }

    @Test
    public void testConstructor_TwoRentals_HaveDifferentIds()
    {
        Rental rental1 = new Rental("V001", "lara123", today, today.plusDays(3));
        Rental rental2 = new Rental("V002", "sara456", today, today.plusDays(3));

        assertNotEquals(rental1.getRentalId(), rental2.getRentalId());
    }

    @Test
    public void testConstructor_WithEndDateBeforeStartDate_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("V001", "lara123", today, today.minusDays(1));
        });
        assertEquals("End date must be after start date", exception.getMessage());
    }

    @Test
    public void testConstructor_WithEndDateEqualsStartDate_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("V001", "lara123", today, today);
        });
        assertEquals("End date must be after start date", exception.getMessage());
    }

    @Test
    public void testConstructor_WithDurationOverMax_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("V001", "lara123", today, today.plusDays(31));
        });
        assertEquals("Rental duration must be between 1 and 30 days", exception.getMessage());
    }

    @Test
    public void testConstructor_WithDurationAtMaxBoundary_Succeeds()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(30));

        assertEquals(today.plusDays(30), rental.getEndDate());
    }

    @Test
    public void testConstructor_WithDurationAtMinBoundary_Succeeds()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(1));

        assertEquals(today.plusDays(1), rental.getEndDate());
    }

    @Test
    public void testConstructor_WithNullVehicleId_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental(null, "lara123", today, today.plusDays(3));
        });
        assertEquals("Vehicle ID cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testConstructor_WithEmptyVehicleId_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("", "lara123", today, today.plusDays(3));
        });
        assertEquals("Vehicle ID cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testConstructor_WithNullUsername_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("V001", null, today, today.plusDays(3));
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testConstructor_WithEmptyUsername_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("V001", "", today, today.plusDays(3));
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testConstructor_WithNullStartDate_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("V001", "lara123", null, today.plusDays(3));
        });
        assertEquals("Start date and end date cannot be null", exception.getMessage());
    }

    @Test
    public void testConstructor_WithNullEndDate_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rental("V001", "lara123", today, null);
        });
        assertEquals("Start date and end date cannot be null", exception.getMessage());
    }

    @Test
    public void testSetStatus_UpdatesStatus()
    {
        Rental rental = new Rental("V001", "lara123", today, today.plusDays(3));

        rental.setStatus(RentalStatus.COMPLETED);

        assertEquals(RentalStatus.COMPLETED, rental.getStatus());
    }
}