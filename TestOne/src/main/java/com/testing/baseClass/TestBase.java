package com.testing.baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public int API_Response_Code_200=200;
	public int API_Response_Code_400=400;
	public int API_Response_Code_404=404;
	public int API_Response_Code_500=500;
	public int API_Response_Code_201=201;
	
	
	public Properties prop;
	
	
	public TestBase()
	{
		try {
		prop = new Properties();
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\testing\\config\\config.properties");
		prop.load(fi);
		}
		catch (FileNotFoundException e)
		{e.printStackTrace();}
		
		catch(IOException e)
		{e.printStackTrace();}
		
	}
	
	
}
