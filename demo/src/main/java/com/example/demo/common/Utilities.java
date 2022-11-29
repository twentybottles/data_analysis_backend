package com.example.demo.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import static com.example.demo.common.WebConst.FORMAT_yyyyMMDD_HHmmss;
import static com.example.demo.common.WebConst.SUMMER_START_DATE;
import static com.example.demo.common.WebConst.SUMMER_END_DATE;
import static com.example.demo.common.WebConst.TIMEZONE_UTC;
import static com.example.demo.common.WebConst.PATH_DOWNLOAD_TRAFFIC_CSV;
import static com.example.demo.common.WebConst.STRING_SEVEN;
import static com.example.demo.common.WebConst.STRING_EIGHT;

import com.example.demo.entity.atlas.TrafficVolumeResponse;
import com.example.demo.entity.enviropi.AllSensorViewEntity;
import com.example.demo.entity.prd.TrafficVolumePrdResponse;

public class Utilities {
	
    public String getUtcLag(Date startDate) throws ParseException {
    	
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyyMMDD_HHmmss);
		sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC));
		String summerStartStr = SUMMER_START_DATE;
		Date summerStart = sdf.parse(summerStartStr);
		String summerEndStr = SUMMER_END_DATE;
		Date summerEnd = sdf.parse(summerEndStr);
		
    	String utcLag = STRING_EIGHT;
    	
		if(startDate.compareTo(summerStart) == 1 && startDate.compareTo(summerEnd) == -1) {
			utcLag = STRING_SEVEN;
		}
    	
		return utcLag;
    }
    
    public void exportTrafficTmsCsv(List<TrafficVolumeResponse> trafficVolumeResponseList) throws IOException {
    	
    	File file = new File(PATH_DOWNLOAD_TRAFFIC_CSV);
    	
    	if (checkBeforeWritefile(file)){

    		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    		pw.print("packetTs");
    		pw.print(",");
    		pw.print("totalBicycle");
    		pw.print(",");
    		pw.print("totalBus");
    		pw.print(",");
    		pw.print("totalCar");
    		pw.print(",");
    		pw.print("totalMotorcycleTuktuk");
    		pw.print(",");
    		pw.print("totalSUV");
    		pw.print(",");
    		pw.print("totalTruck");
    		pw.print(",");
    		pw.print("totalTruckBigRig");
    		pw.print(",");
    		pw.print("totalVan");
    		pw.print(",");
    		pw.print("totalCarSUVVan");
    		pw.println();
    		
    		for(TrafficVolumeResponse traffic : trafficVolumeResponseList){
    			
        		pw.print(traffic.getPacketTs());
        		pw.print(",");
        		pw.print(traffic.getTotalBicycle());
        		pw.print(",");
        		pw.print(traffic.getTotalBus());
        		pw.print(",");
        		pw.print(traffic.getTotalCar());
        		pw.print(",");
        		pw.print(traffic.getTotalMotorcycleTuktuk());
        		pw.print(",");
        		pw.print(traffic.getTotalSUV());
        		pw.print(",");
        		pw.print(traffic.getTotalTruck());
        		pw.print(",");
        		pw.print(traffic.getTotalTruckBigRig());
        		pw.print(",");
        		pw.print(traffic.getTotalVan());
        		pw.println();
    			
    		}
    		
    		pw.close(); 
    		
    	} else {
    		
            System.out.println("ファイルに書き込めません");
        
    	}
	
    }
    
    public void exportTrafficPrdCsv(List<TrafficVolumePrdResponse> trafficVolumeResponseList) throws IOException {
    	
    	File file = new File("C:\\Users\\Riki\\Downloads\\traffic_prd.csv");
    	
    	if (checkBeforeWritefile(file)){

    		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    		pw.print("packetTs");
    		pw.print(",");
    		pw.print("totalBicycle");
    		pw.print(",");
    		pw.print("totalBus");
    		pw.print(",");
    		pw.print("totalCar");
    		pw.print(",");
    		pw.print("totalMotorcycleTuktuk");
    		pw.print(",");
    		pw.print("totalSUV");
    		pw.print(",");
    		pw.print("totalTruck");
    		pw.print(",");
    		pw.print("totalTruckBigRig");
    		pw.print(",");
    		pw.print("totalVan");
    		pw.print(",");
    		pw.print("totalCarSUVVan");
    		pw.println();
    		
    		for(TrafficVolumePrdResponse traffic : trafficVolumeResponseList){
    			
        		pw.print(traffic.getPacketTs());
        		pw.print(",");
        		pw.print(traffic.getTotalBicycle());
        		pw.print(",");
        		pw.print(traffic.getTotalBus());
        		pw.print(",");
        		pw.print(traffic.getTotalCar());
        		pw.print(",");
        		pw.print(traffic.getTotalMotorcycleTuktuk());
        		pw.print(",");
        		pw.print(traffic.getTotalSUV());
        		pw.print(",");
        		pw.print(traffic.getTotalTruck());
        		pw.print(",");
        		pw.print(traffic.getTotalTruckBigRig());
        		pw.print(",");
        		pw.print(traffic.getTotalVan());
        		pw.println();
    			
    		}
    		
    		pw.close(); 
    		
    	} else {
    		
            System.out.println("ファイルに書き込めません");
        
    	}
    }
    
    
    public void exportEnvironmentCsv(List<AllSensorViewEntity> allSensorViewEntityList) throws IOException {
    	
		FileWriter fw = new FileWriter("C:\\Users\\Riki\\Downloads\\environment.csv", false);
		PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
		
		pw.print("eventTime");
		pw.print(",");
		pw.print("averageDecibel");
		pw.print(",");
		pw.print("averageOzone");
		pw.print(",");
		pw.print("averageCo2");
		pw.print(",");
		pw.print("averageHumidity");
		pw.print(",");
		pw.print("averageTemperature");
		pw.println();
		
		for(AllSensorViewEntity environment : allSensorViewEntityList){
			
    		pw.print(environment.getEventTime());
    		pw.print(",");
    		pw.print(environment.getDecibel());
    		pw.print(",");
    		pw.print(environment.getOzone());
    		pw.print(",");
    		pw.print(environment.getCo2());
    		pw.print(",");
    		pw.print(environment.getHumidity());
    		pw.print(",");
    		pw.print(environment.getTemperature());
    		pw.println();
			
		}
		
		pw.close(); 
    }
	
    private static boolean checkBeforeWritefile(File file){

    	if (file.exists()){
          if (file.isFile() && file.canWrite()){
            return true;
          }
        }

        return false;
   }
    
}