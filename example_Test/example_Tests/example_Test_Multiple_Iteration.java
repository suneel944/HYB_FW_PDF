package example_Tests;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import example_Pages.example_Page_Multiple_Iteration;
import excelLib.ExcelLib;

public class example_Test_Multiple_Iteration
{
	/**
	 * Test Case Iteration Count
	 */
	private static String IterationCount;
	//*******************************************************************************
	@DataProvider
	public Object[][] data() throws IOException 
	{
		ExcelLib xl = new ExcelLib("Excel Sheet Name", this.getClass().getSimpleName());
		IterationCount = String.valueOf(xl.iterationCount());
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
		example_Page_Multiple_Iteration example = new example_Page_Multiple_Iteration();
		
		//Test case
		example.beforemethod(this.getClass().getSimpleName(), TestCaseObjective, url, IterationCount);
		example.exampleFunctionalityToBePerformed(url, browserName, search);
	}
	//*******************************************************************************
}