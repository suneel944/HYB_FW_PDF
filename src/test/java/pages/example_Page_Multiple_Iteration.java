package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import excelLib.ExcelLib;
import keywordLib.Keywords;

@SuppressWarnings("unused")
public class example_Page_Multiple_Iteration
{
	//Keyword Library Instantiation
	Keywords k = new Keywords(this.getClass().getSimpleName());

	//Execution Data Sheet Declarations - Column Header
	private static String[][] data = {{"Column 0 Name...Column N Name"}};
	//Initialize Serial Number
	private static int slNo = 1;

	//ExcelLib Library Instantiation
	ExcelLib elib = new ExcelLib();

	//******************************************************************************************
	@BeforeMethod
	public void beforemethod(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl, String IterationCount) 
	{
		//Initiate Report
		k.startTestReport(TestCaseName, TestCaseObjective, TestEnvironmentUrl, IterationCount);;
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
			 * 	*********************Type your code here*********************
			 * 
			 * 	Example For Execution Data Write To Specified Excel Sheet
			 * 	if (condition is true)
			 * 	{
			 * 		String[][] updateData = {{Integer.toString(slNo), url, search, "PASS"}};
			 *		elib.writeWorkbook(append(data, updateData), "SheetName", "FolderName", "WorkbookName.xlsx/xls");
			 * 	}
			 * 	else
			 * 	{
			 * 		String[][] updateData = {{Integer.toString(slNo), url, search, "FAIL"}};
			 *		elib.writeWorkbook(append(data, updateData), "SheetName", "FolderName", "WorkbookName.xlsx/xls");
			 * 	}
			 * 
			 *
			 * 
			 * 
			 * 
			 *  
			 * 
			 * 
			 * 
			 * 
			 */
			//*******************************************************************
			//Increment The Serial Number For Data Representation Purpose In Excel Sheet - Can Be Removed If Not Required
			slNo++;
		}
		catch(Exception e)
		{
			//Capture the abrupt exit of the script through available reporting function in keyword library
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
		//******************************************************************************************
	}

	//Append Array Method
	private static String [][] append(String[][] a, String[][] b)
	{
		String[][] result = new String[a.length+b.length][];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}
}