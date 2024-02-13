package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonStudio {
    public static WebDriver driver;
    public static MyXLSReader excelReader;

    Properties prop;
    public static String takeScreenShot(){
        String screenShotCode = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        return screenShotCode;
    }
    public static File takeScreenShotFile(){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        return screenshot;
    }
    public static String takeScreenShotWithPath(String fileName) throws IOException {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(System.getProperty("user.dir"+"\\Reports\\"+"fileName"+".png"));
        FileUtils.copyFile(sourceFile,destinationFile);
        return destinationFile.getAbsolutePath();
    }
    public static void WebimplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    public void switchToWindow(String title) throws InterruptedException {
        Thread.sleep(5000);
        Set<String> availableWindows = driver.getWindowHandles();
        if (availableWindows.size() >= 1) {
            try {
                for (String windowId : availableWindows) {
                    System.out.println("windown name" + driver.switchTo().window(windowId).getTitle());
                }
            } catch (NoSuchWindowException e) {
                System.out.println(e);
            }
        }
    }

    public WebDriver openBrowserExcelInput(String browserName) throws IOException {

        prop = new Properties();
        File file = new File(System.getProperty("user.dir")+"/src/test/resources/data.properties");
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);

        if(browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }else if(browserName.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }else if(browserName.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));

        return driver;

    }
    @DataProvider
    public Object[][] dataSupplier(Method m) {
        Object[][] obj = null;
        try {
            excelReader = new MyXLSReader(System.getProperty("user.dir")+"/Input/UserDataSheet.xlsx");
            obj = DataUtil.getTestData(excelReader, m.getName(), "Data");

        }catch(Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
