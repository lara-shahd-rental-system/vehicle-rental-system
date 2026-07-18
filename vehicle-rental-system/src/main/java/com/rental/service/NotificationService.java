package com.rental.service;
import com.rental.domain.model.Rental;






public interface NotificationService {

    void sendExpiryReminder(String recipientEmail, Rental rental);
}