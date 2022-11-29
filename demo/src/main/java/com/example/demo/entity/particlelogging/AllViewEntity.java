package com.example.demo.entity.particlelogging;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "all_view")
@Data
public class AllViewEntity {
	
	public AllViewEntity(
			
			Float currCharge,
			Float extpwrval,
			Float voltage,
			String eventTime
			
			){

		this.currCharge = currCharge;
		this.extpwrval = extpwrval;
		this.voltage = voltage;
		this.eventTime = eventTime;

	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long event_id;

	@Column(name="event_time")
	private String eventTime;

	@Column(name="device_id")
	private String deviceId;
	
	private Float currCharge;
	
	private Float extpwrval;
	
	private Float voltage;

}
