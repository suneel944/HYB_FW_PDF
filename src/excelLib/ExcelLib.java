package excelLib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.read.biff.BiffException;

public class ExcelLib
{
	//Current Directory
	private static String currentDir = System.getProperty("user.dir");
	private Workbook workbook;
	private Sheet worksheet;
	private int rows;
	private String testCaseName;
	private int testCaseStartRow = 0;
	private int testCaseEndRow=0;
	private int usedColumnsCount = 0;
	private int iterationCount = 0;
	private String workBookPath = currentDir+"\\TestData\\Input_Data.xls";
	private static File wbFILE;
	private static WritableSheet sheetToEdit;
	private static WritableFont font;
	private static WritableCellFormat cellStyle;
	private static Label label;

	//WB(Workbook) time Stamp
	private static java.util.Date wbTimeStamp = new java.util.Date();
	private static String[] wbDate1 = wbTimeStamp.toString().split(" ");
	private static String[] wbDate2 = wbDate1[3].split(":");
	@SuppressWarnings("unused")
	private static String wbDateVal = wbDate2[0] + wbDate2[1] + wbDate2[2];

	//Writable Workbook path
	String executionWorkBookName = currentDir+"\\Execution\\"+wbDate1[1]+"_"+wbDate1[2]+"_"+wbDate1[5]+"\\execution_data.xls";

	public ExcelLib(String workSheetName, String testCaseName) throws BiffException, IOException
	{
		workbook = Workbook.getWorkbook(new File(workBookPath));
		worksheet = workbook.getSheet(workSheetName);
		this.testCaseName = testCaseName;
		rows = getRowCount();
		testCaseStartRow = getTestCaseStartRow();
		testCaseEndRow = getTestCaseEndRow();
		usedColumnsCount = getUsedColumnsCount();
		iterationCount = getIterationCount();
	}

	public ExcelLib()
	{
		//Do nothing
	}
	
