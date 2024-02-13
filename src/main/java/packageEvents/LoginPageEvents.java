package packageEvents;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import packageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {
    ElementFetch elementFetch = new ElementFetch();

    public void verifyLogin(){
        Assert.assertTrue(elementFetch.getListWebElements("XPATH", LoginPageElements.loginTxt).size()>0, "Login Page Opened.");
    }

    public WebElement enterEmailAddress(){
        WebElement element = elementFetch.getWebElement("ID", LoginPageElements.emailAddressTxt);
        return element;
    }
    public WebElement enterPassword(){
        WebElement element =  elementFetch.getWebElement("ID", LoginPageElements.password);
        return element;
    }
    public WebElement clickLoginButton(){
        WebElement element =  elementFetch.getWebElement("ID", LoginPageElements.loginButton);
        return element;
    }
    public void loginConfirmation(){
        Assert.assertTrue(elementFetch.getListWebElements("XPATH", LoginPageElements.loginConfirm).size()>0, "Log in Successful.");
    }
}
