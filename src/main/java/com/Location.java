package com;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jayway.restassured.response.Response;
import com.utils.BaseConfig;
import com.utils.GenericRestAssuredMethods;

public class Location {

	static GenericRestAssuredMethods ge;
	public Location() {
		ge = GenericRestAssuredMethods.getInstance();
	}
	
	static Map<String,Object> entityDetails = new LinkedHashMap<String,Object>();
	
	
	public Response getLocationDetails(Integer entityId, String entityType) {
		
		Response response = null;
		Map<String,Object> query = new LinkedHashMap<String,Object>();
		if(entityId!=0) {
			query.put(BaseConfig.ENTITY_ID, entityId);
		}
		if(entityType!=null) {
			query.put(BaseConfig.ENTITY_TYPE, entityType);
		}

		String url = BaseConfig.API_SERVER + BaseConfig.API_LOCATIONDETAILS;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		return response;
	}
	
public Response getLocation(String query,double lat, double lon, Integer count) {
		
		Response response = null;
		Map<String,Object> querymap = new LinkedHashMap<String,Object>();
		if(query!=null) {
			querymap.put(BaseConfig.QUERY, query);
		}
		if(lat!=0 && lon!=0) {
			querymap.put(BaseConfig.LATITUDE, lat);
			querymap.put(BaseConfig.LONGITUDE, lon);
		}
		if(count!=0) {
			querymap.put(BaseConfig.COUNT, count);
		}

		String url = BaseConfig.API_SERVER + BaseConfig.API_LOCATIONS;
		response = ge.getRequestWithQueryParamAndHeaders(url, querymap, ApiImpl.getHeaders());
		System.out.println(response.asString());
		if(response.getStatusCode()==200) {
			entityDetails.clear();
			List<Map<String,Object>> location = (List<Map<String,Object>>)ge.extractValueby_JsonPath(response, BaseConfig.LOCATION_SUGGESTIONS);
			for(Map<String,Object> m : location) {
				for(Map.Entry<String, Object> e : m.entrySet()) {
					entityDetails.put(e.getKey(), e.getValue());
				}
			}
		}
		return response;
	}
}
