package com.rental.domain.model;
import com.rental.exception.RentalException;






public class ElectricVehicle extends Vehicle {

	
    private static final int MIN_BATTERY_LEVEL = 20;
    private int batteryLevel;
    
    
    
    public ElectricVehicle(String vehicleId, String brand, String model, int year, double dailyRate,
            int batteryLevel) {
        super(vehicleId, brand, model, year, dailyRate);
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Battery level must be between 0 and 100");
        }
        this.batteryLevel = batteryLevel;
    }
    
    public int getBatteryLevel() {
        return batteryLevel;
    }
    
    
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Battery level must be between 0 and 100");
        }
        this.batteryLevel = batteryLevel;
    }
    
    
    @Override
    public void validateEligibility(User user) throws RentalException {
        if (batteryLevel < MIN_BATTERY_LEVEL) {
            throw new RentalException("Vehicle battery level is too low to rent");
        }
    }
    
    
    
    
}
