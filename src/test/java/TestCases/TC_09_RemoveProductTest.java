package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.DetailsPageObject;
import PageObjects.cartPage;

public class TC_09_RemoveProductTest extends BaseClass {

    @Test
    public void verifyProductRemovalFromCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        HomePage home = new HomePage(driver);
        DetailsPageObject details = new DetailsPageObject(driver);
        cartPage cart = new cartPage(driver);

        home.handleCookiePopup();
        home.searchProduct("Chair");

        // ✅ SAFE CLICK PRODUCT (stale fix)
        By productLocator = By.xpath("(//a[contains(@href,'product')])[1]");

        for (int i = 0; i < 3; i++) {
            try {
                WebElement product = wait.until(
                        ExpectedConditions.elementToBeClickable(productLocator));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", product);

                product.click();
                break;

            } catch (StaleElementReferenceException e) {
                System.out.println("Retry clicking product...");
            }
        }

        // Switch tab
        cart.windowUtil.switchToNewWindow();

        // ✅ Correct Add to Cart locator
        By addToCartBtn = By.xpath("//button[contains(.,'Add to Cart') or contains(.,'ADD TO CART')]");

        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addToCartBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

        // Go to cart
        details.clickGoToCart();

        // Wait cart page
        By cartItem = By.xpath("//div[contains(@class,'cart')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItem));

        // ✅ REMOVE PRODUCT
        By removeBtn = By.xpath("//span[contains(text(),'Remove') or contains(text(),'Delete')]");

        WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
        remove.click();

        // Confirm (if popup)
        try {
            By confirm = By.xpath("//button[contains(.,'Yes') or contains(.,'Remove')]");
            wait.until(ExpectedConditions.elementToBeClickable(confirm)).click();
        } catch (Exception e) {
            System.out.println("No confirmation popup");
        }

        // ✅ VERIFY EMPTY CART
        By emptyMsg = By.xpath("//*[contains(text(),'empty') or contains(text(),'Empty')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyMsg));

        Assert.assertTrue(driver.findElement(emptyMsg).isDisplayed(),
                "Cart is not empty!");

        System.out.println("TC_09 Passed");
    }
}