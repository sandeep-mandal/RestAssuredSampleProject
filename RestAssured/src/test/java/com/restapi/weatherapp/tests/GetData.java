package com.restapi.weatherapp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetData {
	
	@Test
	public void testResponseCode()
	{
		Response resp = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		
		int code = resp.getStatusCode();
		
		System.out.println("Status code is " +code);
		
		Assert.assertEquals(code, 200);
		
	}
	
	@Test
	public void testResponseBody()
	{
		Response resp = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		
		String body = resp.asString();
		
		System.out.println("Response body is " +body);		
		
		System.out.println("Response time is " +resp.getTime());
		
	}
	
}
