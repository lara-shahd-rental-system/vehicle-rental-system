package com.rental.service;

import com.rental.domain.model.Rental;
import java.util.logging.Logger;

public class MockNotificationService implements NotificationService {

    private static final Logger logger = Logger.getLogger(MockNotificationService.class.getName());

    @Override
    public void sendExpiryReminder(String recipientEmail, Rental rental) {
        logger.info("[MOCK EMAIL] To: " + recipientEmail
            + " | Subject: Rental Expiry Reminder | Vehicle: " + rental.getVehicleId()
            + " | Return by: " + rental.getEndDate());
    }
}