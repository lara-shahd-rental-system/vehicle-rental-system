package com.rental.service;
import com.rental.domain.model.Rental;


public class MockNotificationService implements NotificationService {

	@Override
	
    public void sendExpiryReminder(String recipientEmail, Rental rental) {
        System.out.println("[MOCK EMAIL] To: " + recipientEmail
            + " | Subject: Rental Expiry Reminder | Vehicle: " + rental.getVehicleId()
            + " | Return by: " + rental.getEndDate());
    }
}
	

