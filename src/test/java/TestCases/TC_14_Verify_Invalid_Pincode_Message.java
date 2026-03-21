package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.DetailsPageObject;
import PageObjects.HomePage;
import PageObjects.SearchPage;
import Utilities.windowHandleUtility;

public class TC_14_Verify_Invalid_Pincode_Message extends BaseClass {

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
    public void verifyInvalidPincodeMessage() {

        // Step 1: Search product
        hp.searchProduct("Sofa");

        // Step 2: Open product details
        sp.clickFirstProduct();
        who.switchToNewWindow();

        // Step 3: Enter invalid pincode
        dp.enterPincode("000000");   // Invalid pincode
        dp.clickLocate();

        // Step 4: Validate error message
        Assert.assertTrue(
                dp.isInvalidPincodeMessageDisplayed(),
                "Invalid pincode message is not displayed correctly"
        );
    }
}