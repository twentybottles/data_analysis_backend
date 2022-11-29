package com.example.demo.entity.atlas;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "lane_records")
@Data
public class LaneRecordsEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="lane_id", insertable = false, updatable = false)
	private int laneId;	
		
	private Double vehicles;
	
	private Double class1;
	
	private Double class2;
	
	private Double class3;
	
	private Double class4;
	
	private Double class5;
	
	@Column(name="packet_ts")
	private Date packetTs;
	
	private Double class6;
	
	private Double class7;
	
	private Double class8;
	
	private Double class9;
	
	private Double class10;
		
    @ManyToOne(optional=false)
    @JoinColumn(name = "lane_id", referencedColumnName = "id")
    private LanesEntity lanesEntity;
    
    public String getPacket_tsDate(String dateOption) {
    	
    	SimpleDateFormat dateFormat;
    	
    	switch (dateOption) {

		  case "Seconds":
			  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
		    break;
		  case "Minutes":
			  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    break;
    	  case "Hour":
    		  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00");
    	    break;
    	  case "Day":
    		  dateFormat = new SimpleDateFormat("EEE");
    	    break;
    	  case "Date":
    		  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	    break;
    	  case "Month":
    		  dateFormat = new SimpleDateFormat("yyyy-MM");
      	    break;
    	  default:
    		  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00");
    	  }
    	;
    	return dateFormat.format(packetTs);
    }

}

