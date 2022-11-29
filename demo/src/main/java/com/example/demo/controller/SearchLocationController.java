package com.example.demo.controller;

import static com.example.demo.common.WebConst.LOCATION_FIND_URL;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.service.SearchLocationService;

@RestController
public class SearchLocationController {

	@Autowired
	private SearchLocationService searchLocationeService;
	
	@RequestMapping(path = LOCATION_FIND_URL, method = RequestMethod.POST)
	public Map<String, List<Object>> trafficVolumeFind(@RequestBody List<String> deviceList) throws IOException {
		
		Map<String, List<Object>> a = searchLocationeService.find(deviceList);
		
		return a;
		
	}

}