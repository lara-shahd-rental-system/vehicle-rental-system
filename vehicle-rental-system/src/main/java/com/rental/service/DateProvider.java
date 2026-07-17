package com.rental.service;

import java.time.LocalDate;


public interface DateProvider
{
    LocalDate getToday();
}