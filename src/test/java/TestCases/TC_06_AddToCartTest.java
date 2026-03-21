package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC_06_AddToCartTest extends BaseClass {

    WebDriver driver;
    HomePage home;
    WebDriverWait wait;

    

    @Test
    public void verifyAddToCartFunctionality() {

        // 1. Search for a product
        home.searchProduct("Sofa");

        // 2. Click on the first product from search results
        home.clickFirstProduct();

        // 3. Switch to new tab if product opens in new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // 4. Click Add to Cart button
        home.clickAddToCart();

        // 5. Click on cart icon to view cart
        home.clickCart();

        // 6. Validate cart icon is displayed
        Assert.assertTrue(home.isCartDisplayed(), "Cart icon should be visible after adding product");

        System.out.println("Product successfully added to the cart and cart icon is visible.");
    }

    }
