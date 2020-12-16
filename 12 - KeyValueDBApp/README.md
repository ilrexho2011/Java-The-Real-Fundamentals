# Java Key-Value Database using Properties


These tutorials describe a very simple way to store your application settings to a Key-Value database using a special class called PropertiesDb which on the background is using Java Properties .


### Example Test Code

```JAVA
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
```