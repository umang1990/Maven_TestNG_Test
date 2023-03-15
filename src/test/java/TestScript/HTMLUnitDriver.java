package TestScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HTMLUnitDriver {
  @Test(retryAnalyzer = RetryAnalyserImpl.class)//if get error check three times means it will going to run three times
  public void HtmlUnitDriver() {
	// TODO Auto-generated method stub
			WebDriver driver = new HtmlUnitDriver();//Without open browser run all testcases and get result
			driver.get("https://www.google.com/");
			Assert.assertEquals(driver.getTitle(), "Google");
			WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
			w1.sendKeys("Java Tutorial");
			w1.submit();
			Assert.assertEquals(driver.getTitle(), " Tutorial - Google Search");
  }
}
