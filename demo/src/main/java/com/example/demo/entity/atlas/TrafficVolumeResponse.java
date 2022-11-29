package com.example.demo.entity.atlas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.common.Formatter;

import lombok.Data;

@Entity
@Table(name = "lane_records")
@Data
public class TrafficVolumeResponse {
	
	public TrafficVolumeResponse(
			
			Double totalVehicles,
			Double totalBicycle,
			Double totalBus,
			Double totalCar,
			Double totalMotorcycleTuktuk,
			Double totalSUV,
			Double totalTruck,
			Double totalTruckBigRig,
			Double totalVan,
			Double totalCarSUVVan,
			Double maxVehicles,
			Double maxBicycle,
			Double maxBus,
			Double maxCar,
			Double maxMotorcycleTuktuk,
			Double maxSUV,
			Double maxTruck,
			Double maxTruckBigRig,
			Double maxVan,
			Double maxCarSUVVan,
			Date packet_ts,
			String dataOption
			
			){

		this.totalVehicles = totalVehicles;
		this.totalBicycle = totalBicycle;
		this.totalBus = totalBus;
		this.totalCar = totalCar;
		this.totalMotorcycleTuktuk = totalMotorcycleTuktuk;
		this.totalSUV = totalSUV;
		this.totalTruck = totalTruck;
		this.totalTruckBigRig = totalTruckBigRig;
		this.totalVan = totalVan;
		this.totalCarSUVVan = totalCarSUVVan;
		this.maxVehicles = maxVehicles;
		this.maxBicycle = maxBicycle;
		this.maxBus = maxBus;
		this.maxCar = maxCar;
		this.maxMotorcycleTuktuk = maxMotorcycleTuktuk;
		this.maxSUV = maxSUV;
		this.maxTruck = maxTruck;
		this.maxTruckBigRig = maxTruckBigRig;
		this.maxVan = maxVan;
		this.maxCarSUVVan = maxCarSUVVan;
		this.packetTs = getFormatInstance().convertDateFormat(packet_ts, dataOption);

	}
	
	public TrafficVolumeResponse(
			
			Double totalVehicles,
			Double totalBicycle,
			Double totalBus,
			Double totalCar,
			Double totalMotorcycleTuktuk,
			Double totalSUV,
			Double totalTruck,
			Double totalTruckBigRig,
			Double totalVan,
			Double totalCarSUVVan,
			Double maxVehicles,
			Double maxBicycle,
			Double maxBus,
			Double maxCar,
			Double maxMotorcycleTuktuk,
			Double maxSUV,
			Double maxTruck,
			Double maxTruckBigRig,
			Double maxVan,
			Double maxCarSUVVan,
			String packet_ts,
			String dataOption
			
			){

		this.totalVehicles = totalVehicles;
		this.totalBicycle = totalBicycle;
		this.totalBus = totalBus;
		this.totalCar = totalCar;
		this.totalMotorcycleTuktuk = totalMotorcycleTuktuk;
		this.totalSUV = totalSUV;
		this.totalTruck = totalTruck;
		this.totalTruckBigRig = totalTruckBigRig;
		this.totalVan = totalVan;
		this.totalCarSUVVan = totalCarSUVVan;
		this.maxVehicles = maxVehicles;
		this.maxBicycle = maxBicycle;
		this.maxBus = maxBus;
		this.maxCar = maxCar;
		this.maxMotorcycleTuktuk = maxMotorcycleTuktuk;
		this.maxSUV = maxSUV;
		this.maxTruck = maxTruck;
		this.maxTruckBigRig = maxTruckBigRig;
		this.maxVan = maxVan;
		this.maxCarSUVVan = maxCarSUVVan;
		if ("EEE".equals(dataOption)) {
			this.packetTs = getFormatInstance().getDay(packet_ts);
		} else {
			this.packetTs = packet_ts;
		}

	}

	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(insertable = false, updatable = false)
	private int lane_id;	
	
	@Column(name="packet_ts")
	private String packetTs;

	private Double totalVehicles;
	
	private Double totalBicycle;
		
	private Double totalBus;
	
	private Double totalCar;
	
	private Double totalMotorcycleTuktuk;
	
	private Double totalSUV;
	
	private Double totalTruck;
	
	private Double totalTruckBigRig;
		
	private Double totalVan;

	private Double totalCarSUVVan;
	
	private Double maxVehicles;
	
	private Double maxBicycle;
		
	private Double maxBus;
	
	private Double maxCar;
	
	private Double maxMotorcycleTuktuk;
	
	private Double maxSUV;
	
	private Double maxTruck;
	
	private Double maxTruckBigRig;
		
	private Double maxVan;

	private Double maxCarSUVVan;
	
	private Formatter getFormatInstance() {
		
		return new Formatter();
		
	}
	
	

}

