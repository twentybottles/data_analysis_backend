package com.example.demo.controller;

import static com.example.demo.common.WebConst.TRAFFIC_VOLUME_TMS_FIND_URL;
import static com.example.demo.common.WebConst.TRAFFIC_VOLUME_PRD_FIND_URL;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.atlas.TrafficVolumeResponse;
import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.entity.prd.TrafficVolumePrdResponse;
import com.example.demo.service.SearchTrafficVolumePrdService;
import com.example.demo.service.SearchTrafficVolumeTmsService;

@RestController
public class SearchTrafficVolumeController {

	@Autowired
	private SearchTrafficVolumeTmsService searchTrafficVolumeTmservice;

	@Autowired
	private SearchTrafficVolumePrdService searchTrafficVolumePrdService;
	
	@RequestMapping(path = TRAFFIC_VOLUME_TMS_FIND_URL, method = RequestMethod.POST)
	public List<TrafficVolumeResponse> trafficVolumeTmsFind(@RequestBody DataAnaysisRequest request) throws IOException {
					
		return searchTrafficVolumeTmservice.find(request);
		
	}

	@RequestMapping(path = TRAFFIC_VOLUME_PRD_FIND_URL, method = RequestMethod.POST)
	public List<TrafficVolumePrdResponse> trafficVolumePrdFind(@RequestBody DataAnaysisRequest request) throws IOException {
					
		return searchTrafficVolumePrdService.find(request);
		
	}

}