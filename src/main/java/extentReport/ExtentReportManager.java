package extentReport;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
private static ExtentReports extent;
	
	public static ExtentReports createInstance() {
		
		String fileName = getReportName();
		String directory = System.getProperty("user.dir") + "/reports";
		
		new File(directory).mkdirs();
		
		String path = directory + fileName;
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Rohit Framework");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);
		
		return extent;
	}
	
	public static String getReportName() {
		Date d = new Date();
		String fileName = "Automation Report_"+d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}
}
