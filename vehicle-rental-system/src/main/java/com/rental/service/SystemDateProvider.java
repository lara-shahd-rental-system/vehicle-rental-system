package com.rental.service;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Real implementation of DateProvider that returns the actual system date.
 */
public class SystemDateProvider implements DateProvider
{
    @Override
    public LocalDate getToday()
    {
        return LocalDate.now(ZoneId.systemDefault());
    }
}