	public int iteratorCount()
	{
		return iterationCount;
	}
	private int getIterationCount()
	{
		try 
		{
			for(int i =testCaseStartRow; i <= testCaseEndRow; i++)
			{
				if(getCellData(usedColumnsCount,i).equalsIgnoreCase("Yes"))
				{
					iterationCount++;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(iterationCount > 0)
		{
			System.out.println("*************************************************************************************");
			System.out.println("Total number of iterations selected for test script: '"+testCaseName+"' is"+" "+iterationCount);	
			System.out.println("*************************************************************************************");
		}
		else
		{
			System.out.println("*************************************************************************************");
			System.out.println("Total number of iterations selected is 0. Please check execute column in TestData.xls file");
			System.out.println("*************************************************************************************");
		}
		return iterationCount;
	}

	/*Return Two Dimensional Array to DataProvider*/	
	public Object[][] getTestdata()
	{
		int row = 0;
		int col = 0;
		String data[][] = new String[iterationCount][usedColumnsCount-1];

		//Get the Test Data
		for(int i =testCaseStartRow; i <= testCaseEndRow; i++){
			col = 0;
			boolean flag = false;
			if(getCellData(usedColumnsCount,i).equalsIgnoreCase("Yes"))
			{
				flag = true;
				for(int j = 1; j < usedColumnsCount; j++)
				{
					data[row][col] = getCellData(j, i);
					col++;
				}
			}
			if(flag)
			{
				row++;
			}
		}
		return data;
	}

	/*Get Cell Data*/
	private String getCellData(int col, int row) 
	{
		return worksheet.getCell(col, row).getContents();
	}

	public WritableWorkbook writeableWorkbook(Workbook exisitngworkbook, String executionWorkBookPath) throws IOException
	{
		@SuppressWarnings("static-access")
		WritableWorkbook WriteWorkbook = workbook.createWorkbook(new File(executionWorkBookPath), exisitngworkbook);
		return WriteWorkbook;
	}

	public WritableSheet WorkSheet(WritableWorkbook WriteWorkbook, String sheetName)
	{
		WritableSheet sheet = WriteWorkbook.getSheet(sheetName);
		return sheet;
	}

	public void writeCellDataToRow(Workbook writableWorkbook, String sheetName, int row, int column, String result) throws FileNotFoundException 
	{
		try
		{
			@SuppressWarnings("unused")
			WritableCell cell;
			Label label = new Label(column, row, result);
			cell = (WritableCell) label;

			WorkSheet((writeableWorkbook(writableWorkbook, executionWorkBookName)), sheetName).getWritableCell(column,row);			
			((WritableWorkbook) WorkSheet(writeableWorkbook(writableWorkbook, executionWorkBookName), sheetName)).write();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/*Get TestCase Start Row*/
	private int getTestCaseStartRow()
	{
		try 
		{
			for(int i = 0; i < rows; i++)
			{
				if(worksheet.getCell(0,i).getContents().equals(testCaseName.trim()))
				{
					testCaseStartRow = i;
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return testCaseStartRow;
	}

	/*Get Test Case End Row*/
	private int getTestCaseEndRow()
	{
		try 
		{
			for(int i = 0; i < rows; i++)
			{
				if(worksheet.getCell(0,i).getContents().equals(testCaseName.trim()))
				{
					testCaseEndRow = i;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return testCaseEndRow;
	}

	/*Get the Columns Count for the referenced test case*/
	private int getUsedColumnsCount()
	{
		try 
		{
			int count = 0;
			while(!worksheet.getCell(count,testCaseStartRow-1).getContents().equalsIgnoreCase("Execute")){
				usedColumnsCount = count++;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return usedColumnsCount+1;
	}

	/*Gets the total number of row count in the excel sheet*/
	private int getRowCount() 
	{
		return worksheet.getRows();
	}

	/* Create a workbook if it does not exist with respect to date*/
	public void writeWorkBook(String[][] data)
	{
		try
		{
			/*Work book path creation and declarations*/
			//********************************************************************************************************************************
			wbFILE = new File(currentDir +"\\Execution");
			if (!wbFILE.exists())
			{
				wbFILE.mkdir();
			}

			wbFILE = new File(currentDir +"\\Execution\\"+wbDate1[1]+"_"+wbDate1[2]+"_"+wbDate1[5]);
			if (!wbFILE.exists())
			{
				wbFILE.mkdir();
			}

			//If spreadsheet exists use the existing one, otherwise create a new one
			wbFILE = new File(executionWorkBookName);

			//Create writable workbook variable
			WritableWorkbook writableWorkBook = null;
			String sheetName = "Execution_Status";
			if (!wbFILE.exists())
			{
				writableWorkBook = Workbook.createWorkbook(wbFILE);
			}
			else
			{
				//Get the current workbook
				Workbook existingWorkBook = Workbook.getWorkbook(wbFILE);
				writableWorkBook = writeableWorkbook(existingWorkBook, executionWorkBookName);
			}

			//Create sheet if it is not available or use the existing one
			boolean sheetExist = false;
			for(int i = 0; i < writableWorkBook.getNumberOfSheets();)
			{
				sheetExist = true;
				break;
			}

			//Create a new sheet
			if (sheetExist == false)
			{
				writableWorkBook.createSheet(sheetName, 0);
			}

			//Get Sheet to be edited
			sheetToEdit = WorkSheet(writableWorkBook, sheetName);

			//********************************************************************************************************************************

			/*Write to sheet*/
			//Create column header if not present
			if((sheetToEdit.getColumns() == 0) && (sheetToEdit.getRows() == 0))
			{
				//Add columns heading
				addColumn(data);
			}

			//Add rows depending on the data provided
			addRows(data);

			//Write to workbook
			writableWorkBook.write();

			//Close the workbook
			writableWorkBook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addColumn(String[][] data) throws WriteException
	{
		try
		{
			int columnCount = sheetToEdit.getColumns();
			for(int i = 0; i <data[0].length; i++)
			{
				font = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
				cellStyle = new WritableCellFormat(font);
				cellStyle.setBackground(Colour.ORANGE);
				cellStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
				label = new Label(i + columnCount, 0, data[0][i], cellStyle);
				sheetToEdit.addCell(label);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addRows(String[][] data) throws WriteException
	{
		int rowCount = sheetToEdit.getRows();

		//Row Loop
		for(int j = 0; j < data.length; j++)
		{
			rowCount = sheetToEdit.getRows();
			if(j==0)
			{
				continue;
			}
			//Column Loop
			for(int i = 0; i < data[0].length; i++)
			{
				font = new WritableFont(WritableFont.TIMES, 11, WritableFont.NO_BOLD);
				cellStyle = new WritableCellFormat(font);
				cellStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
				label = new Label(i, rowCount, data[j][i], cellStyle);
				sheetToEdit.addCell(label);
			}
		}
	}
}