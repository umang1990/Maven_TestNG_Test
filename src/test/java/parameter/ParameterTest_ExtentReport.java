package parameter;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import CommonUtility.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterTest_ExtentReport {
	WebDriver driver;
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extenttest;
	@BeforeTest
	public void setupExtent()
	{
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target/SparkReport.html");
		//Below code hide because screenshot functionality is not working along with below code.
	//	.viewConfigurer()
	//	.viewOrder()
	//	.as(new ViewName[] {
	//			ViewName.DASHBOARD,
	//			ViewName.DEVICE,
	//			ViewName.AUTHOR
	//	}).apply();

		reports.attachReporter(spark);
	}
	@Parameters("browser")//Parameter functionality
	@BeforeMethod
	public void Setup(String strBrowser) 
		{
			System.out.println("Browser Name"  +strBrowser);
			if(strBrowser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if (strBrowser.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();  
			  
		}
  
	@Test
  public void JavaSearchTest() {
	extenttest = reports.createTest("JavaSearchTest"); 
	driver.get("https://www.google.com/");
	WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
	w1.sendKeys("Java Tutorial");
	w1.submit();
	Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	
  }
  
	@Test
  public void SeleniumSearchTest() {
		extenttest = reports.createTest("SeleniumSearchTest");
	  driver.get("https://www.google.com/");
	  WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
		w1.sendKeys("Selenium Tutorial");
		w1.submit();
		Assert.assertEquals(driver.getTitle(), "elenium Tutorial - Google Search");
	  
  }
  
  
  @AfterMethod
  public void TearDown(ITestResult result) {
	  extenttest.assignAuthor("AutomationTester : Umang")
      .assignCategory("Regression")
      .assignDevice(System.getProperty("os.name"));
	  if(ITestResult.FAILURE == result.getStatus())
	  {
		  extenttest.log(Status.FAIL, result.getThrowable().getMessage());
		  String strPath = Utility.getScreenshotPath(driver);
		  extenttest.fail(MediaEntityBuilder.createScreenCaptureFromPath(strPath).build());
	  }
	  driver.close();
  
  }
  @AfterTest
  public void finishExtent()
  {
	  reports.flush();
  }
}
