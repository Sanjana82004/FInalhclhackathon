package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.DetailsPageObject;
import PageObjects.SearchPage;
import PageObjects.HomePage;
import Utilities.windowHandleUtility;

public class TC_12_Verify_Discount_Calculation extends BaseClass {

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
    public void verifyDiscountCalculation() {

        // Step 1: Search product
        hp.SearchProduct("Sofa");

        // Step 2: Click on first product
        sp.clickFirstProduct();

        // Step 3: Switch to product details window
        who.switchToNewWindow();

        // Step 4: Validate discount
        Assert.assertTrue(
                dp.isDiscountCorrect(),
                "Discount calculation is incorrect on product details page"
        );
    }
}