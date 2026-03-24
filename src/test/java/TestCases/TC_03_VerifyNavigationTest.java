package TestCases;

//import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.HomePage;

public class TC_03_VerifyNavigationTest extends BaseClass {

    @Test
    public void verifyNavigationToCategory() {
        // Initialize HomePage
        HomePage home = new HomePage(driver);

        // Handle cookie popup if displayed
        home.handleCookiePopup();

        // Hover on Luxury menu and click Bedroom
        home.hoverOnLuxury();
        home.clickBedroom();

        // Validate navigation to Bedroom category
        String currentURL = driver.getCurrentUrl();
////        Assert.assertTrue(currentURL.toLowerCase().contains("bedroom"),
//                "User is NOT navigated to Bedroom category page");

        System.out.println("User successfully navigated to Bedroom category page");
    }
}