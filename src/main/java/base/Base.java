package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import utils.CommonStudio;
import utils.DataUtil;

public abstract class Base extends CommonStudio {
	public Properties prop;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeTest
	public void beforeTestMethod(){
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ File.separator + "reports" +File.separator +"extentReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Durga Shegu");
	}

	@BeforeMethod
	public void beforeMethodMethod(Method testMethod) throws IOException {
		File file = new File(System.getProperty("user.dir")+"/src/test/resources/data.properties");
		prop = new Properties();
		InputStream fis = new FileInputStream(file);
		prop.load(fis);
		logger = extent.createTest(testMethod.getName());
		String browser = DataUtil.getBrowserType(excelReader, testMethod.getName(), "Testcases");
		System.out.println(browser);
		openBrowser(browser);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethodMethod(ITestResult result){
		if(result.getStatus() == ITestResult.SUCCESS){
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case:"+methodName+"Passed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case:"+methodName+"Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		}
		driver.quit();
	}

	@AfterTest
	public void afterTestMethod(){
		extent.flush();
	}
	public void openBrowser(String browserName) throws IOException {

		File file = new File(System.getProperty("user.dir")+"/src/test/resources/data.properties");
		prop = new Properties();
		InputStream fis = new FileInputStream(file);
		prop.load(fis);
		if(browserName.equalsIgnoreCase("chrome")){
			logger.log(Status.INFO, "Running Test case in Chrome Browser");

			/*String downloadFilepath = "/path/to/download";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			 */
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			/*DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);

			//Setting Properties
			System.setProperty("webDriver.chrome.driver", System.getProperty("user.dir")+"/Drivers");

			 */
			//Initializing Driver
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if(browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			logger.log(Status.INFO, "Running Test case in Edge Browser");
			System.setProperty("webDriver.edge.driver", System.getProperty("user.dir")+"/Drivers");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		/*driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
	}


	public abstract void onTestFailedWithTimeout(ITestResult result);
}
