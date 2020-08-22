package testCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClassLib.BaseClass;
import excelLib.ExcelLib;
import pages.Amazon_LoginPage;

public class Amazon_Test_001 extends BaseClass
{
	@DataProvider
	public Object[][] data() throws IOException 
	{
		ExcelLib xl = new ExcelLib("Test", this.getClass().getSimpleName());
		return xl.getTestData();
	}
	
	//Test Case Objective
	/**
	 * Test Case Objective
	 */
	private static String TestCaseObjective = "Validate Ordering of a product in Amazon website";

	@Test(dataProvider = "data")
	/*Note: While providing the arguments, please make sure 
	 * that the order of arguments passed inside run function 
	 * should be same as the order respected in data sheet, 
	 * otherwise script wont run
	 * OR In simple words, "the order of the arguments must be respected"*/
	public void Test_pdf_output_for_steps(String url, String browserName, String search) throws Exception
	{
		//Instantiate the page
		Amazon_LoginPage aml = new Amazon_LoginPage();
		
		//Test case
		aml.beforemethod(this.getClass().getSimpleName(), TestCaseObjective, url);
		aml.amazon_test_001(url, browserName, search);
	}
}