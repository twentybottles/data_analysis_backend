package com.example.demo.entity.common;

import lombok.Value;

@Value
public class TrafficVolumeResponse {

	private String groupingDate;
	
	private Long totalBicycle;
		
	private Long totalBus;
	
	private Long totalCar;
	
	private Long totalMotorcycle;
	
	private Long totalSUV;
	
	private Long totalTruck;
	
	private Long totalTruckBigRig;
	
	private Long totalTuktuk;
	
	private Long totalVan;
	
	private String averageBicycle;
		
	private String averageBus;
	
	private String averageCar;
	
	private String averageMotorcycle;
	
	private String averageSUV;
	
	private String averageTruck;
	
	private String averageTruckBigRig;
	
	private String averageTuktuk;
	
	private String averageVan;

}