package com.example.demo.controller;

import static com.example.demo.common.WebConst.ENVIRONMENT_FIND_URL;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.entity.enviropi.AllSensorViewEntity;
import com.example.demo.service.SearchEnvironmentService;	

@RestController
public class SearchEnvironmentController {

	@Autowired
	private SearchEnvironmentService searchEnvironmentService;
	
	@RequestMapping(path = ENVIRONMENT_FIND_URL, method = RequestMethod.POST)
	public List<AllSensorViewEntity> EnvironmentFind(@RequestBody DataAnaysisRequest request) throws IOException {

		return searchEnvironmentService.find(request);

	}

}