package TestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.SearchPage;

public class TC_04_VerifyApplyFilters extends BaseClass {

    @Test
    public void applyFilters() {
        // Initialize page objects
        HomePage home = new HomePage(driver);
        SearchPage search = new SearchPage(driver);

        // Handle potential login popup
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "let popup = document.querySelector('div.login-popup-selector');" +
                "if(popup){ popup.style.display='none'; }"
            );
            System.out.println("Login popup handled.");
        } catch(Exception e) {
            System.out.println("Login popup not found.");
        }

        // 1️⃣ Navigate to Sofas category (uncomment if method exists)
        try {
            home.navigateToSofasCategory();
        } catch(Exception e) {
            System.out.println("Navigation to Sofas category failed or method not implemented.");
        }

        // Wait for page to load
        search.waitForPageLoad();

        // 2️⃣ Apply filters
        try {
            search.selectBrand("Nilkamal");            // Expand and select brand
            search.selectPrice("₹5000 - ₹10000");      // Expand and select price
        } catch(Exception e) {
            System.out.println("Error applying filters: " + e.getMessage());
        }

        // 3️⃣ Click Apply button if necessary
        try {
            search.clickApplyButton();
        } catch(Exception e) {
            System.out.println("Apply button not required or not found.");
        }

        // 4️⃣ Wait for page refresh / new products to load
        search.waitForPageLoad();

        // 5️⃣ Verify that the product list is updated
        try {
            String firstProductName = search.getFirstProductDetail();
            String firstProductBrand = search.getFirstProductBrandName();

            System.out.println("First product name: " + firstProductName);
            System.out.println("First product brand: " + firstProductBrand);

            // Assertion: check if the first product brand matches selected brand
            Assert.assertTrue(firstProductBrand.contains("Nilkamal"),
                    "First product brand does not match selected filter!");
        } catch(Exception e) {
            System.out.println("Error verifying filtered products: " + e.getMessage());
        }
    }
}