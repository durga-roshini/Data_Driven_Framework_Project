package packageEvents;

import org.openqa.selenium.WebElement;
import packageObjects.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
    ElementFetch elementFetch = new ElementFetch();

    public WebElement clickAccountLnk(){
        WebElement element = elementFetch.getWebElement("XPATH", HomePageElements.lnkAccount);
        return element;
    }
    public WebElement clickLoginLnk(){
        WebElement element = elementFetch.getWebElement("XPATH", HomePageElements.lnkLogin);
        return element;
    }
}
