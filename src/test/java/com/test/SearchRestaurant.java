package com.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.CommonApis;
import com.Location;
import com.Restaurant;
import com.jayway.restassured.response.Response;

public class SearchRestaurant extends ApiBaseTestClass{

	CommonApis cm;
	Location lo;
	Restaurant re;
	
	
	@BeforeClass
	void testSetup() {
		cm = new CommonApis();
		lo = new Location();
		re = new Restaurant();
		
	}
	
	@Test
	void searchRestaurantsByCityAndValidateRestaurantDetails() {
		Response getcategories = cm.getcategories();
		//Response cities = cm.getCities("Mumbai", 0, 0, null, 1);
		Response searchResult = re.getSearchResult(0, null, "Mumbai", 0, 2, 0, 0, 0, null, null, null, String.valueOf(CommonApis.categories.get("NightLife")), "cost", "asc");
		Response retaurant = re.getRetaurant(Integer.parseInt(Restaurant.restaurantDetails.get("id").toString()));
		Reporter.report(Restaurant.restaurantDetails.get("id").equals(Restaurant.restaurantMap.get("id")), "Restaurant ids are same as expected !!");
		Reporter.report(Restaurant.restaurantDetails.get("name").equals(Restaurant.restaurantMap.get("name")), "Restaurant name are same as expected !!");
		Reporter.report(Restaurant.restaurantDetails.get("location").equals(Restaurant.restaurantMap.get("location")), "Restaurant name are same as expected !!");
		
	}
	
	@Test
	void searchRestaurantsByCategoryAndValidateRestaurantDetails() {
		Response getcategories = cm.getcategories();
		Response searchResult = re.getSearchResult(0, null, null, 0, 2, 0, 0, 0, null, null, null, String.valueOf(CommonApis.categories.get("NightLife")), "cost", "asc");
		Response retaurant = re.getRetaurant(Integer.parseInt(Restaurant.restaurantDetails.get("id").toString()));
		Reporter.report(Restaurant.restaurantDetails.get("id").equals(Restaurant.restaurantMap.get("id")), "Restaurant ids are same as expected !!");
		Reporter.report(Restaurant.restaurantDetails.get("name").equals(Restaurant.restaurantMap.get("name")), "Restaurant name are same as expected !!");
		Reporter.report(Restaurant.restaurantDetails.get("location").equals(Restaurant.restaurantMap.get("location")), "Restaurant name are same as expected !!");
		
	}
	
	@Test
	void searchRestaurantsByCategoryAndValidateCountOfRestaurants() {
		Response getcategories = cm.getcategories();
		int count =2;
		Response searchResult = re.getSearchResult(0, null, null, 0, count, 0, 0, 0, null, null, null, String.valueOf(CommonApis.categories.get("NightLife")), "cost", "asc");
		Reporter.report(Restaurant.searchedRestaurant.size()==count, "Number Searched restaurants are same as number of restaurants provided in search response !!");
		
	}
	
	@Test
	void searchRestaurantsByCategoryAndValidateReviewsByCount() {
		Response getcategories = cm.getcategories();
		int count =2;
		Response searchResult = re.getSearchResult(0, null, "Mumbai", 0, count, 0, 0, 0, null, null, null, String.valueOf(CommonApis.categories.get("NightLife")), "cost", "asc");
		Response reviews = re.getReviews(Integer.parseInt(Restaurant.restaurantDetails.get("id").toString()), 0, 0);
		Reporter.report(Restaurant.reviewCount==Integer.parseInt(Restaurant.restaurantDetails.get("all_reviews_count").toString()), "Restaurant review count validation successfull !!");
		
	}
	
	
}
