package com.rental.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rental.domain.model.Rental;
import com.rental.domain.model.Vehicle;
import com.rental.exception.RentalException;
import com.rental.persistence.RentalRepository;
import com.rental.persistence.VehicleRepository;


public class RentalServiceTest {
	
	
	private RentalService rentalService;
	private RentalRepository rentalRepository;
	private VehicleRepository vehicleRepository;
	private VehicleService vehicleService;
	private AuthenticationService authenticationService;
	private DoubleBookingCheckStrategy doubleBookingCheckStrategy;
	private DateProvider dateProvider;
	private Vehicle mockVehicle;
	
	
	@BeforeEach
	void setUp() {
		
		
	    rentalRepository = mock(RentalRepository.class);
	    vehicleRepository = mock(VehicleRepository.class);
	    vehicleService = mock(VehicleService.class);
	    authenticationService = mock(AuthenticationService.class);
	    doubleBookingCheckStrategy = mock(DoubleBookingCheckStrategy.class);
	    dateProvider = mock(DateProvider.class);
	    mockVehicle = mock(Vehicle.class);

	    rentalService = new RentalService(rentalRepository, vehicleRepository,
	        vehicleService, authenticationService, doubleBookingCheckStrategy, dateProvider);
	}
	
	
	@Test
	
	void testRentVehicleSuccess() throws RentalException {
	    LocalDate today = LocalDate.of(2026, 7, 1);
	    LocalDate startDate = LocalDate.of(2026, 7, 5);
	    LocalDate endDate = LocalDate.of(2026, 7, 10);

	    when(authenticationService.isLoggedIn()).thenReturn(true);
	    when(vehicleRepository.findById("V001")).thenReturn(mockVehicle);
	    when(doubleBookingCheckStrategy.isBookingAllowed(mockVehicle)).thenReturn(true);
	    when(dateProvider.getToday()).thenReturn(today);

	    
	    Rental result = rentalService.rentVehicle("admin", "V001", startDate, endDate);

	    
	    assertNotNull(result);
	    verify(rentalRepository).addRental(any(Rental.class));
	    verify(vehicleService).markAsRented("V001");
	}
	
	@Test
	
	void testRentVehicleWhenNotLoggedIn() {
	    when(authenticationService.isLoggedIn()).thenReturn(false);

	    assertThrows(RentalException.class, () -> {
	        rentalService.rentVehicle("admin", "V001", LocalDate.now(), LocalDate.now().plusDays(3));});

	    verify(vehicleRepository, never()).findById(any());
	}
	
	@Test
	
	void testRentVehicleWhenVehicleNotFound() {
	    when(authenticationService.isLoggedIn()).thenReturn(true);
	    when(vehicleRepository.findById("V001")).thenReturn(null);

	    assertThrows(RentalException.class, () -> {
	        rentalService.rentVehicle("admin", "V001", LocalDate.now(), LocalDate.now().plusDays(3)); });
	}
	
	@Test
	
	void testRentVehicleWhenNotAvailable() {
	    when(authenticationService.isLoggedIn()).thenReturn(true);
	    when(vehicleRepository.findById("V001")).thenReturn(mockVehicle);
	    when(doubleBookingCheckStrategy.isBookingAllowed(mockVehicle)).thenReturn(false);

	    assertThrows(RentalException.class, () -> {
	        rentalService.rentVehicle("admin", "V001", LocalDate.now(), LocalDate.now().plusDays(3)); });

	    
	      verify(vehicleService, never()).markAsRented(any());
	}
	
	@Test
	
	void testRentVehicleEndDateBeforeStartDate() {
	    when(authenticationService.isLoggedIn()).thenReturn(true);
	    when(vehicleRepository.findById("V001")).thenReturn(mockVehicle);
	    when(doubleBookingCheckStrategy.isBookingAllowed(mockVehicle)).thenReturn(true);

	    
	    LocalDate startDate = LocalDate.of(2026, 7, 10);
	    LocalDate endDate = LocalDate.of(2026, 7, 5);

	    
	    
	    assertThrows(IllegalArgumentException.class, () -> {
	        rentalService.rentVehicle("admin", "V001", startDate, endDate); });
	}
	
	
	@Test
	
	void testRentVehicleStartDateInPast() {
	    when(authenticationService.isLoggedIn()).thenReturn(true);
	    when(vehicleRepository.findById("V001")).thenReturn(mockVehicle);
	    when(doubleBookingCheckStrategy.isBookingAllowed(mockVehicle)).thenReturn(true);
	    when(dateProvider.getToday()).thenReturn(LocalDate.of(2026, 7, 10));

	    
	    LocalDate startDate = LocalDate.of(2026, 7, 5);
	    LocalDate endDate = LocalDate.of(2026, 7, 15);
	    assertThrows(IllegalArgumentException.class, () -> {
	        rentalService.rentVehicle("admin", "V001", startDate, endDate); });
	}
	
	@Test
	void testRentVehicleDurationTooShort() {
	    when(authenticationService.isLoggedIn()).thenReturn(true);
	    when(vehicleRepository.findById("V001")).thenReturn(mockVehicle);
	    when(doubleBookingCheckStrategy.isBookingAllowed(mockVehicle)).thenReturn(true);
	    when(dateProvider.getToday()).thenReturn(LocalDate.of(2026, 7, 1));

	    
	    LocalDate startDate = LocalDate.of(2026, 7, 5);
	    LocalDate endDate = LocalDate.of(2026, 7, 5);

	    
	    assertThrows(IllegalArgumentException.class, () -> {
	        rentalService.rentVehicle("admin", "V001", startDate, endDate);  });
	}
	
	
	@Test
	void testRentVehicleDurationTooLong() {
	    when(authenticationService.isLoggedIn()).thenReturn(true);
	    when(vehicleRepository.findById("V001")).thenReturn(mockVehicle);
	    when(doubleBookingCheckStrategy.isBookingAllowed(mockVehicle)).thenReturn(true);
	    when(dateProvider.getToday()).thenReturn(LocalDate.of(2026, 7, 1));

	    LocalDate startDate = LocalDate.of(2026, 7, 5);
	    LocalDate endDate = LocalDate.of(2026, 8, 10); // 36 يوم فرق

	    assertThrows(RentalException.class, () -> {
	        rentalService.rentVehicle("admin", "V001", startDate, endDate);
	    });
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
