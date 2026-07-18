package com.rental.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rental.domain.model.Rental;
import com.rental.domain.model.RentalStatus;
import com.rental.domain.model.User;
import com.rental.persistence.RentalRepository;
import com.rental.persistence.UserRepository;




public class RentalExpiryReminderServiceTest {

	

	
private RentalExpiryReminderService reminderService;
private RentalRepository rentalRepository;
private UserRepository userRepository;
private NotificationService notificationService;
private DateProvider dateProvider;



@BeforeEach
void setUp() {
    rentalRepository = mock(RentalRepository.class);
    userRepository = mock(UserRepository.class);
    notificationService = mock(NotificationService.class);
    dateProvider = mock(DateProvider.class);
    reminderService = new RentalExpiryReminderService(rentalRepository, userRepository,
        notificationService, dateProvider);
}


@Test
void testSendsReminderWhenExpiringTomorrow() {
	
    LocalDate today = LocalDate.of(2026, 7, 1);
    LocalDate endDate = LocalDate.of(2026, 7, 2);
    Rental mockRental = mock(Rental.class);
    when(mockRental.getStatus()).thenReturn(RentalStatus.ACTIVE);
    when(mockRental.getEndDate()).thenReturn(endDate);
    when(mockRental.getUsername()).thenReturn("admin");
    User mockUser = mock(User.class);
    when(mockUser.getEmail()).thenReturn("admin@email.com"); 
    when(dateProvider.getToday()).thenReturn(today);
    when(rentalRepository.findAll()).thenReturn(List.of(mockRental));
    when(userRepository.findByUsername("admin")).thenReturn(mockUser);
    reminderService.checkAndSendReminders();
    verify(notificationService).sendExpiryReminder("admin@email.com", mockRental);
}

@Test
void testSendsReminderWhenExpiringToday() {
	
    LocalDate today = LocalDate.of(2026, 7, 1);
    LocalDate endDate = LocalDate.of(2026, 7, 1);
    Rental mockRental = mock(Rental.class);
    when(mockRental.getStatus()).thenReturn(RentalStatus.ACTIVE);
    when(mockRental.getEndDate()).thenReturn(endDate);
    when(mockRental.getUsername()).thenReturn("admin");
    User mockUser = mock(User.class);
    when(mockUser.getEmail()).thenReturn("admin@email.com");
    when(dateProvider.getToday()).thenReturn(today);
    when(rentalRepository.findAll()).thenReturn(List.of(mockRental));
    when(userRepository.findByUsername("admin")).thenReturn(mockUser);
    reminderService.checkAndSendReminders();
    verify(notificationService).sendExpiryReminder("admin@email.com", mockRental);
}



@Test
void testDoesNotSendReminderWhenExpiryFarAway() {
	
	
    LocalDate today = LocalDate.of(2026, 7, 1);
    LocalDate endDate = LocalDate.of(2026, 7, 11);
    Rental mockRental = mock(Rental.class);
    when(mockRental.getStatus()).thenReturn(RentalStatus.ACTIVE);
    when(mockRental.getEndDate()).thenReturn(endDate);
    when(dateProvider.getToday()).thenReturn(today);
    when(rentalRepository.findAll()).thenReturn(List.of(mockRental));
    reminderService.checkAndSendReminders();
    verify(notificationService, never()).sendExpiryReminder(anyString(), any(Rental.class));
}
	
@Test
void testDoesNotSendReminderForCompletedRental() {
    LocalDate today = LocalDate.of(2026, 7, 1);
    LocalDate endDate = LocalDate.of(2026, 7, 2);
    Rental mockRental = mock(Rental.class);
    when(mockRental.getStatus()).thenReturn(RentalStatus.COMPLETED);
    when(mockRental.getEndDate()).thenReturn(endDate);
    when(dateProvider.getToday()).thenReturn(today);
    when(rentalRepository.findAll()).thenReturn(List.of(mockRental));
    reminderService.checkAndSendReminders();
    verify(notificationService, never()).sendExpiryReminder(anyString(), any(Rental.class));
}

@Test
void testDoesNotSendReminderWhenNoRentals() {
    when(rentalRepository.findAll()).thenReturn(List.of());
    reminderService.checkAndSendReminders();
    verify(notificationService, never()).sendExpiryReminder(anyString(), any(Rental.class));
}
@Test
void testMultipleRentalsOnlyExpiringOnesNotified() {
    LocalDate today = LocalDate.of(2026, 7, 1);
    Rental expiringSoon = mock(Rental.class);
    when(expiringSoon.getStatus()).thenReturn(RentalStatus.ACTIVE);
    when(expiringSoon.getEndDate()).thenReturn(LocalDate.of(2026, 7, 2));
    when(expiringSoon.getUsername()).thenReturn("admin");
    Rental notExpiringSoon = mock(Rental.class);
    when(notExpiringSoon.getStatus()).thenReturn(RentalStatus.ACTIVE);
    when(notExpiringSoon.getEndDate()).thenReturn(LocalDate.of(2026, 7, 20));
    Rental completedRental = mock(Rental.class);
    when(completedRental.getStatus()).thenReturn(RentalStatus.COMPLETED);
    when(completedRental.getEndDate()).thenReturn(LocalDate.of(2026, 7, 2));
    User mockUser = mock(User.class);
    when(mockUser.getEmail()).thenReturn("admin@email.com");
    when(dateProvider.getToday()).thenReturn(today);
    when(rentalRepository.findAll()).thenReturn(Arrays.asList(expiringSoon, notExpiringSoon, completedRental));
    when(userRepository.findByUsername("admin")).thenReturn(mockUser);
    reminderService.checkAndSendReminders();
    verify(notificationService, times(1)).sendExpiryReminder(anyString(), any(Rental.class));
}

}
