package TestBatch;

import base.Base;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.google.common.collect.Ordering;
import loginModule.LoginModule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.Test;
import packageEvents.WomenPageEvents;
import packageObjects.WomenPageElements;
import utils.DataUtil;
import utils.ElementFetch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Batch1 extends Base {
    LoginModule loginModule = new LoginModule();
    ElementFetch elementFetch = new ElementFetch();
    WomenPageEvents womenPageEvents = new WomenPageEvents();
    @Test(enabled = true, dataProvider="dataSupplier")
    public void Verifying_Price_Range(HashMap<String,String> hMap, Method m){
        try {

           /* if(!DataUtil.isRunnable(excelReader, m.getName(), "Testcases") || hMap.get("Runmode").equals("N")) {

                throw new SkipException("Skipping the test as the runmode is set to N");

            }*/
            //openBrowser(hMap.get("Browser"));
            loginModule.Application_Login(hMap.get("Username"), hMap.get("Password"));
            womenPageEvents.lnkWomenModule().click();
            WebimplicitWait(driver);
            logger.log(Status.PASS, "Women page opened.");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Women Page").build());

            String[] minMaxRange = hMap.get("PriceRange").split("-");
            float minRange = Float.parseFloat(minMaxRange[0].replace("$",""));
            float maxRange = Float.parseFloat(minMaxRange[1].replace("$",""));

            driver.findElement(By.xpath("//span[contains(text(),'$"+minRange+"')]")).click();
            //womenPageEvents.clickPriceRange().click();
            logger.log(Status.PASS, " Price range clicked.");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Price Range").build());

            boolean verifySelectedPriceRAnge = womenPageEvents.verifySelectedPrice().size() > 0;
            String priceRange = String.valueOf(womenPageEvents.verifySelectedPrice().get(0).getText());
            System.out.println(priceRange + verifySelectedPriceRAnge);

            if (verifySelectedPriceRAnge) {
                logger.log(Status.PASS, "Selected price range products are shown.");
                WebimplicitWait(driver);
                logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Selected Price Range").build());
            }

            //Verifying Price ranges according to selected range
            for(WebElement price : womenPageEvents.fetchListOfPrices()) {
                float productPrice = Float.parseFloat(price.getText().replace("$",""));
                if(productPrice>=minRange && productPrice<= maxRange){
                    logger.log(Status.PASS, "Product price"+productPrice+" is in between range of "+minRange+" And "+maxRange);
                }else {
                    logger.log(Status.FAIL, "Product price"+productPrice+" is not in between range of "+minRange+" And "+maxRange);
                }
            }
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Price Range verified").build());
        }catch(Exception e){
            logger.log(Status.FAIL, "Test Case Failed");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Error").build());
            e.printStackTrace();
            driver.quit();
        }
    }

    @Test(enabled = true, dataProvider="dataSupplier")
    public void Verify_Sorting_on_Product_prices(HashMap<String,String> hMap, Method m){
        try {

            loginModule.Application_Login(hMap.get("Username"), hMap.get("Password"));
            womenPageEvents.lnkWomenModule().click();
            WebimplicitWait(driver);
            logger.log(Status.PASS, "Women page opened.");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Women Page").build());

            /*
            //Sorting by Name------------------------------------
            Select drpCountry = new Select(womenPageEvents.drpSortWomen());
            drpCountry.selectByVisibleText("Name");
            logger.log(Status.PASS, "Name selected from Sort dropdown");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Dropdown").build());

            List<String> productNameListAsc = null;
            for(WebElement productName: womenPageEvents.fetchListOfProductNames()){
                productNameListAsc.add(productName.getText());
            }

            //Verifying Ascending order
            boolean isSortedAsc = Ordering.natural().isOrdered(productNameListAsc);
            if(isSortedAsc){
                logger.log(Status.PASS, "Product Names are sorted in Ascending order"+ Arrays.asList(productNameListAsc));
            }else {
                logger.log(Status.FAIL, "Product Names are not sorted in Ascending order"+ Arrays.asList(productNameListAsc));
            }

            womenPageEvents.clickSortSwitch().click();
            logger.log(Status.PASS, "Sort Arrow clicked");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Sort Arrow").build());

            List<String> productNameListDsc = null;
            for(WebElement productName: womenPageEvents.fetchListOfProductNames()){
                productNameListDsc.add(productName.getText());
            }

            //Verifying Descending order
            boolean isSortedDsc = Ordering.natural().reverse().isOrdered(productNameListDsc);
            if(isSortedDsc){
                logger.log(Status.PASS, "Product Names are sorted in Descending order"+ Arrays.asList(productNameListDsc));
            }else {
                logger.log(Status.FAIL, "Product Names are not sorted in Descending order"+ Arrays.asList(productNameListDsc));
            }

             */

            //Sorting by Price-------------------------------------------
            Select drpCountry = new Select(womenPageEvents.drpSortWomen());
            drpCountry.selectByVisibleText("Price");
            logger.log(Status.PASS, "Price selected from Sort dropdown");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Dropdown").build());

            List<Float> productPriceListAsc = new ArrayList<Float>();
            for(WebElement productPrice: womenPageEvents.fetchListOfProductPrices()){
                Float item = Float.parseFloat(productPrice.getText().replace("$",""));
                productPriceListAsc.add(item.floatValue());
            }
            System.out.println(Arrays.asList(productPriceListAsc));
            //Verifying Ascending order
            boolean isSortedAscPrice = Ordering.natural().isOrdered(productPriceListAsc);
            if(isSortedAscPrice){
                logger.log(Status.PASS, "Product Prices are sorted in Ascending order"+ Arrays.asList(productPriceListAsc));
            }else {
                logger.log(Status.FAIL, "Product Prices are not sorted in Ascending order"+ Arrays.asList(productPriceListAsc));
            }

            womenPageEvents.clickSortSwitch().click();
            logger.log(Status.PASS, "Sort Arrow clicked");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Sort Arrow").build());

            List<Float> productPriceListDsc = new ArrayList<Float>();
            for(WebElement productPrice: womenPageEvents.fetchListOfProductPrices()){
                Float item = Float.parseFloat(productPrice.getText().replace("$",""));
                productPriceListAsc.add(item.floatValue());
            }
            System.out.println(Arrays.asList(productPriceListDsc));

            //Verifying Descending order
            boolean isSortedDscPrice = Ordering.natural().reverse().isOrdered(productPriceListDsc);
            if(isSortedDscPrice){
                logger.log(Status.PASS, "Product Prices are sorted in Descending order"+ Arrays.asList(productPriceListDsc));
            }else {
                logger.log(Status.FAIL, "Product Prices are not sorted in Descending order"+ Arrays.asList(productPriceListDsc));
            }
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Sort Arrow").build());
        }catch(Exception e) {
            logger.log(Status.FAIL, "Test Case Failed");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Error").build());
            e.printStackTrace();
            driver.quit();
        }
    }

    @Test(enabled = true, dataProvider="dataSupplier")
    public void Performing_comparision_between_products(HashMap<String,String> hMap, Method m){
        try {

            loginModule.Application_Login(hMap.get("Username"), hMap.get("Password"));
            womenPageEvents.lnkWomenModule().click();
            WebimplicitWait(driver);
            logger.log(Status.PASS, "Women page opened.");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Women Page").build());

           /* int isComparePresent = elementFetch.getListWebElements("XPATH","//span[contains(text(),'Compare Products')]").size();
            if(isComparePresent>0){
                womenPageEvents.clickClearAll().click();
                logger.log(Status.PASS, "Clear All Clicked");

                Alert alert = driver.switchTo().alert();
                alert.accept();
                WebimplicitWait(driver);
                logger.log(Status.PASS, "Comparision Liat Cleared.");
            }

            */
            for(int i=0; i<2; i++){
                elementFetch.getWebElement("XPATH","(//a[@class='link-compare'])["+(i+1)+"]").click();
                logger.log(Status.PASS, "Product added to compare list");
                WebimplicitWait(driver);
                logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Product").build());

                String productName = elementFetch.getWebElement("XPATH","(//div[@class = 'category-products']//h2[@class = 'product-name'])["+(i+1)+"]").getText();
                Assert.assertEquals(elementFetch.getWebElement("XPATH", WomenPageElements.verifyCompareSuccessMsg),"The product "+productName+" has been added to comparison list.");
                logger.log(Status.PASS, "Product added to Compare Successfully");
                WebimplicitWait(driver);
                logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Product").build());
            }

            womenPageEvents.clickCompareBtn().click();
            logger.log(Status.PASS, "Compare Button Clicked");
            WebimplicitWait(driver);

            switchToWindow("Products Comparison List");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Compare Window").build());
            try {
                Assert.assertEquals(elementFetch.getWebElement("XPATH", "(//h2[@class = 'product-name'])[1]"), elementFetch.getWebElement("XPATH", "(//h2[@class = 'product-name'])[2]"));
                logger.log(Status.PASS, "Product Name is same");
            }catch(Exception e){
                logger.log(Status.PASS, "Product Name is not same");
            }
            try {
                Assert.assertEquals(elementFetch.getWebElement("XPATH", "(//span[contains(text(),'Description')]/following::div[1])[1]"),elementFetch.getWebElement("XPATH", "(//span[contains(text(),'Description')]/following::div[1])[2]"));
                logger.log(Status.PASS, "Description is same");
            }catch(Exception e){
                logger.log(Status.PASS, "Description is not same");
            }
            try {
                Assert.assertEquals(elementFetch.getWebElement("XPATH", "(//span[contains(text(),'Short Description')]/following::div[1])[1]"),elementFetch.getWebElement("XPATH", "(//span[contains(text(),'Short Description')]/following::div[1])[2]"));
                logger.log(Status.PASS, "Short Description is same");
            }catch(Exception e){
                logger.log(Status.PASS, "Short Description is not same");
            }
            try {
                Assert.assertEquals(elementFetch.getWebElement("XPATH", "(//span[contains(text(),'SKU')]/following::div[1])[1]"),elementFetch.getWebElement("XPATH", "(//span[contains(text(),'SKU')]/following::div[1])[2]"));
                logger.log(Status.PASS, "SKU is same");
            }catch(Exception e){
                logger.log(Status.PASS, "SKU is not same");
            }
            try {
                Assert.assertEquals(elementFetch.getWebElement("XPATH", "(//span[contains(text(),'Color')]/following::div[1])[1]"),elementFetch.getWebElement("XPATH", "(//span[contains(text(),'Color')]/following::div[1])[2]"));
                logger.log(Status.PASS, "Color is same");
            }catch(Exception e){
                logger.log(Status.PASS, "Color is not same");
            }

            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Comparison Performed").build());
        }catch(Exception e){
            logger.log(Status.FAIL, "Test Case Failed");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Error").build());
            e.printStackTrace();
            driver.quit();
        }
    }

    @Test(enabled = true, dataProvider="dataSupplier")
    public void Adding_products_to_wishlist(HashMap<String,String> hMap, Method m,String product1, String product2){
        try {
            loginModule.Application_Login(hMap.get("Username"), hMap.get("Password"));
            womenPageEvents.lnkWomenModule().click();
            WebimplicitWait(driver);
            logger.log(Status.PASS, "Women page opened.");
            WebimplicitWait(driver);
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Women Page").build());

            //((//a[contains(text(),'Lafayette Convertible Dress')])[1]/following::a[contains(text(),'Add to Wishlist')])[1]

            int productCheck = driver.findElements(By.xpath("(//a[contains(text(),'"+product1+"')])[1]")).size();
            if(productCheck>0){
                logger.log(Status.PASS, "Product Available");
                driver.findElement(By.xpath("((//a[contains(text(),'"+product1+"')])[1]/following::a[contains(text(),'Add to Wishlist')])[1]")).click();
                logger.log(Status.PASS, "Product Added to wish List");
                logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(), "Women Page").build());


            }else {
                logger.log(Status.PASS, "Product Not Available");
            }

        }catch(Exception e){
            logger.log(Status.FAIL, "Test Case Failed");
            logger.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot(),"Error").build());
            e.printStackTrace();
            driver.quit();
        }
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.log(Status.FAIL,"Time out");
    }
}
