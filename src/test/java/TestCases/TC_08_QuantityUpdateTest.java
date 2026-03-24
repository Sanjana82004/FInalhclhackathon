package TestCases;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.cartPage;
import PageObjects.DetailsPageObject;
import PageObjects.HomePage;

public class TC_08_QuantityUpdateTest extends BaseClass {

    @Test
    public void verifyProductQuantityUpdate() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        HomePage home = new HomePage(driver);
        DetailsPageObject details = new DetailsPageObject(driver);
        cartPage cart = new cartPage(driver);

        // Step 1: Handle cookie
        home.handleCookiePopup();

        // Step 2: Search product
        home.searchProduct("Chair");

        // Step 3: Click first product safely
        By productLocator = By.xpath("(//a[contains(@href,'product')])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(productLocator));

        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(productLocator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", product);

        try {
            product.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            WebElement freshProduct = driver.findElement(productLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", freshProduct);
        }

        // Step 4: Switch to new tab (SAFE)
        String parent = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Debug (important)
        System.out.println("Page Title: " + driver.getTitle());

     // Step 5: Correct Add to Cart locator (Pepperfry FIX)

        By addToCartBtn = By.xpath("//button[contains(@class,'addtocart')]");

        // Wait for button
        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addToCartBtn)
        );

        // Scroll
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);

        // Click using JS
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", addBtn);
        
        // Step 6: Click Go to Cart
        details.clickGoToCart();

        // Step 7: Wait for cart page
        By totalLocator = By.xpath("//div[contains(@class,'cart-you-pay')]//span");

        wait.until(ExpectedConditions.visibilityOfElementLocated(totalLocator));

        String initialTotal = cart.getTotalPriceText();
        System.out.println("Initial Total: " + initialTotal);

        // Step 8: Update quantity
        cart.selectQuantity(0, "2");

        // Step 9: Wait for total update
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElementLocated(totalLocator, initialTotal)
        ));

        String updatedTotal = cart.getTotalPriceText();
        System.out.println("Updated Total: " + updatedTotal);

        // Step 10: Validation
        Assert.assertNotEquals(initialTotal, updatedTotal, "Total price not updated!");

        System.out.println("TC_08 Passed");
    }
}