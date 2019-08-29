package com.testing.APITest;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testing.baseClass.TestBase;
import com.testing.client.RestClient;
import com.testing.utility.TestUtility;

public class GETAPITest extends TestBase {

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
	public void TC1GetAPITest() throws ClientProtocolException, IOException, JSONException
	{
		RestClient RC = new RestClient();
		httpReponse =RC.GET(URI);
		
		//1. Response code from the server , it is always n int value
		int ResponeCode  = httpReponse.getStatusLine().getStatusCode();	
		System.out.println("Response code for the call is " + ResponeCode);
		
		Assert.assertEquals(ResponeCode, API_Response_Code_200, "Status is no expected as 200");
		
		//converting JSON response as string  and with UTF-8 format
		String responseString = EntityUtils.toString(httpReponse.getEntity(),"UTF-8");
		
		// Need to change the string response to JSON object for parsing
		JSONObject ResponseJSON = new JSONObject(responseString);
		System.out.println("Reponse from API is "+ ResponseJSON);
		
		//TO get header we are saving them in an array
		Header[] HeadderAray = httpReponse.getAllHeaders();
		//Inserting the header response in HASH Map 
		HashMap<String,String> allHeader = new HashMap<String, String>();
		for (Header header : HeadderAray) // while there is an header object in the HeaderArry the loop will tun 
		{
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("Header array -->"+allHeader);
		
		System.out.println(allHeader.get("Transfer-Encoding"));
		
		
		
		//Calling the utility to search the value of Json object from the Response JSON
		String per_page = TestUtility.getValueByJsonPath(ResponseJSON,"/per_page");
		System.out.println("Value of per_page is "+ per_page);
		
		Assert.assertEquals(per_page,"6","Per page value is correct");	
		String total_pages = TestUtility.getValueByJsonPath(ResponseJSON,"/total_pages");
		System.out.println("Value of per_page is "+ total_pages);	
	
		// Get the value from JSON Array -------------------->
		
		
		String LastName1 = TestUtility.getValueByJsonPath(ResponseJSON, "/data[0]/last_name");
		String ID1 = TestUtility.getValueByJsonPath(ResponseJSON, "/data[0]/id");
		String Email1 = TestUtility.getValueByJsonPath(ResponseJSON, "/data[0]/email");
		String first_name = TestUtility.getValueByJsonPath(ResponseJSON, "/data[0]/first_name");
	
		System.out.println("Following are the details from data[0]");
		System.out.println("");
	System.out.println("First name is "+ first_name +" And Last name is "+ LastName1);
	System.out.println("Email id "+ Email1 +" Id id "+ ID1 );
	}	
}
