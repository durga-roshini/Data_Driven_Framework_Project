package packageEvents;

import org.openqa.selenium.WebElement;
import packageObjects.WomenPageElements;
import utils.ElementFetch;

import java.util.List;

public class WomenPageEvents {
    ElementFetch elementFetch = new ElementFetch();
    public WebElement lnkWomenModule(){
        WebElement element = elementFetch.getWebElement("XPATH", WomenPageElements.lnkWomen);
        return element;
    }

    public WebElement clickPriceRange(){
        WebElement element = elementFetch.getWebElement("XPATH", WomenPageElements.priceRange);
        return element;
    }

    public List<WebElement> verifySelectedPrice(){
        List<WebElement> element = elementFetch.getListWebElements("XPATH", WomenPageElements.selectedPrice);
        return element;
    }

    public List<WebElement> fetchListOfPrices(){
        List<WebElement> element = elementFetch.getListWebElements("XPATH", WomenPageElements.priceList);
        return element;
    }
    public WebElement drpSortWomen(){
        WebElement element = elementFetch.getWebElement("XPATH", WomenPageElements.drpdownSortByWomen);
        return element;
    }
    public List<WebElement> fetchListOfProductNames(){
        List<WebElement> element = elementFetch.getListWebElements("XPATH", WomenPageElements.listOFProductsByName);
        return element;
    }
    public List<WebElement> fetchListOfProductPrices(){
        List<WebElement> element = elementFetch.getListWebElements("XPATH", WomenPageElements.listOfProductsByPrice);
        return element;
    }
    public WebElement clickSortSwitch(){
        WebElement element = elementFetch.getWebElement("XPATH", WomenPageElements.sortSwitch);
        return element;
    }
    public WebElement clickCompareBtn(){
        WebElement element = elementFetch.getWebElement("XPATH", WomenPageElements.compareBtn);
        return element;
    }
    public WebElement clickClearAll(){
        WebElement element = elementFetch.getWebElement("XPATH", WomenPageElements.lnkClearAll);
        return element;
    }
}
