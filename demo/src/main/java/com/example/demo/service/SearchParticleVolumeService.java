package com.example.demo.service;

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
import com.example.demo.entity.particlelogging.AllViewEntity;
import com.example.demo.exception.MyResourceNotFoundException;
import com.example.demo.repository.particlelogging.ParticleVolumeRepository;

@Service
public class SearchParticleVolumeService {

    @Autowired
    private ParticleVolumeRepository particleVolumelRepository;

    @Transactional
    public List<AllViewEntity> find(DataAnaysisRequest request) {
    	
		Formatter formatter = new Formatter();
		
		Utilities util = new Utilities();

    	try {

    		String utcLag = util.getUtcLag(request.getStartDate());

    		return particleVolumelRepository.findPowerValueQuery(
    				request.getDeviceId(), 
    				request.getStartDate(), 
    				request.getEndDate(),
    				request.getInterval(),
    				formatter.getFormatMySql(request.getDateOption()),
    				utcLag
    				);
    		    		
    	} catch (MyResourceNotFoundException | ParseException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}
    	    	
    }

}