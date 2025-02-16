package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {

	// Take screenshot for failed test case
		public static String takeScreenShoot(WebDriver driver, String methodName) {
			String fileName = getScreenShotName(methodName);
			String directory = System.getProperty("user.dir")+"/screeshots/";
			new File(directory).mkdirs();
			String path = directory + fileName;
			try {
				File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(path));
			}catch (Exception e) {
				e.printStackTrace();
			}
			return path;
		}
				
		public static String getScreenShotName(String methodName) {
			String fileName = java.time.LocalDate.now()+"-"+java.time.LocalTime.now()+"-"+methodName+".jpg";		
			return fileName;
		}
}
