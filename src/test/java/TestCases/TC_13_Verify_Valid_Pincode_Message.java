package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.DetailsPageObject;
import PageObjects.SearchPage;
import PageObjects.HomePage;
import Utilities.windowHandleUtility;

public class TC_13_Verify_Valid_Pincode_Message extends BaseClass {

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
    public void verifyValidPincodeMessage() {

        // Step 1: Search product
        hp.SearchProduct("Sofa");

        // Step 2: Open product details
        sp.clickFirstProduct();
        who.switchToNewWindow();

        // Step 3: Enter valid pincode
        dp.enterPincode("411033");   // Example valid pincode
        dp.clickLocate();

        // Step 4: Validate message
        Assert.assertTrue(
                dp.isValidPincodeMessageDisplayed(),
                "Valid pincode delivery message is not displayed correctly"
        );
    }
}