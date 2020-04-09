package com.utils;

import java.awt.List;

import org.apache.log4j.Logger;

public class BaseConfig extends Config {
	

	static Logger log = Logger.getLogger(BaseConfig.class.getName());
	
	public static String API_SERVER = null;
	public static String TEST_DATA_EXCEL = null;
	public static String API_CATAGORIES = null;
	public static String API_CITIES = null;
	public static String API_COLLECTIONS = null;
	public static String API_CUISINES = null;
	public static String API_ESTABLISHMENTS = null;
	public static String API_GEOCODE = null;
	public static String API_LOCATIONDETAILS = null;
	public static String API_LOCATIONS = null;
	public static String API_DAILYMENU = null;
	public static String API_RESTAURANT = null;
	public static String API_REVIEW = null;
	public static String API_SEARCH = null;
	public static String API_KEY = null;
	
	public static final String KEY = "user-key";
	public static final String QUERYBYCITYNAME = "q";
	public static final String QUERY = "query";
	public static final String LATITUDE = "lat";
	public static final String LONGITUDE = "lon";
	public static final String CITYIDS = "city-ids";
	public static final String CITYID = "city_id";
	public static final String COUNT = "count";
	public static final String ENTITY_ID = "entity_id";
	public static final String ENTITY_TYPE = "entity_type";
	public static final String START = "start";
	public static final String RADIUS="radius";
	public static final String RESTAURANT_ID="res_id";
	public static final String CUISINES="cuisines";
	public static final String ESTABLISHMENT_TYPE="establishment_type";
	public static final String COLLECTION_ID="collection_id";
	public static final String CATEGORY="category";
	public static final String SORT="sort";
	public static final String ORDER="order";
	
	public static final String LOCATION_SUGGESTIONS="location_suggestions";

	
	
	
	public BaseConfig(String path) {
		super(path);
		log.info("Loading enviornment configurations");
		
		API_SERVER = get("api.server");				
		TEST_DATA_EXCEL = get("TEST_DATA_EXCEL");
		API_CATAGORIES=get("api.catagories");
		API_CITIES=get("api.cities");
		API_COLLECTIONS=get("api.collections");
		API_CUISINES=get("api.cuisines");
		API_ESTABLISHMENTS=get("api.establishments");
		API_GEOCODE=get("api.geocode");
		API_LOCATIONDETAILS=get("api.locationdetails");
		API_LOCATIONS=get("api.locations");
		API_DAILYMENU=get("api.dailymenu");
		API_RESTAURANT=get("api.restaurant");
		API_REVIEW=get("api.reviews");
		API_SEARCH=get("api.search");
		API_KEY=get("api.key");
		
	}
}

