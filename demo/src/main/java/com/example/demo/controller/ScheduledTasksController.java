package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.RegisterTrackingObjectsService;

@Component
public class ScheduledTasksController {

	@Autowired
	private RegisterTrackingObjectsService registerTrackingObjectsService;
	
//	@Scheduled(fixedDelay = 1000)
//	public void updateTrackingObjects() {
//		
//		registerTrackingObjectsService.save();
//		
//	}

}