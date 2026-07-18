package com.rental.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.rental.domain.model.Rental;

/**
 * Calculates late-return penalties for rentals returned after the agreed end date.
 */
public class LatePenaltyPolicy
{
    private static final double DAILY_LATE_FEE = 20.0;

    /**
     * Calculates the penalty for a rental based on how late it was returned.
     *
     * @param rental the rental being returned
     * @param actualReturnDate the date the vehicle was actually returned
     * @return the penalty amount (0 if returned on time or early)
     */
    public double calculatePenalty(Rental rental, LocalDate actualReturnDate)
    {
        if (!actualReturnDate.isAfter(rental.getEndDate()))
        {
            return 0.0;
        }

        long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), actualReturnDate);
        return lateDays * DAILY_LATE_FEE;
    }
}