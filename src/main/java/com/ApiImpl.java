package com;

import java.util.LinkedHashMap;
import java.util.Map;

import com.utils.BaseConfig;

public class ApiImpl {

	static Map<String,String> headers = new LinkedHashMap<String,String>();
	static Map<String,String> invalidHeaders = new LinkedHashMap<String,String>();
	
	public static Map<String,String> getHeaders(){
		if(headers.isEmpty()){
			headers.put(BaseConfig.KEY, BaseConfig.API_KEY);
		}
		return headers;
	}
	
	public static Map<String,String> getInvalidHeaders(){
		if(invalidHeaders.isEmpty()){
			invalidHeaders.put(BaseConfig.KEY, "appdasddfe");
		}
		return invalidHeaders;
	}
}
