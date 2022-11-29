package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exception.MyResourceNotFoundException;
import com.example.demo.repository.tracking.TrackingObjectsRepository;

@Service
public class SearchLocationService {

    @Autowired
    private TrackingObjectsRepository trackingObjectsRepository;
    
    @Transactional
    public Map<String, List<Object>> find(List<String> deviceList) {
    	
    	Map<String, List<Object>> map = new HashMap<>();

    	try {
    		
    		for(String device : deviceList) {
    		
    			map.put(device, trackingObjectsRepository.findByLatestLocationQuery(device));

    		}

    		return map;
    		
    	} catch (MyResourceNotFoundException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}
    	    	
    }

}