package com.example.demo.entity.common;

import lombok.Data;

@Data
public class TrafficAutoCorrelationResponse {

	private int lag;

	private double vehicles;
	    
	private double bicycle;
        
	private double bus;
    
	private double car;
    
	private double motorcycleTuktuk;
    
	private double suv;
    
	private double truck;
	
	private double truckBigRig;

	private double van;
	
	private double carSuvVan;

}
