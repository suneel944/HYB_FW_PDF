package Amazon_Pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Amazon_Properties.OR_Common;
import keywordLib.Keywords;
import baseClassLib.BaseClass;
import excelLib.ExcelLib;


public class Amazon_LoginPage_Multiple_Iteration extends BaseClass
{
	Keywords k = new Keywords(this.getClass().getSimpleName());

	//Execution Data Sheet Declarations
	private static String[][] data = {{"SL.NO", "URL", "Search", "Result"}};
	private static int slNo = 1;

	ExcelLib elib = new ExcelLib();

	@BeforeMethod
	public void beforemethod(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl, String IterationCount) 
	{
		k.startReport(TestCaseName, TestCaseObjective, TestEnvironmentUrl, IterationCount);
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
			if(k.launchApplication(browser, url))
			{
				String[][] updateData = {{Integer.toString(slNo), url, search, "PASS"}};
				elib.writeWorkBook(append(data, updateData));
			}
			else
			{
				String[][] updateData = {{Integer.toString(slNo), url, search, "FAIL"}};
				elib.writeWorkBook(append(data, updateData));
			}

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

			//*******************************************************************
			//Increment
			slNo++;

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

	//Append Array
	private static String [][] append(String[][] a, String[][] b)
	{
		String[][] result = new String[a.length+b.length][];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}
}