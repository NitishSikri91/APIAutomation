package com.testing.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class RestClient {

	//1. Creating GET method without header
	public CloseableHttpResponse GET(String URI) throws ClientProtocolException, IOException 
	{
		// GET Method code ==============================
		// This will create an object for the connection
		CloseableHttpClient httpClinet = HttpClients.createDefault(); 
		// Using the httpGET method we are actually creating the GET api on the URI (passed as param)
		HttpGet httpGET = new HttpGet(URI); 
		//As a response of the GET , we need to save the response in an object and execute the GET command
		CloseableHttpResponse httpReponse =  httpClinet.execute(httpGET);//hitting the URL for GET response
		return httpReponse;
	}

	//Get method with Header
	public CloseableHttpResponse GET(String URI,HashMap<String, String> headerMap) throws ClientProtocolException, IOException 
	{
		// GET Method code ==============================
		// This will create an object for the connection
		CloseableHttpClient httpClinet = HttpClients.createDefault(); 
		// Using the httpGET method we are actually creating the GET api on the URI (passed as param)
		HttpGet httpGET = new HttpGet(URI); 
		
		//Appending the header 		//for(Map<K, V>.Entry<String, String> entry : headerMap.entrySet())
		for(Map.Entry<String, String> entry : headerMap.entrySet())
		{
			httpGET.addHeader(entry.getKey(), entry.getValue());
		}
		//As a response of the GET , we need to save the response in an object and execute the GET command
		CloseableHttpResponse httpReponse =  httpClinet.execute(httpGET);//hitting the URL for GET response
		return httpReponse;
		}

	
	//3 Post Method
	public CloseableHttpResponse Post(String URI , String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClinet = HttpClients.createDefault();// Creating client 
		HttpPost HttpPost = new HttpPost(URI); // Post Call with URL 
		HttpPost.setEntity(new StringEntity(entityString)); // Defining the Payload

		//For headers entry wiht the request 
		for(Map.Entry<String, String> entry : headerMap.entrySet())
		{
			HttpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpRespone = httpClinet.execute(HttpPost);
		return httpRespone;
	}

	
	
	
}