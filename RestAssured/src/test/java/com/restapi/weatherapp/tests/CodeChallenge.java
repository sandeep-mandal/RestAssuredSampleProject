package com.restapi.weatherapp.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class CodeChallenge {
	
	ResponseSpecification checkStatusCodeAndContentType = 
		    new ResponseSpecBuilder().
		        expectStatusCode(200).
		        expectContentType(ContentType.JSON).
		        build();
	@Test
	public void testResponseCodeAndFormat()
	{
		String zipcode = "03820";		
		
	    given().
	    	pathParam("cityzipcode",zipcode).
	    when().
	        get("http://api.openweathermap.org/data/2.5/weather?zip={cityzipcode},us&APPID=d05a336b7efdc14276d2a4cad2ef7538").
	    then().
	        assertThat().
	        //contentType(ContentType.JSON).and().
	        //statusCode(200).
	        spec(checkStatusCodeAndContentType);		
	}	
	
	@Test
	public void testKnownTempRange()
	{
		String zipcode = "03820";
		Float lrange = 280.5f;
		Float hrange = 350.5f;

	    float temprange = given().
	    	pathParam("cityzipcode",zipcode).
	    when().
	        get("http://api.openweathermap.org/data/2.5/weather?zip={cityzipcode},us&APPID=d05a336b7efdc14276d2a4cad2ef7538").
	    then().extract().path("main.temp");
	    Assert.assertTrue(lrange<=temprange && temprange <=hrange);
		
	}	
	
	/*
	@Test
	public void testKnownTempRangeUsingMethod()
	{
		String zipcode = "03820";
		float knowntemp = 299.5f;		
		float knowntemprange = 20.0f;	
					
	    given().
	    	pathParam("cityzipcode",zipcode).
	    when().
	        get("http://api.openweathermap.org/data/2.5/weather?zip={cityzipcode},us&APPID=d05a336b7efdc14276d2a4cad2ef7538").
	    then().
	    body("main.temp",(iswithinrange(knowntemp,knowntemprange)));
		
	}*/

	private ResponseAwareMatcher<Response> iswithinrange(float knowntemp, float knowntemprange) {
		
		float iknowntemp = knowntemp;
		float lowerrange = iknowntemp - knowntemprange;
		float higherrange = iknowntemp + knowntemprange;
		
		System.out.println(Math.abs(iknowntemp));	
		System.out.println(Math.abs(lowerrange));	
		
		if(Math.round(iknowntemp)>Math.round(lowerrange) && Math.round(iknowntemp)>Math.round(higherrange))
		{
			
		}
		return null;
	}
			
}
