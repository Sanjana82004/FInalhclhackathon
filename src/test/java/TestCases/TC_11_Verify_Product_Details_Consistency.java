package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.DetailsPageObject;
import PageObjects.HomePage;
import PageObjects.SearchPage;
import PageObjects.SearchPage2;
import Utilities.windowHandleUtility;

public class TC_11_Verify_Product_Details_Consistency extends BaseClass {

    DetailsPageObject dp;
    SearchPage2 sp;
    HomePage hp;
    windowHandleUtility who;

    @BeforeMethod
    public void init() {
        dp = new DetailsPageObject(driver);
        sp = new SearchPage2(driver);
        hp = new HomePage(driver);
        who = new windowHandleUtility(driver);
    }

    @Test
    public void verifyProductDetailsConsistency() {

        // Step 1: Search product
        hp.searchProduct("sofa");

        // Step 2: Capture data from Search Page
        String searchProductName = sp.getProductNameByIndex(1);
       
        String searchPrice = sp.getProductPriceByIndex(1);

        // Step 3: Click product and switch window
        sp.clickProductByIndex(1);;
        who.switchToNewWindow();

        // Step 4: Capture data from Details Page
        String detailsProductName = dp.getBrandNameHeader();   
       
        String detailsPrice = dp.getPrice();

        // Step 5: Assertions

        Assert.assertEquals(detailsProductName, searchProductName,
                "Product name mismatch between Search and Details page");


        Assert.assertEquals(detailsPrice, searchPrice,
                "Price mismatch between Search and Details page");
    }
}