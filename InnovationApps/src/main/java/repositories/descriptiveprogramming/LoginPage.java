package repositories.descriptiveprogramming;

import java.util.ArrayList;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.EditFieldDescription;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.WebElementDescription;

//import repositories.appmodels.LoginPageModel;
import utils.BasePage;
import utils.ResultModel;

public class LoginPage extends BasePage {
	private Browser browser;
	//private LoginPageModel loginPageModel;
	
	public LoginPage(Browser browser) throws GeneralLeanFtException {
		this.browser = browser;
	//	this.loginPageModel = new LoginPageModel(browser);
	}
	
	private EditField tbxUsername() {
		try {
			return browser.describe(EditField.class, new EditFieldDescription.Builder().xpath("//input[@id='user']").build());
			//return browser.describe(EditField.class, new EditFieldDescription.Builder().xpath("//div[@id='sb_ifc0']").build());
			
		} catch (GeneralLeanFtException e) {
			return null;
		}
	}
	
	private EditField tbxPassword() {
		try {
			return browser.describe(EditField.class, new EditFieldDescription.Builder().xpath("//input[@name='pass']").build());
		} catch (GeneralLeanFtException e) {
			return null;
		}
	}
	
	private WebElement btnSignIn() {
		try {
			return browser.describe(WebElement.class, new WebElementDescription.Builder().xpath("//input[@type='submit']").build());
		} catch (GeneralLeanFtException e) {
			return null;
		}
	}
	
	//Sol 1: Descriptive Programming
	public ArrayList<ResultModel> doLogin1(String username, String password) throws GeneralLeanFtException {
		ArrayList<ResultModel> lstResultModel = new ArrayList<ResultModel>();
		lstResultModel.add(setValue(tbxUsername(), "[User Name] textbox", username));
		lstResultModel.add(setValue(tbxPassword(), "[Password] textbox", password));
		lstResultModel.add(click(btnSignIn(), "[Login] button"));
		return lstResultModel;
	}
	
	/*public ArrayList<ResultModel> doLogin2(String username, String password) throws GeneralLeanFtException {
		ArrayList<ResultModel> lstResultModel = new ArrayList<ResultModel>();
		lstResultModel.add(setValue(loginPageModel.loginPage().txtUserName(), "[User Name] textbox", username));
		lstResultModel.add(setValue(loginPageModel.loginPage().txtPassword(), "[Password] textbox", password));
		lstResultModel.add(click(loginPageModel.loginPage().btnLogin(), "[Login] button"));
		return lstResultModel;
	}
	*/
	
}
