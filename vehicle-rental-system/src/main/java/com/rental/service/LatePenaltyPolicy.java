package com.rental.service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.rental.domain.model.Rental;


public class LatePenaltyPolicy {

    private static final double PENALTY_PER_DAY = 20.0;

    public double calculatePenalty(Rental rental, LocalDate actualReturnDate) {
        if (actualReturnDate.isAfter(rental.getEndDate())) {
            long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), actualReturnDate);
            return lateDays * PENALTY_PER_DAY;
        }
        return 0.0;
    }
}