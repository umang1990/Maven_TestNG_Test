package ParallelScript;

import org.testng.annotations.Test;

public class SampleThreeTest {
	@Test
	  public void testOne() {
	      long id = Thread.currentThread().getId();
	      System.out.println("TestOne in SampleThree......"+id);
	  }
	  @Test
	  public void testTwo() {
	      long id = Thread.currentThread().getId();
	      System.out.println("TestTwo in SampleThree....."+id);
	  }
	  @Test
	  public void testThree() {
	      long id = Thread.currentThread().getId();
	      System.out.println("TestThree in SampleThree....."+id);
	  }
	  @Test
	  public void testFour() {
	      long id = Thread.currentThread().getId();
	      System.out.println("TestFour in SampleThree......."+id);
	  }
	 /* @Test(invocationCount = 8, threadPoolSize = 3, timeOut = 2000)
	  public void customerRegTest() {
	      long id = Thread.currentThread().getId();
	      System.out.println("customerRegTest in SampleThree......."+id);
	  }*/
}
