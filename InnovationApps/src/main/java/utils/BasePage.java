package utils;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keyboard;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.WebElement;

public class BasePage {
	private int timeout = 60;

	public ResultModel setValue(EditField editField, String eleName, String value) {
		try {
			editField.exists(timeout);
			editField.highlight();
			editField.setValue(value);
			if (eleName.toLowerCase().contains("password")) {
				value = "********";
			}
			return new ResultModel(true, String.format("Successfully entered the text [%s] into %s.", value, eleName));
		} catch (Exception e) {
			return new ResultModel(false, String.format("Unable to enter the text [%s] into %s. An error occurred: %s",
					value, eleName, e.getMessage()));
		}
	}

	public ResultModel click(WebElement element, String eleName) {
		try {
			element.exists(timeout);
			element.highlight();
			element.click();
			return new ResultModel(true, String.format("Successfully clicked on %s", eleName));
		} catch (Exception e) {
			return new ResultModel(false,
					String.format("Unable to click on %s. An error occurred: %s", eleName, e.getMessage()));
		}
	}

	public ResultModel sendString(EditField editField, String eleName, String value) {
		try {
			editField.exists(timeout);
			editField.highlight();
			editField.click();
			Keyboard.sendString(value);
			return new ResultModel(true, String.format("Successfully entered the text [%s] into %s.", value, eleName));
		} catch (Exception e) {
			return new ResultModel(false, String.format("Unable to enter the text [%s] into %s. An error occurred: %s",
					value, eleName, e.getMessage()));
		}
	}

	public ResultModel isExists(WebElement ele, String eleName) {
		ResultModel res = new ResultModel();
		try {
			if (ele.exists(timeout)) {
				ele.highlight();
				res.setMessage(String.format("The %s displayed as expected", eleName));
				return res;
			} else {
				res.setResult(false);
				res.setMessage(String.format("Could not find out %s which is not expected", eleName));
				return res;
			}
		} catch (GeneralLeanFtException e) {
			res.setMessage(String.format("Could not find out %s. ERROR: %s", eleName, e.getMessage()));
			res.setResult(false);
			return res;
		}
	}

	public ResultModel compareText(WebElement ele, String eleName, String expectedText) {
		ResultModel res = new ResultModel();
		try {
			if (ele.exists(timeout)) {
				ele.highlight();
				if (ele.getInnerText().trim().contains(expectedText.trim())) {
					res.setMessage(String.format("The text of %s displayed as expected: %s", eleName, expectedText));
					return res;
				} else {
					res.setResult(false);
					res.setMessage(
							String.format("The text of %s does not display which is not expcted: %s. Actual: %s",
									eleName, expectedText, ele.getInnerText()));
					return res;
				}
			} else {
				res.setResult(false);
				res.setMessage(String.format("Could not find out %s which is not expected", eleName));
				return res;
			}
		} catch (GeneralLeanFtException e) {
			res.setMessage(String.format("Could not find out %s. ERROR: %s", eleName, e.getMessage()));
			res.setResult(false);
			return res;
		}
	}

	public void hardDelay(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
