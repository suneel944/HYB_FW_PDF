package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import keywordLib.Keywords;
import properties.orAmazonCommon;
import excelLib.ExcelLib;


public class Amazon_LoginPage_Multiple_Iteration
{
	Keywords k = new Keywords(this.getClass().getSimpleName());

	//Execution Data Sheet Declarations
	private static String[][] data = {{"SL.NO", "URL", "Search", "Result"}};
	private static int slNo = 1;


	ExcelLib elib = new ExcelLib();

	@BeforeMethod
	public void beforemethod(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl, String IterationCount) 
	{
		k.startTestReport(TestCaseName, TestCaseObjective, TestEnvironmentUrl, IterationCount);
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
				elib.writeWorkbook(append(data, updateData), "Execution_Status", "Execution", "execution_result.xlsx");
			}
			else
			{
				String[][] updateData = {{Integer.toString(slNo), url, search, "FAIL"}};
				elib.writeWorkbook(append(data, updateData), "Execution_Status", "Execution", "execution_result.xlsx");
			}

			//Enter One Plus 6t in search box
			k.inputText(orAmazonCommon.txtAmazonSearchBox, "Search", search);

			//Click the Search button
			k.clickObject(orAmazonCommon.btnSearch, "Search");

			//Verify products page is displayed
			k.verifyPageDisplayed(orAmazonCommon.resultText, "Products");

			//Click the first product
			k.clickObjectJs(orAmazonCommon.lnkFirstProduct, "First Product");

			//Switch Tab Control
			k.switchToSucceedingTab();

			//Wait till the button is enabled
			k.waitTillElementEnable(orAmazonCommon.btnBuyNow);

			//Click the buy now button
			k.clickObject(orAmazonCommon.btnBuyNow, "Buy Now");

			//Verify login page is displayed
			k.verifyPageDisplayed(orAmazonCommon.weLoginSection, "Login");

			//*******************************************************************
			//Increment
			slNo++;

		}
		catch(Exception e)
		{
			//Log the exception in the report and conclude
			k.logResultAndCaptureScreenshot("FAIL", "ERROR : Abrupt Exit", e.toString(), "NO");
		}
		finally
		{
			//Quit Driver
			try{k.closeAllBrowser();}
			catch (Exception e) {k.abortOnException(e);}

			//End Report
			k.endTestReport();
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