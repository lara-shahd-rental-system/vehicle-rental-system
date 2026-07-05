package com.rental.domain.model;

public class Vehicle {

	private String vehicleId ;
	private String brand  ;
	private   String model ;
	private  int year ;
	private  VehicleStatus status ;
	 
	public  Vehicle (String vehicleId , String brand , String model,  int year){
		this.vehicleId=vehicleId;
		this.brand=brand;
		this.model=model;
		this.year=year;
	 }
	
	public String getvehicleId() {
		
		return 	vehicleId;
		
	}
	public String getbrand()
{
		
		return 	brand;
		
	}
	public String getmodel(){
		
		return 	model;
		
	}
	public	int getyear(){
		
		return 	year;
		
	}
public VehicleStatus getstatus(){
	
	return 	status;
	
}


public void setStatus() {
	
	
}










}
