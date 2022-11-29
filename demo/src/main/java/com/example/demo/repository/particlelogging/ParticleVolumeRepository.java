package com.example.demo.repository.particlelogging;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.particlelogging.AllViewEntity;

@Repository
public interface ParticleVolumeRepository extends JpaRepository<AllViewEntity, Long> {
	
    @Query(value="SELECT"
    		+ " new AllViewEntity("
    		+ " ROUND(sv.currCharge,2) as currCharge,"
    		+ " ROUND(sv.extpwrval,2) as extpwrval,"
    		+ " ROUND(sv.voltage,2) as voltage,"
    		+ " DATE_FORMAT(TIMESTAMPADD(HOUR, ?4, sv.eventTime), ?5) as eventTime"
    		+ ")"
    		+ " FROM AllViewEntity sv "
    		+ " WHERE sv.deviceId = ?1 AND sv.eventTime BETWEEN TIMESTAMPADD(HOUR, -?4-?6, ?2) AND TIMESTAMPADD(HOUR, -?4-?6, ?3) "
    		+ " GROUP BY eventTime")
    List<AllViewEntity>findPowerValueQuery(String deviceId, Date startDate, Date endDate, int interval, String format, String utcLag);

	
}