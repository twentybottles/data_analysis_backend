package com.example.demo.service;

import static com.example.demo.common.WebConst.X_AXIS;
import static com.example.demo.common.WebConst.Y_AXIS;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.common.Formatter;
import com.example.demo.common.Utilities;
import com.example.demo.entity.atlas.TrafficVolumeResponse;
import com.example.demo.entity.common.TrafficAutoCorrelationResponse;
import com.example.demo.entity.common.CorrelationResponse;
import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.entity.common.EnvironmentAutoCorrelationResponse;
import com.example.demo.entity.enviropi.AllSensorViewEntity;
import com.example.demo.entity.prd.TrafficVolumePrdResponse;
import com.example.demo.exception.MyResourceNotFoundException;
import com.example.demo.repository.atlas.TrafficVolumeTmsRepository;
import com.example.demo.repository.enviropi.AllSensorViewEntityRepository;

@Service
public class SearchCorrelationTmsService {
	
    @Autowired
    private TrafficVolumeTmsRepository trafficVolumeTmsRepository;

    @Autowired
    private AllSensorViewEntityRepository envDataViewJSTViewRepository;

    public CorrelationResponse normal(DataAnaysisRequest request) {
    	
		Formatter formatter = new Formatter();		
		Utilities util = new Utilities();
		
		List<TrafficVolumeResponse> trafficVolumeResponseList = null;

	    	try {
	    		
	    		String utcLag = util.getUtcLag(request.getStartDate());

	    		if(request.getInterval() < 0) {

	    			request.setInterval(-request.getInterval());
	    			
		    		trafficVolumeResponseList = trafficVolumeTmsRepository.findCorrelationTrafficUtcMinusQuery(
		    				request.getSensorId(), 
		    				request.getStartDate(), 
		    				request.getEndDate(), 
		    				formatter.getFormatPostgreSql(request.getDateOption()), 
		    				request.getInterval(),
		    				request.getMinutes(),
		    				utcLag
		    				);
	    		
	    		} else {

		    		trafficVolumeResponseList = trafficVolumeTmsRepository.findCorrelationTrafficUtcPlusQuery(
		    				request.getSensorId(), 
		    				request.getStartDate(), 
		    				request.getEndDate(), 
		    				formatter.getFormatPostgreSql(request.getDateOption()), 
		    				request.getInterval(),
		    				request.getMinutes(),
		    				utcLag
		    				);

	    		}
	    		
	    		List<AllSensorViewEntity> allSensorViewEntityList = envDataViewJSTViewRepository.findCorrelationAllSensorViewQuery(
	    				request.getDeviceId(), 
	    				formatter.convertDateFormat(request.getStartDate(), request.getDateOption()), 
	    				formatter.convertDateFormat(request.getEndDate(), request.getDateOption()), 
	    				formatter.getFormatMySql(request.getDateOption()),
	    				request.getInterval(),
	    				request.getMinutes(),
	    				utcLag,
	    				formatter.getFormatMySql(request.getDateOption())
	    				);			
    		
//	    		util.exportTrafficTmsCsv(trafficVolumeResponseList);
//	    		util.exportEnvironmentCsv(allSensorViewEntityList);
	
    		Map<String, List<double[]>> corrListMap = getCorrelationArrayList(trafficVolumeResponseList, allSensorViewEntityList);

    		return getCorrelation(corrListMap);
    		
    	} catch (MyResourceNotFoundException | ParseException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}

	}    


