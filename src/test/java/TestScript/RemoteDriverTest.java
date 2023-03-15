package TestScript;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v105.browser.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;


public class RemoteDriverTest {
  @Test
  public void remoteAppTest() throws MalformedURLException, InterruptedException {
	  
	  ChromeOptions options = new ChromeOptions();
	  options.setCapability(CapabilityType.PLATFORM_NAME,Platform.WINDOWS);
	  //options.setCapability(CapabilityType.BROWSER_VERSION,BrowserVersion.CHROME);
	  String strHub = "http://192.168.0.104:4444/";
	  WebDriver driver = new RemoteWebDriver(new URL(strHub), options);
	  driver.get("https://www.google.com/");
		WebElement w1 = driver.findElement(By.xpath("//input[@title='Search']"));
		w1.sendKeys("Java Tutorial");
		//Thread.sleep(5000);
		w1.submit();
		System.out.println("Page Title:"+driver.getTitle());
	  
	  
	  
	  
  }
}
