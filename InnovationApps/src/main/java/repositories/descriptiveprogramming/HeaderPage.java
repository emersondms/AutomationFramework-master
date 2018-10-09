package repositories.descriptiveprogramming;

import java.util.ArrayList;
import java.util.List;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.Link;
import com.hp.lft.sdk.web.LinkDescription;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.WebElementDescription;

//import repositories.appmodels.HeaderPageModel;
import utils.BasePage;
import utils.ResultModel;

public class HeaderPage extends BasePage{

	private Browser browser;
	//private HeaderPageModel headerPageModel;
	
	public HeaderPage(Browser browser) throws GeneralLeanFtException {
		super();
		this.browser = browser;	
		//this.headerPageModel = new HeaderPageModel(browser);
	}
	
	//1.Descriptive Programming. 
	private Link lnkLogin(){
		try {
			return browser.describe(Link.class, new LinkDescription.Builder().xpath("//a[@href='javascript: doLoginForGuest();']").build());
		} catch (GeneralLeanFtException e) {
			return null;
		}
	}
	
	private WebElement iconUserProfile(){
		try {
			return browser.describe(WebElement.class,  new WebElementDescription.Builder().xpath("//div[@class='hype-user-context-menu']//img").build());
		} catch (GeneralLeanFtException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private WebElement linkLogout(){
		try {
			return browser.describe(WebElement.class,  new WebElementDescription.Builder().xpath("//a[@href='javascript:doLogout()']").build());
		} catch (GeneralLeanFtException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	//1.Descriptive Programming. 
	public ResultModel clickOnLoginLink1(){
		return click(lnkLogin(), "[Login] link");
	}

	//1.Descriptive Programming. 
	public List<ResultModel> logout1(){
		List<ResultModel> listRes = new ArrayList<>();
		listRes.add(click(iconUserProfile(), "[User Profile] icon on Header Page"));
		listRes.add(click(linkLogout(), "[LOG OUT] link"));
		return listRes;		
	}
	
/*	//2. Application Model
	public ResultModel clickOnLoginLink2() throws GeneralLeanFtException{
		return click(headerPageModel.headerPage().linkLogin(), "[Login] link");
	}
	
	//2. Application Model
	public List<ResultModel> logout2(){
		List<ResultModel> listRes = new ArrayList<>();
		listRes.add(click(headerPageModel.headerPage().iconUserProfile(), "[User Profile] icon on Header Page"));
		listRes.add(click(headerPageModel.headerPage().linkLogout(), "[LOG OUT] link"));
		return listRes;		
	}*/
	
}
