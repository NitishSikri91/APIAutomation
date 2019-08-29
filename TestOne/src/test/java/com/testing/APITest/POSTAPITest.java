package com.testing.APITest;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;

import javax.swing.text.html.parser.Entity;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.data.Users;
import com.testing.baseClass.TestBase;
import com.testing.client.RestClient;

public class POSTAPITest extends TestBase {

	TestBase testbase;
	String ServiceURL;
	String APIURL;
	String URI;
	RestClient RC;
	CloseableHttpResponse httpReponse;
		
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException, JSONException
	{
		testbase = new TestBase();
		ServiceURL =prop.getProperty("URL");
		APIURL = prop.getProperty("serviceURL");
		URI = ServiceURL+APIURL;
	}		
	
	@Test
	public void PostApiTest() throws JsonGenerationException, JsonMappingException, IOException, JSONException
	{
		RestClient RC = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
//Generate the JSON ..
// Jackson API used for JAVA to JSON - Mashaleing
		ObjectMapper Mapper = new ObjectMapper();
		Users user = new Users("Morpheus","Manager");//Expected user object
		
		
//Need to converT user object to JSON file
		Mapper.writeValue(new File("C:\\Users\\Nitish\\Desktop\\Workspace\\TestOne\\src\\main\\java\\com\\qa\\data\\user.json"),user);
	
//Object to JSON in string
		
	String User_JSON_STR = Mapper.writeValueAsString(user);
	//System.out.println(User_JSON_STR);	
	
	httpReponse = RC.Post(URI, User_JSON_STR, headerMap);//Main call for Post API's
	
	//Status code check
	int Response_code = httpReponse.getStatusLine().getStatusCode();
	System.out.println("Response code is "+ Response_code);
	
	//2. JSON
	
	String responseString  = EntityUtils.toString(httpReponse.getEntity(),"UTF-8");					
		
		JSONObject JsonResponse = new JSONObject(responseString);
		System.out.println("Response from API is "+ JsonResponse);
		
		//JSON to java - DeMarshalling  
		Users Userobj = Mapper.readValue(responseString, Users.class);
		System.out.println(Userobj);
		
		if(user.getName().equals(Userobj.getName()))
		{System.out.println("Compared name in API is true");}
		
		if(user.getJob().equals(Userobj.getJob()))
		{
			System.out.println("Value of Job is same");
		}
		
	}
	
}





