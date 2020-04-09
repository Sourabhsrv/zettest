package com.utils;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.hamcrest.Matchers;
import org.testng.Assert;

import com.CommonApis;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;

import jxl.Sheet;

/**
 * @author Sourabh
 *
 */
public class GenericRestAssuredMethods {

	private static GenericRestAssuredMethods instance = null;

	private GenericRestAssuredMethods() {
		
	}
	public static void init() throws Exception {
		instance = new GenericRestAssuredMethods();
	}

	public static GenericRestAssuredMethods getInstance() {
		return instance;
	}



	
	/**
	 * Return the response after making the get request along with the query params
	 * and with headers Accepts query params and headers as MAP in key value pairs
	 */

	public Response getRequestWithQueryParamAndHeaders(String url, Map<String, ?> query, Map<String, ?> headers) {

		return given().queryParameters(query).headers(headers).expect().statusCode(200).when().get(url);
	}

	public Response getRequestWithHeaders(String url, Map<String,?>headers){
		
		return given()
				.headers(headers).log().all()
				.expect()
				.when().get(url).thenReturn();
	}
	
	/**
	 * Extracts value from the response by Json path
	 **/
	public Object extractValueby_JsonPath(Response response, String memberpath) {
		return response.jsonPath().get(memberpath);
	}

}
