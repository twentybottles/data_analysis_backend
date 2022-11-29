package com.example.demo.entity.prd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "lanes")
@Data
public class LanesEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="sensor_id",insertable = false, updatable = false)
	private int sensorId;	
	
    @ManyToOne(optional=false)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private SensorsEntity sensorsEntity;


}