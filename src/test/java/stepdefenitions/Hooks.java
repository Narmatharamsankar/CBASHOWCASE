package stepdefenitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;



public class Hooks {
    // set the base uri and base path for the api request to be sent 
	
	@Before
	public void setUp() throws FileNotFoundException, IOException{ 
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));
		
		RestAssured.baseURI = prop.getProperty("uri");
		RestAssured.basePath = prop.getProperty("endpoint");
	}
	
	@After
	public void tearDown(){ 
	}

}
