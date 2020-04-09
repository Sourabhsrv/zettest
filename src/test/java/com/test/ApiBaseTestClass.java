package com.test;


import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.jayway.restassured.response.Response;
import com.utils.BaseConfig;
import com.utils.GenericRestAssuredMethods;





public abstract class ApiBaseTestClass {
	static Logger log = Logger.getLogger(ApiBaseTestClass.class.getName());

	public int VALID_STATUS_CODE = 200;
	public int VALID_STATUS_CODE_CREATED = 201;
	public int INVALID_STATUS_CODE = 401;
	public int INVLALID_JSON_STATUS_CODE = 400;
	
	public String VALID_STATUS_LINE_OK = "OK";
	public String VALID_STATUS_LINE_CREATED = "CREATED";
	
//	boolean condition = true;
	
	@BeforeSuite
	public void setUp() {
		log.info("Starting test suite");
		BaseConfig conf = new BaseConfig("env.properties");
		try {
			GenericRestAssuredMethods.init();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("--------------------------------------------------------------------------------------------");
	}
	

	@AfterSuite
	public static void afterSuite() {
		log.info("Closing test suite");
	}
	
	

	
	public void assertIsValidStatCode(Response response) {
		boolean condition = (response.getStatusCode() == VALID_STATUS_CODE);
		Reporter.report(condition,"Response code is : " + response.getStatusCode());			
	}
	
	public void assertIsInvalidStatCode(Response response) {
		boolean condition = response.getStatusCode() == INVALID_STATUS_CODE;
		Reporter.report(condition,"Response code is : " + response.getStatusCode());
	}
	
	public void assertIsValidStatusLine(Response response){
		boolean condition = response.getStatusLine() == VALID_STATUS_LINE_OK || response.getStatusLine()==VALID_STATUS_LINE_CREATED;
		Reporter.report(condition,"Response code is : "+ response.getStatusLine());
	}
	
	public static String getAttr(Response response,String jsonPath) {
		String value="";
		if(response != null) {
			value = response.jsonPath().get(jsonPath);
		}
		return value;		
	}
}
