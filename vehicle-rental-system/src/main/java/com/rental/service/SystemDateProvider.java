package com.rental.service;

import java.time.LocalDate;

/**
 * Real implementation of DateProvider that returns the actual system date.
 */
public class SystemDateProvider implements DateProvider
{
    @Override
    public LocalDate getToday()
    {
        return LocalDate.now();
    }
}