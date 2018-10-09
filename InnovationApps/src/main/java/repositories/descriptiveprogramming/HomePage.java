package repositories.descriptiveprogramming;

import java.util.ArrayList;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.WebElementDescription;

//import repositories.appmodels.HomePageModel;
import utils.BasePage;
import utils.ResultModel;

public class HomePage extends BasePage {
	private Browser browser;
	//private HomePageModel homePageModel;

	public HomePage(Browser browser) throws GeneralLeanFtException {
		super();
		this.browser = browser;
	//	this.homePageModel = new HomePageModel(browser);
	}

	private WebElement iconProfile() {
		try {
			return browser.describe(WebElement.class, new WebElementDescription.Builder()
					.xpath("//main[@id='main']//img[@class='hype--user-picture']").build());
		} catch (GeneralLeanFtException e) {
			return null;
		}

	}

	private WebElement lblAccoutName() {
		try {
			return browser.describe(WebElement.class, new WebElementDescription.Builder()
					.xpath("//main[@id='main']//h1[@class='hype-ce--category-heading hype--heading-bold']").build());
		} catch (GeneralLeanFtException e) {
			return null;
		}
	}

	// Sol 1: Descriptive Programming
	public ArrayList<ResultModel> verifyLoginSuccessful1(String accountName) {
		ArrayList<ResultModel> lstResultModel = new ArrayList<ResultModel>();

		lstResultModel.add(isExists(iconProfile(), "[User Profile] icon"));
		lstResultModel.add(compareText(lblAccoutName(), "[Acount Display Name] web element", accountName));

		return lstResultModel;
	}

	/*// Sol 2: Application Models
	public ArrayList<ResultModel> verifyLoginSuccessful12(String accountName) {
		ArrayList<ResultModel> lstResultModel = new ArrayList<ResultModel>();

		lstResultModel.add(isExists(homePageModel.homePage().iconProfile(), "[User Profile] icon"));
		lstResultModel.add(compareText(homePageModel.homePage().lbHelloMessage(), "[Acount Display Name] web element", accountName));

		return lstResultModel;
	}*/

}
