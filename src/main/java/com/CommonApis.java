package com;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jayway.restassured.response.Response;
import com.utils.BaseConfig;
import com.utils.GenericRestAssuredMethods;

public class CommonApis {
	
	static GenericRestAssuredMethods ge;
	public CommonApis() {
		ge = GenericRestAssuredMethods.getInstance();
	}
	
	public static Map<String,String> headers = new LinkedHashMap<String,String>();
	public static Map<String,String> invalidHeaders = new LinkedHashMap<String,String>();
	public static Map<String,Integer> categories = new LinkedHashMap<String,Integer>();
	public static Map<String,Object> cityDetails = new LinkedHashMap<String,Object>();
	public static Map<String,Object> collectionDetails = new LinkedHashMap<String,Object>();
	public static Map<String,Integer> cuisinesDetails = new LinkedHashMap<String,Integer>();
	public static Map<String,Integer> establishmentDetails = new LinkedHashMap<String,Integer>();
	
	
	
	public Response getcategories() {
		String url = BaseConfig.API_SERVER + BaseConfig.API_CATAGORIES;
		Response response = ge.getRequestWithHeaders(url, ApiImpl.getHeaders());
		//System.out.println(response.asString());
		if(response.getStatusCode()==200) {
			categories.clear();
			List<Map<String,Map<String,Object>>> cat = (List<Map<String,Map<String,Object>>>) ge.extractValueby_JsonPath(response, "categories");
			System.out.println(cat);
			for(Map<String,Map<String,Object>> m : cat) {
				categories.put(m.get("categories").get("name").toString(), Integer.parseInt(m.get("categories").get("id").toString()));
			}
		}
		return response;
	}
	
	
	public Response getCities(String cityName, double lat, double lon, String cityIds, int count) {
		Response response = null;
		Map<String,Object> query = new LinkedHashMap<String,Object>();
		if(cityName!=null) {
			query.put(BaseConfig.QUERYBYCITYNAME, cityName);
		}
		if(lat!=0 && lon!=0) {
			query.put(BaseConfig.LATITUDE, lat);
			query.put(BaseConfig.LONGITUDE, lon);
		}
		if(cityIds!=null) {
			query.put(BaseConfig.CITYIDS, cityIds);
		}
		if(count!=0) {
			query.put(BaseConfig.COUNT, count);
		}
		String url = BaseConfig.API_SERVER + BaseConfig.API_CITIES;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		if(response.getStatusCode()==200) {
			cityDetails.clear();
			List<Map<String,Object>> cityD = (List<Map<String,Object>>)ge.extractValueby_JsonPath(response, "location_suggestions");
			for(Map<String,Object>map : cityD) {
				for(Map.Entry<String, Object> e : map.entrySet()) {
					cityDetails.put(e.getKey(), e.getValue());
				}
			}
		}
		return response;
	}
	
	
	public Response getCollections(Integer cityId, double lat, double lon, int count) {
	
		Response response = null;
		Map<String,Object> query = new LinkedHashMap<String,Object>();
		if(cityId!=0) {
			query.put(BaseConfig.CITYID, cityId);
		}
		if(lat!=0 && lon!=0) {
			query.put(BaseConfig.LATITUDE, lat);
			query.put(BaseConfig.LONGITUDE, lon);
		}
		if(count!=0) {
			query.put(BaseConfig.COUNT, count);
		}
		
		String url = BaseConfig.API_SERVER + BaseConfig.API_COLLECTIONS;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		if(response.getStatusCode()==200) {
			collectionDetails.clear();
			List<Map<String,Object>> cityD = (List<Map<String,Object>>)ge.extractValueby_JsonPath(response, "$");
			for(Map<String,Object>map : cityD) {
				for(Map.Entry<String, Object> e : map.entrySet()) {
					collectionDetails.put(e.getKey(), e.getValue());
				}
			}
		}

		return response;
	}
	
	public Response getCuisines(Integer cityId, double lat, double lon) {
		
		Response response = null;
		Map<String,Object> query = new LinkedHashMap<String,Object>();
		if(cityId!=0) {
			query.put(BaseConfig.CITYID, cityId);
		}
		if(lat!=0 && lon!=0) {
			query.put(BaseConfig.LATITUDE, lat);
			query.put(BaseConfig.LONGITUDE, lon);
		}
		
		String url = BaseConfig.API_SERVER + BaseConfig.API_CUISINES;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		if(response.getStatusCode()==200) {
			cuisinesDetails.clear();
			List<Map<String,Map<String,Object>>> cityD = (List<Map<String,Map<String,Object>>>)ge.extractValueby_JsonPath(response, "cuisines");
			for(Map<String,Map<String,Object>>map : cityD) {
				cuisinesDetails.put(map.get("cuisine").get("cuisine_name").toString(), Integer.parseInt(map.get("cuisine").get("cuisine_id").toString()));
			}
		}
		return response;
	}
	
public Response getEstablishments(Integer cityId, double lat, double lon) {
		
		Response response = null;
		Map<String,Object> query = new LinkedHashMap<String,Object>();
		if(cityId!=0) {
			query.put(BaseConfig.CITYID, cityId);
		}
		if(lat!=0 && lon!=0) {
			query.put(BaseConfig.LATITUDE, lat);
			query.put(BaseConfig.LONGITUDE, lon);
		}
		
		String url = BaseConfig.API_SERVER + BaseConfig.API_ESTABLISHMENTS;
		response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
		System.out.println(response.asString());
		if(response.getStatusCode()==200) {
			establishmentDetails.clear();
			List<Map<String,Map<String,Object>>> cityD = (List<Map<String,Map<String,Object>>>)ge.extractValueby_JsonPath(response, "establishments");
			for(Map<String,Map<String,Object>>map : cityD) {
				establishmentDetails.put(map.get("establishment").get("name").toString(), Integer.parseInt(map.get("establishment").get("id").toString()));
			}
		}
		return response;
	}
	
	
public Response getGeoCode(double lat, double lon) {
	
	Response response = null;
	Map<String,Object> query = new LinkedHashMap<String,Object>();
	if(lat!=0 && lon!=0) {
		query.put(BaseConfig.LATITUDE, lat);
		query.put(BaseConfig.LONGITUDE, lon);
	}
	
	String url = BaseConfig.API_SERVER + BaseConfig.API_GEOCODE;
	response = ge.getRequestWithQueryParamAndHeaders(url, query, ApiImpl.getHeaders());
	System.out.println(response.asString());
	return response;
}

	public static void main(String args[]) {
		
		BaseConfig conf = new BaseConfig("env.properties");
		try {
			GenericRestAssuredMethods.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonApis c = new CommonApis();
		c.getCities("Mumbai", 0, 0, null, 0);
	}
}
