package example_Tests;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import example_Pages.examplePage;
import excelLib.ExcelLib;

public class example_Test_001
{
	//*******************************************************************************
	@DataProvider
	public Object[][] data() throws IOException 
	{
		ExcelLib xl = new ExcelLib("Excel Sheet Name", this.getClass().getSimpleName());
		return xl.getTestData();
		
	}
	//*******************************************************************************
	//Test Case Objective
	/**
	 * Test Case Objective
	 */
	private static String TestCaseObjective = "Validate Ordering of a product in Amazon website";
	
	//*******************************************************************************
	@Test(dataProvider = "data")
	/*Note: While providing the arguments, please make sure 
	 * that the order of arguments passed inside run function 
	 * should be same as the order respected in data sheet, 
	 * otherwise script wont run
	 * OR In simple words, "the order of the arguments must be respected"*/
	
	public void testRun(String url, String browserName, String search) throws Exception
	{
		//Instantiate the page
		examplePage example = new examplePage();
		
		//Test case
		example.beforemethod(this.getClass().getSimpleName(), TestCaseObjective, url);
		example.exampleFunctionalityToBePerformed(url, browserName, search);
	}
	//*******************************************************************************
}