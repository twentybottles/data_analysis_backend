package com.example.demo.entity.tracking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tracking_objects")
@Data
public class TrackingObjectsEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	private String name;

	private Double latitude;
	
	private Double longitude;
	
	private Date created_at;

	private Date updated_at;

}
	