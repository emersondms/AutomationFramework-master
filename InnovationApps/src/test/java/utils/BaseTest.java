package utils;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.SDK;
import com.hp.lft.sdk.SDKConfigurationFactory;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;

public class BaseTest {
	public static BrowserType bType;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();	
	protected static Browser browser;
	
	@BeforeSuite(alwaysRun = true)
	public void Start_Engine() throws Exception {
		PropertiesManager.initializeProperties();

		PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");

		ModifiableSDKConfiguration config;
		config = SDKConfigurationFactory
				.loadConfigurationFromExternalPropertiesFile("src\\main\\resources\\leanft.properties");
		SDK.init(config);
		Reporter.init();

		extent = ExtentManager.createInstance(initReportFileName());
	}

	@BeforeClass(alwaysRun = true)
	public synchronized void beforeClass() {
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
	}

	@BeforeMethod(alwaysRun = true)
	public void launchBrowser(Method method) throws Exception {
		String browserType = "";
		try {
			browserType = PropertiesManager.getProperty("browser");
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (browserType.toUpperCase()) {
		case "IE":
			browser = BrowserFactory.launch(BrowserType.INTERNET_EXPLORER);
			break;
		case "FIREFOX":
			browser = BrowserFactory.launch(BrowserType.FIREFOX);
			break;
		case "CHROME":
			browser = BrowserFactory.launch(BrowserType.CHROME);
			break;
		default:
			browser = BrowserFactory.launch(BrowserType.CHROME);
			break;
		}

		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(method.getName());
		test.set(child);
		String url = PropertiesManager.getProperty("websiteURL");
		
		test.get().info(String.format("Precondition - Launch [%s] browser and navigate to URL [%s]",
				browserType.toUpperCase(), url));
		browser.navigate(url);		
		browser.sync();
	}

	@AfterMethod(alwaysRun = true)
	public synchronized void closeBrowser(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			((ExtentTest) test.get()).fail(result.getThrowable());
			String imgSrc = CaptureScreenshot.captureScreenshot(CaptureScreenshot.generateString(result));
			test.get().fail("Screenshot: ").addScreenCaptureFromPath(imgSrc);
		} else if (result.getStatus() == ITestResult.SKIP)
			((ExtentTest) test.get()).skip(result.getThrowable());
		else if (result.getStatus() == ITestResult.SUCCESS)
			((ExtentTest) test.get()).pass("Test case passed");
		browser.close();
	}

	@AfterSuite(alwaysRun = true)
	public static void tearDown() throws Exception {
		extent.flush();
		Reporter.generateReport();
		SDK.cleanup();

	}

	protected void assertTrue(ResultModel res) {
		try {
			if (res.isResult()) {
				test.get().pass(res.getMessage());
				Reporter.reportEvent(res.getStepName(), res.getMessage(), Status.Passed);

			} else {
				test.get().fail(res.getMessage());
				Reporter.reportEvent(res.getStepName(), res.getMessage(), Status.Failed);
				Assert.fail();
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	protected void assertTrue(List<ResultModel> listRes) throws ReportException {
		try {
			for (ResultModel res : listRes) {
				if (res.isResult()) {
					Reporter.reportEvent(res.getStepName(), res.getMessage(), Status.Passed);
					test.get().pass(res.getMessage());
				} else {
					Reporter.reportEvent(res.getStepName(), res.getMessage(), Status.Failed);
					test.get().fail(res.getMessage());
					Assert.fail();
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public static String initReportFileName() {
		String resultfolderPath = SystemVariable.USER_DIR + "\\ExtentReport";
		String suiteName = SystemVariable.SUITE_NAME;
		String system = SystemVariable.SYSTEM_UNDER_TEST;
		String summaryfileName;

		summaryfileName = String.format("SYSTEM_%s_TestSuite_%s_%s.html", system, suiteName, getDateTime());

		String outputpath = "";
		File summaryFile = new File(resultfolderPath + "\\" + summaryfileName);
		if (!summaryFile.exists()) {
			try {
				File dir = new File(resultfolderPath);
				dir.mkdir();
				summaryFile.createNewFile();
				outputpath = resultfolderPath + "\\" + summaryfileName;
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalStateException("Error occured during Creation of result file. " + e.getMessage());
			}
		}
		return outputpath;
	}

	private static Object getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmm");
		Date date = new Date();
		return dateFormat.format(date);
	}


}
