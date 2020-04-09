package com.test;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;



public class Reporter {
	static Logger log = Logger.getLogger(ApiBaseTestClass.class.getName());
	
	public static void report(String message){
		org.testng.Reporter.log( message  );		
	}	
	
	public static void report(boolean trueCondition, String message){
		String reporting = (message + (trueCondition ? " [PASS]" : " [FAIL]" ));
		org.testng.Reporter.log(reporting + "</br>");
		log.info(reporting);
		Assert.isTrue(trueCondition);
	}

}
