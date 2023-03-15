package TestScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleOneTest {
	
	
  @Test
  public void f() throws InterruptedException {
	 // System.setProperty("webdriver.chrome.driver","H:\\Umang Personal\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
	  
	  	WebDriverManager.chromedriver().setup();//Add dependancy for launch webdriver
		WebDriver driver = new ChromeDriver();
	  
	  	//WebDriverManager.edgedriver().setup();
	  	//WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		//Search on google >> Get all List >> Click on fourth option
		WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
		w1.sendKeys("Java Tutorial");
		Thread.sleep(5000);
		w1.submit();
	  
  }
}