    private Map<String, List<double[]>> getCorrelationArrayList(List<TrafficVolumeResponse> trafficVolumeResponseList, List<AllSensorViewEntity> allSensorViewEntityList) throws ParseException {
    	
    	List<Double> bicycleList = new ArrayList<>();
    	List<Double> busList = new ArrayList<>();
    	List<Double> carList = new ArrayList<>();
    	List<Double> motorcycleTuktukList = new ArrayList<>();
    	List<Double> suvList = new ArrayList<>();
    	List<Double> truckList = new ArrayList<>();
    	List<Double> truckBigRigList = new ArrayList<>();
//    	List<Double> tuktukList = new ArrayList<>();
    	List<Double> vanList = new ArrayList<>();
    	List<Double> decibelList = new ArrayList<>();
    	List<Double> ozoneList = new ArrayList<>();
    	List<Double> particlesList = new ArrayList<>();
    	List<Double> co2List = new ArrayList<>();
    	List<Double> humidityList = new ArrayList<>();
    	List<Double> temperatureList = new ArrayList<>();
    	List<Double> mcPm2_5List = new ArrayList<>();
    	
        for(TrafficVolumeResponse trafficVolumeResponse : trafficVolumeResponseList){
        	
            for(AllSensorViewEntity allSensorViewEntity : allSensorViewEntityList){
            	            	
            	if(allSensorViewEntity.getEventTime().compareTo(trafficVolumeResponse.getPacketTs()) == 0) {
                	
                	bicycleList.add(new Double(trafficVolumeResponse.getTotalBicycle() == null ? 0 : trafficVolumeResponse.getTotalBicycle()));
                	busList.add(new Double(trafficVolumeResponse.getTotalBus() == null ? 0 : trafficVolumeResponse.getTotalBus()));
                	carList.add(new Double(trafficVolumeResponse.getTotalCar() == null ? 0 : trafficVolumeResponse.getTotalCar()));
                	motorcycleTuktukList.add(new Double(trafficVolumeResponse.getTotalMotorcycleTuktuk() == null ? 0 : trafficVolumeResponse.getTotalMotorcycleTuktuk()));
                	suvList.add(new Double(trafficVolumeResponse.getTotalSUV() == null ? 0 : trafficVolumeResponse.getTotalSUV()));
                	truckList.add(new Double(trafficVolumeResponse.getTotalTruck() == null ? 0 : trafficVolumeResponse.getTotalVan()));
                	truckBigRigList.add(new Double(trafficVolumeResponse.getTotalTruckBigRig() == null ? 0 : trafficVolumeResponse.getTotalTruck()));
//                	tuktukList.add(new Double(trafficVolumeResponse.getTotalTuktuk() == null ? 0 : trafficVolumeResponse.getTotalTuktuk()));
                	vanList.add(new Double(trafficVolumeResponse.getTotalVan() == null ? 0 : trafficVolumeResponse.getTotalVan()));
                	decibelList.add(new Double(allSensorViewEntity.getDecibel() == null ? 0 : allSensorViewEntity.getDecibel()));
                	ozoneList.add(new Double(allSensorViewEntity.getOzone() == null ? 0 : allSensorViewEntity.getOzone()));
//                	particlesList.add(new Double(allSensorViewEntity.getParticles() == null ? 0 : allSensorViewEntity.getParticles()));
                	co2List.add(new Double(allSensorViewEntity.getCo2() == null ? 0 : allSensorViewEntity.getCo2()));
                	humidityList.add(new Double(allSensorViewEntity.getHumidity() == null ? 0 : allSensorViewEntity.getHumidity()));
                	temperatureList.add(new Double(allSensorViewEntity.getTemperature() == null ? 0 : allSensorViewEntity.getTemperature()));
                	mcPm2_5List.add(new Double(allSensorViewEntity.getMcPm2_5() == null ? 0 : allSensorViewEntity.getMcPm2_5()));
               	                	
                }
            	
            	if(allSensorViewEntity.getEventTime().compareTo(trafficVolumeResponse.getPacketTs()) == 1) {
                	
                	continue;
                
                }

            }
        
        }
        
		Map<String, List<double[]>> corrListMap = new HashMap<>(); 
		List<double[]> yAxisArrayList = new ArrayList<double[]>(); 
		List<double[]> xAxisArrayList = new ArrayList<double[]>(); 
		
		yAxisArrayList.add(decibelList.stream().mapToDouble(Double::doubleValue).toArray());
		yAxisArrayList.add(ozoneList.stream().mapToDouble(Double::doubleValue).toArray());
//		yAxisArrayList.add(particlesList.stream().mapToDouble(Double::doubleValue).toArray());
		yAxisArrayList.add(co2List.stream().mapToDouble(Double::doubleValue).toArray());
		yAxisArrayList.add(humidityList.stream().mapToDouble(Double::doubleValue).toArray());
		yAxisArrayList.add(temperatureList.stream().mapToDouble(Double::doubleValue).toArray());
		yAxisArrayList.add(mcPm2_5List.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(bicycleList.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(busList.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(carList.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(motorcycleTuktukList.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(suvList.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(truckList.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(truckBigRigList.stream().mapToDouble(Double::doubleValue).toArray());
//		xAxisArrayList.add(tuktukList.stream().mapToDouble(Double::doubleValue).toArray());
		xAxisArrayList.add(vanList.stream().mapToDouble(Double::doubleValue).toArray());
		
		corrListMap.put(Y_AXIS, yAxisArrayList);
		corrListMap.put(X_AXIS, xAxisArrayList);
        
        return corrListMap;
    
    }

    private CorrelationResponse getCorrelation(Map<String, List<double[]>> corrListMap){
    	
    	List<double[]> yAxisArrayList = corrListMap.get(Y_AXIS);
    	List<double[]> xAxisArrayList = corrListMap.get(X_AXIS);
    	
        double[][] corrMap = new double[6][8];
    	
    	for(int y = 0; y < yAxisArrayList.size(); y++) {
    		
        	for(int x = 0; x < xAxisArrayList.size(); x++) {
        		
        		double corr = 0d;
        		
        		if(yAxisArrayList.get(y).length <= 1) {
        			
        			break;
        		
        		} else {

            		corr = new PearsonsCorrelation().correlation(yAxisArrayList.get(y), xAxisArrayList.get(x));
            		
            		if (Double.isNaN(corr)) {
            			
            			corr = 0d;
            		
            		}        			
        			
        		}

                BigDecimal bigDecimal = new BigDecimal(corr).setScale(2,RoundingMode.HALF_DOWN);

        		corrMap[y][x] = bigDecimal.doubleValue();
        		
        	}
	
    	}
    	
        CorrelationResponse correlationResponse = new CorrelationResponse();
    	
        correlationResponse.setCorrMap(corrMap);
    	
    	return correlationResponse;
    	
    }
    
    public List<Object> auto(DataAnaysisRequest request) {
    	
		Formatter formatter = new Formatter();		
		Utilities util = new Utilities();
		
		List<TrafficVolumeResponse> trafficVolumeResponseList = null;
		List<AllSensorViewEntity> allSensorViewEntityList = null;

    	try {
    		
    		String utcLag = util.getUtcLag(request.getStartDate());

    		if(request.getInterval() < 0) {

    			request.setInterval(-request.getInterval());
    			
	    		trafficVolumeResponseList = trafficVolumeTmsRepository.findTrafficVolumeSumAvgUtcMinusQuery(
	    				request.getSensorId(), 
	    				request.getStartDate(), 
	    				request.getEndDate(), 
	    				request.getDateOption(), 
	    				formatter.getFormatPostgreSql(request.getDateOption()), 
	    				request.getInterval(), 
	    				utcLag
	    				);
    		
    		} else {

	    		trafficVolumeResponseList = trafficVolumeTmsRepository.findTrafficVolumeSumAvgUtcPlusQuery(
	    				request.getSensorId(), 
	    				request.getStartDate(), 
	    				request.getEndDate(), 
	    				request.getDateOption(), 
	    				formatter.getFormatPostgreSql(request.getDateOption()), 
	    				request.getInterval(), 
	    				utcLag
	    				);

    		}
    		
    		allSensorViewEntityList = envDataViewJSTViewRepository.findAutoCorrelationAllSensorViewQuery(
    				request.getDeviceId(), 
    				formatter.convertDateFormat(request.getStartDate(), request.getDateOption()), 
    				formatter.convertDateFormat(request.getEndDate(), request.getDateOption()),     				
    				request.getInterval(),
    				formatter.getFormatMySql(request.getDateOption()),
    				utcLag,
    				formatter.getFormatMySql(request.getDateOption())
    				);	

    		
    	} catch (MyResourceNotFoundException | ParseException e) {

    		throw new ResponseStatusException(HttpStatus.NO_CONTENT, "failed find", e);

    	}
    	
    	List<Object> autoCorrList = new ArrayList<>();
    	List<TrafficAutoCorrelationResponse> trafficAutoCorrelationResponseList = new ArrayList<>();
    	List<EnvironmentAutoCorrelationResponse> environmentAutoCorrelationResponseList = new ArrayList<>();
    	TrafficAutoCorrelationResponse trafficAutoCorrelationResponse = new TrafficAutoCorrelationResponse();
    	EnvironmentAutoCorrelationResponse environmentAutoCorrelationResponse = new EnvironmentAutoCorrelationResponse();
    	
    	double initVehicles = 0;
    	double initBicycle = 0;
    	double initBus = 0;
    	double initCar = 0;
    	double initMotorcycleTuktuk = 0;
    	double initSuv = 0;
    	double initTruck = 0;
    	double initTruckBigRig = 0;
    	double initVan = 0;
    	double initCarSuvVan = 0;
    	double initDecibel = 0;
    	double initOzone = 0;
    	double initCo2 = 0;
    	double initHumidity = 0;
    	double initMcPm2_5 = 0;
    	double initTemperature = 0;
    	
    	for (TrafficVolumeResponse trafficVolumeResponse : trafficVolumeResponseList) {
    		
    		initVehicles += trafficVolumeResponse.getMaxVehicles();
    	    initBicycle += trafficVolumeResponse.getMaxBicycle();
    		initBus += trafficVolumeResponse.getMaxBus();
    		initCar += trafficVolumeResponse.getMaxCar();
    		initMotorcycleTuktuk += trafficVolumeResponse.getMaxMotorcycleTuktuk();
    		initSuv += trafficVolumeResponse.getMaxSUV();
    		initTruck += trafficVolumeResponse.getMaxTruck();
    		initTruckBigRig += trafficVolumeResponse.getMaxTruckBigRig();
    		initVan += trafficVolumeResponse.getMaxVan();
    		initCarSuvVan += trafficVolumeResponse.getMaxCarSUVVan();
    		
    	}
    	
    	for (AllSensorViewEntity allSensorViewEntity : allSensorViewEntityList) {

    		initDecibel += allSensorViewEntity.getDecibel();
    		initOzone += allSensorViewEntity.getOzone();
    		initCo2 += allSensorViewEntity.getCo2();
    		initHumidity += allSensorViewEntity.getHumidity();
    		initMcPm2_5 += allSensorViewEntity.getMcPm2_5();
    		initTemperature += allSensorViewEntity.getTemperature();

    	}
    	
    	trafficAutoCorrelationResponse.setLag(0);
    	trafficAutoCorrelationResponse.setVehicles(initVehicles == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setBicycle(initBicycle == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setBus(initBus == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setCar(initCar == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setMotorcycleTuktuk(initMotorcycleTuktuk == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setSuv(initSuv == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setTruck(initTruck == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setTruckBigRig(initTruckBigRig == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setVan(initVan == 0 ? 0 : (double) 1);
    	trafficAutoCorrelationResponse.setCarSuvVan(initCarSuvVan == 0 ? 0 : (double) 1);
    	environmentAutoCorrelationResponse.setLag(0);
    	environmentAutoCorrelationResponse.setDecibel(initDecibel == 0 ? 0 : (double) 1);
    	environmentAutoCorrelationResponse.setOzone(initOzone == 0 ? 0 : (double) 1);
    	environmentAutoCorrelationResponse.setCo2(initCo2 == 0 ? 0 : (double) 1);
    	environmentAutoCorrelationResponse.setHumidity(initHumidity == 0 ? 0 : (double) 1);
    	environmentAutoCorrelationResponse.setMcPm2_5(initMcPm2_5 == 0 ? 0 : (double) 1);
    	environmentAutoCorrelationResponse.setTemperature(initTemperature == 0 ? 0 : (double) 1);
    	
    	trafficAutoCorrelationResponseList.add(trafficAutoCorrelationResponse);
    	environmentAutoCorrelationResponseList.add(environmentAutoCorrelationResponse);

    	for (int i = 0; i < request.getLag(); i++) {
    		
    		trafficAutoCorrelationResponseList.add(trafficAutocorrelation(trafficVolumeResponseList, i));

    	}
    	
    	for (int j = 0; j < request.getLag(); j++) {
    		
    		environmentAutoCorrelationResponseList.add(environmentAutocorrelation(allSensorViewEntityList, j));

    	}
    	
    	autoCorrList.add(trafficAutoCorrelationResponseList);
    	autoCorrList.add(environmentAutoCorrelationResponseList);
    	
    	return autoCorrList;
    
    }
    
    private TrafficAutoCorrelationResponse trafficAutocorrelation(List<TrafficVolumeResponse> dataList, int lag) {
    	
    	// yの平均
    	double sumVehicles = 0;
    	double sumBicycle = 0;
    	double sumBus = 0;
    	double sumCar = 0;
    	double sumMotorcycleTuktuk = 0;
    	double sumSuv = 0;
    	double sumTruck = 0;
    	double sumTruckBigRig = 0;
    	double sumVan = 0;
    	double sumCarSUVVan = 0;
    	
    	for(TrafficVolumeResponse data : dataList) {
    	
    		sumVehicles += data.getMaxVehicles();
    		sumBicycle += data.getMaxBicycle();
    		sumBus += data.getMaxBus();
    		sumCar += data.getMaxCar();
    		sumMotorcycleTuktuk += data.getMaxMotorcycleTuktuk();
    		sumSuv += data.getMaxSUV();
    		sumTruck += data.getMaxTruck();
    		sumTruckBigRig += data.getMaxTruckBigRig();
    		sumVan += data.getMaxVan();
    		sumCarSUVVan += data.getMaxCarSUVVan();
    		
    	}
    	
    	double y_avgVehicles = sumVehicles / dataList.size();
    	double y_avgBicycle = sumBicycle / dataList.size();
    	double y_avgBus = sumBus / dataList.size();
    	double y_avgCar = sumCar / dataList.size();
    	double y_avgMotorcycleTuktuk = sumMotorcycleTuktuk / dataList.size();
    	double y_avgSuv = sumSuv / dataList.size();
    	double y_avgTruck = sumTruck / dataList.size();
    	double y_avgTruckBigRig = sumTruckBigRig / dataList.size();
    	double y_avgVan = sumVan / dataList.size();
    	double y_avgCarSUVVan = sumCarSUVVan / dataList.size();
    	
    	double sum_vehicles_covariance = 0;
    	double sum_bicycle_covariance = 0;
    	double sum_bus_covariance = 0;
    	double sum_car_covariance = 0;
    	double sum_motorcycleTuktuk_covariance = 0;
    	double sum_suv_covariance = 0;
    	double sum_truck_covariance = 0;
    	double sum_truckBigRig_covariance = 0;
    	double sum_van_covariance = 0;
    	double sum_carSuvVan_covariance = 0;
    	
    	// 分子の計算
    	for (int i = lag + 1; i < dataList.size(); i++) {
    		
        	double vehicles_covariance = (dataList.get(i).getMaxVehicles() - y_avgVehicles) * (dataList.get(i - (lag + 1)).getMaxVehicles() - y_avgVehicles);        	
        	double bicycle_covariance = (dataList.get(i).getMaxBicycle() - y_avgBicycle) * (dataList.get(i - (lag + 1)).getMaxBicycle() - y_avgBicycle);
        	double bus_covariance = (dataList.get(i).getMaxBus() - y_avgBus) * (dataList.get(i - (lag + 1)).getMaxBus() - y_avgBus);        	
        	double car_covariance = (dataList.get(i).getMaxCar() - y_avgCar) * (dataList.get(i - (lag + 1)).getMaxCar() - y_avgCar);  	
        	double motorcycleTuktuk_covariance = (dataList.get(i).getMaxMotorcycleTuktuk() - y_avgMotorcycleTuktuk) * (dataList.get(i - (lag + 1)).getMaxMotorcycleTuktuk() - y_avgMotorcycleTuktuk);   	
        	double suv_covariance = (dataList.get(i).getMaxSUV() - y_avgSuv) * (dataList.get(i - (lag + 1)).getMaxSUV() - y_avgSuv);
        	double truck_covariance = (dataList.get(i).getMaxTruck() - y_avgTruck) * (dataList.get(i - (lag + 1)).getMaxTruck() - y_avgTruck);
        	double truckBigRig_covariance = (dataList.get(i).getMaxTruckBigRig() - y_avgTruckBigRig) * (dataList.get(i - (lag + 1)).getMaxTruckBigRig() - y_avgTruckBigRig);
        	double van_covariance = (dataList.get(i).getMaxVan() - y_avgVan) * (dataList.get(i - (lag + 1)).getMaxVan() - y_avgVan);
        	double carSuvVan_covariance = (dataList.get(i).getMaxCarSUVVan() - y_avgCarSUVVan) * (dataList.get(i - (lag + 1)).getMaxCarSUVVan() - y_avgCarSUVVan);

        	sum_vehicles_covariance += vehicles_covariance;
        	sum_bicycle_covariance += bicycle_covariance;
        	sum_bus_covariance += bus_covariance;
        	sum_car_covariance += car_covariance;
        	sum_motorcycleTuktuk_covariance += motorcycleTuktuk_covariance;
        	sum_suv_covariance += suv_covariance;
        	sum_truck_covariance += truck_covariance;
        	sum_truckBigRig_covariance += truckBigRig_covariance;
        	sum_van_covariance += van_covariance;
        	sum_carSuvVan_covariance += carSuvVan_covariance;

    	}
    	
    	// 分母の計算
    	double sum_vehicles_denominator = 0;
    	double sum_bicycle_denominator = 0;
    	double sum_bus_denominator = 0;
    	double sum_car_denominator = 0;
    	double sum_motorcycleTuktuk_denominator = 0;
    	double sum_suv_denominator = 0;
    	double sum_truck_denominator = 0;
    	double sum_truckBigRig_denominator = 0;
    	double sum_van_denominator = 0;
    	double sum_carSuvVan_denominator = 0;

    	for (int j = 0; j < dataList.size(); j++) {
    		
        	double vehicles_denominator =  Math.pow((dataList.get(j).getMaxVehicles() - y_avgVehicles), 2);
        	double bicycle_denominator =  Math.pow((dataList.get(j).getMaxBicycle() - y_avgBicycle), 2);
        	double bus_denominator =  Math.pow((dataList.get(j).getMaxBus() - y_avgBus), 2);
        	double car_denominator =  Math.pow((dataList.get(j).getMaxCar() - y_avgCar), 2);
        	double motorcycleTuktuk_denominator =  Math.pow((dataList.get(j).getMaxMotorcycleTuktuk() - y_avgMotorcycleTuktuk), 2);
        	double suv_denominator =  Math.pow((dataList.get(j).getMaxSUV() - y_avgSuv), 2);
        	double truck_denominator =  Math.pow((dataList.get(j).getMaxTruck() - y_avgTruck), 2);
        	double truckBigRig_denominator =  Math.pow((dataList.get(j).getMaxTruckBigRig() - y_avgTruckBigRig), 2);
        	double van_denominator =  Math.pow((dataList.get(j).getMaxVan() - y_avgVan), 2);
        	double carSuvVan_denominator =  Math.pow((dataList.get(j).getMaxCarSUVVan() - y_avgCarSUVVan), 2);

        	sum_vehicles_denominator += vehicles_denominator;
        	sum_bicycle_denominator += bicycle_denominator;
        	sum_bus_denominator += bus_denominator;
        	sum_car_denominator += car_denominator;
        	sum_motorcycleTuktuk_denominator += motorcycleTuktuk_denominator;
        	sum_suv_denominator += suv_denominator;
        	sum_truck_denominator += truck_denominator;
        	sum_truckBigRig_denominator += truckBigRig_denominator;
        	sum_van_denominator += van_denominator;
        	sum_carSuvVan_denominator += carSuvVan_denominator;

    	}
    	    	
    	TrafficAutoCorrelationResponse trafficAutoCorrelationResponse = new TrafficAutoCorrelationResponse();
   
    	trafficAutoCorrelationResponse.setLag(lag + 1);
    	trafficAutoCorrelationResponse.setVehicles(Double.isNaN(sum_vehicles_covariance / sum_vehicles_denominator) ? 0 : sum_vehicles_covariance / sum_vehicles_denominator);
    	trafficAutoCorrelationResponse.setBicycle(Double.isNaN(sum_bicycle_covariance / sum_bicycle_denominator) ? 0 : sum_bicycle_covariance / sum_bicycle_denominator);
    	trafficAutoCorrelationResponse.setBus(Double.isNaN(sum_bus_covariance / sum_bus_denominator) ? 0 : sum_bus_covariance / sum_bus_denominator);
    	trafficAutoCorrelationResponse.setCar(Double.isNaN(sum_car_covariance / sum_car_denominator) ? 0 : sum_car_covariance / sum_car_denominator);
    	trafficAutoCorrelationResponse.setMotorcycleTuktuk(Double.isNaN(sum_motorcycleTuktuk_covariance / sum_motorcycleTuktuk_denominator) ? 0 : sum_motorcycleTuktuk_covariance / sum_motorcycleTuktuk_denominator);
    	trafficAutoCorrelationResponse.setSuv(Double.isNaN(sum_suv_covariance / sum_suv_denominator) ? 0 : sum_suv_covariance / sum_suv_denominator);
    	trafficAutoCorrelationResponse.setTruck(Double.isNaN(sum_truck_covariance / sum_truck_denominator) ? 0 : sum_truck_covariance / sum_truck_denominator);
    	trafficAutoCorrelationResponse.setTruckBigRig(Double.isNaN(sum_truckBigRig_covariance / sum_truckBigRig_denominator) ? 0 : sum_truckBigRig_covariance / sum_truckBigRig_denominator);
    	trafficAutoCorrelationResponse.setVan(Double.isNaN(sum_van_covariance / sum_van_denominator) ? 0 : sum_van_covariance / sum_van_denominator);
    	trafficAutoCorrelationResponse.setCarSuvVan(Double.isNaN(sum_carSuvVan_covariance / sum_carSuvVan_denominator) ? 0 : sum_carSuvVan_covariance / sum_carSuvVan_denominator);
    	
    	return trafficAutoCorrelationResponse;
    	
    }
    
    private EnvironmentAutoCorrelationResponse environmentAutocorrelation(List<AllSensorViewEntity> dataList, int lag) {
    	
    	// yの平均
    	double sumDecibel = 0;
    	double sumOzone = 0;
    	double sumCo2 = 0;
//    	double sumParticles = 0;
    	double sumHumidity = 0;
    	double sumTemperature = 0;
    	double sumPm2_5 = 0;
    	
    	for(AllSensorViewEntity data : dataList) {
    	
    		sumDecibel += data.getDecibel();
    		sumOzone += data.getOzone();
    		sumCo2 += data.getCo2();
//    		sumParticles += data.getParticles();
    		sumHumidity += data.getHumidity();
    		sumTemperature += data.getTemperature();
    		sumPm2_5 += data.getMcPm2_5();
    		
    	}
    	
    	double y_avgDecibel = sumDecibel / dataList.size();
    	double y_avgOzone = sumOzone / dataList.size();
    	double y_avgCo2 = sumCo2 / dataList.size();
//    	double y_avgParticles = sumParticles / dataList.size();
    	double y_avgHumidity = sumHumidity / dataList.size();
    	double y_avgTemperature = sumTemperature / dataList.size();
    	double y_avgPm2_5 = sumPm2_5 / dataList.size();
    	
    	double sum_decibel_covariance = 0;
    	double sum_ozone_covariance = 0;
    	double sum_co2_covariance = 0;
//    	double sum_particles_covariance = 0;
    	double sum_humidity_covariance = 0;
    	double sum_temperature_covariance = 0;
    	double sum_pm2_5_covariance = 0;
    	
    	// 分子の計算
    	for (int i = lag + 1; i < dataList.size(); i++) {
    		
        	double decibel_covariance = (dataList.get(i).getDecibel() - y_avgDecibel) * (dataList.get(i - (lag + 1)).getDecibel() - y_avgDecibel);        	
        	double ozone_covariance = (dataList.get(i).getOzone() - y_avgOzone) * (dataList.get(i - (lag + 1)).getOzone() - y_avgOzone);
        	double co2_covariance = (dataList.get(i).getCo2() - y_avgCo2) * (dataList.get(i - (lag + 1)).getCo2() - y_avgCo2);        	
//        	double particles_covariance = (dataList.get(i).getParticles() - y_avgParticles) * (dataList.get(i - (lag + 1)).getParticles() - y_avgParticles);  	
        	double humidity_covariance = (dataList.get(i).getHumidity() - y_avgHumidity) * (dataList.get(i - (lag + 1)).getHumidity() - y_avgHumidity);   	
        	double temperature_covariance = (dataList.get(i).getTemperature() - y_avgTemperature) * (dataList.get(i - (lag + 1)).getTemperature() - y_avgTemperature);
        	double pm2_5_covariance = (dataList.get(i).getMcPm2_5() - y_avgPm2_5) * (dataList.get(i - (lag + 1)).getMcPm2_5() - y_avgPm2_5);

        	sum_decibel_covariance += decibel_covariance;
        	sum_ozone_covariance += ozone_covariance;
        	sum_co2_covariance += co2_covariance;
//        	sum_particles_covariance += particles_covariance;
        	sum_humidity_covariance += humidity_covariance;
        	sum_temperature_covariance += temperature_covariance;
        	sum_pm2_5_covariance += pm2_5_covariance;

    	}
    	
    	// 分母の計算
    	double sum_decibel_denominator = 0;
    	double sum_ozone_denominator = 0;
    	double sum_co2_denominator = 0;
//    	double sum_particles_denominator = 0;
    	double sum_humidity_denominator = 0;
    	double sum_temperature_denominator = 0;
    	double sum_pm2_5_denominator = 0;

    	for (int j = 0; j < dataList.size(); j++) {
    		
        	double decibel_denominator =  Math.pow((dataList.get(j).getDecibel() - y_avgDecibel), 2);
        	double ozone_denominator =  Math.pow((dataList.get(j).getOzone() - y_avgOzone), 2);
        	double co2_denominator =  Math.pow((dataList.get(j).getCo2() - y_avgCo2), 2);
//        	double particles_denominator =  Math.pow((dataList.get(j).getParticles() - y_avgParticles), 2);
        	double humidity_denominator =  Math.pow((dataList.get(j).getHumidity() - y_avgHumidity), 2);
        	double temperature_denominator =  Math.pow((dataList.get(j).getTemperature() - y_avgTemperature), 2);
        	double pm2_5_denominator =  Math.pow((dataList.get(j).getMcPm2_5() - y_avgPm2_5), 2);

        	sum_decibel_denominator += decibel_denominator;
        	sum_ozone_denominator += ozone_denominator;
        	sum_co2_denominator += co2_denominator;
//        	sum_particles_denominator += particles_denominator;
        	sum_humidity_denominator += humidity_denominator;
        	sum_temperature_denominator += temperature_denominator;
        	sum_pm2_5_denominator += pm2_5_denominator;

    	}
    	    	
    	EnvironmentAutoCorrelationResponse environmentAutoCorrelationResponse = new EnvironmentAutoCorrelationResponse();
    	
    	environmentAutoCorrelationResponse.setLag(lag + 1);
    	environmentAutoCorrelationResponse.setDecibel(Double.isNaN(sum_decibel_covariance / sum_decibel_denominator) ? 0 : sum_decibel_covariance / sum_decibel_denominator);
    	environmentAutoCorrelationResponse.setOzone(Double.isNaN(sum_ozone_covariance / sum_ozone_denominator) ? 0 : sum_ozone_covariance / sum_ozone_denominator);
    	environmentAutoCorrelationResponse.setCo2(Double.isNaN(sum_co2_covariance / sum_co2_denominator) ? 0 : sum_co2_covariance / sum_co2_denominator);
//    	environmentAutoCorrelationResponse.setParticles(Double.isNaN(sum_particles_covariance / sum_particles_denominator) ? 0 : sum_particles_covariance / sum_particles_denominator);
    	environmentAutoCorrelationResponse.setHumidity(Double.isNaN(sum_humidity_covariance / sum_humidity_denominator) ? 0 : sum_humidity_covariance / sum_humidity_denominator);
    	environmentAutoCorrelationResponse.setTemperature(Double.isNaN(sum_temperature_covariance / sum_temperature_denominator) ? 0 : sum_temperature_covariance / sum_temperature_denominator);
    	environmentAutoCorrelationResponse.setMcPm2_5(Double.isNaN(sum_pm2_5_covariance / sum_pm2_5_denominator) ? 0 : sum_pm2_5_covariance / sum_pm2_5_denominator);

    	return environmentAutoCorrelationResponse;
    	
    }

}