package TestScript;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo_1 {
	
	WebDriver driver;
	@BeforeMethod
	public void Setup() 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();  
			  
		}
  //@Test(priority=3)
	@Test
  public void JavaSearchTest() {
	  
	driver.get("https://www.google.com/");
	WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
	w1.sendKeys("Java Tutorial");
	w1.submit();
	Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	
  }
  //@Test(priority=2)
	@Test(enabled=false)
  public void SeleniumSearchTest() {
	  driver.get("https://www.google.com/");
	  WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
		w1.sendKeys("Selenium Tutorial");
		w1.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
	  
  }
  //@Test(priority=1)
  //@Test(dependsOnMethods = "JavaSearchTest")
	@Test(alwaysRun=true,dependsOnMethods = "JavaSearchTest")//If JavasearchTest class get error but cucumber class should run
  public void cucumberSearchTest() {
	  driver.get("https://www.google.com/");
	  WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
		w1.sendKeys("Cucumber Tutorial");
		w1.submit();
		Assert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search");
	  
  }
  @AfterMethod
  public void TearDown() {
  driver.close();
  
  }
  
}
