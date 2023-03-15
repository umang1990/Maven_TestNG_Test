package TestScript;

import org.testng.annotations.Test;

public class Group_Demo2 {
	@Test(groups="featureOne")
  public void insert() {
	  System.out.println("Group Demo2 1");
  }
  
  @Test(groups="featureTwo")
  public void insert1() {
	  System.out.println("Group Demo2 2");
  }
  @Test(groups="featureThree")
  public void insert2() {
	  System.out.println("Group Demo2 3");
  }

}
