package com.bosch.test.login;

import java.util.Map;
import org.testng.annotations.Test;

import repositories.descriptiveprogramming.HeaderPage;
import repositories.descriptiveprogramming.HomePage;
import repositories.descriptiveprogramming.LoginPage;
import utils.BaseTest;
import utils.CSVAnnotation.CSVFileParameters;
import utils.GenericDataProvider;
import utils.ResultModel;

public class LoginTest extends BaseTest {

	@Test(priority = 0, groups = { "Smoke",
			"Regression" }, dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
	@CSVFileParameters(path = "src\\test\\resources\\test-data\\LoginTestData.csv", delimiter = "|")
	public void login_DescriptiveProgramming(int rowNo, Map<String, String> inputDataMap) {
		try {
			HeaderPage headerPage = new HeaderPage(browser);
			LoginPage loginPage = new LoginPage(browser);
			HomePage homePage = new HomePage(browser);

			test.get().info("Step 1 - Navigate to Login page");
			assertTrue(headerPage.clickOnLoginLink1());

			test.get().info(String.format("Step 2 - Logging into the system with valid username and password"));
			assertTrue(loginPage.doLogin1(inputDataMap.get("username"), inputDataMap.get("password")));

			test.get().info("Step 3 - Verify that Login into the system successfully");
			assertTrue(homePage.verifyLoginSuccessful1(inputDataMap.get("displayname")));

			test.get().info("Step 4 - Logout successfully");
			assertTrue(headerPage.logout1());
		} catch (Exception e) {
			String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
			assertTrue(new ResultModel(false,
					String.format("%s method throws an exception: %s", methodName, e.getMessage())));
		}
	}

	/*@Test(priority = 0, groups = { "Smoke",
			"Regression" }, dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
	@CSVFileParameters(path = "src\\test\\resources\\test-data\\LoginTestData.csv", delimiter = "|")
	public void login_fail(int rowNo, Map<String, String> inputDataMap) {
		try {
			HeaderPage headerPage = new HeaderPage(browser);
			LoginPage loginPage = new LoginPage(browser);
			HomePage homePage = new HomePage(browser);

			test.get().info("Step 1 - Navigate to Login page");
			assertTrue(headerPage.clickOnLoginLink1());

			test.get().info(String.format("Step 2 - Logging into the system with valid username and password"));
			assertTrue(loginPage.doLogin1(inputDataMap.get("username"), inputDataMap.get("displayname")));

			test.get().info("Step 3 - Verify that Login into the system successfully");
			assertTrue(homePage.verifyLoginSuccessful1(inputDataMap.get("displayname")));

			test.get().info("Step 4 - Logout successfully");
			assertTrue(headerPage.logout1());
		} catch (Exception e) {
			String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
			assertTrue(new ResultModel(false,
					String.format("%s method throws an exception: %s", methodName, e.getMessage())));
		}
	}*/

	/*
	 * @Test(priority = 0, groups = { "Smoke", "Regression" }, dataProvider =
	 * "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
	 * 
	 * @CSVFileParameters(path =
	 * "src\\test\\resources\\test-data\\LoginTestData.csv", delimiter = "|")
	 * public void login_ApplicationModel(int rowNo, Map<String, String>
	 * inputDataMap) { try { HeaderPage headerPage = new HeaderPage(browser);
	 * LoginPage loginPage = new LoginPage(browser); HomePage homePage = new
	 * HomePage(browser);
	 * 
	 * test.get().info("Step 1 - Navigate to Login page");
	 * assertTrue(headerPage.clickOnLoginLink2());
	 * 
	 * test.get().info(String.
	 * format("Step 2 - Logging into the system with valid username and password"
	 * )); assertTrue(loginPage.doLogin1(inputDataMap.get("username"),
	 * inputDataMap.get("password")));
	 * 
	 * test.get().info("Step 3 - Verify that Login into the system successfully"
	 * );
	 * assertTrue(homePage.verifyLoginSuccessful1(inputDataMap.get("displayname"
	 * )));
	 * 
	 * test.get().info("Step 4 - Logout successfully");
	 * assertTrue(headerPage.logout2()); } catch (Exception e) { String
	 * methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
	 * assertTrue(new ResultModel(false,
	 * String.format("%s method throws an exception: %s", methodName,
	 * e.getMessage()))); } }
	 */
}
