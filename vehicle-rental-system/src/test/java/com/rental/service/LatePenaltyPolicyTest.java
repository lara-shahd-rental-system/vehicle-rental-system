package com.rental.service;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import com.rental.domain.model.Rental;








class LatePenaltyPolicyTest {

    @Test
    void testNoPenaltyWhenOnTime() {
        LatePenaltyPolicy policy = new LatePenaltyPolicy();
        Rental rental = new Rental("V001", "admin", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 6));

        double penalty = policy.calculatePenalty(rental, LocalDate.of(2026, 7, 6));

        assertEquals(0.0, penalty);
    }

    @Test
    void testNoPenaltyWhenReturnedEarly() {
        LatePenaltyPolicy policy = new LatePenaltyPolicy();
        Rental rental = new Rental("V001", "admin", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 6));

        double penalty = policy.calculatePenalty(rental, LocalDate.of(2026, 7, 5));

        assertEquals(0.0, penalty);
    }

    @Test
    void testPenaltyWhenLateOneDay() {
        LatePenaltyPolicy policy = new LatePenaltyPolicy();
        Rental rental = new Rental("V001", "admin", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 6));

        double penalty = policy.calculatePenalty(rental, LocalDate.of(2026, 7, 7));

        assertEquals(20.0, penalty);
    }

    @Test
    void testPenaltyWhenLateMultipleDays() {
        LatePenaltyPolicy policy = new LatePenaltyPolicy();
        Rental rental = new Rental("V001", "admin", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 6));

        double penalty = policy.calculatePenalty(rental, LocalDate.of(2026, 7, 9));

        assertEquals(60.0, penalty);
    }
}