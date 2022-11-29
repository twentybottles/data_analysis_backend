package com.example.demo.repository.tracking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.tracking.TrackingObjectsEntity;

@Repository
public interface TrackingObjectsRepository extends JpaRepository<TrackingObjectsEntity, Long> {	
	
    @Query(value="SELECT * FROM tracking_objects where created_at ="
    		+ " (SELECT MAX(created_at) FROM tracking_objects) group by name", nativeQuery = true)
    List<TrackingObjectsEntity> findByLatestLocationLimit1Query();
    
    @Query(value="SELECT t.latitude, t.longitude FROM tracking_objects t where name = ? ORDER BY t.name, t.created_at DESC", nativeQuery = true)
    List<Object> findByLatestLocationQuery(String device);

}