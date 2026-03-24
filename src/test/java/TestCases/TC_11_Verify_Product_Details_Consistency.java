package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.DetailsPageObject;
import Utilities.windowHandleUtility;

public class TC_11_Verify_Product_Details_Consistency extends BaseClass {

    DetailsPageObject dp;
    SearchPage sp;
    HomePage hp;
    windowHandleUtility who;

    @BeforeMethod
    public void init() {
        dp = new DetailsPageObject(driver);
        sp = new SearchPage(driver);
        hp = new HomePage(driver);
        who = new windowHandleUtility(driver);
    }

    @Test
    public void verifyProductDetailsConsistency() {

        // Step 1: Search product
        hp.SearchProduct("Sofa");

        // Step 2: Capture values from Search Page
        String searchProductName = sp.getFirstProductName();
        String searchBrandName = sp.getFirstProductBrandName();
        int searchPrice = sp.getFirstProductPrice();

        // Step 3: Click product and switch window
        sp.clickFirstProduct();
        who.switchToNewWindow();

        // Step 4: Capture values from Details Page
        String detailsProductName = dp.getBrandNameHeader();
        String detailsBrandName = dp.getBrandNameTop();
        int detailsPrice = dp.getPriceValue();

        // Step 5: Assertions

        Assert.assertEquals(detailsProductName, searchProductName,
                "Product name mismatch between Search and Details page");

        Assert.assertEquals(detailsBrandName, searchBrandName,
                "Brand name mismatch between Search and Details page");

        Assert.assertEquals(detailsPrice, searchPrice,
                "Price mismatch between Search and Details page");
    }
}