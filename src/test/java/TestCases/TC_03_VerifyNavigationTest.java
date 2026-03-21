package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;

public class TC_03_VerifyNavigationTest extends BaseClass {

    @Test
    public void verifyNavigationToCategory() {
   
        // Create HomePage object
        HomePage hp = new HomePage(driver);

        driver.get("https://www.pepperfry.com/");
        // Step 1: Verify homepage is loaded (good practice)
        Assert.assertTrue(hp.isMenuDisplayed(), "Homepage not loaded properly");

        // Step 2: Click menu
        hp.clickMenu();

        // Step 3: Hover on Luxury category
        hp.hoverOnLuxury();

        // Step 4: Click Bedroom subcategory
        hp.clickBedroom();

        // Step 5: Validation (URL check)
        String currentURL = driver.getCurrentUrl();

        Assert.assertTrue(currentURL.toLowerCase().contains("bedroom"),
                "User is NOT navigated to category page");

        System.out.println("✅ User successfully navigated to Bedroom category page");
    }
}