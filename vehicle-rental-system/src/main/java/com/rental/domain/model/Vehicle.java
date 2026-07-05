package com.rental.domain.model;

public class Vehicle {

	private String vehicleId ;
	private String brand  ;
	private String model ;
	private  int year ;
	private  VehicleStatus status ;
	 
	public  Vehicle (String vehicleId , String brand , String model,  int year){
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
	this.status=status;

	
	
}










}
