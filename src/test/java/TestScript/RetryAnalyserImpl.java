package TestScript;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
// this is implemented with HTMLunitDriver Class 
public class RetryAnalyserImpl implements IRetryAnalyzer{

	private int retryCount = 0;
    private static final int maxRetryCount = 3;
  @Override
  public boolean retry(ITestResult result) {
      if(!result.isSuccess()) {
          if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
          }
      }
      	return false;
  }
}