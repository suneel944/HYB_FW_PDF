package example_Pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClassLib.BaseClass;
import keywordLib.Keywords;

public class example_Page extends BaseClass
{
	//Keyword Library Instantiation
	Keywords k = new Keywords(this.getClass().getSimpleName());

	//******************************************************************************************
	@BeforeMethod
	public void beforemethod(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl) 
	{
		//Initiate Report
		k.startTestReport(TestCaseName, TestCaseObjective, TestEnvironmentUrl);
	}
	//******************************************************************************************

	@SuppressWarnings("static-access")
	@Test
	//******************************************************************************************
	//TestCaseName : Provide Specific name
	//TestCaseObjective : Provide Description about the functionality being performed
	//******************************************************************************************

	//Test Page
	public void exampleFunctionalityToBePerformed(String url, String browserName, String search) throws Exception
	{
		//******************************************************************************************
		/*Note: Maintain the script inside a try/catch/finally block. If there are any sudden abrupt 
		 * exit in the script due to known or unknown issue, that error will be logged in the report
		 */
		//******************************************************************************************

		try
		{
			//Utilize the keyword library to develop the required activity to be performed here in try block
			/*
			 * 
			 * 
			 * 
			 *  Type your code here
			 * 
			 * 
			 * 
			 * 
			 */
		}
		catch(Exception e)
		{
			//Capture the abrupt exit of the script through available reporting function in keyword library
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
		//******************************************************************************************
	}

}