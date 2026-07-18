package com.rental.service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.rental.domain.model.Rental;
import com.rental.domain.model.RentalStatus;
import com.rental.domain.model.User;
import com.rental.persistence.RentalRepository;
import com.rental.persistence.UserRepository;

public class RentalExpiryReminderService {

    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private NotificationService notificationService;
    private DateProvider dateProvider;

    public RentalExpiryReminderService(RentalRepository rentalRepository, UserRepository userRepository,
            NotificationService notificationService, DateProvider dateProvider) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.dateProvider = dateProvider;
    }

 
    public void checkAndSendReminders() {
        List<Rental> allRentals = rentalRepository.findAll();
        LocalDate today = dateProvider.getToday();

        for (Rental rental : allRentals) {
            if (rental.getStatus() == RentalStatus.ACTIVE) {
                long daysUntilExpiry = ChronoUnit.DAYS.between(today, rental.getEndDate());
                if (daysUntilExpiry <= 1) {
                    User user = userRepository.findByUsername(rental.getUsername());
                    if (user != null) {
                        notificationService.sendExpiryReminder(user.getEmail(), rental);
                    }
                }
            }
        }
    }
}