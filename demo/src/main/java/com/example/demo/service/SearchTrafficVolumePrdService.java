package com.example.demo.service;

import static com.example.demo.common.WebConst.DAY;
import static com.example.demo.common.WebConst.HOURS24;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.common.Formatter;
import com.example.demo.common.Utilities;
import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.entity.prd.TrafficVolumePrdResponse;
import com.example.demo.exception.MyResourceNotFoundException;
import com.example.demo.repository.prd.TrafficVolumePrdRepository;

@Service
public class SearchTrafficVolumePrdService {

    @Autowired
    private TrafficVolumePrdRepository trafficVolumePrdRepository;

    @Transactional
    public List<TrafficVolumePrdResponse> find(DataAnaysisRequest request) {

   	try {
    		
    		Formatter formatter = new Formatter();
    		
    		Utilities util = new Utilities();
    		String utcLag = util.getUtcLag(request.getStartDate());
    		
    		// If Local Time is UTC - Correction time (Carson)
    		if(request.getInterval() < 0) {
    			
    			request.setInterval(-request.getInterval());
    			
        		if (DAY.equals(request.getDateOption())) {
        			
            		return trafficVolumePrdRepository.findTrafficVolumeSumMaxUtcMinusQueryByDay(request.getSensorId(), request.getStartDate(), request.getEndDate(), formatter.getFormatPostgreSql(request.getDateOption()), request.getInterval(), utcLag);    			
        			
        		} else if (HOURS24.equals(request.getDateOption())) {
        			
            		return trafficVolumePrdRepository.findTrafficVolumeSumMaxUtcMinusQueryBy24Hours(request.getSensorId(), request.getStartDate(), request.getEndDate(), request.getDateOption(), request.getInterval(), utcLag);
        			
        		}
        		
        		return trafficVolumePrdRepository.findTrafficVolumeSumMaxUtcMinusQuery(request.getSensorId(), request.getStartDate(), request.getEndDate(), request.getDateOption(), formatter.getFormatPostgreSql(request.getDateOption()), request.getInterval(), utcLag);
    		
    		} else {
        		// If Local Time is UTC + Correction time (BKK.TYO,Denmark) 
    			if (DAY.equals(request.getDateOption())) {
        			
            		return trafficVolumePrdRepository.findTrafficVolumeSumMaxUtcPlusQueryByDay(request.getSensorId(), request.getStartDate(), request.getEndDate(), formatter.getFormatPostgreSql(request.getDateOption()), request.getInterval(), utcLag);    			
        			
        		} else if (HOURS24.equals(request.getDateOption())) {
        			
            		return trafficVolumePrdRepository.findTrafficVolumeSumMaxUtcPlusQueryBy24Hours(request.getSensorId(), request.getStartDate(), request.getEndDate(), request.getDateOption(), request.getInterval(), utcLag);
        			
        		}

    			return trafficVolumePrdRepository.findTrafficVolumeSumMaxUtcPlusQuery(request.getSensorId(), request.getStartDate(), request.getEndDate(), request.getDateOption(), formatter.getFormatPostgreSql(request.getDateOption()), request.getInterval(), utcLag);
    			
    		}
    		
    	} catch (MyResourceNotFoundException | ParseException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}
    	    	
    }
    
    @Transactional
    public List<TrafficVolumePrdResponse> findLatestRecord(DataAnaysisRequest request) {

    	try {
    		
    		if(request.getInterval() < 0) {
    			
    			request.setInterval(-request.getInterval());

    			return trafficVolumePrdRepository.findTrafficVolumeSumLatestUtcMinusQuery(request.getSensorId(), request.getStartDate(), request.getDateOption(), request.getInterval());

    		} else {

        		return trafficVolumePrdRepository.findTrafficVolumeSumLatestUtcPlusQuery(request.getSensorId(), request.getStartDate(), request.getDateOption(), request.getInterval());

    		}
    			
    	} catch (MyResourceNotFoundException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}
    	    	
    }

}