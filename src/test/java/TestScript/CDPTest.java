package TestScript;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v105.log.Log;
import org.openqa.selenium.devtools.v105.network.Network;
import org.openqa.selenium.devtools.v106.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v105.log.Log;
import org.openqa.selenium.devtools.v105.network.Network;
import org.openqa.selenium.devtools.v105.network.model.Headers;
import org.openqa.selenium.devtools.v105.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v106.emulation.Emulation;
import io.github.bonigarcia.wdm.WebDriverManager;

//We can run test cases in responsive mode and etc.
//Use ChromeDevTools application for responsive / network tab / listeners and all.
public class CDPTest {
	
	
	EdgeDriver driver;
	DevTools devTools;
	@BeforeMethod
	public void setup()
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		devTools = driver.getDevTools();
		devTools.createSession();
	}
 /* @Test
  public void deviceModeTest() {
	  
	  Map deviceMetrics = new HashMap()
	  {{
		  put("width",600);
		  put("height",600);
		  put("deviceScaleFactor",50);
		  put("mobile",true);
	  }};
	  
	  driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
	  driver.get("https://www.selenium.dev/");
	  
	  
  }*/
 //Find all Request API call in network tab
 /* @Test
  public void CaptureNWTrafficTest()
  {
	  devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
	  devTools.addListener(Network.requestWillBeSent(),
			  entry -> {
				  System.out.println("Request URI..."+ entry.getRequest().getUrl()+ "\n" + "With Method : "+entry.getRequest().getMethod());
			  });
	  driver.get("https://www.google.com");
	  devTools.send(Network.disable());
  }*/
  
  //Capture Console log
  //Is there any console error in application level so we can get it
  /*@Test
  public void CaptureConsoleLogTest()
  {
	  devTools.send(Log.enable());
	  devTools.addListener(Log.entryAdded(),
	  logEntry -> {
		  System.out.println("Log..."+logEntry.getText());
		  System.out.println("Log Level ...."+logEntry.getLevel());
		
		  
	  });
	  driver.get("https://the-internet.herokuapp.com/disappearing_elements");
	  
  }*/
  
  //GeoLocation - get near by location by entering latlong.
 /* @Test
  public void geoLocation()
  {
	  devTools.send(Emulation.setGeolocationOverride(
			  Optional.of(27.664827), 
			  Optional.of(-81.515755), 
			  Optional.of(100)
			  ));
	  driver.get("https://oldnavy.gap.com/stores");
  }*/
  
  //Basic Authentication. using the token we are authenticate.
  @Test
  public void basicAuthTest() {
      devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
         Map<String, Object> headers = new HashMap<>();
         String strUser = "admin";
         String strPwd = "admin";
         //Generating token - Basic YWRtaW46YWRtaW4=
         String basicAuth = "Basic "+new String(new Base64().encode(
                 String.format("%s:%s", strUser, strPwd).getBytes()));
         
         System.out.println("Auth .... + "+basicAuth);
         //Set Header - Auth Token
         headers.put("Authorization", basicAuth);
         devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
         
         driver.get("http://the-internet.herokuapp.com/basic_auth");
         String strMsg = driver.findElement(By.cssSelector("div.example p")).getText();
        Assert.assertEquals(strMsg, "Congratulations! You must have the proper credentials.");
       
 }


	  
	  
}
