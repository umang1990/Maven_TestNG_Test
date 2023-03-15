package TestScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckPoint_Verified {
  @Test
  public void f() throws InterruptedException {
	  WebDriverManager.chromedriver().setup();//Add dependancy for launch webdriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		//Search on google >> Get all List >> Click on fourth option
		WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
		w1.sendKeys("Java Tutorial");
		Thread.sleep(5000);
		w1.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	  
  }
  @Test
  public void SeleniumSearch() throws InterruptedException {
	  WebDriverManager.chromedriver().setup();//Add dependancy for launch webdriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		//Search on google >> Get all List >> Click on fourth option
		WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
		w1.sendKeys("Selenium Tutorial");
		Thread.sleep(5000);
		w1.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
	  
  }
  
}
