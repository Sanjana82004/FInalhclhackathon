package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.DetailsPageObject;
import PageObjects.HomePage;
import PageObjects.SearchPage2;
import Utilities.windowHandleUtility;

public class TC_12_Verify_Discount_Calculation extends BaseClass {

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
    public void verifyDiscountCalculation() {

       
        hp.searchProduct("Sofa");

        
        sp.clickProductByIndex(2);

       
        who.switchToNewWindow();

        
        Assert.assertTrue(
                dp.isDiscountCorrect(),
                "Discount calculation is incorrect on product details page"
        );
    }
}