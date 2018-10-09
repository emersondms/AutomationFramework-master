package utils;

import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
    
    public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance("test-output/extent.html");
    	
        return extent;
    }
    
    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        extent.setSystemInfo("Host Name", getComputerName());
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", SystemVariable.SYSTEM_UNDER_TEST);
        extent.setSystemInfo("URL", SystemVariable.URL_UNDER_TEST);
        return extent;
    }

	private static String getComputerName() {
		Map<String, String> env = System.getenv();
		if(env.containsKey("COMPUTERNAME"))
			return env.get("COMPUTERNAME");
		else if(env.containsKey("HOSTNAME"))
			return env.get("HOSTNAME");
		else
			return "Unknown Computer";
	}
}
