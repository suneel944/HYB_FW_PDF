package Amazon_Pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import keywordLib.Keywords;
import Amazon_Properties.OR_Common;
import baseClassLib.BaseClass;


public class Amazon_LoginPage extends BaseClass
{
	 Keywords k = new Keywords(this.getClass().getSimpleName());

	@BeforeMethod
	public void beforemethod(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl) 
	{
		k.startReport(TestCaseName, TestCaseObjective, TestEnvironmentUrl);
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
			k.inputText(OR_Common.txtAmazonSearchBox, "Search", search);

			//Click the Search button
			k.clickObject(OR_Common.btnSearch, "Search");

			//Verify products page is displayed
			k.verifyPageDisplayed(OR_Common.weProductPageNavigationContainer, "Products");

			//Click the first product
			k.clickObjectJs(OR_Common.lnkFirstProduct, "First Product");

			//Switch Tab Control
			k.switchToSucceedingTab();

			//Wait till the button is enabled
			k.waitTillElementEnable(OR_Common.btnBuyNow);

			//Click the buy now button
			k.clickObject(OR_Common.btnBuyNow, "Buy Now");

			//Verify login page is displayed
			k.verifyPageDisplayed(OR_Common.weLoginSection, "Login");
		}
		catch(Exception e)
		{
			//Log the exception in the report and conclude
			k.logResultAndCaptureImage("FAIL", "ERROR : Abrupt Exit", e.toString(), "NO");
		}
		finally
		{
			//Quit Driver
			driver.quit();

			//End Report
			k.endReport();
		}
	}
}