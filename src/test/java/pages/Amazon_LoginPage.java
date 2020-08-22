package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import keywordLib.Keywords;
import properties.orAmazonCommon;
import baseClassLib.BaseClass;


public class Amazon_LoginPage extends BaseClass
{
	 Keywords k = new Keywords(this.getClass().getSimpleName());

	@BeforeMethod
	public void beforemethod(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl) 
	{
		k.startTestReport(TestCaseName, TestCaseObjective, TestEnvironmentUrl);
	}

	@SuppressWarnings("static-access")
	@Test
	//Amazon Test Script
	public void amazon_test_001(String url, String browser, String search) throws Exception
	{	
		//******************************************************************************************
		/*Note: Maintain the script inside a try/catch/finally block. If there are any sudden abrupt 
		 * exit in the script due to known or unknown issue, that error will be logged in the report
		 */
		//******************************************************************************************
		try
		{

			//Launch Application
			k.launchApplication(browser, url);

			//Enter One Plus 6t in search box
			k.inputText(orAmazonCommon.txtAmazonSearchBox, "Search", search);

			//Click the Search button
			k.clickObject(orAmazonCommon.btnSearch, "Search");

			//Verify products page is displayed
			k.verifyPageDisplayed(orAmazonCommon.resultText, "Products");

			//Click the first product
			k.clickObjectJs(orAmazonCommon.lnkFirstProduct, "First Product");
			
			//Switch To Next Tab
			k.switchToSucceedingTab(); 

			//Wait till the button is enabled
			k.waitTillElementEnable(orAmazonCommon.btnBuyNow);

			//Click the buy now button
			k.clickObject(orAmazonCommon.btnBuyNow, "Buy Now");
			
			//Verify login page is displayed
			k.verifyPageDisplayed(orAmazonCommon.weLoginSection, "Login");
			
		}
		catch(Exception e)
		{
			//Log the exception in the report and conclude
			k.logResultAndCaptureScreenshot("FAIL", "ERROR : Abrupt Exit", e.toString(), "NO");
		}
		finally
		{
			//Quit Driver
			try{driver.quit();}
			catch (Exception e) {k.abortOnException(e);}

			//End Report
			k.endTestReport();
		}
	}
}