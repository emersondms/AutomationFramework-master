package utils;

import java.awt.image.RenderedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.testng.ITestResult;

public class CaptureScreenshot extends BaseTest{
	public static String captureScreenshot(String fileName) {
		try {
			RenderedImage srcImg = browser.getSnapshot();
			String destFile = "ExtentReport/Screenshot/" + fileName + ".png";
			File destImg = new File(destFile);
			ImageIO.write(srcImg, "png", destImg);
			return destFile.replace("ExtentReport", ".");
		} catch (Exception e) {
			return "";
		}
		
	}
	
	public static String generateString(ITestResult result) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		String fileName = result.getName() + "_" + dateFormat.format(date);
		return fileName;
	}
}
