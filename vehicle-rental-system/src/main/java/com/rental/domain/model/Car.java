package com.rental.domain.model;




public class Car extends Vehicle {

   
    public Car(String vehicleId, String brand, String model, int year, double dailyRate) {
        super(vehicleId, brand, model, year, dailyRate);
    }

    @Override
    public void validateEligibility(User user) {
    }
}