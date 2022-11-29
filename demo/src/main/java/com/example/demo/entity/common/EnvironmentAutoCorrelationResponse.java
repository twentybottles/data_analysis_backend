package com.example.demo.entity.common;

import lombok.Data;

@Data
public class EnvironmentAutoCorrelationResponse {

	private int lag;

	private Double decibel;
    
	private Double ozone;
    
	private Double particles;
    
	private Double co2;
    
	private Double humidity;
    
	private Double temperature;
	
	private Double mcPm2_5;

}
