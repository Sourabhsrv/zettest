package com;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jayway.restassured.response.Response;
import com.utils.BaseConfig;
import com.utils.GenericRestAssuredMethods;

public class Restaurant {

	static GenericRestAssuredMethods ge;

	public Restaurant() {
		ge = GenericRestAssuredMethods.getInstance();
	}
	
	public static Map<String,Object> restaurantDetails = new LinkedHashMap<String,Object>(); 
	public static Map<String,Object> restaurantMap = new LinkedHashMap<String,Object>();
	public static ArrayList<Object> searchedRestaurant = new ArrayList<Object>();
	public static int reviewCount=0;

	public Response getDailyMenu(Integer restaurantId) {

		Response response = null;
		Map<String, Object> query = new LinkedHashMap<String, Object>();
		if (restaurantId != 0) {
			query.put(BaseConfig.RESTAURANT_ID, restaurantId);
		}

		String url = BaseConfig.API_SERVER + BaseConfig.API_DAILYMENU;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		return response;
	}

	public Response getRetaurant(Integer restaurantId) {

		Response response = null;
		Map<String, Object> query = new LinkedHashMap<String, Object>();
		if (restaurantId != 0) {
			query.put(BaseConfig.RESTAURANT_ID, restaurantId);
		}

		String url = BaseConfig.API_SERVER + BaseConfig.API_RESTAURANT;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		restaurantMap.clear();
		if(response.getStatusCode()==200) {
			restaurantMap=(Map<String,Object>)ge.extractValueby_JsonPath(response, "$");
			restaurantMap.remove("establishment_types");
			System.out.println("Rest1 " + restaurantMap);
		}
		return response;
	}

	public Response getReviews(Integer restaurantId, Integer start, Integer count) {

		Response response = null;
		Map<String, Object> query = new LinkedHashMap<String, Object>();
		if (restaurantId != 0) {
			query.put(BaseConfig.RESTAURANT_ID, restaurantId);
		}
		if (start != 0) {
			query.put(BaseConfig.START, start);
		}
		if (count != 0) {
			query.put(BaseConfig.COUNT, count);
		}
		
		String url = BaseConfig.API_SERVER + BaseConfig.API_REVIEW;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		if(response.getStatusCode()==200) {
			reviewCount  = Integer.parseInt(ge.extractValueby_JsonPath(response, "reviews_count").toString());
		}
		return response;
	}

	public Response getSearchResult(Integer entityId, String entityType, String queryByCityName, Integer start,
			Integer count, double lat, double lon, double radius, String cuisines, String establishmentType,
			String collectionId, String category, String sort, String order) {

		Response response = null;
		Map<String, Object> query = new LinkedHashMap<String, Object>();
		if (entityId != 0) {
			query.put(BaseConfig.ENTITY_ID, entityId);
		}
		if (entityType != null) {
			query.put(BaseConfig.ENTITY_TYPE, entityType);
		}
		if(queryByCityName!=null) {
			query.put(BaseConfig.QUERYBYCITYNAME, queryByCityName);
		}
		if (start != 0) {
			query.put(BaseConfig.START, start);
		}
		if (count != 0) {
			query.put(BaseConfig.COUNT, count);
		}
		if(lat!=0 && lon!=0) {
			query.put(BaseConfig.LATITUDE, lat);
			query.put(BaseConfig.LONGITUDE, lon);
		}
		if (radius != 0) {
			query.put(BaseConfig.RADIUS, radius);
		}
		if(cuisines!=null) {
			query.put(BaseConfig.CUISINES, cuisines);
		}
		if(establishmentType!=null) {
			query.put(BaseConfig.ESTABLISHMENT_TYPE, establishmentType);
		}
		if(collectionId!=null) {
			query.put(BaseConfig.COLLECTION_ID, collectionId);
		}
		if(category!=null) {
			query.put(BaseConfig.CATEGORY, category);
		}
		if(sort!=null) {
			query.put(BaseConfig.SORT, sort);
		}
		if(order!=null) {
			query.put(BaseConfig.ORDER, order);
		}

		String url = BaseConfig.API_SERVER + BaseConfig.API_SEARCH;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		restaurantDetails.clear();
		searchedRestaurant.clear();
		if(response.getStatusCode()==200) {
			searchedRestaurant=(ArrayList<Object>)ge.extractValueby_JsonPath(response, "restaurants");
			restaurantDetails = (Map<String,Object>)ge.extractValueby_JsonPath(response, "restaurants[0].restaurant");
		}
		return response;
	}
}
