package com.example.demo.service;

import static com.example.demo.common.WebConst.DEVICE_CAR1;
import static com.example.demo.common.WebConst.DEVICE_CAR2;
import static com.example.demo.common.WebConst.DEVICE_CAR3;
import static com.example.demo.common.WebConst.DEVICE_CAR4;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.tracking.TrackingObjectsEntity;
import com.example.demo.exception.MyResourceNotFoundException;
import com.example.demo.repository.tracking.TrackingObjectsRepository;

@Service
public class RegisterTrackingObjectsService {

    @Autowired
    private TrackingObjectsRepository trackingObjectsRepository;
    
    @Transactional
    public void save() {
    	
    	try {
 
    		List<TrackingObjectsEntity> trackingObjectsEntityList = trackingObjectsRepository.findByLatestLocationLimit1Query();
    		
    		for(TrackingObjectsEntity trackingObjectsEntity : trackingObjectsEntityList) {
    			
    			trackingObjectsEntity.setId(null);
    			
    	    	switch (trackingObjectsEntity.getName()) {

    			  case DEVICE_CAR1:
    				  trackingObjectsEntity.setLongitude(Double.sum(trackingObjectsEntity.getLongitude(), 0.0001));
    			    break;
    			  case DEVICE_CAR2:
    				  trackingObjectsEntity.setLongitude(Double.sum(trackingObjectsEntity.getLongitude(), 0.00003));
    			    break;
    	    	  case DEVICE_CAR3:
    	    		  trackingObjectsEntity.setLatitude(Double.sum(trackingObjectsEntity.getLatitude(), 0.00007));
    	    	    break;
    	    	  case DEVICE_CAR4:
    	    		  trackingObjectsEntity.setLatitude(Double.sum(trackingObjectsEntity.getLatitude(), -0.0001));
    	    	    break;
    	    	}
    	    	
    			trackingObjectsEntity.setCreated_at(null);
    			trackingObjectsEntity.setUpdated_at(null);
    				
    		}

    		trackingObjectsRepository.saveAll(trackingObjectsEntityList);
    		    		
    	} catch (MyResourceNotFoundException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}
    	    	
    }

}