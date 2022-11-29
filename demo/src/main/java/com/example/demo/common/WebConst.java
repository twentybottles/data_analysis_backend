package com.example.demo.common;

import java.io.File;

public interface WebConst {
	
    /** ---- String ---- **/
	public static final String Y_AXIS = "yAxis";
	public static final String X_AXIS = "xAxis";
	public static final String SECONDS = "Seconds";
	public static final String MINUTES = "Minutes";
	public static final String HOUR = "Hour";
	public static final String HOURS24 = "24Hours";
	public static final String DAY = "Day";
	public static final String DAYS = "Days";
	public static final String DATE = "Date";
	public static final String MONTH = "Month";
	public static final String FORMAT_yyyyMMDD_HHmmss = "yyyy/MM/dd HH:mm:ss";
	public static final String POSTGRES_FORMAT_SECONDS = "YYYY-MM-DD hh24:MI:SS";
	public static final String POSTGRES_FORMAT_MINUTES = "YYYY-MM-DD hh24:MI";
	public static final String POSTGRES_FORMAT_HOUR = "yyyy-mm-dd hh24";
	public static final String POSTGRES_FORMAT_HOURS24 = "yyyy-mm-dd hh24";
	public static final String POSTGRES_FORMAT_HOURS00 = "yyyy-mm-dd hh:00";
	public static final String POSTGRES_FORMAT_DAY = "yyyy-mm-dd";
	public static final String POSTGRES_FORMAT_DAYS = "EEE";
	public static final String POSTGRES_FORMAT_DATE = "yyyy-mm-dd";
	public static final String POSTGRES_FORMAT_MONTH = "yyyy-MM";
	public static final String MYSQL_FORMAT_SECONDS = "%Y-%m-%d %T";
	public static final String MYSQL_FORMAT_MINUTES = "%Y-%m-%d %H:%i";
	public static final String MYSQL_FORMAT_HOUR = "%Y-%m-%d %H:00";
	public static final String MYSQL_FORMAT_DATE = "%Y-%m-%d";
	public static final String MYSQL_FORMAT_MONTH = "%Y-%m";
	public static final String STRING_ZERO = "0";
	public static final String STRING_ONE = "1";
	public static final String STRING_TWO = "2";
	public static final String STRING_THREE = "3";
	public static final String STRING_FOUR = "4";
	public static final String STRING_FIVE = "5";
	public static final String STRING_SIX = "6";
	public static final String STRING_SEVEN = "7";
	public static final String STRING_EIGHT = "8";
	public static final String SUNDAY = "Sun";
	public static final String MONDAY = "Mon";
	public static final String TUESDAY = "Tue";
	public static final String WEDNESDAY = "Wed";
	public static final String THURSDAY = "Thu";
	public static final String FRIDAY = "Fri";
	public static final String SATURDAY = "Sat";
	public static final String SUMMER_START_DATE = "2022/03/13 02:00:00";
	public static final String SUMMER_END_DATE = "2022/11/06 02:00:00";
	public static final String TIMEZONE_UTC = "UTC";
	public static final String PATH_DOWNLOAD_TRAFFIC_CSV = "C:\\Users\\Riki\\Downloads\\traffic_tms.csv";
	public static final String DEVICE_CAR1 = "car1";
	public static final String DEVICE_CAR2 = "car2";
	public static final String DEVICE_CAR3 = "car3";
	public static final String DEVICE_CAR4 = "car4";


	/** ---- REST ---- **/
	
	public static final String POST = "POST";
	public static final String GET = "GET";
	
    /** ---- Internal API ---- **/
	public static final String TRAFFIC_VOLUME_TMS_FIND_URL = "/api/traffic_volume/tms-prd/find";

	public static final String TRAFFIC_VOLUME_PRD_FIND_URL = "/api/traffic_volume/prd/find";

	public static final String CORRELATION_TMS_NORMAL_URL = "/api/correlation/tms-prd/normal";

	public static final String CORRELATION_TMS_AUTO_URL = "/api/correlation/tms-prd/auto";

	public static final String CORRELATION_PRD_NORMAL_URL = "/api/correlation/prd/normal";

	public static final String CORRELATION_PRD_AUTO_URL = "/api/correlation/prd/auto";

	public static final String POWER_VOLUME_FIND_URL = "/api/power_volume/find";

	public static final String TRAFFIC_TMS_ENV_FIND_URL = "/api/traffic_env/tms-prd/find";

	public static final String TRAFFIC_PRD_ENV_FIND_URL = "/api/traffic_env/prd/find";

	public static final String LOCATION_FIND_URL = "/api/location/find";

	public static final String ENVIRONMENT_FIND_URL = "/api/environment/find";

    /** ---- Defined Host ---- **/

	public static final String LOCALHOST_3000 = "http://localhost:3000";

}