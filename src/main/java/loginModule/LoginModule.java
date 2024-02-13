package loginModule;

import base.Base;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import packageEvents.HomePageEvents;
import packageEvents.LoginPageEvents;

public class LoginModule extends Base {
    HomePageEvents homePageEvents = new HomePageEvents();
    LoginPageEvents loginPageEvents = new LoginPageEvents();

    public void Application_Login(String username, String password){
        try {
            homePageEvents.clickAccountLnk().click();
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Home Page").build());
            homePageEvents.clickLoginLnk().click();
            logger.log(Status.PASS,"Log In page opened.");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Log In Page").build());
           // logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShotWithPath("Test"),"Title").build());

            loginPageEvents.verifyLogin();
            logger.log(Status.PASS,"Log In page Verified.");

            loginPageEvents.enterEmailAddress().sendKeys(username);
            logger.log(Status.PASS,"Mail Entered");
            loginPageEvents.enterPassword().sendKeys(password);
            logger.log(Status.PASS,"Password Entered");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Credentials Entered").build());

            loginPageEvents.clickLoginButton().click();
            logger.log(Status.PASS,"Log in Button Clicked.");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Log in").build());

            loginPageEvents.loginConfirmation();
            logger.log(Status.PASS,"Log In is Successful");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Log in").build());

        }catch(Exception e){
            logger.log(Status.FAIL, "Login Failed");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Error").build());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.log(Status.FAIL,"Time out");
    }
}
