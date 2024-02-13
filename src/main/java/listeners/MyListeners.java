package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import base.Base;

import java.io.File;

public class MyListeners extends Base implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		logger.log(Status.PASS,"Test got passed");
		
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String fileName = System.getProperty("user.dir")+ File.separator + "screenshots" + File.separator + iTestResult.getMethod().getMethodName();
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(f, new File(fileName+".png"));
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.log(Status.FAIL,"Test got failed");
		logger.fail(iTestResult.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		logger.log(Status.FAIL,"Time out");
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
	}
	
}
