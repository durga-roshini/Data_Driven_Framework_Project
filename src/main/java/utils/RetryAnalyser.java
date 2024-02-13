package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying test " + result.getName() + " for " + (retryCount + 1) + " time(s).");
            retryCount++;
            return true;
        }
        return false;
    }
}
