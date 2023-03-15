package TestScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import CommonUtility.Utility;
import freemarker.core.ReturnInstruction.Return;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDriven_Login_Example {
	
	WebDriver driver;
	Properties prop;
	@BeforeTest
	public void loadPropFromFile() throws IOException 
	{
		String path = System.getProperty("user.dir")
				+"//src//test//resources//configFiles//config.properties";
		FileInputStream fin = new FileInputStream(path);
		prop =new Properties();
		prop.load(fin);
		
		
	}
	@BeforeMethod
	public void Setup() 
		{
			String strBrw = prop.getProperty("browser");
			if(strBrw.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(strBrw.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			//WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			driver.manage().window().maximize();  
			  
		}
 // @Test(dataProvider = "LoginData")
 // public void ValidLoginTest(String strUser, String strPwd) {
//		driver.get(prop.getProperty("url"));
	//	driver.findElement(By.xpath(readObjPath("username"))).sendKeys(strUser);
	//	driver.findElement(By.xpath(readObjPath("password"))).sendKeys(strPwd);
	//	driver.findElement(By.xpath(readObjPath("loginBtn"))).click();
	//	boolean isMsg = driver.findElement(By.cssSelector(readObjPath("successMsg"))).isDisplayed();
	//	Assert.assertTrue(isMsg);
	  
 // }
  @Test
  public void ValidLoginTest() throws ParserConfigurationException, SAXException, IOException {
		driver.get(prop.getProperty("url"));
		
		//Read from Excel
		//driver.findElement(By.xpath(readObjPath("username"))).sendKeys(readValue("strUser"));
		//driver.findElement(By.xpath(readObjPath("password"))).sendKeys(readValue("strPwd"));
		//driver.findElement(By.xpath(readObjPath("loginBtn"))).click();
		//Read from Xml
		driver.findElement(By.xpath(readXmldata("uname"))).sendKeys(readValue("strUser"));
		driver.findElement(By.xpath(readXmldata("pwd"))).sendKeys(readValue("strPwd"));
		driver.findElement(By.xpath(readXmldata("loginBtn"))).click();
		boolean isMsg = driver.findElement(By.cssSelector(readObjPath("successMsg"))).isDisplayed();
		Assert.assertTrue(isMsg);
	  
  }
  public String readXmldata(String tagname) throws ParserConfigurationException, SAXException, IOException 
  {
	  String path = System.getProperty("user.dir")+
			  "//src//test//resources//testData/XmlObjRepo.xml";
	  File file = new File(path);
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder build = factory.newDocumentBuilder();
	  Document document = build.parse(file);
	  NodeList list = document.getElementsByTagName("objRep");
	  Node node1=list.item(0);
	  Element elem = (Element)node1;
	  return elem.getElementsByTagName(tagname).item(0).getTextContent();
	  
	  
  }
  public String readValue(String colName) 
  {
	  String colValue = "";
	  String path = System.getProperty("user.dir")+
			  "//src//test//resources//testData/excelRepo.xlsx";
	  FileInputStream fin;
		//.xls -> HSSFWorkbook
		//.xlsx - > XSSFWorkbook
		XSSFWorkbook workbook = null;
		try {
			fin = new FileInputStream(path);
			workbook = new XSSFWorkbook(fin);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet("userDetail");
		int numRows = sheet.getLastRowNum();
		for (int i=1; i<= numRows; i++)
		{
			XSSFRow row = sheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(colName))
			{
				colValue = row.getCell(1).getStringCellValue();
			}
		}
	  return colValue;
  }
  public String readObjPath(String objName)
  {
	  String objPath ="";
	  String path = System.getProperty("user.dir")+
			  "//src//test//resources//testData/excelRepo.xlsx";
	FileInputStream fin;
	//.xls -> HSSFWorkbook
	//.xlsx - > XSSFWorkbook
	XSSFWorkbook workbook = null;
	try {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
	}
	catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	XSSFSheet sheet = workbook.getSheet("loginPage");
	int numRows = sheet.getLastRowNum();
	for (int i=1; i<= numRows; i++)
	{
		XSSFRow row = sheet.getRow(i);
		if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName))
		{
			objPath = row.getCell(1).getStringCellValue();
		}
	}
	  return objPath;
  }
  @DataProvider(name="LoginData")
  public Object[][] getData() throws CsvValidationException, IOException
  {
	  String path = System.getProperty("user.dir")
			  +"//src//test//resources//testData/LoginData.csv";
	  CSVReader reader = new CSVReader (new FileReader(path));
	  ArrayList<Object> dataList = new ArrayList<Object>();
	  String col[];
	  while((col=reader.readNext())!=null)
			  {
		  			Object[] record = {col[0], col[1]};
		  			dataList.add(record);
			  }
	  reader.close();
	  return dataList.toArray(new Object[dataList.size()][]);
  }
  
  @AfterMethod
  public void TearDown() {
	  
	  driver.close();
  
  }
}
