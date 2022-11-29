package com.example.demo.repository.atlas;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.atlas.TrafficVolumeResponse;

@Repository
public interface TrafficVolumeTmsRepository extends JpaRepository<TrafficVolumeResponse, Long> {

    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " to_timestamp(cast(lr.packetTs + cast(CONCAT(?6,':00:00') as time) as text),?5) as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) - cast(CONCAT(?6,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) - cast(CONCAT(?6,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumMaxUtcPlusQuery(long sensorId, Date startDate, Date endDate, String dateOption, String format, int interval, String utcLag);

    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " AVG(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " AVG(lr.class1) as maxBicycle,"
    		+ " AVG(lr.class2) as maxBus,"
    		+ " AVG(lr.class3) as maxCar,"
    		+ " AVG(lr.class4) as maxMotorcycle,"
    		+ " AVG(lr.class5) as maxSUV,"
    		+ " AVG(lr.class6) as maxTruck,"
    		+ " AVG(lr.class7) as maxTruckBigRig,"
//    		+ " AVG(lr.class8) as maxTuktuk,"
    		+ " AVG(lr.class9) as maxVan,"
    		+ " AVG(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " to_timestamp(cast(lr.packetTs + cast(CONCAT(?6,':00:00') as time) as text),?5) as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) - cast(CONCAT(?6,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) - cast(CONCAT(?6,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumAvgUtcPlusQuery(long sensorId, Date startDate, Date endDate, String dateOption, String format, int interval, String utcLag);

    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " lr.packetTs + cast(CONCAT(?4,':00:00') as time) as packetTs,"
    		+ " cast(?3 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs > ?2"
    		+ " GROUP BY packetTs"
    		+ " ORDER BY packetTs ASC")
    List<TrafficVolumeResponse> findTrafficVolumeSumLatestUtcPlusQuery(long sensorId, Date startDate, String dateOption, int interval);
        
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " cast(date_part('dow', lr.packetTs + cast(CONCAT(?5,':00:00') as time)) as text) as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) - cast(CONCAT(?5,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) - cast(CONCAT(?5,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumMaxUtcPlusQueryByDay(long sensorId, Date startDate, Date endDate, String dateOption, int interval, String utcLag);
    
    
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " CONCAT(SUBSTRING(cast(lr.packetTs + cast(CONCAT(?5,':00:00') as time) as text),12,2),':00') as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) - cast(CONCAT(?5,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) - cast(CONCAT(?5,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumMaxUtcPlusQueryBy24Hours(long sensorId, Date startDate, Date endDate, String dateOption, int interval, String utcLag);
    
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " AVG(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as averageVehicles,"
    		+ " AVG(lr.class1) as averageBicycle,"
    		+ " AVG(lr.class2) as averageBus,"
    		+ " AVG(lr.class3) as averageCar,"
    		+ " AVG(lr.class4) as averageMotorcycle,"
    		+ " AVG(lr.class5) as averageSUV,"
    		+ " AVG(lr.class6) as averageTruck,"
    		+ " AVG(lr.class7) as averageTruckBigRig,"
//    		+ " AVG(lr.class8) as averageTuktuk,"
    		+ " AVG(lr.class9) as averageVan,"
    		+ " AVG(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " concat(SUBSTRING(cast(lr.packetTs + cast(CONCAT(?5,':00:00') as time) as text),1,14), TO_CHAR(TRUNC(EXTRACT('minute' FROM lr.packetTs) / ?6 ) * ?6, 'FM09')) as packetTs, "
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) - cast(CONCAT(?5,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) - cast(CONCAT(?5,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findCorrelationTrafficUtcPlusQuery(long sensorId, Date startDate, Date endDate, String dateOption, int interval, int minutes, String utcLag);

    //////////////// UTC Minus ////////////////////
    
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " to_timestamp(cast(lr.packetTs - cast(CONCAT(?6,':00:00') as time) as text),?5) as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) + cast(CONCAT(?6,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) + cast(CONCAT(?6,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumMaxUtcMinusQuery(long sensorId, Date startDate, Date endDate, String dateOption, String format, int interval, String utcLag);
   
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " AVG(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " AVG(lr.class1) as maxBicycle,"
    		+ " AVG(lr.class2) as maxBus,"
    		+ " AVG(lr.class3) as maxCar,"
    		+ " AVG(lr.class4) as maxMotorcycle,"
    		+ " AVG(lr.class5) as maxSUV,"
    		+ " AVG(lr.class6) as maxTruck,"
    		+ " AVG(lr.class7) as maxTruckBigRig,"
//    		+ " AVG(lr.class8) as maxTuktuk,"
    		+ " AVG(lr.class9) as maxVan,"
    		+ " AVG(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " to_timestamp(cast(lr.packetTs - cast(CONCAT(?6,':00:00') as time) as text),?5) as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) + cast(CONCAT(?6,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) + cast(CONCAT(?6,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumAvgUtcMinusQuery(long sensorId, Date startDate, Date endDate, String dateOption, String format, int interval, String utcLag);

    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " cast(lr.packetTs - cast(CONCAT(?4,':00:00') as time) as text) as packetTs,"
    		+ " cast(?3 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs > ?2"
    		+ " GROUP BY packetTs"
    		+ " ORDER BY packetTs ASC")
    List<TrafficVolumeResponse> findTrafficVolumeSumLatestUtcMinusQuery(long sensorId, Date startDate, String dateOption, int interval);
    
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " cast(date_part('dow', lr.packetTs - cast(CONCAT(?5,':00:00') as time)) as text) as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) + cast(CONCAT(?5,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) + cast(CONCAT(?5,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumMaxUtcMinusQueryByDay(long sensorId, Date startDate, Date endDate, String dateOption, int interval, String utcLag);
    
    
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " MAX(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as maxVehicles,"
    		+ " MAX(lr.class1) as maxBicycle,"
    		+ " MAX(lr.class2) as maxBus,"
    		+ " MAX(lr.class3) as maxCar,"
    		+ " MAX(lr.class4) as maxMotorcycle,"
    		+ " MAX(lr.class5) as maxSUV,"
    		+ " MAX(lr.class6) as maxTruck,"
    		+ " MAX(lr.class7) as maxTruckBigRig,"
//    		+ " MAX(lr.class8) as maxTuktuk,"
    		+ " MAX(lr.class9) as maxVan,"
    		+ " MAX(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " CONCAT(SUBSTRING(cast(lr.packetTs - cast(CONCAT(?5,':00:00') as time) as text),12,2),':00') as packetTs,"
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) + cast(CONCAT(?5,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?6,':00:00') as time) + cast(CONCAT(?5,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findTrafficVolumeSumMaxUtcMinusQueryBy24Hours(long sensorId, Date startDate, Date endDate, String dateOption, int interval, String utcLag);
    
    @Query(value="SELECT"
    		+ " new TrafficVolumeResponse("
    		+ " SUM(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as totalVehicles,"
    		+ " SUM(lr.class1) as totalBicycle,"
    		+ " SUM(lr.class2) as totalBus,"
    		+ " SUM(lr.class3) as totalCar,"
    		+ " SUM(lr.class4 + lr.class8) as totalMotorcycle,"
    		+ " SUM(lr.class5) as totalSUV,"
    		+ " SUM(lr.class6) as totalTruck,"
    		+ " SUM(lr.class7) as totalTruckBigRig,"
//    		+ " SUM(lr.class8) as totalTuktuk,"
    		+ " SUM(lr.class9) as totalVan,"
    		+ " SUM(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " AVG(lr.class1 + lr.class2 + lr.class3 + lr.class4 + lr.class5 + lr.class6 + lr.class7 + lr.class8 + lr.class9) as averageVehicles,"
    		+ " AVG(lr.class1) as averageBicycle,"
    		+ " AVG(lr.class2) as averageBus,"
    		+ " AVG(lr.class3) as averageCar,"
    		+ " AVG(lr.class4) as averageMotorcycle,"
    		+ " AVG(lr.class5) as averageSUV,"
    		+ " AVG(lr.class6) as averageTruck,"
    		+ " AVG(lr.class7) as averageTruckBigRig,"
//    		+ " AVG(lr.class8) as averageTuktuk,"
    		+ " AVG(lr.class9) as averageVan,"
    		+ " AVG(lr.class3 + lr.class5 + lr.class9) as totalCarSUVVan,"
    		+ " concat(SUBSTRING(cast(lr.packetTs - cast(CONCAT(?5,':00:00') as time) as text),1,14), TO_CHAR(TRUNC(EXTRACT('minute' FROM lr.packetTs) / ?6 ) * ?6, 'FM09')) as packetTs, "
    		+ " cast(?4 as text) as dataOption"
    		+ ")"
    		+ " FROM LaneRecordsEntity lr "
    		+ " INNER JOIN lr.lanesEntity l"
    		+ " INNER JOIN l.sensorsEntity s"
    		+ " WHERE s.id = ?1 AND lr.packetTs BETWEEN to_timestamp(?2, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) + cast(CONCAT(?5,':00:00') as time) AND to_timestamp(?3, 'YYYY-MM-DD hh24:MI') - cast(CONCAT(?7,':00:00') as time) + cast(CONCAT(?5,':00:00') as time)"
    		+ " GROUP BY packetTs")
    List<TrafficVolumeResponse> findCorrelationTrafficUtcMinusQuery(long sensorId, Date startDate, Date endDate, String dateOption, int interval, int minutes, String utcLag);
    
}