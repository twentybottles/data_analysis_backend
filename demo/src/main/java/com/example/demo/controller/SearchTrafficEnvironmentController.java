package com.example.demo.controller;

import static com.example.demo.common.WebConst.TRAFFIC_PRD_ENV_FIND_URL;
import static com.example.demo.common.WebConst.TRAFFIC_TMS_ENV_FIND_URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.atlas.TrafficVolumeResponse;
import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.entity.enviropi.AllSensorViewEntity;
import com.example.demo.entity.prd.TrafficVolumePrdResponse;
import com.example.demo.service.SearchEnvironmentService;
import com.example.demo.service.SearchTrafficVolumePrdService;
import com.example.demo.service.SearchTrafficVolumeTmsService;

@RestController
public class SearchTrafficEnvironmentController {

	@Autowired
	private SearchTrafficVolumePrdService searchTrafficVolumePrdService;

	@Autowired
	private SearchTrafficVolumeTmsService searchTrafficVolumeTmsService;

	@Autowired
	private SearchEnvironmentService searchEnvironmentService;

	@RequestMapping(path = TRAFFIC_TMS_ENV_FIND_URL, method = RequestMethod.POST)
	public List<Object> trafficTmsEnvVolumeFind(@RequestBody DataAnaysisRequest request) throws IOException {
		
		List<TrafficVolumeResponse> trafficVolumeResponseList = searchTrafficVolumeTmsService.findLatestRecord(request);
		
		List<AllSensorViewEntity> allSensorViewEntityList = searchEnvironmentService.findLatest(request);
		
		List<Object> trafficEnvList = new ArrayList<Object>();
		
		trafficEnvList.add(trafficVolumeResponseList);
		trafficEnvList.add(allSensorViewEntityList);
		trafficEnvList.add(request.getSensorName());
		
		return trafficEnvList;
		
	}
	
	@RequestMapping(path = TRAFFIC_PRD_ENV_FIND_URL, method = RequestMethod.POST)
	public List<Object> trafficPrdEnvVolumeFind(@RequestBody DataAnaysisRequest request) throws IOException {
		
		List<TrafficVolumePrdResponse> trafficVolumeResponseList = searchTrafficVolumePrdService.find(request);
		
		List<AllSensorViewEntity> allSensorViewEntityList = searchEnvironmentService.findLatest(request);
		
		List<Object> trafficEnvList = new ArrayList<Object>();
		
		trafficEnvList.add(trafficVolumeResponseList);
		trafficEnvList.add(allSensorViewEntityList);
		trafficEnvList.add(request.getSensorName());

		return trafficEnvList;
		
	}

}