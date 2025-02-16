package utility;

import java.io.FileReader;
import java.util.Properties;

public class PropertiesReader {

	Properties prop;
	
	/*
	 * This method is used to return value from the properties file "config.prperties -> scr/main/resources"
	 * @param: key
	 */
	public String getDataFromPropertiesFile(String key) {
		
		FileReader fileReader;
		try {
			fileReader = new FileReader(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
			prop = new Properties();  
			prop.load(fileReader);  
		}catch (Exception e) {
			e.printStackTrace();
		}  
		return prop.getProperty(key);		
	}
}
