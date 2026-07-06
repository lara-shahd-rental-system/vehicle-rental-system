package com.rental.domain.model;

public class Vehicle {

	private String vehicleId ;
	private String brand  ;
	private String model ;
	private  int year ;
	private  VehicleStatus status ;
	 
	public  Vehicle (String vehicleId , String brand , String model,  int year){
		if (vehicleId == null || vehicleId.isEmpty()) {
			throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
}
		
		if (brand  == null || brand .isEmpty()) {
			throw new IllegalArgumentException("brand  cannot be null or empty");
}
		if (model  == null || model .isEmpty()) {
			throw new IllegalArgumentException("Model cannot be null or empty");
}
		if (year < 1886) {
			throw new IllegalArgumentException("Invalid year");}
		
		this.vehicleId=vehicleId;
		this.brand=brand;
		this.model=model;
		this.year=year;
		status = VehicleStatus.AVAILABLE;
	 }
	
	public String getVehicleId() {
		
		return 	vehicleId;
	}
	public String getBrand()
{
		
		return 	brand;
		
	}
	public String getModel(){
		
		return 	model;
		
	}
	public	int getYear(){
		
		return 	year;
		
	}
public VehicleStatus getStatus(){
	
	return 	status;
	
}






public void setStatus(VehicleStatus status) {
	

if (status == null) {
throw new IllegalArgumentException("Status cannot be null");
	
}
	this.status=status;

	
	
}

@Override
public String toString() {
	
	return "Vehicle{id='" + vehicleId + "', brand='" + brand + "', model='" + model + "', year=" + year + ", status=" + status + "}";

	
}


public boolean isAvailable() {
	
	return status == VehicleStatus.AVAILABLE;	
}

public void rent()
{
	if (status != (VehicleStatus.AVAILABLE )){
	
	throw new IllegalStateException("Vehicle is not available");

	}
	status = VehicleStatus.RENTED;
	
}

public void  returnVehicle(){
	
	if (status != (VehicleStatus.RENTED  )){
		
		throw new IllegalStateException("Vehicle is not rented");

		}
		status = VehicleStatus.AVAILABLE;
		
	
	
}

 @Override

public boolean equals(Object x) {
	 
	 if (this == x) return true; 
	 if (x == null) return false;
	 if (!(x instanceof Vehicle)) return false;
	 Vehicle other = (Vehicle) x;
     return  this.vehicleId.equals(other.vehicleId);


}










}
