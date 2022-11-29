package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.common.Formatter;
import com.example.demo.common.Utilities;
import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.entity.enviropi.AllSensorViewEntity;
import com.example.demo.exception.MyResourceNotFoundException;
import com.example.demo.repository.enviropi.AllSensorViewEntityRepository;


@Service
public class SearchEnvironmentService {
	
    @Autowired
    private AllSensorViewEntityRepository envDataViewJSTViewRepository;
    
    public List<AllSensorViewEntity> find(DataAnaysisRequest request) {
    	
		Formatter formatter = new Formatter();
		Utilities util = new Utilities();

    	try {

    		String utcLag = util.getUtcLag(request.getStartDate());

    		List<AllSensorViewEntity> allSensorViewEntityList = envDataViewJSTViewRepository.findByAllSensorViewQuery(
    				request.getDeviceId(), 
    				formatter.convertDateFormat(request.getStartDate(), request.getDateOption()), 
    				formatter.convertDateFormat(request.getEndDate(), request.getDateOption()),     				
    				request.getInterval(),
    				formatter.getFormatMySql(request.getDateOption()),
    				utcLag,
    				formatter.getFormatMySql(request.getDateOption())
    		);
    		
    		return allSensorViewEntityList;
    		
    	} catch (MyResourceNotFoundException | ParseException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}

	} 

    public List<AllSensorViewEntity> findLatest(DataAnaysisRequest request) {
    	
		Formatter formatter = new Formatter();

    	try {
    		    		
    		List<AllSensorViewEntity> allSensorViewEntityList = envDataViewJSTViewRepository.findByAllSensorViewLatestQuery(
    				request.getDeviceId(), 
    				formatter.convertDateFormat(request.getStartDate(), request.getDateOption()), 
    				request.getInterval(),
    				formatter.getFormatMySql(request.getDateOption()),
    				formatter.getFormatMySql(request.getDateOption())
    		);
    		
    		return allSensorViewEntityList;
    		
    	} catch (MyResourceNotFoundException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}

	}    

}