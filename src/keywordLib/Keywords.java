package keywordLib;

//TestNG Imports
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

//Java awt Imports
import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

//Java Util Imports
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//ImageIO imports
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

//Selenium Imports
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.itextpdf.text.pdf.PdfWriter;

//ITextPDF Imports
import com.google.common.base.Function;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;

import baseClassLib.BaseClass;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Keywords extends BaseClass 
{
	//********************************************************************
	//Declarations
	//********************************************************************
	static String ClassName;

	static String TestCaseName;

	static File FILE;

	static File imFILE;

	static StopWatch pageLoad = new StopWatch();
	StopWatch timer = new StopWatch();

	/**
	 * Document
	 */
	static Document document;

	/**
	 * PdfWriter
	 */
	static PdfWriter writer;

	/**
	 * PdfPTables
	 */
	static PdfPTable statusTable;

	/**
	 * PdfCell
	 */
	static PdfPCell cell;

	/**
	 * Overal Run Result
	 */
	private static boolean overalRunResultFlag = false;

	/**
	 * Passed Step Count
	 */
	private static int passStepCount = 0;

	/**
	 * Failed Step Count
	 */
	private static int failStepCount = 0;

	//*******************************
	//Date Declarations For Run Summary
	//*******************************
	static java.util.Date runStartTimeStamp = new java.util.Date();
	static String[] date1 = runStartTimeStamp.toString().split(" ");
	static String[] date2 = date1[3].split(":");
	static String dateval = date2[0] + date2[1] + date2[2];

	//Current Directory
	private static String currentDir = System.getProperty("user.dir");

	//Dynamic Report Folder Path
	private static String dateFolder;

	//********************************************************************
	//Constructor
	//********************************************************************

	public Keywords(String testCaseName)
	{
		ClassName = testCaseName;
	}

	//******************************************************************************************************************************
	//ScreenShot functions
	//******************************************************************************************************************************
	protected static String takeNativeScreenshot()
	{
		//****************************************************************************
		//Folder path creation
		//****************************************************************************
		//If Images folder is not present create an image folder in current directory
		imFILE = new File(currentDir +"\\images");
		if (!imFILE.exists())
		{
			imFILE.mkdir();
		}

		//If Screenshots folder is not present, then create a screenshot folder in current directory
		imFILE = new File(currentDir + "\\images\\Screenshots");
		if (!imFILE.exists())
		{
			imFILE.mkdir();
		}

		//****************************************************************************
		//Image Time Stamp
		java.util.Date imgTimeStamp = new java.util.Date();
		String[] imgdate1 = imgTimeStamp.toString().split(" ");
		String[] imgdate2 = imgdate1[3].split(":");
		String imgdateval = imgdate2[0] + imgdate2[1] + imgdate2[2]; 

		//ImagePath
		String imgPath = currentDir +"\\images\\ScreenShots\\page_"+imgdate1[1] + imgdate1[2] + imgdateval+".png";

		//Capture Image
		try
		{
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File(imgPath));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//return Image path
		return imgPath;
	}

	protected static String takeScreenshot()
	{
		//****************************************************************************

		//Image Time Stamp
		java.util.Date imgTimeStamp = new java.util.Date();
		String[] imgdate1 = imgTimeStamp.toString().split(" ");
		String[] imgdate2 = imgdate1[3].split(":");
		String imgdateval = imgdate2[0] + imgdate2[1] + imgdate2[2]; 

		//ImagePath
		String imgPath = currentDir +"\\images\\ScreenShots\\page_"+imgdate1[1] + imgdate1[2] + imgdateval+".png";

		//****************************************************************************
		try
		{
			//****************************************************************************
			//Folder path creation
			//****************************************************************************
			//If Images folder is not present create an image folder in current directory
			imFILE = new File(currentDir +"\\images");
			if (!imFILE.exists())
			{
				imFILE.mkdir();
			}

			//If Screenshots folder is not present, then create a screenshot folder in current directory
			imFILE = new File(currentDir + "\\images\\Screenshots");
			if (!imFILE.exists())
			{
				imFILE.mkdir();
			}

			//GetScreenShot Method Directory and Image File
			File getSreenShotMethodImageFile = new File (imgPath);

			//Take Screenshot of viewable area
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//Write Screenshot to a file
			try 
			{
				FileUtils.copyFile(scrFile, getSreenShotMethodImageFile);
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try 
			{
				terminateIfWebDriverExecution(e);
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return imgPath;
	}

	//******************************************************************************************************************************
	//******************************************************************************************************************************
	//******************************************************************************************************************************
	//Reporting Keywords
	//******************************************************************************************************************************
	//******************************************************************************************************************************
	//******************************************************************************************************************************

	//******************************************************************************************************************************
	//Start Report
	//******************************************************************************************************************************

	public void startReport(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl)
	{	

		Keywords.TestCaseName = TestCaseName;
		pageLoad.reset();
		pageLoad.start();

		//****************************************************************************
		//Folder Path Creation
		//****************************************************************************

		//Create pdf_Reports folder if it is not created
		FILE = new File(currentDir+"\\pdf_Reports");
		if (!FILE.exists())
		{
			FILE.mkdir();
		}

		dateFolder = currentDir+"\\pdf_Reports\\"+date1[1]+"_"+date1[2]+"_"+date1[5];

		FILE = new File(dateFolder);
		if (!FILE.exists())
		{
			FILE.mkdir();
		}

		//Create page specific folder 
		FILE = new File(dateFolder+"\\"+ClassName);
		if (!FILE.exists())
		{
			FILE.mkdir();
		}
		try
		{
			document = new Document(PageSize.A4);
			writer = PdfWriter.getInstance(document, new FileOutputStream(new File(dateFolder+"\\"+ClassName+"\\"+Keywords.TestCaseName+ "_" + date1[1] + date1[2] + dateval +".pdf")));
			document.open();

			//***************************************************************************************************************************
			//Main Heading
			//***************************************************************************************************************************
			//Test Report Name

			//Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			//Add Main Heading
			Font blackTimes = new Font(FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
			Chunk mainHeading = new Chunk(TestCaseName, blackTimes);
			Paragraph p = new Paragraph(mainHeading);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);

			//Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			//Add a dummy line
			document.add(new Paragraph("\n"));

			//***************************************************************************************************************************
			//Test Case Details
			//***************************************************************************************************************************
			//Test case Name
			document.add(new Paragraph("Testcase Name : " +TestCaseName, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Test Objective
			document.add(new Paragraph("Test Objective : " +TestCaseObjective, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Test Environment
			document.add(new Paragraph("Test Environment : " +TestEnvironmentUrl, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Java Version
			document.add(new Paragraph("Java Version : " +System.getProperty("java.version"), new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Host Name
			document.add(new Paragraph("Host Name : " +InetAddress.getLocalHost().getHostName(), new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Operating System
			document.add(new Paragraph("Operating System : " +System.getProperty("os.name"), new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Add a dummy line
			document.add(new Paragraph("\n"));

			//Add a line separator
			document.add(new LineSeparator(0.5f, 100, null, 0, -5));

			Image reportLogo = Image.getInstance(currentDir+"\\hybrid_logo.png");
			//If image size exceeds a threshold value decrease it to below size
			if ((reportLogo.getWidth()>525.00) | (reportLogo.getHeight()>500.00))
			{
				reportLogo.scaleToFit(500, 600);
				reportLogo.setAlignment(Element.ALIGN_CENTER);
			}

			//Add DXC Logo
			document.add(reportLogo);

			//Add a new page/ page break
			document.newPage();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void logResultAndCaptureImage(String Status, String StepName, String StepDescription, String screenCapture, String ...args) 
			throws DocumentException, MalformedURLException, Exception
	{
		java.util.Date date1 = new java.util.Date();

		//******************************************************************************************************************************
		//Basic Table format
		//******************************************************************************************************************************
		try
		{
			Font blackTimesNormal = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
			Font blackTimesBold = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);

			statusTable = new PdfPTable(new float[]{.5f, .5f, .2f, .6f});
			Chunk stepDetails = new Chunk("Step Details", blackTimesBold);
			Paragraph p = new Paragraph(stepDetails);
			p.setAlignment(Element.ALIGN_LEFT);
			cell = new PdfPCell(p);
			cell.setColspan(4);
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			statusTable.addCell(cell);

			Chunk stepNameHeading = new Chunk("Step Name", blackTimesBold);
			cell = new PdfPCell(new Paragraph(stepNameHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			statusTable.addCell(cell);

			Chunk stepDescriptionHeading = new Chunk("Step Description", blackTimesBold);
			cell = new PdfPCell(new Paragraph(stepDescriptionHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			statusTable.addCell(cell);

			Chunk statusHeading = new Chunk("Status", blackTimesBold);
			cell = new PdfPCell(new Paragraph(statusHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			statusTable.addCell(cell);

			Chunk timeHeading = new Chunk("Time", blackTimesBold);
			cell = new PdfPCell(new Paragraph(timeHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			statusTable.addCell(cell);

			//******************************************************************************************************************************
			//Appending Data To Table
			//******************************************************************************************************************************
			//Step name
			Chunk stepName = new Chunk(StepName, blackTimesNormal);
			cell = new PdfPCell(new Paragraph(stepName));
			statusTable.addCell(cell);
			//Step description
			Chunk stepDescription = new Chunk(StepDescription, blackTimesNormal);
			cell = new PdfPCell(new Paragraph(stepDescription));
			statusTable.addCell(cell);

			//Status
			if (Status.equalsIgnoreCase("PASS"))
			{
				Font green = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(39, 174, 96));
				Chunk greenStatus = new Chunk(Status, green);
				cell = new PdfPCell(new Paragraph(greenStatus));
				statusTable.addCell(cell);

				//Increment pass step count
				passStepCount+=1;
			}
			else if (Status.equalsIgnoreCase("Fail"))
			{
				Font red = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(231, 76, 60));
				Chunk redStatus = new Chunk(Status, red);
				cell = new PdfPCell(new Paragraph(redStatus));
				statusTable.addCell(cell);

				//Change the result flag to "True"
				overalRunResultFlag = true;

				//Increment fail step count
				failStepCount+=1;
			}

			//Time
			Chunk time = new Chunk(date1.toLocaleString(), blackTimesNormal);
			cell = new PdfPCell(new Paragraph(time));
			statusTable.addCell(cell);

			//Update Report
			updateReport();

			if (args.length==1)
			{
				document.add(new Paragraph("Page Load Time : " +args[0]+ "secs", new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));
			}

			//Add a dummy line
			document.add(new Paragraph("\n"));

			//Screen capture if needed
			//Test Step Details: Along With Image
			//Create a Dynamic table with respect to number of test logs
			if (screenCapture.equalsIgnoreCase("YES"))
			{
				//Add a dummy line
				document.add(new Paragraph("\n"));
				document.add(new Paragraph(new Paragraph("Screenshot : ", new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD))));
				//Image
				Image img = null;
				try
				{
					if(args[0].contains(currentDir))
					{
						img = Image.getInstance(args[0]);
					}
					else
					{
						/*Suppose if the arg[0] does contain data and if it is not equal
						 * to the required condition
						 */
						throw new NullPointerException();
					}
				}
				catch(NullPointerException | ArrayIndexOutOfBoundsException e)
				{
					img = Image.getInstance(takeScreenshot());
				}

				//If image size exceeds a threshold value decrease it to below size
				if (img.getWidth()>600.00)
				{
					img.scaleToFit(400, img.getHeight());
					img.setAlignment(Image.ALIGN_CENTER);
				}
				if (writer.getVerticalPosition(true) - document.bottom() < 160)
				{
					document.newPage();
				}
				document.add(img);

				//Add a dummy line
				document.add(new Paragraph("\n"));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//******************************************************************************************************************************
	//Update Report
	//******************************************************************************************************************************

	public static void updateReport()
	{
		if (statusTable != null)
		{
			statusTable.setSpacingBefore(15f);
			try 
			{
				//If in case the page space is less add a new page
				if (writer.getVerticalPosition(true)- document.bottom() < 160)
				{
					document.newPage();
				}

				//Add a dummy line
				document.add(new Paragraph("\n"));

				//Add a line separator
				document.add(new LineSeparator(0.5f, 100, null, 0, -5));

				//Add a dummy line
				document.add(new Paragraph("\n"));

				//Add the table
				document.add(statusTable);
			}
			catch (DocumentException e) 
			{
				e.printStackTrace();
			}
			statusTable.setSpacingAfter(15f);
		}
	}

	//******************************************************************************************************************************
	//End Report
	//******************************************************************************************************************************
	@SuppressWarnings("deprecation")
	public static void endReport()
	{
		java.util.Date runEndTimeStamp = new java.util.Date();

		//Stop Timer
		pageLoad.stop();

		//*********************************************************************
		//Result String
		String runResult;
		//Validate run result flag
		if(overalRunResultFlag==true)
		{
			//Set Run result to FAIL
			runResult = "FAIL";
		}
		else
		{
			//Set Run result to PASS
			runResult = "PASS";
		}


		//*********************************************************************

		try
		{	
			//Add a new page
			document.newPage();

			//*****************************************************************
			//Test Summary
			//*****************************************************************
			//Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));


			Font blackTimes = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
			Font greenResult = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(39, 174, 96));
			Font redResult = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(231, 76, 60));
			Font blackTimesDefaultSize = new Font(FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD, BaseColor.BLACK);

			Chunk summaryHeading = new Chunk("Run Summary", blackTimes);
			Paragraph p = new Paragraph(summaryHeading);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);

			//Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			//Add a dummy line
			document.add(new Paragraph("\n"));

			//***************************************************************************************************************************
			//Test Run Details
			//***************************************************************************************************************************

			//Add a line separator
			document.add(new LineSeparator(0.5f, 100, null, 0, -5));

			//Add a dummy line
			document.add(new Paragraph("\n"));

			//Overall Status
			Chunk beginning = new Chunk("Result : ", blackTimesDefaultSize);
			Phrase p1 = new Phrase(beginning);
			if (runResult.equalsIgnoreCase("PASS"))
			{
				Chunk runresult = new Chunk(runResult, greenResult);
				p1.add(runresult);
				Paragraph p8 = new Paragraph();
				p8.add(p1);
				document.add(p8);
			}
			else
			{
				Chunk runresult = new Chunk(runResult, redResult);
				p1.add(runresult);
				Paragraph p8 = new Paragraph();
				p8.add(p1);
				document.add(p8);
			}

			//Overall Passed Steps
			document.add(new Paragraph("Overall Steps Passed : " +passStepCount, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Overall Failed Steps
			document.add(new Paragraph("Overall Steps Failed : " +failStepCount, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Run Started
			document.add(new Paragraph("Run Started : " +runStartTimeStamp.toLocaleString(), new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Run Ended
			document.add(new Paragraph("Run Ended : " +runEndTimeStamp.toLocaleString(), new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Run Duration
			document.add(new Paragraph("Run Duration : "+(pageLoad.getTime()/1000)+ " seconds", new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Add a dummy line
			document.add(new Paragraph("\n"));

			//Add a line separator
			document.add(new LineSeparator(0.5f, 100, null, 0, -5));

			document.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//******************************************************************************************************************************
	//******************************************************************************************************************************
	//******************************************************************************************************************************
	//Keyword Library
	//******************************************************************************************************************************
	//******************************************************************************************************************************
	//******************************************************************************************************************************
	protected static void terminateIfWebDriverExecution(Exception exception) throws Exception
	{
		if(exception.toString().contains("WebDriverException"))
		{
			//Log
			logResultAndCaptureImage("FAIL", "Fatal Error", "Webdriver Has Abruptly Ended/Terminated", "NO");

			//End Report
			endReport();

			//Print The Below Statement To Attract User Attention
			System.err.println("Chrome Session Abruptly Ended/Terminated");
			System.out.println("*********************************************");
			System.out.println("Report Has been Generated!!!");
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

			//Exit Execution
			System.exit(1);
		}
	}

	public void launchApplication(String browserName, String url) throws Exception 
	{
		// Launch Browser
		try 
		{
			//Timer
			startWatch();
			if (browserName.equalsIgnoreCase("Chrome")) 
			{
				System.setProperty("webdriver.chrome.driver", currentDir+"\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				logResultAndCaptureImage("PASS", "Launch Application", "Executing Script On Chrome", "NO");
			} 
			else if (browserName.equalsIgnoreCase("IE")) 
			{
				System.setProperty("webdriver.ie.driver", currentDir+"\\drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				logResultAndCaptureImage("PASS", "Launch Application", "Executing Script On IE", "NO");
			} 
			else if (browserName.equalsIgnoreCase("Firefox")) 
			{
				System.setProperty("webdriver.gecko.driver", currentDir+"\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				logResultAndCaptureImage("PASS", "Launch Application", "Executing Script On FireFox", "NO");
			}
			//Delete All Cookie - Added @ 12/7/2018
			deleteAllCookie();
			driver.get(url);
			logResultAndCaptureImage("PASS", "Launch URL", "Opened URL : "+url, "YES", stopWatch());
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Launch Application", "Failed To Launch The Application", "YES");
			e.printStackTrace();
		}
	}

	public void inputText(By by, String elementname, String data) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Actions actions = new Actions(driver);
				actions.moveToElement(e1).click().build().perform();
				e1.clear();
				e1.sendKeys(data);
				logResultAndCaptureImage("PASS", "Input Text", "'"+data+"'"+" Entered In " + elementname + " Text Field Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Input", "Failed To Locate " +elementname+ " Text field.", "YES");
			e.printStackTrace();
		}
	}

	public void clearText(By by, String elementname) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed())
			{
				Actions actions = new Actions(driver);
				actions.moveToElement(e1).click().build().perform();
				e1.clear();
				e1.sendKeys(" ");
				logResultAndCaptureImage("PASS", "Clear Text", "Cleared " + elementname + " Field Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Clear Text", "Failed To Clear " + elementname + " Field.", "YES");
			e.printStackTrace();
		}
	}

	public void clickObject(By by, String elementname) throws Exception 
	{
		// Click Object
		try 
		{
			startWatch();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Actions actions = new Actions(driver);
				actions.moveToElement(e1).click().build().perform();
				logResultAndCaptureImage("PASS", "Click Object", "Clicked on " + elementname + " Object Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Click Object", "Failed To Click On " + elementname + " Object.", "YES");
			e.printStackTrace();
		}
	}

	public void clickObjectJs(By by, String elementname) throws Exception 
	{
		try 
		{
			startWatch();
			WebElement e1 = driver.findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", e1);
			logResultAndCaptureImage("PASS", "Click Object", "Clicked On " + elementname + " Element.", "NO");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Click Object", "Failed To Click On " + elementname + " Element.", "YES");
			e.printStackTrace();
		}
	}

	public void elementShouldContain(By by, String elementname, String data) throws Exception 
	{
		try 
		{
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				String actualString = e1.getText();
				assertTrue(actualString.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "").toUpperCase().contains(data.replaceAll(" ", "").toUpperCase()));
				logResultAndCaptureImage("PASS", "Element Should Contain", data+" Presents In Element", "YES");
			}
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Element Should Contain", data+" Is Not Present In Element", "YES");
			e.printStackTrace();
		}
	}

	public void elementShouldNotContain(By by, String elementname, String data) throws Exception 
	{
		try 
		{
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				String actualString = e1.getText();
				assertFalse(actualString.contains(data));
				logResultAndCaptureImage("FAIL", "Element Should Not Contain", data+" Is Present In Element", "YES");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("PASS", "Element Should Not Contain", data+" Is Not Presents In Element", "YES");
			e.printStackTrace();
		}
	}

	public void isEnabled(By by, String elementname) throws Exception 
	{
		// Click Button
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isEnabled()) 
			{
				logResultAndCaptureImage("PASS", "Validate Element Is Enabled ", elementname + " Element Is Enabled", "NO");
			} 
			else
			{
				logResultAndCaptureImage("FAIL", "Validate Element Is Enabled", elementname + " Element Is Disabled.", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Validate Element Is Enabled",  elementname + " Element Is Disabled.", "YES");
			e.printStackTrace();
		}
	}

	public void isDisabled(By by, String elementname) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isEnabled()) 
			{
				logResultAndCaptureImage("FAIL", "Element Disabled", elementname + " Is Enabled", "NO");
			}
			else 
			{
				logResultAndCaptureImage("PASS", "Element Disabled", elementname + " Is Disabled", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Element Disabled", elementname + " Element Is Not Disabled", "NO");
			e.printStackTrace();
		}
	}

	public void scrollPageDown() throws Exception 
	{
		try 
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");

			Thread.sleep(100);
			logResultAndCaptureImage("PASS", "Scroll Page Down", "Page Successfully Scrolled Down", "NO");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Scroll Page Down", "Unable To Scroll Page Down", "YES");
			e.printStackTrace();
		}
	}

	public void scrollPageUp() throws Exception 
	{
		try 
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,-250)", "");
			Thread.sleep(100);
			logResultAndCaptureImage("PASS", "Scroll Page Up", "Page Successfully Scrolled Up", "NO");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Scroll Page Up", "Unable To Scroll Page Up", "YES");
			e.printStackTrace();
		}
	}

	public void scrollToView(By by) throws Exception 
	{
		try
		{
			//Wait till the element is visible
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e1);
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Scroll To View", "Could Not Locate The Element", "NO");
			e.printStackTrace();
		}
	}

	public void closeBrowser() throws Exception 
	{
		try 
		{
			driver.close();
			logResultAndCaptureImage("PASS", "Close Browser", "Closed Browser Successfully", "NO");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Close Browser", "Failed to Close Browser", "YES");
			e.printStackTrace();
		}
	}

	public void closeAllBrowser() throws Exception 
	{
		try 
		{
			driver.quit();
			logResultAndCaptureImage("PASS", "Close All Browser", "Closed All Browser Successfully", "NO");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Close All Browser", "Failed To Close All Browser", "YES");
			e.printStackTrace();
		}
	}

	public void confirmAlert(String ...credentials) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alt = driver.switchTo().alert();
			if(credentials.length!=0)
			{
				Thread.sleep(1000);
				alt.sendKeys(credentials[0] + Keys.TAB.toString()+credentials[1]);
			}
			else
			{
				Thread.sleep(1000);
				alt.accept();
			}
			logResultAndCaptureImage("PASS", "Confirm Alert Popup", "Alert Popup Accecpted Successfully", "YES", takeNativeScreenshot());
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Confirm Alert Popup", "Either Failed to Accecpt Alert/The Alert window didn't Popup", "YES", takeNativeScreenshot());
			e.printStackTrace();
		}
	}

	public void dismissAlert() throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alt = driver.switchTo().alert();
			Thread.sleep(1000);
			alt.dismiss();
			logResultAndCaptureImage("PASS", "Dismiss Alert Popup", "Dismissed Alert Popup Successfully", "YES", takeNativeScreenshot());
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Dismiss Alert Popup", "Failed To Dismiss Alert", "YES", takeNativeScreenshot());
			e.printStackTrace();
		}
	}

	public void doubleClick(By by, String elementname) throws Exception 
	{
		try 
		{
			startWatch();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) {
				Actions builder = new Actions(driver);
				builder.doubleClick(e1).build().perform();
				logResultAndCaptureImage("PASS", "Double Click", "Double Clicked On " +elementname+ " Element Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Double Click", " Failed To Locate " + elementname + " Element.", "YES");
			e.printStackTrace();
		}
	}

	public void reloadPage() throws Exception 
	{
		try 
		{
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			logResultAndCaptureImage("PASS", "Refresh Or Reload Page", "Page Refreshed Successfully", "NO");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Refresh Or Reload Page", "Failed to Refresh page", "YES");
			e.printStackTrace();
		}
	}

	public void navigateBack() throws Exception 
	{
		try 
		{
			startWatch();
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			logResultAndCaptureImage("PASS", "Navigate Back", "Navigated Back To Previous Page Successfully", "NO");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Navigate Back", "Failed To Navigate To Back Page", "YES");
			e.printStackTrace();
		}
	}

	public void selectCheckbox(By by, String elementname) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected()) 
				{
					logResultAndCaptureImage("FAIL", "Select Checkbox", elementname+ " Checkbox Is Already Selected", "NO");
				} 
				else 
				{
					e1.click();
					Thread.sleep(2000);
					logResultAndCaptureImage("PASS", "Select Checkbox", elementname+ " Checkbox Is Selected Successfully.", "NO");
				}
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Select Checkbox", "Failed To Locate " +elementname+ " Checkbox", "YES");
			e.printStackTrace();
		}
	}

	public void unselectCheckbox(By by, String elementname) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected()) 
				{
					e1.click();
					Thread.sleep(2000);
					logResultAndCaptureImage("PASS", "Unselect Checkbox", " Checkbox Unselected Successfully.", "NO");
				} 
				else 
				{
					logResultAndCaptureImage("FAIL", "Unselect Checkbox", "Checkbox Is Already Unselected", "NO");
				}
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Unselect Checkbox", " Checkbox Is Already Unselected", "YES");
			e.printStackTrace();
		}
	}

	public void selectByIndex(By by, String elementname, String data) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Select se = new Select(e1);
				int val = Integer.parseInt(data.trim());
				se.selectByIndex(val);
				logResultAndCaptureImage("PASS",  "Select By Index From Dropdown", elementname + " Is Selected From Dropdown Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Select By Index From Dropdown", "Failed to select " + elementname + " from the dropdown.", "YES");
			e.printStackTrace();
		}
	}

	public void selectByText(By by, String elementname, String data) throws Exception {
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Select se = new Select(e1);
				se.selectByVisibleText(data.trim());
				logResultAndCaptureImage("PASS",  "Select By Text From Dropdown", elementname + " Is Selected From Dropdown Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Select By Text From Dropdown", "Failed to select " + elementname + " from the dropdown.", "YES");
			e.printStackTrace();
		}
	}

	public void selectByValue(By by, String elementname, String data) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Select se = new Select(e1);
				se.selectByVisibleText(data.trim());
				logResultAndCaptureImage("PASS",  "Select By Value From Dropdown", elementname + " Is Selected From Dropdown Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Select By Value From Dropdown", "Failed to select " + elementname + " from the dropdown.", "YES");
			e.printStackTrace();
		}
	}

	public void verifyTitle(String Text) throws Exception 
	{
		try 
		{
			if (driver.getTitle().contains(Text))
				logResultAndCaptureImage("PASS", "Verify Page Title", "Page Title " +"'"+Text+"'"+" Is Verified", "NO");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Verify Page Title", "Page Title " +"'"+Text+"'"+ " Does Not Match", "NO");
			e.printStackTrace();
		}
	}

	public void switchToFrameByIndex(int Input) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Input));
			driver.switchTo().frame(Input);
			Thread.sleep(2000);
			logResultAndCaptureImage("PASS", "Switch In To Frame By Index", "Switched In To Frame Successfully.", "NO");
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Switch In To Frame By Index", "Failed To Switch In To Frame.", "YES");
			e.printStackTrace();
		}
	}

	public void switchToFrameByElement(By by) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
			WebElement e1 = driver.findElement(by);
			driver.switchTo().frame(e1);
			Thread.sleep(2000);
			logResultAndCaptureImage("PASS", "Switch In To Frame", "Switched To Frame Successfully.", "NO");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Switch In To Frame", "Failed To Locate Frame.", "YES");
			e.printStackTrace();
		}
	}

	public void waitTillElementEnable(By by) throws Exception
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			logResultAndCaptureImage("FAIL", "Error : Time Out", "Element Is Not Clickable in the page", "YES");
			terminateIfWebDriverExecution(e);
			//Log
			e.printStackTrace();
		}
	}

	public void waitTillElementVisible(By by) throws Exception
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Time Out", "Element Is Not Visible In The Page", "YES");
			e.printStackTrace();
		}
	}

	public void clickRadioButton(By by, String elementname) throws Exception
	{
		try 
		{
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected()) 
				{
					logResultAndCaptureImage("FAIL", "Click Radio Button",  elementname + " Radio Button is already Clicked", "YES");
				} 
				else 
				{
					e1.click();
					logResultAndCaptureImage("PASS", "Click Radio Button", "Clicked " +elementname + " Radio Button Successfully.", "YES");
				}
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Click Radio Button", "Failed To Identify " + elementname+ " Radio Button", "YES");
			e.printStackTrace();
		}
	}

	public void clickRadioButtonByValue(String data) throws Exception 
	{
		try 
		{
			List<WebElement> radios = driver.findElements(By.xpath("//input[@type='radio']"));
			System.out.println("No " +radios.size());

			for(int i=0;i<radios.size();i++)
			{
				System.out.println("value  "+ i +"   "+radios.get(i).getAttribute("value"));
				if (radios.get(i).getAttribute("value").contains(data)) 
				{
					radios.get(i).click();
				}
				logResultAndCaptureImage("PASS", "Click Radio Button By Value", data + "Radio Button Is Clicked Successfully.", "YES");
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Click Radio Button By Value", "Failed To Click " + data + " Radio Button.", "YES");
			e.printStackTrace();
		}
	}

	public void unCheckAll() throws Exception 
	{
		int i=0;
		try 
		{
			List<WebElement> Check = driver.findElements(By.xpath("//input[@type='checkbox']"));
			System.out.println("No " +Check.size());
			for(i=0;i<Check.size();i++)
			{
				System.out.println("value  "+ i +"   "+Check.get(i).getText());
				if (Check.get(i).isSelected()) {
					Check.get(i).click();
				}
			}
			logResultAndCaptureImage("PASS", "Uncheck All Checkbox", "All Check Boxes are UnChecked Successfully.", "YES");
		}

		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Uncheck All Checkbox", "Failed To Unselect Checkboxes.", "YES");
			e.printStackTrace();
		}	
	}

	public boolean verifyIsCheckboxSelected(By by, String elementname) throws Exception 
	{
		try 
		{
			@SuppressWarnings("unused")
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected()) 
				{
					logResultAndCaptureImage("PASS", "Verify Checkbox Is Selected", elementname + "Checkbox Is Selected", "YES");
				} 
				else 
				{
					try
					{
						logResultAndCaptureImage("FAIL", "Verify Checkbox Is Selected", elementname + "Checkbox Is Not Selected", "YES");
						throw new NullPointerException("demo"); 
					}
					catch (NullPointerException e)
					{
						return false;
					}
				}
			}
			return true;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Verify Checkbox Is Not Selected", "Failed To Identify The " +elementname+ " Element", "YES");
			e.printStackTrace();
			return false;
		}
	}

	public void verifyIsCheckboxUnSelected(By by, String elementname) throws Exception 
	{
		try
		{
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected()) 
				{
					logResultAndCaptureImage("FAIL", "Verify Checkbox Is Not Selected", elementname + "Checkbox Is Selected", "YES");
				} 
				else
				{
					logResultAndCaptureImage("PASS", "Verify Checkbox Is Not Selected", elementname + "Checkbox Is Not Selected", "YES");
				}
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Verify Checkbox Is Not Selected", "Failed To Identify The " +elementname+ " Element", "YES");
			e.printStackTrace();
		}
	}

	public boolean verifyElementVisible(By by, String elementname) throws Exception 
	{
		boolean result = false;
		try
		{
			//Explicitwait
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);

			if (e1.isDisplayed()) 
			{
				if (!(elementname.equalsIgnoreCase("NO")))
				{
					logResultAndCaptureImage("PASS", "Verify Element Is Visible", elementname+ " Element Is Visible", "NO");
				}
				result = true;
			}

		} 
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log If Required
			if (!(elementname.equalsIgnoreCase("NO")))
			{
				logResultAndCaptureImage("FAIL", "Verify Element Is Visible", elementname+ " Element Is Not Visible", "NO");
			}
			e.printStackTrace();

			result = false;
		}
		return result;
	}

	public void verifyElementNotVisible(By by, String elementname) throws Exception 
	{
		try
		{
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				logResultAndCaptureImage("FAIL", "Verify Element Is Not Visible", elementname+ " Element Is Visible", "NO");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("PASS", "Verify Element Is Not Visible", elementname+ " Element Is Not Visible", "YES");
			e.printStackTrace();
		}
	}

	public void pageShouldContainText(String text) throws Exception 
	{
		try 
		{
			if(driver.getPageSource().contains(text))
			{
				logResultAndCaptureImage("PASS", "Verify Page Contain Text", "Page Contains The Data " +text, "YES");
			}
			else
			{
				logResultAndCaptureImage("FAIL", "Verify Page Contain Text", "Page Does Not Contains The Data " +text, "YES");
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Verify Page Contain Text", "Error While Locating The Text " +text, "YES");
			e.printStackTrace();
		}
	}

	public void pageShouldContainImage(By by, String elementname) throws Exception 
	{
		try 
		{
			//Fluentwait
			WebElement ImageFile;
			ImageFile = fluentWait(by, 2);

			Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
			if (ImagePresent)
			{
				logResultAndCaptureImage("PASS", "Verify Page Contain Image", "Page Contains The Image " +elementname, "NO");
			}
			else
			{
				logResultAndCaptureImage("FAIL", "Verify Page Contain Image", "Page Does Not Contains The Image " +elementname, "NO");
			}
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Verify Page Contain Image", "Error In Locating " +elementname+ " Image", "YES");	
			e.printStackTrace();
		}
	}

	public void howerMouse(By by, String elementname) throws Exception
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Actions actions = new Actions(driver);
				actions.moveToElement(e1).build().perform();
				logResultAndCaptureImage("PASS", "Perform Mouse Hower", "Successfully Mouseover on "+elementname, "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Perform Mouse Hower", "Failed To Mouseover On " +elementname, "NO");
			e.printStackTrace();
		}
	}

	public void menuSelectionThroughMouseHower(By by, String elementname, String data) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				WebElement element = driver.findElement(By.linkText(data));
				WebElement el = driver.findElement(by);
				Actions actions = new Actions(driver);
				actions.moveToElement(element).perform();
				actions.moveToElement(el).click();
				logResultAndCaptureImage("PASS", "Select Menu Through Mouse Hower", "Selected " +elementname+ " Menu Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Select Menu Through Mouse Hower", "Failed To Select Menu " +elementname+ " Through Mouse Hover", "NO");
			e.printStackTrace();
		}
	}

	public void keyBoardEvent(String data) throws Exception 
	{
		try 
		{
			Robot r=new Robot();
			if (data.equalsIgnoreCase("Enter"))
			{
				r.keyPress(KeyEvent.VK_ENTER);
			}
			else if ((data.equalsIgnoreCase("Tab")))
			{
				r.keyPress(KeyEvent.VK_TAB );
			}
			logResultAndCaptureImage("PASS", "Send A Keyboard Event", "Key " +data+ " Pressed Successfully.", "NO");
		}

		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Send A Keyboard Event", "Failed to Click  the Keyboard.", "NO");
			e.printStackTrace();
		}	
	}

	public void headerCountShouldBe(By by,String elementname,int headercount) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			//WebElement e1 = driver.findElement(by);

			List<WebElement> allHeadersOfTable =  driver.findElements(by);
			int totalHeaders = allHeadersOfTable.size();   
			assertTrue(totalHeaders == headercount);
			logResultAndCaptureImage("PASS", "Compare Header Count", headercount+" Is Same", "NO");

		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Compare Header Count", "Failed To Get Header Count", "YES");
			e.printStackTrace();
		}	
	}

	public void verifyTableExistence(By by,String elementname) throws Exception
	{
		try
		{
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if(e1.isDisplayed())
			{
				WebElement webtable1 = driver.findElement(by);
				List<WebElement>rows = webtable1.findElements(By.tagName("tr"));
				int totalrows = rows.size();
				List<WebElement>columns = rows.get(0).findElements(By.tagName("th"));
				int totalcolumns = columns.size();
				//Added screenshot and log
				logResultAndCaptureImage("PASS", "Verify Table Existence", "Found Table With " +totalrows+ " Rows And " +totalcolumns+ " Columns", "YES");
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Verify Table Existence", "Failed To Find Table", "YES");
			e.printStackTrace();	
		}
	}

	public void uploadFile(String filename) throws AWTException
	{
		StringSelection ss = new StringSelection(filename);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);     
		Robot robot;
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(2000); 
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL); 
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER); 
	}

	public void verifyLinkExistence(By by, String elementname) throws Exception 
	{
		try 
		{
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				logResultAndCaptureImage("PASS", "Verify Link Existence", elementname+ " Link " +" Exist", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Verify Link Existence", elementname+ " Link " +" Does Not Exist", "YES");
			e.printStackTrace();
		}
	}

	public void tableShouldContain(By by,String elementname,String data) throws Exception
	{
		try
		{
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if(e1.isDisplayed())
			{
				WebElement webtable1 = driver.findElement(by);
				List<WebElement>rows = webtable1.findElements(By.tagName("tr"));
				List<WebElement>columns = rows.get(0).findElements(By.tagName("th"));
				int totalcolumns = columns.size();
				List<String> value = new ArrayList<String>();
				for (int j=0; j<totalcolumns; j++)
				{
					value.add(columns.get(j).getText());
				}
				if (value.contains(data))
				{
					logResultAndCaptureImage("PASS", "Table Should Contain", "'"+data+"'"+" Found In Table", "NO");
				}
			}
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Table Should Contain", "Failed To Find "+ "'"+ data+"'" +" In Table", "YES");
			e.printStackTrace();	
		}
	}	

	public void tableShouldNotContain(By by,String elementname,String data) throws Exception
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if(e1.isDisplayed())
			{
				WebElement webtable1 = driver.findElement(by);
				List<WebElement>rows = webtable1.findElements(By.tagName("tr"));
				List<WebElement>columns = rows.get(0).findElements(By.tagName("th"));
				int totalcolumns = columns.size();
				List<String> value = new ArrayList<String>();
				for (int j=0; j<totalcolumns; j++)
				{
					value.add(columns.get(j).getText());
				}
				if (value.contains(data))
				{
					logResultAndCaptureImage("FAIL", "Table Should Not Contain", "'" +data+"'"+ " Was Found In Table ", "YES");
				}
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("PASS", "Table Should Not Contain", "Failed to find "+"'" +data+"'"+ " In Table", "YES");
			e.printStackTrace();
		}
	}

	public void clickLinkInWebTable(By by,String elementname,String link) throws Exception
	{
		try
		{
			startWatch();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if(e1.isDisplayed())
			{
				WebElement webtable1 = driver.findElement(by);
				List<WebElement>links = webtable1.findElements(By.tagName("a"));
				int totallinks = links.size();

				List<String> value = new ArrayList<String>();
				for (int j=0; j<totallinks; j++)
				{
					value.add(links.get(j).getText());
				}
				if (value.contains(link))
				{
					logResultAndCaptureImage("PASS", "Click Link", link + " Clicked Link In Webtable Successfully", "NO");
				}
			}
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Click Link", link+" Link Not Found In Table", "YES");
			e.printStackTrace();	
		}
	}

	public void verifyPageDisplayed(String url) throws Exception 
	{
		try 
		{
			waitForPageLoad();
			Thread.sleep(10000);
			String pageTitle = driver.getCurrentUrl();
			if (pageTitle.toUpperCase().contains(url.toUpperCase())) 
			{
				logResultAndCaptureImage("PASS", " Verify Page Is Displayed Using URL", url + " Page Is Displayed", "YES", stopWatch());

			} 
			else 
			{
				logResultAndCaptureImage("FAIL", "Verify Page Is Displayed Using URL", "Following " +url+ " Page Is Not Displayed", "YES");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error", "Failed To Load Page With Title : " + url, "YES");
			e.printStackTrace();
		}
	}

	public boolean verifyObjectDisplayed(By by) throws Exception 
	{
		WebElement e1=null;
		try
		{
			//Fluentwait
			e1 = fluentWait(by, 2);
			if(e1.isDisplayed())
			{
				logResultAndCaptureImage("PASS", "Verify Object Is Displayed", "Object Is Displayed", "YES");
				stopWatch();
			}
			return e1.isDisplayed();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error", "Failed To Locate The Object", "YES");
			return e1.isDisplayed();
		}
	}

	public void verifyPageDisplayed(By by,String pageName) throws Exception 
	{
		try 
		{	
			/*Wait block
			 * *********************************
			 */
			//Page load
			waitForPageLoad();
			//Fluentwait
			if (verifyElementVisible(by, "NO"))
			{
				WebElement e1;
				e1 = fluentWait(by, 1);

				/*Verification block
				 * *********************************
				 */
				if (e1.isDisplayed())
				{
					logResultAndCaptureImage("PASS", "Verify Page Is Displayed", pageName + " page is Displayed", "YES", stopWatch());
				} 
				else 
				{
					logResultAndCaptureImage("FAIL", "Verify Page Is Displayed", pageName + " page is not Displayed", "YES");
				}
			}
			else
			{
				//raise exception
				throw new NoSuchElementException("Element is not visible");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Verify Page Is Displayed", "Failed As " + pageName + " Page Could Not Be Located", "YES");
			e.printStackTrace();
		}
	}

	public void verifyPageDisplayedUsingPageTitle(String title) throws Exception 
	{
		try
		{
			waitForPageLoad();
			String pageTitle = driver.getTitle();
			if (pageTitle.toUpperCase().contains(title.toUpperCase())) 
			{
				logResultAndCaptureImage("PASS", "Verify Page Is Displayed Using Page Title", title + " Page Is Displayed", "YES", stopWatch());
			} 
			else
			{
				logResultAndCaptureImage("FAIL", "Verify Page Is Displayed Using Page Title", title + " Page Is Not Displayed ", "YES");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error", title + "page is not Displayed", "YES");
			e.printStackTrace();
		}
	}

	public void contentValidation(By by, String data) throws Exception 
	{
		try
		{
			WebElement e1=driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				String actualString = e1.getText().trim();
				Assert.assertEquals(actualString,data.trim());
				logResultAndCaptureImage("Pass", "Content Validation", "'"+data+ "'"+ " Content validated successfully", "YES");
			}
		}

		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Content Validation", "'"+data+ "'"+ " Is Not Present In Element", "YES");
			e.printStackTrace();
		}
	}

	public void switchToPrecedingTab() throws Exception 
	{
		String handle_pointer = "" ;
		try
		{
			startWatch();
			ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
			String handle = driver.getWindowHandle();
			int windowHandles_size = windowHandles.size();

			for (int k=0; k<windowHandles_size; k++)
			{
				handle_pointer = windowHandles.get(k);
				if (handle_pointer.equals(handle))
				{
					//If the first window is selected and to loop around the window, below condition suffice
					if (k==0)
					{
						handle_pointer = windowHandles.get(windowHandles_size-1);
						break;
					}
					//Decrement handler pointer index and grab that handler data from array list
					handle_pointer = windowHandles.get(k-1);
					break;
				}
			}
			//Switch to previous (or preceding) tab
			driver.switchTo().window(handle_pointer);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Switch Tabs", "Unable To Switch To The Preceding Window", "YES");
			e.printStackTrace();
		}
	}

	public void switchToTab(int tabIndex) throws Exception
	{
		try
		{
			startWatch();
			//Wrote the list for debugging purpose only
			ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
			//Switch to current
			driver.switchTo().window(windowHandles.get(tabIndex));
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Switch Tabs", "Unable To Switch To " +tabIndex+ " tab", "YES");
			e.printStackTrace();			
		}
	}

	public void switchToSucceedingTab() throws Exception 
	{
		String handle_pointer = "" ;
		try
		{
			startWatch();
			ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
			String handle = driver.getWindowHandle();
			int windowHandles_size = windowHandles.size();

			for (int k=0; k<windowHandles_size; k++)
			{
				handle_pointer = windowHandles.get(k);
				if (handle_pointer.equals(handle))
				{
					//If the last window is selected and to loop around the window, below condition suffice
					if (k == (windowHandles_size-1))
					{
						handle_pointer = windowHandles.get(0);
						break;
					}
					//Decrement handler pointer index and grab that handler data from array list
					handle_pointer = windowHandles.get(k+1);
					break;
				}
			}
			//Switch to previous (or preceding) tab
			driver.switchTo().window(handle_pointer);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Switch Tabs", "Unable To Switch To The Succeeding Window", "YES");
			e.printStackTrace();
		}
	}

	public void switchToDefaultTab() throws Exception 
	{
		try 
		{
			startWatch();
			ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(windowHandles.get(0));
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Switch To Tab", "Unable To Switch To The Default Window", "YES");
			e.printStackTrace();
		}
	}

	public void refresh() throws Exception 
	{
		try
		{
			driver.navigate().refresh();
		}
		catch(Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Refresh Page", "Failed To Refresh Page", "YES");
			e.printStackTrace();
		}
	}

	public String getCurrentUrl() throws Exception 
	{
		try
		{
			return driver.getCurrentUrl();
		}
		catch(Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Fetch Current URL", "Failed To Fetch Current URL", "NO");
		}
		return null;
	}

	public void waitForPageLoad() throws Exception 
	{
		FluentWait<WebDriver> wait = getFluentWait();
		try 
		{
			Thread.sleep(1000);
			wait.until(new Function<WebDriver, Boolean>() 
			{

				@Override
				public Boolean apply(WebDriver webDriver) 
				{
					return String.valueOf(((JavascriptExecutor) webDriver).executeScript("return document.readyState"))
							.equals("complete");
				}
			}
					);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			e.printStackTrace();
		}
	}

	public WebElement fluentWait(By by, int pollingValue)
	{
		List<Class<? extends Throwable>> ignoreExceptionClasses = new ArrayList<Class<? extends Throwable>>();
		ignoreExceptionClasses.add(NoSuchElementException.class);
		ignoreExceptionClasses.add(ElementNotVisibleException.class);
		ignoreExceptionClasses.add(InvalidElementStateException.class);
		ignoreExceptionClasses.add(StaleElementReferenceException.class);
		try
		{
			//Fluent wait - wait till element is visible - poll for 90 seconds, @ pollingValue seconds interval
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(90, TimeUnit.SECONDS)
					.pollingEvery(pollingValue, TimeUnit.SECONDS)
					.ignoreAll(ignoreExceptionClasses);

			WebElement e1 = wait.until(new Function<WebDriver,WebElement>()
			{
				@Override
				public WebElement apply(WebDriver driver)
				{
					return driver.findElement(by);
				}
			});
			return e1;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			try 
			{
				terminateIfWebDriverExecution(e);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	public FluentWait<WebDriver> getFluentWait() 
	{
		List<Class<? extends Throwable>> ignoreExceptionClasses = new ArrayList<Class<? extends Throwable>>();
		ignoreExceptionClasses.add(NoSuchElementException.class);
		ignoreExceptionClasses.add(ElementNotVisibleException.class);
		ignoreExceptionClasses.add(InvalidElementStateException.class);
		ignoreExceptionClasses.add(StaleElementReferenceException.class);
		try
		{
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoreAll(ignoreExceptionClasses);
			return wait;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			try 
			{
				terminateIfWebDriverExecution(e);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	public String capturePageTitle() throws Exception 
	{
		try
		{
			return driver.getTitle();
		}
		catch(Exception e)
		{
			// TODO : handle exception
			terminateIfWebDriverExecution(e);
			e.printStackTrace();
		}
		return null;
	}

	public int validateTotalNumberOfElements(By by,int expectedCount,String elementName) throws Exception 
	{
		try
		{
			List<WebElement> allElements = driver.findElements(by);
			int totalCount = allElements.size();
			if (totalCount==expectedCount) 
			{
				logResultAndCaptureImage("PASS", "Validate Elements Count", elementName+" Actual And Expected Counts Are Identical", "YES");
			} 
			else 
			{
				logResultAndCaptureImage("FAIL", "Validate Elements Count", elementName+" Actual And Expected Count Does not Match","YES");
			}
			return totalCount;
		}
		catch(Exception e)
		{
			// TODO : handle exception
			terminateIfWebDriverExecution(e);
			e.printStackTrace();
			return 0;
		}
	}

	public void startWatch() throws IOException 
	{
		timer.reset();
		timer.start();
	}

	public String stopWatch() throws IOException 
	{
		timer.stop();
		return Integer.toString((int) (timer.getTime()/1000));
	}

	public String getText(By by)
	{
		try
		{
			//Fetch the requested property
			String textProperty = driver.findElement(by).getText();

			//Return the text data of a web element
			return textProperty;
		}
		catch(Exception e)
		{
			// TODO : handle exception
			try
			{
				terminateIfWebDriverExecution(e);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			return null;
		}
	}

	public String getAttribute(By by, String attributeName)
	{
		try
		{
			//Fetch the requested property
			String attribute = driver.findElement(by).getAttribute(attributeName);
			//Return the requested attribute of a web element
			return attribute;
		}
		catch(Exception e)
		{
			// TODO : handle exception
			try 
			{
				terminateIfWebDriverExecution(e);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	public String getText(By by, String css)
	{
		try
		{
			//Fetch the requested property
			String cssValue = driver.findElement(by).getCssValue(css);
			//Return the css value of a web element
			return cssValue;
		}
		catch(Exception e)
		{
			// TODO : handle exception
			try
			{
				terminateIfWebDriverExecution(e);
			}
			catch(Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	public String getTagName(By by)
	{
		try
		{
			//Fetch the requested property
			String tagName = driver.findElement(by).getTagName();

			//Return the tag name data of a web element
			return tagName;
		}
		catch(Exception e)
		{
			// TODO : handle exception
			try
			{
				terminateIfWebDriverExecution(e);
			}
			catch(Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	public boolean cmpString(String validstring1, String validstring2) throws Exception
	{
		try
		{
			if (validstring1.equals(validstring2))
			{
				//Log
				logResultAndCaptureImage("PASS", "Text Comparision", "Source String : " +"'" +validstring1+ "' "+ "and Destination String : " +"'" +validstring2+ "'" +" Are Identical", "NO");
				return true;
			}
			else
			{
				//Log
				logResultAndCaptureImage("FAIL", "Text Comparision", "Source String : "+"'" +validstring1+ "' "+ "Does Not Match With Destination String : " +"'" +validstring2+ "'", "NO");
				return false;
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Text Comparision", "Error While Comparing Texts", "NO");
			e.printStackTrace();
		}
		return false;
	}

	public void imageSizeValidation(By by, int imageWidth, int imageHeight) throws Exception
	{
		//Gather image data
		WebElement Image = driver.findElement(by);

		//Get the image size
		int actual_image_width = Image.getSize().getWidth();
		//Retrieve height of element.
		int actual_image_height = Image.getSize().getHeight();

		try
		{
			if ((actual_image_width == imageWidth)&&(actual_image_height == imageHeight))
			{
				logResultAndCaptureImage("PASS", "Validate Image Size", "Image Size Validated Successfully", "YES");
			}
			else
			{
				logResultAndCaptureImage("FAIL", "Validate Image Size", "Image Size Validation Unsuccessful", "NO");
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Validate Image Size", "Error While Validating Image Size", "NO");
			e.printStackTrace();
		}
	}

	public boolean verifyPageDisplayed(By by) throws Exception
	{
		try
		{
			waitForPageLoad();
			//Fluentwait
			WebElement e1;
			e1 = fluentWait(by, 2);

			if (e1.isDisplayed()) 
			{
				logResultAndCaptureImage("PASS", "Verify Page Is Displayed", "Page Is Displayed", "YES", stopWatch());
				return true;
			} 
			else 
			{
				logResultAndCaptureImage("FAIL", "Verify Page Is Displayed", "Page Is Not Displayed", "YES");
				return false;
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Verify Page Is Displayed", "Error while verifying page", "YES");
			e.printStackTrace();
			return false;
		}
	}

	public String getCurrentDate()
	{
		String str = "";
		try
		{
			//Updated @ 11/1/2018 - changed from (MM/dd/YY) to (M/d/yy))
			SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
			str = sdf.format(System.currentTimeMillis());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return str;
	}

	public String getListOfValuesFromDropdown(By by, String elementname) throws Exception 
	{
		String str = "";
		try 
		{
			WebElement e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				Select se = new Select(e1);

				List<WebElement> allOptions = se.getOptions();
				for (int j = 0; j < allOptions.size(); j++) 
				{
					str=str+allOptions.get(j).getText()+ " ";
				}					
				str=str.substring(0, str.length()-1);
				logResultAndCaptureImage("PASS", "Retrieve List Of Values From Drop Down", elementname + "dropdown values are retrieved", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Retrieve List Of Values From Drop Down", "Failed to retrieve dorpdown values for " + elementname, "NO");
			e.printStackTrace();
		}
		return str;
	}

	public void stringShouldContain(String Parent, String Child) throws Exception 
	{
		try
		{
			//Validate Substring in a String
			if (Parent.contains(Child))
			{
				logResultAndCaptureImage("PASS", "Validate A Substring In A String", "String "+Parent+" Contains " +Child+ " Substring", "NO");
			}
			else
			{
				logResultAndCaptureImage("FAIL", "Validate A Substring In A String", "String "+Parent+ " doesn't contain " +Child+" Substring", "NO");
			}
		}
		catch (Exception e)
		{
			// TODO : handle exception
			logResultAndCaptureImage("FAIL", "Validate A Substring In A String", "String "+Parent+ " doesn't contain " +Child+" Substring", "NO");
			e.printStackTrace();
		}
	}

	public String randomIdentifier() 
	{
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
		final java.util.Random rand = new java.util.Random();
		// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
		final Set<String> identifiers = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while(builder.toString().length() == 0) 
		{
			int length = rand.nextInt(5)+5;
			for(int i = 0; i < length; i++) 
			{
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if(identifiers.contains(builder.toString())) 
			{
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public void imageValidationThroughAltAttribute(By by, String elementname, String verifyText) throws Exception
	{
		try
		{
			String actual_txt = getAttribute(by, "alt");
			if(actual_txt.equals(verifyText))
			{
				logResultAndCaptureImage("PASS", "Image Validation Through ALT Property",  "Text Message Validated Successfully", "NO");
			}
			else
			{
				logResultAndCaptureImage("FAIL", "Image Validation Through ALT Property",  "Text Message Validation Unsuccessfull", "NO");
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			logResultAndCaptureImage("FAIL", "Image Validation Through ALT Property", "Error while retrieving " +elementname, "YES");
		}
	}

	public void verifyPageDisplayedUsingPageTitle(String title,String pageName) throws Exception 
	{
		try 
		{
			waitForPageLoad();
			String pageTitle = driver.getTitle();
			if (pageTitle.toUpperCase().contains(title.toUpperCase())) 
			{
				logResultAndCaptureImage("PASS", "Verify Page is Displayed Using Page Title ", pageName + "page is Displayed", "YES", stopWatch());
			} 
			else 
			{
				logResultAndCaptureImage("FAIL", "Verify Page is Displayed Using Page Title ", pageName + "page is not Displayed", "YES");
			}

		} 
		catch (Exception e) 
		{
			// TODO: handle exception           
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Verify Page is Displayed Using Page Title ", pageName + "page is not Displayed", "YES");
			e.printStackTrace();
		}
	}

	public void selectByOption(By by,String data) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement e1 = driver.findElement(by);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			if(e1.isDisplayed())
			{
				e1.click();
				e1.sendKeys(data);
				Thread.sleep(2000);
				e1.sendKeys(Keys.ENTER);
			}
		}
		catch (Exception e)
		{
			// TODO : handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Select Value", "Failed To Locate The Element", "YES");
			e.printStackTrace();
		}
	}

	public void extractAttributeValueAndCompare(By by, String expectedValue, String attributeName) throws Exception 
	{
		try 
		{
			WebElement e1=driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				String actualString = getAttribute(by,attributeName);
				Assert.assertEquals(actualString, expectedValue);
				logResultAndCaptureImage("PASS", "Extract Attribute Value ", expectedValue+" Is Present In Element", "NO");
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Extract Atribute Value ", expectedValue+" is not present in element", "YES");			
			e.printStackTrace();
		}
	}

	//Launch url
	public void launchUrl(String url) throws Exception
	{
		try
		{
			//Start Timer
			startWatch();
			//Fetch url
			driver.get(url);
			//Log
			logResultAndCaptureImage("PASS", "Launch URL", "Launched URL "+url+ " Successfully" , "YES", stopWatch());
		}
		catch(Exception e)
		{
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Launch URL", "Failed To Launched URL "+url , "YES");
			e.printStackTrace();
		}
	}

	public void deleteAllCookie()
	{
		try
		{
			driver.manage().deleteAllCookies();
		}
		catch(Exception e)
		{
			// TODO : handle exception
			try
			{
				terminateIfWebDriverExecution(e);
			}
			catch(Exception e1)
			{
				//Do Nothing
			}
			e.printStackTrace();
		}
	}

	public void verifyTextFromAlert(String data) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alt = driver.switchTo().alert();
			Thread.sleep(1000);

			if(data.equalsIgnoreCase(alt.getText()))
			{
				logResultAndCaptureImage("PASS", "Verify Text From Alert", "Alert Text Verified Successfully", "YES", takeNativeScreenshot());
			}
			else
			{
				logResultAndCaptureImage("FAIL", "Verify Text From Alert", "Alert Text Verification Failed", "YES", takeNativeScreenshot());
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Error : Verify Text From Alert", "Alert Text Verification Failed", "YES", takeNativeScreenshot());
			e.printStackTrace();
		}
	}

	public void imgTextRead(String imagePath, String verifyText) throws Exception 
	{
		//Creating file instance and referencing the file in its location
		File imgFile = new File(imagePath);

		//Creating a new tesseract instance and setting the data path
		//that references trained data and the English language library
		ITesseract instance = new Tesseract();
		instance.setDatapath(currentDir+"\\tessdata");		

		//Create a try catch method to run the OCR on the document referred above
		try 
		{
			String result = instance.doOCR(imgFile);
			//Removing \n character from the extracted text
			result = result.replaceAll("\n"," ");
			//Trim unwanted blank spaces before and after string
			result = result.trim();

			if (result.equals(verifyText))
			{
				//Log PASS
				logResultAndCaptureImage("PASS", "Optical Character Recognition", "Warning Message Validated Successfully", "NO");
			}
			else
			{
				//Log FAIL
				logResultAndCaptureImage("FAIL", "Optical Character Recognition", "Warning Message Validation Unsuccessful", "NO");
			}
		}
		catch (TesseractException e) 
		{
			System.err.println(e.getMessage());
		}
	}

	public void pixelToPixelImgCompare(String imgPathA, String imgPathB) throws MalformedURLException, DocumentException, Exception
	{
		//Compares two images pixel by pixel and return comparision data
		BufferedImage imageA = null;
		BufferedImage imageB = null;

		try
		{
			//Read the image argumetns from path and save as file			
			File fileA = new File(imgPathA);
			File fileB = new File(imgPathB);

			//Read the image data from file
			imageA = ImageIO.read(fileA);
			imageB = ImageIO.read(fileB);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		//Compare both image dimension
		int width1 = imageA.getWidth();
		int width2 = imageB.getWidth();
		int height1 = imageA.getHeight();
		int height2 = imageB.getHeight();
		if ((width1 != width2)|| (height1 != height2))
		{
			logResultAndCaptureImage("FAIL", "Pixel To Pixel Image Verification", "Dimension mismatch", "YES");
		}
		else
		{
			long difrnce = 0;
			for (int y = 0; y < height1; y++)
			{
				for (int x = 0; y < width1; x++)
				{
					//Get RGB data
					int rgbA = imageA.getRGB(x, y);
					int rgbB = imageB.getRGB(x, y);
					//As the red RGB is (255,0,0) i.e.,(8bit,8bit,8bit,) hence masking to retrive required data
					int redA = (rgbA >> 16) & 0xff;
					int redB = (rgbB >> 16) & 0xff;
					//As the green RGB is (0,255,0) i.e.,(8bit,8bit,8bit,), hence masking to retrive required data
					int greenA = (rgbA >> 8) & 0xff;
					int greenB = (rgbB >> 8) & 0xff;
					//As the blue RGB is (0,0,255) i.e.,(8bit,8bit,8bit,), hence masking to retrive required data
					int blueA = (rgbA) & 0xff;
					int blueB = (rgbA) & 0xff;
					difrnce += Math.abs(redA-redB);
					difrnce += Math.abs(greenA-greenB);
					difrnce += Math.abs(blueA-blueB);
				}
			}
			//Validate total pixels
			double total_pixels = width1 * height1 * 3;

			//Normalizing the value of different pixels for accuracy
			//(average pixel per color component)
			double avg_different_pixels = difrnce/total_pixels;

			//Difference percentage - there are 255 values of pixels in total
			double percentage = (avg_different_pixels / 255) * 100;

			if (percentage != 0)
			{
				//Log
				logResultAndCaptureImage("FAIL", "Pixel To Pixel Image Verification", "Pixel mismatch", "NO");
			}
			else
			{
				//Log
				logResultAndCaptureImage("PASS", "Pixel To Pixel Image Verification", "Pixels matched", "NO");
			}
		}
	}

	private BufferedImage gridImage;	
	public void saveImage(File output) throws IOException 
	{
		output.delete();

		final String formatName = "png";

		for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName(formatName); iw.hasNext();) 
		{
			ImageWriter writer = iw.next();
			ImageWriteParam writeParam = writer.getDefaultWriteParam();
			ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
			IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
			if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) 
			{
				continue;
			}

			setDPI(metadata);

			final ImageOutputStream stream = ImageIO.createImageOutputStream(output);
			try 
			{
				writer.setOutput(stream);
				writer.write(metadata, new IIOImage(gridImage, null, metadata), writeParam);
			} 
			finally 
			{
				stream.close();
			}
			break;
		}
	}

	private void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException 
	{
		// for PNG, it's dots per millimeter
		// 300 is DPI and 2.54 is 1 inch to cm data
		double dotsPerMilli = 1.0 * 300 / 10 / 2.54;

		IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
		horiz.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
		vert.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode dim = new IIOMetadataNode("Dimension");
		dim.appendChild(horiz);
		dim.appendChild(vert);

		IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
		root.appendChild(dim);

		metadata.mergeTree("javax_imageio_1.0", root);
	}

	public static BufferedImage createResizedCopy(BufferedImage source, int destWidth, int destHeight, Object interpolation)
	{
		//Create a bufferdImage instance
		BufferedImage bicubic = new BufferedImage(destWidth, destHeight, source.getType());
		Graphics2D bg = bicubic.createGraphics();
		bg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
		float sx = (float)destWidth / source.getWidth();
		float sy = (float)destHeight / source.getHeight();
		//Scale the image to requested factor 
		bg.scale(sx, sy);
		bg.drawImage(source, 0, 0, null);
		bg.dispose();
		return bicubic;
	}

	public void rgbToGrayscale(String imagePath) throws Exception
	{
		BufferedImage img = null;
		File f = new File(imagePath);
		//Read Image
		try
		{
			img = ImageIO.read(f);
		}
		catch (Exception e)
		{
			logResultAndCaptureImage("FAIL", "Error", "Failed to read the image", "NO");
			e.printStackTrace();
		}
		//Get image size
		int width = img.getWidth();
		int height = img.getHeight();

		//Convert to grayscale
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				int	p = img.getRGB(x, y);

				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p&0xff;

				//Calculate average
				int avg = (r+g+b)/3;

				//replace RGB value with avg
				p = (a<<24) | (avg<<16) | (avg<<8) | avg;

				img.setRGB(x, y, p);
			}
		}
		//Write image
		try
		{
			ImageIO.write(img, "png", f);
		}
		catch (IOException e)
		{
			logResultAndCaptureImage("FAIL", "Error", "Failed to write back image After RGB To Greyscale Conversion", "NO");
		}
	}

	@SuppressWarnings("static-access")
	public String captureElementScreenshot(By by, String elementname, int ImgScalingFactor) throws Exception 
	{
		int x_factor = ImgScalingFactor;
		try
		{
			//Locate Image element to capture screenshot
			WebElement Image = driver.findElement(by);

			//Capture entire page screenshot as buffer.
			//Used TakeScreenShot, OutputType Interface of selenium and File class of
			//Java to capture screenshot of entire page.
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			//Used selenium getSize() method to get height and width of element.
			//Retrieve width of element.
			int ImageWidth = 0;
			int ImageHeight = 0;
			ImageWidth = Image.getSize().getWidth();
			//Retrieve height of element.
			ImageHeight = Image.getSize().getHeight();

			//Used selenium Point class to get x y coordinates of Image element.
			//get location(x y coordinates) of the element.
			Point point = Image.getLocation();
			int xcord = point.getX();
			int ycord = point.getY();

			//image_Multiplier for Bicubic interpolation
			int X_ImageWidth = ImgScalingFactor * ImageWidth;
			int Y_ImageHeight = ImgScalingFactor * ImageHeight;

			//Reading full image screenshot.
			BufferedImage img = ImageIO.read(screenshot);
			//cut Image using height, width and x y coordinates parameters.
			BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);

			//Creating a new copy of screenshot for reporting purpose
			File reportShot = screenshot;

			//Resize the image using Bicubic interpolation to obtain best result and create a copy (X the original size)

			BufferedImage scaledImg = createResizedCopy(dest, X_ImageWidth, Y_ImageHeight , RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			//Write the modified image back to Screenshot File instance
			ImageIO.write(scaledImg, "png", screenshot);

			//Create a time stamp for the image and use it as an unique identifier for image
			String filename =  new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

			//If Folder path is not created, created it, otherwise ignore it
			this.FILE = new File(currentDir+"\\images\\TesseractImages\\");
			if (!this.FILE.exists())
			{
				this.FILE.mkdir();
			}

			String imagePath = currentDir+"\\images\\TesseractImages\\" + "tessarct_" + filename + ".png";

			//Used FileUtils class of apache.commons.io.
			//save Image screenshot In C:\\
			FileUtils.copyFile(screenshot, new File(imagePath));

			//If image scaling is larger then 4, then convert the image to greyscale
			if (x_factor > 4)
			{
				rgbToGrayscale(imagePath);
			}

			//Log
			//Aim: To write a non scaled image to report
			//Write a non scaled image depending on scaling factor
			String logImg = imagePath; 
			if (x_factor != 1)
			{
				//If Folder path is not created, created it, otherwise ignore it
				this.FILE = new File(currentDir+"\\images\\WebElementScreenShots");
				if (!this.FILE.exists())
				{
					this.FILE.mkdir();
				}
				String reportImagePath = currentDir+"\\images\\WebElementScreenShots\\" + "element_" + filename + ".png";
				//Write a new for reporting purpose
				ImageIO.write(dest, "png", reportShot);
				//save Image screenshot In C:\\
				FileUtils.copyFile(reportShot, new File(reportImagePath));
				logImg = reportImagePath;
			}
			logResultAndCaptureImage("PASS", "Capture Element ScreenShot", "Capturned Element Screenshot", "YES", logImg);
			return logImg;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			terminateIfWebDriverExecution(e);
			//Log
			logResultAndCaptureImage("FAIL", "Capture Element ScreenShot", "Error While Capturing Image", "NO");
			e.printStackTrace();
			return null;
		}
	}
}