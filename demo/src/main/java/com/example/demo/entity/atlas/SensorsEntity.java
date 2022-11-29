package com.example.demo.entity.atlas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sensors")
@Data
public class SensorsEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

}