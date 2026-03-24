package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.cartPage;

public class TC_10_DiscountValidationTest extends BaseClass {

    @Test
    public void verifyDiscountCalculation() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        HomePage home = new HomePage(driver);
        cartPage cart = new cartPage(driver);

        home.handleCookiePopup();
        home.searchProduct("Chair");

        // Click product safely
        By productLocator = By.xpath("(//a[contains(@href,'product')])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(productLocator)).click();

        cart.windowUtil.switchToNewWindow();

        // ✅ PRICE LOCATORS
        By originalPrice = By.xpath("//span[contains(@class,'mrp') or contains(@class,'old-price')]");
        By discountPrice = By.xpath("//span[contains(@class,'price') or contains(@class,'offer-price')]");

        WebElement original = wait.until(ExpectedConditions.visibilityOfElementLocated(originalPrice));
        WebElement discount = wait.until(ExpectedConditions.visibilityOfElementLocated(discountPrice));

        // Extract numbers
        double orig = Double.parseDouble(original.getText().replaceAll("[^0-9]", ""));
        double disc = Double.parseDouble(discount.getText().replaceAll("[^0-9]", ""));

        // Calculate %
        double calculatedDiscount = ((orig - disc) / orig) * 100;

        System.out.println("Original: " + orig);
        System.out.println("Discounted: " + disc);
        System.out.println("Calculated Discount: " + calculatedDiscount);

        // Just check discount applied
        Assert.assertTrue(calculatedDiscount > 0, "Discount not applied!");

        System.out.println("TC_10 Passed");
    }
}