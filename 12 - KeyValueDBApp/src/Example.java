/**
 * ------------------------------------------------------------------------------
 *     Fundamental of Java - Simple key-value database application in Java
 * ------------------------------------------------------------------------------
 *
 */

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * This class contains the main method
 * 
 * @author IR on T3500
 *
 */

public class Example {
	
	public static void main(String[] args) {
		
		//Create Key-Value Database
		PropertiesDb propertiesDb = new PropertiesDb("settings.properties", false);
		Properties properties = propertiesDb.loadProperties();
		
		//Save or update properties
		propertiesDb.updateProperty("Name", "George");
		propertiesDb.updateProperty("Wife", "Liliane");
		
		propertiesDb.updateProperty("Favourite-Items", "Chocolate,Computers,Clothes");
		
		//Delete a property
		//propertiesDb.deleteProperty("Favourite-Items");
		
		//Load the properties
		Optional.ofNullable(properties.getProperty("Favourite-Items")).ifPresent(value -> {
			List<String> favouriteItems = Arrays.asList(value.split(","));
			
			//Lets print the List
			System.out.println(favouriteItems);
		});
		
	}
	
}
