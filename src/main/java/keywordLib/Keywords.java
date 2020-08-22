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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

//Java Util Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidArgumentException;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.bonigarcia.wdm.WebDriverManager;


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

/**
 * Keywords - Testing Function Library
 * @author Suneel Kaushik Subramanya
 * @version 0.1
 */
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
	 * Passed Step Count
	 */
	private static int passStepCount = 0;

	/**
	 * Failed Step Count
	 */
	private static int failStepCount = 0;

	/**
	 * Error Step Count
	 */
	private static int errorStepCount = 0;

	/**
	 * Iteration Passed Step Count
	 */
	private static int iterationPassedStepCount = 0;

	/**
	 * Iteration Failed Step Count
	 */
	private static int iterationFailedStepCount = 0;

	/**
	 * Actual Execution Iteration Count
	 */
	private static int actualIterationCount = 0;

	/**
	 * Iteration Counter For Summary
	 */
	private static int itr = 1;

	/**
	 * Iteration Constant
	 */
	private static final int ITERATIONS = 1000;

	/**
	 * Alpha Numeric String
	 */
	private static final String LEXICON = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * Key Length
	 */
	private static final int KEYLENGTH = 256;

	/**
	 * Iteration Count
	 */
	private static int iteration;

	/**
	 * Initial Flag
	 */
	private static boolean initialFlag = false;

	/**
	 * Iterate Flag
	 */
	private static boolean iterateFlag = false;

	/**
	 * Overall Run Result Flag
	 */
	private static boolean overalRunResultFlag = false;

	/**
	 * Iteration Run Result Flag
	 */
	private static boolean iterationRunResultFlag = false;

	/**
	 * Document Closure Flag
	 */
	private static boolean documentClosed = false;

	/**
	 * User Agent Info
	 */
	private static String userAgent = "NA";

	/**
	 * User Agent Counters
	 */
	private static int chrome=0, ie=0, firefox=0, msedge=0, opera=0;

	/**
	 * User Agent HashMap
	 */
	private static HashMap<String, Integer> userAgentStats = new HashMap<String, Integer>();

	//*******************************
	//Font Declarations
	//*******************************
	private static Font blackTimes = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
	private static Font blackTimesNormal = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
	private static Font blackTimesBold = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
	private static Font redTimesBold = new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(231, 76, 60));
	private static Font redTimesNormal = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(231, 76, 60));
	private static Font blackTimesDefaultSize = new Font(FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD, BaseColor.BLACK);
	private static Font greenResult = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(39, 174, 96));
	private static Font redResult = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(231, 76, 60));

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

	/**
	 * Keywords  - Constructor
	 * @param testCaseName - Test Case Name Which Needs To Be Printed In The Test Report
	 */
	public Keywords(String testCaseName)
	{
		ClassName = testCaseName;
	}

	//******************************************************************************************************************************
	//ScreenShot functions
	//******************************************************************************************************************************
	/**
	 * takeNativeScreenshot - Take Screenshot Of A Active Window With The Help Of Robotium
	 * @return
	 */
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

	/**
	 * takeScreenshot - Take Screenshot Of A Active Web Page With The Help Of WebDriver 
	 * @return Screenshot Destination Path
	 */
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
				imFILE.mkdir();

			//If Screenshots folder is not present, then create a screenshot folder in current directory
			imFILE = new File(currentDir + "\\images\\Screenshots");
			if (!imFILE.exists())
				imFILE.mkdir();

			//GetScreenShot Method Directory and Image File
			File getSreenShotMethodImageFile = new File (imgPath);

			//Take Screenshot of viewable area
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//Write Screenshot to a file
			try {FileUtils.copyFile(scrFile, getSreenShotMethodImageFile);} 
			catch (IOException e) {/* TODO Auto-generated catch block*/ e.printStackTrace();}
		}
		catch(Exception e)
		{
			try {abortOnException(e);}
			catch (Exception e1){e1.printStackTrace();}
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

	/**
	 * startTestReport - Initiates The PDF Test Report
	 * @param TestCaseName - Provide Test Case Name
	 * @param TestCaseObjective - Provide Test Case Objective
	 * @param TestEnvironmentUrl - Provide the Test Environment URL
	 * @param varArgs - Provide Iteration Count, If Multiple Iteration Is Required
	 */
	public void startTestReport(String TestCaseName, String TestCaseObjective, String TestEnvironmentUrl, String...varArgs)
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
			FILE.mkdir();

		//Create Folder Structure
		dateFolder = currentDir+"\\pdf_Reports\\"+date1[1]+"_"+date1[2]+"_"+date1[5];

		FILE = new File(dateFolder);
		if (!FILE.exists())
			FILE.mkdir();

		//Create page specific folder 
		FILE = new File(dateFolder+"\\"+ClassName);
		if (!FILE.exists())
			FILE.mkdir();

		try
		{
			if (initialFlag == false)
			{
				if(varArgs.length==1)
				{
					//Setting the count for internal purpose
					iteration = Integer.parseInt(varArgs[0]);
					actualIterationCount = Integer.parseInt(varArgs[0]);

					//Set iterationFlag to true
					iterateFlag = true;
				}

				//Create pdf instance
				document = new Document(PageSize.A4);
				writer = PdfWriter.getInstance(document, new FileOutputStream(new File(dateFolder+"\\"+ClassName+"\\"+Keywords.TestCaseName+ "_"+ date1[1] + date1[2] + dateval +".pdf")));
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

				//Add Logo
				document.add(reportLogo);

				//Add a new page/ page break
				document.newPage();

				if(actualIterationCount > 1)
				{
					//Add a line separator
					document.add(new LineSeparator(0.8f, 100,BaseColor.RED, 0, -5));

					//Iteration Heading
					Chunk itrHeading = new Chunk("Iteration : "+itr, blackTimes);
					Paragraph pItr = new Paragraph(itrHeading);
					pItr.setAlignment(Paragraph.ALIGN_CENTER);
					document.add(pItr);

					//Add a line separator
					document.add(new LineSeparator(0.8f, 100, BaseColor.RED, 0, -5));

					//Add a dummy line
					document.add(new Paragraph("\n"));
				}

				//Set flag to true
				initialFlag = true;
			}
			else
			{
				//Initiate the iteration constants if kwargs length is > 1
				iterationPassedStepCount = 0;
				iterationFailedStepCount = 0;

				//Add a new page
				document.newPage();

				//Add a line separator
				document.add(new LineSeparator(0.8f, 100, BaseColor.RED, 0, -5));

				//Iteration Heading
				Font blackTimes = new Font(FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);

				Chunk mainHeading = new Chunk("Iteration : "+itr, blackTimes);
				Paragraph p = new Paragraph(mainHeading);
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);

				//Add a line separator
				document.add(new LineSeparator(0.8f, 100, BaseColor.RED, 0, -5));

				//Add a dummy line
				document.add(new Paragraph("\n"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * logResultAndCaptureScreenshot - Capture Screenshot And Log Test Results To Test Report
	 * @param Status - <b>PASS/FAIL/ERROR</b>
	 * @param StepName - Step name
	 * @param StepDescription - A brief description about Step action
	 * @param screenCapture - Provide <b>"YES"/"NO"</b>
	 * @param kwargs - Provide <b>time</b> or provide <b>custom image path</b> which needs to be added to report or if providing both provide <b>time</b> and <b>custom image path</b> respectively
	 * @throws DocumentException
	 * @throws MalformedURLException
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void logResultAndCaptureScreenshot(String Status, String StepName, String StepDescription, String screenCapture, String ...kwargs) 
			throws DocumentException, MalformedURLException, Exception
	{
		//**************************************
		//Exit Criteria
		//If Document Is Closed, Exit Function
		//**************************************
		if (documentClosed) return;

		//**************************************

		java.util.Date date1 = new java.util.Date();
		//******************************************************************************************************************************
		//Basic Table format
		//***************************************************************************************************************************
		try
		{
			statusTable = new PdfPTable(new float[]{.5f, .5f, .2f, .6f});
			Chunk stepDetails =null;
			if(Status.equalsIgnoreCase("ERROR"))
				stepDetails = new Chunk("Error", redTimesBold);
			else
				stepDetails = new Chunk("Step Details", blackTimesBold);

			Paragraph p = new Paragraph(stepDetails);
			p.setAlignment(Element.ALIGN_LEFT);
			cell = new PdfPCell(p);
			cell.setColspan(4);
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			statusTable.addCell(cell);

			Chunk stepNameHeading = null;
			if(Status.equalsIgnoreCase("ERROR"))
				stepNameHeading = new Chunk("Error Name", blackTimesBold);
			else
				stepNameHeading = new Chunk("Step Name", blackTimesBold);

			cell = new PdfPCell(new Paragraph(stepNameHeading));
			cell.setBackgroundColor(new BaseColor(208, 211, 212));
			statusTable.addCell(cell);

			Chunk stepDescriptionHeading = null;
			if(Status.equalsIgnoreCase("ERROR"))
				stepDescriptionHeading = new Chunk("Error Description", blackTimesBold);
			else
				stepDescriptionHeading = new Chunk("Step Description", blackTimesBold);

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
			Chunk stepDescription = null;
			if(Status.equalsIgnoreCase("ERROR"))
				stepDescription = new Chunk(StepDescription, redTimesNormal);
			else
				stepDescription = new Chunk(StepDescription, blackTimesNormal);

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
				passStepCount++;
				iterationPassedStepCount++;
			}
			else if (Status.equalsIgnoreCase("Fail")|(Status.equalsIgnoreCase("ERROR")))
			{
				Font red = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(231, 76, 60));
				Chunk redStatus = new Chunk(Status, red);
				cell = new PdfPCell(new Paragraph(redStatus));
				statusTable.addCell(cell);

				if(actualIterationCount > 1)
					iterationRunResultFlag = true;

				//Change the result flag to "True"
				overalRunResultFlag = true;

				//Increment fail step count
				if(!(Status.equalsIgnoreCase("ERROR")))
				{
					failStepCount++;
					iterationFailedStepCount++;
				}
				else
					errorStepCount++;
			}

			//Time
			Chunk time = new Chunk(date1.toLocaleString(), blackTimesNormal);
			cell = new PdfPCell(new Paragraph(time));
			statusTable.addCell(cell);

			//Update Report
			updateTestReport();
			if (!(kwargs.length == 0)) 
				if (!kwargs[0].contains(currentDir)) 
					document.add(new Paragraph("Page Load Time : " +kwargs[0]+ "secs", new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

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
					if(kwargs[0].contains(currentDir)||kwargs.length >1)
						img = Image.getInstance(kwargs[0]);
					else
						/*Suppose if the arg[0] does contain data and if it is not equal
						 * to the required condition
						 */
						throw new NullPointerException();
				}
				catch(NullPointerException | ArrayIndexOutOfBoundsException e)
				{
					img = Image.getInstance(takeScreenshot()); 
				}

				//If image size exceeds a threshold value decrease it to below size
				if (img.getWidth()>600.00)
				{
					img.scaleToFit(360, img.getHeight());
					img.setAlignment(Image.ALIGN_CENTER);
				}
				if (writer.getVerticalPosition(true) - document.bottom() < 150)
					document.newPage();

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

	/**
	 * updateTestReport - Updates Test Report With Test Steps
	 */
	public static void updateTestReport()
	{
		if (statusTable != null)
		{
			statusTable.setSpacingBefore(15f);
			try 
			{
				//If in case the page space is less add a new page
				if (writer.getVerticalPosition(true)- document.bottom() < 160)
					document.newPage();

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
	/**
	 * endTestReport - Ends The Test Report
	 */
	public static void endTestReport()
	{
		try
		{		
			//Add Iteration Summary
			if(iterateFlag==true)
				iterationSummary();
			else
				//Add Run Summary
				runSummary();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * runSummary - Appends Run Summary To Test Report
	 */
	@SuppressWarnings("deprecation")
	private static void runSummary()
	{
		//Add a new page
		document.newPage();

		java.util.Date runEndTimeStamp = new java.util.Date();
		//Stop Timer
		pageLoad.stop();

		//*********************************************************************
		//Result String
		String runResult;
		//Validate run result flag
		if(overalRunResultFlag == true)
			//Set Run result to FAIL
			runResult = "FAIL";
		else
			//Set Run result to PASS
			runResult = "PASS";

		//*********************************************************************
		try
		{	
			//*****************************************************************
			//Test Summary
			//*****************************************************************
			//Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			//Add run summary
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

			/*User Agent Status*/
			try 
			{
				for (@SuppressWarnings("rawtypes") Map.Entry mapElement : userAgentStats.entrySet())
					if ((Integer)mapElement.getValue() > 0)
						document.add(new Paragraph("User Agent : " +mapElement.getKey()+ " : " +mapElement.getValue()+ " Run/s", new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));
				if (userAgentStats.isEmpty())
					throw new NullPointerException("Force The User Agent Write"); 
			}
			catch (NullPointerException e)
			{
				/*Handle Sudden Abrupt Exit*/
				document.add(new Paragraph("User Agent : " +userAgent, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));
			}

			//Overall Passed Steps
			document.add(new Paragraph("Overall Steps Passed : " +passStepCount, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Overall Failed Steps
			document.add(new Paragraph("Overall Steps Failed : " +failStepCount, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Error Count
			document.add(new Paragraph("Errors : " +errorStepCount, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

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

			//Close PDF Document
			document.close();

			//Change The Flag To True
			documentClosed = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * iterationSummary - Appends The Iteration Summary To Test Report Per Iteration
	 */
	private static void iterationSummary()
	{
		try
		{
			/*User Agents Used In Each iteration*/
			switch (userAgent)
			{
			case "Chrome":
				userAgentStats.put("Chrome", chrome);
				break;
			case "IE":
				userAgentStats.put("IE", ie);
				break;
			case "Firefox":
				userAgentStats.put("Firefox", firefox);
				break;
			case "MsEdge":
				userAgentStats.put("MsEdge", msedge);
				break;
			case "Opera":
				userAgentStats.put("Opera", opera);
			}

			//Add a new page
			document.newPage();

			//Check whether it is single iteration or multiple iteration and then proceed
			//Provide miniature summary
			//Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			//Add iteration summary
			Chunk summaryHeading = new Chunk("Iteration Summary", blackTimes);
			Paragraph p = new Paragraph(summaryHeading);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);

			//Add a line separator
			document.add(new LineSeparator(1f, 100, null, 0, -5));

			//Add a dummy line
			document.add(new Paragraph("\n"));

			//*********************************************************************
			//Iteration Result
			String iterationResult;

			//Validate iteration result flag
			if(iterationRunResultFlag == true)
				//Set Run result to FAIL
				iterationResult = "FAIL";
			else
				//Set Run result to PASS
				iterationResult = "PASS";

			//*********************************************************************

			//Iteration Status
			Chunk itrResult = new Chunk("Iteration "+itr+ " Result : ", blackTimesDefaultSize);
			itr++;
			Phrase p1 = new Phrase(itrResult);
			if (iterationResult.equalsIgnoreCase("PASS"))
			{
				Chunk iterationresult = new Chunk(iterationResult, greenResult);
				p1.add(iterationresult);
				Paragraph p8 = new Paragraph();
				p8.add(p1);
				document.add(p8);
			}
			else
			{
				Chunk iterationresult = new Chunk(iterationResult, redResult);
				p1.add(iterationresult);
				Paragraph p8 = new Paragraph();
				p8.add(p1);
				document.add(p8);
			}

			//*********************************************************************
			//User Agent
			document.add(new Paragraph("User Agent : " +userAgent, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//*********************************************************************

			//Iteration Passed Steps
			document.add(new Paragraph("Iteration Steps Passed : " +iterationPassedStepCount, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Iteration Failed Steps
			document.add(new Paragraph("Iteration Steps Failed : " +iterationFailedStepCount, new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD)));

			//Add a dummy page
			document.newPage();

			//decrement the iterator
			iteration--;
			if (iteration==0)
				//Add run summary and close the pdf
				runSummary();
		}
		catch(Exception e)
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
	/**
	 * abortOnException - Logs Appropriate Exceptions To Report And Terminates The Execution
	 * @param exception Provide Exception As Argument
	 * @throws Exception
	 */
	public static void abortOnException(Exception exception) throws Exception
	{
		overalRunResultFlag = true;
		try
		{	
			//WebDriverException
			if(exception.toString().contains("InvalidArgumentException"))
				logResultAndCaptureScreenshot("ERROR", "Argument Error : Invalid Browser Name", exception.toString(), "YES", takeNativeScreenshot());
			else if(exception.toString().contains("WebDriverException")) 
				logResultAndCaptureScreenshot("ERROR", "Fatal Error : Browser Has Been Closed/Failed Due To Network Failure", exception.toString(), takeNativeScreenshot());
			//NullPointerException
			else if(exception.toString().contains("NullPointerException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Runtime Exception (Null)", exception.toString(), "YES", takeNativeScreenshot());
			//ConnectionClosedException
			else if(exception.toString().contains("ConnectionClosedException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Disconnection In Driver", exception.toString(), "YES", takeNativeScreenshot());
			//ElementNotVisibleException
			else if(exception.toString().contains("ElementNotVisibleException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Element Not Visible (May Have Hidden Property)", exception.toString(), "YES");
			//UnknownServerException
			else if(exception.toString().contains("UnknownServerException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Server Error", exception.toString(), "YES");
			//ErrorInResponseException
			else if(exception.toString().contains("ErrorInResponseException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Fault On Server Side", exception.toString(), "YES", takeNativeScreenshot());
			//ImeActivationFailedException
			else if(exception.toString().contains("ImeActivationFailedException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : IME Engine Activation Failure", exception.toString(), "YES");
			//ImeNotAvailableException
			else if(exception.toString().contains("ImeNotAvailableException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : IME Support Unavailable", exception.toString(), "YES");
			//JavascriptException
			else if(exception.toString().contains("JavascriptException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : javascript Exception", exception.toString(), "YES");
			//JsonException
			else if(exception.toString().contains("JsonException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Json Exception", exception.toString(), "YES", takeNativeScreenshot());
			//NoSuchFrameException
			else if(exception.toString().contains("NoSuchFrameException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Frame Not Available", exception.toString(), "YES");
			//NotFoundException
			else if(exception.toString().contains("NotFoundException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Element Does Not Exist In DOM", exception.toString(), "YES");
			//RemoteDriverServerException
			else if(exception.toString().contains("RemoteDriverServerException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Server Response Failure", exception.toString(), "YES", takeNativeScreenshot());
			//StaleElementReferenceException
			else if(exception.toString().contains("StaleElementReferenceException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Element Is Detached From Current DOM", exception.toString(), "YES");
			//TimeoutException
			else if(exception.toString().contains("TimeoutException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Time Out", exception.toString(), "YES");
			//NoSuchElementException
			else if(exception.toString().contains("NoSuchElementException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Cannot Locate Element", exception.toString(), "YES");
			//ArrayIndexOutOfBoundsException
			else if(exception.toString().contains("ArrayIndexOutOfBoundsException")) 
				logResultAndCaptureScreenshot("ERROR", "Error : Illegal Index", "An Array Has Been Accessed With An Illegal Index", "NO");
			//NoSuchWindowException
			else if(exception.toString().contains("NoSuchWindowException")) 
				logResultAndCaptureScreenshot("Error", "Error : Target Window Does Not Exist", exception.toString(), "YES", takeNativeScreenshot());
			//Other Exceptions
			else 
				logResultAndCaptureScreenshot("ERROR", "Error : Something Went Wrong!", exception.toString(), "YES", takeNativeScreenshot());
		}
		finally
		{
			//Iteration Summary
			if(actualIterationCount > 1) 
				iterationSummary();
			//End Report
			runSummary();

			//Print The Below Statement To Make The User Aware
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.err.println("***********!!!EXCEPTION OCCURED!!!***********");
			System.out.println("************Terminated Execution*************");
			System.out.println("*******!!!Report Has Been Generated!!!*******");
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

			//Quit Driver
			try {driver.quit();}
			//To Catch InvocationTargetException Used Throwable
			catch (Throwable e) {System.err.println("Driver Termination Failed, As Driver Is Not Invoked Yet");}
			//Exit Execution
			System.exit(1);
		}
	}

	/**
	 * launchApplication - Launch Application And Opens Target URL
	 * @param browserName - Browser Name To Be Invoked : <b>"chrome"</b> or <b>"ie"</b> or <b>"firefox"</b> or <b>"msedge"</b>
	 * @param url - URL To Be Launched
	 * @return Boolean (true/false)
	 * @throws Exception
	 */
	public boolean launchApplication(String browserName, String url) throws Exception 
	{
		// Launch Browser
		boolean result = false;
		try 
		{
			//Timer
			startWatch();
			if (browserName.equalsIgnoreCase("Chrome")) 
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				userAgent = "Chrome";
				++chrome;
				logResultAndCaptureScreenshot("PASS", "Launch Application", "Executing Script On Chrome", "NO");
			} 
			else if (browserName.equalsIgnoreCase("IE"))
			{
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				userAgent = "IE";
				++ie;
				logResultAndCaptureScreenshot("PASS", "Launch Application", "Executing Script On IE", "NO");
			} 
			else if (browserName.equalsIgnoreCase("Firefox")) 
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				userAgent = "Firefox";
				++firefox;
				logResultAndCaptureScreenshot("PASS", "Launch Application", "Executing Script On FireFox", "NO");
			}
			else if (browserName.equalsIgnoreCase("MSEDGE"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				userAgent = "MsEdge";
				++msedge;
				logResultAndCaptureScreenshot("PASS", "Launch Application", "Executing Script On Edge", "NO");
			}
			else if (browserName.equalsIgnoreCase("OPERA"))
			{
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				driver.manage().window().maximize();
				userAgent = "Opera";
				++opera;
				logResultAndCaptureScreenshot("PASS", "Launch Application", "Executing Script On Opera", "NO");
			}
			else
			{
				// TODO: handle exception - For Passing Incorrect Browser Name As An Argument
				throw new InvalidArgumentException("Wrong Browser Name Argument");
			}

			deleteAllCookie();
			driver.get(url);
			logResultAndCaptureScreenshot("PASS", "Launch URL", "Opened URL : "+url, "YES", stopWatch());

			result = true;
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Launch Application", "Failed To Launch The Application", "NO");
			// TODO: handle exception
			abortOnException(e);
		}
		return result;
	}

	/**
	 * inputText - Enter Text In The Edit Field
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Where In Text Has To Be Entered
	 * @param data - Text To Be Entered
	 * @throws Exception
	 */
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
				logResultAndCaptureScreenshot("PASS", "Input Text", "'"+data+"'"+" Entered In " + elementname + " Text Field Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Input", "Failed To Locate " +elementname+ " Text field.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * clearText - Clear Text In The In Edit Field
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Where In It's Text Has To Be Cleared
	 * @throws Exception
	 */
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
				logResultAndCaptureScreenshot("PASS", "Clear Text", "Cleared " + elementname + " Field Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Clear Text", "Failed To Clear " + elementname + " Field.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * Click Object - Clicks The Object Specified
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Clicked
	 * @throws Exception
	 */
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
				logResultAndCaptureScreenshot("PASS", "Click Object", "Clicked on " + elementname + " Object Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Click Object", "Failed To Click On " + elementname + " Object.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * ClickObjectJs - Click The Object Specified Using JavaScript
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Clicked
	 * @throws Exception
	 */
	public void clickObjectJs(By by, String elementname) throws Exception 
	{
		try 
		{
			startWatch();
			WebElement e1 = driver.findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", e1);
			logResultAndCaptureScreenshot("PASS", "Click Object", "Clicked On " + elementname + " Element.", "NO");
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Click Object", "Failed To Click On " + elementname + " Element.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * elementShouldContain - Validate The Presence Of Data In Element
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Being Validated
	 * @param data - Data To be Validated
	 * @throws Exception
	 */
	public void elementShouldContain(By by, String elementname, String data) throws Exception 
	{
		try 
		{
			//Fluent wait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				String actualString = e1.getText();
				assertTrue(actualString.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "").toUpperCase().contains(data.replaceAll(" ", "").toUpperCase()));
				logResultAndCaptureScreenshot("PASS", "Element Should Contain", data+" Is Presents In Element", "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Element Should Contain", data+" Is Not Present In Element", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * elementShouldNotContain - Validate The Absence Of Data In Element
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Being Validated
	 * @param data - Data To be Validated
	 * @throws Exception
	 */
	public void elementShouldNotContain(By by, String elementname, String data) throws Exception 
	{
		try
		{
			//Fluent wait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				String actualString = e1.getText();
				assertFalse(actualString.contains(data));
				logResultAndCaptureScreenshot("FAIL", "Element Should Not Contain", data+" Is Present In Element", "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("PASS", "Element Should Not Contain", data+" Is Not Presents In Element", "YES");
			// TODO: handle exception
			abortOnException(e);	
		}
	}

	/**
	 * isEnabled - Validate Whether The Element Is Enabled
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Being Validated
	 * @throws Exception
	 */
	public void isEnabled(By by, String elementname) throws Exception 
	{
		// Click Button
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isEnabled())
				logResultAndCaptureScreenshot("PASS", "Validate Element Is Enabled ", elementname + " Element Is Enabled", "NO");
			else
				logResultAndCaptureScreenshot("FAIL", "Validate Element Is Enabled", elementname + " Element Is Disabled.", "NO");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * isDisabled  - Validate Whether The Element Is Disabled
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Being Validated
	 * @throws Exception
	 */
	public void isDisabled(By by, String elementname) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isEnabled())
				logResultAndCaptureScreenshot("FAIL", "Element Disabled", elementname + " Is Enabled", "NO");
			else
				logResultAndCaptureScreenshot("PASS", "Element Disabled", elementname + " Is Disabled", "NO");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * Scroll Page Down - Scrolls The Page Down For Every 250*multiplier pixels
	 * @param multiplier - Provide Multiplier Based On The Requirement
	 * @throws Exception
	 */
	public void scrollPageDown(String multiplier) throws Exception 
	{
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			Thread.sleep(300);
			jse.executeScript("window.scrollBy(0,250*arguments[0])", multiplier);
			logResultAndCaptureScreenshot("PASS", "Scroll Page Down", "Page Successfully Scrolled Down", "NO");
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Scroll Page Down", "Unable To Scroll Page Down", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * Scroll Page Up - Scrolls The Page Up For -250*multiplier pixels
	 * @param multiplier - Provide Multiplier Based On The Requirement
	 * @throws Exception
	 */
	public void scrollPageUp(String multiplier) throws Exception 
	{
		try 
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			Thread.sleep(300);
			jse.executeScript("window.scrollBy(0,-250*arguments[0])", multiplier);
			logResultAndCaptureScreenshot("PASS", "Scroll Page Up", "Page Successfully Scrolled Up", "NO");
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Scroll Page Up", "Unable To Scroll Page Up", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * Scroll To View - Scroll Till Element Is Visible
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @throws Exception
	 */
	public void scrollToView(By by) throws Exception 
	{
		try
		{
			//Wait till the element is visible
			//Fluent wait
			WebElement e1;
			e1 = fluentWait(by, 2);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e1);
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Scroll To View", "Could Not Locate The Element", "NO");
			abortOnException(e);
		}
	}

	/**
	 * closeBrowser - Close Opened Active Browser Session
	 * @throws Exception
	 */
	public void closeBrowser() throws Exception 
	{
		try 
		{
			driver.close();
			logResultAndCaptureScreenshot("PASS", "Close Browser", "Closed Browser Successfully", "NO");
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Close Browser", "Failed to Close Browser", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * closeAllBrowser - Close All Browser Session
	 * @throws Exception
	 */
	public void closeAllBrowser() throws Exception 
	{
		try 
		{
			driver.quit();
			logResultAndCaptureScreenshot("PASS", "Close All Browser", "Closed All Browser Successfully", "NO");
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Close All Browser", "Failed To Close All Browser", "YES");
			// TODO: handle exception
			abortOnException(e);

		}
	}

	/**
	 * confirmAlert - Confirm/Accept Alert Window Both With And Without Username And Password
	 * @param credentials - Provide Username And Password Respectively
	 * @throws Exception
	 */
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
			logResultAndCaptureScreenshot("PASS", "Confirm Alert Popup", "Alert Popup Accecpted Successfully", "YES", takeNativeScreenshot());
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Confirm Alert Popup", "Either Failed to Accecpt Alert/The Alert window didn't Popup", "YES", takeNativeScreenshot());
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * dismissAlert - Dismiss Alert Window
	 * @throws Exception
	 */
	public void dismissAlert() throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alt = driver.switchTo().alert();
			Thread.sleep(1000);
			alt.dismiss();
			logResultAndCaptureScreenshot("PASS", "Dismiss Alert Popup", "Dismissed Alert Popup Successfully", "YES", takeNativeScreenshot());
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Dismiss Alert Popup", "Failed To Dismiss Alert", "YES", takeNativeScreenshot());
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * doubleClick - Double Click The Object Specified
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Being Clicked
	 * @throws Exception
	 */
	public void doubleClick(By by, String elementname) throws Exception 
	{
		try 
		{
			startWatch();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Actions builder = new Actions(driver);
				builder.doubleClick(e1).build().perform();
				logResultAndCaptureScreenshot("PASS", "Double Click", "Double Clicked On " +elementname+ " Element Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Double Click", " Failed To Locate " + elementname + " Element.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * reloadPage - Reload The Active Web Session
	 * @throws Exception
	 */
	public void reloadPage() throws Exception 
	{
		try
		{
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			logResultAndCaptureScreenshot("PASS", "Refresh Or Reload Page", "Page Refreshed Successfully", "NO");
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Refresh Or Reload Page", "Failed to Refresh page", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * navigateBack - Navigate Back To Previous URL In Active Web Session
	 * @throws Exception
	 */
	public void navigateBack() throws Exception 
	{
		try 
		{
			startWatch();
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			logResultAndCaptureScreenshot("PASS", "Navigate Back", "Navigated Back To Previous Page Successfully", "NO");
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Navigate Back", "Failed To Navigate To Back Page", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * selectCheckbox - Select Checkbox 
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Selected
	 * @throws Exception
	 */
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
					logResultAndCaptureScreenshot("FAIL", "Select Checkbox", elementname+ " Checkbox Is Already Selected", "NO");
				} 
				else 
				{
					e1.click();
					Thread.sleep(2000);
					logResultAndCaptureScreenshot("PASS", "Select Checkbox", elementname+ " Checkbox Is Selected Successfully.", "NO");
				}
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Select Checkbox", "Failed To Locate " +elementname+ " Checkbox", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * unSelectCheckbox - Uncheck Checkbox 
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Unchecked
	 * @throws Exception
	 */
	public void unCheckCheckbox(By by, String elementname) throws Exception 
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
					logResultAndCaptureScreenshot("PASS", "Uncheck Checkbox", " Uncheck Checkbox Successfully.", "NO");
				} 
				else 
				{
					logResultAndCaptureScreenshot("FAIL", "Uncheck Checkbox", "Checkbox Is Already Unchecked", "NO");
				}
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Uncheck Checkbox", "Failed To Uncheck Checkbox", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * selectByIndex - Select From Dropdown Using Index
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Selected
	 * @param index - Provide Index Of Option To Be Selected From Dropdown
	 * @throws Exception
	 */
	public void selectByIndex(By by, String elementname, String index) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Select se = new Select(e1);
				int val = Integer.parseInt(index.trim());
				se.selectByIndex(val);
				logResultAndCaptureScreenshot("PASS",  "Select By Index From Dropdown", elementname + " Is Selected From Dropdown Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Select By Index From Dropdown", "Failed to select " + elementname + " from the dropdown.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * selectByText - Select From Dropdown Using Text
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Selected
	 * @param text - Provide Text Of Option To Be Selected From Dropdown
	 * @throws Exception
	 */
	public void selectByText(By by, String elementname, String text) throws Exception {
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				Select se = new Select(e1);
				se.selectByVisibleText(text.trim());
				logResultAndCaptureScreenshot("PASS",  "Select By Text From Dropdown", elementname + " Is Selected From Dropdown Successfully.", "NO");
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Select By Text From Dropdown", "Failed to select " + elementname + " from the dropdown.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * selectByValue - Select From Dropdown Using Value
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Selected
	 * @param value - Provide Value Of Option To Be Selected From Dropdown
	 * @throws Exception
	 */
	public void selectByValue(By by, String elementname, String value) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed())
			{
				Select se = new Select(e1);
				se.selectByVisibleText(value.trim());
				logResultAndCaptureScreenshot("PASS",  "Select By Value From Dropdown", elementname + " Is Selected From Dropdown Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Select By Value From Dropdown", "Failed to select " + elementname + " from the dropdown.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * verifyTitle - Verify Page Title
	 * @param title - Provide Page Title
	 * @throws Exception
	 */
	public void verifyTitle(String title) throws Exception 
	{
		try 
		{
			if (driver.getTitle().contains(title))
				logResultAndCaptureScreenshot("PASS", "Verify Page Title", "Page Title " +"'"+title+"'"+" Is Verified", "NO");
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Verify Page Title", "Page Title " +"'"+title+"'"+ " Does Not Match", "NO");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * switchToFrameByIndex
	 * @param Index - Provide Index Of The Frame To Be Switched
	 * @throws Exception
	 */
	public void switchToFrameByIndex(int Index) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Index));
			driver.switchTo().frame(Index);
			Thread.sleep(2000);
			logResultAndCaptureScreenshot("PASS", "Switch In To Frame By Index", "Switched In To Frame Successfully.", "NO");
		} 
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Switch In To Frame By Index", "Failed To Switch In To Frame.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * switchToFrameByElement - Switch To Frame By Locator Address
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @throws Exception
	 */
	public void switchToFrameByElement(By by) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
			WebElement e1 = driver.findElement(by);
			driver.switchTo().frame(e1);
			Thread.sleep(2000);
			logResultAndCaptureScreenshot("PASS", "Switch In To Frame", "Switched To Frame Successfully.", "NO");
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Switch In To Frame", "Failed To Locate Frame.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * waitTillElementEnable - Wait Till The Element Under Test Is Clickable
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @throws Exception
	 */
	public void waitTillElementEnable(By by) throws Exception
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Time Out", "Element Is Not Clickable in the page", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * waitTillElementVisible - Wait Till The Element Under Test Is Visible
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @throws Exception
	 */
	public void waitTillElementVisible(By by) throws Exception
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} 
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Time Out", "Element Is Not Visible In The Page", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * clickRadioButton - Click/Activate Radio Button
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Clicked/Activated
	 * @throws Exception
	 */
	public void clickRadioButton(By by, String elementname) throws Exception
	{
		try 
		{
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected())
					logResultAndCaptureScreenshot("FAIL", "Click Radio Button",  elementname + " Radio Button is already Clicked", "YES");
				else 
				{
					e1.click();
					logResultAndCaptureScreenshot("PASS", "Click Radio Button", "Clicked " +elementname + " Radio Button Successfully.", "YES");
				}
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Click Radio Button", "Failed To Identify " + elementname+ " Radio Button", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * clickRadioButtonByValue - Click/Activate Radio Button By Value
	 * @param value - Provide Attribute Value Of Radio Button
	 * @throws Exception
	 */
	public void clickRadioButtonByValue(String value) throws Exception 
	{
		try 
		{
			List<WebElement> radios = driver.findElements(By.xpath("//input[@type='radio']"));
			for(int i=0;i<radios.size();i++)
			{
				if (radios.get(i).getAttribute("value").contains(value))
					radios.get(i).click();
				logResultAndCaptureScreenshot("PASS", "Click Radio Button By Value", value + "Radio Button Is Clicked Successfully.", "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Click Radio Button By Value", "Failed To Click " + value + " Radio Button.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * unCheckAll - Uncheck All Checkbox
	 * @throws Exception
	 */
	public void unCheckAll() throws Exception 
	{
		try 
		{
			List<WebElement> Check = driver.findElements(By.xpath("//input[@type='checkbox']"));
			for(int i=0;i<Check.size();i++)
			{
				if (Check.get(i).isSelected())
					Check.get(i).click();
			}
			logResultAndCaptureScreenshot("PASS", "Uncheck All Checkbox", "All Check Boxes are UnChecked Successfully.", "YES");
		}

		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Uncheck All Checkbox", "Failed To Unselect Checkboxes.", "YES");
			// TODO: handle exception
			abortOnException(e);
		}	
	}

	/**
	 * verifyIsCheckboxSelected - Validate If Checkbox Is Selected By Element Locator Address
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Validated
	 * @return Boolean (true/false)
	 * @throws Exception
	 */
	public boolean verifyIsCheckboxSelected(By by, String elementname) throws Exception 
	{
		boolean result = false;
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected())
					logResultAndCaptureScreenshot("PASS", "Verify Checkbox Is Selected", elementname + "Checkbox Is Selected", "YES");
				else 
				{
					try
					{
						logResultAndCaptureScreenshot("FAIL", "Verify Checkbox Is Selected", elementname + "Checkbox Is Not Selected", "YES");
						throw new NullPointerException("demo"); 
					}
					catch (NullPointerException e)
					{
						result = false;
					}
				}
			}
			result = true;
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Verify Checkbox Is Not Selected", "Failed To Identify The " +elementname+ " Element", "YES");
			// TODO: handle exception
			abortOnException(e);
			result = false;
		}
		return result;
	}

	/**
	 * verifyIsCheckboxUnchecked - Validate Whether Checkbox Is Unchecked
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Validated
	 * @throws Exception
	 */
	public void verifyIsCheckboxUnchecked(By by, String elementname) throws Exception 
	{
		try
		{
			//Fluent wait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed()) 
			{
				if (e1.isSelected())
					logResultAndCaptureScreenshot("FAIL", "Verify Checkbox Is Not Selected", elementname + "Checkbox Is Selected", "YES"); 
				else
					logResultAndCaptureScreenshot("PASS", "Verify Checkbox Is Not Selected", elementname + "Checkbox Is Not Selected", "YES");
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Verify Checkbox Is Not Selected", "Failed To Identify The " +elementname+ " Element", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * verifyElementVisible - Verify If Element Is Visible In The Active Web Session
	 * @param by - Element Locator Addresss (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Verified Or Provide "NO" To Disable Report Log For Function Call
	 * @return Boolean (true/false)
	 * @throws Exception
	 */
	public boolean verifyElementVisible(By by, String elementname) throws Exception 
	{
		boolean result = false;
		try
		{
			//Explicit wait
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);

			if (e1.isDisplayed()) 
			{
				if (!(elementname.equalsIgnoreCase("NO")))
					logResultAndCaptureScreenshot("PASS", "Verify Element Is Visible", elementname+ " Element Is Visible", "NO");
				result = true;
			}

		} 
		catch (Exception e)
		{
			//Log If Required
			if (!(elementname.equalsIgnoreCase("NO")))
				logResultAndCaptureScreenshot("FAIL", "Verify Element Is Visible", elementname+ " Element Is Not Visible", "NO");
			// TODO: handle exception
			abortOnException(e);
			result = false;
		}
		return result;
	}

	/**
	 * verifyElementNotVisible - Verify Element Is Not Visible In The Active Web Session
	 * @param by - Element Locator Addresss (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Verified
	 * @throws Exception
	 */
	public void verifyElementNotVisible(By by, String elementname) throws Exception 
	{
		try
		{
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed())
				logResultAndCaptureScreenshot("FAIL", "Verify Element Is Not Visible", elementname+ " Element Is Visible", "NO");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			//Log
			logResultAndCaptureScreenshot("PASS", "Verify Element Is Not Visible", elementname+ " Element Is Not Visible", "YES");
		}
	}

	/**
	 * pageShouldContainText - Validate The Presence Of Text Content In An Active Web Session's Page
	 * @param text - Text To Be Validated
	 * @throws Exception
	 */
	public void pageShouldContainText(String text) throws Exception 
	{
		try 
		{
			if(driver.getPageSource().contains(text))
				logResultAndCaptureScreenshot("PASS", "Verify Page Contain Text", "Page Contains The Data " +text, "YES");
			else
				logResultAndCaptureScreenshot("FAIL", "Verify Page Contain Text", "Page Does Not Contains The Data " +text, "YES");
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Verify Page Contain Text", "Error While Locating The Text " +text, "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * pageShouldContainImage - Validate The Presence Of An Image In An Active Web Session
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Verified
	 * @throws Exception
	 */
	public void pageShouldContainImage(By by, String elementname) throws Exception 
	{
		try 
		{
			//Fluent wait
			WebElement ImageFile;
			ImageFile = fluentWait(by, 2);

			Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
			if (ImagePresent)
				logResultAndCaptureScreenshot("PASS", "Verify Page Contain Image", "Page Contains The Image " +elementname, "NO");
			else
				logResultAndCaptureScreenshot("FAIL", "Verify Page Contain Image", "Page Does Not Contains The Image " +elementname, "NO");
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Verify Page Contain Image", "Error In Locating " +elementname+ " Image", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * howerMouse - Hover Mouser Pointer Over An Active Web Session's Element
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Hovered Upon
	 * @throws Exception
	 */
	public void hoverMouse(By by, String elementname) throws Exception
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
				logResultAndCaptureScreenshot("PASS", "Perform Mouse Hover Action", "Successfully Mouseover on "+elementname, "NO");
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Perform Mouse Hover Action", "Failed To Mouseover On " +elementname, "NO");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * menuSelectionThroughMouseHover - Select Menu After Hovering Over A Web Element
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Hovered And Selected
	 * @param linkText - Provide The Link Text To Be Clicked After Hovering
	 * @throws Exception
	 */
	public void menuSelectionThroughMouseHover(By by, String elementname, String linkText) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement e1 = driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				WebElement element = driver.findElement(By.linkText(linkText));
				WebElement el = driver.findElement(by);
				Actions actions = new Actions(driver);
				actions.moveToElement(element).perform();
				Thread.sleep(2000);
				actions.moveToElement(el).click();
				logResultAndCaptureScreenshot("PASS", "Select Menu Through Mouse Hover", "Selected " +elementname+ " Menu Successfully.", "NO");
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Select Menu Through Mouse Hover", "Failed To Select Menu " +elementname+ " Through Mouse Hover", "NO");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * keyBoardEvent - Input Keyboard Event
	 * @param keyName - Provide Action Key Name To Be Performed 
	 * @throws Exception
	 */
	public void keyBoardEvent(String keyName) throws Exception 
	{
		try
		{
			Robot r=new Robot();
			if (keyName.equalsIgnoreCase("Enter"))
				r.keyPress(KeyEvent.VK_ENTER);
			else if ((keyName.equalsIgnoreCase("Tab")))
				r.keyPress(KeyEvent.VK_TAB );
			logResultAndCaptureScreenshot("PASS", "Send A Keyboard Event", "Key " +keyName+ " Pressed Successfully.", "NO");
		}

		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Send A Keyboard Event", "Failed To Click The "+keyName+ " Key In Keyboard.", "NO");
			// TODO: handle exception
			abortOnException(e);
		}	
	}

	/**
	 * mouseKeyPress - Perform Mouse Click On Specified Coordinate 
	 * @param xPos - X - Coordinate Pointer Position
	 * @param yPos - Y - Coordinate Pointer Position
	 * @throws Exception
	 */
	public void mouseKeyPress(int xPos, int yPos) throws Exception
	{

		Robot r = new Robot();
		r.mouseMove(xPos, yPos);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		//Log
		logResultAndCaptureScreenshot("PASS", "Perform Click Action", "Clicked At " +xPos+ " And " +yPos+ " Position Successfully", "YES");
	}

	/**
	 * headerCountShouldBe - Compare Header Count In A Table With User Specified Count 
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Validated
	 * @param headercount - Provide Header Count Of A Table
	 * @throws Exception
	 */
	public void headerCountShouldBe(By by,String elementname,int headercount) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			List<WebElement> allHeadersOfTable =  driver.findElements(by);
			int totalHeaders = allHeadersOfTable.size();   
			assertTrue(totalHeaders == headercount);
			logResultAndCaptureScreenshot("PASS", "Compare Header Count", headercount+" Is Same", "NO");

		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Compare Header Count", "Failed To Get Header Count", "YES");
			// TODO: handle exception
			abortOnException(e);
		}	
	}

	/**
	 * verifyTableExistence - Verify Table Existence In An Active Web Session
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Verified
	 * @throws Exception
	 */
	public void verifyTableExistence(By by,String elementname) throws Exception
	{
		try
		{
			//Fluent wait
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
				logResultAndCaptureScreenshot("PASS", "Verify Table Existence", "Found Table With " +totalrows+ " Rows And " +totalcolumns+ " Columns", "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Verify Table Existence", "Failed To Find Table", "YES");
			// TODO: handle exception
			abortOnException(e);	
		}
	}

	/**
	 * uploadFile - Upload A File In Specified Location
	 * @param filename - Provide Complete Path Of The File
	 * @throws AWTException
	 */
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

	/**
	 * verifyLinkExistence - Verify Link Existence In An Active Web Session
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Verified
	 * @throws Exception
	 */
	public void verifyLinkExistence(By by, String elementname) throws Exception 
	{
		try 
		{
			//Fluent wait
			WebElement e1;
			e1 = fluentWait(by, 2);
			if (e1.isDisplayed())
				logResultAndCaptureScreenshot("PASS", "Verify Link Existence", elementname+ " Link " +" Exist", "NO");
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Verify Link Existence", elementname+ " Link " +" Does Not Exist", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * tableShouldContain - Verify Data Existence In An Active Web Session's Table
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param tableName - Table Name To Be Verified
	 * @param data - Data To Be Verified In Table
	 * @throws Exception
	 */
	public void tableShouldContain(By by,String tableName,String data) throws Exception
	{
		try
		{
			//Fluent wait
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
					value.add(columns.get(j).getText());
				if (value.contains(data))
					logResultAndCaptureScreenshot("PASS", tableName+" Table Should Contain", "'"+data+"'"+" Found In Table "+tableName, "NO");
				else
					logResultAndCaptureScreenshot("FAIL", tableName+" Table Should Contain", "Failed To Find "+ "'"+ data+"'" +" In Table "+tableName, "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", tableName+" Table Should Contain", "Failed To Locate "+tableName+ " Table", "YES");
			// TODO: handle exception
			abortOnException(e);	
		}
	}	

	/**
	 * tableShouldNotContain - Verify Data Absence In An Active Web Session Table
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param tableName - Table Name To Be Verified
	 * @param data - Data To Be Validated
	 * @throws Exception
	 */
	public void tableShouldNotContain(By by,String tableName,String data) throws Exception
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
					value.add(columns.get(j).getText());
				if (value.contains(data))
					logResultAndCaptureScreenshot("FAIL", tableName+" Table Should Not Contain", "'" +data+"'"+ " Was Found In Table "+tableName, "YES");
				else
					logResultAndCaptureScreenshot("PASS", tableName+" Table Should Not Contain", "Failed to find "+"'" +data+"'"+ " In Table "+tableName, "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", tableName+" Table Should Contain", "Failed To Locate "+tableName+ " Table", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * clickLinkInWebTable - Click Link In An Active Web Session's Table 
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param tableName - Table Name Where In Click Action Is Being Performed
	 * @param link - Provide Link Name To Be Clicked
	 * @throws Exception
	 */
	public void clickLinkInWebTable(By by,String tableName,String link) throws Exception
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
					value.add(links.get(j).getText());
				if (value.contains(link))
					logResultAndCaptureScreenshot("PASS", "Click Link In "+tableName+" Table", link + " Clicked Link In "+tableName+" table Successfully", "NO");
				else
					logResultAndCaptureScreenshot("FAIL", "Click Link In "+tableName+" Table", link+" Link Not Found In "+tableName+" Table", "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Click Link In "+tableName+" Table", "Failed To Locate "+tableName+ " Table", "YES");
			// TODO: handle exception
			abortOnException(e);	
		}
	}

	/**
	 * verifyPageDisplayed - Verify Page Is Displayed Using URL
	 * @param url - Page URL To Be Validated
	 * @throws Exception
	 */
	public void verifyPageDisplayed(String url) throws Exception 
	{
		try 
		{
			waitForPageLoad();
			Thread.sleep(10000);
			String pageTitle = driver.getCurrentUrl();
			if (pageTitle.toUpperCase().contains(url.toUpperCase()))
				logResultAndCaptureScreenshot("PASS", " Verify Page Is Displayed Using URL", url + " Page Is Displayed", "YES", stopWatch());
			else
				logResultAndCaptureScreenshot("FAIL", "Verify Page Is Displayed Using URL", "Following " +url+ " Page Is Not Displayed", "YES");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * verifyPageDisplayed - Verify Page Is Displayed With The Help Of Unique Object In That Page
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param pageName - Page Name To Be Verified
	 * @throws Exception
	 */
	public void verifyPageDisplayed(By by,String pageName) throws Exception 
	{
		try 
		{	
			//Page load
			waitForPageLoad();
			if (verifyElementVisible(by, "NO"))
			{
				//Fluent wait
				WebElement e1;
				e1 = fluentWait(by, 1);
				if (e1.isDisplayed())
					logResultAndCaptureScreenshot("PASS", "Verify Page Is Displayed", pageName + " page is Displayed", "YES", stopWatch()); 
				else
					logResultAndCaptureScreenshot("FAIL", "Verify Page Is Displayed", pageName + " page is not Displayed", "YES");
			}
			else
			{
				//raise exception
				throw new NoSuchElementException("Element is not visible");
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Verify Page Is Displayed", "Failed As " + pageName + " Page Could Not Be Located", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * validateContent - Validate Content In An Active Web Session
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param content - Text Content To Be Validated
	 * @throws Exception
	 */
	public void validateContent(By by, String content) throws Exception 
	{
		try
		{
			WebElement e1=driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				String actualString = e1.getText().trim();
				Assert.assertEquals(actualString,content.trim());
				logResultAndCaptureScreenshot("Pass", "Content Validation", "'<<<<<<"+content+">>>>>>'"+ " Content validated successfully", "YES");
			}
		}

		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Content Validation", "'<<<<<<"+content+">>>>>>"+ " Is Not Present In Element", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * switchToPrecedingTab - Switch To Previous Tab
	 * Note : Follows Round Robin Rule. Left To Right
	 * @throws Exception
	 */
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
			//Log
			logResultAndCaptureScreenshot("FAIL", "Switch Tabs", "Unable To Switch To The Preceding Window", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * switchToTab - Switch To Specified Tab In A Browser
	 * @param tabIndex - Provide The Index Of The Tab To be Switched (Note : Index Starts From '0')
	 * @throws Exception
	 */
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
			//Log
			logResultAndCaptureScreenshot("FAIL", "Switch Tabs", "Unable To Switch To " +tabIndex+ " tab", "YES");
			// TODO: handle exception
			abortOnException(e);			
		}
	}

	/**
	 * switchToSucceedingTab - Switch To Next Tab
	 * Note: Follows  Round Robin Rule. Right To Left
	 * @throws Exception
	 */
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
			//Log
			logResultAndCaptureScreenshot("FAIL", "Switch Tabs", "Unable To Switch To The Succeeding Window", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * switchToDefaultTab - Switch To Default Tab / First Tab
	 * @throws Exception
	 */
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
			//Log
			logResultAndCaptureScreenshot("FAIL", "Switch To Tab", "Unable To Switch To The Default Window", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * refreshPage - Refreshes/Reloads A Current Active Web Session
	 * @throws Exception
	 */
	public void refreshPage() throws Exception 
	{
		try
		{
			driver.navigate().refresh();
		}
		catch(Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Refresh Page", "Failed To Refresh Page", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * getCurrentUrl - Get Current URL Of An Active Web Session
	 * @return Current URL Of Active Session/ Null
	 * @throws Exception
	 */
	public String getCurrentUrl() throws Exception 
	{
		try
		{
			return driver.getCurrentUrl();
		}
		catch(Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Fetch Current URL", "Failed To Fetch Current URL", "NO");
			// TODO: handle exception
			abortOnException(e);
			return null;
		}
	}

	/**
	 * waitForPageLoad - Wait Till Page Loads Completely
	 * @throws Exception
	 */
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
			abortOnException(e);
		}
	}

	/**
	 * fluentWait - Wait Till An Element Condition Is Met Using Polling And ignoring Exceptions
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param pollingValue - Provide The Polling Interval (0.5 sec to N seconds)
	 * @return (Positive) WebElement / (Negative) null 
	 */
	@SuppressWarnings("deprecation")
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
				abortOnException(e);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * getFluentWait - Initializes Fluent Wait With 1 Second Polling
	 * @return (Fluent Wait) wait 
	 */
	@SuppressWarnings("deprecation")
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
				abortOnException(e);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * capturePageTitle - Fetch Page Title From An Active Web Session
	 * @return
	 * @throws Exception
	 */
	public String capturePageTitle() throws Exception 
	{
		try
		{
			return driver.getTitle();
		}
		catch(Exception e)
		{
			// TODO : handle exception
			abortOnException(e);
		}
		return null;
	}

	/**
	 * validateTotalNumberOfElements - Validate Total Number Of Elements In An Active Web Session
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param expectedCount - Provide the Elements Count
	 * @param elementName - Element Name To Be Validated
	 * @return (Positive) Total Count Of Elements/ (Negative) 0
	 * @throws Exception
	 */
	public int validateTotalNumberOfElements(By by,int expectedCount,String elementName) throws Exception 
	{
		try
		{
			List<WebElement> allElements = driver.findElements(by);
			int totalCount = allElements.size();
			if (totalCount==expectedCount)
				logResultAndCaptureScreenshot("PASS", "Validate Elements Count", elementName+" Actual And Expected Counts Are Identical", "YES");
			else 
				logResultAndCaptureScreenshot("FAIL", "Validate Elements Count", elementName+" Actual And Expected Count Does not Match","YES");
			return totalCount;
		}
		catch(Exception e)
		{
			// TODO : handle exception
			abortOnException(e);
			return 0;
		}
	}

	/**
	 * startWatch - Start Timer HH:MM:SS
	 * @throws IOException
	 */
	public void startWatch() throws IOException 
	{
		timer.reset();
		timer.start();
	}

	/**
	 * stopWatch - Stop Timer HH:MM:SS
	 * @return - Stop Time
	 * @throws IOException
	 */
	public String stopWatch() throws IOException 
	{
		timer.stop();
		return Integer.toString((int) (timer.getTime()/1000));
	}

	/**
	 * getText - Get Text From An Element Under Focus
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @return text
	 */
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
				abortOnException(e);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * getAttribute - Fetch Attribute Of An Element
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param attributeName - Attribute To Be Fetched
	 * @return Attribute
	 */
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
				abortOnException(e);
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * getCss - Fetch CSS Value Of An Web Element
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param attribute - CSS Attribute From Which Value Has To Be Fetched
	 * @return CSS Value
	 */
	public String getCss(By by, String attribute)
	{
		try
		{
			//Fetch the requested property
			String cssValue = driver.findElement(by).getCssValue(attribute);
			//Return the css value of a web element
			return cssValue;
		}
		catch(Exception e)
		{
			// TODO : handle exception
			try
			{
				abortOnException(e);
			}
			catch(Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * getTagName - Fetch Tag Name Of A Web Element
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @return Tag Name
	 */
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
				abortOnException(e);
			}
			catch(Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * cmpString - Compare Two Strings
	 * @param validstring1 - String To Be Verified
	 * @param validstring2 - String To Be Verified
	 * @return boolean (true/false)
	 * @throws Exception
	 */
	public boolean cmpString(String validstring1, String validstring2) throws Exception
	{
		try
		{
			if (validstring1.equals(validstring2))
			{
				//Log
				logResultAndCaptureScreenshot("PASS", "Text Comparision", "Source String : " +"'" +validstring1+ "' "+ "and Destination String : " +"'" +validstring2+ "'" +" Are Identical", "NO");
				return true;
			}
			else
			{
				//Log
				logResultAndCaptureScreenshot("FAIL", "Text Comparision", "Source String : "+"'" +validstring1+ "' "+ "Does Not Match With Destination String : " +"'" +validstring2+ "'", "NO");
				return false;
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Text Comparision", "Error While Comparing Texts", "NO");
			// TODO: handle exception
			abortOnException(e);
		}
		return false;
	}

	/**
	 * imageSizeValidation - Validate An Image Element's Width And Height In An Active Web Session 
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param imageWidth - Expected Width Of Image Element
	 * @param imageHeight - Expected Height Of Image Element
	 * @throws Exception
	 */
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
				logResultAndCaptureScreenshot("PASS", "Validate Image Size", "Image Size Validated Successfully", "YES");
			else
				logResultAndCaptureScreenshot("FAIL", "Validate Image Size", "Image Size Validation Unsuccessful", "NO");
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Validate Image Size", "Error While Validating Image Size", "NO");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * getCurrentDate - Fetch Current Date From System
	 * @return - Current Date (MM/DD/YY) Format
	 */
	public String getCurrentDate()
	{
		String str = "";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			str = sdf.format(System.currentTimeMillis());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * getCurrentDate - Fetch Current Date From System In Specified Format
	 * @param dateFormat - M/d/yy or M/dd/yyyy or more
	 * @see {@link https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html}
	 * @return
	 */
	public String getCurrentDate(String dateFormat)
	{
		String str = "";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			str = sdf.format(System.currentTimeMillis());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * getListOfValuesFromDropdown - Get List Of Values In A Drop down 
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name Under Test
	 * @return 
	 * @throws Exception
	 */
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
					str+=allOptions.get(j).getText()+ " ";
				str=str.substring(0, str.length()-1);
				logResultAndCaptureScreenshot("PASS", "Retrieve List Of Values From Drop Down", elementname + "dropdown values are retrieved", "NO");
			}
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Retrieve List Of Values From Drop Down", "Failed to retrieve dorpdown values for " + elementname, "NO");
			// TODO: handle exception
			abortOnException(e);
		}
		return str;
	}

	/**
	 * stringShouldContain - Validate A Substring In A String
	 * @param Parent - Parent String
	 * @param Child - Substring Of A Parent String
	 * @throws Exception
	 */
	public void stringShouldContain(String parent, String child) throws Exception 
	{
		try
		{
			//Validate Substring in a String
			if (parent.contains(child))
				logResultAndCaptureScreenshot("PASS", "Validate A Substring In A String", "String "+parent+" Contains " +child+ " Substring", "NO");
			else
				logResultAndCaptureScreenshot("FAIL", "Validate A Substring In A String", "String "+parent+ " doesn't contain " +child+" Substring", "NO");
		}
		catch (Exception e)
		{
			// TODO : handle exception
			logResultAndCaptureScreenshot("FAIL", "Error : Validate A Substring In A String", "Failed To Validate "+child+ " String", "NO");
			e.printStackTrace();
		}
	}

	/**
	 * randomIdentifier - Create A New Random String As Per The String Length Provided
	 * @param stringLen - Length Of The String To Be Generated 
	 * @return String
	 */
	public static String randomIdentifier(int stringLen) 
	{
		final java.util.Random rand = new java.util.Random();
		// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
		final Set<String> identifiers = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while(builder.toString().length() == 0)
		{
			int length = rand.nextInt(stringLen)+5;
			for(int i = 0; i < length; i++)
				builder.append(LEXICON.charAt(rand.nextInt(LEXICON.length())));
			if(identifiers.contains(builder.toString()))
				builder = new StringBuilder();
		}
		return builder.toString();
	}

	/**
	 * imageValidationThroughAltAttribute - Validate Image Through Alt property
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Image Element Name To Be Validated
	 * @param verifyText - Provide The Image Text To Be Verified
	 * @throws Exception
	 */
	public void imageValidationThroughAltAttribute(By by, String elementname, String verifyText) throws Exception
	{
		try
		{
			String actual_txt = getAttribute(by, "alt");
			if(actual_txt.equals(verifyText))
				logResultAndCaptureScreenshot("PASS", "Image Validation Through ALT Property",  "Text Message Validated Successfully", "NO");
			else
				logResultAndCaptureScreenshot("FAIL", "Image Validation Through ALT Property",  "Text Message Validation Unsuccessfull", "NO");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			logResultAndCaptureScreenshot("FAIL", "Image Validation Through ALT Property", "Error while retrieving " +elementname, "YES");
		}
	}

	/**
	 * verifyPageDisplayedUsingPageTitle - Verify Page In An Active Web Session Using Page Title
	 * @param title - Page Title Of Active Web Session
	 * @param pageName - Provide Page Title
	 * @throws Exception
	 */
	public void verifyPageDisplayedUsingPageTitle(String title,String pageName) throws Exception 
	{
		try 
		{
			waitForPageLoad();
			String pageTitle = driver.getTitle();
			if (pageTitle.toUpperCase().contains(title.toUpperCase()))
				logResultAndCaptureScreenshot("PASS", "Verify Page is Displayed Using Page Title ", pageName + "page is Displayed", "YES", stopWatch());
			else
				logResultAndCaptureScreenshot("FAIL", "Verify Page is Displayed Using Page Title ", pageName + "page is not Displayed", "YES");

		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Verify Page is Displayed Using Page Title ", pageName + "page is not Displayed", "YES");
			// TODO: handle exception           
			abortOnException(e);
		}
	}

	/**
	 * selectByOption - Select From Dropdown Using Option 
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param option - Option Available In DOM For The Dropdown
	 * @throws Exception
	 */
	public void selectByOption(By by,String option) throws Exception 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement e1 = driver.findElement(by);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			if(e1.isDisplayed())
			{
				e1.click();
				e1.sendKeys(option);
				Thread.sleep(2000);
				e1.sendKeys(Keys.ENTER);
				logResultAndCaptureScreenshot("PASS", "Select From Dropdown", "Selected "+option+ " From Dropdown", "YES");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Select From Dropdown", "Failed To Locate The Element", "YES");
			// TODO : handle exception
			abortOnException(e);
		}
	}

	/**
	 * extractAttributeValueAndCompare - Extract Attribute Value From An Element Specified And Compare With Expected Value
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param expectedValue - Expected Value To Be Validated In Element's Attribute Value
	 * @param attributeName - Attribute Value's Name To be Validated
	 * @throws Exception
	 */
	public void extractAttributeValueAndCompare(By by, String expectedValue, String attributeName) throws Exception 
	{
		try 
		{
			WebElement e1=driver.findElement(by);
			if (e1.isDisplayed()) 
			{
				String actualString = getAttribute(by,attributeName);
				Assert.assertEquals(actualString, expectedValue);
				logResultAndCaptureScreenshot("PASS", "Extract Attribute Value ", expectedValue+" Is Present In Element", "NO");
			}
		} 
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Extract Atribute Value ", expectedValue+" is not present in element", "YES");
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * launchUrl - Launches Specified URL In Current Browser Session
	 * @param url - Web Address To Be Navigated To
	 * @throws Exception
	 */
	public void launchUrl(String url) throws Exception
	{
		try
		{
			//Start Timer
			startWatch();
			//Fetch url
			driver.get(url);
			//Log
			logResultAndCaptureScreenshot("PASS", "Launch URL", "Launched URL "+url+ " Successfully" , "YES", stopWatch());
		}
		catch(Exception e)
		{
			abortOnException(e);
			//Log
			logResultAndCaptureScreenshot("FAIL", "Launch URL", "Failed To Launched URL "+url , "YES");
			e.printStackTrace();
		}
	}

	/**
	 * deleteAllCookie - Delete All Cookie From Current Browser Session
	 */
	public void deleteAllCookie() throws Exception
	{
		try
		{
			driver.manage().deleteAllCookies();
		}
		catch(Exception e)
		{
			// TODO : handle exception
			abortOnException(e);
		}
	}

	/**
	 * verifyTextFromAlert - Verify Text Message Present In Alert Window
	 * @param text - Text Message To Be Validated
	 * @throws Exception
	 */
	public void verifyTextFromAlert(String text) throws Exception 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alt = driver.switchTo().alert();
			Thread.sleep(1000);

			if(text.equalsIgnoreCase(alt.getText()))
				logResultAndCaptureScreenshot("PASS", "Verify Text From Alert", "Alert Text Verified Successfully", "YES", takeNativeScreenshot());
			else
				logResultAndCaptureScreenshot("FAIL", "Verify Text From Alert", "Alert Text Verification Failed", "YES", takeNativeScreenshot());
		}
		catch (Exception e) 
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Error : Verify Text From Alert", "Alert Text Verification Failed", "YES", takeNativeScreenshot());
			// TODO: handle exception
			abortOnException(e);
		}
	}

	/**
	 * imgTextRead - Extract Text From Image And Validate
	 * @param imagePath - Image Source path (Saved Location - Complete Location With File Extension)
	 * @param verifyText - Text Message To Be Verified
	 * @throws Exception
	 */
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
				logResultAndCaptureScreenshot("PASS", "Optical Character Recognition", "Warning Message Validated Successfully", "NO");
			else
				logResultAndCaptureScreenshot("FAIL", "Optical Character Recognition", "Warning Message Validation Unsuccessful", "NO");
		}
		catch (TesseractException e) 
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * pixelToPixelImageCompare - Compare Two Images With Respect To Its Dimensions And RGB Count
	 * @param imgPathA - Image Source path (Saved Location - Complete Location With File Extension)
	 * @param imgPathB - Image Source path (Saved Location - Complete Location With File Extension)
	 * @throws MalformedURLException
	 * @throws DocumentException
	 * @throws Exception
	 */
	public void pixelToPixelImgCompare(String imgPathA, String imgPathB) throws MalformedURLException, DocumentException, Exception
	{
		//Compares two images pixel by pixel and return comparison data
		BufferedImage imageA = null;
		BufferedImage imageB = null;

		try
		{
			//Read the image arguments from path and save as file			
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
			logResultAndCaptureScreenshot("FAIL", "Pixel To Pixel Image Verification", "Dimension mismatch", "YES");
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
				//Log
				logResultAndCaptureScreenshot("FAIL", "Pixel To Pixel Image Verification", "Pixel mismatch", "NO");
			else
				//Log
				logResultAndCaptureScreenshot("PASS", "Pixel To Pixel Image Verification", "Pixels matched", "NO");
		}
	}


	private BufferedImage gridImage;	
	/**
	 * saveAsPngImage - Save An Image In ".png" Format After Increasing The DPI Of The Image
	 * @param output
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void saveAsPngImage(File output) throws IOException 
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
				continue;

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

	/**
	 * setDPI - Set DPI For Image Meta Data If It Is Not Set
	 * @param metadata
	 * @throws IIOInvalidTreeException
	 */
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

	/**
	 * createResizedCopy - Resizes An Image Without Losing It's Original Details (Without Pixelating The Original Image)
	 * @param source - Image Source
	 * @param destWidth - Provide The Required Destination Width (X_Resolution)
	 * @param destHeight - Provide The Required Destination Height (Y_Resolution)
	 * @param interpolation - Provide The Interpolation Technique Of Choice To Achieve Required Result (Use : Bicubic Interpolation Technique To Achieve Best Result) 
	 * @return BufferedImage
	 * Example - BufferedImage scaledImg = createResizedCopy(bufferedImageDest, 8000, 6000 , RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	 * <br>bufferedImageDest - Source Image Read Into Buffer
	 */
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

	/**
	 * rgbToGrayScale - Convert An RGB Image To Gray Scale Image
	 * @param imagePath - Image Path To Be Converted (Complete Path Including File Extension)
	 * @throws Exception
	 */
	public void rgbToGrayScale(String imagePath) throws Exception
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
			logResultAndCaptureScreenshot("FAIL", "Error", "Failed to read the image", "NO");
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
			logResultAndCaptureScreenshot("FAIL", "Error", "Failed to write back image After RGB To Greyscale Conversion", "NO");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	/**
	 * captureElementScreenshot - Capture Element Screenshot In An Active Web Session's Page
	 * @param by - Element Locator Address (Xpath, CSS, ID, Classname..etc)
	 * @param elementname - Element Name To Be Captured
	 * @param ImgScalingFactor - Scale The Image To Required Size (Provide Higher Value Than 1, If Scaling Is Required)
	 * @return Element Image Screenshot Path
	 * @throws Exception
	 */
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
				this.FILE.mkdir();

			String imagePath = currentDir+"\\images\\TesseractImages\\" + "tessarct_" + filename + ".png";

			//Create a new image file
			FileUtils.copyFile(screenshot, new File(imagePath));

			//If image scaling is larger then 4, then convert the image to greyscale
			if (x_factor > 4)
				rgbToGrayScale(imagePath);

			//Log
			//Write a non scaled image depending on scaling factor
			String logImg = imagePath; 
			if (x_factor != 1)
			{
				//If Folder path is not created, created it, otherwise ignore it
				this.FILE = new File(currentDir+"\\images\\WebElementScreenShots");
				if (!this.FILE.exists())
					this.FILE.mkdir();
				String reportImagePath = currentDir+"\\images\\WebElementScreenShots\\" + "element_" + filename + ".png";
				//Write a new for reporting purpose
				ImageIO.write(dest, "png", reportShot);
				//save Image screenshot In C:\\
				FileUtils.copyFile(reportShot, new File(reportImagePath));
				logImg = reportImagePath;
			}
			logResultAndCaptureScreenshot("PASS", "Capture Element ScreenShot", "Capturned Element Screenshot", "YES", logImg);

			//Return Non Scaled Image
			return logImg;
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Capture Element ScreenShot", "Error While Capturing Image", "NO");
			// TODO: handle exception
			abortOnException(e);
			return null;
		}
	}

	/**
	 * extractImagesFromDoc - Extract Images From Document
	 * @param docx - Document Path (".docx" extension)
	 * @throws Exception
	 */
	public static void extractImagesFromDoc(XWPFDocument docx) throws Exception
	{
		try 
		{
			List<XWPFPictureData> piclist = docx.getAllPictures();
			// traverse through the list and write each image to a file
			Iterator<XWPFPictureData> iterator = piclist.iterator();
			int i = 0;
			while (iterator.hasNext()) 
			{
				XWPFPictureData pic = iterator.next();
				byte[] bytepic = pic.getData();
				BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytepic));
				String extractedFolderImgPath = currentDir+"\\Doc_images";
				//If directory does not exist, create a new directory
				FILE = new File(extractedFolderImgPath);
				if(!FILE.exists()) {FILE.mkdir();}
				String docImg = extractedFolderImgPath+pic.getFileName();
				ImageIO.write(imag, "png", new File(docImg));
				i++;
				logResultAndCaptureScreenshot("PASS", "Extract Image "+i+" From Document", "Extracted Image", "YES", docImg);
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			logResultAndCaptureScreenshot("FAIL", "Extract Images From Document", "Failed To Extract Images From Document", "NO");
			System.exit(-1);
		}
	}

	/**
	 * encryptPassword - Encrypts Incoming Password Using Base64
	 * @param password - Provide Password Which Needs To Be Encrypted
	 * @return - Encrypted Password / null (For Failure Condition)
	 */
	public static String encryptPassword(String password)
	{
		try
		{
			//Generate Salt
			String salt = randomIdentifier(60);

			//Generate Hash
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATIONS, KEYLENGTH);
			Arrays.fill(password.toCharArray(), Character.MIN_VALUE);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

			//Generate Secure Password
			byte[] intmPassword = skf.generateSecret(spec).getEncoded();
			String encryptedPassword = Base64.getEncoder().encodeToString(intmPassword);

			//Clear spec
			spec.clearPassword();

			return encryptedPassword;
		}
		catch (Exception e)
		{
			//TODO : handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * dragAndDrop - Perform Drag And Drop Opeation Using Action Chains
	 * @param from - Source Locator Address (Xpath, CSS, ID, Class Name..etc)
	 * @param to - Destination Locator Address (Xpath, CSS, ID, Class Name...etc)
	 * @throws Exception
	 */
	public void dragAndDrop(By from, By to) throws Exception
	{
		try
		{
			//Element Which Needs To Drag
			WebElement e1 = driver.findElement(from);

			//Element On Which Need To Drop
			WebElement e2 = driver.findElement(to);

			//Perform Action
			Actions action = new Actions(driver);
			action.clickAndHold(e1).build().perform();
			Thread.sleep(1000);
			action.moveToElement(e2).build().perform();
			Thread.sleep(1000);
			action.moveByOffset(-1, -1).build().perform();
			Thread.sleep(1000);
			action.release().build().perform();
			//Log
			logResultAndCaptureScreenshot("PASS", "Drag And Drop", "Performed Drag And Drop Successfully", "YES");
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Drag And Drop", "Failed To Perform Drag And Drop", "YES");
			//TODO : handle exception
			e.printStackTrace();
		}
	}

	/**
	 * validateDropdownOptions - Validate N Number Drop-Down Options Depending On The Argument Length
	 * @param by - Drop Down Menu Locator Address (Xpath, CSS, ID, Class Name..etc)
	 * @param optionList - Drop-Down Options Separated With ";"
	 * @throws Exception
	 */
	public void validateDropDownOptions(By by, String optionList) throws Exception
	{
		try
		{
			WebElement e1 = fluentWait(by, 2);
			List<String> data= Arrays.asList(optionList.split(";"));
			int failCounter = 0;
			if(e1.isDisplayed())
			{
				Select se = new Select(e1);
				ArrayList<WebElement> options = new ArrayList<WebElement>(se.getOptions());
				while(options.iterator().hasNext() && data.iterator().hasNext())
				{
					//Reset Flag After Each Iteration
					if (!(options.iterator().next().getText().equalsIgnoreCase(data.iterator().next())))
						failCounter++;
				}
				if (failCounter>0)
					logResultAndCaptureScreenshot("FAIL", "Validate Drop-Down Options", "Validation Failed!", "NO");
				else 
					logResultAndCaptureScreenshot("PASS", "Validate Drop-Down Options", "Successfully Validated Dropdown Options", "NO");
			}
		}
		catch (Exception e)
		{
			//Log
			logResultAndCaptureScreenshot("FAIL", "Validate Dropdown Options", "Failed To Locate Drop-Down Element", "YES");
			//TODO : handle exception
			e.printStackTrace();
		}
	}

	/**
	 * validateLinksInPage - Validate Whether Links Are Working Correctly/Broken In Any Page Based On The HTTP Response Code 
	 * @param homePageURL - Home Page URL
	 * @throws Exception
	 */
	public void validateLinksInPage(String homePageURL) throws Exception
	{
		try 
		{
			//*************************************************
			/*Declarations*/
			String url = "";
			int responseCode = 200;
			HttpURLConnection huc = null;
			int validLinks=0,invalidLinks=0;

			//*************************************************
			List<WebElement> links = driver.findElements(By.tagName("a"));
			Iterator<WebElement> itr = links.iterator();
			while (itr.hasNext())
			{
				url = itr.next().getAttribute("href");
				if (url==null || url.isEmpty())
					continue;
				if(!url.startsWith(homePageURL))
					continue;
				try
				{
					huc = (HttpURLConnection)(new URL(url).openConnection());
					huc.setRequestMethod("HEAD");
					huc.connect();
					responseCode = huc.getResponseCode();
					/*Validate*/
					if (responseCode >= 400)
					{
						invalidLinks++;
						if (!itr.hasNext())
							logResultAndCaptureScreenshot("FAIL", "Validate Links In page", "Validation Failed As The Number Of Invalid/Broken Links : "+invalidLinks, "NO");
					}
					else
					{
						validLinks++;
						if (!itr.hasNext())
							logResultAndCaptureScreenshot("PASS", "Validate Links In Page", "Validated Links Successfully. Valid Links : "+validLinks+ " Invalid/Broken Links : "+invalidLinks, "NO");
					}
				} 
				catch (MalformedURLException e)
				{
					logResultAndCaptureScreenshot("ERROR", "Validate Links In Page", e.toString(), "NO");
					e.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			logResultAndCaptureScreenshot("ERROR", "Validate Links In Page", e.toString(), "YES");
			e.printStackTrace();
		}
	}
}