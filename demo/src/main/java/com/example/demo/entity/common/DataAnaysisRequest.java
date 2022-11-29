package com.example.demo.entity.common;

import java.util.Date;
import lombok.Data;

@Data
public class DataAnaysisRequest {
	
	private String dateOption;
	
	private long sensorId;

	private String deviceId;

	private Date startDate;
	
	private Date endDate;

	private int interval;
	
	private String sensorName;
	
	private int minutes;
	
	private int lag;
	
}