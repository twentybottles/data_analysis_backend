package com.example.demo.repository.enviropi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.enviropi.AllSensorViewEntity;

@Repository
public interface AllSensorViewEntityRepository extends JpaRepository<AllSensorViewEntity, Long> {

	@Query(value="SELECT"
    		+ " new AllSensorViewEntity("
    		+ " ROUND(sv.decibel,2) as decibel,"
    		+ " ROUND(sv.ozone,2) as ozone,"
    		+ " ROUND(sv.particles,2) as particles,"
    		+ " ROUND(sv.co2,2) as co2,"
    		+ " ROUND(sv.humidity,2) as humidity,"
    		+ " ROUND(sv.temperature,2) as temperature,"
    		+ " ROUND(sv.mcPm2_5,2) as mcPm2_5,"
    		+ " ROUND(MAX(sv.decibel),2) as maxDecibel,"
    		+ " ROUND(MAX(sv.ozone),2) as maxOzone,"
    		+ " ROUND(MAX(sv.particles),2) as maxParticles,"
    		+ " ROUND(MAX(sv.co2),2) as maxCo2,"
    		+ " ROUND(MAX(sv.humidity),2) as maxHumidity,"
    		+ " ROUND(MAX(sv.temperature),2) as maxTemperature,"
    		+ " ROUND(MAX(sv.mcPm2_5),2) as maxMcPm2_5,"
    		+ " DATE_FORMAT(TIMESTAMPADD(HOUR, ?4, sv.eventTime), ?5) as eventTime,"
    		+ " '?7' as format"
    		+ ")"
    		+ " FROM AllSensorViewEntity sv "
    		+ " WHERE sv.deviceId = ?1 AND sv.eventTime BETWEEN TIMESTAMPADD(HOUR, -?4-?6, ?2) AND TIMESTAMPADD(HOUR, -?4-?6, ?3) "
    		+ " GROUP BY eventTime")
    List<AllSensorViewEntity>findByAllSensorViewQuery(String deviceId, String startDate, String endDate, int interval, String dateOption, String utcLag, String format);    

	@Query(value="SELECT"
    		+ " new AllSensorViewEntity("
    		+ " ROUND(sv.decibel,2) as decibel,"
    		+ " ROUND(sv.ozone,2) as ozone,"
    		+ " ROUND(sv.particles,2) as particles,"
    		+ " ROUND(sv.co2,2) as co2,"
    		+ " ROUND(sv.humidity,2) as humidity,"
    		+ " ROUND(sv.temperature,2) as temperature,"
    		+ " ROUND(sv.mcPm2_5,2) as mcPm2_5,"
    		+ " ROUND(MAX(sv.decibel),2) as maxDecibel,"
    		+ " ROUND(MAX(sv.ozone),2) as maxOzone,"
    		+ " ROUND(MAX(sv.particles),2) as maxParticles,"
    		+ " ROUND(MAX(sv.co2),2) as maxco2,"
    		+ " ROUND(MAX(sv.humidity),2) as maxHumidity,"
    		+ " ROUND(MAX(sv.temperature),2) as maxTemperature,"
    		+ " ROUND(MAX(sv.mcPm2_5),2) as maxMcPm2_5,"
    		+ " DATE_FORMAT(TIMESTAMPADD(HOUR, ?3, sv.eventTime), ?4) as eventTime,"
    		+ " '?5' as format"
    		+ ")"
    		+ " FROM AllSensorViewEntity sv "
    		+ " WHERE sv.deviceId = ?1 AND sv.eventTime > ?2 "
    		+ " GROUP BY eventTime")
    List<AllSensorViewEntity>findByAllSensorViewLatestQuery(String deviceId, String startDate, int interval, String dateOption, String format);    
	
	@Query(value="SELECT"
    		+ " new AllSensorViewEntity("
    		+ " ROUND(AVG(sv.decibel),2) as maxDecibel,"
    		+ " ROUND(AVG(sv.ozone),2) as maxOzone,"
    		+ " ROUND(AVG(sv.particles),2) as maxParticles,"
    		+ " ROUND(AVG(sv.co2),2) as maxco2,"
    		+ " ROUND(AVG(sv.humidity),2) as maxHumidity,"
    		+ " ROUND(AVG(sv.temperature),2) as maxTemperature,"
    		+ " ROUND(AVG(sv.mcPm2_5),2) as maxMcPm2_5,"
    		+ " DATE_FORMAT(TIMESTAMPADD(HOUR, ?4, sv.eventTime), ?5) as eventTime,"
    		+ " '?7' as format"
    		+ ")"
    		+ " FROM AllSensorViewEntity sv "
    		+ " WHERE sv.deviceId = ?1 AND sv.eventTime BETWEEN TIMESTAMPADD(HOUR, -?4-?6, ?2) AND TIMESTAMPADD(HOUR, -?4-?6, ?3) "
    		+ " GROUP BY eventTime")
    List<AllSensorViewEntity>findAutoCorrelationAllSensorViewQuery(String deviceId, String startDate, String endDate, int interval, String dateOption, String utcLag, String format);    
	
    @Query(value="SELECT"
    		+ " new AllSensorViewEntity("
    		+ " ROUND(AVG(sv.decibel),2) as decibel,"
    		+ " ROUND(AVG(sv.ozone),2) as ozone,"
    		+ " ROUND(AVG(sv.particles),2) as particles,"
    		+ " ROUND(AVG(sv.co2),2) as co2,"
    		+ " ROUND(AVG(sv.humidity),2) as humidity,"
    		+ " ROUND(AVG(sv.temperature),2) as temperature,"
    		+ " ROUND(AVG(sv.mcPm2_5),2) as mcPm2_5,"
    		+ " DATE_FORMAT(from_unixtime(ceiling(unix_timestamp(TIMESTAMPADD(HOUR, ?5, sv.eventTime)) / (?6 * 60)) * (?6 * 60)), ?4) as eventTime,"
			+ " '?8' as format"
    		+ ")"
    		+ " FROM AllSensorViewEntity sv "
    		+ " WHERE sv.deviceId = ?1 AND sv.eventTime BETWEEN TIMESTAMPADD(HOUR, -?5-?7, ?2) AND TIMESTAMPADD(HOUR, -?5-?7, ?3) "
    		+ " GROUP BY eventTime")
    List<AllSensorViewEntity>findCorrelationAllSensorViewQuery(String deviceId, String startDate, String endDate, String dateOption, int interval, int minutes, String utcLag, String format);

}