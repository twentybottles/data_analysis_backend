package com.example.demo.entity.enviropi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "all_sensor_view")
@Data
public class AllSensorViewEntity {
	
	public AllSensorViewEntity(
			
			Double decibel,
			Double ozone,
			Double particles,
			Double co2,
			Double humidity,
			Double temperature,
			Double mcPm2_5,
			String eventTime,
			String format
			
			){

		this.decibel = decibel;
		this.ozone = ozone;
		this.particles = particles;
		this.co2 = co2;
		this.humidity = humidity;
		this.temperature = temperature;
		this.mcPm2_5 = mcPm2_5;
		this.eventTime = eventTime;
		this.format = format;

	}
	
	public AllSensorViewEntity(
			
			Double decibel,
			Double ozone,
			Double particles,
			Double co2,
			Double humidity,
			Double temperature,
			Double mcPm2_5,
			Double maxDecibel,
			Double maxOzone,
			Double maxParticles,
			Double maxCo2,
			Double maxHumidity,
			Double maxTemperature,
			Double maxMcPm2_5,
			String eventTime,
			String format
	
			){

		this.decibel = decibel;
		this.ozone = ozone;
		this.particles = particles;
		this.co2 = co2;
		this.humidity = humidity;
		this.temperature = temperature;
		this.mcPm2_5 = mcPm2_5;
		this.maxDecibel = maxDecibel;
		this.maxOzone = maxOzone;
		this.maxParticles = maxParticles;
		this.maxCo2 = maxCo2;
		this.maxHumidity = maxHumidity;
		this.maxTemperature = maxTemperature;
		this.maxMcPm2_5 = maxMcPm2_5;
		this.eventTime = eventTime;
		this.format = format;

	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long event_id;

	@Column(name="device_id")
	private String deviceId;
    
	@Column(name="event_time")
	private String eventTime;
    
	private Double decibel;
        
	private Double ozone;
    
	private Double particles;
    
	private Double co2;
    
	private Double humidity;
    
	private Double temperature;
	
	@Column(name="MC_PM_2_5")
	private Double mcPm2_5;
	
	private String format;
	
	private Double maxDecibel;
    
	private Double maxOzone;
    
	private Double maxParticles;
    
	private Double maxCo2;
    
	private Double maxHumidity;
    
	private Double maxTemperature;

	private Double maxMcPm2_5;


}
