package com.example.demo.entity.common;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class TrafficVolumeCorrelationResponse {

	private String groupingDate;
	
	private String Bicycle;
		
	private String Bus;
	
	private String Car;
	
	private String Motorcycle;
	
	private String SUV;
	
	private String Truck;
	
	private String TruckBigRig;
	
	private String Tuktuk;
	
	private String Van;

}