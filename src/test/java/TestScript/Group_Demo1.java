package TestScript;

import org.testng.annotations.Test;

public class Group_Demo1 {
	@Test(groups="featureOne")
	  public void insert1() {
		  System.out.println("Group Demo1 1");
	  }
	  
	@Test(groups="featureTwo")
	  public void insert2() {
		  System.out.println("Group Demo1 2");
	  }
	  @Test(groups="featureThree")
	  public void insert3() {
		  System.out.println("Group Demo1 3");
	  }
}
