package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	public static final String pathName = "C:\\\\Users\\\\Suren\\\\git\\\\repository\\\\APIAutomation\\\\AutomationFramework\\\\src\\\\test\\\\java\\\\resources\\\\global.properties";
	
	//Method to extract the values from global.properties
	public static String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(pathName);
		prop.load(fis);
		return prop.getProperty(key);		
		}
		
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
	    JsonPath js = new JsonPath(resp);
	    return js.get(key).toString();
		}
		
	public RequestSpecification requestSpecification() throws IOException {
		
		if(null==req)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl"))		    
	    		.addFilter(RequestLoggingFilter.logRequestTo(log))
	    		.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
	
	    return req;
		}
		return req;
	}	

}
